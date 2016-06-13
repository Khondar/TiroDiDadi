package com.example.basil.dicelauncher;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giacomo on 13/06/2016.
 */
public class SelectDicePlayerFragment extends Fragment {

    private List<Sacchetta> sacchettaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SacchettaAdatper mAdapter;
    Context context;
    DiceStorage magazzino;
    public final static String DELETE = "com.SelectDicePlayerFragment.delete";
    public final static String LOAD = "com.SelectDicePlayerFragment.load";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.caricamento_recyclerview, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        context = getActivity();

        prepareSacchetteData();

        mAdapter = new SacchettaAdatper(sacchettaList);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    protected void prepareSacchetteData() {

        try {
            FileInputStream fis = context.openFileInput(SelezioneDatiFragment.FILESALVATAGGIO);
            ObjectInputStream is = new ObjectInputStream(fis);
            magazzino = (DiceStorage) is.readObject();
            is.close();
            fis.close();
            Toast.makeText(context, "file caricato", Toast.LENGTH_SHORT).show();
            sacchettaList = magazzino.getPlayers();
        } catch (java.io.FileNotFoundException e) {
            Toast.makeText(context, "file non trovato", Toast.LENGTH_SHORT).show();

        } catch (java.io.IOException e) {

            Toast.makeText(context, "file non caricato, IOException", Toast.LENGTH_SHORT).show();

        } catch (java.lang.ClassNotFoundException e) {

            Toast.makeText(context, "file non caricato, Classe non trovata", Toast.LENGTH_SHORT).show();

        }
    }

    public void cancellaCarica (String tag, String number){

        int id = Integer.parseInt(number);
        Sacchetta miaSacca = sacchettaList.get(id);

        switch (tag){
            case LOAD:
                  int[] args = miaSacca.svuotaLaSacchetta();
                break;
            case DELETE:
                    sacchettaList.remove(id);
                break;
            default:
                break;
        }

    }
}
