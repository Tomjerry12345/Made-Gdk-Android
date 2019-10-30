package com.tomjerry.submission1;

import android.os.Parcelable;
import android.os.Parcel;

public class Film implements Parcelable {
    String judul , rilis , genre , durasi , deskripsi;
    int photo;

    public Film(String judul, String rilis, String genre, String durasi, int photo , String deskripsi) {
        this.judul = judul;
        this.rilis = rilis;
        this.genre = genre;
        this.durasi = durasi;
        this.deskripsi = deskripsi;
        this.photo = photo;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    public String getRilis() {
        return rilis;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPhoto()
    {
        return photo;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeString(this.rilis);
        dest.writeString(this.genre);
        dest.writeString(this.durasi);
        dest.writeString(this.deskripsi);
        dest.writeInt(this.photo);
    }

    public Film() {
    }

    protected Film(Parcel in) {
        this.judul = in.readString();
        this.rilis = in.readString();
        this.genre = in.readString();
        this.durasi = in.readString();
        this.deskripsi = in.readString();
        this.photo = in.readInt();
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
