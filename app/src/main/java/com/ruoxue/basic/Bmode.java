package com.ruoxue.basic;


import com.ruoxue.basic.mode.BaseMode;

/**
 * Created by ruoxue_ye on 2015/12/11.
 */
public class Bmode extends BaseMode<Bmode> {

    /**
     * lm : 2
     * id : 2
     * title : 父母抗氧化方案
     * img_sl : attach/201407/1406740364156631662.jpg
     * comment : 控糖降糖/预防糖尿病并发症
     * minPrice : 688
     */

    private String lm;
    private String id;
    private String title;
    private String img_sl;
    private String comment;
    private String minPrice;

    public void setLm(String lm) {
        this.lm = lm;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImg_sl(String img_sl) {
        this.img_sl = img_sl;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getLm() {
        return lm;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg_sl() {
        return img_sl;
    }

    public String getComment() {
        return comment;
    }

    public String getMinPrice() {
        return minPrice;
    }
}
