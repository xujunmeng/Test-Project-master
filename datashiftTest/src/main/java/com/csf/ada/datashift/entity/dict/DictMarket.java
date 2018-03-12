package com.csf.ada.datashift.entity.dict;

import org.bson.types.ObjectId;

import com.csf.app.dal.entity.MultiLanguage;
import com.csf.platform.dataentry.entity.dict.DictBase;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "dict_market", noClassnameStored = true)
public class DictMarket extends DictBase {
    @Id
    private ObjectId id;
    private String code = "";
    private String ename = "";
    private String zhsname = "";
    private String abbr = "";
    @Embedded
    private MultiLanguage shorten;
    @Embedded
    private MultiLanguage exchange;
    @Embedded
    private MultiLanguage board;
    private String mkt = "";
    private String country = "";
    private String cov = "";
    private long order;
    private String ls = "";

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public MultiLanguage getExchange() {
        return exchange;
    }

    public void setExchange(MultiLanguage exchange) {
        this.exchange = exchange;
    }

    public MultiLanguage getBoard() {
        return board;
    }

    public void setBoard(MultiLanguage board) {
        this.board = board;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public MultiLanguage getShorten() {
        return shorten;
    }

    public void setShorten(MultiLanguage shorten) {
        this.shorten = shorten;
    }

    public String getMkt() {
        return mkt;
    }

    public void setMkt(String mkt) {
        this.mkt = mkt;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
