package com.ooe.fh.liftme.UI.Layout.Elements;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 05.11.2016.
 */

public class OverviewTraining_Listitem_Holder extends LinearLayout {

    @Bind(R.id.txtview_completetraining_name)
    TextView txtview_completetraining_name;

    @Bind(R.id.rlayout_background_overview_listitem)
    RelativeLayout rlayout_background_overview_listitem;

    //Models
    private OverviewTraining_Listitem_Model model;

    public OverviewTraining_Listitem_Holder(Context context) {
        super(context);
        inflateLayout(context);
    }

    public OverviewTraining_Listitem_Holder(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.overviewtraining_listview_listitem, this, true);
        ButterKnife.bind(rootView);
    }


    public void setData(OverviewTraining_Listitem_Model model) {

        this.model = model;
        txtview_completetraining_name.setText(model.getName_trainingsplan());
        rlayout_background_overview_listitem.setBackgroundColor(model.getColor_trainingsplan());
    }
}

