package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.SerializedName;

public class CreatePostResponse {

    @SerializedName("STATUS")
    private int STATUS;

    @SerializedName("MESSAGE")
    private String MESSAGE;

    public int getSTATUS() {
        return STATUS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }
}
