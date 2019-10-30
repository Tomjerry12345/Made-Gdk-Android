package com.tomjerry.submission1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {
    TextView tvJudul , tvRilis , tvGenre , tvDurasi , tvDeskripsi;
    ImageView imgPhoto;
    public static final String EXTRA_PERSON = "extra_person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvJudul =(TextView) findViewById(R.id.tv_judul);
        tvRilis =(TextView) findViewById(R.id.tv_rilis);
        tvGenre =(TextView) findViewById(R.id.tv_genre);
        tvDurasi =(TextView) findViewById(R.id.tv_durasi);
        tvDeskripsi =(TextView) findViewById(R.id.tv_overview);
        imgPhoto =(ImageView) findViewById(R.id.img_photo);

        Film film = getIntent().getParcelableExtra(EXTRA_PERSON);
        String txtJudul = film.getJudul();
        String txtRilis = film.getRilis();
        String txtGenre = film.getGenre();
        String txtDurasi = film.getDurasi();
        String txtDeskripsi = film.getDeskripsi();
        int photo = film.getPhoto();

        tvJudul.setText(txtJudul);
        tvRilis.setText(txtRilis);
        tvGenre.setText(txtGenre);
        tvDurasi.setText(txtDurasi);
        tvDeskripsi.setText(txtDeskripsi);
        imgPhoto.setImageResource(photo);
    }
}
