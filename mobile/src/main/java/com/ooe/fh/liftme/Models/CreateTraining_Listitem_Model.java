package com.ooe.fh.liftme.Models;

/**
 * Created by Max on 04.11.2016.
 */

public class CreateTraining_Listitem_Model {

    //Amount of repetitions
    private int amount_trainingsplan_listitem;
    //Name of exercise
    private String title_trainingsplan_listitem;

    public CreateTraining_Listitem_Model() {
        super();
    }

    public CreateTraining_Listitem_Model(String title_trainingsplan_listitem, int amount_trainingsplan_listitem) {
        super();
        this.amount_trainingsplan_listitem = amount_trainingsplan_listitem;
        this.title_trainingsplan_listitem = title_trainingsplan_listitem;
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
}
