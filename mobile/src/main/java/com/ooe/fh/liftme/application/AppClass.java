package com.ooe.fh.liftme.application;

import android.app.Application;
import android.content.Context;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;

import java.util.List;

/**
 * Created by m.altenhuber on 5/11/2016.
 */
public class AppClass extends Application {

    //Static Context
    private static Context context;

    //Static Models
    public static List<OverviewTraining_Listitem_Model> overviewTraining_Listitem_models;


    public void onCreate(){
        super.onCreate();
        AppClass.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AppClass.context;
    }
}
