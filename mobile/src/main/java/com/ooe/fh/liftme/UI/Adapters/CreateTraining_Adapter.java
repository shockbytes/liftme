package com.ooe.fh.liftme.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ooe.fh.liftme.Models.CreateTraining_Listitem_Model;
import com.ooe.fh.liftme.R;
import com.ooe.fh.liftme.UI.Layout.Elements.CreateTraining_Listitem_Holder;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Max on 04.11.2016.
 */

public class CreateTraining_Adapter extends RecyclerView.Adapter<CreateTraining_Listitem_Holder>
        implements View.OnDragListener {

    private List<CreateTraining_Listitem_Model> exerciseList;
    private Context mContext;

    public CreateTraining_Adapter(List<CreateTraining_Listitem_Model> exerciseList, Context context) {
        this.exerciseList = exerciseList;
        mContext = context;
    }

    @Override
    public CreateTraining_Listitem_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.createtraining_listview_listitem, parent, false);

        final CreateTraining_Listitem_Holder holder = new CreateTraining_Listitem_Holder(itemView);

        //drag listener which catches the event when sth. was dropped over an recycleview item
        itemView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                int position = holder.getModel().getpositionAddedToRecycleView();
                View view = (View) event.getLocalState();
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:

                        //an exercise was dropped to an item
                        if(view.getId() == R.id.btn_exercise1 || view.getId() == R.id.btn_exercise2 || view.getId() == R.id.btn_exercise3 || view.getId() == R.id.btn_exercise4) {

                            TextView name = (TextView) itemView.findViewById(R.id.txtview_exercise_name);
                            name.setText(((Button)view).getText().toString());
                            exerciseList.get(exerciseList.size()-position).setTitle_trainingsplan_listitem(((Button)view).getText().toString());
                            exerciseList.get(exerciseList.size()-position).setTitle_background_color(mContext.getResources().getColor(R.color.colorGreenExercise));
                            holder.getRlayout_exercise_background().setBackgroundColor(mContext.getResources().getColor(R.color.colorGreenExercise));
                        }
                        //a weight was dropped to an item
                        else if(view.getId() == R.id.btn_weight1 || view.getId() == R.id.btn_weight2 || view.getId() == R.id.btn_weight3 || view.getId() == R.id.btn_weight4) {
                            Log.e("currently Dragged", " Weight, Tag: "+position);

                            int weight = 0;
                            switch(((Button)view).getText().toString()){
                                case "10": weight=10; break;
                                case "20": weight=20; break;
                                case "30": weight=30; break;
                                case "60": weight=60; break;
                            }

                            TextView repetitions = (TextView) itemView.findViewById(R.id.txtview_exercise_repetition);
                            repetitions.setText("" + weight);
                            exerciseList.get(exerciseList.size()-position).setAmount_trainingsplan_listitem(weight);
                            exerciseList.get(exerciseList.size()-position).setAmount_background_color(mContext.getResources().getColor(R.color.colorGreenExercise));
                            holder.getRlayout_repetition_background().setBackgroundColor(mContext.getResources().getColor(R.color.colorGreenExercise));
                        }

                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        view.setVisibility(View.VISIBLE);
                        broadcastCustomIntent(exerciseList.get(exerciseList.size()-position), position);
                    default:
                        break;
                }
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(CreateTraining_Listitem_Holder holder, int position) {
        CreateTraining_Listitem_Model ci = exerciseList.get(position);
        holder.getTxtview_exercise_name().setText(ci.getTitle_trainingsplan_listitem());
        holder.getTxtview_exercise_repetition().setText(Integer.toString(ci.getAmount_trainingsplan_listitem()));
        holder.getRlayout_exercise_background().setBackgroundColor(ci.getTitle_background_color());
        holder.getRlayout_repetition_background().setBackgroundColor(ci.getAmount_background_color());
        holder.setModel(ci);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    /**
     * Method to send a broadcast with the action, that a item was dragged to a recycle view item
     * @param model
     * @param position
     */
    public void broadcastCustomIntent(CreateTraining_Listitem_Model model, int position) {
        Intent intent = new Intent();
        // add data to the Intent
        intent.putExtra("MODEL_TITLE", model.getTitle_trainingsplan_listitem());
        intent.putExtra("MODEL_WEIGHT", model.getAmount_trainingsplan_listitem());
        intent.putExtra("MODEL_TITLE_BACKGROUND", model.getTitle_background_color());
        intent.putExtra("MODEL_WEIGHT_BACKGROUND", model.getAmount_background_color());
        intent.putExtra("MODEL_POSITION", position);
        intent.setAction("ON_ITEM_DRAG_HAPPENED");
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }


    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        View tempView = (View) dragEvent.getLocalState();
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                tempView.setVisibility(View.VISIBLE);
            default:
                break;
        }
        return true;
    }
}
