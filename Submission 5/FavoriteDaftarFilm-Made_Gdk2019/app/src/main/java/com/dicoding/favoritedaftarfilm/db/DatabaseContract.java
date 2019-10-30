package com.dicoding.favoritedaftarfilm.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    static String  TABLE_FAVORIT = "favorite_films";
    static String TABLE_FAVORIT_FILMS = "favorite_tv";

    public static final class FavoriteColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String DESKRIPSI = "overview";
        public static String PHOTO = "poster_path";
        public static String POPULARITY = "popularity";
        public static String BACKGROUND = "background_path";
        public static String VOTE_AVERAGE = "vote_average";
        public static String DATE = "release_date";
        public static String VOTE_COUNT = "vote_count";
        public static String FAVORITE_COLUMNS = "favorite_columns";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORIT)
                .build();
    }

    public static final class FavoriteFilmColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String DESKRIPSI = "deskripsi";
        public static String PHOTO = "photo";
        public static String POPULARITY = "popularity";
        public static String BACKGROUND = "background";
        public static String VOTE_AVERAGE = "vote_average";
        public static String VOTE_COUNT = "vote_count";
        public static String FAVORITE_COLUMNS = "favorite_columns";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORIT_FILMS)
                .build();
    }

    public static final String AUTHORITY = "com.dicoding.daftarfilm";
    private static final String SCHEME = "content";

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static float getColumnFloat(Cursor cursor, String columnName) {
        return cursor.getFloat(cursor.getColumnIndex(columnName));
    }

}

