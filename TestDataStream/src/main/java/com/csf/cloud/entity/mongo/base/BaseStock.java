package com.csf.cloud.entity.mongo.base;

import com.csf.cloud.entity.mongo.BaseObject;

import java.util.Date;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class BaseStock extends BaseObject {
    private static final long serialVersionUID = -3414878843823221560L;
    private String code;
    private String tick;
    private Name name;
    private Abbr abbr;
    private Org org;
    private Mkt mkt;
    private Ls ls;
    private Long ivol;
    private Long share;
    private Boolean adr;
    private Double rat;
    private String fv;
    private Fvcur fvcur;
    private String dss;
    private String rkd;
    private String cik;
    private Rem rem;
    private String isin;
    private String sedol;
    private Long cov;
    private Integer stat;
    private String id;
    private String upu;
    private Date upt;
    private String cru;
    private Date crt;
    private String src;
    private String sid;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTick() {
        return tick;
    }
    public void setTick(String tick) {
        this.tick = tick;
    }
    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }
    public Abbr getAbbr() {
        return abbr;
    }
    public void setAbbr(Abbr abbr) {
        this.abbr = abbr;
    }
    public Org getOrg() {
        return org;
    }
    public void setOrg(Org org) {
        this.org = org;
    }
    public Mkt getMkt() {
        return mkt;
    }
    public void setMkt(Mkt mkt) {
        this.mkt = mkt;
    }
    public Ls getLs() {
        return ls;
    }
    public void setLs(Ls ls) {
        this.ls = ls;
    }
    public Long getIvol() {
        return ivol;
    }
    public void setIvol(Long ivol) {
        this.ivol = ivol;
    }
    public Long getShare() {
        return share;
    }
    public void setShare(Long share) {
        this.share = share;
    }
    public Boolean getAdr() {
        return adr;
    }
    public void setAdr(Boolean adr) {
        this.adr = adr;
    }
    public Double getRat() {
        return rat;
    }
    public void setRat(Double rat) {
        this.rat = rat;
    }
    public String getFv() {
        return fv;
    }
    public void setFv(String fv) {
        this.fv = fv;
    }
    public Fvcur getFvcur() {
        return fvcur;
    }
    public void setFvcur(Fvcur fvcur) {
        this.fvcur = fvcur;
    }
    public String getDss() {
        return dss;
    }
    public void setDss(String dss) {
        this.dss = dss;
    }
    public String getRkd() {
        return rkd;
    }
    public void setRkd(String rkd) {
        this.rkd = rkd;
    }
    public String getCik() {
        return cik;
    }
    public void setCik(String cik) {
        this.cik = cik;
    }
    public Rem getRem() {
        return rem;
    }
    public void setRem(Rem rem) {
        this.rem = rem;
    }
    public String getIsin() {
        return isin;
    }
    public void setIsin(String isin) {
        this.isin = isin;
    }
    public String getSedol() {
        return sedol;
    }
    public void setSedol(String sedol) {
        this.sedol = sedol;
    }
    public Long getCov() {
        return cov;
    }
    public void setCov(Long cov) {
        this.cov = cov;
    }
    public Integer getStat() {
        return stat;
    }
    public void setStat(Integer stat) {
        this.stat = stat;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUpu() {
        return upu;
    }
    public void setUpu(String upu) {
        this.upu = upu;
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
    public Date getCrt() {
        return crt;
    }
    public void setCrt(Date crt) {
        this.crt = crt;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public class Name extends BaseObject{
        private static final long serialVersionUID = -3458628624942003862L;
        private String en;
        private String szh;
        public String getEn() {
            return en;
        }
        public void setEn(String en) {
            this.en = en;
        }
        public String getSzh() {
            return szh;
        }
        public void setSzh(String szh) {
            this.szh = szh;
        }
    }
    public class Abbr extends BaseObject{
        private static final long serialVersionUID = 7430735377136512855L;
        private String en;
        private String py;
        private String szh;
        public String getEn() {
            return en;
        }
        public void setEn(String en) {
            this.en = en;
        }
        public String getPy() {
            return py;
        }
        public void setPy(String py) {
            this.py = py;
        }
        public String getSzh() {
            return szh;
        }
        public void setSzh(String szh) {
            this.szh = szh;
        }
    }
    public class Org extends BaseObject{
        private static final long serialVersionUID = -1238245090663031795L;
        private String en;
        private String id;
        private String szh;
        public String getEn() {
            return en;
        }
        public void setEn(String en) {
            this.en = en;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getSzh() {
            return szh;
        }
        public void setSzh(String szh) {
            this.szh = szh;
        }
    }
    public class Mkt extends BaseObject{
        private static final long serialVersionUID = 3842458765560340827L;
        private String abbr;
        private String code;
        private String en;
        private String szh;
        public String getAbbr() {
            return abbr;
        }
        public void setAbbr(String abbr) {
            this.abbr = abbr;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getEn() {
            return en;
        }
        public void setEn(String en) {
            this.en = en;
        }
        public String getSzh() {
            return szh;
        }
        public void setSzh(String szh) {
            this.szh = szh;
        }
    }
    public class Rem extends BaseObject{
        private static final long serialVersionUID = -4205266501206958294L;
        private String en;
        private String szh;
        public String getEn() {
            return en;
        }
        public void setEn(String en) {
            this.en = en;
        }
        public String getSzh() {
            return szh;
        }
        public void setSzh(String szh) {
            this.szh = szh;
        }
    }
    public class Ls extends BaseObject {
        private static final long serialVersionUID = -8042696927317645466L;
        private String code;
        private String dt;
        private String edt;
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDt() {
            return dt;
        }
        public void setDt(String dt) {
            this.dt = dt;
        }
        public String getEdt() {
            return edt;
        }
        public void setEdt(String edt) {
            this.edt = edt;
        }
    }
    public class Fvcur extends BaseObject{
        private static final long serialVersionUID = 18254953402785938L;
        private String code;
        protected String en;
        protected String szh;
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getEn() {
            return en;
        }
        public void setEn(String en) {
            this.en = en;
        }
        public String getSzh() {
            return szh;
        }
        public void setSzh(String szh) {
            this.szh = szh;
        }
    }
}
