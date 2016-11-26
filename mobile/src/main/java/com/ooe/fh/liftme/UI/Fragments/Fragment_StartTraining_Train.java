package com.ooe.fh.liftme.UI.Fragments;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Wearable;
import com.ibm.mobilefirst.mobileedge.MobileEdgeController;
import com.ibm.mobilefirst.mobileedge.connectors.AndroidWear;
import com.ibm.mobilefirst.mobileedge.interpretation.Classification;
import com.ibm.mobilefirst.mobileedge.interpretation.InterpretationListener;
import com.ibm.mobilefirst.mobileedge.utils.GesturesDataUtils;
import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.TrainExercise_Page_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Activity.MainActivity;
import com.ooe.fh.liftme.UI.Adapters.Train_PagerAdapter;
import com.ooe.fh.liftme.UI.Layout.Elements.TrainExercise_Page_Holder;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:  Max
 * Date:    05.11.2016.
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
    int mActuallRepetition = 0;

    private AndroidWear wearConnector;
    private MobileEdgeController wearController;
    private Classification classification;
    private GoogleApiClient mGoogleApiClient;
    private String mNodeID;

    public static Fragment_StartTraining_Train newInstance(OverviewTraining_Listitem_Model _model) {
        Fragment_StartTraining_Train f = new Fragment_StartTraining_Train();
        f.setData(_model);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createExerciseRecognition();
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

        mGoogleApiClient = ((MainActivity) getActivity()).getGoogleApiClient();
        mNodeID = ((MainActivity) getActivity()).getNodeId();
        startChronometer();
        txtview_train_title.setText(model.getName_trainingsplan());

        mPagerAdapter = new Train_PagerAdapter();
        createAllPage();
        pager_train.setAdapter(mPagerAdapter);
        pager_train.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setHasOptionsMenu(true);
    }

    /**
     * Starts the chronometer
     */
    private void startChronometer() {
        chronometer1.setBase(SystemClock.elapsedRealtime());
        chronometer1.start();

    }

    private void switchExercise() {

        final int intToDoRep = model.getExercises_traingsplan().get(mActuallPage).getAmount_trainingsplan_listitem();
        String exercise = model.getExercises_traingsplan().get(mActuallPage).getTitle_trainingsplan_listitem();

        mActuallRepetition = 0;
        pager_train.setCurrentItem(mActuallPage);
        String data = exercise + "_" + intToDoRep;
        sendMessage("exercise", data.getBytes());
    }

    private void updateViewText() {

        mActuallRepetition++;
        String exercise = model.getExercises_traingsplan().get(mActuallPage).getTitle_trainingsplan_listitem();
        int todoRep = model.getExercises_traingsplan().get(mActuallPage).getAmount_trainingsplan_listitem();
        if (exercise.equals("Pause")) {
            String data = "pause_" + todoRep;
            sendMessage("exercise", data.getBytes());
            pauseTimer();
            pager_train.setCurrentItem(mActuallPage);
            mActuallRepetition = 0;
        } else {

            TextView actualRep = (TextView) mPagerAdapter.getView(mActuallPage).findViewById(R.id.txtview_actualrepetition);
            if (mActuallRepetition == todoRep) {
                mActuallPage++;
                if (mActuallPage >= mPagerAdapter.getCount()) {
                    sendMessage("end", null);
                } else {
                    switchExercise();
                    updateViewText();
                }
            } else {
                actualRep.setText(String.valueOf(mActuallRepetition));
            }
        }
    }

    private void setData(OverviewTraining_Listitem_Model _model) {
        model = _model;
    }

    private void createAllPage() {
        int bgID;
        for (int i = 0; i < model.getExercises_traingsplan().size(); i++) {
            TrainExercise_Page_Holder test = new TrainExercise_Page_Holder(getContext());
            if (model.getExercises_traingsplan().get(i)
                    .getTitle_trainingsplan_listitem().equals("Curls")) {
                bgID = R.drawable.bg_curlsbackground;
            } else if (model.getExercises_traingsplan().get(i)
                    .getTitle_trainingsplan_listitem().equals("Bench Press")) {
                bgID = R.drawable.bg_benchpress;
            } else if (model.getExercises_traingsplan().get(i)
                    .getTitle_trainingsplan_listitem().equals("Butterfly")) {
                bgID = R.drawable.bg_butterfly;
            } else {
                bgID = R.drawable.bg_pause;
            }
            test.setData(new TrainExercise_Page_Model(
                    model.getExercises_traingsplan().get(i)
                            .getTitle_trainingsplan_listitem(),
                    model.getExercises_traingsplan().get(i)
                            .getAmount_trainingsplan_listitem(), bgID));
            mPagerAdapter.addView(test, i);
        }
    }

    /**
     * Starts paustimer of training
     */
    private void pauseTimer() {

        final TextView actualRep = (TextView) mPagerAdapter.getView(mActuallPage)
                .findViewById(R.id.txtview_actualrepetition);
        final int intToDoRep = model.getExercises_traingsplan().get(mActuallPage)
                .getAmount_trainingsplan_listitem();
        final Timer t = new Timer();
        final TimerTask tt = new TimerTask() {
            int countdown = 0;

            @Override
            public void run() {

                if (countdown >= intToDoRep) {
                    t.cancel();
                    t.purge();
                    mActuallPage++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switchExercise();
                        }
                    });
                    return;
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        actualRep.setText(String.valueOf(countdown));
                        countdown++;
                    }
                });
            }
        };
        t.schedule(tt, 0, 1000);
    }

    @Override
    public void onStart() {
        super.onStart();
        startExerciseRecognition();

        sendMessage("start", null);

        CreateTraining_Listitem_Model first = model.getExercises_traingsplan().get(0);
        String data = first.getTitle_trainingsplan_listitem() + "_" + first.getAmount_trainingsplan_listitem();
        sendMessage("exercise", data.getBytes());
    }

    @Override
    public void onStop() {
        super.onStop();
        stopExerciseRecognition();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyExerciseRecognition();
    }

    private void createExerciseRecognition() {

        wearConnector = new AndroidWear();
        wearController = new MobileEdgeController();
        classification = new Classification(getActivity());

        wearController.connect(getActivity(), wearConnector);
        loadGestures();
    }

    private void startExerciseRecognition() {

        classification.setListener(new InterpretationListener() {
            @Override
            public void onInterpretationDetected(String name, Object additionalInfo) {

                JSONObject jsonResult = (JSONObject) additionalInfo;
                handleResult(jsonResult);
            }
        });

        wearController.registerInterpretation(classification);
        wearController.turnClassificationSensorsOn();
    }

    private void stopExerciseRecognition() {
        wearController.turnClassificationSensorsOff();
        wearController.unregisterClassification(classification);
    }

    private void destroyExerciseRecognition() {
        wearController.disconnect(wearConnector);
    }

    private void handleResult(JSONObject json) {

        String gesture = json.optString("recognized");

        if (mActuallPage >= mPagerAdapter.getCount()) {
            stopExerciseRecognition();
            getFragmentManager().popBackStack();
            return;
        }

        String exercise = model.getExercises_traingsplan().get(mActuallPage)
                .getTitle_trainingsplan_listitem();

        if (gesture.contains(getString(R.string.exercise_recognition_curl))) {
            if (exercise.equals(getResources().getString(R.string.fragment_create_training_button_exercise_curls))) {
                updateViewText();
                sendMessage("repetition", null);
            }
        } else if (gesture.contains(getString(R.string.exercise_recognition_benchpress))) {
            if (exercise.equals(getResources().getString(R.string.fragment_create_training_button_exercise_benchpress))) {
                updateViewText();
                sendMessage("repetition", null);
            }
        } else if (gesture.contains(getString(R.string.exercise_recognition_butterfly))) {
            if (exercise.equals(getResources().getString(R.string.fragment_create_training_button_exercise_butterfly))) {
                updateViewText();
                sendMessage("repetition", null);
            }
        }
    }

    private void loadGestures() {
        ArrayList<InputStream> savedGesturesAsInputStream = GesturesDataUtils
                .getEnabledGesturesAsInputStream(getActivity());

        for (InputStream is : savedGesturesAsInputStream) {
            classification.loadGesture(is);
        }
    }

    private void sendMessage(String path, byte[] data) {
        Wearable.MessageApi.sendMessage(mGoogleApiClient, mNodeID, path, data)
                .setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
                    @Override
                    public void onResult(@NonNull MessageApi.SendMessageResult sendMessageResult) {

                    }
                });
    }

}
