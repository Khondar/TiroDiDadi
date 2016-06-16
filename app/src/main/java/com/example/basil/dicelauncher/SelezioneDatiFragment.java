package com.example.basil.dicelauncher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SelezioneDatiFragment extends Fragment {


    public static final String SOUND = "com.SelezioneDatiFragment.sound";
    public static final String NATURAL20 = "com.SelezioneDatiFragment.natural20";
    public static final String FAIL = "com.SelezioneDatiFragment.fail";

    List<Dice> setDiDadiCollection;

    Context context;

    EditText nD4Text, nD6Text, nD8Text, nD10Text, nD12Text, nD20Text, nD100Text;

    TextView risultatod4, risultatod6, risultatod8, risultatod10, risultatod12, risultatod20, risultatod100;
    TextView totaled4, totaled6, totaled8, totaled10, totaled12, totaled20, totaled100;
    TextView risultato, risultatoTot;

    TextView risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100;

    Button resetBotton;
    Dice dado = new Dice();

    int[] setDadiSalvato = new int[7];
    final Sacchetta miaSacchetta = new Sacchetta();

    Sacchetta sacchettaRecuperata;
    RisultatiLancio lancioD20;
    int[] risultatiDelD20;

    OpenStorageHelper db;
    long sacchetta_ID;
    long dice_id;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.selezione_dati_fragment_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new OpenStorageHelper(getContext());

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/PrinceValiant.ttf");

        nD4Text = (EditText) view.findViewById(R.id.editD4);
        nD6Text = (EditText) view.findViewById(R.id.editD6);
        nD8Text = (EditText) view.findViewById(R.id.editD8);
        nD10Text = (EditText) view.findViewById(R.id.editD10);
        nD12Text = (EditText) view.findViewById(R.id.editD12);
        nD20Text = (EditText) view.findViewById(R.id.editD20);
        nD100Text = (EditText) view.findViewById(R.id.editD100);

        nD4Text.setTypeface(type);
        nD6Text.setTypeface(type);
        nD8Text.setTypeface(type);
        nD10Text.setTypeface(type);
        nD12Text.setTypeface(type);
        nD20Text.setTypeface(type);
        nD100Text.setTypeface(type);

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

        risultatod4.setTypeface(type);
        risultatod6.setTypeface(type);
        risultatod8.setTypeface(type);
        risultatod10.setTypeface(type);
        risultatod12.setTypeface(type);
        risultatod20.setTypeface(type);
        risultatod100.setTypeface(type);

        totaled4.setTypeface(type);
        totaled6.setTypeface(type);
        totaled8.setTypeface(type);
        totaled10.setTypeface(type);
        totaled12.setTypeface(type);
        totaled20.setTypeface(type);
        totaled100.setTypeface(type);

        risultato.setTypeface(type);
        risultatoTot.setTypeface(type);

        risulNumd4 = (TextView) view.findViewById(R.id.totalNumberD4);
        risulNumd6 = (TextView) view.findViewById(R.id.totalNumberD6);
        risulNumd8 = (TextView) view.findViewById(R.id.totalNumberD8);
        risulNumd10 = (TextView) view.findViewById(R.id.totalNumberD10);
        risulNumd12 = (TextView) view.findViewById(R.id.totalNumberD12);
        risulNumd20 = (TextView) view.findViewById(R.id.totalNumberD20);
        risulNumd100 = (TextView) view.findViewById(R.id.totalNumberD100);

        risulNumd4.setTypeface(type);
        risulNumd6.setTypeface(type);
        risulNumd8.setTypeface(type);
        risulNumd10.setTypeface(type);
        risulNumd12.setTypeface(type);
        risulNumd20.setTypeface(type);
        risulNumd100.setTypeface(type);

        risultatod4.setMovementMethod(new ScrollingMovementMethod());
        risultatod6.setMovementMethod(new ScrollingMovementMethod());
        risultatod8.setMovementMethod(new ScrollingMovementMethod());
        risultatod10.setMovementMethod(new ScrollingMovementMethod());
        risultatod12.setMovementMethod(new ScrollingMovementMethod());
        risultatod20.setMovementMethod(new ScrollingMovementMethod());
        risultatod100.setMovementMethod(new ScrollingMovementMethod());

        risulNumd4.setMovementMethod(new ScrollingMovementMethod());
        risulNumd6.setMovementMethod(new ScrollingMovementMethod());
        risulNumd8.setMovementMethod(new ScrollingMovementMethod());
        risulNumd10.setMovementMethod(new ScrollingMovementMethod());
        risulNumd12.setMovementMethod(new ScrollingMovementMethod());
        risulNumd20.setMovementMethod(new ScrollingMovementMethod());
        risulNumd100.setMovementMethod(new ScrollingMovementMethod());
        risultatoTot.setMovementMethod(new ScrollingMovementMethod());

        ImageView imaged4 = (ImageView) view.findViewById(R.id.d4);
        imaged4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod4.setText("");
                totaled4.setText("");
                int nD4Int = setNumeroDadiDiretto(nD4Text);
                RisultatiLancio lancioD4 = lancioDadi(nD4Int, 4);
                stampaSetDadi(lancioD4, risultatod4, totaled4, risulNumd4);
                stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);

                String tag = SOUND;
                invioTag(tag);
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
                stampaSetDadi(lancioD6, risultatod6, totaled6, risulNumd6);
                stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);

                String tag = SOUND;
                invioTag(tag);
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
                stampaSetDadi(lancioD8, risultatod8, totaled8, risulNumd8);
                stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);

                String tag = SOUND;
                invioTag(tag);
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
                stampaSetDadi(lancioD10, risultatod10, totaled10, risulNumd10);
                stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);

                String tag = SOUND;
                invioTag(tag);
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
                stampaSetDadi(lancioD12, risultatod12, totaled12, risulNumd12);
                stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);

                String tag = SOUND;
                invioTag(tag);
            }
        });

        ImageView imaged20 = (ImageView) view.findViewById(R.id.d20);
        imaged20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                risultatod20.setText("");
                totaled20.setText("");
                int nD20Int = setNumeroDadiDiretto(nD20Text);
                lancioD20 = lancioDadi(nD20Int, 20);
                stampaSetDadi20(lancioD20, risultatod20, totaled20, risulNumd20);
                stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);

                String tag = SOUND;
                invioTag(tag);
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
                stampaSetDadi(lancioD100, risultatod100, totaled100, risulNumd100);
                stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);

                String tag = SOUND;
                invioTag(tag);
            }
        });


        resetBotton = (Button) view.findViewById(R.id.reset);
        resetBotton.setTypeface(type);

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        if (savedInstanceState != null) {
            //Risistema il fragment
            risultatod4.setText(savedInstanceState.getString("risultatod4"));
            risultatod6.setText(savedInstanceState.getString("risultatod6"));
            risultatod8.setText(savedInstanceState.getString("risultatod8"));
            risultatod10.setText(savedInstanceState.getString("risultatod10"));
            risultatod12.setText(savedInstanceState.getString("risultatod12"));
            //  risultatod20.setText(savedInstanceState.getString("risultatod20"));
            try {
                risultatiDelD20 = savedInstanceState.getIntArray("risultati del 20");
                for (int j = 0; j < risultatiDelD20.length; j++) {
                    if (risultatiDelD20[j] == 20) {
                        String first = "<font color='#be1e09'> [20] </font>";
                        risultatod20.append(Html.fromHtml(first));
                    } else {
                        risultatod20.append(" [" + Integer.toString(risultatiDelD20[j]) + "] ");
                    }
                }
                lancioD20 = new RisultatiLancio(risultatiDelD20.length);
                lancioD20.setElencoLanci(risultatiDelD20);
            } catch (Exception e) {
            }

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("risultatod4", risultatod4.getText().toString());
        outState.putString("risultatod6", risultatod6.getText().toString());
        outState.putString("risultatod8", risultatod8.getText().toString());
        outState.putString("risultatod10", risultatod10.getText().toString());
        outState.putString("risultatod12", risultatod12.getText().toString());
        outState.putString("risultatod100", risultatod100.getText().toString());

        outState.putString("totaled4", totaled4.getText().toString());
        outState.putString("totaled6", totaled6.getText().toString());
        outState.putString("totaled8", totaled8.getText().toString());
        outState.putString("totaled10", totaled10.getText().toString());
        outState.putString("totaled12", totaled12.getText().toString());
        outState.putString("totaled20", totaled20.getText().toString());
        outState.putString("totaled100", totaled100.getText().toString());

        outState.putString("risulNumd4", risulNumd4.getText().toString());
        outState.putString("risulNumd6", risulNumd6.getText().toString());
        outState.putString("risulNumd8", risulNumd8.getText().toString());
        outState.putString("risulNumd10", risulNumd10.getText().toString());
        outState.putString("risulNumd12", risulNumd12.getText().toString());
        outState.putString("risulNumd20", risulNumd20.getText().toString());
        outState.putString("risulNumd100", risulNumd100.getText().toString());

        outState.putString("totaleTot", risultato.getText().toString());
        outState.putString("totaleRes", risultatoTot.getText().toString());

        try {
            outState.putIntArray("risultati del 20", lancioD20.getElencoLanci());
        } catch (Exception e) {
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void rollDice() {
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
        lancioD20 = lancioDadi(nD20Int, 20);
        RisultatiLancio lancioD100 = lancioDadi(nD100Int, 100);

        if (nD4Int != 0) {
            stampaSetDadi(lancioD4, risultatod4, totaled4, risulNumd4);
        }
        if (nD6Int != 0) {
            stampaSetDadi(lancioD6, risultatod6, totaled6, risulNumd6);
        }
        if (nD8Int != 0) {
            stampaSetDadi(lancioD8, risultatod8, totaled8, risulNumd8);
        }
        if (nD10Int != 0) {
            stampaSetDadi(lancioD10, risultatod10, totaled10, risulNumd10);
        }
        if (nD12Int != 0) {
            stampaSetDadi(lancioD12, risultatod12, totaled12, risulNumd12);
        }
        if (nD20Int != 0) {
            stampaSetDadi20(lancioD20, risultatod20, totaled20, risulNumd20);
        }
        if (nD100Int != 0) {
            stampaSetDadi(lancioD100, risultatod100, totaled100, risulNumd100);
        }

        stampaTotale(risulNumd4, risulNumd6, risulNumd8, risulNumd10, risulNumd12, risulNumd20, risulNumd100, risultatoTot, risultato);
    }

    public void saveDice() {

        setDadiSalvato = recuperaIDadi();

        miaSacchetta.riempiLaSacchetta(setDadiSalvato);
    }

    public void loadDice(String values) {

        sacchettaRecuperata = db.getSacchetta(Long.valueOf(values));

        svuotaIDadi(sacchettaRecuperata.svuotaLaSacchetta());
        db.close();
    }

    public void insertName(String name) {
        Toast.makeText(context, "Hai inserito il nome " + name, Toast.LENGTH_SHORT).show();


        setDadiSalvato = recuperaIDadi();
        miaSacchetta.setNomeProprietario(name);

        sacchetta_ID = db.createSacchetta(miaSacchetta);
        setDiDadiCollection = riempiLaSacchetta(setDadiSalvato);

        db.close();
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

    public void stampaSetDadi(RisultatiLancio lancio, TextView textView, TextView textView2, TextView textView3) {

        int i = lancio.getSommaLanci();
        int[] args = lancio.getElencoLanci();
        for (int j = 0; j < args.length; j++) {
            textView.append(" [" + Integer.toString(args[j]) + "] ");
        }
        textView2.setText("Totale:");
        textView3.setText("" + i);
    }

    public void stampaTotale(TextView textView1, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView stampa, TextView totale) {
        totale.setText("TOT:");
        stampa.setText("" + (prendiValore(textView1) + prendiValore(textView2) + prendiValore(textView3) + prendiValore(textView4) + prendiValore(textView5) + prendiValore(textView6) + prendiValore(textView7)));
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

    public void invioTag(String tag) {
        Intent intent = new Intent();
        intent.setAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
        intent.putExtra(DiceAndRollBroadcast.Extras.BUTTOM_TAG, tag);
        LocalBroadcastManager.getInstance(getView().getContext()).sendBroadcast(intent);
    }

    public void stampaSetDadi20(RisultatiLancio lancio, TextView textView, TextView textView2, TextView textView3) {

        int i = lancio.getSommaLanci();
        int[] args = lancio.getElencoLanci();
        int counter = 0;
        int fail = 0;
        for (int j = 0; j < args.length; j++) {
            if (args[j] == 20) {
                counter++;
                String first = "<font color='#be1e09'> [20] </font>";
                textView.append(Html.fromHtml(first));
            } else if (args[j] == 1) {
                fail++;
                textView.append(" [" + Integer.toString(args[j]) + "] ");
            } else {
                textView.append(" [" + Integer.toString(args[j]) + "] ");
            }

        }
        if (counter > 0) {
            String tag = NATURAL20;
            invioTag(tag);
        }

        if (fail > 0) {
            String tag = FAIL;
            invioTag(tag);
        }
        textView2.setText("Totale:");
        textView3.setText("" + i);
    }

    public int[] recuperaIDadi() {
        int[] nDadi = new int[7];

        nDadi[0] = setNumeroDadi(nD4Text);
        nDadi[1] = setNumeroDadi(nD6Text);
        nDadi[2] = setNumeroDadi(nD8Text);
        nDadi[3] = setNumeroDadi(nD10Text);
        nDadi[4] = setNumeroDadi(nD12Text);
        nDadi[5] = setNumeroDadi(nD20Text);
        nDadi[6] = setNumeroDadi(nD100Text);

        return nDadi;
    }

    public void svuotaIDadi(int[] mieiDadi) {
        nD4Text.setText("" + mieiDadi[0]);
        nD6Text.setText("" + mieiDadi[1]);
        nD8Text.setText("" + mieiDadi[2]);
        nD10Text.setText("" + mieiDadi[3]);
        nD12Text.setText("" + mieiDadi[4]);
        nD20Text.setText("" + mieiDadi[5]);
        nD100Text.setText("" + mieiDadi[6]);
    }

    private List<Dice> riempiLaSacchetta (int[] setDadi){
        List<Dice> setDiDadi = new ArrayList<>();
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
                dice.setSacchettaID((int)sacchetta_ID);
                dice_id=db.createDice(dice);
                setDiDadi.add(dice);
            }
        }
        return setDiDadi;
    }

}
