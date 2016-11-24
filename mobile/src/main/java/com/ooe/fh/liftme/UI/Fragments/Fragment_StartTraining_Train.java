package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.TrainExercise_Page_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Adapters.Train_PagerAdapter;
import com.ooe.fh.liftme.UI.Layout.Elements.TrainExercise_Page_Holder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Max on 05.11.2016.
 */

public class Fragment_StartTraining_Train extends Global_Fragment {

    @Bind(R.id.chronometer1)
    Chronometer chronometer1;

    @Bind(R.id.pager_train)
    ViewPager pager_train;

    @Bind(R.id.txtview_train_title)
    TextView txtview_train_title;

    //Adapters
    private Train_PagerAdapter mPagerAdapter;

    //Model
    OverviewTraining_Listitem_Model model;

    //Primitive types
    int mActuallPage = 0;

    public static Fragment_StartTraining_Train newInstance(Context context, OverviewTraining_Listitem_Model _model) {
        Fragment_StartTraining_Train f = new Fragment_StartTraining_Train();
        f.setData(_model);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_starttraining_train, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startChronometer();
        txtview_train_title.setText(model.getName_trainingsplan());
        mPagerAdapter = new Train_PagerAdapter();
        createAllPage();
        pager_train.setAdapter(mPagerAdapter);


        pager_train.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {return true;}
        });

        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    /**
     * Starts the chronometer
     */
    private void startChronometer() {
        chronometer1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer cArg) {
                long time = SystemClock.elapsedRealtime() - cArg.getBase();
                int h = (int) (time / 3600000);
                int m = (int) (time - h * 3600000) / 60000;
                int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                String hh = h < 10 ? "0" + h : h + "";
                String mm = m < 10 ? "0" + m : m + "";
                String ss = s < 10 ? "0" + s : s + "";
                cArg.setText(hh + ":" + mm + ":" + ss);
            }
        });
        chronometer1.setBase(SystemClock.elapsedRealtime());
        chronometer1.start();

    }

    private void updateViewText(String updatedText) {
        TextView exercise = (TextView) mPagerAdapter.getView(mActuallPage).findViewById(R.id.txtview_exercise_name_pager);

        if(exercise.getText().toString().equals("Pause")) {
            pauseTimer();
            mActuallPage++;
            pager_train.setCurrentItem(mActuallPage);
        }else {
            TextView toDoRep = (TextView) mPagerAdapter.getView(mActuallPage).findViewById(R.id.txtview_todorepetition);
            TextView actualRep = (TextView) mPagerAdapter.getView(mActuallPage).findViewById(R.id.txtview_actualrepetition);

            if (Integer.parseInt(updatedText) == Integer.parseInt(actualRep.getText().toString())) {
                mActuallPage++;
                pager_train.setCurrentItem(mActuallPage);
            } else {
                toDoRep.setText(updatedText);
            }
        }
    }

    private void setData(OverviewTraining_Listitem_Model _model) {
        model = _model;
    }

    private void createAllPage() {
        int bgID = 0;
        for (int i = 0; i < model.getExercises_traingsplan().size(); i++) {
            TrainExercise_Page_Holder test = new TrainExercise_Page_Holder(getContext());
            if(model.getExercises_traingsplan().get(i).getTitle_trainingsplan_listitem().equals("Curls")){
                bgID = R.drawable.bg_curlsbackground;
            }else if(model.getExercises_traingsplan().get(i).getTitle_trainingsplan_listitem().equals("Bench Press")){
                bgID = R.drawable.bg_benchpress;
            }else if(model.getExercises_traingsplan().get(i).getTitle_trainingsplan_listitem().equals("Butterfly")){
                bgID = R.drawable.bg_butterfly;
            }else{
                bgID = R.drawable.bg_pause;
            }
            test.setData(new TrainExercise_Page_Model(model.getExercises_traingsplan().get(i).getTitle_trainingsplan_listitem(), model.getExercises_traingsplan().get(i).getAmount_trainingsplan_listitem(),bgID));
            mPagerAdapter.addView(test, i);
        }
    }

    /**
     * Starts paustimer of training
     */
    private void pauseTimer(){
        TextView toDoRep = (TextView) mPagerAdapter.getView(mActuallPage).findViewById(R.id.txtview_todorepetition);
        final TextView actualRep = (TextView) mPagerAdapter.getView(mActuallPage).findViewById(R.id.txtview_actualrepetition);
        final int intToDoRep = Integer.parseInt(toDoRep.getText().toString());
        Thread t = new Thread() {
            int countdown = 0;
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        if(countdown == intToDoRep) break;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                actualRep.setText(Integer.toString(countdown));
                                countdown++;
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }
}
