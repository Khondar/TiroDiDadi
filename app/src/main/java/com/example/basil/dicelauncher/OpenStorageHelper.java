package com.example.basil.dicelauncher;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    private static final String SACCHETTA_CREATE = "create table " + SACCHETTA_TABLE + " (" + KEY_ID +" integer primary key autoincrement, "+ KEY_SACCHETTA_NAME + " text not null);";
    private static final String DICE_CREATE = "create table " + DICE_TABLE + " (" + KEY_ID + " integer primary key autoincrement, "+ KEY_DICE_FACCE + " integer not null, "+ KEY_SACCHETTA_ID +" integer not null);";

    public OpenStorageHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SACCHETTA_CREATE);
        database.execSQL(DICE_CREATE);
    }

    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {

        database.execSQL("DROP TABLE IF EXISTS "+ SACCHETTA_TABLE);
        database.execSQL("DROP TABLE IF EXISTS "+ DICE_TABLE);
        onCreate(database);
    }


}
