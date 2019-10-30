package com.tomjerry.submission1;

import android.widget.BaseAdapter;
import android.content.Context;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ImageView;

public class FilmAdapter extends BaseAdapter
{

    private Context context;
    private ArrayList<Film> film;

    public void setHeroes(ArrayList<Film> film) {
        this.film = film;
    }
    public FilmAdapter(Context context) {
        this.context = context;
        film = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return film.size();
    }

    @Override
    public Object getItem(int i) {
        return film.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_film, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Film film = (Film) getItem(i);
        viewHolder.bind(film);
        return view;
    }

    private class ViewHolder {
        private TextView tvJudul , tvRilis , tvGenre , tvDurasi;
        private ImageView imgPhoto;
        ViewHolder(View view) {
            tvJudul = (TextView) view.findViewById(R.id.tv_judul);
            tvRilis = (TextView) view.findViewById(R.id.tv_rilis);
            tvGenre = (TextView) view.findViewById(R.id.tv_genre);
            tvDurasi = (TextView) view.findViewById(R.id.tv_durasi);
            imgPhoto = (ImageView) view.findViewById(R.id.img_photo);
        }
        void bind(Film film) {
            tvJudul.setText(film.getJudul());
            tvRilis.setText(film.getRilis());
            tvGenre.setText(film.getGenre());
            tvDurasi.setText(film.getDurasi());
            imgPhoto.setImageResource(film.getPhoto());
        }
    }
}

