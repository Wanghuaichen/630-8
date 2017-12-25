package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zzf on 17-6-27.
 */

@Entity(nameInDb = "people")
public class people {
    @Id
    @Property(nameInDb = "id")
    Long id;
    @Property(nameInDb = "name")
    String name;
    @Generated(hash = 527938566)
    public people(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1277389469)
    public people() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
