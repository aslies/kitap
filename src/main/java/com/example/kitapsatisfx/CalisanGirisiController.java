package com.example.kitapsatisfx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

public class CalisanGirisiController {

    @FXML
    private TextField tf_kullaniciAdi;

    @FXML
    private PasswordField tf_sifre;

    @FXML
    private Button btn_girisYap;

    @FXML
    private Label lbl_sifremiUnuttum;

    @FXML
    private ComboBox<String> cmb_sistemTuru;

    boolean girisYapildiMi;

    @FXML
    private void initialize() {

        ObservableList<String> sistemTuru = FXCollections.observableArrayList();

        tf_kullaniciAdi.setPromptText("Kullanıcı Adı");
        tf_kullaniciAdi.setFocusTraversable(false);

        tf_sifre.setPromptText("Şifre");
        tf_sifre.setFocusTraversable(false);

        sistemTuru.add("Çalışan Yönetim Sistemi");
        sistemTuru.add("Sipariş Yönetim Sistemi");
        sistemTuru.add("Kitap Yönetim Sistemi");

        cmb_sistemTuru.getItems().addAll(sistemTuru);

        girisYapildiMi = false;


    }

    @FXML
    protected void login(){
        String connectionUrl = "jdbc:sqlserver://localhost:1433;database=KitapSatis;user=sa;password=YamanLucy123;encrypt=true;trustServerCertificate=true";

        ResultSet resultSet = null;

        int pozisyonID;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             ) {

            String query = "SELECT calisanKullaniciAdi, calisanSifre, pozisyonID FROM Calisanlar WHERE calisanKullaniciAdi=? AND calisanSifre=?";
            PreparedStatement st = connection.prepareStatement(query);

            String kullaniciAdi = tf_kullaniciAdi.getText();
            String kullaniciSifre = tf_sifre.getText();

            st.setString(1,kullaniciAdi);
            st.setString(2, kullaniciSifre);

            resultSet = st.executeQuery();

            while (resultSet.next()) {
                if(kullaniciAdi.equals(resultSet.getString(1))
                        &&kullaniciSifre.equals(resultSet.getString(2))){
                    pozisyonID = resultSet.getInt(3);
                    if ((pozisyonID == 2) | (pozisyonID == 3) | (pozisyonID == 4) && cmb_sistemTuru.getValue().equals("Çalışan Yönetim Sistemi")) {
                        girisYapildiMi = true;
                        try {
                            ChangeScene new_scene = new ChangeScene();
                            new_scene.changeScene(btn_girisYap, "CalisanYonetimSistemi.fxml");
                        } catch (IOException e) {
                            System.out.println("Yeni pencere açılamadı.");
                        }
                    }
                    if((pozisyonID == 5) | (pozisyonID == 6) | (pozisyonID == 7) && cmb_sistemTuru.getValue().equals("Sipariş Yönetim Sistemi")){
                        girisYapildiMi = true;
                        try {
                            ChangeScene new_scene = new ChangeScene();
                            new_scene.changeScene(btn_girisYap, "SiparisYonetimSistemi.fxml");
                        } catch (IOException e) {
                            System.out.println("Yeni pencere açılamadı.");
                        }
                    }
                    if((pozisyonID == 5) | (pozisyonID == 6) | (pozisyonID == 7) && cmb_sistemTuru.getValue().equals("Kitap Yönetim Sistemi")){
                        girisYapildiMi = true;
                        try {
                            ChangeScene new_scene = new ChangeScene();
                            new_scene.changeScene(btn_girisYap, "KitapYonetimSistemi.fxml");
                        } catch (IOException e) {
                            System.out.println("Yeni pencere açılamadı.");
                        }
                    }
                }
            }
            if(!girisYapildiMi) {
                System.out.println("Hatalı giriş yaptınız.Tekrar deneyiniz.");
                System.out.println("Şifrenizi unuttuysanız 'Şifremi unuttum' seçeneğine tıklayınız.");
                tf_kullaniciAdi.clear();
                tf_sifre.clear();
                cmb_sistemTuru.setValue(null);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
