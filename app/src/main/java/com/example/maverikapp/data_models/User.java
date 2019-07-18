package com.example.maverikapp.data_models;

public class User {

    private int id;
    private String email,name,college,year,role,gender;

    public User(int id, String email, String name, String college, String year, String role, String gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.college = college;
        this.year = year;
        this.role = role;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCollege() {
        return college;
    }

    public String getYear() {
        return year;
    }

    public String getRole() {
        return role;
    }

    public String getGender() {
        return gender;
    }


}
