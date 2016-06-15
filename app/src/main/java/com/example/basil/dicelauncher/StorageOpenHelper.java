package com.example.basil.dicelauncher;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by giacomo on 13/06/2016.
 */
public class StorageOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "magazzinoDadi.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Sacchetta, Integer> sacchettaDao;
    private Dao<Dice, Integer> diceDao;
    private ConnectionSource connectionSource;

    private RuntimeExceptionDao<Sacchetta, Integer> sacchettaRuntimeDao = null;
    private RuntimeExceptionDao<Dice, Integer> diceRuntimeDao = null;

    public StorageOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {



        if (connectionSource == null) {
            try {
                connectionSource = new JdbcConnectionSource("jdbc:h2:/data/data/com.example.helloandroidh2/databases/helloAndroidH2");
                sacchettaDao = DaoManager.createDao(connectionSource, Sacchetta.class);
                diceDao = DaoManager.createDao(connectionSource, Dice.class);
            } catch (java.sql.SQLException e) {
                throw new RuntimeException("Problems initializing database objects", e);
            }
            try {
                TableUtils.createTable(connectionSource, Sacchetta.class);
                TableUtils.createTable(connectionSource, Dice.class);
            } catch (java.sql.SQLException e) {
                Log.e(StorageOpenHelper.class.getName(), "Unable to create datbases", e);
                throw new RuntimeException(e);
            }
        }

        //RuntimeExceptionDao<Sacchetta, Integer> dao = getSacchettaDao();
        //Sacchetta miaSacchetta = new Sacchetta();
        //dao.create(miaSacchetta);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(StorageOpenHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Sacchetta.class, true);
            TableUtils.dropTable(connectionSource, Dice.class, true);

            onCreate(db, connectionSource);
        }
        catch (java.sql.SQLException e) {
            Log.e(StorageOpenHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

   public Dao<Sacchetta, Integer> getSacchettaDao() throws java.sql.SQLException {
        if (sacchettaDao == null) {
            sacchettaDao = getDao(Sacchetta.class);
        }
        return sacchettaDao;
    }

    public Dao<Dice, Integer> getDiceDao() throws java.sql.SQLException {
        if (diceDao == null) {
            diceDao = getDao(Dice.class);
        }
        return diceDao;
    }


    public RuntimeExceptionDao<Sacchetta, Integer> getSacchettaRuntimeDao() {
        if (sacchettaRuntimeDao == null) {
            sacchettaRuntimeDao = getRuntimeExceptionDao(Sacchetta.class);
        }
        return sacchettaRuntimeDao;
    }

    public RuntimeExceptionDao<Dice, Integer> getDiceRuntimeDao() {
        if (diceRuntimeDao == null) {
            diceRuntimeDao = getRuntimeExceptionDao(Dice.class);
        }
        return diceRuntimeDao;
    }

    @Override
    public void close(){
        super.close();
        sacchettaDao = null;
        diceDao = null;
        sacchettaRuntimeDao = null;
        diceRuntimeDao = null;
    }

}

