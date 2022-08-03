package com.example.appmetanit.RestAPIConnect.allUsersEndpoint;

import com.example.appmetanit.RestAPIConnect.ServerAPI;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;

public class AllUsersController implements GetAllUsers {
    @Override
    public Call<List<UserData>> getAllUsers() {
        return null;
    }
}
