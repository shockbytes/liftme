package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Adapters.OverviewTraining_Adapter;
import com.ooe.fh.liftme.application.AppClass;
import com.ooe.fh.liftme.utils.Listeners;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 04.11.2016.
 */

public class Fragment_OverviewTraining_Base extends Global_Fragment{

    @Bind(R.id.listview_overviewtraining)
    ListView listview_overviewtraining;

    //Composite types
    private List<OverviewTraining_Listitem_Model> mItemData;

    //Listeners
    public Listeners.OnOverviewTrainingListItemClickListener clickListener;


    public static Fragment_OverviewTraining_Base newInstance(Context context) {
        Fragment_OverviewTraining_Base f = new Fragment_OverviewTraining_Base();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemData = AppClass.overviewTraining_Listitem_models;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overviewtraining_basic, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final OverviewTraining_Adapter adapter = new OverviewTraining_Adapter(getContext(), mItemData);
        listview_overviewtraining.setAdapter(adapter);
        listview_overviewtraining.setDivider(null);
        listview_overviewtraining.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                OverviewTraining_Listitem_Model listItem = (OverviewTraining_Listitem_Model) listview_overviewtraining.getItemAtPosition(position);
                if (clickListener != null) {
                    clickListener.onOverviewTrainingListItemClick(listItem);
                }
            }});
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setButtonClickListener(Listeners.OnOverviewTrainingListItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
