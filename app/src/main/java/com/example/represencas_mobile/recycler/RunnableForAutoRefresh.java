package com.example.represencas_mobile.recycler;

import android.os.Handler;

public class RunnableForAutoRefresh {

    private Handler handler;
    private Runnable runnable;
    private final static int UPDATE_INTERVAL = 1000;

    private void go() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //Get data from the server
                handler.postDelayed(runnable, UPDATE_INTERVAL);
            }
        };
        handler.postDelayed(runnable, UPDATE_INTERVAL);
    }
}
