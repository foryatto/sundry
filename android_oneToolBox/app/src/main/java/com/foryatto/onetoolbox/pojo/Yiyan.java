package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Yiyan implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"constant":"我无知又愚蠢，世界残酷又疯狂。","source":"进击的巨人"}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * constant : 我无知又愚蠢，世界残酷又疯狂。
         * source : 进击的巨人
         */

        private String constant;
        private String source;
    }
}
