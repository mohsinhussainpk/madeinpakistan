package com.e.mohsinhussain.madeinpakistan;

import android.app.Activity;
import android.app.ProgressDialog;


import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends Activity {

    public static FirebaseDatabase firebase;
    public static DatabaseReference database;
    public static DatabaseReference table;
    public static StorageReference mStorageRef;
    private static String DB_NAME = "MIP";
    private ProgressDialog Dialog;
    static  int timer=5000;
    static  int timer1=2000;
    CheckConnetivity check;
    Boolean conn;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_splash);
//
//
//
//
////        if (!isTaskRoot()
////                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
////                && getIntent().getAction() != null
////                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {
////
////            finish();
////            return;
////        }
//
////        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
////        if (preferences.getBoolean("firstTime", true)) {
////            // Show splash screen
////            // Wait a few seconds.
////        } else {
////            // Nothing to do here. Go straight to the second activity.
////        }
////        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
////                getSupportActivity()).edit();
////        editor.putBoolean("firstTime", false);
////        editor.commit();
////        startActivity(MainActivity.this, ...)
////        finish();
//
//
//
//    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /**
     * Called when the activity is first created.
     */
    Thread splashTread;
    DAL layer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        CheckConnetivity check = new CheckConnetivity();
        Boolean conn = check.isNetworkAvailable(this.getApplicationContext());
        DAL layer=new DAL(this,this);

        if(conn) {
            firebase = FirebaseDatabase.getInstance();
            //firebase.setPersistenceEnabled(true);

            database = firebase.getReference(DB_NAME);
//        table = database.child(ENTITY_NAME_PROFILES);

            mStorageRef = FirebaseStorage.getInstance().getReference();

if(timer!=0)
{

    StartAnimations();

}
            layer.sliderDetail();






        }


        else
        {
            check.connectivityMessage("Check Network Connection",this);

        }






    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash1);
        ImageView iv1 = (ImageView) findViewById(R.id.splash2);

        iv.clearAnimation();
        iv1.clearAnimation();
        iv.startAnimation(anim);
        iv1.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < timer) {
                        sleep(100);
                        waited += 100;
                    }

                    SplashActivity.this.finish();

                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }
}
