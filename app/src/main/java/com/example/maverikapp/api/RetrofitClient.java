package com.example.maverikapp.api;

import com.example.maverikapp.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private Retrofit mRetrofit;

    private RetrofitClient(){


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
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
