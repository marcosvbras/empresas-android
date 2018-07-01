package com.marcosvbras.empresas.views.listeners

import android.os.Bundle

interface BaseViewModelCallback {
    fun showErrorDialog(message: String)
    fun showErrorDialog(message: Int)
    fun openActivity(activity: Class<*>, finishCurrentActivity: Boolean)
    fun openActivity(activity: Class<*>, bundle: Bundle, finishCurrentActivity: Boolean)
    fun setToolbar(viewId: Int, title: String, displayHomeAsUpEnabled: Boolean)
    fun setToolbarTitle(title: String)
}