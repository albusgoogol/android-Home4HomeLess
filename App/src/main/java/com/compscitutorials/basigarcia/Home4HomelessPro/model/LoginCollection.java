package com.compscitutorials.basigarcia.Home4HomelessPro.model;

import com.google.gson.annotations.SerializedName;

public class LoginCollection {

    @SerializedName("success")
    private String success;

    @SerializedName("status")
    private String status;

    public String getSuccess() {
        return success;
    }

    public String getStatus() {
        return status;
    }
}
