package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by zzf on 17-12-22.
 */
@Entity(nameInDb = "user_info")
public class UserInfo {
    @Id
    @Property(nameInDb = "id")
    Long id;
    @Property(nameInDb = "username")
    String username;
    @Property(nameInDb = "password")
    String password;
    @Property(nameInDb = "permission")
    String permission;
    @Generated(hash = 1575907600)
    public UserInfo(Long id, String username, String password, String permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permission = permission;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPermission() {
        return this.permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
}
