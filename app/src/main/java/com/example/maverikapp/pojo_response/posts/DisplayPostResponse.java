package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DisplayPostResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalPosts")
    @Expose
    private String totalPosts;

    @SerializedName("posts")
    @Expose
    private List<DisplayPostDetailsResponse> posts;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(String totalPosts) {
        this.totalPosts = totalPosts;
    }

    public List<DisplayPostDetailsResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<DisplayPostDetailsResponse> posts) {
        this.posts = posts;
    }



}
