package com.example.maverikapp.api;

import com.example.maverikapp.data_models.AuthenticationServerRequest;
import com.example.maverikapp.data_models.AuthenticationServerResponse;
import com.example.maverikapp.data_models.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST(" ")
    Call<AuthenticationServerResponse> operation(@Body AuthenticationServerRequest request);

    @GET("/posts/display_all_post.php")
    Call<Post> getPosts();
}
