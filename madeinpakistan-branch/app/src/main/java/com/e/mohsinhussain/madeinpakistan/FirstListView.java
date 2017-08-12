package com.e.mohsinhussain.madeinpakistan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirstListView extends AppCompatActivity {
    String category="";
    public final static  String CAT = "category";
    ListView listView;
    DAL layer;
    private ArrayList<String> brands;
    public static DatabaseReference ref;
    CustomListView Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_list_view);

        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder() .build();

        mAdView.loadAd(adRequest);

        category =getIntent().getStringExtra(StoresActivity.CAT);
        layer=new DAL(this,this);
        ref=StoresActivity.ref.child(category);
        Log.d("ref",ref.toString());
        layer.popoulateLists(ref);//Method has nothing to do with category
      //  Log.d("checkprofile:",String.valueOf(DAL.getBrandName1.size()));
        //dal.searchProfile(getIntent().getStringExtra("category"));
        listView=(ListView)findViewById(R.id.category_listview);
        brands=DAL.getBrandName1;
        Adapter=new CustomListView(this,DAL.getBrandName1, DAL.getImageUrl1);
        Adapter.notifyDataSetChanged();
        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                ref.child(brands.get(position)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("url")){
                            Toast.makeText(getApplicationContext(),"WebBrowser",Toast.LENGTH_LONG).show();
                            Intent mIntent = new Intent(FirstListView.this, Webview.class);
                            Log.d("URL:",dataSnapshot.child("url").getValue(String.class));
                            mIntent.putExtra("brandUrl", dataSnapshot.child("url").getValue(String.class));
                            startActivity(mIntent);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                            return;
                        }
                        else{
                            //Toast.makeText(getApplication(),"NewList",Toast.LENGTH_LONG).show();
                            Intent mIntent = new Intent(FirstListView.this, SecondListViewActivity.class);
                            Log.d("Position",String.valueOf(position));
                            mIntent.putExtra(CAT, brands.get(position));
                            startActivity(mIntent);
                            overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                return;
//                notifyAll();
//                Log.d("Newlist:",brands.get(position))*/
            }
        });
        FloatingActionMenu materialDesignFAM;

        FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5, floatingActionButton6;

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floating_home);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floating_share);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.floating_exit);

        floatingActionButton1.setBackgroundColor(Color.parseColor("#ffffff"));
        floatingActionButton2.setBackgroundColor(Color.parseColor("#ffffff"));
        floatingActionButton3.setBackgroundColor(Color.parseColor("#ffffff"));

        //floatingActionButton4 = (FloatingActionButton) findViewById(R.id.floating_google_plus);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                getOpenHomeIntent(FirstListView.this);
                //startActivity(HomeIntent);

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                getShareIntent(FirstListView.this);
                //startActivity(ShareIntent);

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                getExitIntent(FirstListView.this);
                //startActivity(exitIntent);

            }
        });





    }
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



    @Override
    public void onBackPressed() {


//        super.onBackPressed();
//        Intent intent= new Intent(this,MainActivity.class);
//        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit);
    }

}
