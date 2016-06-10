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
import android.widget.ImageView;
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
    TextView risultatoTot;

    TextView risulNumd4;
    TextView risulNumd6;
    TextView risulNumd8;
    TextView risulNumd10;
    TextView risulNumd12;
    TextView risulNumd20;
    TextView risulNumd100;

    Button resetBotton;
    Dice dado = new Dice();

    int[] setDadiSalvato = new int[7];



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
        risultatoTot = (TextView) view.findViewById(R.id.resultNumTot);

        risulNumd4 = (TextView) view.findViewById(R.id.totalNumberD4);
        risulNumd6 = (TextView) view.findViewById(R.id.totalNumberD6);
        risulNumd8 = (TextView) view.findViewById(R.id.totalNumberD8);
        risulNumd10 = (TextView) view.findViewById(R.id.totalNumberD10);
        risulNumd12 = (TextView) view.findViewById(R.id.totalNumberD12);
        risulNumd20 = (TextView) view.findViewById(R.id.totalNumberD20);
        risulNumd100 = (TextView) view.findViewById(R.id.totalNumberD100);

        ImageView imaged4 = (ImageView) view.findViewById(R.id.d4);
        imaged4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod4.setText("");
                totaled4.setText("");
                int nD4Int = setNumeroDadiDiretto(nD4Text);
                RisultatiLancio lancioD4 = lancioDadi(nD4Int, 4);
                stampaSetDadi(lancioD4.getElencoLanci(), risultatod4, totaled4, risulNumd4);
                stampaTotale(risulNumd4,risulNumd6,risulNumd8,risulNumd10,risulNumd12,risulNumd20,risulNumd100,risultatoTot, risultato);

            }
        });

        ImageView imaged6 = (ImageView) view.findViewById(R.id.d6);
        imaged6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod6.setText("");
                totaled6.setText("");
                int nD6Int = setNumeroDadiDiretto(nD6Text);
                RisultatiLancio lancioD6 = lancioDadi(nD6Int, 4);
                stampaSetDadi(lancioD6.getElencoLanci(), risultatod6, totaled6, risulNumd6);
                stampaTotale(risulNumd4,risulNumd6,risulNumd8,risulNumd10,risulNumd12,risulNumd20,risulNumd100,risultatoTot, risultato);
            }
        });

        ImageView imaged8 = (ImageView) view.findViewById(R.id.d8);
        imaged8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod8.setText("");
                totaled8.setText("");
                int nD8Int = setNumeroDadiDiretto(nD8Text);
                RisultatiLancio lancioD8 = lancioDadi(nD8Int, 8);
                stampaSetDadi(lancioD8.getElencoLanci(), risultatod8, totaled8, risulNumd8);
                stampaTotale(risulNumd4,risulNumd6,risulNumd8,risulNumd10,risulNumd12,risulNumd20,risulNumd100,risultatoTot, risultato);
            }
        });

        ImageView imaged10 = (ImageView) view.findViewById(R.id.d10);
        imaged10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod10.setText("");
                totaled10.setText("");
                int nD10Int = setNumeroDadiDiretto(nD10Text);
                RisultatiLancio lancioD10 = lancioDadi(nD10Int, 10);
                stampaSetDadi(lancioD10.getElencoLanci(), risultatod10, totaled10, risulNumd10);
                stampaTotale(risulNumd4,risulNumd6,risulNumd8,risulNumd10,risulNumd12,risulNumd20,risulNumd100,risultatoTot, risultato);
            }
        });

        ImageView imaged12 = (ImageView) view.findViewById(R.id.d12);
        imaged12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod12.setText("");
                totaled12.setText("");
                int nD12Int = setNumeroDadiDiretto(nD12Text);
                RisultatiLancio lancioD12 = lancioDadi(nD12Int, 12);
                stampaSetDadi(lancioD12.getElencoLanci(), risultatod12, totaled12, risulNumd12);
                stampaTotale(risulNumd4,risulNumd6,risulNumd8,risulNumd10,risulNumd12,risulNumd20,risulNumd100,risultatoTot, risultato);
            }
        });

        ImageView imaged20 = (ImageView) view.findViewById(R.id.d20);
        imaged20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod20.setText("");
                totaled20.setText("");
                int nD20Int = setNumeroDadiDiretto(nD20Text);
                RisultatiLancio lancioD20 = lancioDadi(nD20Int, 20);
                stampaSetDadi(lancioD20.getElencoLanci(), risultatod20, totaled20, risulNumd20);
                stampaTotale(risulNumd4,risulNumd6,risulNumd8,risulNumd10,risulNumd12,risulNumd20,risulNumd100,risultatoTot, risultato);
            }
        });

        ImageView imaged100 = (ImageView) view.findViewById(R.id.d100);
        imaged100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod100.setText("");
                totaled100.setText("");
                int nD100Int = setNumeroDadiDiretto(nD100Text);
                RisultatiLancio lancioD100 = lancioDadi(nD100Int, 100);
                stampaSetDadi(lancioD100.getElencoLanci(), risultatod100, totaled100, risulNumd100);
                stampaTotale(risulNumd4,risulNumd6,risulNumd8,risulNumd10,risulNumd12,risulNumd20,risulNumd100,risultatoTot, risultato);
            }
        });


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

                risulNumd4.setText("");
                risulNumd6.setText("");
                risulNumd8.setText("");
                risulNumd10.setText("");
                risulNumd12.setText("");
                risulNumd20.setText("");
                risulNumd100.setText("");

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
                risultato.setText("");
                risultatoTot.setText("");

            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            //Risistema il fragment
            risultatod4.setText(savedInstanceState.getString("risultatod4"));
            risultatod6.setText(savedInstanceState.getString("risultatod6"));
            risultatod8.setText(savedInstanceState.getString("risultatod8"));
            risultatod10.setText(savedInstanceState.getString("risultatod10"));
            risultatod12.setText(savedInstanceState.getString("risultatod12"));
            risultatod20.setText(savedInstanceState.getString("risultatod20"));
            risultatod100.setText(savedInstanceState.getString("risultatod100"));

            totaled4.setText(savedInstanceState.getString("totaled4"));
            totaled6.setText(savedInstanceState.getString("totaled6"));
            totaled8.setText(savedInstanceState.getString("totaled8"));
            totaled10.setText(savedInstanceState.getString("totaled10"));
            totaled12.setText(savedInstanceState.getString("totaled12"));
            totaled20.setText(savedInstanceState.getString("totaled20"));
            totaled100.setText(savedInstanceState.getString("totaled100"));

            risulNumd4.setText(savedInstanceState.getString("risulNumd4"));
            risulNumd6.setText(savedInstanceState.getString("risulNumd6"));
            risulNumd8.setText(savedInstanceState.getString("risulNumd8"));
            risulNumd10.setText(savedInstanceState.getString("risulNumd10"));
            risulNumd12.setText(savedInstanceState.getString("risulNumd12"));
            risulNumd20.setText(savedInstanceState.getString("risulNumd20"));
            risulNumd100.setText(savedInstanceState.getString("risulNumd100"));

            risultato.setText(savedInstanceState.getString("totaleTot"));
            risultatoTot.setText(savedInstanceState.getString("totaleRes"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //salva il fragment
        outState.putString("risultatod4",risultatod4.getText().toString());
        outState.putString("risultatod6",risultatod6.getText().toString());
        outState.putString("risultatod8",risultatod8.getText().toString());
        outState.putString("risultatod10",risultatod10.getText().toString());
        outState.putString("risultatod12",risultatod12.getText().toString());
        outState.putString("risultatod20",risultatod20.getText().toString());
        outState.putString("risultatod100",risultatod100.getText().toString());

        outState.putString("totaled4",totaled4.getText().toString());
        outState.putString("totaled6",totaled6.getText().toString());
        outState.putString("totaled8",totaled8.getText().toString());
        outState.putString("totaled10",totaled10.getText().toString());
        outState.putString("totaled12",totaled12.getText().toString());
        outState.putString("totaled20",totaled20.getText().toString());
        outState.putString("totaled100",totaled100.getText().toString());

        outState.putString("risulNumd4",risulNumd4.getText().toString());
        outState.putString("risulNumd6",risulNumd6.getText().toString());
        outState.putString("risulNumd8",risulNumd8.getText().toString());
        outState.putString("risulNumd10",risulNumd10.getText().toString());
        outState.putString("risulNumd12",risulNumd12.getText().toString());
        outState.putString("risulNumd20",risulNumd20.getText().toString());
        outState.putString("risulNumd100",risulNumd100.getText().toString());

        outState.putString("totaleTot", risultato.getText().toString());
        outState.putString("totaleRes", risultatoTot.getText().toString());

    }

    public void diceAndRoll(String tag) {

        switch (tag) {
            case ROLL:

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

                risulNumd4.setText("");
                risulNumd6.setText("");
                risulNumd8.setText("");
                risulNumd10.setText("");
                risulNumd12.setText("");
                risulNumd20.setText("");
                risulNumd100.setText("");

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

                if (nD4Int != 0) {
                    stampaSetDadi(lancioD4.getElencoLanci(), risultatod4, totaled4, risulNumd4);
                }
                if (nD6Int != 0) {
                    stampaSetDadi(lancioD6.getElencoLanci(), risultatod6, totaled6, risulNumd6);
                }
                if (nD8Int != 0) {
                    stampaSetDadi(lancioD8.getElencoLanci(), risultatod8, totaled8, risulNumd8);
                }
                if (nD10Int != 0) {
                    stampaSetDadi(lancioD10.getElencoLanci(), risultatod10, totaled10, risulNumd10);
                }
                if (nD12Int != 0) {
                    stampaSetDadi(lancioD12.getElencoLanci(), risultatod12, totaled12, risulNumd12);
                }
                if (nD20Int != 0) {
                    stampaSetDadi(lancioD20.getElencoLanci(), risultatod20, totaled20, risulNumd20);
                }
                if (nD100Int != 0) {
                    stampaSetDadi(lancioD100.getElencoLanci(), risultatod100, totaled100, risulNumd100);
                }


                int sommaTotale = lancioD4.getSommaLanci() + lancioD6.getSommaLanci() + lancioD8.getSommaLanci() + lancioD10.getSommaLanci() + lancioD12.getSommaLanci() + lancioD20.getSommaLanci() + lancioD100.getSommaLanci();
                risultato.setText("TOTALE:");
                risultatoTot.setText(Integer.toString(sommaTotale));


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

    public int setNumeroDadiDiretto(EditText textView) {
        int nD;
        try {
            nD = Integer.parseInt(textView.getEditableText().toString());
        } catch (NumberFormatException e) {
            nD = 1;
        }
        return nD;
    }

    public RisultatiLancio lancioDadi(int nD, int nFacce) {
        RisultatiLancio lancio = new RisultatiLancio(nD);
        for (int j = 0; j < nD; j++) {
            int nuovoNumero = dado.rollable(nFacce);
            lancio.setElencoLanci(j, nuovoNumero);
        }
        return lancio;
    }

    public void stampaSetDadi(int[] args, TextView textView, TextView textView2, TextView textView3) {

        int i = 0;
        for (int j = 0; j < args.length; j++) {
            textView.append(" [" + Integer.toString(args[j]) + "] ");
            i = i + args[j];
        }
        textView2.setText("Totale:");
        textView3.setText("" + i);
    }

   public void stampaTotale (TextView textView1, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView stampa, TextView totale){
       totale.setText("TOTALE:");
       stampa.setText(""+(prendiValore(textView1)+prendiValore(textView2)+prendiValore(textView3)+prendiValore(textView4)+prendiValore(textView5)+prendiValore(textView6)+prendiValore(textView7)));
   }

    public int prendiValore(TextView textView) {
        int nD;
        try {
            nD = Integer.parseInt(textView.getText().toString());
        } catch (NumberFormatException e) {
            nD = 0;
        }
        return nD;
    }
}
