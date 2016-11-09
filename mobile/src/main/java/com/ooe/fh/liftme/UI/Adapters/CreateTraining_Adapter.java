package com.ooe.fh.liftme.UI.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Layout.Elements.CreateTraining_Listitem_Holder;
import com.ooe.fh.liftme.UI.Layout.Elements.OverviewTraining_Listitem_Holder;
import com.ooe.fh.liftme.utils.Listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 04.11.2016.
 */

public class CreateTraining_Adapter extends RecyclerView.Adapter<CreateTraining_Listitem_Holder> {

    private List<CreateTraining_Listitem_Model> contactList;

    private Listeners.OnRecycleViewListItemClickListener onClickListener;

    public CreateTraining_Adapter(List<CreateTraining_Listitem_Model> contactList) {
        this.contactList = contactList;
    }

    @Override
    public CreateTraining_Listitem_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.createtraining_listview_listitem, parent, false);

        return new CreateTraining_Listitem_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(CreateTraining_Listitem_Holder holder, int position) {
        CreateTraining_Listitem_Model ci = contactList.get(position);
        holder.getTxtview_exercise_name().setText(ci.getTitle_trainingsplan_listitem());
        holder.getTxtview_exercise_repetition().setText(Integer.toString(ci.getAmount_trainingsplan_listitem()));
        holder.setModel(ci);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
