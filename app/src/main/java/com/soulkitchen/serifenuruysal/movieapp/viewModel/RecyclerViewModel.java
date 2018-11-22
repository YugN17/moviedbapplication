package com.soulkitchen.serifenuruysal.movieapp.viewModel;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.soulkitchen.serifenuruysal.movieapp.R;
import com.soulkitchen.serifenuruysal.movieapp.util.Utils;

/**
 * Created by S.Nur Uysal on 21.11.2018.
 */
public abstract class RecyclerViewModel extends BaseViewModel{


    public abstract RecyclerView.Adapter getAdapter(@LayoutRes int layoutId);

    public abstract RecyclerView.LayoutManager getLayoutManager();

    /**
     * Add an RecyclerView.ItemDecoration to this RecyclerView. You can override this method and return your custom item decoraition
     *
     * @return your custom item decoration
     */
    public RecyclerView.ItemDecoration getDecoration() {
        return null;
    }

    @CallSuper
    public void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        if (view != null && adapter != null) {
            view.setAdapter(adapter);
        }
    }

    @CallSuper
    public void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        if (view != null && manager != null) {
            view.setLayoutManager(manager);
        }
    }

    @CallSuper
    public void setDecoration(RecyclerView view, RecyclerView.ItemDecoration decoration) {
        if (view != null && decoration != null) {
            view.addItemDecoration(decoration);
        }
    }


    /**
     * calculate span count for specific row width
     *
     * @return calculated span count with default value
     */
    public int getColoumnCount(Context context) {
        int space = context.getResources().getDimensionPixelOffset(R.dimen.space_standart);
        int rowWidth = context.getResources().getDimensionPixelOffset(R.dimen.movie_row_width);

        int spanCount = 1;
        boolean loop = true;
        int screenWidth = Utils.getScreenWidth(context);
        while (loop) {
            int sumColoumnWidth = screenWidth - ((spanCount + 1) * space);
            int newRW = (sumColoumnWidth / spanCount);

            if (newRW <= rowWidth) {
                loop = false;
            } else {
                spanCount = spanCount + 1;
            }
        }
        return spanCount;
    }
}
