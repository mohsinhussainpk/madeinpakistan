package com.example.mohsinhussain.allinoneapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Hp on 8/3/2017.
 */

public class CustomDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no,rate;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cutom_dialogue);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        rate=(Button) findViewById(R.id.btn_rate);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        rate.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                SplashActivity.timer=0; // to open main activty direct when open again (not closed)
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            case R.id.btn_rate:
                //final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

                c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=Orion.Soft")));

                break;
            default:
                break;
        }


    }
}