package com.ooe.fh.liftme.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.ooe.fh.liftme.R;

import butterknife.ButterKnife;

/**
 * Created by Max on 23.11.2016.
 */

public class TrainExercise_Page_Model {


    //Actual amount of repetitions
    private int actualAmount_trainingsplan_listitem;
    //Amount of repetitions
    private int amount_trainingsplan_listitem;
    //Name of exercise
    private String title_trainingsplan_listitem;
    private int backgroundID;


    public TrainExercise_Page_Model(String title_trainingsplan_listitem, int amount_trainingsplan_listitem, int backgroundID) {
        actualAmount_trainingsplan_listitem = 0;
        this.amount_trainingsplan_listitem = amount_trainingsplan_listitem;
        this.backgroundID = backgroundID;
        this.title_trainingsplan_listitem = title_trainingsplan_listitem;
    }

    public int getActualAmount_trainingsplan_listitem() {
        return actualAmount_trainingsplan_listitem;
    }

    public void setActualAmount_trainingsplan_listitem(int actualAmount_trainingsplan_listitem) {
        this.actualAmount_trainingsplan_listitem = actualAmount_trainingsplan_listitem;
    }

    public int getAmount_trainingsplan_listitem() {
        return amount_trainingsplan_listitem;
    }

    public void setAmount_trainingsplan_listitem(int amount_trainingsplan_listitem) {
        this.amount_trainingsplan_listitem = amount_trainingsplan_listitem;
    }

    public String getTitle_trainingsplan_listitem() {
        return title_trainingsplan_listitem;
    }

    public void setTitle_trainingsplan_listitem(String title_trainingsplan_listitem) {
        this.title_trainingsplan_listitem = title_trainingsplan_listitem;
    }

    public int getBackgroundID() {
        return backgroundID;
    }

    public void setBackgroundID(int backgroundID) {
        this.backgroundID = backgroundID;
    }
}
