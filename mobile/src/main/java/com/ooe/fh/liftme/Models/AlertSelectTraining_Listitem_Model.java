package com.ooe.fh.liftme.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 08.11.2016.
 */

public class AlertSelectTraining_Listitem_Model {

    //Referenced color of training
    private int color_selecttraining ;
    //Name of training
    private String name_selecttraining;


    public AlertSelectTraining_Listitem_Model() {
        super();
    }

    public AlertSelectTraining_Listitem_Model(int color_selecttraining, String name_selecttraining) {
        super();
        this.color_selecttraining = color_selecttraining;
        this.name_selecttraining = name_selecttraining;
    }

    public int getColor_selecttraining() {
        return color_selecttraining;
    }

    public void setColor_selecttraining(int color_selecttraining) {
        this.color_selecttraining = color_selecttraining;
    }

    public String getName_selecttraining() {
        return name_selecttraining;
    }

    public void setName_selecttraining(String name_selecttraining) {
        this.name_selecttraining = name_selecttraining;
    }
}
