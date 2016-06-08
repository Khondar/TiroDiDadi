package com.example.basil.dicelauncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nD4Text;
    EditText nD6Text;
    EditText nD8Text;
    EditText nD10Text;
    EditText nD12Text;
    EditText nD20Text;
    EditText nD100Text;

    TextView risultato;
    TextView stampaDadi;
    TextView stampaSetDadi;

    Button loadBotton;
    Button saveBotton;
    Button rollBotton;

    int[] setDadiSalvato = new int[7];
    List<Player> players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nD4Text = (EditText) findViewById(R.id.editD4);
        nD6Text = (EditText) findViewById(R.id.editD6);
        nD8Text = (EditText) findViewById(R.id.editD8);
        nD10Text = (EditText) findViewById(R.id.editD10);
        nD12Text = (EditText) findViewById(R.id.editD12);
        nD20Text = (EditText) findViewById(R.id.editD20);
        nD100Text = (EditText) findViewById(R.id.editD100);
        risultato = (TextView) findViewById(R.id.result);
        stampaDadi = (TextView) findViewById(R.id.result2);
        stampaSetDadi = (TextView) findViewById(R.id.setDadi);
        loadBotton = (Button) findViewById(R.id.load);
        saveBotton = (Button) findViewById(R.id.save);
        rollBotton = (Button) findViewById(R.id.roll);


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


                stampaSetDadi(lancioD4.getElencoLanci(), stampaDadi, 4);
                stampaSetDadi(lancioD6.getElencoLanci(), stampaDadi, 6);
                stampaSetDadi(lancioD8.getElencoLanci(), stampaDadi, 8);
                stampaSetDadi(lancioD10.getElencoLanci(), stampaDadi, 10);
                stampaSetDadi(lancioD12.getElencoLanci(), stampaDadi, 12);
                stampaSetDadi(lancioD20.getElencoLanci(), stampaDadi, 20);
                stampaSetDadi(lancioD100.getElencoLanci(), stampaDadi, 100);


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

                SelectNameFragment fragment = new SelectNameFragment();

                getSupportFragmentManager().beginTransaction().add(R.id.layout, fragment).addToBackStack(null).commit();


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
