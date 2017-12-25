package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by zzf on 17-6-27.
 * CREATE TABLE "dictionary" ("ID" TEXT,"NO" TEXT,"Content" TEXT,"T_pid" TEXT,"Type" TEXT,"Belong" TEXT,"B_Valid" TEXT,"Shortcut" TEXT,"T_BZ" TEXT,"I_BZ" TEXT)

 */

@Entity(nameInDb = "dictionary")
public class Dictionary {
    @Property(nameInDb = "ID")
    int id;
    @Id
    @Property(nameInDb = "NO")
    Long no;
    @Property(nameInDb = "Content")
    String content;
    @Property(nameInDb = "T_pid")
    String t_pid;
    @Property(nameInDb = "Type")
    String type;
    @Property(nameInDb = "Belong")
    String belong;
    @Property(nameInDb = "B_Valid")
    String b_valid;
    @Property(nameInDb = "Shortcut")
    String shortcut;
    @Property(nameInDb = "T_BZ")
    String t_bz;
    @Property(nameInDb = "I_BZ")
    String i_bz;
    @Generated(hash = 1554594344)
    public Dictionary(int id, Long no, String content, String t_pid, String type, String belong, String b_valid, String shortcut, String t_bz, String i_bz) {
        this.id = id;
        this.no = no;
        this.content = content;
        this.t_pid = t_pid;
        this.type = type;
        this.belong = belong;
        this.b_valid = b_valid;
        this.shortcut = shortcut;
        this.t_bz = t_bz;
        this.i_bz = i_bz;
    }
    @Generated(hash = 487998537)
    public Dictionary() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Long getNo() {
        return this.no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getT_pid() {
        return this.t_pid;
    }
    public void setT_pid(String t_pid) {
        this.t_pid = t_pid;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getBelong() {
        return this.belong;
    }
    public void setBelong(String belong) {
        this.belong = belong;
    }
    public String getB_valid() {
        return this.b_valid;
    }
    public void setB_valid(String b_valid) {
        this.b_valid = b_valid;
    }
    public String getShortcut() {
        return this.shortcut;
    }
    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }
    public String getT_bz() {
        return this.t_bz;
    }
    public void setT_bz(String t_bz) {
        this.t_bz = t_bz;
    }
    public String getI_bz() {
        return this.i_bz;
    }
    public void setI_bz(String i_bz) {
        this.i_bz = i_bz;
    }
}
