package com.hana.tracerstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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


public class EditProfil extends AppCompatActivity {

    String get_username;
    String url = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_by_id_api.php?username=";
    String HttpUrl = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/edit_api.php";

    // Creating EditText.
    EditText username, password, nama_lengkap, tanggal_lahir, tahun_lulus, judul_skripsi, pekerjaan, alamat_kerja, kota, hp, email, foto, status, level, tanggal, alamat;
    Spinner jenis_kelamin, jurusan;

    // Creating button;
    Button edit;
    TextView login;
    RequestQueue requestQueue;

    CustomAlert alert = new CustomAlert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nama_lengkap = findViewById(R.id.nama);
        tanggal_lahir = findViewById(R.id.tanggal_lahir);
        jurusan = findViewById(R.id.jurusan);
        tahun_lulus = findViewById(R.id.th_lulus);
        judul_skripsi = findViewById(R.id.judul_skripsi);
        pekerjaan = findViewById(R.id.pekerjaan);
        alamat_kerja = findViewById(R.id.lokasi_kerja);
        kota = findViewById(R.id.kota);
        hp = findViewById(R.id.telp);
        email = findViewById(R.id.email);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        //level = findViewById(R.id.nim);
        //tanggal = findViewById(R.id.nim);
        alamat = findViewById(R.id.alamat);


        get_username = getIntent().getStringExtra("username");
        setEmptyValue();
        getDetail();

        edit = findViewById(R.id.update);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });


    }

    void updateData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        alert.succes(EditProfil.this, "Info", "Data Berhasil Terupdate!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        alert.error(EditProfil.this, "Error", volleyError.toString());

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
                params.put("program_studi", jurusan.getSelectedItem().toString().trim());
                params.put("th_lulus", tahun_lulus.getText().toString().trim());
                params.put("judul_skripsi", judul_skripsi.getText().toString().trim());
                params.put("pekerjaan", pekerjaan.getText().toString().trim());
                params.put("alamat_kerja", alamat_kerja.getText().toString().trim());
                params.put("kota", kota.getText().toString().trim());
                params.put("hp", hp.getText().toString().trim());
                params.put("email", email.getText().toString().trim());
                params.put("foto", "");
                params.put("status", "Y");
                params.put("jenis_kelamin", "L");
                params.put("level", "mahasiswa");
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

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(), Main.class));
        super.onBackPressed();
    }

    private void getDetail() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (url + get_username),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            Log.d("tes", jsonarray.toString());
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
                                String _password = jsonobject.getString("password");

                                nama_lengkap.setText(_nama);
                                username.setText(_username);
                                jenis_kelamin.setSelection(0);
                                alamat.setText(_alamat);
                                tanggal_lahir.setText(_tanggal_lahir);
                                jurusan.setSelection(0);
                                //thLulus.setText(_tahun_lulus);
                                judul_skripsi.setText(_judul_skripsi);
                                hp.setText(_telp);
                                email.setText(_email);
                                pekerjaan.setText(_pekerjaan);
                                alamat_kerja.setText(_lokasi_kerja);
                                kota.setText(_kota);
                                password.setText(_password);
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

                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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
        alamat_kerja.setText("");
        pekerjaan.setText("");
        tanggal_lahir.setText("");
        judul_skripsi.setText("");
        hp.setText("");
        email.setText("");
        kota.setText("");
    }

}
