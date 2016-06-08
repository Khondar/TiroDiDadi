package com.example.basil.dicelauncher;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by basil on 08/06/2016.
 */
public class SelectNameFragment extends Fragment {

    FileOutputStream salvaNuovoPlayers;
    String fileName = "salvataggio";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.select_name_fragment_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText namePlayerTxt = (EditText) view.findViewById(R.id.namePlayerEditText);
        final String namePlayerStr = namePlayerTxt.getEditableText().toString();

        Button insert = (Button) view.findViewById(R.id.bottoneInsert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    salvaNuovoPlayers = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
                    salvaNuovoPlayers.write(namePlayerStr.getBytes());
                    salvaNuovoPlayers.close();
                }catch (FileNotFoundException ex){
                    Toast.makeText(getActivity(),"Eccezione File non trovato", Toast.LENGTH_LONG).show();
                }catch (IOException e){
                    Toast.makeText(getActivity(),"Eccezione", Toast.LENGTH_LONG).show();
                }
                catch (NullPointerException e){
                    Toast.makeText(getActivity(),"Eccezione nullpoint", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    }
