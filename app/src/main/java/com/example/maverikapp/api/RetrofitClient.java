package com.example.maverikapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://192.168.43.118/Maverick/public/";
    private static RetrofitClient mInstance;
    private Retrofit mRetrofit;

    private RetrofitClient(){


        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance  = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return mRetrofit.create(Api.class);
    }
}
