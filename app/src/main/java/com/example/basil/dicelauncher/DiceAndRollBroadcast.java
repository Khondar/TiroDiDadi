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

        public static String ACTION_ROLL_DICE = "com.package.ACTION_ROLL_DICE";
    }

    public static interface Extras {
        public static String BUTTOM_TAG = "com.package.BUTTOM_TAG";
            }

    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        if(action.equals(Action.ACTION_ROLL_DICE)){
            String tag = intent.getStringExtra(Extras.BUTTOM_TAG);
            diceAndRoll(tag);
        }

    }


    public void diceAndRoll(String tag){

    }

}
