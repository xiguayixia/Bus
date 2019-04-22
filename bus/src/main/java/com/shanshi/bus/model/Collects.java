package com.shanshi.bus.model;

import org.springframework.stereotype.Component;

@Component
public class Collects {
    private Integer id;

    private Integer userid;

    private String buslinenum;

    private String buslinecode;

    private String buslinename;

    private Boolean collects;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBuslinenum() {
        return buslinenum;
    }

    public void setBuslinenum(String buslinenum) {
        this.buslinenum = buslinenum == null ? null : buslinenum.trim();
    }

    public String getBuslinecode() {
        return buslinecode;
    }

    public void setBuslinecode(String buslinecode) {
        this.buslinecode = buslinecode == null ? null : buslinecode.trim();
    }

    public String getBuslinename() {
        return buslinename;
    }

    public void setBuslinename(String buslinename) {
        this.buslinename = buslinename == null ? null : buslinename.trim();
    }

    public Boolean getCollects() {
        return collects;
    }

    public void setCollects(Boolean collects) {
        this.collects = collects;
    }
}