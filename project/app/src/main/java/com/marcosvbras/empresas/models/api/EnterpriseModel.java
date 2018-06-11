package com.marcosvbras.empresas.models.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.marcosvbras.empresas.views.utils.Constants;
import com.marcosvbras.empresas.models.domain.Enterprise;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterpriseModel {

    public interface OnRequestEnterpriseListener extends RequestCommons {
        void onSingleEnterpriseReceived(Enterprise enterprise);
        void onEnterpriseListReceived(List<Enterprise> enterpriseList);
        void onEnterpriseRequestFailure(String message);
        void onRequestError(String message);
        void onRequestStarted();
        void onRequestFinished();
    }

    private OnRequestEnterpriseListener requestListener;

    public EnterpriseModel(OnRequestEnterpriseListener requestListener) {
        this.requestListener = requestListener;
    }

    public void requestEnterprises(String query) {
        requestListener.onRequestStarted();
        Call<EnterpriseResponse> call;

        if(TextUtils.isEmpty(query) || query == null)
            call = new RetrofitConfig(UserModel.getCredentials()).getEnterpriseService().getAllEnterprises();
        else
            call = new RetrofitConfig(UserModel.getCredentials()).getEnterpriseService().getEnterprises(query);

        call.enqueue(enterpriseListCallback());
    }

    public void requestSingleEnterprise(@NonNull int id) {
        requestListener.onRequestStarted();
        Call<EnterpriseDetailResponse> call = new RetrofitConfig(
                UserModel.getCredentials()
        ).getEnterpriseService().getEnterprise(id);
        call.enqueue(singleEnterpriseCallback());
    }

    private Callback<EnterpriseResponse> enterpriseListCallback() {
        return new Callback<EnterpriseResponse>() {
            @Override
            public void onResponse(Call<EnterpriseResponse> call, Response<EnterpriseResponse> response) {
                requestListener.onRequestFinished();

                if (response.code() == Constants.UNAUTHORIZED)
                    requestListener.onUnauthorizedRequest();
                else if (response.code() == Constants.SERVER_ERROR)
                    requestListener.onServerError();
                else if (response.code() == Constants.OK && response.body() != null)
                    requestListener.onEnterpriseListReceived(response.body().getListEnterprises());
                else
                    requestListener.onRequestError(response.message());
            }

            @Override
            public void onFailure(Call<EnterpriseResponse> call, Throwable throwable) {
                requestListener.onRequestFinished();
                requestListener.onEnterpriseRequestFailure(throwable.getMessage());
            }
        };
    }

    private Callback<EnterpriseDetailResponse> singleEnterpriseCallback() {
        return new Callback<EnterpriseDetailResponse>() {
            @Override
            public void onResponse(Call<EnterpriseDetailResponse> call, Response<EnterpriseDetailResponse> response) {
                requestListener.onRequestFinished();

                if (response.code() == 401)
                    requestListener.onUnauthorizedRequest();
                else if(response.isSuccessful() && response.body() != null)
                    requestListener.onSingleEnterpriseReceived(response.body().getEnterprise());
                else
                    requestListener.onRequestError(response.message());
            }

            @Override
            public void onFailure(Call<EnterpriseDetailResponse> call, Throwable throwable) {
                requestListener.onRequestFinished();
                requestListener.onEnterpriseRequestFailure(throwable.getMessage());
            }
        };
    }
}
