package com.example.basil.dicelauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

/**
 * Created by basil on 08/06/2016.
 */
public class DiceAndRollBroadcast extends BroadcastReceiver {

    public static interface  Action {

        public static String ACTION_ROLL_DICE = "com.DiceAndRollBroadcast.ACTION_ROLL_DICE";
    }

    public static interface Extras {
        public static String BUTTOM_TAG = "com.DiceAndRollBroadcast.BUTTOM_TAG";
        public static String MESSAGE_TAG = "com.DiceAndRollBroadcast.MESSAGE_TAG";
            }

    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        if(action.equals(Action.ACTION_ROLL_DICE)){
            String tag = intent.getStringExtra(Extras.BUTTOM_TAG);
            String message = intent.getStringExtra(Extras.MESSAGE_TAG);
            diceAndRoll(tag, message);
        }

    }


    public void diceAndRoll(String tag, String message){

    }



}
