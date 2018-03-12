package com.csf.cloud.entity.mongo.base;

import com.csf.cloud.entity.mongo.BaseObject;

import java.util.Date;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class BaseNewsCat extends BaseObject {
    private static final long serialVersionUID = 332134454127057456L;
    private String cd;
    private String pcd;
    private String szh;
    private String en;
    private Date sdt;
    private Date edt;
    public String getCd() {
        return cd;
    }
    public void setCd(String cd) {
        this.cd = cd;
    }
    public String getPcd() {
        return pcd;
    }
    public void setPcd(String pcd) {
        this.pcd = pcd;
    }
    public String getSzh() {
        return szh;
    }
    public void setSzh(String szh) {
        this.szh = szh;
    }
    public String getEn() {
        return en;
    }
    public void setEn(String en) {
        this.en = en;
    }
    public Date getSdt() {
        return sdt;
    }
    public void setSdt(Date sdt) {
        this.sdt = sdt;
    }
    public Date getEdt() {
        return edt;
    }
    public void setEdt(Date edt) {
        this.edt = edt;
    }

}
