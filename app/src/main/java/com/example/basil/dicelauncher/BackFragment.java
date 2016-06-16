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
 * Created by giacomo on 16/06/2016.
 */
public class BackFragment extends Fragment {

    final static public String RETURN_BACK="back";
    public String tag;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.back_fragment, container, false);

        Button back = (Button) v.findViewById(R.id.back);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/PrinceValiant.ttf");
        back.setTypeface(type);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tag = RETURN_BACK;
                Intent intent = new Intent();
                intent.setAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
                intent.putExtra(DiceAndRollBroadcast.Extras.BUTTOM_TAG, tag);
                intent.putExtra(DiceAndRollBroadcast.Extras.MESSAGE_TAG, "");
                LocalBroadcastManager.getInstance(getView().getContext()).sendBroadcast(intent);
            }
        });

        return v;
    }


}
