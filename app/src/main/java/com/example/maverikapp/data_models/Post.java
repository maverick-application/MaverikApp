package com.example.maverikapp.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalPosts")
    @Expose
    private String totalPosts;

    @SerializedName("posts")
    @Expose
    private List<PostDetails> posts;


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

    public List<PostDetails> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDetails> posts) {
        this.posts = posts;
    }



}
