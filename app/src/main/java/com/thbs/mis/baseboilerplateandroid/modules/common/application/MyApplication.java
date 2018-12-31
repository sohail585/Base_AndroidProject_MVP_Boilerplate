package com.thbs.mis.baseboilerplateandroid.modules.common.application;

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
