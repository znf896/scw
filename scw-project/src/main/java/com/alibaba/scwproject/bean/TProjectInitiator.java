package com.alibaba.scwproject.bean;

public class TProjectInitiator {
    private Integer id;

    private String selfintroduction;

    private String detailintroduction;

    private String telphone;

    private String hotline;

    private Integer projected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelfintroduction() {
        return selfintroduction;
    }

    public void setSelfintroduction(String selfintroduction) {
        this.selfintroduction = selfintroduction == null ? null : selfintroduction.trim();
    }

    public String getDetailintroduction() {
        return detailintroduction;
    }

    public void setDetailintroduction(String detailintroduction) {
        this.detailintroduction = detailintroduction == null ? null : detailintroduction.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline == null ? null : hotline.trim();
    }

    public Integer getProjected() {
        return projected;
    }

    public void setProjected(Integer projected) {
        this.projected = projected;
    }
}