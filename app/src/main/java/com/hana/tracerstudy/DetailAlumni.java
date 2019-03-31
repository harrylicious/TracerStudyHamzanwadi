package com.hana.tracerstudy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.hana.tracerstudy.Model.ModelMahasiswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DetailAlumni extends AppCompatActivity {

    AppPreferences appPreferences = new AppPreferences();

    private Button btnHapus;
    FloatingActionButton tlp, email;
    String url = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_by_id_api.php?username=";

    String id, hp, alamat_email, username;
    int key;
    String pos;
    TextView namaLengkap, nim, jenisKelamin, tglLahir, pekerjaan, lokasiKerja, kota, status, thLulus, sudahBekerja, tempatBekerja, jurusan, judulSkripsi, alamat, ttl;
    ImageView gambar;

    ArrayList<ModelMahasiswa> mahasiswa = new ArrayList<>();
    Intent callIntent, emailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alumni);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        username = getIntent().getStringExtra("username");
        getDetail();

        toolbar.setTitle("Detail");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        btnHapus = findViewById(R.id.hapus);

        if (appPreferences.getLevel(getApplicationContext(),"level") == "mahasiswa") {
            btnHapus.setVisibility(View.INVISIBLE);
        }

        namaLengkap = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        jenisKelamin = findViewById(R.id.jenis_kelamin);
        //tglLahir = findViewById(R.id.tgllahir);
        pekerjaan = findViewById(R.id.pekerjaan);
        lokasiKerja = findViewById(R.id.lokasi_kerja);
        kota = findViewById(R.id.kota);
        thLulus = findViewById(R.id.tahunLulus);
        judulSkripsi = findViewById(R.id.judul_skripsi);
        sudahBekerja = findViewById(R.id.sudahBekerja);
        tempatBekerja = findViewById(R.id.tempatBekerja);
        jurusan = findViewById(R.id.jurusan);
        alamat = findViewById(R.id.alamat);
        ttl = findViewById(R.id.ttl);
        thLulus = findViewById(R.id.th_lulus);
        tlp = findViewById(R.id.tlp);
        email = findViewById(R.id.email);
        gambar = findViewById(R.id.gambar);




        tlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + hp));
                startActivity(callIntent);
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + alamat_email)); // only email apps should handle this
                emailIntent.putExtra(Intent.EXTRA_EMAIL, alamat_email);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });


      /*  sudahBekerja.setText(mahasiswa.get(pos).getStatus());
        tempatBekerja.setText(mahasiswa.get(pos).getPekerjaan());
        jurusan.setText(mahasiswa.get(pos).getAlamat_kerja());
        alamat.setText(mahasiswa.get(pos).getAlamat());
        jurusan.setText(mahasiswa.get(pos).getProgram_studi());
        ttl.setText(mahasiswa.get(pos).getTanggal_lahir());
        th_lulus.setText(mahasiswa.get(pos).getTahun_lulus().toString());*/


    }

    private void getDetail() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (url + username),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String _nama = jsonobject.getString("nama_lengkap");
                                String _username = jsonobject.getString("username");
                                String _jenis_kelamin = jsonobject.getString("jenis_kelamin");
                                String _pekerjaan = jsonobject.getString("pekerjaan");
                                String _lokasi_kerja = jsonobject.getString("alamat_kerja");
                                String _kota = jsonobject.getString("kota");
                                String _status = jsonobject.getString("status");
                                String _program_studi = jsonobject.getString("program_studi");
                                String _judul_skripsi = jsonobject.getString("judul_skripsi");
                                String _tanggal_lahir = jsonobject.getString("tanggal_lahir");
                                String _alamat = jsonobject.getString("alamat");
                                String _telp = jsonobject.getString("hp");
                                String _email = jsonobject.getString("email");
                                String _poto = "http://tracerstudy.si.ftuhamzanwadi.ac.id/foto_user/" + jsonobject.getString("foto");

                                namaLengkap.setText(_nama);
                                nim.setText(_username);
                                jenisKelamin.setText(_jenis_kelamin);
                                alamat.setText(_alamat);
                                ttl.setText(_tanggal_lahir);
                                jurusan.setText(_program_studi);
                                //thLulus.setText(_tahun_lulus);
                                judulSkripsi.setText(_judul_skripsi);
                                hp = _telp;
                                alamat_email = _email;
                                pekerjaan.setText(_pekerjaan);
                                tempatBekerja.setText(_lokasi_kerja);
                                kota.setText(_kota);
                                if (!_pekerjaan.equals("")) {
                                    sudahBekerja.setText("Sudah Kerja");
                                } else {
                                    sudahBekerja.setText("Belum Kerja");
                                }

                                }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {

                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        Volley.newRequestQueue(this).add(stringRequest);
    }


}
