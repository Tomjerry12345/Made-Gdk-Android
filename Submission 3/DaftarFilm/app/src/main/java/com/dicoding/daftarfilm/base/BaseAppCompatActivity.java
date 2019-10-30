package com.dicoding.daftarfilm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    public static final String KEY_TITLE = "title";
    public static final String KEY_FRAGMENT = "fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AndroidNetworking.initialize(this);
    }
}
