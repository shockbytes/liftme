package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Activity.MainActivity;
import com.ooe.fh.liftme.application.AppClass;
import com.ooe.fh.liftme.utils.Listeners;

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
                ((MainActivity)getActivity()).getmViewPager().setPagingEnabled(false);
                rlayout_background_selectbutton.setEnabled(false);
                btn_start_startTraining.setEnabled(false);
                startTimer();
            }
        });

        rlayout_background_selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo - Show all created trainings
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
            int countdown = 5;
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if(countdown == 0) break;
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(countdown == 0){
                                    txtview_countdown_startTraining.setText("GO");
                                    if (clickListener != null) {
                                        clickListener.onStartTrainingStartButtonClick();
                                    }
                                }else {
                                    txtview_countdown_startTraining.setText(Integer.toString(countdown));
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

    public void setButtonClickListener(Listeners.OnStartTrainingStartButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }
}