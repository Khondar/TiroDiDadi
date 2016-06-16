package com.example.basil.dicelauncher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basil on 15/06/2016.
 */
public class OpenStorageHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "diceStorage.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SACCHETTA_TABLE = "sacchetta";
    private static final String DICE_TABLE = "dice";

    private static final String KEY_ID = "_id";

    private static final String KEY_SACCHETTA_ID = "sacchetta_id";
    private static final String KEY_SACCHETTA_NAME = "name";

    private static final String KEY_DICE_FACCE = "facce";

    private static final String SACCHETTA_CREATE = "create table " + SACCHETTA_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_SACCHETTA_NAME + " text not null);";
    private static final String DICE_CREATE = "create table " + DICE_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_DICE_FACCE + " integer not null, " + KEY_SACCHETTA_ID + " integer not null);";

    public OpenStorageHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SACCHETTA_CREATE);
        database.execSQL(DICE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + SACCHETTA_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + DICE_TABLE);
        onCreate(database);
    }


    public void close() {

        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public long createSacchetta(Sacchetta sacchetta) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SACCHETTA_NAME, sacchetta.getNomeProprietario());
        long sacchetta_id = database.insert(SACCHETTA_TABLE, null, values);

        return sacchetta_id;
    }

    public Sacchetta getSacchetta(long sacchetta_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + SACCHETTA_TABLE + " WHERE "
                + KEY_ID + " = " + sacchetta_id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Sacchetta td = new Sacchetta();
        td.setCounter(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setNomeProprietario((c.getString(c.getColumnIndex(KEY_SACCHETTA_NAME))));

        return td;
    }

    public int updateSacchetta(Sacchetta sacchetta) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SACCHETTA_NAME, sacchetta.getNomeProprietario());

        return db.update(SACCHETTA_TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(sacchetta.getCounter())});
    }

    public void deleteSacchetta(long sacchetta_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(SACCHETTA_TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(sacchetta_id)});
    }

    public long createDice(Dice dice) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DICE_FACCE, dice.getFacce());
        values.put(KEY_SACCHETTA_ID, dice.getSacchettaID());

        // insert row
        long dice_id = db.insert(DICE_TABLE, null, values);

        return dice_id;
    }

    public int updateDice(Dice dice) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DICE_FACCE, dice.getFacce());

        // updating row
        return db.update(DICE_TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(dice.getId())});
    }

    public List<Dice> getAllDicesByTag(long tag_name) {
        List<Dice> todos = new ArrayList<Dice>();

        String selectQuery = "SELECT  * FROM " + DICE_TABLE + " td WHERE td."
                + KEY_SACCHETTA_ID + " = '" + tag_name + "' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Dice td = new Dice();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setFacce((c.getInt(c.getColumnIndex(KEY_DICE_FACCE))));
                td.setSacchettaID(c.getInt(c.getColumnIndex(KEY_SACCHETTA_ID)));

                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public List<Sacchetta> getAllSacchetta() {
        List<Sacchetta> todos = new ArrayList<Sacchetta>();

        String selectQuery = "SELECT  * FROM " + SACCHETTA_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Sacchetta td = new Sacchetta();
                td.setCounter(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setNomeProprietario((c.getString(c.getColumnIndex(KEY_SACCHETTA_NAME))));
                td.setSetDiDadi(getAllDicesByTag(td.getCounter()));

                todos.add(td);
            } while (c.moveToNext());
        }
        return todos;
    }
}
