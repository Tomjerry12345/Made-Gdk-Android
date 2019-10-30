package com.tomjerry.submission1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.content.res.TypedArray;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private String[] dataJudul;
    private String[] dataRilis;
    private String[] dataGenre;
    private String[] dataDurasi;
    private String[] dataDeskripsi;
    private TypedArray dataPhoto;
    private FilmAdapter adapter;
    private ArrayList<Film> filmArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new FilmAdapter(this);

        ListView listView = (ListView) findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, filmArray.get(i).getJudul(), Toast.LENGTH_SHORT).show();
                Film film = new Film();
                film.setJudul(dataJudul[i]);
                film.setRilis(dataRilis[i]);
                film.setGenre(dataGenre[i]);
                film.setDurasi(dataDurasi[i]);
                film.setDeskripsi(dataDeskripsi[i]);
                film.setPhoto(dataPhoto.getResourceId(i, -1));
                Intent moveWithObjectIntent = new Intent(MainActivity.this, DetailActivity.class);
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_PERSON, film);
                startActivity(moveWithObjectIntent);
            }
        });
    }

    private void prepare() {
        dataJudul = getResources().getStringArray(R.array.data_judul);
        dataRilis = getResources().getStringArray(R.array.data_rilis);
        dataGenre = getResources().getStringArray(R.array.data_genre);
        dataDurasi = getResources().getStringArray(R.array.data_durasi);
        dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem() {
        filmArray = new ArrayList<>();


        for (int i = 0; i < dataJudul.length; i++) {
            Film film = new Film();
            film.setJudul(dataJudul[i]);
            film.setPhoto(dataPhoto.getResourceId(i, -1));
            film.setRilis(dataRilis[i]);
            film.setGenre(dataGenre[i]);
            film.setDurasi(dataDurasi[i]);
            film.setDeskripsi(dataDeskripsi[i]);
            filmArray.add(film);
        }

        adapter.setHeroes(filmArray);
    }
}

