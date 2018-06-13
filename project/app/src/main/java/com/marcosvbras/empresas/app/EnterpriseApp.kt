package com.marcosvbras.empresas.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.marcosvbras.empresas.views.utils.Constants

class EnterpriseApp : Application() {

    companion object {
        private var instance: EnterpriseApp? = null

        fun getApp(): EnterpriseApp = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private fun getPreferences(preferenceKey: String) : SharedPreferences {
        return getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)
    }

    fun writeCredentials(accessToken: String, client: String, uid: String) {
        val sharedPreferences = getPreferences(Constants.AUTH_PREF_KEY)
        val editor = sharedPreferences.edit()
        editor.putString(Constants.ACCESS_TOKEN_KEY, accessToken)
        editor.putString(Constants.CLIENT_KEY, client)
        editor.putString(Constants.UID_KEY, uid)
        editor.commit()
    }
}