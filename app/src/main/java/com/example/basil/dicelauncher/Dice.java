package com.example.basil.dicelauncher;

import android.content.Context;
import android.widget.Toast;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by basil on 07/06/2016.
 */

@DatabaseTable (tableName = "dadi")
public class Dice implements IRollable {

    @DatabaseField(id = true)
    int id;

    @DatabaseField
    int facce;

    @DatabaseField(foreign = true, canBeNull = false)
    Sacchetta sacchetta;

    @Override
    public int rollable (int nFacce){

        return (int)( Math.random()*nFacce )+ 1;
    }

    Dice (){}

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
