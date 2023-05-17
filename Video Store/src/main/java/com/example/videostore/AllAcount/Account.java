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
    private String role;
    private ArrayList<Item> itemRented = new ArrayList<Item>();
    private int point = 0;

    public Account(String id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

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

    public void setRole(String role) {
        this.role = role;
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

    public String getRole() {
        return role;
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
