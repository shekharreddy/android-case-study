package com.target.dealbrowserpoc.dealbrowser.utils;


import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 *  Item decorator to show space between List items or Grid items
 */

public class RecyclerViewListDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private static final int VIEW_TYPE_LIST = 0;
    private static final int VIEW_TYPE_GRID = 1;
    private int currentViewType;

    public RecyclerViewListDecoration(int space, int viewType) {
        this.space = space;
        currentViewType = viewType;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(currentViewType == VIEW_TYPE_LIST) {
            outRect.bottom = space;
        } else {
            outRect.bottom = space;
            outRect.left = space;
            outRect.right = space;
            outRect.top = space;
        }
    }
}