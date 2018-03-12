package com.csf.cloud.entity.mongo.announce;

import com.csf.cloud.entity.mongo.BaseObject;
import com.csf.cloud.entity.mongo.CommonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class AnnouncementStock extends CommonObject {
	private static final long serialVersionUID = -3266752348656293500L;
	private String typ;
    private String title;
    private AnnounceFile file;
    private String valid;
    private List<String> cat;
    private List<AnnounceSecu> secu;
    private String sortcode;
    private String pubid;
    private Date pdt;
    private String pub;   // 发布日期 yyyy-MM-dd
    private String pid;
    private String id;
    private String src;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnnounceFile getFile() {
        return file;
    }

    public void setFile(AnnounceFile file) {
        this.file = file;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public List<String> getCat() {
        return cat;
    }

    public void setCat(List<String> cat) {
        this.cat = cat;
    }

    public void addCat(String newsCat) {
        if (this.cat == null) {
            this.cat = new ArrayList<String>();
        }
        this.cat.add(newsCat);
    }

    public List<AnnounceSecu> getSecu() {
        return secu;
    }

    public void setSecu(List<AnnounceSecu> secu) {
        this.secu = secu;
    }

    public void addSecu(AnnounceSecu newsSecu) {
        if (this.secu == null) {
            this.secu = new ArrayList<AnnounceSecu>();
        }
        this.secu.add(newsSecu);
    }

    public String getSortcode() {
        return sortcode;
    }

    public void setSortcode(String sortcode) {
        this.sortcode = sortcode;
    }

    public String getPubid() {
        return pubid;
    }

    public void setPubid(String pubid) {
        this.pubid = pubid;
    }

    public Date getPdt() {
        return pdt;
    }

    public void setPdt(Date pdt) {
        this.pdt = pdt;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    public static class AnnounceFile extends BaseObject {
		private static final long serialVersionUID = 1366588891278341223L;
		private String fn;
        private String ext;
        private Long bytes;
        private Integer pn;
        private String md5;
        private String url;

        public String getFn() {
            return fn;
        }

        public void setFn(String fn) {
            this.fn = fn;
        }

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }

        public Long getBytes() {
            return bytes;
        }

        public void setBytes(Long bytes) {
            this.bytes = bytes;
        }

        public Integer getPn() {
            return pn;
        }

        public void setPn(Integer pn) {
            this.pn = pn;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    public class AnnounceSecu extends BaseObject {
		private static final long serialVersionUID = -1820459064431363962L;
		private String cd;
        private String mkt;
        private String org;

        public String getCd() {
            return cd;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }

        public String getMkt() {
            return mkt;
        }

        public void setMkt(String mkt) {
            this.mkt = mkt;
        }

        public String getOrg() {
            return org;
        }

        public void setOrg(String org) {
            this.org = org;
        }
    }
}
