package com.twtech.village_app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView=(WebView)findViewById(R.id.web1);

        webView.getSettings().setJavaScriptEnabled(true);

        //Intent i=getIntent();

        String str=getIntent().getStringExtra("img1");

//        webView.loadUrl(i.getStringExtra("img1"));
//        webView.setVisibility(View.VISIBLE);

        webView.loadUrl(str);

        String sh=getIntent().getStringExtra("str11");


        webView.loadUrl(sh);


        String iq1=getIntent().getStringExtra("iq");


        webView.loadUrl(iq1);


        webView.setWebViewClient(new WebViewClient()

        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                getSupportActionBar().setTitle(view.getTitle());

            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  Toast.makeText(ParallaxActivity.this, "facebook is blocked", Toast.LENGTH_SHORT).show();
                return url.contains("facebook");
            }
        });



    }
}
