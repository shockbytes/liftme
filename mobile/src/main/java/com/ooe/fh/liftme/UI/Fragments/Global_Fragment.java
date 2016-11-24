package com.ooe.fh.liftme.UI.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;


public abstract class Global_Fragment extends Fragment {

//    public abstract String setFragmentName();
    private String FRAGMENT_NAME;

    @Override
    public void onAttach(Context context) {
        FRAGMENT_NAME = this.getClass().getSimpleName();
        super.onAttach(context);
    }

    public String getFragmentName(){
        return FRAGMENT_NAME;
    }
}
