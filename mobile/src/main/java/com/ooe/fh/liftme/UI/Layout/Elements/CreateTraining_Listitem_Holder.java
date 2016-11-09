package com.ooe.fh.liftme.UI.Layout.Elements;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.utils.Listeners;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 04.11.2016.
 */


public class CreateTraining_Listitem_Holder extends RecyclerView.ViewHolder {

    @Bind(R.id.rlayout_exercise_background)
    RelativeLayout rlayout_exercise_background;

    @Bind(R.id.llayout_exercise_listitem)
    LinearLayout llayout_exercise_listitem;

    @Bind(R.id.txtview_exercise_name)
    TextView txtview_exercise_name;

    @Bind(R.id.rlayout_repetition_background)
    RelativeLayout rlayout_repetition_background;

    @Bind(R.id.txtview_exercise_repetition)
    TextView txtview_exercise_repetition;


    //Models
    private CreateTraining_Listitem_Model model;

    public CreateTraining_Listitem_Holder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public RelativeLayout getRlayout_exercise_background() {
        return rlayout_exercise_background;
    }

    public void setRlayout_exercise_background(RelativeLayout rlayout_exercise_background) {
        this.rlayout_exercise_background = rlayout_exercise_background;
    }

    public LinearLayout getLlayout_exercise_listitem() {
        return llayout_exercise_listitem;
    }

    public void setLlayout_exercise_listitem(LinearLayout llayout_exercise_listitem) {
        this.llayout_exercise_listitem = llayout_exercise_listitem;
    }

    public TextView getTxtview_exercise_name() {
        return txtview_exercise_name;
    }

    public void setTxtview_exercise_name(TextView txtview_exercise_name) {
        this.txtview_exercise_name = txtview_exercise_name;
    }

    public RelativeLayout getRlayout_repetition_background() {
        return rlayout_repetition_background;
    }

    public void setRlayout_repetition_background(RelativeLayout rlayout_repetition_background) {
        this.rlayout_repetition_background = rlayout_repetition_background;
    }

    public TextView getTxtview_exercise_repetition() {
        return txtview_exercise_repetition;
    }

    public void setTxtview_exercise_repetition(TextView txtview_exercise_repetition) {
        this.txtview_exercise_repetition = txtview_exercise_repetition;
    }

    public CreateTraining_Listitem_Model getModel() {
        return model;
    }

    public void setModel(CreateTraining_Listitem_Model model) {
        this.model = model;
    }
}
