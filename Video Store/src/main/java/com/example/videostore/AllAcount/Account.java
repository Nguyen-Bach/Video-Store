package com.example.videostore.AllAcount;

import com.example.videostore.Entity;
import com.example.videostore.Item;
import com.example.videostore.ItemError;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.SplittableRandom;

public class Account extends Entity {
    private String name;
    private String address;
    private String phone;
    private String username;
    private String password;
    private String type;
    private ArrayList<Item> itemRented = new ArrayList<Item>();
    private int point = 0;

    public Account(String id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
        this.type = "Guest";
    }

    public Account(String id, String username, String password, String address, String phone, String name, String type, int point) {
        this(id, username, password);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.point = point;
        this.type = type;
    }

    public void setId(String id) throws ItemError {
        if (!idValidAccount(id)) {
            throw new ItemError("invalid Id");
        }
        super.setId(id);
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

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public int getPoint() {
        return point;
    }



    public boolean idValidAccount(String id) {
        if (!id.matches("C\\d{3}")) {
            return false;
        } else {
            return true;
        }
    };

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", itemRented=" + itemRented +
                ", point=" + point +
                '}';
    }
}
