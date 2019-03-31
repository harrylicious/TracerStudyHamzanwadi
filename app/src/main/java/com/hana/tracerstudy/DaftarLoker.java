package com.hana.tracerstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hana.tracerstudy.Model.ModelLoker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DaftarLoker extends AppCompatActivity {
    AppPreferences appPreferences = new AppPreferences();
    ListView list;
    Toolbar toolbar;
    ArrayList<ModelLoker> lokers = new ArrayList<>();
    AlumniAdapter adapter;

    String nama, deskripsi, tgl, gambar;
    FloatingActionButton tambah;

    TextView jml, jurusan;
    private static final String URL = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/api_loker.php";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_loker);




        list = findViewById(R.id.list);
        jml = findViewById(R.id.jml);

        tambah = findViewById(R.id.tambah);

        if (appPreferences.getLevel(getApplicationContext(),"level") == "mahasiswa") {
            tambah.setVisibility(View.INVISIBLE);
        }

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TambahLoker.class));
            }
        });


        final Intent i = new Intent(getApplicationContext(), DetailLoker.class);

        showData();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("id", String.valueOf(lokers.get(position).getId()));
                //i.putExtra("id", String.valueOf(lokers.get(position).getId()));

                i.putExtra("nama", lokers.get(position).getNama_loker());
                i.putExtra("deskripsi", lokers.get(position).getDeskripsi_loker());
                i.putExtra("tgl", lokers.get(position).getTgl());
                i.putExtra("gambar", lokers.get(position).getGambar());

                //Toast.makeText(getApplicationContext(), lokers.get(position).getGambar(), Toast.LENGTH_LONG).show();

                startActivity(i);
            }
        });



    }

    void showData() {
        Log.d("a","Tes");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject loker = array.getJSONObject(i);

                                lokers.add(new ModelLoker(
                                        loker.getInt("id"),
                                        loker.getString("nama_loker"),
                                        loker.getString("deskripsi_loker"),
                                        loker.getString("tgl"),
                                        loker.getString("gambar")
                                ));
                            }

                            LokerAdapter adapter = new LokerAdapter(lokers, getApplicationContext());
                            list.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}