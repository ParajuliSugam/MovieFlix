package com.example.sugamparajuli.movieflix.Rest;

import com.example.sugamparajuli.movieflix.Rest.response.MovieListing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListingService {

    @GET("upcoming")
    Call<MovieListing> getUpcomingMovies(@Query("api_key") String apiKey);

}
