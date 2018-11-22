package com.soulkitchen.serifenuruysal.movieapp.viewModel;

import com.soulkitchen.serifenuruysal.movieapp.data.models.Genre;
import com.soulkitchen.serifenuruysal.movieapp.data.models.Movie;

/**
 * Created by S.Nur Uysal on 21.11.2018.
 */
public  class MovieViewModel extends BaseViewModel{
    private Movie mItem;

    public MovieViewModel(Movie item) {
        mItem = item;
    }

    public String getTitle() {
        return mItem.getTitle();
    }

    public String getOverview() {
        return mItem.getOverview();
    }

    public String getImageUrl() {
        return mItem.getPosterPath();
    }
    public String getBackdrop() {
        return mItem.getBackdropPath();
    }
    public String getId() {
        return mItem.getId()+"";
    }
    public String getReleaseDate() {
        return mItem.getReleaseDate();
    }
    public String getOriginalLanguage() {
        return mItem.getOriginalLanguage()+"";
    }
    public String getVoteAverage() {
        return mItem.getVoteAverage()+"";
    }
    public String getVoteCount() {
        return mItem.getVoteCount()+"";
    }
    public String getRuntime() {
        return mItem.getRuntime()!=null?mItem.getRuntime()+"":"-";
    }
    public String getBackdropPath() {
        return mItem.getBackdropPath()+"";
    }
    public String getGenres() {
        StringBuilder genres=new StringBuilder();
        if (mItem.getGenres()!=null&&mItem.getGenres().size()>0){
            for (Genre genre:mItem.getGenres()) {
                genres.append(genre.getId()+" | ");
            }
        }
        return genres.toString();
    }
    public String getHomepage() {
        return mItem.getHomepage()+"";
    }
}
