package com.example.maverikapp.pojo_response.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayEventsDetailResponse {

    @SerializedName("e_id")
    @Expose
    private String e_id;

    @SerializedName("e_name")
    @Expose
    private String e_name;

    @SerializedName("e_desc")
    @Expose
    private String e_desc;

    @SerializedName("e_date")
    @Expose
    private String e_date;

    @SerializedName("e_college")
    @Expose
    private String e_college;

    @SerializedName("e_budget")
    @Expose
    private String e_budget;

    @SerializedName("e_cost")
    @Expose
    private String e_cost;

    @SerializedName("e_capacity")
    @Expose
    private String e_capacity;

    @SerializedName("e_img_l")
    @Expose
    private String e_img_l;

    @SerializedName("e_img_n")
    @Expose
    private String e_img_n;

    @SerializedName("e_sponsor_name")
    @Expose
    private String e_sponsor_name;

    @SerializedName("e_sponsor_img")
    @Expose
    private String e_sponsor_img;

    @SerializedName("e_sponsor_link")
    @Expose
    private String e_sponsor_link;


    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_desc() {
        return e_desc;
    }

    public void setE_desc(String e_desc) {
        this.e_desc = e_desc;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public String getE_college() {
        return e_college;
    }

    public void setE_college(String e_college) {
        this.e_college = e_college;
    }

    public String getE_budget() {
        return e_budget;
    }

    public void setE_budget(String e_budget) {
        this.e_budget = e_budget;
    }

    public String getE_cost() {
        return e_cost;
    }

    public void setE_cost(String e_cost) {
        this.e_cost = e_cost;
    }

    public String getE_capacity() {
        return e_capacity;
    }

    public void setE_capacity(String e_capacity) {
        this.e_capacity = e_capacity;
    }

    public String getE_img_l() {
        return e_img_l;
    }

    public void setE_img_l(String e_img_l) {
        this.e_img_l = e_img_l;
    }

    public String getE_img_n() {
        return e_img_n;
    }

    public void setE_img_n(String e_img_n) {
        this.e_img_n = e_img_n;
    }

    public String getE_sponsor_name() {
        return e_sponsor_name;
    }

    public void setE_sponsor_name(String e_sponsor_name) {
        this.e_sponsor_name = e_sponsor_name;
    }

    public String getE_sponsor_img() {
        return e_sponsor_img;
    }

    public void setE_sponsor_img(String e_sponsor_img) {
        this.e_sponsor_img = e_sponsor_img;
    }

    public String getE_sponsor_link() {
        return e_sponsor_link;
    }

    public void setE_sponsor_link(String e_sponsor_link) {
        this.e_sponsor_link = e_sponsor_link;
    }
}
