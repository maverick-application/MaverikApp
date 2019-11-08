package com.example.maverikapp.api;

import com.example.maverikapp.pojo_response.auth.AuthenticationResponse;
import com.example.maverikapp.pojo_response.events.DisplayEventsResponse;
import com.example.maverikapp.pojo_response.events.EventsResponse;
import com.example.maverikapp.pojo_response.posts.PostResponse;
import com.example.maverikapp.pojo_response.posts.DisplayPostResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("auth/register.php")
    Call<AuthenticationResponse> userSignUp(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("soy") String soy,
            @Field("college") String college,
            @Field("level") String level,
            @Field("role") String role

    );

    @FormUrlEncoded
    @POST("auth/login.php")
    Call<AuthenticationResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );



    // This is used for getting all posts
    @Headers("Content-Type: application/json")
    @GET("posts/display_all_posts.php")
    Call<DisplayPostResponse> getPosts(@Header("USER") String USER);

    //This is used for liking the post
    @FormUrlEncoded
    @POST("posts/display_all_posts.php")
    Call<PostResponse> getLikePost(
            @Field("user_id") String user_id,
            @Field("post_id") String post_id

    );

    //This is used for the creation of the post

    @FormUrlEncoded
    @POST("posts/create_post.php")
    Call<PostResponse> createPost(
            @Field("p_title") String p_title,
            @Field("p_desc") String p_desc,
            @Field("p_user_id") String p_user_id,
            @Field("p_img_name") String p_img_name,
            @Field("p_img") String p_img
    );

    //This is used for the Editing of the post

    @FormUrlEncoded
    @POST("posts/edit_posts.php")
    Call<PostResponse> editPost(
      @Field("id") String post_id,
      @Field("title") String title,
      @Field("desc") String desc,
      @Field("img") String img,
      @Field("link") String link
    );

    //This is used for deleting the post
    @Headers("Content-Type: application/json")
    @GET("posts/edit_posts.php")
    Call<PostResponse> deletePost(
            @Header("POST") int post_id
    );

    //This is used for the Creating of the post
    @FormUrlEncoded
    @POST("events/create_event.php")
    Call<EventsResponse> createEvent(
            @Field("e_name") String name,
            @Field("e_desc") String desc,
            @Field("e_collage") String collage,
            @Field("e_budget") String budget,
            @Field("e_total_no") String total_no,
            @Field("e_status") String status,
            @Field("e_img_l") String img_l,
            @Field("e_img_n") String img_n,
            @Field("e_sponsor_name") String sponsor_name,
            @Field("e_sponsor_img") String sponsor_img,
            @Field("e_sponsor_link") String sponsor_link
    );

    //This is used for getting all the future pots of the events
    @FormUrlEncoded
    @POST("events/display_all_events.php")
    Call<DisplayEventsResponse> displayEvents(
      @Field("event_status") String status
    );


    @Headers("Content-Type: application/json")
    @GET("events/display_all_events.php")
    Call<EventsResponse> deleteEvent(
            @Header("EVENT") String eventId
    );


}
