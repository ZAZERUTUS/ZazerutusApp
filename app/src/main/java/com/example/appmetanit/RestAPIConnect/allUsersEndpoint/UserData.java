package com.example.appmetanit.RestAPIConnect.allUsersEndpoint;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("id")
    private int id;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }


//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
