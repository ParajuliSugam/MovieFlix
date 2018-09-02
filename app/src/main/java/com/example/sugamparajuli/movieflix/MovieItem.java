package com.example.sugamparajuli.movieflix;

public class MovieItem {
    private String movieName;
    private String category;
    private String Rating;
    private int movie_poster;

    public MovieItem() {
    }

    public MovieItem(String movieName, String category, String rating, int movie_poster) {
        this.movieName = movieName;
        this.category = category;
        this.Rating = rating;
        this.movie_poster = movie_poster;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getCategory() {
        return category;
    }

    public String getRating() {
        return Rating;
    }

    public int getMovie_poster() {
        return movie_poster;
    }
//
//    public void setMovieName(String movieName) {
//        this.movieName = movieName;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public void setRating(String rating) {
//        Rating = rating;
//    }
//
//    public void setMovie_poster(int movie_poster) {
//        this.movie_poster = movie_poster;
//    }
}
