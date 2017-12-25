package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zzf on 17-12-7.
 */

@Entity(nameInDb = "xianzhi")
public class xianzhi {
    @Property(nameInDb = "id")
    @Id
    Long id;
    @Property(nameInDb = "limitvalue")
    String limitvalue;

    @Generated(hash = 902904501)
    public xianzhi(Long id, String limitvalue) {
        this.id = id;
        this.limitvalue = limitvalue;
    }

    @Generated(hash = 9923132)
    public xianzhi() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLimitvalue() {
        return this.limitvalue;
    }

    public void setLimitvalue(String limitvalue) {
        this.limitvalue = limitvalue;
    }
}
