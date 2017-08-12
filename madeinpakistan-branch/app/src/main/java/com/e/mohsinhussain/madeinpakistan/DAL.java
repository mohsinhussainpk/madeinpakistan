package com.e.mohsinhussain.madeinpakistan;

/**
 * Created by Hp on 6/19/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Hp on 5/13/2017.
 */

public class DAL  {

    private static String DB_NAME = "MIP";


    private FirebaseDatabase firebase;
    private static DatabaseReference database;
    private static DatabaseReference table;
    private static Context context;
    private ProgressDialog mProgressDialog;
    public static String category;

    //List<Profile> searchResults;
    private boolean success = false;
    Activity activity;
    ArrayList<String> brandName = new ArrayList<String>();
    ArrayList<String> brandImgId = new ArrayList<String>();
    ArrayList<String> brandCategory = new ArrayList<String>();
    ArrayList<String> brandUrl = new ArrayList<String>();
    public static ArrayList<String> getBrandName;
    public static ArrayList<String> getBrandUrl;
    public static ArrayList<String> getImageUrl;
    public static ArrayList<Bitmap> bitmaps;
    public static ArrayList<String> sliderImage;
    public static ArrayList<String> sliderUrl;
    public static ArrayList<String> getBrandName1;
    public static ArrayList<String> getImageUrl1;

    private StorageReference mStorageRef;

    ArrayList<String> getBrandCategory = new ArrayList<String>();
    String[] copy;

    Scanner scanner;
    public static int  counter = 0;
    private int array_position;

//    private StorageReference mStorageRef;

    //public static String[] getBrandCategory=new  String[10];



    public DAL(Activity activity, Context context) {

        this.activity = activity;
        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);

        database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);

        mStorageRef = FirebaseStorage.getInstance().getReference();



        this.context = context;

    }

    public DAL()
    {

    }

    //the listviews other than stores activity are displayed using populatelists
    public void popoulateLists(DatabaseReference ref){
        final ProgressDialog Dialog;
        getBrandName1=new ArrayList<>();
        getImageUrl1=new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot record : dataSnapshot.getChildren()) {

                    if(!record.getKey().equals("img url")) { //If the key is the img url then don't put it in the list
                        //see firebase database structure
                        Log.d("Recordcheck",String.valueOf(record.getKey().equals("img url")));
                        Log.d("CATEG",record.getKey());

                        getImageUrl1.add(record.child("img url").getValue(String.class));
                        getBrandName1.add(String.valueOf(record.getKey()));
                    }

                }
                Log.d("getbrandname count",String.valueOf(getBrandName1.size()));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
//Search profile Method is a method for loading first time the data into stores Activity
    public static void searchProfile(final String category) {
        final ProgressDialog progressDialog;
        Log.i("DAL::context", String.valueOf(context));

//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();


//        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);

        // Convert the color (Decimal value) to HEX value: (e.g: #4b96a0)
//        String color = colorDecToHex(75, 150, 160);
//
//        // Define a shape with rounded corners
//        final float[] roundedCorners = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
//        ShapeDrawable pgDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners,     null, null));
//
//        // Sets the progressBar color
//        pgDrawable.getPaint().setColor(Color.parseColor(color));
//
//        // Adds the drawable to your progressBar
//        ClipDrawable progress = new ClipDrawable(pgDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
//        progressBar.setProgressDrawable(progress);
//
//        // Sets a background to have the 3D effect
//        progressBar.setBackgroundDrawable(Utils.getActivity().getResources()
//                .getDrawable(android.R.drawable.progress_horizontal));

        progressDialog=new ProgressDialog(MainActivity.context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait");
        progressDialog.setMax(100);
        progressDialog.show();


        table = database.child(category);




        table.keepSynced(true);

        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getBrandName=new ArrayList<>();
                getImageUrl=new ArrayList<>();
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

                //              Dialog.hide();
                Intent intent=new Intent(MainActivity.context,StoresActivity.class);//opening the  stores activity as soon as the
                                                                                    //progress bar finishes.
                intent.putExtra("category",category);
               // Log.d("imdone",String.valueOf(DAL.progressDialog.isShowing()));
                ((Activity)MainActivity.context).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                MainActivity.context.startActivity(intent);

                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public static void sliderDetail() {
        final ProgressDialog Dialog;
        Dialog = new ProgressDialog(context);
        Dialog.setMessage("Loading...");
        int counter1=0;
//        Dialog.show();




        SplashActivity.table = SplashActivity.database.child("Slider Detail");


        SplashActivity.table.keepSynced(true);




        SplashActivity.table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sliderImage=new ArrayList<String>();
                sliderUrl=new ArrayList<String>();
                for (DataSnapshot record : dataSnapshot.getChildren()) {


                    // Log.i("DAL::deleteProfile", record.child("Name").getValue(String.class) + " "  );
                    //      Log.i("DAL::deleteProfile", counter + " "  );

                    //Log.i("DAL::deleteProfile", record.child("Roll").getValue(String.class) + " "  );
                    sliderImage.add( String.valueOf(record.child("img url").getValue(String.class)));
                    sliderUrl.add( String.valueOf(record.child("url").getValue(String.class)));
                  // Toast.makeText(context,sliderImage.get(counter),Toast.LENGTH_SHORT).show();
//getImageUrl.add("https://firebasestorage.googleapis.com/v0/b/all-in-one-app-panoplytek.appspot.com/o/0.jpg?alt=media&token=058741a8-140b-4eab-9443-edd35c2f2f0c");
//                    getImageUrl.add(String.valueOf(record.child("Imgid").getValue(String.class)));
//                   URL url = new URL(getImageUrl.get);
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    imageView.setImageBitmap(bmp);




                    //getBrandCategory[counter]= String.valueOf( record.child("Category").getValue(String.class));
                    //Toast.makeText(context,getBrandName.get(counter),Toast.LENGTH_SHORT).show();
                    // Log.i("DAL::deleteProfile", getitemname[counter] + " "  );
                    //Log.i("DAL::deleteProfile", getcgpa[counter] + " "  );
                    // MainActivity.XMENArray.set(counter1, sliderImage.get(counter1)) ;

                    counter++;


                }


                Intent intent=new Intent(context,MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                ((Activity)context).startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                ((Activity)context).finish();

//                Dialog.dismiss();

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
