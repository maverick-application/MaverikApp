package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatePostModel {

    @SerializedName("p_name")
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




    @SerializedName("MESSAGE")
    private String MESSAGE;

    @SerializedName("STATUS")
    private int STATUS;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public int getSTATUS() {
        return STATUS;
    }






}
