package com.example.basil.dicelauncher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basil on 15/06/2016.
 */
public class OpenStorageAdapter {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = OpenStorageAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private OpenStorageHelper osHelper;

    private static final String SACCHETTA_TABLE = "sacchetta";
    private static final String DICE_TABLE = "dice";

    private static final String KEY_ID = "_id";

    private static final String KEY_SACCHETTA_ID = "sacchetta_id";
    private static final String KEY_SACCHETTA_NAME = "name";

    private static final String KEY_DICE_FACCE = "facce";

    public OpenStorageAdapter(Context context) {
        this.context = context;
    }

    public OpenStorageAdapter open() throws SQLException {
        osHelper = new OpenStorageHelper(context);
        database = osHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        osHelper = new OpenStorageHelper(context);
        SQLiteDatabase db = osHelper.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public long createSacchetta(Sacchetta sacchetta) {
        osHelper = new OpenStorageHelper(context);
        database = osHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SACCHETTA_NAME, sacchetta.getNomeProprietario());
        long sacchetta_id = database.insert(SACCHETTA_TABLE, null, values);

        return sacchetta_id;
    }

    public Sacchetta getSacchetta(long sacchetta_id) {
        osHelper = new OpenStorageHelper(context);
        SQLiteDatabase db = osHelper.getReadableDatabase();

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
        osHelper = new OpenStorageHelper(context);
        SQLiteDatabase db = osHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SACCHETTA_NAME, sacchetta.getNomeProprietario());

        return db.update(SACCHETTA_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(sacchetta.getCounter()) });
    }

    public void deleteSacchetta(long sacchetta_id) {
        osHelper = new OpenStorageHelper(context);
        SQLiteDatabase db = osHelper.getWritableDatabase();
        db.delete(SACCHETTA_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(sacchetta_id) });
    }

    public long createDice(Dice dice) {
        osHelper = new OpenStorageHelper(context);
        SQLiteDatabase db = osHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DICE_FACCE, dice.getFacce());
        values.put(KEY_SACCHETTA_ID, dice.getSacchettaID());

        // insert row
        long dice_id = db.insert(DICE_TABLE, null, values);

        return dice_id;
    }

    public int updateDice(Dice dice) {
        osHelper = new OpenStorageHelper(context);
        SQLiteDatabase db = osHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DICE_FACCE, dice.getFacce());

        // updating row
        return db.update(DICE_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(dice.getId()) });
    }

    public List<Dice> getAllDicesByTag(long tag_name) {
        List<Dice> todos = new ArrayList<Dice>();

        String selectQuery = "SELECT  * FROM " + DICE_TABLE + " td WHERE td."
                + KEY_SACCHETTA_ID + " = '" + tag_name;

        osHelper = new OpenStorageHelper(context);
        SQLiteDatabase db = osHelper.getWritableDatabase();
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

}
