package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Poet implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"Poetry":"立身不高一步立，如尘里振衣、泥中濯足，如何超达？","Poet":"null","Poem_title":"菜根谭·概论"}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * Poetry : 立身不高一步立，如尘里振衣、泥中濯足，如何超达？
         * Poet : null
         * Poem_title : 菜根谭·概论
         */

        private String Poetry;
        private String Poet;
        private String Poem_title;
    }
}
