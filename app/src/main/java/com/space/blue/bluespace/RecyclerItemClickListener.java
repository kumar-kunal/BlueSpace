package com.space.blue.bluespace;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private RecyclerItemClickListener.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener listener){
        mListener =  listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }


        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        }
        return false;
    }
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {}
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
}
