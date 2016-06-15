package com.example.basil.dicelauncher;
import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by giacomo on 15/06/2016.
 */
public class RollerProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.provider.Dadi";
    static final String URL = "content://" + PROVIDER_NAME + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    /*static final String FACCE = "facce";
    static final String SACCHETTA = "sacchetta";*/

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int DADI_ID = 1;
    static final int FACCE = 2;
    static final int DADI_SACCHETTA_ID = 3;

    static final int SACCHETTA_ID = 4;
    static final int NOME = 5;
    static final int LISTA_DADI = 6;


    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "dadi/#", DADI_ID);
        uriMatcher.addURI(PROVIDER_NAME, "dadi", FACCE);
        uriMatcher.addURI(PROVIDER_NAME, "dadi/#/sacchetta/#", DADI_SACCHETTA_ID);

        uriMatcher.addURI(PROVIDER_NAME, "sacchetta/#", SACCHETTA_ID);
        uriMatcher.addURI(PROVIDER_NAME, "sacchetta/nome", NOME);
        uriMatcher.addURI(PROVIDER_NAME, "sacchetta/lista_dadi", LISTA_DADI);

    }

    /**
     * Database specific constant declarations
     */
    private SQLiteDatabase db;
//    static final String DATABASE_NAME = "DiceStorage";
    static final String DADI_TABLE_NAME = "Dadi";
    static final String SACCHETTA_TABLE_NAME = "Sacchetta";
    /* static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + STUDENTS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " grade TEXT NOT NULL);";*/

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */
    private static class StorageOpenHelper extends OrmLiteSqliteOpenHelper {

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

            }

            try {
                TableUtils.createTable(connectionSource, Sacchetta.class);
                TableUtils.createTable(connectionSource, Dice.class);
            } catch (java.sql.SQLException e) {
                Log.e(StorageOpenHelper.class.getName(), "Unable to create datbases", e);
                throw new RuntimeException(e);
            }
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


    @Override
    public boolean onCreate() {
        Context context = getContext();
        StorageOpenHelper dbHelper = new StorageOpenHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new student record
         */
        long rowID = db.insert(	STUDENTS_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */

        if (rowID > 0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STUDENTS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case DADI_ID:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case FACCE:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = FACCE;
        }
        Cursor c = qb.query(db,	projection,	selection, selectionArgs,null, null, sortOrder);

        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case STUDENTS:
                count = db.delete(STUDENTS_TABLE_NAME, selection, selectionArgs);
                break;

            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( STUDENTS_TABLE_NAME, _ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)){
            case STUDENTS:
                count = db.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);
                break;

            case STUDENT_ID:
                count = db.update(STUDENTS_TABLE_NAME, values, _ID + " = " + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";

            /**
             * Get a particular student
             */
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.students";

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
