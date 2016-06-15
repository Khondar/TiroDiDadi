package com.example.basil.dicelauncher;

import android.support.annotation.NonNull;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@DatabaseTable (tableName = "sacchette")
public class Sacchetta implements Serializable{

    @ForeignCollectionField
    ForeignCollection<Dice> setDiDadiCollection = new ForeignCollection<Dice>() {
        @Override
        public CloseableIterator<Dice> iterator(int flags) {
            return null;
        }

        @Override
        public CloseableIterator<Dice> closeableIterator(int flags) {
            return null;
        }

        @Override
        public CloseableIterator<Dice> iteratorThrow() throws SQLException {
            return null;
        }

        @Override
        public CloseableIterator<Dice> iteratorThrow(int flags) throws SQLException {
            return null;
        }

        @Override
        public CloseableWrappedIterable<Dice> getWrappedIterable() {
            return null;
        }

        @Override
        public CloseableWrappedIterable<Dice> getWrappedIterable(int flags) {
            return null;
        }

        @Override
        public void closeLastIterator() throws SQLException {

        }

        @Override
        public boolean isEager() {
            return false;
        }

        @Override
        public int update(Dice obj) throws SQLException {
            return 0;
        }

        @Override
        public int updateAll() throws SQLException {
            return 0;
        }

        @Override
        public int refresh(Dice obj) throws SQLException {
            return 0;
        }

        @Override
        public int refreshAll() throws SQLException {
            return 0;
        }

        @Override
        public int refreshCollection() throws SQLException {
            return 0;
        }

        @Override
        public boolean add(Dice obj) {
            return false;
        }

        @Override
        public CloseableIterator<Dice> closeableIterator() {
            return null;
        }

        @Override
        public boolean addAll(Collection<? extends Dice> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean contains(Object object) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Dice> iterator() {
            return null;
        }

        @Override
        public boolean remove(Object object) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(T[] array) {
            return null;
        }
    };

    @DatabaseField (columnName = "nome_proprietario")
    String nomeProprietario;

    @DatabaseField(generatedId = true, columnName = "sacchetta_id")
    int counter;

    List<Dice> setDiDadi = new ArrayList<>(setDiDadiCollection);

    Sacchetta (){    }

    public void riempiLaSacchetta (int[] setDadi){

        for(int j = 0; j < 7; j++){
            for(int i=0; i<setDadi[j];i++){
                Dice dice = new Dice();
                int facce;
                switch (j) {
                    case 0:
                        facce = 4;
                        break;
                    case 1:
                        facce = 6;
                        break;
                    case 2:
                        facce = 8;
                        break;
                    case 3:
                        facce = 10;
                        break;
                    case 4:
                        facce = 12;
                        break;
                    case 5:
                        facce = 20;
                        break;
                    case 6:
                        facce = 100;
                        break;
                    default:
                        facce=0;
                        break;
                }
                dice.setFacce(facce);
                setDiDadi.add(dice);
                }
            }
        }

    public int[] svuotaLaSacchetta(){
        int[]setDadi = new int[7];
        for(int i=0; i<setDiDadi.size(); i++){
            Dice dice = setDiDadi.get(i);
            int facce = dice.getFacce();
            switch (facce){
                case 4:
                    setDadi[0]++;
                    break;
                case 6:
                    setDadi[1]++;
                    break;
                case 8:
                    setDadi[2]++;
                    break;
                case 10:
                    setDadi[3]++;
                    break;
                case 12:
                    setDadi[4]++;
                    break;
                case 20:
                    setDadi[5]++;
                    break;
                case 100:
                    setDadi[6]++;
                    break;
            }
        }
        return setDadi;
    }

    public ForeignCollection<Dice> getSetDiDadi() {
        return setDiDadiCollection;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setSetDiDadi(List<Dice> setDiDadi) {
        this.setDiDadi = setDiDadi;
    }
}





