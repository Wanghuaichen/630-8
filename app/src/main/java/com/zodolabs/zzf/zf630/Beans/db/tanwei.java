package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zzf on 17-12-7.
 */

@Entity(nameInDb = "tanwei")
public class tanwei {
    @Id
    @Property(nameInDb = "id")
    Long id;
    @Property(nameInDb = "tanweinum")
    String tanweinum;
    @Generated(hash = 421773788)
    public tanwei(Long id, String tanweinum) {
        this.id = id;
        this.tanweinum = tanweinum;
    }
    @Generated(hash = 1880552401)
    public tanwei() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTanweinum() {
        return this.tanweinum;
    }
    public void setTanweinum(String tanweinum) {
        this.tanweinum = tanweinum;
    }
}
