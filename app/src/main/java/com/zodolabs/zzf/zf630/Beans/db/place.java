package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zzf on 17-6-27.
 * CREATE TABLE "place" (
 * "id"  INTEGER NOT NULL,
 * "name"  TEXT,
 * "province"  TEXT,
 * "city"  TEXT,
 * "county"  TEXT,
 * PRIMARY KEY ("id")
 * )
 */
@Entity(nameInDb = "place")
public class place {
    @Id
    @Property(nameInDb = "id")
    Long id;
    @Property(nameInDb = "name")
    String name;
    @Property(nameInDb = "province")
    String province;
    @Property(nameInDb = "city")
    String city;
    @Property(nameInDb = "county")
    String county;
    @Generated(hash = 723469052)
    public place(Long id, String name, String province, String city,
            String county) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.city = city;
        this.county = county;
    }
    @Generated(hash = 168584598)
    public place() {
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
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return this.county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
}
