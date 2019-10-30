package com.dicoding.daftarfilm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbFavorite";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREAT_TABLE_FAVORITE_FILMS = String.format("CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAVORIT_FILMS,
            DatabaseContract.FavoriteColumns._ID,
            DatabaseContract.FavoriteColumns.TITLE,
            DatabaseContract.FavoriteColumns.POPULARITY,
            DatabaseContract.FavoriteColumns.VOTE_AVERAGE,
            DatabaseContract.FavoriteColumns.OVERVIEW,
            DatabaseContract.FavoriteColumns.BACKGROUNDPATH,
            DatabaseContract.FavoriteColumns.POSTERPATH,
            DatabaseContract.FavoriteColumns.RELEASEDATE,
            DatabaseContract.FavoriteColumns.VOTE_COUNT,
            DatabaseContract.FavoriteColumns.FAVORITE_COLUMNS
    );

    private static final String SQL_CREAT_TABLE_FAVORITE_TV = String.format("CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAVORIT_TV,
            DatabaseContract.FavoriteTvColumns._ID,
            DatabaseContract.FavoriteTvColumns.TITLE,
            DatabaseContract.FavoriteTvColumns.POPULARITY,
            DatabaseContract.FavoriteTvColumns.VOTE_AVERAGE,
            DatabaseContract.FavoriteTvColumns.DESKRIPSI,
            DatabaseContract.FavoriteTvColumns.BACKGROUND,
            DatabaseContract.FavoriteTvColumns.FAVORITE_COLUMNS,
            DatabaseContract.FavoriteTvColumns.VOTE_COUNT,
            DatabaseContract.FavoriteTvColumns.PHOTO
    );


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAT_TABLE_FAVORITE_FILMS);
        db.execSQL(SQL_CREAT_TABLE_FAVORITE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.TABLE_FAVORIT_FILMS);
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.TABLE_FAVORIT_TV);
        onCreate(db);
    }
}
