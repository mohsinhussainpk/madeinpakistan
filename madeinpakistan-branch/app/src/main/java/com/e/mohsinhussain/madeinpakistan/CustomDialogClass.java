package com.e.mohsinhussain.madeinpakistan;

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
public static boolean mainactivityexit=false;
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
                //SplashActivity.timer1=0;
                //ExitActivity exitActivity=new ExitActivity();

                if( ! (c instanceof MainActivity))
                {
                    mainactivityexit=true;

                    Intent intent=new Intent(c,MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    c.setResult(123);
                    c.startActivity(intent);


                }
                else
                {

                    c.finish();

                }


                break;
            case R.id.btn_no:
                dismiss();
                break;
            case R.id.btn_rate:
                //final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object

                c.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.e.mohsinhussain.madeinpakistan")));

                break;
            default:
                break;
        }


    }
}

//class ExitActivity extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (android.os.Build.VERSION.SDK_INT >= 21) {
//            finishAndRemoveTask();
//        } else {
//            finish();
//        }
//    }
//
//    public static void exitApplication(Context context) {
//        Intent intent = new Intent(context, ExitActivity.class);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//
//        context.startActivity(intent);
//    }
//}