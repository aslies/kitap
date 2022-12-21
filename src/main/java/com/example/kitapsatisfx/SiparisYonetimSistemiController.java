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
import java.util.ArrayList;
import java.util.Objects;

public class SiparisYonetimSistemiController {


        @FXML
        private TableView<Siparis> tb_siparis;

        @FXML
        private TableColumn<Siparis, Integer> siparisID;

        @FXML
        private TableColumn<Siparis, Integer> siparisTutari;

        @FXML
        private TableColumn<Siparis, Integer> musteriID;

        @FXML
        private TableColumn<Siparis, String> siparisTarihi;

        @FXML
        private TableColumn<Siparis, String> siparisDurumu;

        @FXML
        private TableColumn<Siparis, String> siparisNotu;

        @FXML
        private Button btn_calisanEkle;

        @FXML
        private Button btn_calisanSil;

        @FXML
        private Button btn_calisanGuncelle;

        @FXML
        private Button btn_calisanEkle1;

        @FXML
        private ComboBox<String> cmb_istenen;

        @FXML
        private Button btn_hesapla;

        @FXML
        private Button btn_siparisBilgisi;

        @FXML
        private Button btn_siparisDetayi;

        @FXML
        private TextArea ta_sonuc;

        ObservableList<Siparis> siparisler = FXCollections.observableArrayList();
        @FXML
        private void initialize() throws SQLException {

                ObservableList<String> istenen = FXCollections.observableArrayList();
                istenen.add("En Büyük Değer");
                istenen.add("En Küçük Değer");

                siparisID.setCellValueFactory(new PropertyValueFactory<Siparis,Integer>("siparisID"));
                musteriID.setCellValueFactory(new PropertyValueFactory<Siparis,Integer>("musteriID"));
                siparisTutari.setCellValueFactory(new PropertyValueFactory<Siparis,Integer>("siparisTutari"));
                siparisTarihi.setCellValueFactory(new PropertyValueFactory<Siparis,String>("siparisTarihi"));
                siparisDurumu.setCellValueFactory(new PropertyValueFactory<Siparis,String>("siparisDurumu"));
                siparisNotu.setCellValueFactory(new PropertyValueFactory<Siparis,String>("siparisNotu"));

                cmb_istenen.getItems().addAll(istenen);

                siparileriYukle();
        }

