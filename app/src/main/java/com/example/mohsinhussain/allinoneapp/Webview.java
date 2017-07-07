package com.example.mohsinhussain.allinoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {

    private  static WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        browser = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);



        String url=getIntent().getStringExtra("brandUrl");


        browser.getSettings().getJavaScriptEnabled();
        browser.setWebChromeClient(new WebChromeClient());
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(url);
        browser.setWebViewClient(new WebViewClient());
        // TextView textView= (TextView) findViewById(R.id.textview);


    }
}
