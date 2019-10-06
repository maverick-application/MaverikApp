package com.example.maverikapp.pojo_response.utility;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollegeDetails {

    @SerializedName("college_id")
    @Expose
    private String college_id;

    @SerializedName("college_username")
    @Expose
    private String college_username;

    @SerializedName("college_img")
    @Expose
    private String college_img;

    @SerializedName("college_address")
    @Expose
    private String college_address;

    @SerializedName("college_link")
    @Expose
    private String college_link;

    @SerializedName("college_name")
    @Expose
    private String college_name;



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

    public String getCollege_username() {
        return college_username;
    }

    public void setCollege_username(String college_username) {
        this.college_username = college_username;
    }

    public String getCollege_address() {
        return college_address;
    }

    public void setCollege_address(String college_address) {
        this.college_address = college_address;
    }

    public String getCollege_link() {
        return college_link;
    }

    public void setCollege_link(String college_link) {
        this.college_link = college_link;
    }
}
