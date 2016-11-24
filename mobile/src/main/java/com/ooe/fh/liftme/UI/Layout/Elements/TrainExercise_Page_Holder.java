package com.ooe.fh.liftme.UI.Layout.Elements;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.TrainExercise_Page_Model;
import com.ooe.fh.liftme.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 23.11.2016.
 */

public class TrainExercise_Page_Holder  extends FrameLayout {

    @Bind(R.id.dynamicview_background)
    RelativeLayout dynamicview_background;

    @Bind(R.id.txtview_actualrepetition)
    TextView txtview_actualrepetition;

    @Bind(R.id.txtview_todorepetition)
    TextView txtview_todorepetition;

    @Bind(R.id.txtview_exercise_name_pager)
    TextView txtview_exercise_name_pager;


    private TrainExercise_Page_Model model;

    public TrainExercise_Page_Holder(Context context) {
        super(context);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.trainfragment_pager_layout, this, true);
        ButterKnife.bind(rootView);
    }

    public void setData(TrainExercise_Page_Model model){
        this.model = model;
        dynamicview_background.setBackground(ContextCompat.getDrawable(getContext(), model.getBackgroundID()));
        txtview_actualrepetition.setText(Integer.toString(model.getActualAmount_trainingsplan_listitem()));
        txtview_todorepetition.setText(Integer.toString(model.getAmount_trainingsplan_listitem()));
        txtview_exercise_name_pager.setText(model.getTitle_trainingsplan_listitem());
    }
}
