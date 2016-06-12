package com.compscitutorials.basigarcia.Home4HomelessPro.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InformCollection {

    @SerializedName("inform")
    private List<Inform> listInform;

    public List<Inform> getListInform() {
        return listInform;
    }

    public class Inform {

        @SerializedName("code")
        private String code;

        @SerializedName("date")
        private String date;

        @SerializedName("location")
        private String location;

        @SerializedName("status")
        private String status;

        @SerializedName("member_id")
        private String memId;

        public String getCode() {
            return code;
        }

        public String getDate() {
            return date;
        }

        public String getLocation() {
            return location;
        }

        public String getStatus() {
            return status;
        }

        public String getMemId() {
            return memId;
        }
    }
}
