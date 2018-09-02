package com.example.sugamparajuli.movieflix.Rest;

import com.example.sugamparajuli.movieflix.Rest.response.MovieListing;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public static Retrofit retrofit = null;
    public static MovieListingService movieListingService = null;
    public static RetrofitManager retrofitManager = null;
    public static String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private RetrofitManager(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        movieListingService = retrofit.create(MovieListingService.class);
    }
    public static RetrofitManager getInstance(){
        if (retrofitManager == null){
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }
    public void getUpcomingMovieList(String apiKey, Callback<MovieListing> getMovieListingcallBack){

        Call<MovieListing> getMovieListing = movieListingService.getUpcomingMovies(apiKey);
        getMovieListing.enqueue(getMovieListingcallBack);
    }
}
