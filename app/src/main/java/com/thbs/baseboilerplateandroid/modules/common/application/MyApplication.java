package com.thbs.baseboilerplateandroid.modules.common.application;

/**
 * Created by muhammed_suhail on 3/12/2018.
 */

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;
    private static Application applicaion;

    public static Context getContext() {
        return MyApplication.context;
    }

    public static Application getApplicaion() {
        return MyApplication.applicaion;
    }

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        MyApplication.applicaion = getApplicaion();
    }

}
