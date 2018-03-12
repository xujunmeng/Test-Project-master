package com.csf.cloud.entity.mongo.base;

import com.csf.cloud.entity.mongo.BaseObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by junmeng.xu on 2016/8/2.
 */
public class BaseBond extends BaseObject {
    private static final long serialVersionUID = -1036866042324638552L;
    private ObjectId _id;
    private Abbr abbr;
    private String bid;
    private String code;
    private Date crt;
    private String cru;
    private Form form;
    private In in;
    private Mkt mkt;
    private Name name;
    private Org org;
    private Date paydt;
    private Rem rem;
    private Double repay;
    private String sid;
    private String src;
    private Integer stat;
    private String tick;
    private Typ typ;
    private Date upt;
    private String upu;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Abbr getAbbr() {
        return abbr;
    }

    public void setAbbr(Abbr abbr) {
        this.abbr = abbr;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCrt() {
        return crt;
    }

    public void setCrt(Date crt) {
        this.crt = crt;
    }

    public String getCru() {
        return cru;
    }

    public void setCru(String cru) {
        this.cru = cru;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public In getIn() {
        return in;
    }

    public void setIn(In in) {
        this.in = in;
    }

    public Mkt getMkt() {
        return mkt;
    }

    public void setMkt(Mkt mkt) {
        this.mkt = mkt;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Date getPaydt() {
        return paydt;
    }

    public void setPaydt(Date paydt) {
        this.paydt = paydt;
    }

    public Rem getRem() {
        return rem;
    }

    public void setRem(Rem rem) {
        this.rem = rem;
    }

    public Double getRepay() {
        return repay;
    }

    public void setRepay(Double repay) {
        this.repay = repay;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getTick() {
        return tick;
    }

    public void setTick(String tick) {
        this.tick = tick;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public Date getUpt() {
        return upt;
    }

    public void setUpt(Date upt) {
        this.upt = upt;
    }

    public String getUpu() {
        return upu;
    }

    public void setUpu(String upu) {
        this.upu = upu;
    }

    public class Abbr extends BaseObject {

        private static final long serialVersionUID = 8084732784809272496L;
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

    public class Form extends BaseObject {
        private static final long serialVersionUID = 2873724961951259116L;
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

    public class In extends BaseObject {
        private static final long serialVersionUID = -3540462228677487856L;
        private Date end;
        private Long freq;
        private Mode mode;
        private String pay;
        private Paymode paymode;
        private Rattyp rattyp;
        private Date st;

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }

        public Long getFreq() {
            return freq;
        }

        public void setFreq(Long freq) {
            this.freq = freq;
        }

        public Mode getMode() {
            return mode;
        }

        public void setMode(Mode mode) {
            this.mode = mode;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public Paymode getPaymode() {
            return paymode;
        }

        public void setPaymode(Paymode paymode) {
            this.paymode = paymode;
        }

        public Rattyp getRattyp() {
            return rattyp;
        }

        public void setRattyp(Rattyp rattyp) {
            this.rattyp = rattyp;
        }

        public Date getSt() {
            return st;
        }

        public void setSt(Date st) {
            this.st = st;
        }

    }

    public class Mode extends BaseObject {
        private static final long serialVersionUID = 512430608507892639L;
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

    public class Paymode extends BaseObject {
        private static final long serialVersionUID = 8430860327632653709L;
        private String code;
        private String en;
        private String szh;

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

    public class Rattyp extends BaseObject {
        private static final long serialVersionUID = 3063591401039785866L;
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

    public class Mkt extends BaseObject {
        private static final long serialVersionUID = 3542414637380288772L;
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

    public class Name extends BaseObject {
        private static final long serialVersionUID = 2862438613858806158L;
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

    public class Org extends BaseObject {
        private static final long serialVersionUID = 3544577797084623287L;
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

    public class Rem extends BaseObject {
        private static final long serialVersionUID = -3516217567081729181L;
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

    public class Typ extends BaseObject {
        private static final long serialVersionUID = 2916491402606564703L;
        private String code;
        private String en;
        private String szh;

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
