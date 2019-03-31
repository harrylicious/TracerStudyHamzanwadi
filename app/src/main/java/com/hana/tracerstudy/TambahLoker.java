package com.hana.tracerstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TambahLoker extends AppCompatActivity {
    RequestQueue requestQueue;
    String HttpUrl = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/tambah_loker_api.php";

    CustomAlert alert = new CustomAlert();

    TextView nama_loker, deskripsi, tgl;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_loker);

        nama_loker = findViewById(R.id.nama_loker);
        deskripsi = findViewById(R.id.deskripsi);
        tgl = findViewById(R.id.tgl);

        simpan = findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nama_loker.getText() == "" || deskripsi.getText() == "" || tgl.getText() == "") {
                    alert.error(TambahLoker.this, "Error", "Field Masih Kosong!");

                }
                else {
                    insertData();
                }
            }
        });




    }


    void insertData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        alert.succes(TambahLoker.this, "Info", "Data Berhasil Tersimpan!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        alert.error(TambahLoker.this, "Error", "Data Gagal Disimpan!");

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("nama_loker", nama_loker.getText().toString().trim());
                params.put("deskripsi_loker", deskripsi.getText().toString().trim());
                params.put("tgl", tgl.getText().toString());


                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }



}
