package com.example.basil.dicelauncher;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SacchettaAdatper extends RecyclerView.Adapter<SacchettaAdatper.MyViewHolder> {

    public static final String LOAD = "com.SacchettaAdapter.carica";
    public static final String DELETE = "com.SacchettaAdapter.cancella";

    private List<Sacchetta> sacchettaList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, resultd4, resultd6, resultd8, resultd10, resultd12, resultd20, resultd100, id;
        public Button load, delete;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            resultd4 = (TextView) view.findViewById(R.id.resultCarD4);
            resultd6 = (TextView) view.findViewById(R.id.resultCarD6);
            resultd8 = (TextView) view.findViewById(R.id.resultCarD8);
            resultd10 = (TextView) view.findViewById(R.id.resultCarD10);
            resultd12 = (TextView) view.findViewById(R.id.resultCarD12);
            resultd20 = (TextView) view.findViewById(R.id.resultCarD20);
            resultd100 = (TextView) view.findViewById(R.id.resultCarD100);
            id = (TextView) view.findViewById(R.id.numero_id);

            load = (Button) view.findViewById(R.id.load);
            delete = (Button) view.findViewById(R.id.delete);



            load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int posizione = Integer.parseInt(id.getText().toString());
                    String tag = LOAD;
                    String number = Integer.toString(posizione);
                    mandaIlSegnale(v, tag,number);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int posizione = Integer.parseInt(id.getText().toString());
                    String tag = DELETE;
                    String number = Integer.toString(posizione);
                    mandaIlSegnale(v, tag,number);
                }
            });

        }
    }

    public void mandaIlSegnale(View v, String tag, String number){
        Intent intent = new Intent();
        intent.setAction(DiceAndRollBroadcast.Action.ACTION_ROLL_DICE);
        intent.putExtra(DiceAndRollBroadcast.Extras.BUTTOM_TAG, tag);
        intent.putExtra(DiceAndRollBroadcast.Extras.MESSAGE_TAG, number);
        LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
    }

    public SacchettaAdatper(List<Sacchetta> sacchettaList) { this.sacchettaList = sacchettaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Sacchetta sacchetta = sacchettaList.get(position);
        holder.name.setText(sacchetta.getNomeProprietario());
        int[] setDiDadi = sacchetta.svuotaLaSacchetta();
        holder.resultd4.setText(Integer.toString(setDiDadi[0]));
        holder.resultd6.setText(Integer.toString(setDiDadi[1]));
        holder.resultd8.setText(Integer.toString(setDiDadi[2]));
        holder.resultd10.setText(Integer.toString(setDiDadi[3]));
        holder.resultd12.setText(Integer.toString(setDiDadi[4]));
        holder.resultd20.setText(Integer.toString(setDiDadi[5]));
        holder.resultd100.setText(Integer.toString(setDiDadi[6]));
        holder.id.setText(Integer.toString(sacchetta.getCounter()));

    }

    @Override
    public int getItemCount() {
        return sacchettaList.size();
    }

}
