package com.marcosvbras.empresas.utils

import android.databinding.BaseObservable

class ErrorObservable() : BaseObservable() {

    constructor(error: String) : this() {
        this.error = error
        notifyChange()
    }

    constructor(error: Int) : this() {
        this.errorInt = error
        notifyChange()
    }

    var error: String? = null
        set(value) {
            field = value
            notifyChange()
        }

    var errorInt: Int? = null
        set(value) {
            field = value
            notifyChange()
        }

    fun clear() {
        error = null
        errorInt = null
        notifyChange()
    }

    fun hasError() : Boolean = error != null || errorInt != null
}