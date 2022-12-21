package com.example.kitapsatisfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class CalisanYoneticiController {

    @FXML
    private TextField tf_calisanAdi;

    @FXML
    private TextField tf_calisanSoyadi;

    @FXML
    private TextField tf_kullaniciAdi;

    @FXML
    private PasswordField tf_calisanSifre;

    @FXML
    private ComboBox<String> cmb_calisanPozisyon;

    @FXML
    private ComboBox<Integer> cmb_calismaSuresi;

    @FXML
    private TextField tf_calisanMail;

    @FXML
    private TextField tf_calisanTelNo;

    @FXML
    private TableView<Calisan> tb_calisan;

    @FXML
    private TableColumn<Calisan, String> calisanAdi;

    @FXML
    private TableColumn<Calisan, String> calisanSoyadi;

    @FXML
    private TableColumn<Calisan, String> calisanPozisyon;

    @FXML
    private TableColumn<Calisan, Integer> calismaSuresi;

    @FXML
    private TableColumn<Calisan, Integer> calisanMaas;

    @FXML
    private TableColumn<Calisan, Integer> calisanID;

    @FXML
    private TableColumn<Calisan, String> calisanMail;

    @FXML
    private TableColumn<Calisan, String>  calisanKullaniciAdi;

    @FXML
    private Button btn_calisanEkle;

    @FXML
    private Button btn_calisanSil;

    @FXML
    private Button btn_calisanGuncelle;

    @FXML
    private ComboBox<String> cmb_istenen;

    @FXML
    private ComboBox<String> cmb_sutunBilgisi;

    @FXML
    private ComboBox<String> cmb_guncellenecek;

    @FXML
    private Label lbl_sonuc;

    @FXML
    private Button btn_hesapla;

    @FXML
    private Button btn_calisanBilgisi;

    @FXML
    private ComboBox<String> cmb_siralamaSekli;

    @FXML
    private ComboBox<String> cmb_siralanacakSutun;

    @FXML
    private Button btn_sirala;

    ObservableList<Calisan> calisanlar = FXCollections.observableArrayList();

    String connectionUrl = "jdbc:sqlserver://localhost:1433;database=KitapSatis;user=sa;password=YamanLucy123;encrypt=true;trustServerCertificate=true";


    @FXML
    private void initialize() throws SQLException {

        tf_calisanAdi.setPromptText("Çalışan Adı");
        tf_calisanSoyadi.setPromptText("Çalışan Soyadı");
        tf_kullaniciAdi.setPromptText("Kullanıcı Adı");
        tf_calisanSifre.setPromptText("Şifre");
        tf_calisanMail.setPromptText("Mail");
        tf_calisanTelNo.setPromptText("Telefon No");


        ObservableList<String> calisanPozisyonlar = FXCollections.observableArrayList();
        ObservableList<Integer> calisma_Suresi = FXCollections.observableArrayList();
        ObservableList<String> istenen = FXCollections.observableArrayList();
        ObservableList<String> sutunBilgisi = FXCollections.observableArrayList();
        ObservableList<String> siralamaSekli = FXCollections.observableArrayList();
        ObservableList<String> siralanacakSutun = FXCollections.observableArrayList();
        ObservableList<String > guncellenecek = FXCollections.observableArrayList();

        calisanAdi.setCellValueFactory(new PropertyValueFactory<Calisan,String>("calisanAdi"));
        calisanSoyadi.setCellValueFactory(new PropertyValueFactory<Calisan,String>("calisanSoyadi"));
        calisanPozisyon.setCellValueFactory(new PropertyValueFactory<Calisan,String>("calisanPozisyon"));
        calismaSuresi.setCellValueFactory(new PropertyValueFactory<Calisan,Integer>("calismaSuresi"));
        calisanMaas.setCellValueFactory(new PropertyValueFactory<Calisan,Integer>("calisanMaas"));
        calisanID.setCellValueFactory(new PropertyValueFactory<Calisan,Integer>("calisanID"));
        calisanMail.setCellValueFactory(new PropertyValueFactory<Calisan,String>("calisanMail"));
        calisanKullaniciAdi.setCellValueFactory(new PropertyValueFactory<Calisan,String>("calisanKullaniciAdi"));

        for(int i=2;i<=21;i++){
            ResultSet resultSet = null;

            try (Connection connection = DriverManager.getConnection(connectionUrl);) {
                PreparedStatement st = connection.prepareStatement("SELECT pozisyon FROM Pozisyonlar WHERE pozisyonID=?");
                st.setString(1, String.valueOf(i));
                resultSet = st.executeQuery();
                while (resultSet.next()) {
                    calisanPozisyonlar.add(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for(int j=1;j<=10;j++){
            calisma_Suresi.add(j);
        }

        istenen.add("En Büyük Değer");
        istenen.add("En Küçük Değer");
        istenen.add("Ortalama Değer");

        sutunBilgisi.add("Maaş");
        sutunBilgisi.add("Çalışma Süresi");

        siralamaSekli.add("Küçükten büyüğe");
        siralamaSekli.add("Büyükten küçüğe");

        siralanacakSutun.add("Ad");
        siralanacakSutun.add("Soyad");
        siralanacakSutun.add("Çalışma Süresi");
        siralanacakSutun.add("Maaş");

        guncellenecek.add("Ad");
        guncellenecek.add("Soyad");
        guncellenecek.add("Kullanıcı Adı");
        guncellenecek.add("Şifre");
        guncellenecek.add("Pozisyon");
        guncellenecek.add("Çalışma Süresi");
        guncellenecek.add("Mail");
        guncellenecek.add("Telefon No");

        cmb_calisanPozisyon.getItems().addAll(calisanPozisyonlar);
        cmb_calismaSuresi.getItems().addAll(calisma_Suresi);
        cmb_istenen.getItems().addAll(istenen);
        cmb_sutunBilgisi.getItems().addAll(sutunBilgisi);
        cmb_siralamaSekli.getItems().addAll(siralamaSekli);
        cmb_siralanacakSutun.getItems().addAll(siralanacakSutun);
        cmb_guncellenecek.getItems().addAll(guncellenecek);

        calisanlariYukle();
    }

    public String[] bilgiAl(){
        String arr[] = new String[8];

        String adi = tf_calisanAdi.getText();
        String soyadi = tf_calisanSoyadi.getText();
        String kullaniciAdi = tf_kullaniciAdi.getText();
        String sifre = tf_calisanSifre.getText();
        String pozisyon = cmb_calisanPozisyon.getValue();
        String sure = String.valueOf(cmb_calismaSuresi.getValue());
        String mail = tf_calisanMail.getText();
        String telNo = tf_calisanTelNo.getText();

        boolean result1 = (telNo.matches("[0-9]+")) && (telNo.length() == 11);

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        boolean result2 = pat.matcher(mail).matches();

        if(result1 && result2){
            arr[0] = adi;
            arr[1] = soyadi;
            arr[2] = pozisyon;
            arr[3] = sure;
            arr[4] = telNo;
            arr[5] = mail;
            arr[6] = kullaniciAdi;
            arr[7] = sifre;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Çalışan Ekleme");
            alert.setHeaderText(null);
            alert.setContentText("Hatalı veya eksik bilgi girdiniz.\nİşlem gerçekleltirilemedi..");
            alert.showAndWait();
        }

        return arr;
    }

    @FXML
    void calisanEkle(ActionEvent event) throws SQLException {
        ResultSet resultSet = null;

        String query= "INSERT INTO Calisanlar (calisanAdi, calisanSoyadi, pozisyonID," +
                "calisanCalismaSuresi, calisanTelefonNo, calisanKullaniciAdi, " +
                "calisanSifre, kidemID, calisanMail) VALUES(?,?,?,?,?,?,?,?,?);";

        String[] arr = bilgiAl();

        int maksID = sonCalisanID() + 1;
        Calisan calisan = new Calisan(maksID,arr[0], arr[1],arr[2],Integer.valueOf(arr[3]),arr[4],arr[5],arr[6],arr[7]);

        calisanlar.add(calisan);

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            try(PreparedStatement pst = connection.prepareStatement(query)){ {
                pst.setString(1,calisan.getCalisanAdi());
                pst.setString(2,calisan.getCalisanSoyadi());
                pst.setInt(3,calisan.getPozisyonID());
                pst.setInt(4,calisan.getCalismaSuresi());
                pst.setString(5,calisan.getCalisanTelNo());
                pst.setString(6,calisan.getCalisanKullaniciAdi());
                pst.setString(7,calisan.getSifre());
                pst.setInt(8,calisan.getKidemID());
                pst.setString(9,calisan.getCalisanMail());
                pst.executeUpdate();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Çalışan Ekleme");
            alert.setHeaderText(null);
            alert.setContentText("Çalışan eklendi.");
            alert.showAndWait();

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        alanlariTemizle();
    }

    public void adGuncelle() throws SQLException {
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        String query;
        String adi = tf_calisanAdi.getText();
        int calisanID = calisan.getCalisanID();
        query = "UPDATE Calisanlar SET calisanAdi=? WHERE calisanID=?;";
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1,adi);
                pst.setString(2, String.valueOf(calisanID));
                pst.executeUpdate();
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    public void soyadGuncelle() throws SQLException{
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        int calisanID = calisan.getCalisanID();
        String query;
        String soyadi = tf_calisanSoyadi.getText();
        query = "UPDATE Calisanlar SET calisanSoyadi=? WHERE calisanID=?;";
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1,soyadi);
                pst.setString(2, String.valueOf(calisanID));
                pst.executeUpdate();
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    public void kullaniciAdiGuncelle() throws SQLException{
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        int calisanID = calisan.getCalisanID();
        String query;
        String kullaniciAdi = tf_kullaniciAdi.getText();
        query = "UPDATE Calisanlar SET calisanKullaniciAdi=? WHERE calisanID=?;";
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1,kullaniciAdi);
                pst.setString(2, String.valueOf(calisanID));
                pst.executeUpdate();
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    public void sifreGuncelle() throws SQLException{
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        int calisanID = calisan.getCalisanID();
        String query;
        String sifre = tf_calisanSifre.getText();
        query = "UPDATE Calisanlar SET calisanSifre=? WHERE calisanID=?;";
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1,sifre);
                pst.setString(2, String.valueOf(calisanID));
                pst.executeUpdate();
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    public void calismaSuresiGuncelle() throws SQLException{
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        int calisanID = calisan.getCalisanID();
        String query;
        int sure = cmb_calismaSuresi.getValue();
        query = "UPDATE Calisanlar SET calisanCalismaSuresi=? WHERE calisanID=?;";
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1,String.valueOf(sure));
                pst.setString(2, String.valueOf(calisanID));
                pst.executeUpdate();
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    public void mailGuncelle() throws SQLException{
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        int calisanID = calisan.getCalisanID();
        String query;
        String mail = tf_calisanMail.getText();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        boolean result2 = pat.matcher(mail).matches();
        query = "UPDATE Calisanlar SET calisanMail=? WHERE calisanID=?;";
        if(result2){
            try (Connection connection = new SQLDatabaseConnection().connect()) {
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.setString(1,mail);
                    pst.setString(2, String.valueOf(calisanID));
                    pst.executeUpdate();
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    public void telefonGuncelle() throws SQLException{
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        int calisanID = calisan.getCalisanID();
        String query;
        String telNo = tf_calisanTelNo.getText();
        boolean result1 = (telNo.matches("[0-9]+")) && (telNo.length() == 11);
        query = "UPDATE Calisanlar SET calisanMail=? WHERE calisanID=?;";
        if(result1){
            try (Connection connection = new SQLDatabaseConnection().connect()) {
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.setString(1,telNo);
                    pst.setString(2, String.valueOf(calisanID));
                    pst.executeUpdate();
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    public void pozisyonGuncelle() throws SQLException{
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();
        int calisanID = calisan.getCalisanID();
        String pozisyon = cmb_calisanPozisyon.getValue();

        String query;

        query = "UPDATE Calisanlar SET pozisyonID=? WHERE calisanID=?;";
        try (Connection connection = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, String.valueOf(pozisyonIDBul(pozisyon)));
                pst.setString(2, String.valueOf(calisanID));
                pst.executeUpdate();
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Çalışan Güncelle");
        alert.setHeaderText(null);
        alert.setContentText("Çalışan güncellendi.");
        alert.showAndWait();
        alanlariTemizle();
        calisanlariYukle();
    }

    @FXML
    void calisanGuncelle(ActionEvent event) throws SQLException {

        String guncellenecek = cmb_guncellenecek.getValue();

        if(guncellenecek.equals("Ad")){
            adGuncelle();
        }
        if(guncellenecek.equals("Soyad")){
            soyadGuncelle();
        }
        if(guncellenecek.equals("Pozisyon")){
            pozisyonGuncelle();
        }
        if(guncellenecek.equals("Çalışma Süresi")){
            calismaSuresiGuncelle();
        }
        if(guncellenecek.equals("Kullanıcı Adı")){
            kullaniciAdiGuncelle();
        }
        if(guncellenecek.equals("Şifre")){
            sifreGuncelle();
        }
        if(guncellenecek.equals("Mail")){
            mailGuncelle();
        }
        if(guncellenecek.equals("Telefon No")){
            telefonGuncelle();
        }

    }

    public int pozisyonIDBul(String pozisyon) throws SQLException {
        ResultSet resultSet = null;
        int pozisyonID = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            PreparedStatement st = connection.prepareStatement("SELECT pozisyonID FROM Pozisyonlar WHERE pozisyon=?");
            st.setString(1,pozisyon);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                pozisyonID = resultSet.getInt(1);
            }
        }
        return pozisyonID;
    }

    @FXML
    void sirala(ActionEvent event){

        String siralamaTuru = cmb_siralamaSekli.getValue();
        String sutunBilgisi = cmb_siralanacakSutun.getValue();

        String query = "";
        String sutun = "";
        TableColumn column = null;

        if (sutunBilgisi.equals("Maaş")) {
            sutun = "calisanMaas";
            column = calisanMaas;
        }
        if (sutunBilgisi.equals("Çalışma Süresi")){
            sutun = "calisanCalismaSuresi";
            column = calismaSuresi;
        }
        if (sutunBilgisi.equals("Ad")) {
            sutun = "calisanAdi";
            column = calisanAdi;
        }
        if (sutunBilgisi.equals("Soyad")){
            sutun = "calisanSoyadi";
            column = calisanSoyadi;
        }
        if(siralamaTuru.equals("Büyükten küçüğe")){
            query= "SELECT * FROM Calisanlar ORDER BY "+sutun+" DESC;";
            column.setSortType(TableColumn.SortType.DESCENDING);
            tb_calisan.getSortOrder().add(column);
        }
        if(siralamaTuru.equals("Küçükten büyüğe")){
            query= "SELECT * FROM Calisanlar ORDER BY "+sutun+";";
            tb_calisan.getSortOrder().add(column);
        }

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            PreparedStatement st = connection.prepareStatement(query);
            st.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void degerHesapla(ActionEvent event) throws SQLException {

        String istenen = cmb_istenen.getValue();
        String sutunBilgisi = cmb_sutunBilgisi.getValue();

        String query = "";
        ResultSet resultSet = null;

        int sonuc = 0;
        String sutun="";
        ArrayList<Integer> maaslar = maaslariBul();

        if (sutunBilgisi.equals("Maaş")) {
            if(istenen.equals("En Büyük Değer")){
                sonuc = Collections.max(maaslar);
                lbl_sonuc.setText("Hesaplanan değer:"+sonuc);
            }
            else if(istenen.equals("En Küçük Değer")){
                sonuc = Collections.min(maaslar);
                lbl_sonuc.setText("Hesaplanan değer:"+sonuc);
            }
            else  if(istenen.equals("Ortalama Değer")){
                int avg = 0;
                for(int i=0;i<maaslar.size();i++){
                    avg += maaslar.get(i);
                }
                sonuc = avg/(maaslar.size());
            }


        }
        if (sutunBilgisi.equals("Çalışma Süresi")){
            sutun = "calisanCalismaSuresi";
            if(istenen.equals("En Büyük Değer")){
                query= "SELECT MAX("+sutun+") FROM Calisanlar";
            }
            else if(istenen.equals("En Küçük Değer")){
                query= "SELECT MIN("+sutun+") FROM Calisanlar";
            }
            else  if(istenen.equals("Ortalama Değer")){
                query = "SELECT AVG("+sutun+") FROM Calisanlar";
            }
            try (Connection connection = DriverManager.getConnection(connectionUrl);) {
                PreparedStatement st = connection.prepareStatement(query);
                resultSet = st.executeQuery();
                while (resultSet.next()) {
                    sonuc = resultSet.getInt(1);
                }
                lbl_sonuc.setText("Hesaplanan değer:"+sonuc);
            }
        }
    }
    @FXML
    void calisanSil(ActionEvent event) throws SQLException {

        String query= "DELETE FROM Calisanlar WHERE calisanID=?";
        Calisan calisan = tb_calisan.getSelectionModel().getSelectedItem();


        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,String.valueOf(calisan.getCalisanID()));
            try (st) {
                st.execute();
            }

            calisanlar.remove(calisanlar.indexOf(calisan));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Çalışan Silme");
            alert.setHeaderText(null);
            alert.setContentText("Çalışan silindi.");
            alert.showAndWait();
            alanlariTemizle();
        }

    }
    private void alanlariTemizle(){
        tf_calisanAdi.clear();
        tf_calisanSoyadi.clear();
        tf_kullaniciAdi.clear();
        tf_calisanMail.clear();
        tf_calisanTelNo.clear();
        tf_calisanSifre.clear();
        cmb_calisanPozisyon.setValue(null);
        cmb_calismaSuresi.setValue(null);
        cmb_guncellenecek.setValue(null);
    }

    private void calisanlariYukle() throws SQLException {

        calisanlar.clear();

        String query = "SELECT calisanID, calisanAdi, calisanSoyadi, pozisyonID,calisanCalismaSuresi, " +
                "calisanTelefonNo, calisanMail, calisanKullaniciAdi calisanSifre, kidemID FROM Calisanlar";

        ResultSet rs = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                int calisanID = rs.getInt(1);
                String calisanAdi = rs.getString(2);
                String calisanSoyadi = rs.getString(3);
                int pozisyonID = rs.getInt(4);
                int calisanCalismaSuresi = rs.getInt(5);
                String calisanTelefonNo = rs.getString(6);
                String calisanMail = rs.getString(7);
                String calisanKullaniciAdi = rs.getString(8);
                String calisanSifre = rs.getString(9);

                Calisan calisan = new Calisan(calisanID, calisanAdi, calisanSoyadi,
                        pozisyonID,
                        calisanCalismaSuresi,
                        calisanTelefonNo,
                        calisanMail,
                        calisanKullaniciAdi,
                        calisanSifre);
                calisanlar.add(calisan);
            }
        }
        tb_calisan.setItems(calisanlar);
    }

    public int sonCalisanID(){
        ResultSet resultSet = null;
        String query = "SELECT MAX(calisanID) FROM Calisanlar";
        int maksID = 0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
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

    public ArrayList<Integer> maaslariBul() throws SQLException {
        String query = "SELECT c.calisanID ,(p.maas + p.maas*(k.ekMaasYuzdesi/100.0)) AS calisanMaas " +
                "FROM Pozisyonlar AS p INNER JOIN Calisanlar  AS c ON p.pozisyonID=c.pozisyonID " +
                "INNER JOIN Kidem AS k ON k.kidemID=c.kidemID;";

        ResultSet rs = null;
        int calisanMaas = 0;
        ArrayList<Integer>  maaslar = new ArrayList<>();

        try (Connection conn = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                rs = pst.executeQuery();
                while (rs.next()){
                    calisanMaas = rs.getInt("calisanMaas");
                    maaslar.add(calisanMaas);
                }
            }
        }
        return maaslar;
    }



}
