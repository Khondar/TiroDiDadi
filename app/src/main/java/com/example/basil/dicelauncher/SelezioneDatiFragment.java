package com.example.basil.dicelauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by basil on 08/06/2016.
 */
public class SelezioneDatiFragment extends Fragment {

    public static final String ROLL = "roll";
    public static final String SAVE = "save";
    public static final String LOAD = "load";

    EditText nD4Text;
    EditText nD6Text;
    EditText nD8Text;
    EditText nD10Text;
    EditText nD12Text;
    EditText nD20Text;
    EditText nD100Text;

    TextView risultatod4;
    TextView totaled4;
    TextView risultatod6;
    TextView totaled6;
    TextView risultatod8;
    TextView totaled8;
    TextView risultatod10;
    TextView totaled10;
    TextView risultatod12;
    TextView totaled12;
    TextView risultatod20;
    TextView totaled20;
    TextView risultatod100;
    TextView totaled100;
    TextView risultato;

    Button resetBotton;

    int[] setDadiSalvato = new int[7];
    List<Player> players;

    Dice dado = new Dice();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.selezione_dati_fragment_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nD4Text = (EditText) view.findViewById(R.id.editD4);
        nD6Text = (EditText) view.findViewById(R.id.editD6);
        nD8Text = (EditText) view.findViewById(R.id.editD8);
        nD10Text = (EditText) view.findViewById(R.id.editD10);
        nD12Text = (EditText) view.findViewById(R.id.editD12);
        nD20Text = (EditText) view.findViewById(R.id.editD20);
        nD100Text = (EditText) view.findViewById(R.id.editD100);

        risultatod4 = (TextView) view.findViewById(R.id.resultD4);
        totaled4 = (TextView) view.findViewById(R.id.totalD4);
        risultatod6 = (TextView) view.findViewById(R.id.resultD6);
        totaled6 = (TextView) view.findViewById(R.id.totalD6);
        risultatod8 = (TextView) view.findViewById(R.id.resultD8);
        totaled8 = (TextView) view.findViewById(R.id.totalD8);
        risultatod10 = (TextView) view.findViewById(R.id.resultD10);
        totaled10 = (TextView) view.findViewById(R.id.totalD10);
        risultatod12 = (TextView) view.findViewById(R.id.resultD12);
        totaled12 = (TextView) view.findViewById(R.id.totalD12);
        risultatod20 = (TextView) view.findViewById(R.id.resultD20);
        totaled20 = (TextView) view.findViewById(R.id.totalD20);
        risultatod100 = (TextView) view.findViewById(R.id.resultD100);
        totaled100 = (TextView) view.findViewById(R.id.totalD100);
        risultato = (TextView) view.findViewById(R.id.resultTot);

        resetBotton = (Button) view.findViewById(R.id.reset);

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       // IntentFilter filter = new IntentFilter();
       // filter.addAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
       // LocalBroadcastManager.getInstance(getView().getContext()).registerReceiver(broadcast, filter);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStop() {
       //LocalBroadcastManager.getInstance(getView().getContext()).unregisterReceiver(broadcast);
        super.onStop();
    }



        //private DiceAndRollBroadcast broadcast = new DiceAndRollBroadcast() {
           // @Override
            public void diceAndRoll(String tag) {

                switch (tag) {
                    case ROLL:

                        Toast.makeText(getContext(), "ROLL", Toast.LENGTH_SHORT).show();


                        risultatod4.setText("");
                        totaled4.setText("");
                        risultatod6.setText("");
                        totaled6.setText("");
                        risultatod8.setText("");
                        totaled8.setText("");
                        risultatod10.setText("");
                        totaled10.setText("");
                        risultatod12.setText("");
                        totaled12.setText("");
                        risultatod20.setText("");
                        totaled20.setText("");
                        risultatod100.setText("");
                        totaled100.setText("");


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
                stampaSetDadi(lancioD4.getElencoLanci(), risultatod4, totaled4, 4);}
                if(nD6Int != 0){
                stampaSetDadi(lancioD6.getElencoLanci(), risultatod6, totaled6, 6);}
                if(nD8Int != 0){
                stampaSetDadi(lancioD8.getElencoLanci(), risultatod8, totaled8, 8);}
                if(nD10Int != 0){
                stampaSetDadi(lancioD10.getElencoLanci(), risultatod10, totaled10, 10);}
                if(nD12Int != 0){
                stampaSetDadi(lancioD12.getElencoLanci(), risultatod12, totaled12, 12);}
                if(nD20Int != 0){
                stampaSetDadi(lancioD20.getElencoLanci(), risultatod20, totaled20, 20);}
                if(nD100Int != 0){
                stampaSetDadi(lancioD100.getElencoLanci(), risultatod100, totaled100, 100);}


                int sommaTotale = lancioD4.getSommaLanci() + lancioD6.getSommaLanci() + lancioD8.getSommaLanci() + lancioD10.getSommaLanci() + lancioD12.getSommaLanci() + lancioD20.getSommaLanci() + lancioD100.getSommaLanci();
                risultato.setText("Somma totale: " + Integer.toString(sommaTotale));


                        break;
                    case SAVE:

                        Toast.makeText(getContext(), "SAVE", Toast.LENGTH_SHORT).show();

                        break;
                    case LOAD:

                        Toast.makeText(getContext(), "LOAD", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        break;
                }


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
            int nuovoNumero = dado.rollable(nFacce);
            lancio.setElencoLanci(j, nuovoNumero);
        }
        return lancio;
    }

    public void stampaSetDadi(int[] args, TextView textView, TextView textView2, int facce){
        textView.append("Risultati d" + facce +": ");
        int i=0;
        for(int j=0; j<args.length; j++){
            textView.append("[" + Integer.toString(args[j]) + "]");
            i = i+args[j];
        }
        textView2.append("Totale: " + i);
    }

}
