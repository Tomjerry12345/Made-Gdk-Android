package com.dicoding.daftarfilm.model.tv;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;

import java.util.ArrayList;
import android.os.Parcelable;

public class Result implements Parcelable{

    @SerializedName("original_name")
    @Expose
    private String originalName;
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
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<>();
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("overview")
    @Expose
    private String overview;
	@SerializedName("origin_country")
    @Expose
    private List<String> originCountry = new ArrayList<>();

    private int favorite;

    private int id;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param id
     * @param genreIds
     * @param title
     * @param releaseDate
     * @param overview
     * @param posterPath
     * @param originalTitle
     * @param voteAverage
     * @param originalLanguage
     * @param adult
     * @param backdropPath
     * @param voteCount
     * @param video
     * @param popularity
     */
    public Result(String originalName, String backdropPath, List<Integer> genreIds, Integer id, String originalLanguage, String name, String overview, String posterPath, String firstAirDate, List<String> originCountry, Float voteAverage, Integer voteCount, Float popularity) {
        super();
        this.originalName = originalName;
        this.backdropPath = backdropPath;
        this.genreIds = genreIds;
        this.id = id;
        this.originalLanguage = originalLanguage;
        this.name = name;
        this.overview = overview;
        this.posterPath = posterPath;
        this.firstAirDate = firstAirDate;
        this.originCountry = originCountry;
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
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

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }
	

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalName);
        dest.writeString(this.backdropPath);
        dest.writeList(this.genreIds);
        dest.writeValue(this.id);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.posterPath);
        dest.writeValue(this.popularity);
        dest.writeList(this.originCountry);
        dest.writeValue(this.voteAverage);
        dest.writeValue(this.voteCount);
        dest.writeInt(this.favorite);
        dest.writeInt(this.id);
    }

	protected Result(Parcel in) {
		this.originalName = in.readString();
        this.backdropPath = in.readString();
        this.genreIds = new ArrayList<>();
        in.readList(this.genreIds, List.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalLanguage = in.readString();
        this.name = in.readString();
        this.overview = in.readString();
        this.firstAirDate = in.readString();
        this.posterPath = in.readString();
        this.popularity = (Float) in.readValue(Float.class.getClassLoader());
        this.originCountry = new ArrayList<>();
        in.readList(this.genreIds, List.class.getClassLoader());
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
