package com.dicoding.daftarfilm.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.provider.Settings;
import android.widget.Toast;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.db.FavoriteHelper;
import com.dicoding.daftarfilm.fragment.FavoriteFragment;
import com.dicoding.daftarfilm.fragment.FragmentFilm;
import com.dicoding.daftarfilm.fragment.FragmentTv;
import com.dicoding.daftarfilm.notifikasi.DailyReminder;
import com.dicoding.daftarfilm.notifikasi.ReleaseToday;

public class MainActivity extends AppCompatActivity
{
    private Fragment pageContent = new FragmentFilm();

    private BottomNavigationView bottomNavigationView;

    private FavoriteHelper favoriteHelper;

    private static final String KEY_FRAGMENT = "key_fragment";

    private static final String TAG = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pageContent).commit();

        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pageContent).commit();

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pageContent).commit();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        boolean stateRelease = sharedPreferences.getBoolean(getString(R.string.state_release), false);
        boolean stateDaily = sharedPreferences.getBoolean(getString(R.string.state_daily), false);
        ReleaseToday releaseToday = new ReleaseToday();
        DailyReminder dailyReminder = new DailyReminder();
        if (stateRelease){
            releaseToday.setRepeatingAlarm(this);
        } else {
            releaseToday.cancelAlarm(this);

        }

        if (stateDaily){
            dailyReminder.setRepeatingAlarm(this);
        } else {
            dailyReminder.cancelAlarm(this);

        }

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        bottomNavigationView = findViewById(R.id.btm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_movie :
                        pageContent = new FragmentFilm();
                        break;
                    case R.id.menu_tv :
                        pageContent = new FragmentTv();
                        break;
                    case R.id.menu_favorie :
                        pageContent= new FavoriteFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pageContent).commit();

                return true;
            }
        });

    }

    public static class DataObserver extends ContentObserver {
        final Context context;
        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
        Log.i(TAG,"onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    //Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ganti_bahasa) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else if (item.getItemId() == R.id.ganti_reminder) {
            Intent mIntent = new Intent(this , SettingActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }

}

