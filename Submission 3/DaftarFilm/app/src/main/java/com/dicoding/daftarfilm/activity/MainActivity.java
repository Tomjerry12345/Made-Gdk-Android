package com.dicoding.daftarfilm.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;
import android.provider.Settings;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.base.BaseAppCompatActivity;
import com.dicoding.daftarfilm.fragment.HomeFragment;

public class MainActivity extends BaseAppCompatActivity
{
    private Fragment pageContent = new HomeFragment();
    private String title = "Home";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pageContent).commit();

        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
            title = savedInstanceState.getString(KEY_TITLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pageContent).commit();

        }
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
        outState.putString(KEY_TITLE, title);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }

}

