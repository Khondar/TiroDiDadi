package com.example.basil.dicelauncher;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SelezioneDatiFragment dadi;
    MenuFragment menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            //Ripristina l'istanza del fragment
            dadi = (SelezioneDatiFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent1");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();

            menu = (MenuFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent2");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
        }else{
            dadi = new SelezioneDatiFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();
            menu = new MenuFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
        }

        getSupportActionBar().hide();



    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcast, filter);
        startService((new Intent(this, ShakeAndRollService.class)).setAction(ShakeAndRollService.NULLA));
        super.onStart();
    }


    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcast);
        stopService(new Intent(getBaseContext(), ShakeAndRollService.class));
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState,"mContent1", dadi);
        getSupportFragmentManager().putFragment(outState,"mContent2", menu);
    }

    private DiceAndRollBroadcast broadcast = new DiceAndRollBroadcast() {
        @Override
        public void diceAndRoll(String tag) {
            Intent serviceIntent = new Intent(MainActivity.this, ShakeAndRollService.class);
            String action = "no";
            switch (tag) {
                case MenuFragment.ROLL:
                    action = SelezioneDatiFragment.ROLL;
                    serviceIntent.setAction(ShakeAndRollService.ROLL);
                    break;
                case MenuFragment.SAVE:
                    action = SelezioneDatiFragment.SAVE;
                    serviceIntent.setAction(ShakeAndRollService.SAVE);
                    break;
                case MenuFragment.LOAD:
                    action = SelezioneDatiFragment.LOAD;
                    serviceIntent.setAction(ShakeAndRollService.LOAD);
                    break;
                case SelezioneDatiFragment.SOUND:
                    action = SelezioneDatiFragment.NULLA;
                    serviceIntent.setAction(ShakeAndRollService.ROLL);
                    break;
            }
            getBaseContext().startService(serviceIntent);
            SelezioneDatiFragment selezioneDatiFragment = (SelezioneDatiFragment) getSupportFragmentManager().findFragmentByTag("dadi");
            selezioneDatiFragment.diceAndRoll(action);


        }
    };
}


