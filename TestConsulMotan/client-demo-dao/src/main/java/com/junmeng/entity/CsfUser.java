package com.junmeng.entity;

import com.junmeng.common.BaseColSeriObject;

import java.util.Date;

/**
 * Created by junmeng.xu on 2016/11/11.
 */
public class CsfUser extends BaseColSeriObject {

    private static final long serialVersionUID = 1L;
    private Integer uid;
    private String name;
    private Integer age;
    private String represent;
    private Date crt;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRepresent() {
        return represent;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }

    public Date getCrt() {
        return crt;
    }

    public void setCrt(Date crt) {
        this.crt = crt;
    }
}
