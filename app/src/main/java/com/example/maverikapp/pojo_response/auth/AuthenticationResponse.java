package com.example.maverikapp.pojo_response.auth;

public class AuthenticationResponse {
    private int result;
    private String message;
    private UserDetailsResponse userDetailsResponse;

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public UserDetailsResponse getUserDetailsResponse() {
        return userDetailsResponse;
    }
}
