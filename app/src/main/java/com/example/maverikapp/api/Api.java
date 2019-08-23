package com.example.maverikapp.api;

import com.example.maverikapp.data_models.AuthenticationServerRequest;
import com.example.maverikapp.data_models.PostLikeModel;
import com.example.maverikapp.pojo_response.AuthenticationServerResponse;
import com.example.maverikapp.data_models.CreatePostModel;
import com.example.maverikapp.data_models.DisplayPost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @POST(" ")
    Call<AuthenticationServerResponse> operation(@Body AuthenticationServerRequest request);

    @Headers("Content-Type: application/json")
    @GET("/posts/display_all_post.php")
    Call<DisplayPost> getPosts(@Header("userid") String userid);

    @FormUrlEncoded
    @POST("/posts/display_all_post.php")
    Call<PostLikeModel> getLikePost(
            @Field("user_id") String user_id,
            @Field("post_id") String post_id

    );

    @FormUrlEncoded
    @POST("/posts/create_post.php")
    Call<CreatePostModel> createPost(@Field("p_name") String p_name,
                                     @Field("p_desc") String p_desc,
                                     @Field("p_user_id") String p_user_id,
                                     @Field("p_img_name") String p_img_name,
                                     @Field("p_img") String p_img
                                );
}
