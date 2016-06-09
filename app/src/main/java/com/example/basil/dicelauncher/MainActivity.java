package com.example.basil.dicelauncher;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuFragment menu = new MenuFragment();
        SelezioneDatiFragment dadi = new SelezioneDatiFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, menu).addToBackStack(null).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentDadi, dadi, "dadi").addToBackStack(null).commit();

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcast, filter);
        super.onStart();
    }


    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcast);
        super.onStop();
    }

    private DiceAndRollBroadcast broadcast = new DiceAndRollBroadcast() {
        @Override
        public void diceAndRoll(String tag) {
            String action = "no";
            switch (tag) {
                case MenuFragment.ROLL:
                    action = SelezioneDatiFragment.ROLL;
//                            SelezioneDatiFragment.ROLL;
                    break;
                case MenuFragment.SAVE:
                    action = SelezioneDatiFragment.SAVE;
                    // SelezioneDatiFragment.SAVE
                    break;
                case MenuFragment.LOAD:
                    action = SelezioneDatiFragment.LOAD;
                    //(SelezioneDatiFragment.LOAD);
                    break;
            }

            SelezioneDatiFragment selezioneDatiFragment = (SelezioneDatiFragment) getSupportFragmentManager().findFragmentByTag("dadi");
            selezioneDatiFragment.diceAndRoll(action);


        }
    };
}



      /*

        rollBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stampaDadi.setText("");

                int nD4Int = setNumeroDadi(nD4Text);
                int nD6Int = setNumeroDadi(nD6Text);
                int nD8Int = setNumeroDadi(nD8Text);
                int nD10Int = setNumeroDadi(nD10Text);
                int nD12Int = setNumeroDadi(nD12Text);
                int nD20Int = setNumeroDadi(nD20Text);
                int nD100Int = setNumeroDadi(nD100Text);

                RisultatiLancio lancioD4 = lancioDadi(nD4Int, 4);
                RisultatiLancio lancioD6 = lancioDadi(nD6Int, 6);
                RisultatiLancio lancioD8 = lancioDadi(nD8Int, 8);
                RisultatiLancio lancioD10 = lancioDadi(nD10Int, 10);
                RisultatiLancio lancioD12 = lancioDadi(nD12Int, 12);
                RisultatiLancio lancioD20 = lancioDadi(nD20Int, 20);
                RisultatiLancio lancioD100 = lancioDadi(nD100Int, 100);

                if(nD4Int != 0){
                stampaSetDadi(lancioD4.getElencoLanci(), stampaDadi, 4);}
                if(nD6Int != 0){
                stampaSetDadi(lancioD6.getElencoLanci(), stampaDadi, 6);}
                if(nD8Int != 0){
                stampaSetDadi(lancioD8.getElencoLanci(), stampaDadi, 8);}
                if(nD10Int != 0){
                stampaSetDadi(lancioD10.getElencoLanci(), stampaDadi, 10);}
                if(nD12Int != 0){
                stampaSetDadi(lancioD12.getElencoLanci(), stampaDadi, 12);}
                if(nD20Int != 0){
                stampaSetDadi(lancioD20.getElencoLanci(), stampaDadi, 20);}
                if(nD100Int != 0){
                stampaSetDadi(lancioD100.getElencoLanci(), stampaDadi, 100);}


                int sommaTotale = lancioD4.getSommaLanci() + lancioD6.getSommaLanci() + lancioD8.getSommaLanci() + lancioD10.getSommaLanci() + lancioD12.getSommaLanci() + lancioD20.getSommaLanci() + lancioD100.getSommaLanci();
                risultato.setText("Somma totale: " + Integer.toString(sommaTotale));


            }
        });

        saveBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setDadiSalvato[0] = setNumeroDadi(nD4Text);
                setDadiSalvato[1] = setNumeroDadi(nD6Text);
                setDadiSalvato[2] = setNumeroDadi(nD8Text);
                setDadiSalvato[3] = setNumeroDadi(nD10Text);
                setDadiSalvato[4] = setNumeroDadi(nD12Text);
                setDadiSalvato[5] = setNumeroDadi(nD20Text);
                setDadiSalvato[6] = setNumeroDadi(nD100Text);

//                SelectNameFragment fragment = new SelectNameFragment();
//
//                getSupportFragmentManager().beginTransaction().add(R.id.layout, fragment).addToBackStack(null).commit();


            }
        });

        resetBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nD4Text.setText("");
                nD6Text.setText("");
                nD8Text.setText("");
                nD10Text.setText("");
                nD12Text.setText("");
                nD20Text.setText("");
                nD100Text.setText("");

            }
        });
    }

    public int setNumeroDadi(EditText textView) {
        int nD;
        try {
            nD = Integer.parseInt(textView.getEditableText().toString());
        } catch (NumberFormatException e) {
            nD = 0;
        }
        return nD;
    }

    public RisultatiLancio lancioDadi (int nD, int nFacce){
        RisultatiLancio lancio = new RisultatiLancio(nD);
        for (int j = 0; j < nD; j++) {
            Dice dado = new Dice(nFacce);
            int nuovoNumero = dado.rollable();
            lancio.setElencoLanci(j, nuovoNumero);
        }
        return lancio;
    }

    public void stampaSetDadi(int[] args, TextView textView, int facce){
        textView.append("Risultati d" + facce +": ");
        int i=0;
        for(int j=0; j<args.length; j++){
            textView.append(Integer.toString(args[j]) + " - ");
            i = i+args[j];
        }
        textView.append("Totale: " + i +"\n");
    }

    public void stampaSetDadiSalvati(int[] args, TextView textView){
        textView.append("Set dadi salvati: ");
        for(int j=0; j<args.length; j++){
            textView.append(Integer.toString(args[j]) + " - ");
        }
        textView.append("\n");
    }

}
*/


