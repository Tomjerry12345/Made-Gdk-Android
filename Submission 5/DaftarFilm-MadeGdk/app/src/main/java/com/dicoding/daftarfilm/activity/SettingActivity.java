package com.dicoding.daftarfilm.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.notifikasi.DailyReminder;
import com.dicoding.daftarfilm.notifikasi.ReleaseToday;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final Switch switchRelease = findViewById(R.id.switchRelease);
        final Switch switchDaily = findViewById(R.id.switchDaily);

        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean stateRelease = sharedPreferences.getBoolean(getString(R.string.state_release), false);
        boolean stateDaily = sharedPreferences.getBoolean(getString(R.string.state_daily), false);
        ReleaseToday releaseToday = new ReleaseToday();
        if (stateRelease){
            switchRelease.setChecked(true);
            releaseToday.setRepeatingAlarm(this.getApplicationContext());
        } else {
            switchRelease.setChecked(false);
            releaseToday.cancelAlarm(this.getApplicationContext());

        }

        DailyReminder dailyReminder = new DailyReminder();
        if (stateDaily){
            switchDaily.setChecked(true);
            dailyReminder.setRepeatingAlarm(this.getApplicationContext());
        } else {
            switchDaily.setChecked(false);
            dailyReminder.cancelAlarm(this.getApplicationContext());

        }


        switchRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ReleaseToday releaseToday = new ReleaseToday();
                if (isChecked){
                    switchRelease.setChecked(true);
                    editor.putBoolean(getString(R.string.state_release), true);
                    editor.apply();
                    releaseToday.setRepeatingAlarm(getApplicationContext().getApplicationContext());
                } else {
                    switchRelease.setChecked(false);
                    editor.putBoolean(getString(R.string.state_release), false);
                    editor.apply();
                    releaseToday.cancelAlarm(getApplicationContext().getApplicationContext());
                }
            }
        });

        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DailyReminder dailyReminder = new DailyReminder();
                if (isChecked){
                    switchDaily.setChecked(true);
                    editor.putBoolean(getString(R.string.state_release), true);
                    editor.apply();
                    dailyReminder.setRepeatingAlarm(getApplicationContext().getApplicationContext());
                } else {
                    switchDaily.setChecked(false);
                    editor.putBoolean(getString(R.string.state_release), false);
                    editor.apply();
                    dailyReminder.cancelAlarm(getApplicationContext().getApplicationContext());
                }
            }
        });
//
    }
}
