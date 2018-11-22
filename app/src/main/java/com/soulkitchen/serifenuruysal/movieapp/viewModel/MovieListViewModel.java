package com.soulkitchen.serifenuruysal.movieapp.viewModel;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paginate.Paginate;
import com.soulkitchen.serifenuruysal.movieapp.adapter.MovieRecyclerAdapter;
import com.soulkitchen.serifenuruysal.movieapp.api.ApiClient;
import com.soulkitchen.serifenuruysal.movieapp.data.models.Movie;
import com.soulkitchen.serifenuruysal.movieapp.data.models.MoviesResponse;
import com.soulkitchen.serifenuruysal.movieapp.util.CenterGridDecor;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.soulkitchen.serifenuruysal.movieapp.fragment.MovieFragment.NOWPLAYING;
import static com.soulkitchen.serifenuruysal.movieapp.fragment.MovieFragment.POPULAR;
import static com.soulkitchen.serifenuruysal.movieapp.fragment.MovieFragment.UPCOMING;

/**
 * Created by S.Nur Uysal on 21.11.2018.
 */
public class MovieListViewModel extends RecyclerViewModel {
    private ArrayList<Movie> mMovieLists;
    private Context mContext;
    private MovieRecyclerAdapter mAdapter;
    private MovieRecyclerAdapter.OnItemClickListener mListener;
    private GridLayoutManager mLayoutManager;


    public  MovieListViewModel(Context context,MovieRecyclerAdapter.OnItemClickListener listener) {

        mContext=context;
        mListener=listener;
        mMovieLists = new ArrayList<>();
        mLayoutManager = new GridLayoutManager(mContext, getColoumnCount(mContext));
    }

    public void fillData(int movieType,int page) {

        if (movieType == NOWPLAYING) {
            callNowPlaying(page);
        } else if (movieType == UPCOMING) {
            callUpcoming(page);
        } else if (movieType == POPULAR) {
            callPopular(page);
        }
    }

    @Override
    public RecyclerView.Adapter getAdapter(@LayoutRes int layoutId) {

        mAdapter =  new MovieRecyclerAdapter(mContext, mMovieLists, mListener);

        return mAdapter;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public RecyclerView.ItemDecoration getDecoration() {
        return new CenterGridDecor(mContext, getColoumnCount(mContext));
    }

    private void callNowPlaying(int page) {
        Call<MoviesResponse> movieList = ApiClient.getInstance().getNowPlayingMovies(page);
        movieList.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    MoviesResponse data = response.body();

                    fillAdapter(data.getMovies());

                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }

    private void fillAdapter(ArrayList<Movie> movies) {
        if (movies!=null&& movies.size()>0){
//            mMovieLists.clear();
            mMovieLists.addAll(movies);
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }

    }

    private void callUpcoming(int page) {

        ApiClient.getInstance().getPopularMovies(page).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    MoviesResponse data = response.body();
                    fillAdapter(data.getMovies());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }

    private void callPopular(int page) {
        ApiClient.getInstance().getUpcomingMovies(page).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    MoviesResponse data = response.body();
                    fillAdapter(data.getMovies());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }
}
