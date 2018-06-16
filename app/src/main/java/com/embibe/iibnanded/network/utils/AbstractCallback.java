package com.embibe.iibnanded.network.utils;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IoT-Engg team on 11/06/18.
 */
public class AbstractCallback<T extends BaseResponse> implements Callback {

    public static final String SOMETHING_WENT_WRONT_TEXT = "Something went wrong. Please try again";
    public static final int ERROR_CODE_SERVER_DOWN = 500;
    private IResponsePublisher mResponsePublisher;
    @RequestTypes.Interface
    private int mRequestType;

    public AbstractCallback(@RequestTypes.Interface int requestType, IResponsePublisher responsePublisher) {
        mResponsePublisher = responsePublisher;
        mRequestType = requestType;
    }

    @Override
    public void onResponse(Call call, Response response) {
        int code = response.code();
        if (code >= 200 && code < 300) { //Successful
            handleSuccess(call, response);
        } else if (code == 401) { //Un-authorized
            mResponsePublisher.onUnauthorised(mRequestType, call, null);
        } else if (code >= 400 && code < 500) { //Client error
            mResponsePublisher.onError(mRequestType, call, new Throwable(getClientErrorMessage(response)));
        } else if (code >= 500 && code < 600) {//Server error
            mResponsePublisher.onError(mRequestType, call, new Throwable(getServerErrorMessage(code, response)));
        } else { //Unknown problem occurred
            mResponsePublisher.onError(mRequestType, call, new Throwable(getClientErrorMessage(response)));
        }
    }

    protected void handleSuccess(Call call, Response response) {
        if(mRequestType == RequestTypes.GET_DASHBOARD_INFO) {
            mResponsePublisher.onSuccess(mRequestType, call, response);
        }else {
            T baseResponse = (T) response.body();
            mResponsePublisher.onSuccess(mRequestType, call, baseResponse);
        }

    }

    @Override
    public void onFailure(Call call, Throwable throwable) {
        if (throwable instanceof IOException) {
            mResponsePublisher.onError(mRequestType, call, throwable);
        } else {
            mResponsePublisher.onError(mRequestType, call, throwable);
        }
    }

    private String getClientErrorMessage(Response response) {
        String errorMessage = SOMETHING_WENT_WRONT_TEXT;
        String resp = null;
        if (response != null && response.raw() != null) {
            errorMessage = response.raw().message();
            try {
                resp = response.errorBody().string();
                JSONObject jsonObject = new JSONObject(resp);
                errorMessage = jsonObject.getString("Message here");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return errorMessage;
    }

    private String getServerErrorMessage(int code, Response response) {
        String errorMessage = SOMETHING_WENT_WRONT_TEXT;
        if (code == ERROR_CODE_SERVER_DOWN) {
            errorMessage = "Server Down";
        }
        return errorMessage;
    }
}
