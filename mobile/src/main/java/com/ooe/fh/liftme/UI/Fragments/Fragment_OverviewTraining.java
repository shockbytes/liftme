package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class Fragment_OverviewTraining extends Global_Fragment implements Listeners.OnOverviewTrainingListItemClickListener, Listeners.OnOverviewDetailButtonCloseClickListener{

    //Manager
    private FragmentManager mFragmentManager;

    public static Fragment_OverviewTraining newInstance(Context context) {
        Fragment_OverviewTraining f = new Fragment_OverviewTraining();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overviewtraining, container, false);
        ButterKnife.bind(this, rootView);

        final Fragment_OverviewTraining_Base fragment = Fragment_OverviewTraining_Base.newInstance(getContext());
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

    /**
     * Adds fragment to the flContent
     * @param fragment          Fragment to add
     * @param addToBackStack    True if the fragment should added to the backstack, otherwise false
     */
    private void addFragment(@NonNull Global_Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fLContent, fragment, fragment.getFragmentName());
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getFragmentName());
        }
        transaction.commit();
    }

    //Listener functions
    @Override
    public void onOverviewTrainingListItemClick(OverviewTraining_Listitem_Model model) {
        Fragment_OverviewTraining_Detail fragment = Fragment_OverviewTraining_Detail.newInstance(getContext(), model);
        fragment.setButtonClickListener(this);
        addFragment(fragment, true);
    }

    @Override
    public void onOverviewDetailButtonCloseClick() {
        mFragmentManager.popBackStack();
    }
}
