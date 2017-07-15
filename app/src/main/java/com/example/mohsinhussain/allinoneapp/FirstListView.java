package com.example.mohsinhussain.allinoneapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        category =getIntent().getStringExtra(StoresActivity.CAT);
        layer=new DAL(this,this);
        ref=StoresActivity.ref.child(category);
        Log.d("ref",ref.toString());
        layer.popoulateLists(ref);//Method has nothing to do with category
        Log.d("checkprofile:",String.valueOf(DAL.getBrandName.size()));
        //dal.searchProfile(getIntent().getStringExtra("category"));
        listView=(ListView)findViewById(R.id.category_listview);
        brands=DAL.getBrandName;
        Adapter=new CustomListView(this,DAL.getBrandName, DAL.getImageUrl);
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
                            Toast.makeText(getApplication(),"NewList",Toast.LENGTH_LONG).show();
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


    }
}
