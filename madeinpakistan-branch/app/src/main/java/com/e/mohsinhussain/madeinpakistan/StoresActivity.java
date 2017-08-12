package com.e.mohsinhussain.madeinpakistan;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

        MobileAds.initialize(this, "ca-app-pub-7071387714574454~6272238199");
        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder() .build();

        mAdView.loadAd(adRequest);

        FloatingActionMenu materialDesignFAM;
        FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5, floatingActionButton6;

        category=getIntent().getStringExtra(MainActivity.CATEGORY);
        click=getIntent().getIntExtra("Click",click);
        ref=MainActivity.table.child(category);
        //layer =new DAL(this,this);
        //layer.popoulateLists(ref);
        brands=DAL.getBrandName;
        //DAL layer= new DAL(this,this);
        Log.d("ref",ref.toString());
        CustomListView myAdapter = null;

            myAdapter = new CustomListView(StoresActivity.this, brands, DAL.getImageUrl);

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
                            //Toast.makeText(getApplicationContext(),"WebBrowser",Toast.LENGTH_LONG).show();
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
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floating_home);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floating_share);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.floating_exit);

        floatingActionButton1.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        floatingActionButton2.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        floatingActionButton3.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);

        //floatingActionButton4 = (FloatingActionButton) findViewById(R.id.floating_google_plus);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                 getOpenHomeIntent(StoresActivity.this);
                //startActivity(HomeIntent);

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                 getShareIntent(StoresActivity.this);
                //startActivity(ShareIntent);

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                getExitIntent(StoresActivity.this);
                //startActivity(exitIntent);

            }
        });




    }
   // @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void getOpenHomeIntent(Context context) {

      Intent intent=new Intent(context,MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);



    }

    public void getShareIntent(Context context) {

       //twitter.com/drkarthiik")); //catches and opens a url to the desired page
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.e.mohsinhussain.madeinpakistan \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

    public void getExitIntent(Context context) {

        CustomDialogClass customDialogClass=new CustomDialogClass(this);
        customDialogClass.show();
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
@Override
public void onBackPressed() {


//        super.onBackPressed();
//        Intent intent= new Intent(this,MainActivity.class);
//        startActivity(intent);
    finish();
    overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);
}


}