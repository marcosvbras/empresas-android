package com.marcosvbras.empresas.listeners;

import android.view.View;

public interface RecyclerViewTouchListener {
    void onItemClick(View view, int position);
    void onLongItemClick(View view, int position);
}
