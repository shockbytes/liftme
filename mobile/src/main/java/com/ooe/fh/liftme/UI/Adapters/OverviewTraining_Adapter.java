package com.ooe.fh.liftme.UI.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.UI.Layout.Elements.OverviewTraining_Listitem_Holder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 05.11.2016.
 */

public class OverviewTraining_Adapter extends GlobalList_Adapter<OverviewTraining_Listitem_Model>  {

    private Context mContext;

    //List of items to show
    private List<OverviewTraining_Listitem_Model> mListItems = new ArrayList<>();


    public OverviewTraining_Adapter(Context context, List<OverviewTraining_Listitem_Model> objects) {
        super(context, objects);
        mContext = context;
        mListItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OverviewTraining_Listitem_Holder listItem = (OverviewTraining_Listitem_Holder) convertView;

        if (listItem == null) {
            listItem = new OverviewTraining_Listitem_Holder(mContext);
        }

        OverviewTraining_Listitem_Model completeTrainingsplanListItemModel = (OverviewTraining_Listitem_Model)getItem(position);
        listItem.setData(completeTrainingsplanListItemModel);

        return listItem;
    }
}
