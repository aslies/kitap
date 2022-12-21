package com.example.kitapsatisfx;

import java.sql.*;

public class Calisan {
    private int calisanID;
    private String calisanAdi;
    private String calisanSoyadi;
    private String calisanPozisyon;
    private int calismaSuresi;
    private float calisanMaas;
    private String calisanTelNo;
    private String calisanMail;
    private String calisanKullaniciAdi;
    private String sifre;
    private int pozisyonID;
    private int kidemID;

    String connectionUrl = "jdbc:sqlserver://localhost:1433;database=KitapSatis;user=sa;password=YamanLucy123;encrypt=true;trustServerCertificate=true";


    public Calisan(String calisanAdi, String calisanSoyadi, String calisanPozisyon, int calismaSuresi,
                   String calisanTelNo, String calisanMail, String calisanKullaniciAdi, String sifre) throws SQLException {
        this.calisanID = getCalisanID();
        this.calisanAdi = calisanAdi;
        this.calisanSoyadi = calisanSoyadi;
        this.calisanPozisyon = calisanPozisyon;
        this.calismaSuresi = calismaSuresi;
        this.calisanTelNo = calisanTelNo;
        this.calisanMail = calisanMail;
        this.calisanKullaniciAdi = calisanKullaniciAdi;
        this.sifre = sifre;
        this.calisanMaas = getCalisanMaas();
        this.pozisyonID = getPozisyonID();
        this.kidemID = getKidemID();
    }
    public Calisan(int calisanID, String calisanAdi, String calisanSoyadi, String calisanPozisyon, int calismaSuresi,
                   String calisanTelNo, String calisanMail, String calisanKullaniciAdi, String sifre) throws SQLException {
        this.calisanID = calisanID;
        this.calisanAdi = calisanAdi;
        this.calisanSoyadi = calisanSoyadi;
        this.calisanPozisyon = calisanPozisyon;
        this.calismaSuresi = calismaSuresi;
        this.calisanTelNo = calisanTelNo;
        this.calisanMail = calisanMail;
        this.calisanKullaniciAdi = calisanKullaniciAdi;
        this.sifre = sifre;
        this.calisanMaas = getCalisanMaas();
        this.pozisyonID = getPozisyonID();
        this.kidemID = getKidemID();
    }

    public Calisan(String calisanAdi, String calisanSoyadi, int pozisyonID, int calismaSuresi,
                   String calisanTelNo, String calisanMail, String calisanKullaniciAdi, String sifre) throws SQLException {
        this.calisanID = getCalisanID();
        this.calisanAdi = calisanAdi;
        this.calisanSoyadi = calisanSoyadi;
        this.calismaSuresi = calismaSuresi;
        this.calisanTelNo = calisanTelNo;
        this.calisanMail = calisanMail;
        this.calisanKullaniciAdi = calisanKullaniciAdi;
        this.sifre = sifre;
        this.calisanMaas = getCalisanMaas();
        this.kidemID = getKidemID();
        this.calisanPozisyon = getCalisanPozisyon();
        this.calisanMaas = getCalisanMaas();
        this.pozisyonID = pozisyonID;
    }

    public Calisan(int calisanID, String calisanAdi, String calisanSoyadi, int pozisyonID, int calismaSuresi,
                   String calisanTelNo, String calisanMail, String calisanKullaniciAdi, String sifre) throws SQLException {
        this.calisanID = calisanID;
        this.calisanAdi = calisanAdi;
        this.calisanSoyadi = calisanSoyadi;
        this.pozisyonID = pozisyonID;
        this.calismaSuresi = calismaSuresi;
        this.calisanTelNo = calisanTelNo;
        this.calisanMail = calisanMail;
        this.calisanKullaniciAdi = calisanKullaniciAdi;
        this.sifre = sifre;
        this.calisanMaas = getCalisanMaas();
        this.kidemID = getKidemID();
        this.calisanPozisyon = getCalisanPozisyon();
        this.calisanMaas = getCalisanMaas();

    };

    Calisan(){

    }

    public void setCalisanID(String calisanMail){
        ResultSet resultSet = null;
        String query = "SELECT calisanID FROM Calisanlar WHERE calisanMail=?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,calisanMail);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                this.calisanID = Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCalisanID(){
        setCalisanID(this.calisanMail);
        return this.calisanID;
    }

    public String getCalisanMail() {
        return calisanMail;
    }

    public void setCalisanMail(String calisanMail) {
        this.calisanMail = calisanMail;
    }

    public String getCalisanAdi() {
        return calisanAdi;
    }

    public void setCalisanAdi(String calisanAdi) {
        this.calisanAdi = calisanAdi;
    }

    public String getCalisanSoyadi() {
        return calisanSoyadi;
    }

    public void setCalisanSoyadi(String calisanSoyadi) {
        this.calisanSoyadi = calisanSoyadi;
    }

    public int getCalismaSuresi() {
        return calismaSuresi;
    }

    public void setCalismaSuresi(int calismaSuresi) {
        this.calismaSuresi = calismaSuresi;
    }

    public String getCalisanTelNo() {
        return calisanTelNo;
    }

    public void setCalisanTelNo(String calisanTelNo) {
        this.calisanTelNo = calisanTelNo;
    }

    public String getCalisanKullaniciAdi() {
        return calisanKullaniciAdi;
    }

    public void setCalisanKullaniciAdi(String calisanKullaniciAdi) {
        this.calisanKullaniciAdi = calisanKullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void setPozisyonID(String pozisyon){
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            PreparedStatement st = connection.prepareStatement("SELECT pozisyonID FROM Pozisyonlar WHERE pozisyon=?");
            st.setString(1,pozisyon);
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                this.pozisyonID = Integer.parseInt(resultSet.getString(1));
            }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public int getPozisyonID(){
        setPozisyonID(this.calisanPozisyon);
        return pozisyonID;
    }

    public String getCalisanPozisyon() {
        setCalisanPozisyon(this.pozisyonID);
        return this.calisanPozisyon;
    }

    public void setCalisanPozisyon(int pozisyonID) {
        ResultSet resultSet = null;
        String query = "SELECT pozisyon FROM Pozisyonlar WHERE pozisyonID=?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, String.valueOf(pozisyonID));
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                this.calisanPozisyon = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setKidemID(int calismaSuresi){
        ResultSet resultSet = null;

        String query = "SELECT kidemID FROM Kidem WHERE calismaSuresi=?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, String.valueOf(calismaSuresi));
            resultSet = st.executeQuery();
            while (resultSet.next()) {
                this.kidemID = Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getKidemID(){
        setKidemID(this.calismaSuresi);
        return kidemID;
    }

    public void setCalisanMaas() throws SQLException {
        this.calisanMaas = calisanMaas(this.calisanID);
    }

    public float getCalisanMaas() throws SQLException {
        setCalisanMaas();
        return calisanMaas;
    }

    public int calisanMaas(int calisanID) throws SQLException {
        String query = "SELECT (p.maas + p.maas*(k.ekMaasYuzdesi/100.0)) AS calisanMaas " +
                "FROM Pozisyonlar AS p INNER JOIN Calisanlar  AS c ON " +
                "p.pozisyonID=c.pozisyonID INNER JOIN Kidem AS k ON k.kidemID=c.kidemID WHERE calisanID=?;";

        ResultSet rs = null;
        int calisanMaas = 0;

        try (Connection conn = new SQLDatabaseConnection().connect()) {
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, String.valueOf(calisanID));
                rs = pst.executeQuery();
                while (rs.next()){
                    calisanMaas = rs.getInt("calisanMaas");
                }
            }
        }
        return calisanMaas;
    }

}