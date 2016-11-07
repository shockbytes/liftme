package com.ooe.fh.liftme.UI.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nhaarman.listviewanimations.ArrayAdapter;

import java.util.List;

import static java.util.Collections.addAll;

/**
 * Created by m.altenhuber on 2/22/2016.
 */
public abstract class GlobalList_Adapter<S> extends ArrayAdapter<S> {

    public final Context mContext;

    public GlobalList_Adapter(final Context context, List<S> itemModels) {
        mContext = context;
        addAll(itemModels);
    }

    @NonNull
    @Override
    public List<S> getItems() {
        return super.getItems();
    }
}

