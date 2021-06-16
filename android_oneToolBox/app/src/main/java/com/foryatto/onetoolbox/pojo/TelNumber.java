package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TelNumber implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"prefix":"182","mts":"1827326","province":"湖南","city":"株洲","catName":"中国移动","telString":"18273261198","post_code":"412000","city_code":"731","area_code":"430200"}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * prefix : 182
         * mts : 1827326
         * province : 湖南
         * city : 株洲
         * catName : 中国移动
         * telString : 18273261198
         * post_code : 412000
         * city_code : 731
         * area_code : 430200
         */

        private String prefix;
        private String mts;
        private String province;
        private String city;
        private String catName;
        private String telString;
        private String post_code;
        private String city_code;
        private String area_code;
    }
}
