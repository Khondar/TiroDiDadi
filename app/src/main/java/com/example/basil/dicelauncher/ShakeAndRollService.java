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
    private MediaPlayer mPlayerFail;

    public static final String ROLL = "com.ShakeAndRollService.roll";
    public static final String NULLA = "com.ShakeAndRollService.nulla";
    public static final String SAVE = "com.ShakeAndRollService.save";
    public static final String LOAD = "com.ShakeAndRollService.load";
    public static final String NATURAL20 = "com.ShakeAndRollService.natural20";
    public static final String NOME = "com.ShakeAndRollService.nome";
    public static final String FAIL = "com.ShakeAndRollService.fail";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        mPlayerRoll = new MediaPlayer();

        mPlayerNatural = new MediaPlayer();

        mPlayerLoad = new MediaPlayer();

        mPlayerSave = new MediaPlayer();

        mPlayerFail = new MediaPlayer();

        Context context = ShakeAndRollService.this.getApplicationContext();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String azione = intent.getAction();

        switch (azione) {
            case ROLL:
                mPlayerRoll.reset();
                mPlayerRoll = MediaPlayer.create(this, R.raw.shakeandrolldice);
                mPlayerRoll.start();
                break;
            case SAVE:
                mPlayerSave.reset();
                mPlayerSave = MediaPlayer.create(this, R.raw.magicsave);
                mPlayerSave.start();
                break;
            case LOAD:
                mPlayerLoad.reset();
                mPlayerLoad = MediaPlayer.create(this, R.raw.magicload);
                mPlayerLoad.start();
                break;
            case NATURAL20:
                mPlayerNatural.reset();
                mPlayerNatural = MediaPlayer.create(this, R.raw.colpito);
                mPlayerNatural.start();
                break;
            case FAIL:
                mPlayerFail.reset();
                mPlayerFail = MediaPlayer.create(this, R.raw.fail);
                mPlayerFail.start();
            default:
                break;
        }


            return START_STICKY;
    }

    }
