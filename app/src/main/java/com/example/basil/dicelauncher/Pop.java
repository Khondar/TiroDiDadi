package com.example.basil.dicelauncher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Danilo on 17/06/2016.
 */
public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        TextView textView = (TextView) findViewById(R.id.testo);
        Button button = (Button) findViewById(R.id.okButton);
        Typeface type = Typeface.createFromAsset(this.getAssets(),"fonts/PrinceValiant.ttf");
        textView.setTypeface(type);
        button.setTypeface(type);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pop.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));

    }

}
