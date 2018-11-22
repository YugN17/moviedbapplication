package com.soulkitchen.serifenuruysal.movieapp.viewModel;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

import static com.soulkitchen.serifenuruysal.movieapp.util.Constants.IMAGE_URL;

/**
 * Created by S.Nur Uysal on 21.11.2018.
 */
public abstract class BaseViewModel extends ViewModel {
    /**
     * download image from url and set imageView. This method is in use by xml
     *
     * @param view imageView
     * @param url  image url
     */
    @BindingAdapter({"imageUrl"})
    public static void setImage(ImageView view, String url) {
        RequestManager requestManager = Glide.with(view.getContext());
       String appendedUrl=IMAGE_URL+url;
        RequestBuilder requestBuilder = requestManager.load(appendedUrl);
        requestBuilder.into(view);
    }

    @BindingAdapter({"backgroundResource"})
    public static void setBackgroundResource(View view, int resId) {
        view.setBackgroundResource(resId);
    }


}
