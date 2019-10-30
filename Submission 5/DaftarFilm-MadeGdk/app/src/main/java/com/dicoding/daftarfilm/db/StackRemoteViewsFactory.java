package com.dicoding.daftarfilm.db;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.model.film.Result;
import com.dicoding.daftarfilm.widget.FavoriteMovieWidget;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<Result> widgetItems = new ArrayList<>();
    private Context context;

    public StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        FavoriteHelper favoriteHelper = new FavoriteHelper(context);
        favoriteHelper.open();
        widgetItems = FavoriteHelper.getInstance(context).getAllFilm();
    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
        widgetItems = FavoriteHelper.getInstance(context).getAllFilm();
        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return widgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);

        if (widgetItems.size() >0){
            Result modelMovie = widgetItems.get(position);

            Bitmap bmp = null;
            try {
                bmp = Glide.with(context)
                        .asBitmap()
                        .load(modelMovie.getPosterPath())
                        .into(130, 195)
                        .get();
                rv.setImageViewBitmap(R.id.imageView, bmp);
            } catch (InterruptedException | ExecutionException e) {

            }

            Bundle extras = new Bundle();
            extras.putInt(FavoriteMovieWidget.EXTRA_ITEM, position);
            Intent fillIntent = new Intent();
            fillIntent.putExtras(extras);

            rv.setOnClickFillInIntent(R.id.imageView, fillIntent);
        }

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

