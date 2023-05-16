package com.example.videostore;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class Account extends Entity{
    private String name;
    private String address;
    private String phone;
    private String username;
    private String password;
    private ArrayList<Item> itemRented = new ArrayList<Item>();
    private int point = 0;


    public Account(String id, String name, String address, String phone, String username, String password, ArrayList<Item> itemRented, int point) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.itemRented = itemRented;
        this.point = point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setItemRented(ArrayList<Item> itemRented) {
        this.itemRented = itemRented;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Item> getItemRented() {
        return itemRented;
    }

    public int getPoint() {
        return point;
    }
}
