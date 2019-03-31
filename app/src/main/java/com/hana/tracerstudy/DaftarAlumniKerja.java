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


public class DaftarAlumniKerja extends AppCompatActivity {
    ListView list;
    Toolbar toolbar;
    AlumniAdapter adapter;

    ArrayList<ModelMahasiswa> mhs = new ArrayList<>();


    TextView jml, jurusan;
    private static final String URL_PRODUCTS = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_alumni_status_kerja.php?key=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_alumni_kerja);



        list = findViewById(R.id.list);
        jml = findViewById(R.id.jml);


        final Intent i = new Intent(getApplicationContext(), DetailAlumni.class);

        showData();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("username", mhs.get(position).getUsername());
                i.putExtra("username", mhs.get(position).getUsername().toString());
                startActivity(i);
            }
        });



    }

    void showData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
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




}