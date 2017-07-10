package com.example.mohsinhussain.allinoneapp;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //ViewPager viewPager;
    //CustomSwipeAdapter customSwipeAdapter;
    public static final String CATEGORY = "category";
    private static String DB_NAME = "Brand Records";

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.a,R.drawable.armchair,R.drawable.taxi,R.drawable.b,R.drawable.blackbackground};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();


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

        init();






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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","panoplytek@gmail.com", null));

            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
//            Intent myIntent = new Intent(Intent.ACTION_SEND);
//            PackageManager pm = getPackageManager();
//            Intent tempIntent = new Intent(Intent.ACTION_SEND);
//            tempIntent.setType("*/*");
//            List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
//            for (int i = 0; i < resInfo.size(); i++) {
//                ResolveInfo ri = resInfo.get(i);
//                if (ri.activityInfo.packageName.contains("android.gm")) {
//                    myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
//
//                    myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"panoplytek@gmail.com"});
//                    myIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("panoplytek@gmail.com"));
//                    myIntent.setAction(Intent.ACTION_SEND);
//                }
//            }
//            startActivity(myIntent);

//
//            final Intent intent = new Intent(android.content.Intent.ACTION_SEND));
//            intent.setType("text/plain");
//            final PackageManager pm = getPackageManager();
//            final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
//            ResolveInfo best = null;
//            for (final ResolveInfo info : matches)
//                if (info.activityInfo.packageName.endsWith(".gm") ||
//                        info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
//            if (best != null)
//                intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
//            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=Orion.Soft")));




        } else if (id == R.id.nav_slideshow) {

            Intent intent=new Intent(getApplicationContext(),TermsAndConditionActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_manage) {
            Intent intent=new Intent(getApplicationContext(),PrivacyActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_share) {
//            Intent myIntent=new Intent(Intent.ACTION_SEND);
//            myIntent.setType("Text/Plan");
//            String shareBody="Your body here";
//            String shareSubject="Your Subject here";
//            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
//            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
//            startActivity(Intent.createChooser(myIntent,"Share"));

            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }


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


        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SliderAdapter(MainActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);



        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }

    public void initializeConnection()
    {
        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);

        database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);

        mStorageRef = FirebaseStorage.getInstance().getReference();



        layer=new DAL(this,this);


        layer.searchProfile("");



    }







}