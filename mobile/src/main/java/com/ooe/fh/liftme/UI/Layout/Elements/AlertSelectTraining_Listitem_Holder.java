package com.ooe.fh.liftme.UI.Layout.Elements;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.AlertSelectTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 08.11.2016.
 */

public class AlertSelectTraining_Listitem_Holder extends LinearLayout {

    @Bind(R.id.txtview_name_alert_selecttraining)
    TextView txtview_name_alert_selecttraining;

    @Bind(R.id.txtview_colorbox_alert_selecttraining)
    TextView txtview_colorbox_alert_selecttraining;

    //Models
    private AlertSelectTraining_Listitem_Model model;

    public AlertSelectTraining_Listitem_Holder(Context context) {
        super(context);
        inflateLayout(context);
    }

    public AlertSelectTraining_Listitem_Holder(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.alert_selecttraining_listview_listitem, this, true);
        ButterKnife.bind(rootView);
    }


    public void setData(AlertSelectTraining_Listitem_Model model) {

        this.model = model;
        txtview_name_alert_selecttraining.setText(model.getName_selecttraining());
        txtview_colorbox_alert_selecttraining.setBackgroundColor(model.getColor_selecttraining());
    }
}
