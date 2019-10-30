package com.dicoding.daftarfilm.db;

import android.database.Cursor;


import com.dicoding.daftarfilm.model.film.Result;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class MappingHelper {

    public static ArrayList<Result> mapCursorToArrayList(Cursor movieCursor) {
        ArrayList<Result> movieList = new ArrayList<>();
        while (movieCursor.moveToNext()) {
            int id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(_ID));
            String title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.TITLE));
            String overview = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.OVERVIEW));
            String posterPath = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.POSTERPATH));
            float popularity = movieCursor.getFloat(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.POPULARITY));
            String backdropPath = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.BACKGROUNDPATH));
            float voteAverage = movieCursor.getFloat(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.VOTE_AVERAGE));
            int voteCount = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.VOTE_COUNT));
            String releaseDate = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.RELEASEDATE));
            movieList.add(new Result(id, title, overview, posterPath, popularity, backdropPath, voteAverage , voteCount, releaseDate));
        }
        return movieList;
    }

}

