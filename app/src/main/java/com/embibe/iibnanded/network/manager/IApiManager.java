package com.embibe.iibnanded.network.manager;


import com.embibe.iibnanded.network.utils.IResponsePublisher;
import com.embibe.iibnanded.network.utils.RequestTypes;

/**
 * Created by IoT-Engg team on 11/06/18.
 */
public interface IApiManager {
    /**
     * Register response publisher to get the callback of network request.
     *
     * @param responsePublisher - response publisher
     */
    void registerResponseObserver(IResponsePublisher responsePublisher);

    /**
     * Unregister response publisher.
     *
     * @param responsePublisher - responsePublisher
     */
    void unregisterResponseObserver(IResponsePublisher responsePublisher);

    /**
     * Sets the base url used in retrofit client.
     * This could be useful if base url changes for few network calls.
     *
     * @param baseUrl - base url
     */
    void setBaseUrl(String baseUrl);

    /**
     * Unregister all response publisher
     */
    void unregisterAllResponseObserver();

    public void getDashboardInfo(int studentId);
    public void validateCredentials(String userName, String password);
}
