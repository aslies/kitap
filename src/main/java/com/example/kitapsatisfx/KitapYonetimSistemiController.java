package com.example.kitapsatisfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KitapYonetimSistemiController {

    @FXML
    private TableView<Kitap> tb_kitaplar;

    @FXML
    private TableColumn<Kitap, Integer> kitapID;

    @FXML
    private TableColumn<Kitap, String> kitapAdi;

    @FXML
    private TableColumn<Kitap, Integer> kitapBirimFiyati;

    @FXML
    private TableColumn<Kitap, String> yazarAdi;

    @FXML
    private TableColumn<Kitap, String> kitapKategori;

    @FXML
    private TableColumn<Kitap, Integer> kitapSayfaSayisi;

    @FXML
    private TableColumn<Kitap, Integer> kitapStokAdedi;

    @FXML
    private Button btn_kitapSil;

    @FXML
    private Button btn_fiyatGuncelle;

    @FXML
    private Button btn_kitapDetayi;

    @FXML
    private ComboBox<String> cmb_istenen;

    @FXML
    private Button btn_hesapla;

    @FXML
    private TextArea ta_sonuc;

    @FXML
    private TextField tf_kitapAdi;

    @FXML
    private TextField tf_yazarAdi;

    @FXML
    private TextField tf_kitapFiyat;

    @FXML
    private PasswordField tf_sayfaSayisi;

    @FXML
    private ComboBox<String> cmb_kategoriler;

    @FXML
    private TextField tf_stokAdedi;

    @FXML
    private Button btn_kitapEkle;

    ObservableList<Kitap> kitaplar = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws SQLException {


        ObservableList<String> istenen = FXCollections.observableArrayList();
        ObservableList<String> kategoriler = FXCollections.observableArrayList();
        istenen.add("En Büyük Değer");
        istenen.add("En Küçük Değer");

        kitapID.setCellValueFactory(new PropertyValueFactory<Kitap,Integer>("kitapID"));
        kitapAdi.setCellValueFactory(new PropertyValueFactory<Kitap,String>("kitapAdi"));
        kitapKategori.setCellValueFactory(new PropertyValueFactory<Kitap,String>("kitapKategori"));
        kitapBirimFiyati.setCellValueFactory(new PropertyValueFactory<Kitap,Integer>("kitapBirimFiyati"));
        kitapSayfaSayisi.setCellValueFactory(new PropertyValueFactory<Kitap,Integer>("kitapSayfaSayisi"));
        kitapStokAdedi.setCellValueFactory(new PropertyValueFactory<Kitap,Integer>("kitapStokAdedi"));
        yazarAdi.setCellValueFactory(new PropertyValueFactory<Kitap,String>("yazarAdi"));

        cmb_istenen.getItems().addAll(istenen);
        for(int i=1;i<=13;i++){
            ResultSet resultSet = null;

            try (Connection connection = new SQLDatabaseConnection().connect()) {
                PreparedStatement st = connection.prepareStatement("SELECT kategoriAdi FROM Kategoriler WHERE kategoriID=?");
                st.setString(1, String.valueOf(i));
                resultSet = st.executeQuery();
                while (resultSet.next()) {
                    kategoriler.add(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cmb_kategoriler.getItems().addAll(kategoriler);
        kitaplariYukle();

    }

    @FXML
    void degerHesapla(ActionEvent event) throws SQLException{
        String istenen = cmb_istenen.getValue();
        String query = null;
        if(istenen.equals("En Büyük Değer")){
            query= "SELECT MAX(kitapBirimFiyati) FROM Kitaplar";
        }
        else if(istenen.equals("En Küçük Değer")){
            query= "SELECT MIN(kitapBirimFiyati) FROM Kitaplar";
        }
        else  if(istenen.equals("Ortalama Değer")){
            query = "SELECT AVG(kitapBirimFiyati) FROM Kitaplar";
        }
        ResultSet resultSet;
        int sonuc = 0;
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            PreparedStatement st = connection.prepareStatement(query);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                sonuc = resultSet.getInt(1);
            }
            ta_sonuc.setText("Hesaplanan değer:"+sonuc);
        }

    }

    @FXML
    void fiyatGuncelle(ActionEvent event) throws SQLException {
        Kitap kitap = tb_kitaplar.getSelectionModel().getSelectedItem();
        String query = "UPDATE Kitaplar SET kitapBirimFiyati =? WHERE kitapID =?;";
        int yeni_fiyat = Integer.valueOf(tf_kitapFiyat.getText());
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try(PreparedStatement pst = connection.prepareStatement(query)){
                pst.setInt(1,yeni_fiyat);
                pst.setString(2,String.valueOf(kitap.getKitapID()));
                pst.executeUpdate();
            }
            tb_kitaplar.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fiyat Güncelleme");
            alert.setHeaderText(null);
            alert.setContentText("Fiyat güncellendi.");
            alert.showAndWait();
        }

    }

    public void alanlariTemizle(){
        tf_stokAdedi.clear();
        tf_sayfaSayisi.clear();
        tf_kitapFiyat.clear();
        tf_yazarAdi.clear();
        tf_kitapAdi.clear();
        cmb_kategoriler.setValue(null);
    }

    @FXML
    void kitapEkle(ActionEvent event) {

        ResultSet resultSet = null;

        String query= "INSERT INTO Kitaplar (kitapAdi, kitapBirimFiyati, kategoriID, yazarID," +
                "kitapSayfaSayisi, kitapStokAdedi) VALUES(?,?,?,?,?,?);";

        String[] arr = bilgiAl();
        int maksID = sonCalisanID() + 1;

        Kitap kitap = new Kitap(maksID,arr[0], arr[1],Integer.valueOf(arr[2]),Integer.valueOf(arr[3]),arr[4],Integer.valueOf(arr[5]));

        kitaplar.add(kitap);

        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try(PreparedStatement pst = connection.prepareStatement(query)){ {
                pst.setString(1,kitap.getKitapAdi());
                pst.setInt(2,kitap.getKitapBirimFiyati());
                pst.setInt(3,kitap.getKategoriID());
                pst.setInt(4,kitap.getYazarID());
                pst.setInt(5,kitap.getKitapSayfaSayisi());
                pst.setInt(6,kitap.getKitapStokAdedi());
                pst.executeUpdate();
            }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Kitap Ekleme");
                alert.setHeaderText(null);
                alert.setContentText("Kitap eklendi.");
                alert.showAndWait();

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        alanlariTemizle();

    }

    @FXML
    void kitapSil(ActionEvent event) throws SQLException {
        String query= "DELETE FROM Kitaplar WHERE kitapID=?";
        Kitap kitap = tb_kitaplar.getSelectionModel().getSelectedItem();


        try (Connection connection = new SQLDatabaseConnection().connect()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,String.valueOf(kitap.getKitapID()));
            try (st) {
                st.execute();
            }

            kitaplar.remove(kitaplar.indexOf(kitap));
            tb_kitaplar.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kitap Silme");
            alert.setHeaderText(null);
            alert.setContentText("Kitap silindi.");
            alert.showAndWait();
        }

    }

    public void kitaplariYukle() throws SQLException {

        ResultSet rs = null;
        String query = "SELECT * FROM Kitaplar";
        try (Connection conn = new SQLDatabaseConnection().connect()) {
            try(PreparedStatement pst = conn.prepareStatement(query)){
                rs = pst.executeQuery();
                while (rs.next()) {
                    Kitap kitap = new Kitap(
                            rs.getInt("kitapID"),
                            rs.getString("kitapAdi"),
                            rs.getInt("yazarID"),
                            rs.getInt("kitapBirimFiyati"),
                            rs.getInt("kitapSayfaSayisi"),
                            rs.getInt("kategoriID"),
                            rs.getInt("kitapStokAdedi"));
                    kitaplar.add(kitap);

                }
                rs.close();
            }


        }
        tb_kitaplar.setItems(kitaplar);
    }

    public String[] bilgiAl(){
        String arr[] = new String[6];

        String kitapAdi = tf_kitapAdi.getText();
        String kitapFiyat = tf_kitapFiyat.getText();
        String yazarAdi = tf_yazarAdi.getText();
        String sayfaSayisi = tf_sayfaSayisi.getText();
        String kategori = cmb_kategoriler.getValue();
        String stokAdedi = tf_stokAdedi.getText();

        arr[0] = kitapAdi;
        arr[1] = kitapFiyat;
        arr[2] = yazarAdi;
        arr[3] = sayfaSayisi;
        arr[4] = kategori;
        arr[5] = stokAdedi;

        return arr;
    }

    public int sonCalisanID(){
        ResultSet resultSet = null;
        String query = "SELECT MAX(kitapID) FROM Kitaplar";
        int maksID = 0;
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            PreparedStatement st = connection.prepareStatement(query);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                maksID = Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maksID;
    }

}
