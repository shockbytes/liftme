package com.ooe.fh.liftme.UI.Layout.Elements;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 04.11.2016.
 */


public class CreateTraining_Listitem_Holder extends LinearLayout {

    @Bind(R.id.txtview_exercise_name)
    TextView txtview_exercise_name;

    @Bind(R.id.txtview_exercise_repetition)
    TextView txtview_exercice_repetition;

    //Models
    private CreateTraining_Listitem_Model model;

    public CreateTraining_Listitem_Holder(Context context) {
        super(context);
        inflateLayout(context);
    }

    public CreateTraining_Listitem_Holder(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.createtraining_listview_listitem, this, true);
        ButterKnife.bind(rootView);
    }


    public void setData(CreateTraining_Listitem_Model model) {

        this.model = model;
        txtview_exercise_name.setText(model.getTitle_trainingsplan_listitem());
        txtview_exercice_repetition.setText(Integer.toString(model.getAmount_trainingsplan_listitem()));
    }
}
