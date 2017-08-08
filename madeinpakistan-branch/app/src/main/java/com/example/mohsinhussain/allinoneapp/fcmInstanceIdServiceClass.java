package com.example.mohsinhussain.allinoneapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Hp on 8/4/2017.
 */

public class fcmInstanceIdServiceClass extends FirebaseInstanceIdService{

    @Override
    public void onTokenRefresh() {

        String fcm_token= FirebaseInstanceId.getInstance().getToken();
        Log.i("Fcm token",fcm_token);

    }
}
