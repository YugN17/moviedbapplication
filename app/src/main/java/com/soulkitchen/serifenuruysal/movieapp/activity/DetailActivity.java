package com.soulkitchen.serifenuruysal.movieapp.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.soulkitchen.serifenuruysal.movieapp.R;
import com.soulkitchen.serifenuruysal.movieapp.data.models.Movie;
import com.soulkitchen.serifenuruysal.movieapp.databinding.MovieDetailActivityBinding;
import com.soulkitchen.serifenuruysal.movieapp.viewModel.MovieViewModel;

public class DetailActivity extends AppCompatActivity {
    public static final String MOVIE = "MOVIE";
    public MovieDetailActivityBinding binding;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (getIntent() != null) {
            movie = getIntent().getParcelableExtra(MOVIE);
            toolbar.setTitle(movie.getTitle());
        }
        binding.setViewModel(new MovieViewModel(movie));


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
