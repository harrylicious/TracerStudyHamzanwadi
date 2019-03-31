package com.hana.tracerstudy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {
    private SliderView sliderView;
    SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private LinearLayout mLinearLayout;
    Intent emailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        CardView loker = findViewById(R.id.loker);
        loker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DaftarLoker.class));
            }
        });

        sliderView = (SliderView) findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat
        // melakukan zoom.
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://ftuhamzanwadi.ac.id/");


        setupSlider();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + "universitas@hamzanwadi.ac.id")); // only email apps should handle this
                emailIntent.putExtra(Intent.EXTRA_EMAIL, "universitas@hamzanwadi.ac.id");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://tracerstudy.ti.ftuhamzanwadi.ac.id/lampiran/IMG_0895%20copy.jpg"));
        fragments.add(FragmentSlider.newInstance("https://tracerstudy.ti.ftuhamzanwadi.ac.id/lampiran/1.JPG"));
        fragments.add(FragmentSlider.newInstance("https://tracerstudy.ti.ftuhamzanwadi.ac.id/lampiran/3.jpg"));
        fragments.add(FragmentSlider.newInstance("https://tracerstudy.ti.ftuhamzanwadi.ac.id/lampiran/2.JPG"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getApplicationContext(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            startActivity(new Intent(getApplicationContext(), LoginBy.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
