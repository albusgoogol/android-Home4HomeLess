package com.compscitutorials.basigarcia.Home4HomelessPro.service;

import com.compscitutorials.basigarcia.Home4HomelessPro.model.LoginCollection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IHome4Service {

    @FormUrlEncoded
    @POST("check_login.php")
    Call<LoginCollection> login(@Field("email") String email,
                                @Field("pass") String password, @Field("android") String os);
}
