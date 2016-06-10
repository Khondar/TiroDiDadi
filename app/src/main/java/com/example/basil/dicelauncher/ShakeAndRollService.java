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

    private MediaPlayer mPlayer;

    public static final String ROLL = "roll";
    public static final String NULLA = "nulla";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        //mPlayer = MediaPlayer.create(this, R.raw.sei_un_mito);
        mPlayer = new MediaPlayer();
        mPlayer = MediaPlayer.create(this, R.raw.shakeandrolldice);
        Context context = ShakeAndRollService.this.getApplicationContext();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String azione = intent.getAction();

        switch (azione) {
            case ROLL:
              //  mPlayer.reset();
                mPlayer.start();
                break;
        }


            return START_STICKY;
    }

    }
