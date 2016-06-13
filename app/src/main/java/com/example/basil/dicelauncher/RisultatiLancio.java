package com.example.basil.dicelauncher;

/**
 * Created by basil on 07/06/2016.
 */
public class RisultatiLancio {

    int[] elencoLanci;
    int sommaLanci;

    RisultatiLancio (int numeroLanci){
        elencoLanci = new int[numeroLanci];
        sommaLanci = 0;
    }

    public void setElencoLanci(int i, int risultatoLancio) {
        elencoLanci[i]=risultatoLancio;
    }

    public int[] getElencoLanci() {
        return elencoLanci;
    }

    public int getSommaLanci() {
        for (int j=0; j<elencoLanci.length; j++){
            sommaLanci = sommaLanci + elencoLanci[j];
        }
        return sommaLanci;
    }

    public void setElencoLanci(int[] elencoLanci) {
        this.elencoLanci = elencoLanci;
    }

}
