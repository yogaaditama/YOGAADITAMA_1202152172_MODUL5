package com.example.asusx453sa.YOGAADITAMA_1202152172_MODUL5;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by asusx453sa on 25/03/18.
 */

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {
    RecyclerAdapter adapter;

    public SwipeHelper(int dragDirs, int swipeDirs){
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(RecyclerAdapter adapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.dismissData(viewHolder.getAdapterPosition());
    }
}
