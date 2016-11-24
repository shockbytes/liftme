package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.AlertSelectTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Activity.MainActivity;
import com.ooe.fh.liftme.UI.Adapters.AlertSelectTraining_Adapter;
import com.ooe.fh.liftme.UI.Adapters.OverviewTraining_Adapter;
import com.ooe.fh.liftme.application.AppClass;
import com.ooe.fh.liftme.utils.Listeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 04.11.2016.
 */

public class Fragment_StartTraining_Base extends Global_Fragment{

    @Bind(R.id.btn_start_startTraining)
    Button btn_start_startTraining;

    @Bind(R.id.txtview_countdown_startTraining)
    TextView txtview_countdown_startTraining;

    @Bind(R.id.txtview_selecttraining_selectbutton)
    TextView txtview_selecttraining_selectbutton;

    @Bind(R.id.txtview_countdowntext_startTraining)
    TextView txtview_countdowntext_startTraining;

    @Bind(R.id.rlayout_background_selectbutton)
    RelativeLayout rlayout_background_selectbutton;

    //Primitive types
    private int mSelectedTraining = -1;

    //Composite types
    private List<OverviewTraining_Listitem_Model> mItemData;

    //Listeners
    public Listeners.OnStartTrainingStartButtonClickListener clickListener;


    public static Fragment_StartTraining_Base newInstance(Context context) {
        Fragment_StartTraining_Base f = new Fragment_StartTraining_Base();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemData = AppClass.overviewTraining_Listitem_models;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_starttraining_basic, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_start_startTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedTraining == -1){

                }else {
                    ((MainActivity) getActivity()).getmViewPager().setPagingEnabled(false);
                    rlayout_background_selectbutton.setEnabled(false);
                    btn_start_startTraining.setEnabled(false);
                    startTimer();
                }
            }
        });

        rlayout_background_selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTrainingDialog();
            }
        });

        updateSelectButton();
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getmViewPager().setPagingEnabled(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
            updateSelectButton();
        }
    }

    /**
     * Starts countdown of training
     */
    private void startTimer(){
        txtview_countdowntext_startTraining.setText("");
        Thread t = new Thread() {
            int countdown = 6;
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        if(countdown == -1) break;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(countdown == 0){
                                    if (clickListener != null) {
                                        clickListener.onStartTrainingStartButtonClick(mItemData.get(mSelectedTraining));
                                    }
                                }
                                else if(countdown == 1){
                                    txtview_countdown_startTraining.setText("GO");

                                }else {
                                    txtview_countdown_startTraining.setText(Integer.toString(countdown-1));
                                }
                                countdown--;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    /**
     * Updates "Select-Button" color and name
     */
    private void updateSelectButton(){
        if(AppClass.overviewTraining_Listitem_models.size() != 0){
            rlayout_background_selectbutton.setBackgroundColor(AppClass.overviewTraining_Listitem_models.get(AppClass.overviewTraining_Listitem_models.size()-1).getColor_trainingsplan());
            txtview_selecttraining_selectbutton.setText(AppClass.overviewTraining_Listitem_models.get(AppClass.overviewTraining_Listitem_models.size()-1).getName_trainingsplan());
        }
    }

    /**
     * Shows SelectTraining-Dialog
     */
    private void selectTrainingDialog() {
        List<AlertSelectTraining_Listitem_Model> alertitems = new ArrayList<>();
        for(int i = 0; i < AppClass.overviewTraining_Listitem_models.size(); i++){
            alertitems.add(new AlertSelectTraining_Listitem_Model(
                    AppClass.overviewTraining_Listitem_models.get(i).getColor_trainingsplan(),
                    AppClass.overviewTraining_Listitem_models.get(i).getName_trainingsplan()));
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.alert_select_training, null);
        dialogBuilder.setView(dialogView);

        final ListView listview_alert = (ListView) dialogView.findViewById(R.id.listview_select_training);
        final AlertSelectTraining_Adapter adapter = new AlertSelectTraining_Adapter(getContext(), alertitems);
        listview_alert.setAdapter(adapter);
        listview_alert.setDivider(null);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        listview_alert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                AlertSelectTraining_Listitem_Model listItem = (AlertSelectTraining_Listitem_Model) listview_alert.getItemAtPosition(position);
                rlayout_background_selectbutton.setBackgroundColor(listItem.getColor_selecttraining());
                txtview_selecttraining_selectbutton.setText(listItem.getName_selecttraining());
                mSelectedTraining = position;
                alertDialog.cancel();
            }});

        final Button btn_cancel_alert_selecttraining = (Button) dialogView.findViewById(R.id.btn_cancel_alert_selecttraining);
        btn_cancel_alert_selecttraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

    }

    public void setButtonClickListener(Listeners.OnStartTrainingStartButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }
}