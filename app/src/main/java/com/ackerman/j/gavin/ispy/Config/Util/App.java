package com.ackerman.j.gavin.ispy.Config.Util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseHelper;
import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseManager;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;



/**
 * Created by hashcode on 2016/04/16.
 */
public class App extends Application {

    private static Context context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static DatabaseHelper dbHelper;
    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
        dbHelper = new DatabaseHelper();
        DatabaseManager.initializeInstance(dbHelper);
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();


    public static synchronized App getInstance() {
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }



    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
