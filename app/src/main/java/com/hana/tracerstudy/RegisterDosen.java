package com.hana.tracerstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class RegisterDosen extends AppCompatActivity {

    String get_username;
    String url = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_by_id_api.php?username=";

    // Creating EditText.
    EditText username, password, nama_lengkap, tanggal_lahir, tahun_lulus, judul_skripsi, pekerjaan, alamat_kerja, kota, hp, email, foto, level, tanggal, alamat;
    Spinner jenis_kelamin, jurusan;

    // Creating button;
    Button daftar;
    TextView login;
    RequestQueue requestQueue;
    String HttpUrl = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/insert_api.php";

    CustomAlert alert = new CustomAlert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dosen);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nama_lengkap = findViewById(R.id.nama);
        tanggal_lahir = findViewById(R.id.tanggal_lahir);
        kota = findViewById(R.id.kota);
        hp = findViewById(R.id.telp);
        email = findViewById(R.id.email);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        //level = findViewById(R.id.nim);
        //tanggal = findViewById(R.id.nim);
        alamat = findViewById(R.id.alamat);

        daftar = findViewById(R.id.daftar);
        login = findViewById(R.id.link_login);

        setEmptyValue();


        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }

    void insertData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        alert.succes(RegisterDosen.this, "Info", "Data Berhasil Tersimpan!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        alert.error(RegisterDosen.this, "Error", "Data Gagal Disimpan!");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("username", username.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                params.put("nama_lengkap", nama_lengkap.getText().toString().trim());
                params.put("tanggal_lahir", tanggal_lahir.getText().toString().trim());
                params.put("program_studi", "-");
                params.put("th_lulus", "");
                params.put("judul_skripsi", "-");
                params.put("pekerjaan", "-");
                params.put("alamat_kerja", "-");
                params.put("kota", kota.getText().toString().trim());
                params.put("hp", hp.getText().toString().trim());
                params.put("email", email.getText().toString().trim());
                params.put("foto", "");
                params.put("status", "Y");
                params.put("jenis_kelamin", jenis_kelamin.getSelectedItem().toString());
                params.put("level", "admin");
                params.put("tanggal", date);
                params.put("alamat", alamat.getText().toString().trim());

                return params;
            }
        };
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
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
                                String _judul_skripsi = jsonobject.getString("judul_skripsi");
                                String _tanggal_lahir = jsonobject.getString("tanggal_lahir");
                                String _alamat = jsonobject.getString("alamat");
                                String _telp = jsonobject.getString("hp");
                                String _email = jsonobject.getString("email");

                                nama_lengkap.setText(_nama);
                                username.setText(_username);
                                jenis_kelamin.setSelection(0);
                                alamat.setText(_alamat);
                                tanggal_lahir.setText(_tanggal_lahir);
                                //thLulus.setText(_tahun_lulus);
                                hp.setText(_telp);
                                email.setText(_email);
                                kota.setText(_kota);
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


    void setEmptyValue() {
        username.setText("");
        password.setText("");
        nama_lengkap.setText("");
        alamat.setText("");
        tanggal_lahir.setText("");
        hp.setText("");
        email.setText("");
        kota.setText("");
    }

}
