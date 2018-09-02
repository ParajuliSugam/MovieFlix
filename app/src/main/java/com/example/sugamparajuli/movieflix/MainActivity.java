package com.example.sugamparajuli.movieflix;

import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sugamparajuli.movieflix.Rest.RetrofitManager;
import com.example.sugamparajuli.movieflix.Rest.response.MovieListing;
import com.example.sugamparajuli.movieflix.Rest.response.Result;
import com.example.sugamparajuli.movieflix.MovieListAdapter;
import com.example.sugamparajuli.movieflix.BuildConfig;
import com.example.sugamparajuli.movieflix.MovieItem;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Result> upcomingMovielist = new ArrayList<>();
    private MovieListAdapter rcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_movie_list);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        getMovieListing();

        rcAdapter = new MovieListAdapter(MainActivity.this,upcomingMovielist);
        recyclerView.setAdapter(rcAdapter);


//        rvMovieListing = findViewById(R.id.rv_movie_list);
//        rvMovieListing.setHasFixedSize(true);
//        rvMovieListing.setLayoutManager(new LinearLayoutManager(this));
//        listItems = new ArrayList<>();
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        rvMovieListing.setLayoutManager(gridLayoutManager);
//
//
//        rvMovieListing.setAdapter(new MovieListAdapter(getMovieList(),this));
//
////        movieList = new ArrayList<>();
//
////        RecyclerView movieView= (RecyclerView) findViewById(R.id.rv_movie_list);
////
//
//    }
//
//    private ArrayList<MovieItem> getMovieList(){
//        ArrayList<MovieItem> movieList = new ArrayList<>();
//
//        movieList.add(new MovieItem("Baasuri","Love Story","4.6/5",R.drawable.basuri_poster));
//        movieList.add(new MovieItem("Kanxi","Love/Romance","4/5",R.drawable.kanxi_poster));
//        movieList.add(new MovieItem("Mangalam","Category","4.9/5",R.drawable.mangalam_poster));
//        movieList.add(new MovieItem("Parba","Comedy/Romance","3.8/5",R.drawable.parba_poster));
//        movieList.add(new MovieItem("Patuki","Drama/Thriller","4.5/5",R.drawable.patuki_poster));
//        movieList.add(new MovieItem("Sher Bahadur","Action/Drama","4.1/5",R.drawable.sherbahadur_poster));
//        movieList.add(new MovieItem("Jholay","Love Story","4.6/5",R.drawable.jholay_poster));
//        movieList.add(new MovieItem("Kri","Love/Romance","4/5",R.drawable.kri_poster));
//        movieList.add(new MovieItem("Sunkesari","Category","4.9/5",R.drawable.sunkesari_poster));
//        movieList.add(new MovieItem("Tulke","Comedy/Romance","3.8/5",R.drawable.tulke_poster));
//        movieList.add(new MovieItem("Purano Dunga","Drama/Thriller","4.5/5",R.drawable.purano_dunga_poster));
//        movieList.add(new MovieItem("Baasuri","Love Story","4.6/5",R.drawable.basuri_poster));
//        movieList.add(new MovieItem("Kanxi","Love/Romance","4/5",R.drawable.kanxi_poster));
//        movieList.add(new MovieItem("Mangalam","Category","4.9/5",R.drawable.mangalam_poster));
//        movieList.add(new MovieItem("Parba","Comedy/Romance","3.8/5",R.drawable.parba_poster));
//        movieList.add(new MovieItem("Patuki","Drama/Thriller","4.5/5",R.drawable.patuki_poster));
//
//        return movieList;
//   }
    }

    private void getMovieListing() {
        RetrofitManager.getInstance().getUpcomingMovieList(BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieListing>() {
            @Override
            public void onResponse(Call<MovieListing> call, Response<MovieListing> response) {
                if (response.code()==200){
                    upcomingMovielist.addAll(response.body().getResults());
                    rcAdapter.notifyDataSetChanged();

                    rcAdapter.setClickListener(new MovieListAdapter.MovieItemClickListener() {
                        @Override
                        public void onClick(Result result) {
                            Intent startDetailActivity = new Intent(MainActivity.this,MovieViewActivity.class);
                            startDetailActivity.putExtra("Movie_Detail", result);
                            startActivity(startDetailActivity);
                        }
                    });
                }else{
                    Log.i(TAG,"onResponse"+response);
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<MovieListing> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    private void updatemovies(Response<MovieListing> response){
        upcomingMovielist.addAll(response.body().getResults());
        rcAdapter.notifyDataSetChanged();
        rcAdapter.setClickListener(new MovieListAdapter.MovieItemClickListener() {
            @Override
            public void onClick(Result result) {
                startActivity(MovieViewActivity.getLaunchIntent(MainActivity.this,result));
            }
        });
    }
}
