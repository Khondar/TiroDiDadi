package com.example.basil.dicelauncher;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by basil on 11/06/2016.
 */
public class Sacchetta {

    List<Dice> setDiDadi = new ArrayList<>();

    public void riempiLaSacchetta (int[] setDadi){

        for(int j = 0; j < 7; j++){
            for(int i=0; i<setDadi[j];i++){
                Dice dice = new Dice();
                int facce;
                switch (j) {
                    case 0:
                        facce = 4;
                        break;
                    case 1:
                        facce = 6;
                        break;
                    case 2:
                        facce = 8;
                        break;
                    case 3:
                        facce = 10;
                        break;
                    case 4:
                        facce = 12;
                        break;
                    case 5:
                        facce = 20;
                        break;
                    case 6:
                        facce = 100;
                        break;
                    default:
                        facce=0;
                        break;
                }
                dice.setFacce(facce);
                setDiDadi.add(dice);
                }
            }
        }

    public int[] svuotaLaSacchetta(){
        int[]setDadi = new int[7];
        for(int i=0; i<setDiDadi.size(); i++){
            Dice dice = setDiDadi.get(i);
            int facce = dice.getFacce();
            switch (facce){
                case 4:
                    setDadi[0]++;
                    break;
                case 6:
                    setDadi[1]++;
                    break;
                case 8:
                    setDadi[2]++;
                    break;
                case 10:
                    setDadi[3]++;
                    break;
                case 12:
                    setDadi[4]++;
                    break;
                case 20:
                    setDadi[5]++;
                    break;
                case 100:
                    setDadi[6]++;
                    break;
            }
        }
        return setDadi;
    }
}




