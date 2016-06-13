package com.example.basil.dicelauncher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by basil on 12/06/2016.
 */
public class PlayerNameFragment extends Fragment {


    String name;
    Context context;
    public static final String NAME = "name";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.insert_player_name, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/PrinceValiant.ttf");

        Button playerButton = (Button) view.findViewById(R.id.savePlayer);
        playerButton.setTypeface(type);
        TextView textView = (TextView) view.findViewById(R.id.comando);
        textView.setTypeface(type);
        final EditText insertName = (EditText) view.findViewById(R.id.playerName);
        insertName.setTypeface(type);

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertName.getEditableText().toString().equals("")){
                    name = "No name";
                }else{
                    name = insertName.getEditableText().toString();
                }

                //Intent insertName = new Intent(getActivity().getBaseContext(), MainActivity.class);
                //insertName.putExtra("name", name);
                //getActivity().startActivity(insertName);

                Intent intent = new Intent();
                intent.setAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
                intent.putExtra(DiceAndRollBroadcast.Extras.BUTTOM_TAG, name);
                LocalBroadcastManager.getInstance(getView().getContext()).sendBroadcast(intent);
                getActivity().getFragmentManager().beginTransaction().remove(PlayerNameFragment.this).commit();

            }
        });

    }
}

/*try{
                    FileInputStream fis = context.openFileInput(FILESALVATAGGIO);
                    ObjectInputStream is = new ObjectInputStream(fis);
                    magazzino = (DiceStorage) is.readObject();
                    is.close();
                    fis.close();
                    Toast.makeText(context,"file caricato",Toast.LENGTH_SHORT).show();

                }
                catch (java.io.FileNotFoundException e){
                    magazzino = new DiceStorage();
                    Toast.makeText(context,"file non trovato",Toast.LENGTH_SHORT).show();
                }
                catch (java.io.IOException e){

                    Toast.makeText(context,"file non caricato, IOException",Toast.LENGTH_SHORT).show();

                }
                catch (java.lang.ClassNotFoundException e){

                    Toast.makeText(context,"file non caricato, Classe non trovata",Toast.LENGTH_SHORT).show();

                }*/