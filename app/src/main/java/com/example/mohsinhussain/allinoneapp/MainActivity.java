package com.example.mohsinhussain.allinoneapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CATEGORY = "category";
    private static String DB_NAME = "Brand Records";

    int click = 0;
    public static String category = "";
    static DAL layer;
    TextView textviewShoppiing;

    TextView textviewFood ;
    TextView textViewCarHiring;
    TextView textViewClassified;
    TextView textViewJobSite;
    TextView textViewNews;
    TextView textViewWallets;
    TextView textViewTechNews;
    TextView textViewBooking;
    TextView textViewGrocery;
    TextView textViewCarSite;
    TextView textViewRealState;
    TextView textViewPriceCompany;
    TextView textViewEducation;

    TextView textViewTravelling;
    TextView textViewHomeServices;
    TextView textViewMedicine;
    TextView textViewFinanace;
    TextView textViewFurniture;
    TextView textViewPrinting;
    TextView textViewFlower;
    TextView textViewDeal;
    TextView textViewKids;
    TextView textViewWomen;
    TextView textViewDelivery;
    TextView textViewEbuzz;
    TextView textViewMusic;
    TextView textViewLegal;



    //static String category = "";
    public static FirebaseDatabase firebase;
    public static DatabaseReference database;
    public static DatabaseReference table;
    public static StorageReference mStorageRef;
  //  static DAL layer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ProgressDialog progress = new ProgressDialog(this);
//        progress.setMessage("Loading");
//        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progress.setIndeterminate(false);
//       progress.show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);

        database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);

        mStorageRef = FirebaseStorage.getInstance().getReference();



          layer=new DAL(this,this);

        try {
            Thread.sleep(500);
            layer.searchProfile("");





        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


//        ImageButton imageButton= (ImageButton) findViewById(R.id.shoppingImage);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                category12="Shopping";
//            }
//        });


       //layer.addProfile();
        //layer.addImages();
//        layer.retrieve();
//        //layer.getBrandName();
//        layer.printData();
//layer.searchProfile();

        //layer.printData();




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } 

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void moveToBrandsList(View view) {


        // TextView textviewFood = (TextView) findViewById(R.id.textviewFood);
        textviewShoppiing = (TextView) findViewById(R.id.textviewShopping);
        textviewFood = (TextView) findViewById(R.id.textviewFood);
        textViewCarHiring = (TextView) findViewById(R.id.carhiring);
        textViewClassified = (TextView) findViewById(R.id.classifiedsite);
        textViewJobSite = (TextView) findViewById(R.id.josite);
        textViewNews = (TextView) findViewById(R.id.news);
        textViewWallets = (TextView) findViewById(R.id.mobileWallets);
        textViewBooking = (TextView) findViewById(R.id.Booking);
        textViewGrocery = (TextView) findViewById(R.id.grocery);
        textViewCarSite = (TextView) findViewById(R.id.carsite);
        textViewRealState = (TextView) findViewById(R.id.realstate);
        textViewPriceCompany = (TextView) findViewById(R.id.pricecomapnysite);
        textViewEducation = (TextView) findViewById(R.id.education);
        textViewTechNews = (TextView) findViewById(R.id.technews);
        textViewTravelling = (TextView) findViewById(R.id.travelsite);
        textViewHomeServices = (TextView) findViewById(R.id.HomeServiceSite);
        textViewMedicine = (TextView) findViewById(R.id.medicineSite);
        textViewFinanace = (TextView) findViewById(R.id.financeSite);
        textViewFurniture = (TextView) findViewById(R.id.furnitureSite);
        textViewPrinting = (TextView) findViewById(R.id.printingSite);
        textViewFlower = (TextView) findViewById(R.id.giftandflowersSite);
        textViewDeal = (TextView) findViewById(R.id.dealSite);
        textViewKids = (TextView) findViewById(R.id.babyandkidsSite);
        textViewWomen = (TextView) findViewById(R.id.womenshoppingSite);
        textViewDelivery = (TextView) findViewById(R.id.deliveryserviceSite);
        textViewEbuzz = (TextView) findViewById(R.id.ebuzzsite);
        textViewMusic = (TextView) findViewById(R.id.musicSite);
        textViewLegal = (TextView) findViewById(R.id.legalsite);




        switch (view.getId()) {


            case R.id.shoppingImage:


                category = textviewShoppiing.getText().toString();
                break;

            case R.id.foodImage:
                category = textviewFood.getText().toString();
                break;
            case R.id.carhiringImage:
                category = textViewCarHiring.getText().toString();
                break;

            case R.id.classifiedSitesImage:
                category = textViewClassified.getText().toString();
                break;
            case R.id.jobSiteImage:
                category = textViewJobSite.getText().toString();
                break;
            case R.id.newsImage:
                category = textViewNews.getText().toString();
                break;
            case R.id.mobileWalletsImage:
                category = textViewWallets.getText().toString();
                break;
            case R.id.BookingImage:
                category = textViewBooking.getText().toString();
                break;
            case R.id.GroceryImage:
                category = textViewGrocery.getText().toString();
                break;
            case R.id.CarSiteImage:
                category = textViewCarSite.getText().toString();
                break;
            case R.id.realStateImage:
                category = textViewRealState.getText().toString();
                break;
            case R.id.priceComapnySiteImage:
                category = textViewPriceCompany.getText().toString();
                break;
            case R.id.educationSiteImage:
                category = textViewEducation.getText().toString();
                break;
            case R.id.technewsSiteImage:
                category = textViewTechNews.getText().toString();
                break;
            case R.id.TraveletsiteImage:
                category = textViewTravelling.getText().toString();
                break;

            case R.id.HomeServiceSiteImage:
                category = textViewHomeServices.getText().toString();
                break;

            case R.id.medicineSiteImage:
                category = textViewMedicine.getText().toString();
                break;

            case R.id.financeSiteImage:
                category = textViewFinanace.getText().toString();
                break;
            case R.id.furnitureSiteImage:
                category = textViewFurniture.getText().toString();
                break;
            case R.id.printingSiteImage:
                category = textViewPrinting.getText().toString();
                break;
            case R.id.giftandflowersSiteImage:
                category = textViewFlower.getText().toString();
                break;
            case R.id.dealSiteImage:
                category = textViewDeal.getText().toString();
                break;
            case R.id.babyandkidsSiteImage:
                category = textViewKids.getText().toString();
                break;

            case R.id.womenshoppingSiteImage:
                category = textViewWomen.getText().toString();
                break;
            case R.id.deliveryserviceSiteImage:
                category = textViewDelivery.getText().toString();
                break;
            case R.id.ebuzzsiteImage:
                category = textViewEbuzz.getText().toString();
                break;
            case R.id.musicSiteImage:
                category = textViewMusic.getText().toString();
                break;
            case R.id.legalsiteImage:
                category = textViewLegal.getText().toString();
                break;




        }

             Intent intent=new Intent(this,StoresActivity.class);

        layer.searchProfile(category);

        intent.putExtra(CATEGORY,category);

        //layer.printData();
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }






}