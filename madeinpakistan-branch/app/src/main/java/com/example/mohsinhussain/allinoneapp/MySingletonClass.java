package com.example.mohsinhussain.allinoneapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Hp on 8/4/2017.
 */

public class MySingletonClass {

    private static MySingletonClass mInstance;
    private static Context context;
    private RequestQueue requestQueue;

    private MySingletonClass(Context context)
    {
this.context=context;
        requestQueue=getRequestQueue();

    }

    private RequestQueue getRequestQueue()
    {

        if(requestQueue==null)
        {

            requestQueue= Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized MySingletonClass getmInstance(Context context)
    {
if (mInstance==null)
{
    mInstance=new MySingletonClass((context));

}

return mInstance;
    }


    public <T>void addToRequestQueue(Request<T> request)
    {

        getRequestQueue().add(request);
    }
}
