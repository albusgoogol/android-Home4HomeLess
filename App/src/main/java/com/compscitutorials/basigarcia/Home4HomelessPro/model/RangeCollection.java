package com.compscitutorials.basigarcia.Home4HomelessPro.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RangeCollection {

    @SerializedName("range")
    private List<Items> itemRange;

    @SerializedName("job")
    private List<Job> itemJob;

    public List<Job> getItemJob() {
        return itemJob;
    }

    public List<Items> getItemRange() {
        return itemRange;
    }

    public class Items {
        @SerializedName("code")
        private String code;

        @SerializedName("job")
        private String name;

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    public class Job {
        @SerializedName("job")
        private String job;

        public String getJob() {
            return job;
        }
    }
}
