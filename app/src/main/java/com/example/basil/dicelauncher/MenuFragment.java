package com.example.basil.dicelauncher;

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

/**
 * Created by basil on 08/06/2016.
 */
public class MenuFragment extends Fragment {

    public static final String ROLL = "com.MenuFragment.roll";
    public static final String SAVE = "com.MenuFragment.save";
    public static final String LOAD = "com.MenuFragment.load";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.menu_fragment_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/PrinceValiant.ttf");

        Button rollButton = (Button) view.findViewById(R.id.roll);
        rollButton.setTypeface(type);
        Button saveButton = (Button) view.findViewById(R.id.save);
        saveButton.setTypeface(type);
        Button loadButton = (Button) view.findViewById(R.id.load);
        loadButton.setTypeface(type);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = ROLL;
                invioTag(tag);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = SAVE;
                invioTag(tag);

            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = LOAD;
                invioTag(tag);
            }
        });

    }

    public void invioTag(String tag) {
        Intent intent = new Intent();
        intent.setAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
        intent.putExtra(DiceAndRollBroadcast.Extras.BUTTOM_TAG, tag);
        LocalBroadcastManager.getInstance(getView().getContext()).sendBroadcast(intent);
    }
}
