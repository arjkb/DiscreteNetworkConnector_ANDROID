package com.example.classiclogic.discretenetworkconnector;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by arjun on 21/11/15.
 */
public class MyLocalService extends Service {

    final String LOGTAG = "DNC_LOG";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showNotification(String message) {
        Log.v(LOGTAG, " MyLocalService: showNotification() " + message);
    }
}
