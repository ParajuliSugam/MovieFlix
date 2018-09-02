package com.example.sugamparajuli.movieflix.Rest.response;

import com.google.gson.annotations.SerializedName;

public class Genres {
    @SerializedName("id")
    private Integer genreid;

    @SerializedName("name")
    private String genreName;

    public Integer getGenreid() {
        return genreid;
    }

    public void setGenreid(Integer genreid) {
        this.genreid = genreid;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
