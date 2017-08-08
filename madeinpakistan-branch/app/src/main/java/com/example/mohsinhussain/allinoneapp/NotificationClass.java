package com.example.mohsinhussain.allinoneapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Hp on 8/3/2017.
 */

public class NotificationClass extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       // super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size()>0)
        {
            String title,message,imgurl;
            title=remoteMessage.getData().get("title");
            message=remoteMessage.getData().get("message");
            imgurl=remoteMessage.getData().get("imgurl");

            Intent intent=new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

            Uri sounduri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            final NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle(title);
            builder.setContentText(message);
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.drawable.nav_icon);
            builder.setContentIntent(pendingIntent);
            builder.setSound(sounduri);

            final ImageRequest imageRequest=new ImageRequest (imgurl, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {

                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0,builder.build());
                }
            },0,0,null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
       MySingletonClass.getmInstance(this).addToRequestQueue(imageRequest);




}

    }
}
