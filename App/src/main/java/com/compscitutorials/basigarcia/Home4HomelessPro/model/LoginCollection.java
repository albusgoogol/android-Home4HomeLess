package com.compscitutorials.basigarcia.Home4HomelessPro.model;

import com.google.gson.annotations.SerializedName;

public class LoginCollection {

    @SerializedName("success")
    private String success;

    @SerializedName("status")
    private String status;

    @SerializedName("fname")
    private String fname;

    @SerializedName("lname")
    private String lname;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getSuccess() {
        return success;
    }

    public String getStatus() {
        return status;
    }
}
