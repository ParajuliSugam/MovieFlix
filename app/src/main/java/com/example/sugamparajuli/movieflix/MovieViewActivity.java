package com.example.sugamparajuli.movieflix;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sugamparajuli.movieflix.Rest.response.Result;

public class MovieViewActivity extends AppCompatActivity {

    private static final String TAG = MovieViewActivity.class.getSimpleName();

    Toolbar toolbar;
    TextView tvToolbarTitle;
    ImageView ivToolBarIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_title);

        setSupportActionBar(toolbar);
        tvToolbarTitle.setText("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Result movieDetail = (Result) intent.getExtras().get("Movie_Detail");

        Log.i(TAG, "onCreate: " + movieDetail.getOriginalTitle());
        Log.i(TAG, "onCreate: " + movieDetail.getReleaseDate());
    }

    public static Intent getLaunchIntent(Context context, Result result) {
        Intent movieDetailActivityIntent = new Intent(context, MovieViewActivity.class);
        movieDetailActivityIntent.putExtra("Movie_Detail", result);
        return movieDetailActivityIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

}
