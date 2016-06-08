package com.example.basil.dicelauncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by basil on 08/06/2016.
 */
public class SelectNameFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.selectnamefragment_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText namePlayerTxt = (EditText) view.findViewById(R.id.namePlayerEditText);
        String namePlayerStr = namePlayerTxt.getEditableText().toString();

        Button insert = (Button) view.findViewById(R.id.bottoneInsert);
    }

    }
