package com.ooe.fh.liftme.Models;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Max on 05.11.2016.
 */

public class OverviewTraining_Listitem_Model extends RealmObject{

    //Referenced color of training
    private int color_trainingsplan ;
    //Name of training
    private String name_trainingsplan;
    //Exercises of training
    private RealmList<CreateTraining_Listitem_Model> exercises_traingsplan;

    public OverviewTraining_Listitem_Model() {
        super();
    }

    public OverviewTraining_Listitem_Model(int color_trainingsplan, String name_trainingsplan, List<CreateTraining_Listitem_Model> exercises_traingsplan) {
        super();
        this.color_trainingsplan = color_trainingsplan;
        this.name_trainingsplan = name_trainingsplan;
        this.exercises_traingsplan = new RealmList<CreateTraining_Listitem_Model>();
        this.exercises_traingsplan.addAll(exercises_traingsplan);
    }

    public int getColor_trainingsplan() {
        return color_trainingsplan;
    }

    public void setColor_trainingsplan(int color_trainingsplan) {
        this.color_trainingsplan = color_trainingsplan;
    }

    public String getName_trainingsplan() {
        return name_trainingsplan;
    }

    public void setName_trainingsplan(String name_trainingsplan) {
        this.name_trainingsplan = name_trainingsplan;
    }

    public List<CreateTraining_Listitem_Model> getExercises_traingsplan() {
        return exercises_traingsplan;
    }

    public void setExercises_traingsplan(ArrayList<CreateTraining_Listitem_Model> exercises_traingsplan) {
        this.exercises_traingsplan = new RealmList<CreateTraining_Listitem_Model>();
        this.exercises_traingsplan.addAll(exercises_traingsplan);
    }
}
