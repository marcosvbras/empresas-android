package com.marcosvbras.empresas.views.listeners;

import android.view.View;

public interface OnRecyclerViewTouchListener {
    void onItemClick(View view, int position);
    void onLongItemClick(View view, int position);
}
