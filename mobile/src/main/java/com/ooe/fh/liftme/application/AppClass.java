package com.ooe.fh.liftme.application;

import android.app.Application;
import android.content.Context;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

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
        overviewTraining_Listitem_models = new ArrayList<OverviewTraining_Listitem_Model>();
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<OverviewTraining_Listitem_Model> trainings = realm.where(OverviewTraining_Listitem_Model.class).findAll();
        overviewTraining_Listitem_models.addAll(trainings);
    }

    public static Context getAppContext() {
        return AppClass.context;
    }
}
