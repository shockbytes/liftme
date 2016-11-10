package com.ooe.fh.liftme.utils;

import com.google.android.gms.wearable.MessageEvent;
import com.ooe.fh.liftme.Models.OverviewTraining_Listitem_Model;

/**
 * Created by Max on 05.11.2016.
 */

public class Listeners {

    public interface OnOverviewTrainingListItemClickListener{
        void onOverviewTrainingListItemClick(OverviewTraining_Listitem_Model model);
    }

    public interface OnOverviewDetailButtonCloseClickListener{
        void onOverviewDetailButtonCloseClick();
    }

    public interface OnStartTrainingStartButtonClickListener{
        void onStartTrainingStartButtonClick();
    }

    public interface OnRecycleViewListItemClickListener {
        void onOverviewTrainingListItemClick(OverviewTraining_Listitem_Model model);
    }

    public interface OnMessageReceivedListener {
        void onMessageReceived(MessageEvent event);
    }

}
