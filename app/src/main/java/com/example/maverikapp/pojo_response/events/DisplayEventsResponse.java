package com.example.maverikapp.pojo_response.events;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DisplayEventsResponse {

    @SerializedName("result")
    @Expose
    private int result;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("total_no")
    @Expose
    private int total_no;

    @SerializedName("events")
    @Expose
    private List<DisplayEventsDetailResponse> events;



    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal_no() {
        return total_no;
    }

    public void setTotal_no(int total_no) {
        this.total_no = total_no;
    }

    public List<DisplayEventsDetailResponse> getEvents() {
        return events;
    }

    public void setEvents(List<DisplayEventsDetailResponse> events) {
        this.events = events;
    }
}
