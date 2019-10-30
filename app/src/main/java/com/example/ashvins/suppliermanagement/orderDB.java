package com.example.ashvins.suppliermanagement;

public class orderDB {
    private String Date;
    private String item_name;
    private int  quantity;
    private String status;

    public orderDB() {

    }

    public orderDB(String date, String item_name, int quantity, String status) {
        Date = date;
        this.item_name = item_name;
        this.quantity = quantity;
        this.status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
