package com.example.maverikapp.pojo_response.auth;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("soy")// soy : Student of Year
    @Expose
    private String soy;

    @SerializedName("college")
    @Expose
    private String college;

    @SerializedName("level")
    @Expose
    private String level;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("member_id")
    @Expose
    private String member_id;

    public String getName() {
        return name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getSoy() {
        return soy;
    }

    public String getCollege() {
        return college;
    }

    public String getLevel() {
        return level;
    }

    public String getRole() {
        return role;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setSoy(String soy) {
        this.soy = soy;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setRole(String role) {
        this.role = role;
    }



}
