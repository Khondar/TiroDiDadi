package com.example.basil.dicelauncher;

import java.util.Random;

/**
 * Created by basil on 07/06/2016.
 */
public class Dice implements IRollable {

    int facce;
    int risultato;

    @Override
    public int rollable (int nFacce){

        return (int)( Math.random()*nFacce )+ 1;
    }
}
