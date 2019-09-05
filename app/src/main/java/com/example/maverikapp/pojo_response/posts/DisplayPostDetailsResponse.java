package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayPostDetailsResponse {

    @SerializedName("p_id")
    @Expose
    private String p_id;

    @SerializedName("p_title")
    @Expose
    private String p_title;

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

    @SerializedName("p_like_status")
    @Expose
    private String p_like_status;

    @SerializedName("p_user_id")
    @Expose
    private String p_user_id;


    @SerializedName("p_college")
    @Expose
    private DisplayPostCollegeDetails p_college;


    public String getP_likes() {
        return p_likes;
    }

    public String getP_like_status() {
        return p_like_status;
    }

    public void setP_likes(String p_likes) {
        this.p_likes = p_likes;
    }

    public void setP_like_status(String p_like_status) {
        this.p_like_status = p_like_status;
    }
    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_title() {
        return p_title;
    }

    public void setP_title(String p_title) {
        this.p_title = p_title;
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

    public String getP_user_id() {
        return p_user_id;
    }

    public DisplayPostCollegeDetails getP_college() {
        return p_college;
    }

    public void setP_college(DisplayPostCollegeDetails p_college) {
        this.p_college = p_college;
    }

}
