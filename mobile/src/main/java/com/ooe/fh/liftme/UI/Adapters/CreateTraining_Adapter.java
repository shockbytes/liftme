package com.ooe.fh.liftme.UI.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.UI.Layout.Elements.CreateTraining_Listitem_Holder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 04.11.2016.
 */

public class CreateTraining_Adapter extends GlobalList_Adapter<CreateTraining_Listitem_Model>  {

    private Context mContext;

    //List of items to show
    private List<CreateTraining_Listitem_Model> mListItems = new ArrayList<>();

    public CreateTraining_Adapter(Context context, List<CreateTraining_Listitem_Model> objects) {
        super(context, objects);
        mContext = context;
        mListItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CreateTraining_Listitem_Holder listItem = (CreateTraining_Listitem_Holder) convertView;

        if (listItem == null) {
            listItem = new CreateTraining_Listitem_Holder(mContext);
        }

        CreateTraining_Listitem_Model createTrainingsplanListItemModel = (CreateTraining_Listitem_Model)getItem(position);
        listItem.setData(createTrainingsplanListItemModel);

        return listItem;
    }
}

