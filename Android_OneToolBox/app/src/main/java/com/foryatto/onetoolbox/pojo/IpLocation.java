package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class IpLocation implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"ip":"110.110.110.110","Geographical_location":"黑龙江省哈尔滨市 铁通"}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * ip : 110.110.110.110
         * Geographical_location : 黑龙江省哈尔滨市 铁通
         */

        private String ip;
        private String Geographical_location;
    }
}
