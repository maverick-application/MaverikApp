package com.example.maverikapp.data_models;


    public class User {

        private String name;
        private String email;
        private String user_id;
        private String password;
        private String old_password;
        private String new_password;
        private String gender;
        private String college;
        private String role;
        private String year;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }



        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setOld_password(String old_password) {
            this.old_password = old_password;
        }

        public void setNew_password(String new_password) {
            this.new_password = new_password;
        }
    }
