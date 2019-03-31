package com.hana.tracerstudy.Model;

public class ModelLoker {

    String nama_loker, deskripsi_loker, tgl, gambar;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_loker() {
        return nama_loker;
    }

    public void setNama_loker(String nama_loker) {
        this.nama_loker = nama_loker;
    }

    public String getDeskripsi_loker() {
        return deskripsi_loker;
    }

    public void setDeskripsi_loker(String deskripsi_loker) {
        this.deskripsi_loker = deskripsi_loker;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public ModelLoker(int id, String nama_loker, String deskripsi_loker, String tgl, String gambar) {
        this.id = id;
        this.nama_loker = nama_loker;
        this.deskripsi_loker = deskripsi_loker;
        this.tgl = tgl;
        this.gambar = gambar;
    }
}
