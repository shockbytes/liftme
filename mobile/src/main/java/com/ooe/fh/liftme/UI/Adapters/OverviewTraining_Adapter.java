package com.ooe.fh.liftme.UI.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Layout.Elements.OverviewTraining_Listitem_Holder;
import com.ooe.fh.liftme.utils.Listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 05.11.2016.
 */

public class OverviewTraining_Adapter extends RecyclerView.Adapter<OverviewTraining_Listitem_Holder>   {

    private List<OverviewTraining_Listitem_Model> contactList;

    private Listeners.OnRecycleViewListItemClickListener onClickListener;

    public OverviewTraining_Adapter(List<OverviewTraining_Listitem_Model> contactList) {
        this.contactList = contactList;
    }

    public void setOnItemClickListener(Listeners.OnRecycleViewListItemClickListener listener) {
        onClickListener = listener;
    }

    @Override
    public OverviewTraining_Listitem_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.overviewtraining_listview_listitem, parent, false);

        return new OverviewTraining_Listitem_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(OverviewTraining_Listitem_Holder holder, int position) {
        OverviewTraining_Listitem_Model ci = contactList.get(position);
        holder.getTxtview_completetraining_name().setText(ci.getName_trainingsplan());
        holder.getRlayout_background_overview_listitem().setBackgroundColor(ci.getColor_trainingsplan());
        holder.setModel(ci);
        holder.setOnItemClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
