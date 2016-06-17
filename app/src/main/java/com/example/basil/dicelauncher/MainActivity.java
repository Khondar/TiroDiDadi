package com.example.basil.dicelauncher;


import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    SelezioneDatiFragment dadi;
    MenuFragment menu;
    PlayerNameFragment nomeFragment;
    SelectDicePlayerFragment sceltaPlayer;
    BackFragment backFragment;
    SharedPreferences sharP;
    final static private String SHARP = "com.MainActivity.Sharp";
    final static private String PREFERENCE = "com.MainActivity.Preference";
    int variabile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            sharP = getBaseContext().getSharedPreferences(PREFERENCE, 0);
            variabile = sharP.getInt(SHARP, 0);
            switch (variabile){
                case 1:
                    dadi = (SelezioneDatiFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent1");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();

                    menu = (MenuFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent2");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
                    nomeFragment = new PlayerNameFragment();
                    sceltaPlayer = new SelectDicePlayerFragment();
                    backFragment = new BackFragment();
                    break;
                case 2:
                    dadi = (SelezioneDatiFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent1");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();

                    nomeFragment = (PlayerNameFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent3");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, nomeFragment, "nomeEditor").addToBackStack(null).commit();
                    menu = new MenuFragment();
                    sceltaPlayer = new SelectDicePlayerFragment();
                    backFragment = new BackFragment();
                    break;
                case 3:
                    sceltaPlayer = (SelectDicePlayerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "mContent4");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, sceltaPlayer, "sceltaPlayer").addToBackStack(null).commit();

                    backFragment = (BackFragment) getSupportFragmentManager().getFragment(savedInstanceState, "mContent5");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, backFragment, "backFragment").addToBackStack(null).commit();
                    nomeFragment = new PlayerNameFragment();
                    dadi = new SelezioneDatiFragment();
                    menu = new MenuFragment();
                    break;
                default:
                    dadi = (SelezioneDatiFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent1");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();

                    menu = (MenuFragment)getSupportFragmentManager().getFragment(savedInstanceState, "mContent2");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
                    nomeFragment = new PlayerNameFragment();
                    sceltaPlayer = new SelectDicePlayerFragment();
                    backFragment = new BackFragment();
                    break;
            }
        } else {
            dadi = new SelezioneDatiFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();
            menu = new MenuFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
            nomeFragment = new PlayerNameFragment();
            sceltaPlayer = new SelectDicePlayerFragment();
            backFragment = new BackFragment();
            sharP = getBaseContext().getSharedPreferences(PREFERENCE, 0);
            SharedPreferences.Editor edit = sharP.edit();
            edit.putInt(SHARP, 0).commit();
        }

        getSupportActionBar().hide();

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcast, filter);
        Log.d("Main Activity", "onStart");
        startService((new Intent(this, ShakeAndRollService.class)).setAction(ShakeAndRollService.NULLA));
        super.onStart();
    }


    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcast);
        stopService(new Intent(getBaseContext(), ShakeAndRollService.class));
        Log.d("Main Activity", "onStop");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        sharP = getBaseContext().getSharedPreferences(PREFERENCE, 0);
        variabile = sharP.getInt(SHARP, 0);
        switch (variabile){
            case 1:
                getSupportFragmentManager().putFragment(outState,"mContent1", dadi);
                getSupportFragmentManager().putFragment(outState,"mContent2", menu);
                break;
            case 2:
                getSupportFragmentManager().putFragment(outState, "mContent3", nomeFragment);
                getSupportFragmentManager().putFragment(outState,"mContent1", dadi);
                break;
            case 3:
                getSupportFragmentManager().putFragment(outState, "mContent4", sceltaPlayer);
                getSupportFragmentManager().putFragment(outState, "mContent5", backFragment);
                break;
            default:
                getSupportFragmentManager().putFragment(outState,"mContent1", dadi);
                getSupportFragmentManager().putFragment(outState,"mContent2", menu);
                break;
        }
    }

    private DiceAndRollBroadcast broadcast = new DiceAndRollBroadcast() {
        @Override
        public void diceAndRoll(String tag, String message) {
            Intent serviceIntent = new Intent(MainActivity.this, ShakeAndRollService.class);
            String action = "no";
            String id = message;
            sharP = getBaseContext().getSharedPreferences(PREFERENCE, 0);
            SharedPreferences.Editor edit = sharP.edit();
            switch (tag) {
                case MenuFragment.ROLL:
                    serviceIntent.setAction(ShakeAndRollService.ROLL);
                    comandaIlService(serviceIntent);
                    comandaIDadi().rollDice();
                    edit.putInt(SHARP, 1).commit();
                    break;
                case MenuFragment.SAVE:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, nomeFragment, "nomeEditor").addToBackStack(null).commit();
                    serviceIntent.setAction(ShakeAndRollService.SAVE);
                    comandaIlService(serviceIntent);
                    comandaIDadi().saveDice();
                    edit.putInt(SHARP, 2).commit();
                    break;
                case MenuFragment.LOAD:
                    serviceIntent.setAction(ShakeAndRollService.LOAD);
                    comandaIlService(serviceIntent);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, sceltaPlayer, "sceltaPlayer").addToBackStack(null).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, backFragment, "backFragment").addToBackStack(null).commit();
                    edit.putInt(SHARP, 3).commit();
                    break;


                case SelezioneDatiFragment.SOUND:
                    serviceIntent.setAction(ShakeAndRollService.ROLL);
                    comandaIlService(serviceIntent);
                    break;
                case SelezioneDatiFragment.NATURAL20:
                    serviceIntent.setAction(ShakeAndRollService.NATURAL20);
                    comandaIlService(serviceIntent);
                    break;
                case SelezioneDatiFragment.FAIL:
                    serviceIntent.setAction(ShakeAndRollService.FAIL);
                    comandaIlService(serviceIntent);
                    break;


                case PlayerNameFragment.NAME:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
                    comandaIDadi().insertName(id);
                    serviceIntent.setAction(ShakeAndRollService.SAVE);
                    comandaIlService(serviceIntent);
                    edit.putInt(SHARP, 1).commit();
                    break;

                case SacchettaAdatper.LOAD:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();

                    serviceIntent.setAction(ShakeAndRollService.LOAD);
                    comandaIlService(serviceIntent);
                    comandaIDadi().loadDice(id);
                    edit.putInt(SHARP, 1).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();
                    break;
                case SacchettaAdatper.DELETE:
                    SelectDicePlayerFragment selezionaPersonaggi = (SelectDicePlayerFragment) getSupportFragmentManager().findFragmentByTag("sceltaPlayer");
                    selezionaPersonaggi.cancella(id);
                    getSupportFragmentManager().beginTransaction().detach(getSupportFragmentManager().findFragmentByTag("sceltaPlayer")).attach(getSupportFragmentManager().findFragmentByTag("sceltaPlayer")).commit();
                    break;
                case BackFragment.RETURN_BACK:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
                    edit.putInt(SHARP, 1).commit();
                    break;
                case PlayerNameFragment.BACK:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMenu, menu, "menu").addToBackStack(null).commit();
                    edit.putInt(SHARP, 1).commit();
                    break;
                default:
                    break;
            }
        }
    };

    private SelezioneDatiFragment comandaIDadi(){
        return (SelezioneDatiFragment) getSupportFragmentManager().findFragmentByTag("dadi");
    }

    private void comandaIlService(Intent intent){
        getBaseContext().startService(intent);
    }
}


