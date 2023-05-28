package com.example.videostore.AllAccount;

import com.example.videostore.Item;
import com.example.videostore.ItemError;
import com.example.videostore.controller.LogInController;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.CollationElementIterator;
import java.util.*;

public class Customer {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String username;
    private String password;
    private String type;
    private static ArrayList<Item> itemRented = new ArrayList<>();
    private static ArrayList<Item> itemNotRented = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private int point = 0;



    public Customer(String id, String username, String password, String address, String phone, String name, String type, int point) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public static ArrayList<Customer> getArrayCustomers() {
        return customers;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        Customer.customers = customers;
    }

    public static boolean idValidAccount(String id) {
        return id.matches("C\\d{3}");
    }

    public static ArrayList<Item> initializedItemRented() throws FileNotFoundException {
        itemRented.clear();
        ArrayList<String> idItems = new ArrayList<>();
        int index = 0;
        String idValid = LogInController.getIdValid();

        Scanner scanCustomerFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
        Scanner scanItemFile = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));

        while (scanCustomerFile.hasNext()) {
            List<String> account = Arrays.asList(scanCustomerFile.nextLine().split(","));

            if (account.get(0).equals(idValid)) {
                int n = 9;

                while (n != account.size()) {
                    idItems.add(account.get(n));
                    n++;
                }
            }
        }
        Collections.sort(idItems);
        while (scanItemFile.hasNext()) {
            List<String> item = Arrays.asList(scanItemFile.nextLine().split(","));


            if (index < idItems.size()) {
                if (item.get(0).equals(idItems.get(index))) {

                    String id = item.get(0);
                    String title = item.get(1);

                    String stringRentalType = item.get(2);
                    Item.RentalType rentalType = Item.convertRentalType(stringRentalType);

                    String stringLoanType = item.get(3);
                    Item.LoanType loanType = Item.convertLoanType(stringLoanType);

                    int numOfCopies = Integer.parseInt(item.get(4));
                    double rentalFee = Double.parseDouble(item.get(5));

                    String stringGenre = item.get(6);
                    Item.Genre genre = Item.convertGenre(stringGenre);

                    itemRented.add(new Item(id, title, rentalType, loanType, numOfCopies, rentalFee, genre));
                    index++;
                }
            }

        }
        return itemRented;
    }

    public static ArrayList<Item> initializeItemNotRented() throws FileNotFoundException {
        itemNotRented.clear();
        ArrayList<String> idItems = new ArrayList<>();
        int index = 0;
        String idValid = LogInController.getIdValid();
        ArrayList<String> idItemNotRented = new ArrayList<>();

        Scanner scanCustomerFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
        Scanner scanItemFile = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));

        while (scanCustomerFile.hasNext()) {
            List<String> account = Arrays.asList(scanCustomerFile.nextLine().split(","));

            if (account.get(0).equals(idValid)) {
                int n = 9;

                while (n != account.size()) {
                    idItems.add(account.get(n));
                    n++;
                }
            }
        }
        Collections.sort(idItems);
        scanItemFile.close();

        Scanner scanItemFileForItem = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));
        while (scanItemFileForItem.hasNext()) {
            List<String> item = Arrays.asList(scanItemFileForItem.nextLine().split(","));
            idItemNotRented.add(item.get(0));
        }
        scanItemFileForItem.close();

        idItemNotRented.removeAll(idItems);

        Collections.sort(idItemNotRented);

        index = 0;
        Scanner scanItemNotRentedFile = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));
        while (scanItemNotRentedFile.hasNext()) {
            List<String> item = Arrays.asList(scanItemNotRentedFile.nextLine().split(","));


            if (index < idItemNotRented.size()) {
                if (item.get(0).equals(idItemNotRented.get(index))) {

                    String id = item.get(0);
                    String title = item.get(1);

                    String stringRentalType = item.get(2);
                    Item.RentalType rentalType = Item.convertRentalType(stringRentalType);

                    String stringLoanType = item.get(3);
                    Item.LoanType loanType = Item.convertLoanType(stringLoanType);

                    int numOfCopies = Integer.parseInt(item.get(4));
                    double rentalFee = Double.parseDouble(item.get(5));

                    String stringGenre = item.get(6);
                    Item.Genre genre = Item.convertGenre(stringGenre);

                    itemNotRented.add(new Item(id, title, rentalType, loanType, numOfCopies, rentalFee, genre));
                    index++;
                }
            }

        }
        scanItemNotRentedFile.close();

        return itemNotRented;
    }

    public static ArrayList<Customer> initializeCustomer() throws FileNotFoundException {
        customers.clear();
        Scanner scanFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
        try {
            while (scanFile.hasNext()) {
                List<String> account = Arrays.asList(scanFile.nextLine().split(","));

                String id = account.get(0);
                String username = account.get(7);
                String password = account.get(8);
                String address = account.get(2);
                String phone = account.get(3);
                String name = account.get(1);
                String type = account.get(5);
                int point = Integer.parseInt(account.get(6));

                customers.add(new Customer(id, username, password, address, phone, name, type, point));
            }
        } catch (Exception e) {
            System.out.println("no file found");
        }
        return customers;
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
