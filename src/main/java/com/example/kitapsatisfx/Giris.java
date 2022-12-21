package com.example.kitapsatisfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Giris extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Giris.class.getResource("giris.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Giriş");
        stage.setScene(scene);
        stage.show();

//        try {
//            String bookName = "Bülbülü Öldürmek";
//            float price = 50;
//            String categoryName = "Roman";
//            int authorID = 8;
//            String bookDesc = "-";
//            String path = "/Users/asli/Downloads/bulbuluOldurmek.jpg";
//            int publisherID = 1;
//            int numOfStocks = 10;
//            int numOfPages = 500;
//
//            SQLConnection conn = new SQLConnection();
//            PreparedStatement ps = conn.connection.prepareStatement("INSERT INTO Kitaplar (kitapAdi, kitapBirimFiyati, kategoriID, yazarID, kitapAciklama, kitapGoruntu, yayinciID, kitapStokAdeti, kitapSayfaSayisi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
//            PreparedStatement ps2 = conn.connection.prepareStatement("SELECT * FROM Kategoriler WHERE KategoriAdi = ?");
//            ps2.setString(1, categoryName);
//            ResultSet rs = ps2.executeQuery();
//
//            if (rs.next()) {
//                ps.setInt(3, rs.getInt("KategoriID"));
//            }
//
//            File file = new File(path);
//            FileInputStream fis=new FileInputStream(file);
//            ps.setString(1,bookName);
//            ps.setFloat(2, price);
//            ps.setInt(4, authorID);
//            ps.setString(5, bookDesc);
//            ps.setBinaryStream(6,fis,(int)file.length());
//            ps.setInt(7, publisherID);
//            ps.setInt(8, numOfStocks);
//            ps.setInt(9, numOfPages);
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//

    }

    public static void main(String[] args)  {
        launch();
    }
}