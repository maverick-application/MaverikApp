package com.example.maverikapp.api;

import com.example.maverikapp.data_models.AuthenticationServerRequest;
import com.example.maverikapp.data_models.AuthenticationServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST(" ")
    Call<AuthenticationServerResponse> operation(@Body AuthenticationServerRequest request);
}
