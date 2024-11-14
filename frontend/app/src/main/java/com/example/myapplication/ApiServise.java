package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
public interface ApiServise {

    @POST("api/registration/register")
    Call<ResponseBody> registerUser(@Body Users registerRequest);
    @POST("/api/authorization/login")  // укажите правильный путь к вашему API для входа
    Call<ResponseBody> loginUser(@Body LoginRequest loginRequest);
}
