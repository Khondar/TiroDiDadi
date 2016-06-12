package com.example.basil.dicelauncher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by basil on 12/06/2016.
 */
public class DiceStorage implements Serializable {

    List<Sacchetta> players = new ArrayList<>();

    public void stipaLaSacchetta(Sacchetta sacchetta){
        players.add(sacchetta);
    }

    public List<Sacchetta> getPlayers() {
        return players;
    }
}
