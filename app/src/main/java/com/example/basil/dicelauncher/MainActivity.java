package com.example.basil.dicelauncher;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuFragment menu = new MenuFragment();
        SelezioneDatiFragment dadi = new SelezioneDatiFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, menu).addToBackStack(null).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcast, filter);
        super.onStart();
    }


    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcast);
        super.onStop();
    }

    private DiceAndRollBroadcast broadcast = new DiceAndRollBroadcast() {
        @Override
        public void diceAndRoll(String tag) {
            String action = "no";
            switch (tag) {
                case MenuFragment.ROLL:
                    action = SelezioneDatiFragment.ROLL;
                    break;
                case MenuFragment.SAVE:
                    action = SelezioneDatiFragment.SAVE;
                    break;
                case MenuFragment.LOAD:
                    action = SelezioneDatiFragment.LOAD;
                    break;
            }

            SelezioneDatiFragment selezioneDatiFragment = (SelezioneDatiFragment) getSupportFragmentManager().findFragmentByTag("dadi");
            selezioneDatiFragment.diceAndRoll(action);


        }
    };
}


