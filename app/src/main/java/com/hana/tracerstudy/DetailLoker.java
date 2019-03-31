package com.hana.tracerstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailLoker extends AppCompatActivity {

    String _nama, _deskripsi, _tgl;

    TextView nama, deskripsi, tgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_loker);

        nama = findViewById(R.id.nama_loker);
        deskripsi = findViewById(R.id.deskripsi);
        tgl = findViewById(R.id.tgl);

        _nama = getIntent().getStringExtra("nama");
        _deskripsi = getIntent().getStringExtra("deskripsi");
        _tgl = getIntent().getStringExtra("tgl");

        nama.setText(_nama);
        deskripsi.setText(_deskripsi);
        tgl.setText(_tgl);


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
}
