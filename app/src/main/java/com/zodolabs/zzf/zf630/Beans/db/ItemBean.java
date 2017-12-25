package com.zodolabs.zzf.zf630.Beans.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zzf on 17-6-27.
 * CREATE TABLE "itembase" ("IID" TEXT,"B_Valid" TEXT,"Code" TEXT,"Pid" TEXT,"Print" TEXT,"T_type" TEXT,"Name" TEXT,"Unit" TEXT,"Dilute" TEXT,"Method" TEXT,"MesF"
 * TEXT,"RefF" TEXT,"Ref" TEXT,"BlankMax" TEXT,"BlankMsg" TEXT,"Blank" TEXT,"BlankCal" TEXT,"NumRT" TEXT,"RTtype" TEXT,"RTvalue" TEXT,"RTvalue_min" TEXT,
 * "Titration" TEXT,"waittime" TEXT,"testtime" TEXT,"Reserve" TEXT,"Reserve_add" TEXT,"Aglo" TEXT,"SDNum" TEXT,"ABS1" TEXT,"ABS2" TEXT,"ABS3" TEXT,"ABS4"
 * TEXT,"ABS5" TEXT,"ABS6" TEXT,"ABS7" TEXT,"ABS8" TEXT,"Depth1" TEXT,"Depth2" TEXT,"Depth3" TEXT,"Depth4" TEXT,"Depth5" TEXT,"Depth6" TEXT,"Depth7" TEXT,
 * "Depth8" TEXT,"T_BZ1" TEXT,"T_BZ2" TEXT,"I_BZ1" TEXT,"I_BZ2" TEXT,"B_BZ" TEXT,"batch" TEXT)
 */
