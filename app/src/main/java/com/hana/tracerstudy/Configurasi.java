package com.hana.tracerstudy;

/**
 * Created by muhammadyusuf on 01/19/2017.
 * kodingindonesia
 */

public class Configurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://tracerstudy.lombokit.com/tambahMahasiswa.php";
    public static final String URL_GET_ALL = "http://tracerstudy.lombokit.com/tampilSemuaMahasiswa.php";
    public static final String URL_GET_EMP = "http://tracerstudy.lombokit.com/tampilMahasiswa.php?id=";
    public static final String URL_UPDATE_EMP = "http://tracerstudy.lombokit.com/updateMahasiswa.php";
    public static final String URL_DELETE_EMP = "http://tracerstudy.lombokit.com/hapusMahasiswa.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_MHS_ID = "id";
    public static final String KEY_MHS_NIM = "nim";
    public static final String KEY_MHS_NAMA = "nama_lengkap";
    public static final String KEY_MHS_JK = "jk";
    public static final String KEY_MHS_ALAMAT = "alamat";
    public static final String KEY_MHS_PROVINSI = "provinsi";
    public static final String KEY_MHS_TMHSAT_LAHIR = "tMHSat_lahir";
    public static final String KEY_MHS_TGL_LAHIR = "tgl_lahir";
    public static final String KEY_MHS_JURUSAN = "jurusan";
    public static final String KEY_MHS_TH_LULUS = "th_lulus";
    public static final String KEY_MHS_JUDUL_SKRIPSI = "judul_skripsi";
    public static final String KEY_MHS_PEKERJAAN = "pekerjaan";
    public static final String KEY_MHS_EMAIL = "email";
    public static final String KEY_MHS_TELP = "telp";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NIM = "nim";
    public static final String TAG_NAMA = "nama_lengkap";
    public static final String TAG_JK = "jk";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_PROVINSI = "provinsi";
    public static final String TAG_TEMPAT_LAHIR = "tempat_lahir";
    public static final String TAG_TGL_LAHIR = "tgl_lahir";
    public static final String TAG_JURUSAN = "jurusan";
    public static final String TAG_TH_LULUS = "th_lulus";
    public static final String TAG_JUDUL_SKRIPSI = "judul_skripsi";
    public static final String TAG_PEKERJAAN = "pekerjaan";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_TELP = "telp";

    
    public static final String MHS_ID = "id";
}
