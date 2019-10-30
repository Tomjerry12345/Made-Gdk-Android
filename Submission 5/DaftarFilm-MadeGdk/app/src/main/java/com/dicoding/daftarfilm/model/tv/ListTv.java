package com.dicoding.daftarfilm.model.tv;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTv implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<ListTv> CREATOR = new Creator<ListTv>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListTv createFromParcel(Parcel in) {
            return new ListTv(in);
        }

        public ListTv[] newArray(int size) {
            return (new ListTv[size]);
        }

    }
    ;

    protected ListTv(Parcel in) {
        in.readList(this.results, (com.dicoding.daftarfilm.model.tv.Result.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ListTv() {
    }

    /**
     * 
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public ListTv(Integer page, Integer totalResults, Integer totalPages, List<Result> results) {
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
