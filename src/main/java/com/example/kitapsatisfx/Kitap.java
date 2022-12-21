package com.example.kitapsatisfx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Kitap {

    private int kitapID;
    private int kategoriID;
    private String kitapKategori;
    private String kitapAciklama;
    private String kitapAdi;
    private int kitapBirimFiyati;
    private String kitapGoruntu;
    private int kitapSayfaSayisi;
    private int kitapStokAdedi;
    private int yayinciID;
    private int yazarID;
    private String yazarAdi;

    public Kitap(int kitapID, int kategoriID, String kitapAciklama, String kitapAdi, int kitapBirimFiyati,
                 String kitapGoruntu, int kitapSayfaSayisi, int kitapStokAdedi, int yayinciID, int yazarID) {
        this.kitapID = kitapID;
        this.kategoriID = kategoriID;
        this.kitapAciklama = kitapAciklama;
        this.kitapAdi = kitapAdi;
        this.kitapBirimFiyati = kitapBirimFiyati;
        this.kitapGoruntu = kitapGoruntu;
        this.kitapSayfaSayisi = kitapSayfaSayisi;
        this.kitapStokAdedi = kitapStokAdedi;
        this.yayinciID = yayinciID;
        this.yazarID = yazarID;
        this.kitapKategori = getKitapKategori();
        this.yazarAdi = getYazarAdi();
    }

    public Kitap(int kategoriID, String kitapAciklama, String kitapAdi, int kitapBirimFiyati,
                 String kitapGoruntu, int kitapSayfaSayisi, int kitapStokAdedi, int yayinciID, int yazarID) {
        this.kategoriID = kategoriID;
        this.kitapAciklama = kitapAciklama;
        this.kitapAdi = kitapAdi;
        this.kitapBirimFiyati = kitapBirimFiyati;
        this.kitapGoruntu = kitapGoruntu;
        this.kitapSayfaSayisi = kitapSayfaSayisi;
        this.kitapStokAdedi = kitapStokAdedi;
        this.yayinciID = yayinciID;
        this.yazarID = yazarID;
        this.kitapKategori = getKitapKategori();
        this.yazarAdi = getYazarAdi();
        this.kitapID = getKitapID();
    }

    public Kitap(String kitapAdi, String yazarAdi, int kitapBirimFiyati, int kitapSayfaSayisi, String kitapKategori, int kitapStokAdedi){
        this.kitapID = getKitapID();
        this.kitapAdi = kitapAdi;
        this.yazarAdi = yazarAdi;
        this.kitapBirimFiyati = kitapBirimFiyati;
        this.kitapSayfaSayisi = kitapSayfaSayisi;
        this.kitapKategori = kitapKategori;
        this.kitapStokAdedi = kitapStokAdedi;
    }
    public Kitap(int kitapID, String kitapAdi, String yazarAdi, int kitapBirimFiyati, int kitapSayfaSayisi, String kitapKategori, int kitapStokAdedi){
        this.kitapID = kitapID;
        this.kitapAdi = kitapAdi;
        this.yazarAdi = yazarAdi;
        this.kitapBirimFiyati = kitapBirimFiyati;
        this.kitapSayfaSayisi = kitapSayfaSayisi;
        this.kitapKategori = kitapKategori;
        this.kitapStokAdedi = kitapStokAdedi;
    }
    public Kitap(int kitapID, String kitapAdi, int yazarID, int kitapBirimFiyati, int kitapSayfaSayisi, int kategoriID, int kitapStokAdedi){
        this.kitapID = kitapID;
        this.kitapAdi = kitapAdi;
        this.yazarID = yazarID;
        this.kitapBirimFiyati = kitapBirimFiyati;
        this.kitapSayfaSayisi = kitapSayfaSayisi;
        this.kategoriID = kategoriID;
        this.kitapStokAdedi = kitapStokAdedi;
        this.yazarAdi = getYazarAdi();
        this.kitapKategori = getKitapKategori();
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public String getKitapAciklama() {
        return kitapAciklama;
    }

    public void setKitapAciklama(String kitapAciklama) {
        this.kitapAciklama = kitapAciklama;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public int getKitapBirimFiyati() {
        return kitapBirimFiyati;
    }

    public void setKitapBirimFiyati(int kitapBirimFiyati) {
        this.kitapBirimFiyati = kitapBirimFiyati;
    }

    public String getKitapGoruntu() {
        return kitapGoruntu;
    }

    public void setKitapGoruntu(String kitapGoruntu) {
        this.kitapGoruntu = kitapGoruntu;
    }

    public int getKitapSayfaSayisi() {
        return kitapSayfaSayisi;
    }

    public void setKitapSayfaSayisi(int kitapSayfaSayisi) {
        this.kitapSayfaSayisi = kitapSayfaSayisi;
    }

    public int getKitapStokAdedi() {
        return kitapStokAdedi;
    }

    public void setKitapStokAdedi(int kitapStokAdedi) {
        this.kitapStokAdedi = kitapStokAdedi;
    }

    public int getYayinciID() {
        return yayinciID;
    }

    public void setYayinciID(int yayinciID) {
        this.yayinciID = yayinciID;
    }

    public int getYazarID() {
        return yazarID;
    }

    public void setYazarID(int yazarID) {
        this.yazarID = yazarID;
    }

    public void setKitapKategori(int kategoriID){
        ResultSet resultSet = null;
        String query = "SELECT kategoriAdi FROM Kategoriler WHERE kategoriID=?";

        try (Connection connection = new SQLDatabaseConnection().connect()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, String.valueOf(kategoriID));
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                this.kitapKategori = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getKitapKategori(){
        setKitapKategori(this.kategoriID);
        return kitapKategori;
    }

    public void setYazarAdi(int yazarID){
        ResultSet resultSet = null;
        String query = "SELECT yazarAdi,yazarSoyadi FROM Yazarlar WHERE yazarID=?";
        String ad = "";
        String soyad = "";

        try (Connection connection = new SQLDatabaseConnection().connect()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, String.valueOf(yazarID));
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                ad = resultSet.getString(1);
                soyad = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.yazarAdi = ad + " " + soyad;
    }

    public String getYazarAdi(){
        setYazarAdi(this.yazarID);
        return yazarAdi;
    }

    public void setKitapID(String kitapAdi){
        ResultSet resultSet = null;
        String query = "SELECT kitapID FROM Kitaplar WHERE kitapAdi=?";

        try (Connection connection = new SQLDatabaseConnection().connect()) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,kitapAdi);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                this.kitapID = Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getKitapID(){
        setKitapID(this.kitapAdi);
        return kitapID;
    }
}
