package com.dicoding.daftarfilm.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_FAVORIT_FILMS = "favorite_films";
    static String TABLE_FAVORIT_TV = "favorite_tv";

    public static final class FavoriteColumns implements BaseColumns {
        static String TITLE = "title";
        static String OVERVIEW = "overview";
        static String POSTERPATH = "poster_path";
        static String POPULARITY = "popularity";
        static String BACKGROUNDPATH = "background_path";
        static String VOTE_AVERAGE = "vote_average";
        static String VOTE_COUNT = "vote_count";
        static String RELEASEDATE = "release_date";
        static String FAVORITE_COLUMNS = "favorite_columns";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORIT_FILMS)
                .build();
    }

    public static final class FavoriteTvColumns implements BaseColumns {
        static String TITLE = "title";
        static String DESKRIPSI = "deskripsi";
        static String PHOTO = "photo";
        static String POPULARITY = "popularity";
        static String BACKGROUND = "background";
        static String VOTE_AVERAGE = "vote_average";
        static String FAVORITE_COLUMNS = "favorite_columns";
        static String VOTE_COUNT = "vote_count";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORIT_TV)
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
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
