package com.example.kitapsatisfx;

public class SiparisDetaylari {

    private int kitapID;
    private int siparisAdet;
    private int siparisID;
    private int siparisIndirim;

    public SiparisDetaylari(int kitapID, int siparisAdet, int siparisID, int siparisIndirim) {
        this.kitapID = kitapID;
        this.siparisAdet = siparisAdet;
        this.siparisID = siparisID;
        this.siparisIndirim = siparisIndirim;
    }

    public int getKitapID() {
        return kitapID;
    }

    public void setKitapID(int kitapID) {
        this.kitapID = kitapID;
    }

    public int getSiparisAdet() {
        return siparisAdet;
    }

    public void setSiparisAdet(int siparisAdet) {
        this.siparisAdet = siparisAdet;
    }

    public int getSiparisID() {
        return siparisID;
    }

    public void setSiparisID(int siparisID) {
        this.siparisID = siparisID;
    }

    public int getSiparisIndirim() {
        return siparisIndirim;
    }

    public void setSiparisIndirim(int siparisIndirim) {
        this.siparisIndirim = siparisIndirim;
    }
}
