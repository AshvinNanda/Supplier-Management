package com.example.ashvins.suppliermanagement;

public class supplierDB {
    private String itemName;
    private String cName;
    private String cAddr;
    private Long landline;
    private Long mobile;
    private String email;

    public supplierDB() {

    }

    public supplierDB(String itemName, String cName, String cAddr, Long landline, Long mobile, String email) {
        this.itemName = itemName;
        this.cName = cName;
        this.cAddr = cAddr;
        this.landline = landline;
        this.mobile = mobile;
        this.email = email;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddr() {
        return cAddr;
    }

    public void setcAddr(String cAddr) {
        this.cAddr = cAddr;
    }

    public Long getLandline() {
        return landline;
    }

    public void setLandline(Long landline) {
        this.landline = landline;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
