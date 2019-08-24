package com.example.maverikapp.data_models;

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


    public String getMessage() {
        return message;
    }
}
