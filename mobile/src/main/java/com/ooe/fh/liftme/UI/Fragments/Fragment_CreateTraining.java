package com.ooe.fh.liftme.UI.Fragments;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Activity.MainActivity;
import com.ooe.fh.liftme.UI.Adapters.CreateTraining_Adapter;
import com.ooe.fh.liftme.UI.Adapters.OverviewTraining_Adapter;
import com.ooe.fh.liftme.application.AppClass;
import com.ooe.fh.liftme.utils.Listeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Max on 04.11.2016.
 */

public class Fragment_CreateTraining extends Global_Fragment{



    @Bind(R.id.recycleview_createTrainingsplan)
    RecyclerView recycleview_createTrainingsplan;

    @Bind(R.id.btn_finish_createTrainingsplan)
    Button btn_finish_createTrainingsplan;

    @Bind(R.id.btn_ex_createTraingsplan)
    Button btn_ex_createTraingsplan;

    @Bind(R.id.btn_kg_createTraingsplan)
    Button btn_kg_createTraingsplan;

    @Bind(R.id.bottom_sheet_ex)
    View bottom_sheet_ex;

    @Bind(R.id.bottom_sheet_kg)
    View bottom_sheet_kg;

    @Bind(R.id.btn_addexercise_createTrainingsplan)
    Button btn_addexercise_createTrainingsplan;

    @Bind(R.id.btn_exercise1)
    Button btn_exercise_pushup;

    @Bind(R.id.btn_exercise2)
    Button btn_exercise_situp;

    @Bind(R.id.btn_exercise3)
    Button btn_exercise_burpes;

    @Bind(R.id.btn_exercise4)
    Button btn_exercise_pause;

    @Bind(R.id.btn_weight1)
    Button btn_weight_10;

    @Bind(R.id.btn_weight2)
    Button btn_weight_15;

    @Bind(R.id.btn_weight3)
    Button btn_weight_20;

    @Bind(R.id.btn_weight4)
    Button btn_weight_60;

    //Primitive types
    private int mSelectedColor;


    //Composite types
    private List<CreateTraining_Listitem_Model> mItemData;
    final ArrayList<Button> mColorButtons = new ArrayList<>();
    LinearLayoutManager llm;

    //UI-Elements
    private BottomSheetBehavior mBottomSheetBehaviorEX;
    private BottomSheetBehavior mBottomSheetBehaviorKG;

    //Adapters
    private CreateTraining_Adapter mAdapter;

    //Listener
    NestedItemTouchListener mNestedItemTouchListener = new NestedItemTouchListener();

    //BroadcastReceiver
    BroadcastReceiver mReceiver = null;

