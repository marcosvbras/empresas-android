package com.marcosvbras.empresas.business.model

import com.marcosvbras.empresas.business.api.refrofit.APIService
import com.marcosvbras.empresas.business.api.responses.LoginBody
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserModel {

    fun login(loginBody: LoginBody) : Observable<Void>? {
        return APIService.getService()?.login(loginBody)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

}