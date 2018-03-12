package com.csf.cloud.entity.mongo.announce;

import com.csf.cloud.entity.mongo.CommonObject;

import java.sql.Timestamp;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class AnnouncementTemp extends CommonObject {
    private static final long serialVersionUID = 6687576848877075326L;
    private String annSid;
    private String annTitle;
    private String annSortcode;
    private Timestamp annPdt;
    private String ext;
    private String tickAForPeriodic;
    private Boolean isSpecialName;

    public String getAnnSid() {
        return annSid;
    }
    public void setAnnSid(String annSid) {
        this.annSid = annSid;
    }
    public String getAnnTitle() {
        return annTitle;
    }
    public void setAnnTitle(String annTitle) {
        this.annTitle = annTitle;
    }
    public String getAnnSortcode() {
        return annSortcode;
    }
    public void setAnnSortcode(String annSortcode) {
        this.annSortcode = annSortcode;
    }
    public Timestamp getAnnPdt() {
        return annPdt;
    }
    public void setAnnPdt(Timestamp annPdt) {
        this.annPdt = annPdt;
    }
    public String getExt() {
        return ext;
    }
    public void setExt(String ext) {
        this.ext = ext;
    }
    public String getTickAForPeriodic() {
        return tickAForPeriodic;
    }
    public void setTickAForPeriodic(String tickAForPeriodic) {
        this.tickAForPeriodic = tickAForPeriodic;
    }
    public Boolean getIsSpecialName() {
        return isSpecialName;
    }
    public void setIsSpecialName(Boolean isSpecialName) {
        this.isSpecialName = isSpecialName;
    }
}
