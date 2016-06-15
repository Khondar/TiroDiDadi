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

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giacomo on 13/06/2016.
 */
public class SelectDicePlayerFragment extends Fragment {

    private StorageOpenHelper databaseHelper = null;
    private List<Sacchetta> sacchettaList;
    private RecyclerView recyclerView;
    private SacchettaAdatper mAdapter;
    Context context;
    private int selectedRecordPosition = -1;
    public final static String DELETE = "com.SelectDicePlayerFragment.delete";
    public final static String LOAD = "com.SelectDicePlayerFragment.load";

    private Dao<Sacchetta, Integer> sacchettaDao;
    private Dao<Dice, Integer> diceDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.caricamento_recyclerview, container, false);

        try {
            // This is how, a reference of DAO object can be done
            sacchettaDao =  getHelper().getSacchettaDao();
            diceDao =  getHelper().getDiceDao();

            // Query the database. We need all the records so, used queryForAll()
            /*studentList = studentDao.queryForAll();

            // Set the header of the ListView
            final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.list_item, listview, false);
            listview.addHeaderView(rowView);

            //Now, link the  RecordArrayAdapter with the ListView
            listview.setAdapter(new RecordArrayAdapter(this, R.layout.list_item, studentList, techerDao));

            // Attach OnItemLongClickListener and OnItemClickListener to track user action and perform accordingly
            listview.setOnItemLongClickListener(this);
            listview.setOnItemClickListener(this);

            // If, no record found in the database, appropriate message needs to be displayed.
            populateNoRecordMsg();*/

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        /*try {
            FileInputStream fis = context.openFileInput(SelezioneDatiFragment.FILESALVATAGGIO);
            ObjectInputStream is = new ObjectInputStream(fis);

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

        }*/
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

    private StorageOpenHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, StorageOpenHelper.class);
        }
        return databaseHelper;
    }
}
