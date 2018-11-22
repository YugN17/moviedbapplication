package com.soulkitchen.serifenuruysal.movieapp.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.soulkitchen.serifenuruysal.movieapp.R;

/**
 * Created by S.Nur Uysal on 21.11.2018.
 */
public class CenterGridDecor extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mSpacing;

    public CenterGridDecor(int spanCount, int spacing) {
        this.mSpanCount = spanCount;
        this.mSpacing = spacing;
    }

    public CenterGridDecor(Context context, int spanCount) {
        this.mSpanCount = spanCount;
        this.mSpacing =  context.getResources().getDimensionPixelOffset(R.dimen.space_standart);
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % mSpanCount; // item column

        outRect.left = mSpacing - column * mSpacing / mSpanCount; // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * mSpacing / mSpanCount; // (column + 1) * ((1f / spanCount) * spacing)

        if (position < mSpanCount) { // top edge
            outRect.top = mSpacing;
        }
        outRect.bottom = mSpacing; // item bottom
    }
}
