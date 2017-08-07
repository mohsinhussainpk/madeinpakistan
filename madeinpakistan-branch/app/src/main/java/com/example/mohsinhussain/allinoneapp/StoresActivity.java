package com.example.mohsinhussain.allinoneapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.mohsinhussain.allinoneapp.R.id.listView;

public class StoresActivity extends AppCompatActivity {


    public static ListView listView;
    int click;
    DAL layer;
    public static final String CAT="category";
    public static DatabaseReference ref;
    private ArrayList<String> brands;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder() .build();

        mAdView.loadAd(adRequest);

        category=getIntent().getStringExtra(MainActivity.CATEGORY);
        click=getIntent().getIntExtra("Click",click);
        ref=MainActivity.table.child(category);
        //layer =new DAL(this,this);
        //layer.popoulateLists(ref);
        brands=DAL.getBrandName;
        //DAL layer= new DAL(this,this);
        Log.d("ref",ref.toString());
        CustomListView myAdapter = null;
        try {
            Thread.sleep(500);
            myAdapter = new CustomListView(StoresActivity.this, brands, DAL.getImageUrl);

        }catch (Exception e){
            e.printStackTrace();
        }
        myAdapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                ref.child(brands.get(i)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("url")){
                            Toast.makeText(getApplicationContext(),"WebBrowser",Toast.LENGTH_LONG).show();
                            Intent mIntent = new Intent(StoresActivity.this, Webview.class);
                            Log.d("URL:",dataSnapshot.child("url").getValue(String.class));
                            mIntent.putExtra("brandUrl", dataSnapshot.child("url").getValue(String.class));
                            startActivity(mIntent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            return;
                        }else{
                            Intent mIntent = new Intent(StoresActivity.this, FirstListView.class);
                            mIntent.putExtra(CAT, brands.get(i));
                            startActivity(mIntent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


    }
/*

    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            for(int i=0;i<5;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
    */
}