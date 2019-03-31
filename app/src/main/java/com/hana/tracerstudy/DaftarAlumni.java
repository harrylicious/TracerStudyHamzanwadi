package com.hana.tracerstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hana.tracerstudy.Model.ModelMahasiswa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DaftarAlumni extends AppCompatActivity {
    ListView list;
    Toolbar toolbar;

    ArrayList<ModelMahasiswa> mhs = new ArrayList<>();

    int key = 0, th_lulus_selected;

    Spinner jurusan;


    private String username;
    AlumniAdapter adapter;
    TextView jml;
    private static final String URL = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/api.php";
    private static final String URL_PerJurusan = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_data_filter.php?key=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_alumni);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Daftar Alumni");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main.class));
            }
        });
        setSupportActionBar(toolbar);


        list = findViewById(R.id.list);
        jml = findViewById(R.id.jml);
        jurusan = findViewById(R.id.jurusan);

        showData();


        getCountMahasiswa(key);

        jurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    showDataBerdasarkanID(  0);
                    getCountMahasiswa(0);
                }
                else if (position == 1){
                    showDataBerdasarkanID( 1);
                    getCountMahasiswa(1);
                }
                else if (position == 2){
                    showDataBerdasarkanID( 2);
                    getCountMahasiswa(2);
                }
                else if (position == 3){
                    showDataBerdasarkanID(3);
                    getCountMahasiswa(3);
                }
                else if (position == 4){
                    showDataBerdasarkanID(4);
                    getCountMahasiswa(4);
                }
                else {
                    showDataBerdasarkanID(  0);
                    getCountMahasiswa(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final Intent i = new Intent(getApplicationContext(), DetailAlumni.class);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("username", mhs.get(position).getUsername());
                i.putExtra("username", mhs.get(position).getUsername().toString());
                startActivity(i);
            }
        });


    }


    void showDataBerdasarkanID(int key) {
        list.setAdapter(null);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PerJurusan + key,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject mahasiswa = array.getJSONObject(i);

                                mhs.add(new ModelMahasiswa(
                                        mahasiswa.getString("id_member"),
                                        mahasiswa.getString("username"),
                                        mahasiswa.getString("password"),
                                        mahasiswa.getString("nama_lengkap"),
                                        mahasiswa.getString("tanggal_lahir"),
                                        mahasiswa.getString("program_studi"),
                                        mahasiswa.getString("th_lulus"),
                                        mahasiswa.getString("judul_skripsi"),
                                        mahasiswa.getString("pekerjaan"),
                                        mahasiswa.getString("alamat_kerja"),
                                        mahasiswa.getString("kota"),
                                        mahasiswa.getString("hp"),
                                        mahasiswa.getString("email"),
                                        mahasiswa.getString("foto"),
                                        mahasiswa.getString("status"),
                                        mahasiswa.getString("jenis_kelamin"),
                                        mahasiswa.getString("level"),
                                        mahasiswa.getString("tanggal"),
                                        mahasiswa.getString("alamat")
                                ));
                            }

                            adapter = null;
                            adapter = new AlumniAdapter(mhs, getApplicationContext());
                            adapter.addAll(mhs);
                            adapter.notifyDataSetChanged();
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

    void showData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject mahasiswa = array.getJSONObject(i);

                                mhs.add(new ModelMahasiswa(
                                        mahasiswa.getString("id_member"),
                                        mahasiswa.getString("username"),
                                        mahasiswa.getString("password"),
                                        mahasiswa.getString("nama_lengkap"),
                                        mahasiswa.getString("tanggal_lahir"),
                                        mahasiswa.getString("program_studi"),
                                        mahasiswa.getString("th_lulus"),
                                        mahasiswa.getString("judul_skripsi"),
                                        mahasiswa.getString("pekerjaan"),
                                        mahasiswa.getString("alamat_kerja"),
                                        mahasiswa.getString("kota"),
                                        mahasiswa.getString("hp"),
                                        mahasiswa.getString("email"),
                                        mahasiswa.getString("foto"),
                                        mahasiswa.getString("status"),
                                        mahasiswa.getString("jenis_kelamin"),
                                        mahasiswa.getString("level"),
                                        mahasiswa.getString("tanggal"),
                                        mahasiswa.getString("alamat")
                                ));
                            }

                            AlumniAdapter adapter = new AlumniAdapter(mhs, getApplicationContext());
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


    void getCountMahasiswa(int key) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_count.php?key=" + key,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject jsonobject = array.getJSONObject(i);

                                jml.setText(jsonobject.getString("jml"));
                            }

                            adapter = null;
                            adapter = new AlumniAdapter(mhs, getApplicationContext());
                            adapter.addAll(mhs);
                            adapter.notifyDataSetChanged();
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