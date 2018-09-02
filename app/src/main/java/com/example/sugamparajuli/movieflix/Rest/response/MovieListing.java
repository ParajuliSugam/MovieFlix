package com.example.sugamparajuli.movieflix.Rest.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieListing implements Parcelable{

    @SerializedName("page")
    private Integer page;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("results")
    private ArrayList<Result> results = new ArrayList<Result>();

    @SerializedName("dates")
    private Dates dates;


    protected MovieListing(Parcel in) {
        results = in.createTypedArrayList(Result.CREATOR);
        dates = in.readParcelable(Dates.class.getClassLoader());
    }

    public static final Creator<MovieListing> CREATOR = new Creator<MovieListing>() {
        @Override
        public MovieListing createFromParcel(Parcel in) {
            return new MovieListing(in);
        }

        @Override
        public MovieListing[] newArray(int size) {
            return new MovieListing[size];
        }
    };

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(results);
        parcel.writeParcelable(dates, i);
    }
}
