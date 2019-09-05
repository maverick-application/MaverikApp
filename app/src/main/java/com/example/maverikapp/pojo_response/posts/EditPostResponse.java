package com.example.maverikapp.pojo_response.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditPostResponse {

    /*
    This Java Class is used to record the response after editing of the post
     */

    @SerializedName("result")
    @Expose
    private int result;


    @SerializedName("message")
    @Expose
    private String message;

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

}