    public static Fragment_CreateTraining newInstance(Context context) {
        Fragment_CreateTraining f = new Fragment_CreateTraining();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemData = new ArrayList<CreateTraining_Listitem_Model>();
        //addFakeData();

        //register broadcast receiver
        if(mReceiver == null) {
            mReceiver = new OnDragHappenedBroadcastReceiver();
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(
                    mReceiver,
                    new IntentFilter(getResources().getString(R.string.intent_filter_drag_broadcast_receiver)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_training, container, false);
        ButterKnife.bind(this, rootView);
        mBottomSheetBehaviorEX = BottomSheetBehavior.from(bottom_sheet_ex);
        mBottomSheetBehaviorKG = BottomSheetBehavior.from(bottom_sheet_kg);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recycleview_createTrainingsplan.setHasFixedSize(true);
        llm = new LinearLayoutManager(getContext());
        llm.setReverseLayout(false);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview_createTrainingsplan.setLayoutManager(llm);
        mAdapter = new CreateTraining_Adapter(mItemData, getContext());
        recycleview_createTrainingsplan.setAdapter(mAdapter);

        btn_finish_createTrainingsplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allItemsComplete() && mItemData.size() != 0) {
                    finishNamingDialog();
                }else{
                    defaultFinishDialog();
                }
            }
        });

        btn_ex_createTraingsplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehaviorEX.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_kg_createTraingsplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehaviorKG.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        btn_addexercise_createTrainingsplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int color = getContext().getResources().getColor(R.color.colorRedExercise);
                mItemData.add(0,new CreateTraining_Listitem_Model(getResources().getString(R.string.exercise_list_model_example_text) + mItemData.size(), 0, color, color, mItemData.size()+1));
                mAdapter.notifyItemInserted(0);
                //llm.scrollToPositionWithOffset(0,0);
                recycleview_createTrainingsplan.invalidate();
     }
        });


        btn_exercise_pushup.setOnLongClickListener(mNestedItemTouchListener);
        btn_exercise_situp.setOnLongClickListener(mNestedItemTouchListener);
        btn_exercise_burpes.setOnLongClickListener(mNestedItemTouchListener);
        btn_exercise_pause.setOnLongClickListener(mNestedItemTouchListener);


        btn_weight_10.setOnLongClickListener(mNestedItemTouchListener);
        btn_weight_15.setOnLongClickListener(mNestedItemTouchListener);
        btn_weight_20.setOnLongClickListener(mNestedItemTouchListener);
        btn_weight_60.setOnLongClickListener(mNestedItemTouchListener);

        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        //register the broadcast receiver
        if(mReceiver == null) {
            mReceiver = new OnDragHappenedBroadcastReceiver();
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(
                    mReceiver,
                    new IntentFilter(getResources().getString(R.string.intent_filter_drag_broadcast_receiver)));
        }
    }

    @Override
    public void onDestroy() {

        //unregister the broadcast receiver
        if (mReceiver != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        super.onDestroy();
    }


    /**
     * Shows Naming-Dialog, if the user finished creating trainingsplan
     */
    private void finishNamingDialog() {
        mColorButtons.clear();
        mSelectedColor = getResources().getColor(R.color.colorBlack);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_finish_createtraingsplan, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final Button btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel_alert_finish);
        final EditText etxt_name = (EditText) dialogView.findViewById(R.id.edittxt_trainingsname_alert_finish);

        mColorButtons.add((Button) dialogView.findViewById(R.id.btn_black_alert_finish));
        mColorButtons.add((Button) dialogView.findViewById(R.id.btn_red_alert_finish));
        mColorButtons.add((Button) dialogView.findViewById(R.id.btn_green_alert_finish));
        mColorButtons.add((Button) dialogView.findViewById(R.id.btn_blue_alert_finish));
        mColorButtons.add((Button) dialogView.findViewById(R.id.btn_yellow_alert_finish));

        mColorButtons.get(0).setBackgroundResource(R.drawable.design_colorbutton_border_black);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        mColorButtons.get(0).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetHighlight();
                mColorButtons.get(0).setBackgroundResource(R.drawable.design_colorbutton_border_black);
                mSelectedColor = getResources().getColor(R.color.colorBlack);
            }
        });
        mColorButtons.get(1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetHighlight();
                mColorButtons.get(1).setBackgroundResource(R.drawable.design_colorbutton_border_red);
                mSelectedColor = getResources().getColor(R.color.colorRed);
            }
        });
        mColorButtons.get(2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetHighlight();
                mColorButtons.get(2).setBackgroundResource(R.drawable.design_colorbutton_border_green);
                mSelectedColor = getResources().getColor(R.color.colorGreen);
            }
        });
        mColorButtons.get(3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetHighlight();
                mColorButtons.get(3).setBackgroundResource(R.drawable.design_colorbutton_border_blue);
                mSelectedColor = getResources().getColor(R.color.colorBlue);
            }
        });
        mColorButtons.get(4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetHighlight();
                mColorButtons.get(4).setBackgroundResource(R.drawable.design_colorbutton_border_yellow);
                mSelectedColor = getResources().getColor(R.color.colorYellow);
            }
        });

        final Button done = (Button) dialogView.findViewById(R.id.btn_done_alert_finish);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Name restrictions at least 3 letters and maximal 9 letters
                if(etxt_name.getText().toString().length() > 3 && etxt_name.getText().toString().length() < 10){
                    AppClass.overviewTraining_Listitem_models.add(new OverviewTraining_Listitem_Model(mSelectedColor,etxt_name.getText().toString(),new ArrayList(mItemData)));
                    mItemData.clear();
                    ((MainActivity)getActivity()).getmViewPager().setCurrentItem(1);
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.copyToRealm(new OverviewTraining_Listitem_Model(mSelectedColor,etxt_name.getText().toString(),mItemData));
                    realm.commitTransaction();
                    alertDialog.cancel();
                }else{
                    etxt_name.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });
    }

    /**
     * Shows DefaultFinish-Dialog, if the user finished creating trainingsplan
     */
    private void defaultFinishDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_defaultfinish_createtrainingsplan, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final Button btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel_alert_createtraining);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }

    /**
     * Resets highlighting from colorbuttons
     */
    private void resetHighlight(){
        mColorButtons.get(0).setBackgroundColor(getResources().getColor(R.color.colorBlack));
        mColorButtons.get(1).setBackgroundColor(getResources().getColor(R.color.colorRed));
        mColorButtons.get(2).setBackgroundColor(getResources().getColor(R.color.colorGreen));
        mColorButtons.get(3).setBackgroundColor(getResources().getColor(R.color.colorBlue));
        mColorButtons.get(4).setBackgroundColor(getResources().getColor(R.color.colorYellow));
    }

    /**
     * Broadcast receiver which listens if an item was dropped on an recycleview item
     */
    public class OnDragHappenedBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction() == getResources().getString(R.string.intent_filter_drag_broadcast_receiver)){
                String title = intent.getStringExtra(getResources().getString(R.string.intent_extra_title));
                int repetitions = intent.getIntExtra(getResources().getString(R.string.intent_extra_repetitions), -1);
                int positon = intent.getIntExtra(getResources().getString(R.string.intent_extra_position), -1);
                int title_background = intent.getIntExtra(getResources().getString(R.string.intent_extra_background_title), -1);
                int weight_background = intent.getIntExtra(getResources().getString(R.string.intent_extra_background_repetitions), -1);
                mItemData.get(mItemData.size()-positon).setAmount_trainingsplan_listitem(repetitions);
                mItemData.get(mItemData.size()-positon).setTitle_trainingsplan_listitem(title);
                mItemData.get(mItemData.size()-positon).setTitle_background_color(title_background);
                mItemData.get(mItemData.size()-positon).setAmount_background_color(weight_background);

                if(!mItemData.get(mItemData.size()-positon).getTitle_trainingsplan_listitem().equals(getResources().getString(R.string.exercise_list_model_example_text)) &&
                        mItemData.get(mItemData.size()-positon).getAmount_trainingsplan_listitem() != 0
                        ){
                    mItemData.get(mItemData.size()-positon).setCompleteModel(true);
                }
            }

        }
    }

    /**
     * Longclick listener for draggind an item
     */
    private class NestedItemTouchListener implements View.OnLongClickListener {

        public boolean onLongClick(View view) {
            if(mItemData.size() != 0) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
            return false;
        }
    }

    /**
     * Checks if all items are complete
     * @return True if all items are complete, otherwise false
     */
    private boolean allItemsComplete(){
        for(int i = 0; i < mItemData.size(); i++){
            if(!mItemData.get(i).isCompleteModel()){
                return false;
            }
        }
        return true;
    }

}