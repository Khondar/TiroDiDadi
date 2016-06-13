package com.example.basil.dicelauncher;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by giacomo on 13/06/2016.
 */
public class StorageOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "magazzinoDadi.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Sacchetta, Integer> sacchettaDao = null;
    private RuntimeExceptionDao<Sacchetta, Integer> simpleRuntimeDao = null;


    public StorageOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

        try {

            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource, Sacchetta.class);

        }
        catch (java.sql.SQLException e) {
            Log.e(StorageOpenHelper.class.getName(), "Unable to create datbases", e);
            throw new RuntimeException(e);
        }

        RuntimeExceptionDao<Sacchetta, Integer> dao = getSacchettaDao();
        Sacchetta miaSacchetta = new Sacchetta();
        dao.create(miaSacchetta);

      /*  // here we try inserting data in the on-create as a test
        RuntimeExceptionDao<SimpleData, Integer> dao = getSimpleDataDao();
        long millis = System.currentTimeMillis();
        // create some entries in the onCreate
        SimpleData simple = new SimpleData(millis);
        dao.create(simple);
        simple = new SimpleData(millis + 1);
        dao.create(simple);
        Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
        */

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(StorageOpenHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Sacchetta.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        }
        catch (java.sql.SQLException e) {
            Log.e(StorageOpenHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    //Questo è un commento


    public RuntimeExceptionDao<Sacchetta, Integer> getSacchettaDaoDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(Sacchetta.class);
        }
        return simpleRuntimeDao;
    }

}

