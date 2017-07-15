package com.example.mohsinhussain.allinoneapp;

/**
 * Created by Hp on 6/19/2017.
 */

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.view.View;
import java.util.List;
import java.util.Scanner;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.R.attr.translateY;

/**
 * Created by Hp on 5/13/2017.
 */

public class DAL  {

    private static Context context;
    Activity activity;
    public static ArrayList<String> getBrandName;
    public static ArrayList<String> getImageUrl;
    public static ArrayList<Bitmap> bitmaps;
    public static ArrayList<String> sliderImage;
    public static ArrayList<String> sliderUrl;
    private static DatabaseReference reference;
    public static int  counter = 0;
    public static ProgressDialog progressDialog;
    public DAL(Activity activity, Context context) {

        this.activity = activity;


        this.context = context;

    }

    public DAL()
    {

    }
//the listviews other than stores activity are displayed using populatelists
    public void popoulateLists(DatabaseReference ref){

        getBrandName=new ArrayList<>();
        getImageUrl=new ArrayList<>();
        reference=ref;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot record : dataSnapshot.getChildren()) {

                    if(!record.getKey().equals("img url")) { //If the key is the img url then don't put it in the list
                        //see firebase database structure
                        Log.d("Recordcheck",String.valueOf(record.getKey().equals("img url")));
                        Log.d("CATEG",record.getKey());

                        getImageUrl.add(record.child("img url").getValue(String.class));
                        getBrandName.add(String.valueOf(record.getKey()));
                    }

                }
                Log.d("getbrandname count",String.valueOf(getBrandName.size()));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
//Search profile Method is a method for loading first time the data into stores Activity
    public static void searchProfile(final String category,DatabaseReference ref) {

        progressDialog=new ProgressDialog(MainActivity.context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait");
        progressDialog.setMax(100);
        progressDialog.show();
        getBrandName=new ArrayList<>();
        getImageUrl=new ArrayList<>();
        reference=ref.child(category);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot record : dataSnapshot.getChildren()) {
                    if(!record.getKey().equals("img url")) {
                        Log.d("Recordcheck",String.valueOf(record.getKey().equals("img url")));
                        Log.d("CATEG",record.getKey());

                        getImageUrl.add(record.child("img url").getValue(String.class));
                        getBrandName.add(String.valueOf(record.getKey()));
                    }
                    // getBrandName.add(String.valueOf(record.child("name").getValue(String.class)));
                }
                Log.d("getbrandname count",String.valueOf(getBrandName.size()));
                progressDialog.dismiss();
                //              Dialog.hide();
                Intent intent=new Intent(MainActivity.context,StoresActivity.class);//opening the  stores activity as soon as the
                                                                                    //progress bar finishes.
                intent.putExtra("category",category);
                Log.d("imdone",String.valueOf(DAL.progressDialog.isShowing()));
                MainActivity.context.startActivity(intent);
                ((Activity)MainActivity.context).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public static void sliderDetail() {


        sliderImage=new ArrayList<String>();
        sliderUrl=new ArrayList<String>();


        IntroActivity.table = IntroActivity.database.child("Slider Detail");


        IntroActivity.table.keepSynced(true);




        IntroActivity.table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot record : dataSnapshot.getChildren()) {


                    Log.i("DAL::deleteProfile", record.child("Name").getValue(String.class) + " "  );
                    //      Log.i("DAL::deleteProfile", counter + " "  );

                    //Log.i("DAL::deleteProfile", record.child("Roll").getValue(String.class) + " "  );
                    sliderImage.add( String.valueOf(record.child("Imgid").getValue(String.class)));
                    sliderUrl.add( String.valueOf(record.child("Url").getValue(String.class)));
                     Log.i("DAL::sliderimage", sliderImage.get(counter) + " "  );
                    Log.i("DAL::sliderimageurl", sliderUrl.get(counter) + " "  );

//getImageUrl.add("https://firebasestorage.googleapis.com/v0/b/all-in-one-app-panoplytek.appspot.com/o/0.jpg?alt=media&token=058741a8-140b-4eab-9443-edd35c2f2f0c");
//                    getImageUrl.add(String.valueOf(record.child("Imgid").getValue(String.class)));
//                   URL url = new URL(getImageUrl.get);
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    imageView.setImageBitmap(bmp);




                    //getBrandCategory[counter]= String.valueOf( record.child("Category").getValue(String.class));
                    //Toast.makeText(context,getBrandName.get(counter),Toast.LENGTH_SHORT).show();
                    // Log.i("DAL::deleteProfile", getitemname[counter] + " "  );
                    //Log.i("DAL::deleteProfile", getcgpa[counter] + " "  );
                    counter++;


                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        for(int k=0 ; k<getBrandName.size() ;k++)
//        {
//
//            URL url = null;
//            try {
//
//                url = new URL(getImageUrl.get(k));
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                bitmaps.add(bmp);
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

    }





//
//    public void printData() {
//        if (getBrandName.isEmpty()) {
//            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();
//
//        } else {
//            for (int i = 0; i < getBrandName.size(); i++) {
//                Toast.makeText(context, getImageUrl.get(i), Toast.LENGTH_SHORT).show();
//            }
//
//
//        }
//
//    }
}