        @FXML
        void odemeKontrol(ActionEvent event) throws SQLException {
                Siparis siparis = tb_siparis.getSelectionModel().getSelectedItem();
                String query = "UPDATE Siparisler SET odemeDurumu = 'Ödeme Yapildi.' WHERE siparisID =?;";
                if(Objects.equals(siparis.getOdemeDurumu(), "Ödeme Yapildi.")){
                        siparis.setSiparisDurumu("Ödeme Yapildi.");
                        try (Connection conn = new SQLDatabaseConnection().connect()) {
                                try (PreparedStatement pst = conn.prepareStatement(query)) {
                                        pst.setString(1,String.valueOf(siparis.getSiparisID()));
                                        pst.executeUpdate();
                                }
                                tb_siparis.refresh();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Ödeme Kontrol");
                                alert.setHeaderText(null);
                                alert.setContentText("Ödeme alındı.");
                                alert.showAndWait();
                        }
                }
                else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ödeme Kontrol");
                        alert.setHeaderText(null);
                        alert.setContentText("Ödeme gerçekleşmedi.");
                        alert.showAndWait();
                }
        }

        @FXML
        void siparisHazirla(ActionEvent event) throws SQLException {
                Siparis siparis = tb_siparis.getSelectionModel().getSelectedItem();
                String query = "UPDATE Siparisler SET siparisDurumu = 'Siparis Hazirlaniyor.' WHERE siparisID =?;";
                if(Objects.equals(siparis.getSiparisDurumu(), "Ödeme Yapildi.")){
                        siparis.setSiparisDurumu("Siparis Hazirlaniyor.");
                        try (Connection conn = new SQLDatabaseConnection().connect()) {
                                try (PreparedStatement pst = conn.prepareStatement(query)) {
                                        pst.setString(1,String.valueOf(siparis.getSiparisID()));
                                        pst.executeUpdate();
                                }
                                tb_siparis.refresh();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Sipariş Hazırlama");
                                alert.setHeaderText(null);
                                alert.setContentText("Ödeme alındı.\nSipariş hazırlanıyor.");
                                alert.showAndWait();
                        }
                }
                else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sipariş Hazırlama");
                        alert.setHeaderText(null);
                        alert.setContentText("Ödeme gerçekleşmedi.\nSipariş hazırlanamıyor.");
                        alert.showAndWait();
                }

        }

        @FXML
        void kargoyaVer(ActionEvent event) throws SQLException{
                Siparis siparis = tb_siparis.getSelectionModel().getSelectedItem();
                String query = "UPDATE Siparisler SET siparisDurumu = 'Kargoya verildi.' WHERE siparisID =?;";
                if(Objects.equals(siparis.getSiparisDurumu(), "Siparis Hazirlaniyor.")){
                        siparis.setSiparisDurumu("Kargoya Verildi.");
                        try (Connection conn = new SQLDatabaseConnection().connect()) {
                                try (PreparedStatement pst = conn.prepareStatement(query)) {
                                        pst.setString(1,String.valueOf(siparis.getSiparisID()));
                                        pst.executeUpdate();
                                }
                                tb_siparis.refresh();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Kargo Durumu");
                                alert.setHeaderText(null);
                                alert.setContentText("Sipariş hazır.\nKargoya veriliyor.");
                                alert.showAndWait();
                        }
                }
                else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Kargo Durumu");
                        alert.setHeaderText(null);
                        alert.setContentText("Sipariş hazır değil.\nKargoya verilemez.");
                        alert.showAndWait();
                }

        }

        @FXML
        void siparisiIptalEt(ActionEvent event) throws SQLException{
                String query= "DELETE FROM Siparisler WHERE siparisID=?";
                Siparis siparis = tb_siparis.getSelectionModel().getSelectedItem();


                try (Connection conn = new SQLDatabaseConnection().connect()) {
                        PreparedStatement st = conn.prepareStatement(query);
                        st.setString(1,String.valueOf(siparis.getSiparisID()));
                        st.execute();

                        siparisler.remove(siparisler.indexOf(siparis));

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sipariş İptal");
                        alert.setHeaderText(null);
                        alert.setContentText("Sipariş iptal edildi.");
                        alert.showAndWait();
                }

        }

        @FXML
        void degerHesapla(ActionEvent event) throws SQLException {
                String istenen = cmb_istenen.getValue();

                String query = "";
                ResultSet resultSet = null;

                Siparis siparis = null;
                SiparisDetaylari siparisDetaylari = null;

                int sonuc = 0;

                ArrayList<Integer> sonuclar = new ArrayList<>();

                if(istenen.equals("En Büyük Değer")){
                        query= "SELECT SUM(sd.siparisAdet*k.kitapBirimFiyati) AS siparisTutari FROM Kitaplar " +
                                "AS k INNER JOIN SiparisDetaylari AS sd  ON sd.kitapID = k.kitapID  " +
                                "GROUP BY sd.siparisID; ";
                }
                else if(istenen.equals("En Küçük Değer")){
                        query= "SELECT SUM(sd.siparisAdet*k.kitapBirimFiyati) AS siparisTutari FROM Kitaplar " +
                                "AS k INNER JOIN SiparisDetaylari AS sd  ON sd.kitapID = k.kitapID  " +
                                "GROUP BY sd.siparisID ORDER BY siparisTutari; ";
                }

                try (Connection connection = new SQLDatabaseConnection().connect()) {
                        PreparedStatement st = connection.prepareStatement(query);
                        resultSet = st.executeQuery();
                        while (resultSet.next()) {
                                sonuc = resultSet.getInt(1);
                                sonuclar.add(sonuc);
                        }

                }
                if(istenen.equals("En Büyük Değer")){
                        ta_sonuc.clear();
                        ta_sonuc.setText("Maksimum Sipariş Tutarı:"+sonuclar.get(0));
                }
                else if(istenen.equals("En Küçük Değer")){
                        ta_sonuc.clear();
                        ta_sonuc.setText("Minimum Sipariş Tutarı:"+sonuclar.get(0));
                }

        }

        @FXML
        void siparisBilgisiAl(ActionEvent event) throws SQLException {
                String istenen = cmb_istenen.getValue();

                String query = "";
                ResultSet resultSet = null;

                Siparis siparis = null;
                SiparisDetaylari siparisDetaylari = null;

                int sonuc = 0;
                int sID = 0;

                ArrayList<int[]> sonuclar = new ArrayList<>();

                if(istenen.equals("En Büyük Değer")){
                        query= "SELECT SUM(sd.siparisAdet*k.kitapBirimFiyati) AS siparisTutari, sd.siparisID  FROM Kitaplar " +
                                "AS k INNER JOIN SiparisDetaylari AS sd  ON sd.kitapID = k.kitapID  " +
                                "GROUP BY sd.siparisID; ";
                }
                else if(istenen.equals("En Küçük Değer")){
                        query= "SELECT SUM(sd.siparisAdet*k.kitapBirimFiyati) AS siparisTutari, sd.siparisID  FROM Kitaplar " +
                                "AS k INNER JOIN SiparisDetaylari AS sd  ON sd.kitapID = k.kitapID  " +
                                "GROUP BY sd.siparisID ORDER BY siparisTutari; ";
                }

                try (Connection connection = new SQLDatabaseConnection().connect()) {
                        PreparedStatement st = connection.prepareStatement(query);
                        resultSet = st.executeQuery();
                        while (resultSet.next()) {
                                sonuc = resultSet.getInt(1);
                                sID = resultSet.getInt(2);
                                sonuclar.add(new int[]{sonuc, sID});
                        }

                }
                if(istenen.equals("En Büyük Değer")){
                        ta_sonuc.clear();
                        ta_sonuc.setText("Maksimum Sipariş Tutarı:"+sonuclar.get(0)[0]);
                        ta_sonuc.appendText("\n\nMaksimum Tutara Sahip Sipariş ID:"+sonuclar.get(0)[1]);
                        tb_siparis.getSelectionModel().clearSelection();
                        tb_siparis.getSelectionModel().select(sonuclar.get(0)[1]-1);
                }
                else if(istenen.equals("En Küçük Değer")){
                        ta_sonuc.clear();
                        ta_sonuc.setText("Minimum Sipariş Tutarı:"+sonuclar.get(0)[0]);
                        ta_sonuc.appendText("\n\nMinimum Tutara Sahip Sipariş ID:"+sonuclar.get(0)[1]);
                        tb_siparis.getSelectionModel().clearSelection();
                        tb_siparis.getSelectionModel().select(sonuclar.get(0)[1]-1);
                }

        }

        public void siparileriYukle() throws SQLException {

                ResultSet rs = null;
                String query = "SELECT * FROM Siparisler";
                try (Connection conn = new SQLDatabaseConnection().connect()) {
                        try(PreparedStatement pst = conn.prepareStatement(query)){
                                rs = pst.executeQuery();
                                while (rs.next()) {
                                        Siparis siparis = new Siparis(
                                        rs.getInt("siparisId"),
                                        rs.getInt("calisanID"),
                                        rs.getInt("musteriID"),
                                        rs.getString("siparisDurumu"),
                                        rs.getString("siparisNotu"),
                                        rs.getString("siparisTarihi"),
                                        rs.getString("odemeDurumu"));
                                        siparisler.add(siparis);

                                }
                                rs.close();
                        }


                }
                tb_siparis.setItems(siparisler);
        }

        @FXML

        void siparisDetayGoruntule(ActionEvent event) throws SQLException{
                String query1 = "SELECT COUNT(sd.kitapID) AS kitapAdedi FROM Kitaplar AS k " +
                        "INNER JOIN SiparisDetaylari  AS sd  ON sd.kitapID = k.kitapID " +
                        "WHERE sd.siparisID=? GROUP BY sd.siparisID ;";

                String query2 = "SELECT k.kitapAdi FROM Kitaplar AS k INNER JOIN " +
                        "SiparisDetaylari  AS sd  ON sd.kitapID = k.kitapID WHERE sd.siparisID =?;";

                Siparis siparis = tb_siparis.getSelectionModel().getSelectedItem();
                int siparisID = siparis.getSiparisID();

                ResultSet rs = null;

                int kitapAdedi = 0;
                ArrayList<String> kitaplar = new ArrayList<>();
                String kitapAdi;
                int siparisTutari = siparis.getSiparisTutari();

                try (Connection conn = new SQLDatabaseConnection().connect()) {
                        try(PreparedStatement pst = conn.prepareStatement(query1)){
                                pst.setString(1,String.valueOf(siparisID));
                                rs = pst.executeQuery();
                                while (rs.next()){
                                        kitapAdedi = rs.getInt("kitapAdedi");
                                }
                                rs.close();
                        }

                }
                ResultSet rs2 = null;
                try (Connection conn = new SQLDatabaseConnection().connect()) {
                        try(PreparedStatement pst = conn.prepareStatement(query2)){
                                pst.setString(1,String.valueOf(siparisID));
                                rs2 = pst.executeQuery();
                                while (rs2.next()){
                                        kitapAdi = rs2.getString("kitapAdi");
                                        kitaplar.add(kitapAdi);
                                }
                                rs2.close();
                        }
                }
                ta_sonuc.clear();
                ta_sonuc.setText("Sipariş Edilen Kitap Sayısı:"+kitapAdedi+"\n\n");
                for(int i=0;i<kitaplar.size();i++){
                        ta_sonuc.appendText((i+1)+"."+kitaplar.get(i)+"\n");
                }
                ta_sonuc.appendText("\nSipariş Tutarı:"+siparisTutari+" TL");

                }

}




