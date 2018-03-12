package com.junmeng.entity;

import java.io.Serializable;

/**
 * Created by junmeng.xu on 2016/12/2.
 */
public class FileSources implements Serializable {

    private static final long serialVersionUID = -1;

    private Integer id;
    private String fileName;
    private String contentType;

    public FileSources() {
    }

    public FileSources(Integer id, String fileName, String contentType) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "FileSources{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
