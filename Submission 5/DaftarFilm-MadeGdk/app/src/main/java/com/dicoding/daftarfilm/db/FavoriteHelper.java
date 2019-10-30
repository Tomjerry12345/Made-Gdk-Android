package com.dicoding.daftarfilm.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.dicoding.daftarfilm.model.film.Result;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.BACKGROUNDPATH;

import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.OVERVIEW;
import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.FAVORITE_COLUMNS;


import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.POPULARITY;
import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.POSTERPATH;
import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.RELEASEDATE;
import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.VOTE_AVERAGE;
import static com.dicoding.daftarfilm.db.DatabaseContract.FavoriteColumns.VOTE_COUNT;
import static com.dicoding.daftarfilm.db.DatabaseContract.TABLE_FAVORIT_FILMS;
import static com.dicoding.daftarfilm.db.DatabaseContract.TABLE_FAVORIT_TV;

public class FavoriteHelper {
    public static final String DATABASE_TABLE = TABLE_FAVORIT_FILMS;
    public static final String DATABASE_TABLE_FILM = TABLE_FAVORIT_TV;
    private static DatabaseHelper databaseHelper;
    private static  FavoriteHelper INSTANCE;


    private static SQLiteDatabase database;


    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
        if (database.isOpen()){
            database.close();
        }
    }

    public ArrayList<Result>getAllFilm(){
        ArrayList<Result> arrayList =new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Result modelMovie;
        if (cursor.getCount() > 0){
            do {
                modelMovie = new Result();
                modelMovie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                modelMovie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                modelMovie.setPopularity(cursor.getFloat(cursor.getColumnIndexOrThrow(POPULARITY)));
                modelMovie.setVoteAverage(cursor.getFloat(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                modelMovie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                modelMovie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKGROUNDPATH)));
                modelMovie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTERPATH)));
                modelMovie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASEDATE)));
                modelMovie.setFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(FAVORITE_COLUMNS)));
                modelMovie.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(VOTE_COUNT)));
                arrayList.add(modelMovie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<com.dicoding.daftarfilm.model.tv.Result>getAllTv(){
        ArrayList<com.dicoding.daftarfilm.model.tv.Result> arrayList =new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE_FILM, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        com.dicoding.daftarfilm.model.tv.Result modelTv;
        if (cursor.getCount() > 0){
            do {
                modelTv = new com.dicoding.daftarfilm.model.tv.Result();
                modelTv.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                modelTv.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.TITLE)));
                modelTv.setPopularity(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.POPULARITY)));
                modelTv.setVoteAverage(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.VOTE_AVERAGE)));
                modelTv.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.DESKRIPSI)));
                modelTv.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.PHOTO)));
                modelTv.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.BACKGROUND)));
                modelTv.setFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.FAVORITE_COLUMNS)));
                modelTv.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteTvColumns.VOTE_COUNT)));
                arrayList.add(modelTv);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertFavorite (Result modelMovie){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, modelMovie.getTitle());
//        Log.i(TAG, modelMovie.getTitle());
        contentValues.put(POPULARITY, modelMovie.getPopularity());
        contentValues.put(VOTE_AVERAGE, modelMovie.getVoteAverage());
        contentValues.put(OVERVIEW, modelMovie.getOverview());
        contentValues.put(BACKGROUNDPATH, modelMovie.getBackdropPath());
        contentValues.put(POSTERPATH, modelMovie.getPosterPath());
        contentValues.put(RELEASEDATE, modelMovie.getReleaseDate());
        contentValues.put(FAVORITE_COLUMNS, modelMovie.getFavorite());
        contentValues.put(VOTE_COUNT, modelMovie.getVoteCount());
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public long insertFavoriteTv (com.dicoding.daftarfilm.model.tv.Result modelTv){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.FavoriteTvColumns.TITLE, modelTv.getName());
//        Log.i(TAG, modelFilms.getTitle());
        contentValues.put(DatabaseContract.FavoriteTvColumns.POPULARITY, modelTv.getPopularity());
        contentValues.put(DatabaseContract.FavoriteTvColumns.VOTE_AVERAGE, modelTv.getVoteAverage());
        contentValues.put(DatabaseContract.FavoriteTvColumns.DESKRIPSI, modelTv.getOverview());
        contentValues.put(DatabaseContract.FavoriteTvColumns.PHOTO, modelTv.getPosterPath());
        contentValues.put(DatabaseContract.FavoriteTvColumns.BACKGROUND, modelTv.getBackdropPath());
        contentValues.put(DatabaseContract.FavoriteTvColumns.FAVORITE_COLUMNS, modelTv.getFavorite());
        contentValues.put(DatabaseContract.FavoriteTvColumns.VOTE_COUNT, modelTv.getVoteCount());
        return database.insert(DATABASE_TABLE_FILM, null, contentValues);
    }

    public int deleteFavorite(int id){
        return database.delete(TABLE_FAVORIT_FILMS, _ID +"= '"+id+"'", null);
    }

    public int deleteFavoriteTv(int id){
        return database.delete(TABLE_FAVORIT_TV, _ID +"= '"+id+"'", null);
    }

    public Cursor queryByIdMovieProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryMovieProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    public long insertMovieProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateMovieProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteMovieProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }

    public Cursor queryByIdTvProvider(String id) {
        return database.query(DATABASE_TABLE_FILM, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryTvProvider() {
        return database.query(DATABASE_TABLE_FILM
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    public long insertTvProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE_FILM, null, values);
    }

    public int updateTvProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE_FILM, values, _ID + " = ?", new String[]{id});
    }

    public int deleteTvProvider(String id) {
        return database.delete(DATABASE_TABLE_FILM, _ID + " = ?", new String[]{id});
    }
}
