package com.example.basil.dicelauncher;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by basil on 10/06/2016.
 */
public class ShakeAndRollService extends Service {

    private MediaPlayer mPlayerRoll;
    private MediaPlayer mPlayerNatural;
    private MediaPlayer mPlayerLoad;
    private MediaPlayer mPlayerSave;

    public static final String ROLL = "roll";
    public static final String NULLA = "nulla";
    public static final String SAVE = "save";
    public static final String LOAD = "load";
    public static final String NATURAL20 = "natural20";
    public static final String NOME = "nome";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        mPlayerRoll = new MediaPlayer();
        mPlayerRoll = MediaPlayer.create(this, R.raw.shakeandrolldice);
        mPlayerNatural = new MediaPlayer();
        mPlayerNatural = MediaPlayer.create(this, R.raw.colpito);
        mPlayerLoad = new MediaPlayer();
        mPlayerLoad = MediaPlayer.create(this, R.raw.magicload);
        mPlayerSave = new MediaPlayer();
        mPlayerSave = MediaPlayer.create(this, R.raw.magicsave);
        Context context = ShakeAndRollService.this.getApplicationContext();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String azione = intent.getAction();

        switch (azione) {
            case ROLL:
                mPlayerRoll.start();
                break;
            case SAVE:
                mPlayerSave.start();
                break;
            case LOAD:
                mPlayerLoad.start();
                break;
            case NATURAL20:
                mPlayerNatural.start();
                break;
            default:
                break;
        }


            return START_STICKY;
    }

    }
