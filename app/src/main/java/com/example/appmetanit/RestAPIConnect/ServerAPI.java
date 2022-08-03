package com.example.appmetanit.RestAPIConnect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerAPI {
    @GET("login")
    Integer login();

    @GET("all_users")
    List<List<String>> getAllUsers();
}
