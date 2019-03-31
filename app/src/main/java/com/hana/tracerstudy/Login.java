package com.hana.tracerstudy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity {
    AppPreferences appPreferences = new AppPreferences();


    private   String LOGIN_URL = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/login_api.php";
    String url = "https://tracerstudy.ti.ftuhamzanwadi.ac.id/get_by_id_api.php?username=";
    private   String KEY_USERNAME = "username";
    private   String KEY_PASSWORD = "password";
    private   String LOGIN_SUCCESS = "success";

    public String level, sLevel;
    public int kodeLevel;


    private ProgressDialog pDialog;

    EditText username, password;

    Button login;
    TextView daftar;

    CustomAlert alert = new CustomAlert();

    TextView levelUser;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        levelUser = findViewById(R.id.level);
        gambar = findViewById(R.id.gambar);

        sLevel = appPreferences.getLevel(getApplicationContext(),"level");
        if (sLevel == "alumni") {
            gambar.setImageDrawable(getResources().getDrawable(R.drawable.mhs));
            levelUser.setText("Alumni");
        }
        else {
            gambar.setImageDrawable(getResources().getDrawable(R.drawable.dosen));
            levelUser.setText("Dosen");
        }

        login = findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                level = getDetail(username.getText().toString());
                login();
            }
        });

        daftar = findViewById(R.id.link_daftar);
        daftar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sLevel == "alumni") {
                    startActivity(new Intent(getApplicationContext(), Register.class));
                }
                else {
                    startActivity(new Intent(getApplicationContext(), RegisterDosen.class));
                }
            }
        });



    }

    private void login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(LOGIN_SUCCESS)) {

                            alert.succes(Login.this, "Info", "Berhasil.");
                            gotoMain();

                        } else {

                            alert.error(Login.this, "Info", "Uups. Tidak ditemukan!");

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Toast.makeText(getApplicationContext(), "The server unreachable", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(KEY_USERNAME, username.getText().toString().trim());
                params.put(KEY_PASSWORD, password.getText().toString().trim());

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);

    }


    private String getDetail(String usr) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (url + usr),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String _level = jsonobject.getString("level");
                                level = _level;

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

                            //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        Volley.newRequestQueue(this).add(stringRequest);
        return level;
    }


    private void gotoMain() {
        Intent i = new Intent(getApplicationContext(), Main.class);
        i.putExtra("username", username.getText().toString());
        startActivity(i);
        finish();
    }



}

