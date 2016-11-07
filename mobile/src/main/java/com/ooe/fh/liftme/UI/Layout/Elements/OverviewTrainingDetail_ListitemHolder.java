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
 * Created by Max on 05.11.2016.
 */

public class OverviewTrainingDetail_ListitemHolder extends LinearLayout {

    @Bind(R.id.txtview_overviewdetail_name)
    TextView txtview_overviewdetail_name;

    @Bind(R.id.txtview_overviewdetail_repetition)
    TextView txtview_overviewdetail_repetition;

    //Models
    private CreateTraining_Listitem_Model model;

    public OverviewTrainingDetail_ListitemHolder(Context context) {
        super(context);
        inflateLayout(context);
    }

    public OverviewTrainingDetail_ListitemHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.overviewtraining_detail_listview_listitem, this, true);
        ButterKnife.bind(rootView);
    }


    public void setData(CreateTraining_Listitem_Model model) {

        this.model = model;
        txtview_overviewdetail_name.setText(model.getTitle_trainingsplan_listitem());
        txtview_overviewdetail_repetition.setText(Integer.toString(model.getAmount_trainingsplan_listitem()));
    }
}

