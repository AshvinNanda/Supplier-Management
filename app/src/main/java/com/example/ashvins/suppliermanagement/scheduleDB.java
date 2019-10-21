package com.example.ashvins.suppliermanagement;

public class scheduleDB {
    private String cName;
    private String iName;
    private String cAddr;
    private Integer quantity;
    private String sType;
    private String date;
    private String time;

    public scheduleDB() {

    }

    public scheduleDB(String cName, String iName, String cAddr, Integer quantity, String sType, String date, String time) {
        this.cName = cName;
        this.iName = iName;
        this.cAddr = cAddr;
        this.quantity = quantity;
        this.sType = sType;
        this.date = date;
        this.time = time;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getcAddr() {
        return cAddr;
    }

    public void setcAddr(String cAddr) {
        this.cAddr = cAddr;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
