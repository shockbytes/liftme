package com.ooe.fh.liftme.UI.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ooe.fh.liftme.Models.AlertSelectTraining_Listitem_Model;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;
import com.ooe.fh.liftme.UI.Layout.Elements.AlertSelectTraining_Listitem_Holder;
import com.ooe.fh.liftme.UI.Layout.Elements.OverviewTraining_Listitem_Holder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 08.11.2016.
 */

public class AlertSelectTraining_Adapter extends GlobalList_Adapter<AlertSelectTraining_Listitem_Model>  {

    private Context mContext;

    //List of items to show
    private List<AlertSelectTraining_Listitem_Model> mListItems = new ArrayList<>();


    public AlertSelectTraining_Adapter(Context context, List<AlertSelectTraining_Listitem_Model> objects) {
        super(context, objects);
        mContext = context;
        mListItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlertSelectTraining_Listitem_Holder listItem = (AlertSelectTraining_Listitem_Holder) convertView;

        if (listItem == null) {
            listItem = new AlertSelectTraining_Listitem_Holder(mContext);
        }

        AlertSelectTraining_Listitem_Model selectTrainingListItemModel = (AlertSelectTraining_Listitem_Model)getItem(position);
        listItem.setData(selectTrainingListItemModel);

        return listItem;
    }
}
