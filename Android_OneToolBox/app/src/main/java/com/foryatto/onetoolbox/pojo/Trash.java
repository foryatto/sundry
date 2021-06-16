package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Trash implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"Name":"啤酒","Type":"湿垃圾","Description":{"Concept":"湿垃圾即厨余垃圾，是指日常生活垃圾产生的容易腐烂的生物质废弃物","Including":"食材废料、剩饭剩菜、过期食品、蔬菜水果、瓜皮果核、花卉绿植、中药残渣等","Release_requirement":"1.纯流质的食物垃圾，如牛奶等，应直接倒进下水口 2.有包装物的湿垃圾应将包装物去除后分类投放，包装物请投放到对应的可回收垃圾或干垃圾容器"}}
     */

    private String code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * Name : 啤酒
         * Type : 湿垃圾
         * Description : {"Concept":"湿垃圾即厨余垃圾，是指日常生活垃圾产生的容易腐烂的生物质废弃物","Including":"食材废料、剩饭剩菜、过期食品、蔬菜水果、瓜皮果核、花卉绿植、中药残渣等","Release_requirement":"1.纯流质的食物垃圾，如牛奶等，应直接倒进下水口 2.有包装物的湿垃圾应将包装物去除后分类投放，包装物请投放到对应的可回收垃圾或干垃圾容器"}
         */

        private String Name;
        private String Type;
        private DescriptionBean Description;

        @Data
        public static class DescriptionBean implements Serializable {
            /**
             * Concept : 湿垃圾即厨余垃圾，是指日常生活垃圾产生的容易腐烂的生物质废弃物
             * Including : 食材废料、剩饭剩菜、过期食品、蔬菜水果、瓜皮果核、花卉绿植、中药残渣等
             * Release_requirement : 1.纯流质的食物垃圾，如牛奶等，应直接倒进下水口 2.有包装物的湿垃圾应将包装物去除后分类投放，包装物请投放到对应的可回收垃圾或干垃圾容器
             */

            private String Concept;
            private String Including;
            private String Release_requirement;
        }
    }
}
