package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Adapters.OverviewDetailTraining_Adapter;
import com.ooe.fh.liftme.utils.Listeners;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 05.11.2016.
 */

public class Fragment_OverviewTraining_Detail extends Global_Fragment{

    @Bind(R.id.txtview_overviewtrainingdetail_title)
    TextView txtview_overviewtrainingdetail_title;

    @Bind(R.id.btn_close_overviewtrainingdetail)
    Button btn_close_overviewtrainingdetail;

    @Bind(R.id.listview_overviewtrainingdetail)
    ListView listview_overviewtrainingdetail;

    //Models
    private OverviewTraining_Listitem_Model mModel;

    //Composite types
    private List<CreateTraining_Listitem_Model> mItemData;

    //Listeners
    public Listeners.OnOverviewDetailButtonCloseClickListener clickListener;

    public static Fragment_OverviewTraining_Detail newInstance(Context context, OverviewTraining_Listitem_Model _model) {
        Fragment_OverviewTraining_Detail f = new Fragment_OverviewTraining_Detail();
        f.setDatas(_model);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemData = mModel.getExercises_traingsplan();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overviewtraining_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        txtview_overviewtrainingdetail_title.setText(mModel.getName_trainingsplan());

        final OverviewDetailTraining_Adapter adapter = new OverviewDetailTraining_Adapter(getContext(), mItemData);
        listview_overviewtrainingdetail.setAdapter(adapter);
        listview_overviewtrainingdetail.setDivider(null);

        btn_close_overviewtrainingdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onOverviewDetailButtonCloseClick();
                }
            }
        });
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Helper-Method to set itemData
     * Necessary, because newInstance is static!
     *
     * @param _model        Model of clicked OverviewTraining_Listitem_Model
     */
    private void setDatas(OverviewTraining_Listitem_Model _model) {
        mModel = _model;
    }

    public void setButtonClickListener(Listeners.OnOverviewDetailButtonCloseClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
