package com.example.basil.dicelauncher;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by basil on 11/06/2016.
 */
public class Sacchetta {

    List<Dice> setDadi = new List<Dice>() {
        @Override
        public void add(int location, Dice object) {

        }

        @Override
        public boolean add(Dice object) {
            return false;
        }

        @Override
        public boolean addAll(int location, Collection<? extends Dice> collection) {
            return false;
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
        public Dice get(int location) {
            return null;
        }

        @Override
        public int indexOf(Object object) {
            return 0;
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
        public int lastIndexOf(Object object) {
            return 0;
        }

        @Override
        public ListIterator<Dice> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Dice> listIterator(int location) {
            return null;
        }

        @Override
        public Dice remove(int location) {
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
        public Dice set(int location, Dice object) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public List<Dice> subList(int start, int end) {
            return null;
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

    public void riempiLaSacchetta (int[] setDadi){

        for(int j = 0; j < setDadi.length; j++){
            for(int i=0; i<setDadi[j];i++){

            }
        }
    }


}
