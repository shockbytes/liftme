package com.ooe.fh.liftme.Models;


import io.realm.RealmObject;

/**
 * Created by Max on 04.11.2016.
 */

public class CreateTraining_Listitem_Model extends RealmObject {

    //Amount of repetitions
    private int amount_trainingsplan_listitem;
    //Name of exercise
    private String title_trainingsplan_listitem;
    //Exercise and repetition inserted
    private boolean completeModel;

    //background colors
    private int title_background_color;
    private int amount_background_color;

    //position
    private int positionAddedToRecycleView = -1;

    public CreateTraining_Listitem_Model() {
        super();
    }

    public CreateTraining_Listitem_Model(String title_trainingsplan_listitem, int amount_trainingsplan_listitem,
                                         int title_background_color, int amount_background_color, int positionAddedToRecycleView) {
        super();
        this.amount_trainingsplan_listitem = amount_trainingsplan_listitem;
        this.title_trainingsplan_listitem = title_trainingsplan_listitem;
        this.title_background_color = title_background_color;
        this.amount_background_color = amount_background_color;
        this.positionAddedToRecycleView = positionAddedToRecycleView;
        completeModel = false;
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

    public int getAmount_background_color() {
        return amount_background_color;
    }

    public void setAmount_background_color(int amount_background_color) {
        this.amount_background_color = amount_background_color;
    }

    public int getTitle_background_color() {
        return title_background_color;
    }

    public void setTitle_background_color(int title_background_color) {
        this.title_background_color = title_background_color;
    }

    public int getpositionAddedToRecycleView() {
        return positionAddedToRecycleView;
    }

    public void setpositionAddedToRecycleView(int positionAddedToRecycleView) {
        this.positionAddedToRecycleView = positionAddedToRecycleView;
    }

    public boolean isCompleteModel() {
        return completeModel;
    }

    public void setCompleteModel(boolean completeModel) {
        this.completeModel = completeModel;
    }
}
