package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostLikeModel {

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("post_id")
    @Expose
    private String post_id;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private int result;

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
