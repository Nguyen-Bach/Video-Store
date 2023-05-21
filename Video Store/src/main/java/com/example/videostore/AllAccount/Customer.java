package com.example.videostore.AllAccount;

import com.example.videostore.Item;
import com.example.videostore.ItemError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String username;
    private String password;
    private String type;
    private ArrayList<Item> itemRented = new ArrayList<Item>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private int point = 0;

    public Customer(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = "Guest";
    }

    public Customer(String id, String username, String password, String address, String phone, String name, String type, int point) {
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
        this.id = id;
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

    public String getId() {
        return id;
    }

    public boolean idValidAccount(String id) {
        if (!id.matches("C\\d{3}")) {
            return false;
        } else {
            return true;
        }
    }

    public static void initializeCustomer() throws FileNotFoundException {
        customers.clear();
        Scanner scanFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
        try {
            while (scanFile.hasNext()) {
                List<String> account = Arrays.asList(scanFile.nextLine().split(","));

                String id = account.get(0);
                String username = account.get(6);
                String password = account.get(7);
                String address = account.get(2);
                String phone = account.get(3);
                String name = account.get(1);
                String type = account.get(5);

                customers.add(new Customer(id, username, password, address, phone, name, type, 0));
            }
        } catch (Exception e) {
            System.out.println("no file found");
        }
    }




    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", itemRented=" + itemRented +
                ", point=" + point +
                '}';
    }
}
