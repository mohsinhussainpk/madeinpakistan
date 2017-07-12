package com.example.mohsinhussain.allinoneapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Webview extends AppCompatActivity {

    private  static WebView browser;
    ProgressBar progressBar;
    int progress=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        browser = (WebView)findViewById(R.id.webView);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url=getIntent().getStringExtra("brandUrl");
        browser.getSettings().getJavaScriptEnabled();
        browser.getSettings().setBuiltInZoomControls(true);
        browser.loadUrl(url);
        browser.setWebViewClient(new webclient());




        // TextView textView= (TextView) findViewById(R.id.textview);


    }

    public class webclient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    }