@Entity(nameInDb = "ItemBean")
public class ItemBean {
    @Property(nameInDb = "IID")
    String id;
    @Property(nameInDb = "B_Valid")
    String b_valid;
    @Property(nameInDb = "Code")
    String Code;
    @Property(nameInDb = "Pid")
    String Pid;
    @Property(nameInDb = "Print")
    @Id
    Long Print;
    @Property(nameInDb = "T_type")
    String T_type;
    @Property(nameInDb = "Name")
    String Name;
    @Property(nameInDb = "Unit")
    String Unit;
    @Property(nameInDb = "Dilute")
    float Dilute;
    @Property(nameInDb = "Method")
    String Method;
    @Property(nameInDb = "mesF")
    String mesF;
    @Property(nameInDb = "RefF")
    String RefF;
    @Property(nameInDb = "Ref")
    String Ref;
    @Property(nameInDb = "BlankMax")
    float BlankMax;
    @Property(nameInDb = "BlankMsg")
    float BlankMsg;
    @Property(nameInDb = "Blank")
    float Blank;
    @Property(nameInDb = "BlankCal")
    String BlankCal;
    @Property(nameInDb = "NumRT")
    String NumRT;
    @Property(nameInDb = "RTtype")
    String RTtype;
    @Property(nameInDb = "RTvalue")
    float RTvalue;
    @Property(nameInDb = "RTvalue_min")
    float RTvalue_min;
    @Property(nameInDb = "Titration")
    float Titration;
    @Property(nameInDb = "waittime")
    String waittime;
    @Property(nameInDb = "testtime")
    String testtime;
    @Property(nameInDb = "Reserve")
    String Reserve;
    @Property(nameInDb = "Reserve_add")
    String Reserve_add;
    @Property(nameInDb = "Aglo")
    String Aglo;
    @Property(nameInDb = "SDNum")
    String SDNum;
    @Property(nameInDb = "ABS1")
    float ABS1;
    @Property(nameInDb = "ABS2")
    float ABS2;
    @Property(nameInDb = "ABS3")
    float ABS3;
    @Property(nameInDb = "ABS4")
    float ABS4;
    @Property(nameInDb = "ABS5")
    float ABS5;
    @Property(nameInDb = "ABS6")
    float ABS6;
    @Property(nameInDb = "ABS7")
    float ABS7;
    @Property(nameInDb = "ABS8")
    float ABS8;
    @Property(nameInDb = "Depth1")
    float Depth1;
    @Property(nameInDb = "Depth2")
    float Depth2;
    @Property(nameInDb = "Depth3")
    float Depth3;
    @Property(nameInDb = "Depth4")
    float Depth4;
    @Property(nameInDb = "Depth5")
    float Depth5;
    @Property(nameInDb = "Depth6")
    float Depth6;
    @Property(nameInDb = "Depth7")
    float Depth7;
    @Property(nameInDb = "Depth8")
    float Depth8;
    @Property(nameInDb = "T_BZ1")
    String T_BZ1;
    @Property(nameInDb = "T_BZ2")
    String T_BZ2;
    @Property(nameInDb = "I_BZ1")
    String I_BZ1;
    @Property(nameInDb = "I_BZ2")
    String I_BZ2;
    @Property(nameInDb = "B_BZ")
    String B_BZ;
    @Property(nameInDb = "batch")
    String batch;
    @Generated(hash = 1717233639)
    public ItemBean(String id, String b_valid, String Code, String Pid, Long Print, String T_type, String Name, String Unit, float Dilute, String Method,
            String mesF, String RefF, String Ref, float BlankMax, float BlankMsg, float Blank, String BlankCal, String NumRT, String RTtype, float RTvalue,
            float RTvalue_min, float Titration, String waittime, String testtime, String Reserve, String Reserve_add, String Aglo, String SDNum, float ABS1,
            float ABS2, float ABS3, float ABS4, float ABS5, float ABS6, float ABS7, float ABS8, float Depth1, float Depth2, float Depth3, float Depth4,
            float Depth5, float Depth6, float Depth7, float Depth8, String T_BZ1, String T_BZ2, String I_BZ1, String I_BZ2, String B_BZ, String batch) {
        this.id = id;
        this.b_valid = b_valid;
        this.Code = Code;
        this.Pid = Pid;
        this.Print = Print;
        this.T_type = T_type;
        this.Name = Name;
        this.Unit = Unit;
        this.Dilute = Dilute;
        this.Method = Method;
        this.mesF = mesF;
        this.RefF = RefF;
        this.Ref = Ref;
        this.BlankMax = BlankMax;
        this.BlankMsg = BlankMsg;
        this.Blank = Blank;
        this.BlankCal = BlankCal;
        this.NumRT = NumRT;
        this.RTtype = RTtype;
        this.RTvalue = RTvalue;
        this.RTvalue_min = RTvalue_min;
        this.Titration = Titration;
        this.waittime = waittime;
        this.testtime = testtime;
        this.Reserve = Reserve;
        this.Reserve_add = Reserve_add;
        this.Aglo = Aglo;
        this.SDNum = SDNum;
        this.ABS1 = ABS1;
        this.ABS2 = ABS2;
        this.ABS3 = ABS3;
        this.ABS4 = ABS4;
        this.ABS5 = ABS5;
        this.ABS6 = ABS6;
        this.ABS7 = ABS7;
        this.ABS8 = ABS8;
        this.Depth1 = Depth1;
        this.Depth2 = Depth2;
        this.Depth3 = Depth3;
        this.Depth4 = Depth4;
        this.Depth5 = Depth5;
        this.Depth6 = Depth6;
        this.Depth7 = Depth7;
        this.Depth8 = Depth8;
        this.T_BZ1 = T_BZ1;
        this.T_BZ2 = T_BZ2;
        this.I_BZ1 = I_BZ1;
        this.I_BZ2 = I_BZ2;
        this.B_BZ = B_BZ;
        this.batch = batch;
    }
    @Generated(hash = 95333960)
    public ItemBean() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getB_valid() {
        return this.b_valid;
    }
    public void setB_valid(String b_valid) {
        this.b_valid = b_valid;
    }
    public String getCode() {
        return this.Code;
    }
    public void setCode(String Code) {
        this.Code = Code;
    }
    public String getPid() {
        return this.Pid;
    }
    public void setPid(String Pid) {
        this.Pid = Pid;
    }
    public Long getPrint() {
        return this.Print;
    }
    public void setPrint(Long Print) {
        this.Print = Print;
    }
    public String getT_type() {
        return this.T_type;
    }
    public void setT_type(String T_type) {
        this.T_type = T_type;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getUnit() {
        return this.Unit;
    }
    public void setUnit(String Unit) {
        this.Unit = Unit;
    }
    public float getDilute() {
        return this.Dilute;
    }
    public void setDilute(float Dilute) {
        this.Dilute = Dilute;
    }
    public String getMethod() {
        return this.Method;
    }
    public void setMethod(String Method) {
        this.Method = Method;
    }
    public String getMesF() {
        return this.mesF;
    }
    public void setMesF(String mesF) {
        this.mesF = mesF;
    }
    public String getRefF() {
        return this.RefF;
    }
    public void setRefF(String RefF) {
        this.RefF = RefF;
    }
    public String getRef() {
        return this.Ref;
    }
    public void setRef(String Ref) {
        this.Ref = Ref;
    }
    public float getBlankMax() {
        return this.BlankMax;
    }
    public void setBlankMax(float BlankMax) {
        this.BlankMax = BlankMax;
    }
    public float getBlankMsg() {
        return this.BlankMsg;
    }
    public void setBlankMsg(float BlankMsg) {
        this.BlankMsg = BlankMsg;
    }
    public float getBlank() {
        return this.Blank;
    }
    public void setBlank(float Blank) {
        this.Blank = Blank;
    }
    public String getBlankCal() {
        return this.BlankCal;
    }
    public void setBlankCal(String BlankCal) {
        this.BlankCal = BlankCal;
    }
    public String getNumRT() {
        return this.NumRT;
    }
    public void setNumRT(String NumRT) {
        this.NumRT = NumRT;
    }
    public String getRTtype() {
        return this.RTtype;
    }
    public void setRTtype(String RTtype) {
        this.RTtype = RTtype;
    }
    public float getRTvalue() {
        return this.RTvalue;
    }
    public void setRTvalue(float RTvalue) {
        this.RTvalue = RTvalue;
    }
    public float getRTvalue_min() {
        return this.RTvalue_min;
    }
    public void setRTvalue_min(float RTvalue_min) {
        this.RTvalue_min = RTvalue_min;
    }
    public float getTitration() {
        return this.Titration;
    }
    public void setTitration(float Titration) {
        this.Titration = Titration;
    }
    public String getWaittime() {
        return this.waittime;
    }
    public void setWaittime(String waittime) {
        this.waittime = waittime;
    }
    public String getTesttime() {
        return this.testtime;
    }
    public void setTesttime(String testtime) {
        this.testtime = testtime;
    }
    public String getReserve() {
        return this.Reserve;
    }
    public void setReserve(String Reserve) {
        this.Reserve = Reserve;
    }
    public String getReserve_add() {
        return this.Reserve_add;
    }
    public void setReserve_add(String Reserve_add) {
        this.Reserve_add = Reserve_add;
    }
    public String getAglo() {
        return this.Aglo;
    }
    public void setAglo(String Aglo) {
        this.Aglo = Aglo;
    }
    public String getSDNum() {
        return this.SDNum;
    }
    public void setSDNum(String SDNum) {
        this.SDNum = SDNum;
    }
    public float getABS1() {
        return this.ABS1;
    }
    public void setABS1(float ABS1) {
        this.ABS1 = ABS1;
    }
    public float getABS2() {
        return this.ABS2;
    }
    public void setABS2(float ABS2) {
        this.ABS2 = ABS2;
    }
    public float getABS3() {
        return this.ABS3;
    }
    public void setABS3(float ABS3) {
        this.ABS3 = ABS3;
    }
    public float getABS4() {
        return this.ABS4;
    }
    public void setABS4(float ABS4) {
        this.ABS4 = ABS4;
    }
    public float getABS5() {
        return this.ABS5;
    }
    public void setABS5(float ABS5) {
        this.ABS5 = ABS5;
    }
    public float getABS6() {
        return this.ABS6;
    }
    public void setABS6(float ABS6) {
        this.ABS6 = ABS6;
    }
    public float getABS7() {
        return this.ABS7;
    }
    public void setABS7(float ABS7) {
        this.ABS7 = ABS7;
    }
    public float getABS8() {
        return this.ABS8;
    }
    public void setABS8(float ABS8) {
        this.ABS8 = ABS8;
    }
    public float getDepth1() {
        return this.Depth1;
    }
    public void setDepth1(float Depth1) {
        this.Depth1 = Depth1;
    }
    public float getDepth2() {
        return this.Depth2;
    }
    public void setDepth2(float Depth2) {
        this.Depth2 = Depth2;
    }
    public float getDepth3() {
        return this.Depth3;
    }
    public void setDepth3(float Depth3) {
        this.Depth3 = Depth3;
    }
    public float getDepth4() {
        return this.Depth4;
    }
    public void setDepth4(float Depth4) {
        this.Depth4 = Depth4;
    }
    public float getDepth5() {
        return this.Depth5;
    }
    public void setDepth5(float Depth5) {
        this.Depth5 = Depth5;
    }
    public float getDepth6() {
        return this.Depth6;
    }
    public void setDepth6(float Depth6) {
        this.Depth6 = Depth6;
    }
    public float getDepth7() {
        return this.Depth7;
    }
    public void setDepth7(float Depth7) {
        this.Depth7 = Depth7;
    }
    public float getDepth8() {
        return this.Depth8;
    }
    public void setDepth8(float Depth8) {
        this.Depth8 = Depth8;
    }
    public String getT_BZ1() {
        return this.T_BZ1;
    }
    public void setT_BZ1(String T_BZ1) {
        this.T_BZ1 = T_BZ1;
    }
    public String getT_BZ2() {
        return this.T_BZ2;
    }
    public void setT_BZ2(String T_BZ2) {
        this.T_BZ2 = T_BZ2;
    }
    public String getI_BZ1() {
        return this.I_BZ1;
    }
    public void setI_BZ1(String I_BZ1) {
        this.I_BZ1 = I_BZ1;
    }
    public String getI_BZ2() {
        return this.I_BZ2;
    }
    public void setI_BZ2(String I_BZ2) {
        this.I_BZ2 = I_BZ2;
    }
    public String getB_BZ() {
        return this.B_BZ;
    }
    public void setB_BZ(String B_BZ) {
        this.B_BZ = B_BZ;
    }
    public String getBatch() {
        return this.batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
}
