package com.example.basil.dicelauncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giacomo on 13/06/2016.
 */
public class SelectDicePlayerFragment extends Fragment {

    private List<Sacchetta> sacchettaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SacchettaAdatper mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.caricamento_recyclerview, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        mAdapter = new SacchettaAdatper(sacchettaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

        return view;
    }

    protected void prepareMovieData() {
        Sacchetta sacchetta = new Sacchetta();
        sacchettaList.add(sacchetta);

        mAdapter.notifyDataSetChanged();
    }
}
