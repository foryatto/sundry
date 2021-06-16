package com.foryatto.onetoolbox.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Baike implements Serializable {

    /**
     * code : 200
     * msg : success
     * data : {"content":"仙人掌(学名：Opuntiastricta(Haw.)Haw.var.dillenii(Ker-Gawl.)Benson)，是仙人掌属的一种植物。别名仙巴掌、观音掌、霸王、火掌等，为仙人掌科植物。仙人掌为丛生肉质灌木，上部分枝宽倒卵形、倒卵状椭圆形或近圆形；花辐状，花托倒卵形；种子多数扁圆形，边缘稍不规则，无毛，淡黄褐色。仙人掌喜强烈光照，耐炎热，干旱、瘠薄，生命力顽强，生长适温为20-30℃。主要分布在美国南部及东南部沿海地区、西印度群岛、百慕大群岛和南美洲北部、中国南方及东南亚等热带、亚热带地区的干旱地区。 仙人掌的种类繁多，世界上共有70-110个属，2000余种，具体可以分为：团扇仙人掌类、段型仙人掌类、蟹爪仙人掌(螃蟹兰)、叶型森林性仙人掌类、球形仙人掌。常生长于沙漠等干燥环境中，被称为\u201c沙漠英雄花\u201d，为多肉植物的一类。(概述图参考资料来源 )","ImgUrl":"http://pic.baike.soso.com/ugc/baikepic2/711/20200306175558-1035437305_jpeg_200_250_16564.jpg/0"}
     */

    private int code;
    private String msg;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * content : 仙人掌(学名：Opuntiastricta(Haw.)Haw.var.dillenii(Ker-Gawl.)Benson)，是仙人掌属的一种植物。别名仙巴掌、观音掌、霸王、火掌等，为仙人掌科植物。仙人掌为丛生肉质灌木，上部分枝宽倒卵形、倒卵状椭圆形或近圆形；花辐状，花托倒卵形；种子多数扁圆形，边缘稍不规则，无毛，淡黄褐色。仙人掌喜强烈光照，耐炎热，干旱、瘠薄，生命力顽强，生长适温为20-30℃。主要分布在美国南部及东南部沿海地区、西印度群岛、百慕大群岛和南美洲北部、中国南方及东南亚等热带、亚热带地区的干旱地区。 仙人掌的种类繁多，世界上共有70-110个属，2000余种，具体可以分为：团扇仙人掌类、段型仙人掌类、蟹爪仙人掌(螃蟹兰)、叶型森林性仙人掌类、球形仙人掌。常生长于沙漠等干燥环境中，被称为“沙漠英雄花”，为多肉植物的一类。(概述图参考资料来源 )
         * ImgUrl : http://pic.baike.soso.com/ugc/baikepic2/711/20200306175558-1035437305_jpeg_200_250_16564.jpg/0
         */

        private String content;
        private String ImgUrl;
    }
}

