package com.example.maverikapp.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostDetails {

    @SerializedName("p_id")
    @Expose
    private String p_id;

    @SerializedName("p_name")
    @Expose
    private String p_name;

    @SerializedName("p_desc")
    @Expose
    private String p_desc;

    @SerializedName("p_img")
    @Expose
    private String p_img;

    @SerializedName("p_likes")
    @Expose
    private String p_likes;

    @SerializedName("p_time")
    @Expose
    private String p_time;


    public String getP_likes() {
        return p_likes;
    }

    public void setP_likes(String p_likes) {
        this.p_likes = p_likes;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public String getP_img() {
        return p_img;
    }

    public void setP_img(String p_img) {
        this.p_img = p_img;
    }

    public String getP_time() {
        return p_time;
    }

    public void setP_time(String p_time) {
        this.p_time = p_time;
    }




}
