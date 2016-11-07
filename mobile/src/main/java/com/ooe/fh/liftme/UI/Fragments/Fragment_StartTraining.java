package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.utils.Listeners;

import butterknife.ButterKnife;

/**
 * Created by Max on 05.11.2016.
 */

public class Fragment_StartTraining extends Global_Fragment implements Listeners.OnStartTrainingStartButtonClickListener{

    //Manager
    private FragmentManager mFragmentManager;

    public static Fragment_StartTraining newInstance(Context context) {
        Fragment_StartTraining f = new Fragment_StartTraining();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_starttraining, container, false);
        ButterKnife.bind(this, rootView);

        final Fragment_StartTraining_Base fragment = Fragment_StartTraining_Base.newInstance(getContext());
        addFragment(fragment, false);
        fragment.setButtonClickListener(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getView() != null) {
            Log.e("TestMessage----------","----------TestMessage");
        }
    }

    /**
     * Adds fragment to the flContent
     * @param fragment          Fragment to add
     * @param addToBackStack    True if the fragment should added to the backstack, otherwise false
     */
    private void addFragment(@NonNull Global_Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fLContent_starttraining, fragment, fragment.getFragmentName());
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getFragmentName());
        }
        transaction.commit();
    }

    @Override
    public void onStartTrainingStartButtonClick() {
        Fragment_StartTraining_Train fragment = Fragment_StartTraining_Train.newInstance(getContext());
        addFragment(fragment, true);
    }


}
