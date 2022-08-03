package com.example.appmetanit.RestAPIConnect.allUsersEndpoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAllUsers {
    @GET("all_users")
    Call<List<UserData>> getAllUsers();
}
