package com.zodolabs.zzf.zf630.Beans.normal;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Created by zzf on 17-12-14.
 */

@Entity(nameInDb = "JianceBean")
public class JianceBean {
    @Property(nameInDb = "ypbh")
    @Id
    Long ypbh;
    @Property(nameInDb = "jcxm")
    String jcxm;
    @Property(nameInDb = "ypmc")
    String ypmc;
    @Property(nameInDb = "ypfl")
    String ypfl;
    @Property(nameInDb = "ypcd")
    String ypcd;
    @Property(nameInDb = "lydw")
    String lydw;
    @Property(nameInDb = "xgd")
    String xgd;
    @Property(nameInDb = "jcz")
    String jcz;
    @Property(nameInDb = "jcjg")
    String jcjg;
    @Property(nameInDb = "date")
    Date date;
    @Property(nameInDb = "upload")
    String upload;

    @Generated(hash = 1137804702)
    public JianceBean(Long ypbh, String jcxm, String ypmc, String ypfl, String ypcd,
                      String lydw, String xgd, String jcz, String jcjg, Date date,
                      String upload) {
        this.ypbh = ypbh;
        this.jcxm = jcxm;
        this.ypmc = ypmc;
        this.ypfl = ypfl;
        this.ypcd = ypcd;
        this.lydw = lydw;
        this.xgd = xgd;
        this.jcz = jcz;
        this.jcjg = jcjg;
        this.date = date;
        this.upload = upload;
    }

    @Generated(hash = 512749342)
    public JianceBean() {
    }

    public JianceBean(Long ypbh) {
        this(0L, "", "", "", "", "", "", "", "", null, "");
    }

    public Long getYpbh() {
        return this.ypbh;
    }

    public void setYpbh(Long ypbh) {
        this.ypbh = ypbh;
    }

    public String getJcxm() {
        return this.jcxm;
    }

    public void setJcxm(String jcxm) {
        this.jcxm = jcxm;
    }

    public String getYpmc() {
        return this.ypmc;
    }

    public void setYpmc(String ypmc) {
        this.ypmc = ypmc;
    }

    public String getYpfl() {
        return this.ypfl;
    }

    public void setYpfl(String ypfl) {
        this.ypfl = ypfl;
    }

    public String getYpcd() {
        return this.ypcd;
    }

    public void setYpcd(String ypcd) {
        this.ypcd = ypcd;
    }

    public String getLydw() {
        return this.lydw;
    }

    public void setLydw(String lydw) {
        this.lydw = lydw;
    }

    public String getXgd() {
        return this.xgd;
    }

    public void setXgd(String xgd) {
        this.xgd = xgd;
    }

    public String getJcz() {
        return this.jcz;
    }

    public void setJcz(String jcz) {
        this.jcz = jcz;
    }

    public String getJcjg() {
        return this.jcjg;
    }

    public void setJcjg(String jcjg) {
        this.jcjg = jcjg;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUpload() {
        return this.upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    @Override
    public String toString() {
        return ""+ypbh+","+ypmc+","+ypfl+","+jcxm+","+ypcd+","+lydw+","+date+","+xgd+","+jcz+","+jcjg+","+upload+"\n";
    }
}
