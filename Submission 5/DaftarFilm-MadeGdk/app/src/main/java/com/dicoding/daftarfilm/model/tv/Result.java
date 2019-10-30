package com.dicoding.daftarfilm.model.tv;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;

import java.util.ArrayList;
import android.os.Parcelable;

public class Result implements Parcelable{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("overview")
    @Expose
    private String overview;

    private int favorite;

    private int id;

    String url = "https://image.tmdb.org/t/p/w500";

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param id
     * @param overview
     * @param posterPath
     * @param voteAverage
     * @param backdropPath
     * @param voteCount
     * @param popularity
     */
    public Result(String backdropPath, Integer id, String name, String overview, String posterPath, String firstAirDate , Float voteAverage, Integer voteCount, Float popularity) {
        super();
        this.backdropPath = url + backdropPath;
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.posterPath = url + posterPath;
        this.firstAirDate = firstAirDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.popularity = popularity;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdropPath);
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.posterPath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeInt(this.favorite);
        dest.writeInt(this.id);
    }

	protected Result(Parcel in) {
        this.backdropPath = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.overview = in.readString();
        this.firstAirDate = in.readString();
        this.posterPath = in.readString();
        this.popularity = (Float) in.readValue(Float.class.getClassLoader());
        this.voteAverage = (Float) in.readValue(Float.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.favorite = in.readInt();
        this.id = in.readInt();
    }

	public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

}
