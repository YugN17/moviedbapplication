package com.soulkitchen.serifenuruysal.movieapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.soulkitchen.serifenuruysal.movieapp.databinding.MovieRowBinding;
import com.soulkitchen.serifenuruysal.movieapp.R;
import com.soulkitchen.serifenuruysal.movieapp.data.models.Movie;
import com.soulkitchen.serifenuruysal.movieapp.viewModel.MovieViewModel;

import java.util.ArrayList;

/**
 * Created by S.Nur Uysal on 21.11.2018.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Movie> mItems;
    private OnItemClickListener mListener;

    public MovieRecyclerAdapter(Context context, ArrayList<Movie> items, OnItemClickListener listener) {
        mContext = context;
        mItems = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.row_movie, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mItems.get(position);
        holder.setViewModel(new MovieViewModel(movie));


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MovieRowBinding mBinding;

        public ViewHolder(MovieRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(mItems.get(getAdapterPosition()));
                }
            });
        }

        public void setViewModel(MovieViewModel viewModel) {
            mBinding.setViewModel(viewModel);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Movie wrapper);
    }

}
