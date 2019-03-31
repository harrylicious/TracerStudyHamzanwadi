package com.hana.tracerstudy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main extends AppCompatActivity {

    AppPreferences appPreferences = new AppPreferences();

    String url = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_by_id_api.php?username=";
    Integer rc;
    String username;
    CardView mhs, dosen, evaluasi, loker, card_kerja, card_tidak_kerja;
    TextView nama, edit, kerja, tidak_kerja, jml;
    int kodeLevel;
    Button tambah_loker;
    String sLevel="";

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mhs = findViewById(R.id.mahasiswa);
        evaluasi = findViewById(R.id.evaluasi);
        card_kerja = findViewById(R.id.kerja_card);
        card_tidak_kerja = findViewById(R.id.tidak_kerja_card);
        loker = findViewById(R.id.loker);
        dosen = findViewById(R.id.dosen);
        nama = findViewById(R.id.nama);
        edit = findViewById(R.id.edit);
        kerja = findViewById(R.id.kerja);
        tidak_kerja = findViewById(R.id.tidak_kerja);
        jml = findViewById(R.id.jml_semua);

        tambah_loker = findViewById(R.id.tambah_loker);


        showJumlahSemua();
        showJumlahKerja();
        showJumlahTidakKerja();


        card_kerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarAlumniKerja.class));
            }
        });

        card_tidak_kerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarAlumniTidakKerja.class));
            }
        });


        dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarDosen.class));
            }
        });

        loker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarLoker.class));
            }
        });

        tambah_loker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TambahLoker.class));
            }
        });

        username = getIntent().getStringExtra("username");
        getDetail();


        mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarAlumni.class));
            }
        });

        sLevel = appPreferences.getLevel(getApplicationContext(),"level");
        if (sLevel == "alumni") {
            dosen.setVisibility(View.INVISIBLE);
            tambah_loker.setVisibility(View.INVISIBLE);
        }
        else {
            dosen.setVisibility(View.VISIBLE);
            tambah_loker.setVisibility(View.VISIBLE);
        }

        evaluasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoProfil();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Yakin ingin keluar?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    void showJumlahSemua() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,  "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_count.php?key=5",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject data = array.getJSONObject(i);
                                String hasil = data.getString("jml") + " Data Alumni Terdaftar";
                                jml.setText(hasil);
                            }

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

    void showJumlahKerja() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_count_kerja.php?key=0",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject data = array.getJSONObject(i);
                                String hasil = data.getString("jml") + " Aktif Kerja";
                                kerja.setText(hasil);
                            }

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

    void showJumlahTidakKerja() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_count_kerja.php?key=1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject data = array.getJSONObject(i);
                                String hasil = data.getString("jml") + " Tidak Kerja";
                                tidak_kerja.setText(hasil);
                            }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.info) {
            startActivity(new Intent(getApplicationContext(), Tentang.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void getDetail() {

        //pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (url + username),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            for (int i = 0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String _nama = jsonobject.getString("nama_lengkap");
                                nama.setText(_nama);
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

    private void gotoProfil() {
        Intent i = new Intent(getApplicationContext(), EditProfil.class);
        i.putExtra("username", username);
        startActivity(i);
        finish();
    }
}
