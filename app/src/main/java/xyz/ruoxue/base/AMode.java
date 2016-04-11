package xyz.ruoxue.base;


import xyz.ruoxue.base.mode.BaseMode;

/**
 * Created by ruoxue_ye on 2015/12/11.
 */
public class AMode extends BaseMode<AMode> {


    /**
     * id : 1
     * title : 云南 绿A螺旋藻精片300片
     * origprice : 358
     * current_price : 158
     * img_sl : attach/201407/1406583447361773588.jpg
     */

    private String id;
    private String title;
    private String origprice;
    private String current_price;
    private String img_sl;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrigprice(String origprice) {
        this.origprice = origprice;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public void setImg_sl(String img_sl) {
        this.img_sl = img_sl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOrigprice() {
        return origprice;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public String getImg_sl() {
        return img_sl;
    }

    @Override
    public String toString() {
        return "AMode{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", origprice='" + origprice + '\'' +
                ", current_price='" + current_price + '\'' +
                ", img_sl='" + img_sl + '\'' +
                '}';
    }
}
