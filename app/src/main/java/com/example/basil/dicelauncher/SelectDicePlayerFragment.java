package com.example.basil.dicelauncher;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giacomo on 13/06/2016.
 */
public class SelectDicePlayerFragment extends Fragment {


    private List<Sacchetta> sacchettaList;
    private RecyclerView recyclerView;
    private SacchettaAdatper mAdapter;
    Context context;
    RecyclerView.LayoutManager mLayoutManager;

    OpenStorageHelper db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.caricamento_recyclerview, container, false);

//database

        db = new OpenStorageHelper(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        context = getActivity();

        prepareSacchetteData();

        mAdapter = new SacchettaAdatper(sacchettaList);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    protected void prepareSacchetteData() {
        sacchettaList = db.getAllSacchetta();
        db.close();
    }

    public void cancella (String number){
            db.deleteSacchetta(Long.valueOf(number));
        }

    }


