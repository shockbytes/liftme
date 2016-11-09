package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by Max on 04.11.2016.
 */

public class Fragment_CreateTraining extends Global_Fragment {

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

    public static Fragment_CreateTraining newInstance(Context context) {
        Fragment_CreateTraining f = new Fragment_CreateTraining();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemData = new ArrayList<CreateTraining_Listitem_Model>();
        //addFakeData();
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
        mAdapter = new CreateTraining_Adapter(mItemData);
        recycleview_createTrainingsplan.setAdapter(mAdapter);

        btn_finish_createTrainingsplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishNamingDialog();
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
                mItemData.add(0,new CreateTraining_Listitem_Model("Drag & Drop " + mItemData.size(), 0));
                mAdapter.notifyItemInserted(0);
                llm.scrollToPositionWithOffset(0,0);
            }
        });

        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Add fake data to listview
     */
    private void addFakeData() {
        CreateTraining_Listitem_Model head1 = new CreateTraining_Listitem_Model("Exercise X", 0);
        CreateTraining_Listitem_Model head2 = new CreateTraining_Listitem_Model("Exercise X", 0);
        CreateTraining_Listitem_Model head3 = new CreateTraining_Listitem_Model("Exercise X", 0);
        CreateTraining_Listitem_Model head4 = new CreateTraining_Listitem_Model("Exercise X", 0);

        mItemData.add(head1);
        mItemData.add(head2);
        mItemData.add(head3);
        mItemData.add(head4);
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
                    AppClass.overviewTraining_Listitem_models.add(new OverviewTraining_Listitem_Model(mSelectedColor,etxt_name.getText().toString(),mItemData));
                    ((MainActivity)getActivity()).getmViewPager().setCurrentItem(1);
                    alertDialog.cancel();
                }else{
                    etxt_name.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
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
}