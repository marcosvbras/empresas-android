package com.marcosvbras.empresas.views.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerViewTouchConfig implements RecyclerView.OnItemTouchListener {

    private RecyclerView recyclerView;
    private OnRecyclerViewTouchListener onRecyclerViewTouchListener;
    private GestureDetector gestureDetector;

    public RecyclerViewTouchConfig(Context context, RecyclerView recyclerView, OnRecyclerViewTouchListener onRecyclerViewTouchListener) {
        this.recyclerView = recyclerView;
        this.onRecyclerViewTouchListener = onRecyclerViewTouchListener;
        gestureDetector = new GestureDetector(context, onSimpleGestureListener());
    }

    private GestureDetector.SimpleOnGestureListener onSimpleGestureListener() {
        return new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                View clickedView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(clickedView != null && onRecyclerViewTouchListener != null)
                    onRecyclerViewTouchListener.onItemClick(clickedView, recyclerView.getChildAdapterPosition(clickedView));

                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                View clickedView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(clickedView != null && onRecyclerViewTouchListener != null)
                    onRecyclerViewTouchListener.onLongItemClick(clickedView, recyclerView.getChildAdapterPosition(clickedView));
            }
        };
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recycleView, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
}
