package com.csf.cloud.entity.mongo.base;

import com.csf.cloud.entity.mongo.BaseObject;
import org.bson.types.ObjectId;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class BaseDictMarket extends BaseObject {
    private static final long serialVersionUID = 6594306634943172440L;
    private ObjectId id;
    private String code;
    private String ename;
    private String zhsname;
    private String abbr;
    private Shorten shorten;
    private Exchange exchange;
    private Board board;
    private String mkt;
    private String country;
    private String cov;
    private long order;
    private String ls;
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getZhsname() {
        return zhsname;
    }
    public void setZhsname(String zhsname) {
        this.zhsname = zhsname;
    }
    public String getAbbr() {
        return abbr;
    }
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
    public Shorten getShorten() {
        return shorten;
    }
    public void setShorten(Shorten shorten) {
        this.shorten = shorten;
    }
    public Exchange getExchange() {
        return exchange;
    }
    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public String getMkt() {
        return mkt;
    }
    public void setMkt(String mkt) {
        this.mkt = mkt;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCov() {
        return cov;
    }
    public void setCov(String cov) {
        this.cov = cov;
    }
    public long getOrder() {
        return order;
    }
    public void setOrder(long order) {
        this.order = order;
    }
    public String getLs() {
        return ls;
    }
    public void setLs(String ls) {
        this.ls = ls;
    }
    public class Shorten extends BaseObject {
        private static final long serialVersionUID = -19704012201723088L;
        protected String en;
        protected String szh;
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
    public class Exchange extends BaseObject{
        private static final long serialVersionUID = 6515097981401663083L;
        protected String en;
        protected String szh;
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
    public class Board extends BaseObject {
        private static final long serialVersionUID = -7903736008056521381L;
        protected String en;
        protected String szh;
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
