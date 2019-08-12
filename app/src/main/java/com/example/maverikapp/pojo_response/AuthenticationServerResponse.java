package com.example.maverikapp.pojo_response;

import com.example.maverikapp.data_models.User;

public class AuthenticationServerResponse {
    private String result;
    private String message;
    private User user;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
