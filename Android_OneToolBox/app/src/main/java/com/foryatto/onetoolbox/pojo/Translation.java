package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Translation implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"Original":"我爱你","Translation":"I love you"}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * Original : 我爱你
         * Translation : I love you
         */

        private String Original;
        private String Translation;
    }
}
