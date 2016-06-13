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
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SelezioneDatiFragment dadi;
    MenuFragment menu;
    PlayerNameFragment nomeFragment;
    SelectDicePlayerFragment sceltaPlayer;

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

        Intent intent = getIntent();
        String name= intent.getStringExtra("name");

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcast, filter);

        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(PlayerSetBroadcast.Action.ACTION_PLAYER);

        LocalBroadcastManager.getInstance(this).registerReceiver(playerBroadcast, filter2);

        startService((new Intent(this, ShakeAndRollService.class)).setAction(ShakeAndRollService.NULLA));
        super.onStart();
    }


    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcast);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(playerBroadcast);
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
            String message= "no";
            switch (tag) {
                case MenuFragment.ROLL:
                    action = SelezioneDatiFragment.ROLL;
                    serviceIntent.setAction(ShakeAndRollService.ROLL);
                    break;
                case MenuFragment.SAVE:
                    action = SelezioneDatiFragment.SAVE;
                    nomeFragment = new PlayerNameFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, nomeFragment, "nomeEditor").addToBackStack(null).commit();
                    serviceIntent.setAction(ShakeAndRollService.SAVE);
                    break;
                case MenuFragment.LOAD:
                    action = SelezioneDatiFragment.LOAD;
                    serviceIntent.setAction(ShakeAndRollService.LOAD);
                    sceltaPlayer = new SelectDicePlayerFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, sceltaPlayer, "sceltaPlayer").addToBackStack(null).commit();
                    break;
                case SelezioneDatiFragment.SOUND:
                    action = SelezioneDatiFragment.NULLA;
                    serviceIntent.setAction(ShakeAndRollService.ROLL);
                    break;
                case SelezioneDatiFragment.NATURAL20:
                    action = SelezioneDatiFragment.NULLA;
                    serviceIntent.setAction(ShakeAndRollService.NATURAL20);
                    break;
                default:
                    action = SelezioneDatiFragment.NAME;
                    serviceIntent.setAction(ShakeAndRollService.NOME);
                    message = tag;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
                    break;
            }
            getBaseContext().startService(serviceIntent);

            SelezioneDatiFragment selezioneDatiFragment = (SelezioneDatiFragment) getSupportFragmentManager().findFragmentByTag("dadi");
            selezioneDatiFragment.diceAndRoll(action, message);

        }


    };

    private PlayerSetBroadcast playerBroadcast = new PlayerSetBroadcast(){
        @Override
        public void selectPlayer(String tag, String number){
            String action = "default";
            String message = number;
            switch (tag){
                case SacchettaAdatper.LOAD:
                    action = SelectDicePlayerFragment.LOAD;
                    SelezioneDatiFragment selezioneDatiFragment = (SelezioneDatiFragment) getSupportFragmentManager().findFragmentByTag("dadi");
                    selezioneDatiFragment.diceAndRoll(action, message);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();
                    break;
                case SacchettaAdatper.DELETE:
                    action = SelectDicePlayerFragment.DELETE;
                    SelectDicePlayerFragment selezionaPersonaggi = (SelectDicePlayerFragment) getSupportFragmentManager().findFragmentByTag("sceltaPlayer");
                    selezionaPersonaggi.cancellaCarica(action, message);
                    break;
            }
            //SelectDicePlayerFragment selezioneDatiFragment = (SelectDicePlayerFragment) getSupportFragmentManager().findFragmentByTag("sceltaPlayer");
            //selezioneDatiFragment.cancellaCarica(action, message);
        }
    };
}


