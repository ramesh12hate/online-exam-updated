package com.embibe.iibnanded.network.api.login;

import com.embibe.iibnanded.network.utils.BaseResponse;
import com.embibe.iibnanded.network.utils.IResponsePublisher;
import com.vmware.iot.smart_interaction_provisioning_android.network.api.getEventSource.IValidateCredentials;

import retrofit2.Retrofit;

/**
 * Created by IoT-Engg team on 11/06/18.
 */
public class ValidateCredentials extends BaseResponse implements IValidateCredentials {
    private IValidateCredentials.IValidateCredentialsAPI mValidateCredentials;
    private IResponsePublisher mResponsePublisher;
    private String mUsername;
    private String mPassword;

    public ValidateCredentials(Retrofit retrofit, String username, String password, IResponsePublisher publisher) {
        mResponsePublisher = publisher;
        mValidateCredentials = retrofit.create(IValidateCredentials.IValidateCredentialsAPI.class);
        this.mUsername = username;
        this.mPassword = password;
    }

    @Override
    public void validateCredentialsAPI() {
//        Call<LoginModel> call = mValidateCredentials.validateCredentialsAPI(mUsername, mPassword);
//        call.enqueue(new AbstractCallback<LoginModel>(RequestTypes.LOGIN, mResponsePublisher));
    }
}
