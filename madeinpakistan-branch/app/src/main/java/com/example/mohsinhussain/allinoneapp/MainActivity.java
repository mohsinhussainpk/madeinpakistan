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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,  BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    //ViewPager viewPager;
    //CustomSwipeAdapter customSwipeAdapter;
    public static final String CATEGORY = "category";
    private static String DB_NAME = "Brand Records";

    private  ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.a,R.drawable.armchair,R.drawable.taxi,R.drawable.b,R.drawable.blackbackground};

    private ArrayList<String> XMENArray = DAL.sliderImage;


    public static Context context;
    int click;
    public static String category = "";



    //static String category = "";
    public static FirebaseDatabase firebase;
    public static DatabaseReference database;
    public static DatabaseReference table;
    public static StorageReference mStorageRef;
    private ArrayList<String> brands;
    //  static DAL layer;
    String[] web = {

            "Automobiles",
            "Banks",
            "Daily Use Items",
            "Goverment Web Services",
            "Home Appliances",
            "Life Style",
            "Phones",
            "Sports Goods",
            "Travel",
            "Web Services",
            //"Social",







    } ;
    int[] imageId = {

            R.drawable.newspaper,
            R.drawable.deals,
            R.drawable.shoppingcart,
            R.drawable.food,
            R.drawable.taxi,
           // R.drawable.,
            R.drawable.onlinejobsearchsymbol,
            R.drawable.wallet,
            R.drawable.theaterticket,
            R.drawable.groceriesbag,
            R.drawable.carbuy,






    };
    int currentImage =-1;
    public static DAL layer;
    ImageButton GridImageButton;
    //  static DAL layer;
    public static InterstitialAd mInterstitialAd;
    SliderLayout sliderLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        click=0;
//        ProgressDialog progress = new ProgressDialog(this);
//        progress.setMessage("Loading");
//        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progress.setIndeterminate(false);
//       progress.show();

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        CustomGrid adapter = new CustomGrid(this, web, imageId);
        ExpandableHeightGridView grid=(ExpandableHeightGridView) findViewById(R.id.customgrid);
        grid.setAdapter(adapter);

        grid.setExpanded(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sliderLayout = (SliderLayout)findViewById(R.id.slider);






        //layer.sliderDetail();



        for(int i=0 ; i<XMENArray.size() ; i++){

            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .image(XMENArray.get(i));

            final int finalI = i;
            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    //  Toast.makeText(getApplicationContext(),XMENArray.get(finalI),Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(MainActivity.this, Webview.class);

                    mIntent.putExtra("brandUrl", DAL.sliderUrl.get(finalI));

                    startActivity(mIntent);
                }
            });

//            textSliderView
//                    .description(name)
//                    .image(Hash_file_maps.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit)
//                    .setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle());

//            textSliderView.getBundle()
//                    .putString("extra",name);
//
            sliderLayout.addSlider(textSliderView);
            currentImage=i;
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
       sliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) this);
//        //init();

        // layer.sliderDetail();

        //Log.i("count", IntroActivity.count + " "  );

        GridImageButton= (ImageButton) findViewById(R.id.os_images);

//        AdView adView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder() .setRequestAgent("android_studio:ad_template").build();
//        adView.loadAd(adRequest);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder() .build();

        mAdView.loadAd(adRequest);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        init();
        DAL layer=new DAL(this,this);

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
//        if (id == R.id.action_settings) {
//            return true;
//        }

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




        }


         else if (id == R.id.nav_share) {
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
       }


    public void itemClicked(int position) {



        CheckConnetivity check = new CheckConnetivity();
        Boolean conn = check.isNetworkAvailable(this.getApplicationContext());

        ImageButton imageButton= (ImageButton) findViewById(R.id.os_images);


        if(conn) {

            layer=new DAL(this,this);
            layer.searchProfile(web[position]);



        }


        else
        {
            check.connectivityMessage("Check Network Connection",this);

        }



    }


    private void init() {


//        for(int i=0;i<XMEN.length;i++)
//            XMENArray.add(XMEN[i]);
//
//        mPager = (ViewPager) findViewById(R.id.pager);
//        mPager.setAdapter(new SliderAdapter(MainActivity.this,XMENArray));
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);
//
//
//
//        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == XMENArray.size()) {
//                    currentPage = 0;
//                }
//                mPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 4000, 4000);
    }

    public void initializeConnection()
    {
        firebase = FirebaseDatabase.getInstance();
        //firebase.setPersistenceEnabled(true);
        table = firebase.getReference().child("MIP");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Log.d("REF",table.toString());

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume","OnResume called");
        initializeConnection();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}