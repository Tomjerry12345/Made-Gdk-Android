package com.dicoding.daftarfilm.notifikasi;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.dicoding.daftarfilm.R;
import com.dicoding.daftarfilm.model.film.ListFilm;
import com.dicoding.daftarfilm.model.film.Result;
import com.dicoding.daftarfilm.retrofit.ApiReleaseToday;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReleaseToday extends BroadcastReceiver {
    public static final int ID = 101;
    private ArrayList<Result> listFilm = new ArrayList<Result>();

    public ReleaseToday() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = formater.format(date);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiReleaseToday.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiReleaseToday apiReleaseToday = retrofit.create(ApiReleaseToday.class);

        Call<ListFilm> call = apiReleaseToday.releaseToday("movie?api_key=407db308a634f3f54cc6c27b7051147d&primary_release_date.gte="+todayDate+"&primary_release_date.lte="+todayDate);

        call.enqueue(new Callback<ListFilm>() {
            @Override
            public void onResponse(Call<ListFilm> call, Response<ListFilm> response) {
                ListFilm film = response.body();
                listFilm = new ArrayList<Result>();

                for (int a = 0; a < film.getResults().size(); a++){
                    listFilm.add(new Result(film.getResults().get(a).getBackdropPath(), film.getResults().get(a).getOverview(), film.getResults().get(a).getPosterPath()
                            , film.getResults().get(a).getReleaseDate(), film.getResults().get(a).getTitle(), film.getResults().get(a).getVoteAverage()
                            , film.getResults().get(a).getVoteCount(), film.getResults().get(a).getPopularity()

                    ));

                }

                showAlarmNotification(context);

            }

            @Override
            public void onFailure(Call<ListFilm> call, Throwable t) {
//
            }
        });
    }

    private void showAlarmNotification(Context context) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "AlarmManager channel";

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Submission 5")
                .setSmallIcon(R.drawable.ic_search_black_24dp)
                .setContentText("Film "+listFilm.get(0).getTitle()+" sudah rilis, yuk check")
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Film "+listFilm.get(0).getTitle()+" sudah rilis, yuk check")
                        .addLine("Film "+listFilm.get(1).getTitle()+" sudah rilis, yuk check")
                        .addLine("Film "+listFilm.get(2).getTitle()+" sudah rilis, yuk check")
                        .addLine("Film "+listFilm.get(3).getTitle()+" sudah rilis, yuk check")
                        .addLine("Film "+listFilm.get(4).getTitle()+" sudah rilis, yuk check")
                        .setSummaryText(context.getString(R.string.more)))
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            builder.setStyle(new NotificationCompat.InboxStyle()
                    .addLine("Film "+listFilm.get(0).getTitle()+" sudah rilis, yuk check")
                    .addLine("Film "+listFilm.get(1).getTitle()+" sudah rilis, yuk check")
                    .addLine("Film "+listFilm.get(2).getTitle()+" sudah rilis, yuk check")
                    .addLine("Film "+listFilm.get(3).getTitle()+" sudah rilis, yuk check")
                    .addLine("Film "+listFilm.get(4).getTitle()+" sudah rilis, yuk check")
                    .setSummaryText(context.getString(R.string.more)));

            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(ID, notification);
        }

    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseToday.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

    }

    public void setRepeatingAlarm(Context context) {
        Calendar calendar = Calendar.getInstance();

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(context, ReleaseToday.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,intent1, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

}

