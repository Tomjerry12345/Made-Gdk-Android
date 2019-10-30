package com.dicoding.daftarfilm.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.dicoding.daftarfilm.db.StackRemoteViewsFactory;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}
