package com.example.basil.dicelauncher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DiceStorage implements Serializable {

    List<Sacchetta> players = new ArrayList<>();

    public void stipaLaSacchetta(Sacchetta sacchetta){
        players.add(sacchetta);
    }

    public List<Sacchetta> getPlayers() {
        return players;
    }
}
