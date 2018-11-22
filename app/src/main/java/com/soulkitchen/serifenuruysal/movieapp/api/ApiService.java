package com.soulkitchen.serifenuruysal.movieapp.api;

import com.soulkitchen.serifenuruysal.movieapp.data.models.Movie;
import com.soulkitchen.serifenuruysal.movieapp.data.models.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by S.Nur Uysal on 20.11.2018.
 */
public interface ApiService {

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("page") int page);
    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("page") int page);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") long id);

}
