package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class NcmRemark implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"content":"忘不掉的人像星星, 到了晚上才特别明显。","songPic":"http://p1.music.126.net/zuinxxqLArVNf-zp6Kf1cg==/109951164575721512.jpg","songAutho":"王巨星","songName":"情字难（男版）","songId":"1411576088","nickname":"丅vv丅","avatar":"http://p2.music.126.net/EaDAMsgSMxM8qK5euRmsZg==/109951165629041546.jpg","likedCount":"8559","time":"1577403408423"}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * content : 忘不掉的人像星星, 到了晚上才特别明显。
         * songPic : http://p1.music.126.net/zuinxxqLArVNf-zp6Kf1cg==/109951164575721512.jpg
         * songAutho : 王巨星
         * songName : 情字难（男版）
         * songId : 1411576088
         * nickname : 丅vv丅
         * avatar : http://p2.music.126.net/EaDAMsgSMxM8qK5euRmsZg==/109951165629041546.jpg
         * likedCount : 8559
         * time : 1577403408423
         */

        private String content;
        private String songPic;
        private String songAutho;
        private String songName;
        private String songId;
        private String nickname;
        private String avatar;
        private String likedCount;
        private String time;
    }
}
