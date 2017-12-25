package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zzf on 17-6-27.
 */
@Entity(nameInDb = "type")
public class type {
    @Id
    @Property(nameInDb = "id")
    Long id;
    @Property(nameInDb = "typename")
    String typename;
    @Generated(hash = 1464453433)
    public type(Long id, String typename) {
        this.id = id;
        this.typename = typename;
    }
    @Generated(hash = 1761568405)
    public type() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTypename() {
        return this.typename;
    }
    public void setTypename(String typename) {
        this.typename = typename;
    }
}
