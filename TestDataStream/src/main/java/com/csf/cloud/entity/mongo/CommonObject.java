package com.csf.cloud.entity.mongo;

import java.util.Date;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class CommonObject extends BaseObject {

    private static final long serialVersionUID = 7881472936400922387L;

    private String sid;
    private Date crt;
    private Date upt;
    private String cru;
    private String upu;
    private Integer stat;

    public Date getCrt() {
        return crt;
    }

    public void setCrt(Date crt) {
        this.crt = crt;
    }

    public Date getUpt() {
        return upt;
    }

    public void setUpt(Date upt) {
        this.upt = upt;
    }

    public String getCru() {
        return cru;
    }

    public void setCru(String cru) {
        this.cru = cru;
    }

    public String getUpu() {
        return upu;
    }

    public void setUpu(String upu) {
        this.upu = upu;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
