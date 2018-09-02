package com.example.sugamparajuli.movieflix;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sugamparajuli.movieflix.Rest.response.Result;


import java.util.ArrayList;

/**
 * Created by ashish on 7/12/2016.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {
    private ArrayList<Result> movieListingDetailsArrayList;
    private Context context;
    private MovieItemClickListener movieItemClickListener;

    public MovieListAdapter(Context context, ArrayList<Result> listView) {
        this.movieListingDetailsArrayList = listView;
        this.context = context;
    }

    public void setClickListener(MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_movie, parent, false);
        MovieListViewHolder rcv = new MovieListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, final int position) {
        holder.tvMovieTitle.setText(movieListingDetailsArrayList.get(position).getOriginalTitle());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185//"+movieListingDetailsArrayList.get(position).getPosterPath())
                .into(holder.ivMovieImage);
        holder.tvMovieRatings.setText("" + movieListingDetailsArrayList.get(position).getVoteAverage());
        holder.rlMovieContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movieItemClickListener != null) {
                    movieItemClickListener.onClick(movieListingDetailsArrayList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.movieListingDetailsArrayList.size();
    }


    public static class MovieListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovieTitle, tvMovieRatings, tvCategory;
        private ImageView ivMovieImage;
        private CardView rlMovieContainer;

        public MovieListViewHolder(View itemView) {
            super(itemView);

            tvMovieTitle = (TextView) itemView.findViewById(R.id.tv_movie_name);
            tvMovieRatings = (TextView) itemView.findViewById(R.id.tv_movie_rating);
            ivMovieImage = (ImageView) itemView.findViewById(R.id.iv_movie_poster);
            rlMovieContainer = (CardView) itemView.findViewById(R.id.cv_movie);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_movie_category);

        }
    }

    public interface MovieItemClickListener {
        void onClick(Result result);
    }
}
