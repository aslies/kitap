package com.example.kitapsatisfx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Siparis {

    private int siparisID;
    private int calisanID;
    private int musteriID;
    private String siparisDurumu;
    private String siparisNotu;
    private String siparisTarihi;
    private String odemeDurumu;
    private int siparisTutari;

    public Siparis(){
    }

    public Siparis(int siparisID, int calisanID, int musteriID, String siparisDurumu, String siparisNotu,
                   String siparisTarihi, String odemeDurumu) throws SQLException {
        this.siparisID = siparisID;
        this.calisanID = calisanID;
        this.musteriID = musteriID;
        this.siparisDurumu = siparisDurumu;
        this.siparisNotu = siparisNotu;
        this.siparisTarihi = siparisTarihi;
        this.odemeDurumu = odemeDurumu;
        this.siparisTutari = getSiparisTutari();
    }
    public Siparis(int siparisID, int calisanID, int musteriID, String siparisDurumu, String siparisNotu,
                   String siparisTarihi, String odemeDurumu, int siparisTutari) throws SQLException {
        this.siparisID = siparisID;
        this.calisanID = calisanID;
        this.musteriID = musteriID;
        this.siparisDurumu = siparisDurumu;
        this.siparisNotu = siparisNotu;
        this.siparisTarihi = siparisTarihi;
        this.odemeDurumu = odemeDurumu;
        this.siparisTutari = siparisTutari;
    }

    public int getSiparisID() {
        return siparisID;
    }

    public void setSiparisID(int siparisID) {
        this.siparisID = siparisID;
    }

    public int getCalisanID() {
        return calisanID;
    }

    public void setCalisanID(int calisanID) {
        this.calisanID = calisanID;
    }

    public int getMusteriID() {
        return musteriID;
    }

    public void setMusteriID(int musteriID) {
        this.musteriID = musteriID;
    }

    public String getSiparisDurumu() {
        return siparisDurumu;
    }

    public void setSiparisDurumu(String siparisDurumu) {
        this.siparisDurumu = siparisDurumu;
    }

    public String getSiparisNotu() {
        return siparisNotu;
    }

    public void setSiparisNotu(String siparisNotu) {
        this.siparisNotu = siparisNotu;
    }

    public String getSiparisTarihi() {
        return siparisTarihi;
    }

    public void setSiparisTarihi(String siparisTarihi) {
        this.siparisTarihi = siparisTarihi;
    }

    public String getOdemeDurumu() {
        return odemeDurumu;
    }

    public void setOdemeDurumu(String odemeDurumu) {
        this.odemeDurumu = odemeDurumu;
    }

    public int getSiparisTutari() throws SQLException {
        setSiparisTutari(this.siparisID);
        return siparisTutari;
    }

    public void setSiparisTutari(int siparisID) throws SQLException {
        String query = "SELECT sd.siparisID, SUM(sd.siparisAdet*k.kitapBirimFiyati) AS siparisTutari " +
                "FROM Kitaplar AS k INNER JOIN SiparisDetaylari  AS sd  ON sd.kitapID = k.kitapID " +
                "GROUP BY sd.siparisID HAVING sd.siparisID=?;";
        ResultSet rs = null;
        int tutar = 0;

        try (Connection conn = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, String.valueOf(siparisID));
                rs = pst.executeQuery();
                while (rs.next()){
                    tutar = rs.getInt("siparisTutari");
                }
            }
        }
        this.siparisTutari = tutar;
    }


    public int siparisTutariHesapla(int siparisID) throws SQLException{
        String query = "SELECT sd.siparisID, SUM(sd.siparisAdet*k.kitapBirimFiyati) AS siparisTutari " +
                "FROM Kitaplar AS k INNER JOIN SiparisDetaylari  AS sd  ON sd.kitapID = k.kitapID " +
                "GROUP BY sd.siparisID HAVING sd.siparisID=?;";
        ResultSet rs = null;
        int siparisTutari = 0;

        try (Connection conn = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, String.valueOf(siparisID));
                rs = pst.executeQuery();
                while (rs.next()){
                    siparisTutari = rs.getInt("siparisTutari");
                }
            }
        }
        return siparisTutari;
    }


}
