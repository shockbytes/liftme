package com.ooe.fh.liftme.UI.Layout.Elements;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.utils.Listeners;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 05.11.2016.
 */

public class OverviewTraining_Listitem_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.txtview_completetraining_name)
    TextView txtview_completetraining_name;

    @Bind(R.id.rlayout_background_overview_listitem)
    RelativeLayout rlayout_background_overview_listitem;

    //Models
    private OverviewTraining_Listitem_Model model;

    private Listeners.OnRecycleViewListItemClickListener onClickListener;


    public OverviewTraining_Listitem_Holder(View view) {
        super(view);
        ButterKnife.bind(this,view);
        view.setOnClickListener(this);
    }

    public void setOnItemClickListener(Listeners.OnRecycleViewListItemClickListener listener) {
        onClickListener = listener;
    }

    public TextView getTxtview_completetraining_name() {
        return txtview_completetraining_name;
    }

    public void setTxtview_completetraining_name(TextView txtview_completetraining_name) {
        this.txtview_completetraining_name = txtview_completetraining_name;
    }

    public RelativeLayout getRlayout_background_overview_listitem() {
        return rlayout_background_overview_listitem;
    }

    public void setRlayout_background_overview_listitem(RelativeLayout rlayout_background_overview_listitem) {
        this.rlayout_background_overview_listitem = rlayout_background_overview_listitem;
    }

    public OverviewTraining_Listitem_Model getModel() {
        return model;
    }

    public void setModel(OverviewTraining_Listitem_Model model) {
        this.model = model;
    }

    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.onOverviewTrainingListItemClick(model);
        }
    }
}

