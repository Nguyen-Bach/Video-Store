package com.example.videostore.Utility;

import com.example.videostore.AllAccount.Customer;

public class AccountUtility {   //not using this class

    public Customer convert(String string) {
        String list[] = string.split(",");

        String id = list[0];
        String name = list[1];
        String address = list[2];
        String phone = list[3];
        String type = list[5];
        String username = list[6];
        String password = list[7];

        return new Customer(id, username, password, address, phone, name, type, 0);
        }




}
