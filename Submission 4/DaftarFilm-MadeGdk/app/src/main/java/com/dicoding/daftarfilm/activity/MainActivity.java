package com.dicoding.daftarfilm.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.provider.Settings;
import android.widget.Toast;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.base.BaseAppCompatActivity;
import com.dicoding.daftarfilm.db.FavoriteHelper;
import com.dicoding.daftarfilm.fragment.FavoriteFragment;
import com.dicoding.daftarfilm.fragment.HomeFragment;

public class MainActivity extends BaseAppCompatActivity
{
    private Fragment pageContent = new HomeFragment();

    private BottomNavigationView bottomNavigationView;

    private FavoriteHelper favoriteHelper;



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

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_movie :
                        pageContent = new HomeFragment();
                        Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_favorie :
                        pageContent= new FavoriteFragment();
                        Toast.makeText(MainActivity.this, "Star clicked", Toast.LENGTH_SHORT).show();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, pageContent).commit();

                return true;
            }
        });

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
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }

}

