package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatePostResponse {

    @SerializedName("p_title")
    @Expose
    private String p_name;

    @SerializedName("p_desc")
    @Expose
    private String p_desc;

    @SerializedName("p_img")
    @Expose
    private String p_img;

    @SerializedName("p_user_id")
    @Expose
    private String p_user_id;


    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private int result;



    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }






}
