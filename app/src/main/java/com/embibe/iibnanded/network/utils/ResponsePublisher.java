package com.embibe.iibnanded.network.utils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by IoT-Engg team on 11/06/18.
 */
public class ResponsePublisher<T extends BaseResponse> implements IResponsePublisher<T> {

    private CopyOnWriteArrayList<IResponsePublisher> mResponsePublisher;

    public ResponsePublisher() {
        mResponsePublisher = new CopyOnWriteArrayList<IResponsePublisher>();
    }

    @Override
    public void onSuccess(int requestType, Call call, Response response) {
        for (IResponsePublisher publisher : mResponsePublisher) {
            publisher.onSuccess(requestType, call, response);
        }
    }

    @Override
    public void onError(@RequestTypes.Interface int requestType, Call call, Throwable error) {
        for (IResponsePublisher publisher : mResponsePublisher) {
            publisher.onError(requestType, call, error);
        }
    }

    /**
     * Register response publisher to get the callback of network request.
     *
     * @param responsePublisher - response publisher
     */
    public void registerResponsePublisher(IResponsePublisher responsePublisher) {
        if (!mResponsePublisher.contains(responsePublisher)) {
            mResponsePublisher.add(responsePublisher);
        }
    }

    /**
     * Unregister response publisher.
     *
     * @param responsePublisher - responsePublisher
     */
    public void unregisterResponsePublisher(IResponsePublisher responsePublisher) {
        if (mResponsePublisher != null) {
            mResponsePublisher.remove(responsePublisher);
        }
    }

    /**
     * Unregister all response publisher
     */
    public void unregisterAllResponsePublisher() {
        mResponsePublisher.clear();
    }

    @Override
    public void onSuccess(int requestType, @NotNull Call<?> call, @NotNull T responseBean) {
        for (IResponsePublisher publisher : mResponsePublisher) {
            publisher.onSuccess(requestType, call, responseBean);
        }
    }

    @Override
    public void onUnauthorised(int requestType, @NotNull Call<?> call, @NotNull T responseBean) {
        for (IResponsePublisher publisher : mResponsePublisher) {
            publisher.onUnauthorised(requestType, call, responseBean);
        }
    }
}
