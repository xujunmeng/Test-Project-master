package com.csf.cloud.service;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by junmeng.xu on 2016/8/2.
 */
public class JobParameter extends HashMap<Object, Object> {

    private static final long serialVersionUID = -7130169966958053505L;

    /** job名称 */
    private String task;
    /** 数据接入方式: delta:增量, history:全量历史 */
    private String type;
    /** 起始日期 */
    private Date from;
    /** 截止日期 */
    private Date to;

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getFrom() {
        return from;
    }
    public void setFrom(Date from) {
        this.from = from;
    }
    public Date getTo() {
        return to;
    }
    public void setTo(Date to) {
        this.to = to;
    }

}
