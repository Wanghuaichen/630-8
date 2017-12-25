package com.zodolabs.zzf.zf630.Beans.normal;

/**
 * Created by zzf on 17-12-11.
 */

public class HuiGuiBean {
    String num;
    float nongdu;
    float xiguangdu;

    public HuiGuiBean(String num, float xiguangdu, float nongdu) {
        this.num = num;
        this.nongdu = nongdu;
        this.xiguangdu = xiguangdu;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public float getNongdu() {
        return nongdu;
    }

    public void setNongdu(float nongdu) {
        this.nongdu = nongdu;
    }

    public float getXiguangdu() {
        return xiguangdu;
    }

    public void setXiguangdu(float xiguangdu) {
        this.xiguangdu = xiguangdu;
    }
}
