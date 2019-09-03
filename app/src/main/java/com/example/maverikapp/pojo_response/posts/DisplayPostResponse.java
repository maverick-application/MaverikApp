package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DisplayPostResponse {

    @SerializedName("USER")
    @Expose
    private String USER;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("total_posts")
    @Expose
    private String total_posts;

    @SerializedName("posts")
    @Expose
    private List<DisplayPostDetailsResponse> posts;







    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotal_posts() {
        return total_posts;
    }

    public void setTotal_posts(String total_posts) {
        this.total_posts = total_posts;
    }


    public List<DisplayPostDetailsResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<DisplayPostDetailsResponse> posts) {
        this.posts = posts;
    }



}
