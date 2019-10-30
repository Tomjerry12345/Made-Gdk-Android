package com.dicoding.daftarfilm.model.film;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFilm implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<ListFilm> CREATOR = new Creator<ListFilm>() {


        @SuppressWarnings({
				"unchecked"
			})
        public ListFilm createFromParcel(Parcel in) {
            return new ListFilm(in);
        }

        public ListFilm[] newArray(int size) {
            return (new ListFilm[size]);
        }

    }
    ;

    protected ListFilm(Parcel in) {
        in.readList(this.results, (com.dicoding.daftarfilm.model.film.Result.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListFilm() {
    }

    /**
     *
     * @param results
     */
    public ListFilm(List<Result> results) {
        super();
        this.results = results;
    }


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}

