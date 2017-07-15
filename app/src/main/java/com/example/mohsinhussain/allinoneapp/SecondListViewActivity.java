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

public class SecondListViewActivity extends AppCompatActivity {

    String category;
    ListView listView;
    CustomListView Adapter;
    private ArrayList<String> brands;

    public DatabaseReference ref;
    DAL layer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_list_view);
        category =getIntent().getStringExtra(StoresActivity.CAT);
        layer=new DAL(this,this);

        ref=FirstListView.ref.child(category);
        Log.d("ref",ref.toString());
        layer.popoulateLists(ref);//Method has nothing to do with category.
        brands=DAL.getBrandName;
        Log.d("checkprofile:",String.valueOf(DAL.getBrandName.size()));
        //dal.searchProfile(getIntent().getStringExtra("category"));
        listView=(ListView)findViewById(R.id.category_listview2);
        Adapter=new CustomListView(this,brands, DAL.getImageUrl);
        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                ref.child(brands.get(position)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("url")) {
                            Toast.makeText(getApplicationContext(), "WebBrowser", Toast.LENGTH_LONG).show();
                            Intent mIntent = new Intent(SecondListViewActivity.this, Webview.class);
                            Log.d("URL:", dataSnapshot.child("url").getValue(String.class));
                            mIntent.putExtra("brandUrl", dataSnapshot.child("url").getValue(String.class));
                            startActivity(mIntent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            return;
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
