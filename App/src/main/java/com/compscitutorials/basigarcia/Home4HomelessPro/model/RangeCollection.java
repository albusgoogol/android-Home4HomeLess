package com.compscitutorials.basigarcia.Home4HomelessPro.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RangeCollection {

    @SerializedName("items")
    private List<Items> item;

    public List<Items> getItem() {
        return item;
    }

    public class Items {
        @SerializedName("code")
        private String code;

        @SerializedName("name")
        private String name;

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
