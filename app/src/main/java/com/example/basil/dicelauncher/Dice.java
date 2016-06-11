package com.example.basil.dicelauncher;

import android.content.Context;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by basil on 07/06/2016.
 */
public class Dice implements IRollable {

    int facce=0;

    @Override
    public int rollable (int nFacce){

        return (int)( Math.random()*nFacce )+ 1;
    }

    public int rollable(Context context){
        if (facce != 0){

            return (int)( Math.random()*facce )+ 1;
        }else{
            Toast.makeText(context, "Il dado non ha facce", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public void setFacce(int facce) {
        this.facce = facce;
    }

    public int getFacce() {
        return facce;
    }
}
