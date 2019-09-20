package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayPostCollegeDetails {

    @SerializedName("college_id")
    @Expose
    private String college_id;

    @SerializedName("college_name")
    @Expose
    private String college_name;

    @SerializedName("college_img")
    @Expose
    private String college_img;

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getCollege_img() {
        return college_img;
    }

    public void setCollege_img(String college_img) {
        this.college_img = college_img;
    }

}
