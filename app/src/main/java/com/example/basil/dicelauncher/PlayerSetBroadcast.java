package com.example.basil.dicelauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by basil on 13/06/2016.
 */
public class PlayerSetBroadcast extends BroadcastReceiver {
    public static interface  Action {
        public static String ACTION_PLAYER = "com.package.ACTION_PLAYER";
    }

    public static interface Extras {
        public static String CARD_TAG = "com.package.BUTTOM_TAG";
        public static String CARD_COMMAND = "42";
    }

    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        if(action.equals(Action.ACTION_PLAYER)){
            String number = intent.getStringExtra(Extras.CARD_TAG);
            String tag = intent.getStringExtra(Extras.CARD_COMMAND);
            selectPlayer(tag, number);
        }

    }

    public void selectPlayer(String tag, String number){

    }
}
