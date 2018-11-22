package com.soulkitchen.serifenuruysal.movieapp.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soulkitchen.serifenuruysal.movieapp.R;
import com.soulkitchen.serifenuruysal.movieapp.activity.DetailActivity;
import com.soulkitchen.serifenuruysal.movieapp.adapter.MovieRecyclerAdapter;
import com.soulkitchen.serifenuruysal.movieapp.data.models.Movie;
import com.soulkitchen.serifenuruysal.movieapp.databinding.MovieListFragmentBinding;
import com.soulkitchen.serifenuruysal.movieapp.viewModel.MovieListViewModel;

import static com.soulkitchen.serifenuruysal.movieapp.activity.DetailActivity.MOVIE;

public class MovieFragment extends BaseFragment implements MovieRecyclerAdapter.OnItemClickListener {
    public MovieListFragmentBinding binding;
    private static final String MOVIE_TYPE = "MOVIE_TYPE";
    public static final int NOWPLAYING = 1;
    public static final int UPCOMING = 2;
    public static final int POPULAR = 3;
    int pageNumber = 1;
     boolean loading = true;
     int pastVisiblesItems, visibleItemCount, totalItemCount;

    @IntDef({NOWPLAYING, UPCOMING, POPULAR})
    @interface MovieType {
    }

    public static MovieFragment newInstance(@MovieType int movieType) {
        Bundle args = new Bundle();
        args.putInt(MOVIE_TYPE, movieType);

        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false);
        MovieListViewModel viewModel = new MovieListViewModel(getContext(), this);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.getViewModel().fillData(getArguments().getInt(MOVIE_TYPE),pageNumber);
        binding.rvMostPopular.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) //check for scroll down
                {
                    LinearLayoutManager mLayoutManager = binding.getViewModel().getLayoutManager();
                    visibleItemCount =mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                        {
                            loading = false;
//                            Log.v("...", "Last Item Wow ! "+pageNumber);
                            binding.getViewModel().fillData(getArguments().getInt(MOVIE_TYPE),pageNumber++);
                        }
                    }
            }
        });



    }


    @Override
    public void onItemClicked(Movie wrapper) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra(MOVIE, wrapper);
        startActivity(i);

    }


}
