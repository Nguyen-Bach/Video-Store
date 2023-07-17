package com.example.videostore.controller;

import com.example.videostore.AllAccount.Customer;
import com.example.videostore.Item;
import com.example.videostore.ZApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class RentItemController implements Initializable {
    @FXML
    private Label noItemLabel;
    @FXML
    private TextField idTextField;
    @FXML
    private Button rentButton;
    @FXML
    private Button backButton;


    @FXML
    public void rentButtonClick() throws IOException {
        ArrayList<String> tempLine = new ArrayList<>();
        ArrayList<String> tempLineItem = new ArrayList<>();
        String idItemRent = idTextField.getText();
        boolean itemExits = false;
        boolean inStock = true;
        boolean canRent = true;
        boolean duplicate = false;
        String userId = LogInController.getIdValid();
        String customerType = LogInController.getCustomerType();
        ArrayList<Item> rentedItem = Customer.initializedItemRented();
        int numberOfRentedItem = rentedItem.size();
        String loanType = null;
        int index = 0;

        while (index != rentedItem.size()) {
            if (rentedItem.get(index).getId().equalsIgnoreCase(idItemRent)) {
                duplicate = true;
            }
            index++;
        }

        Scanner scanItemFile = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));
        while (scanItemFile.hasNext()) {
            List<String> item = Arrays.asList(scanItemFile.nextLine().split(","));

            if (item.get(0).equals(idItemRent)) {
                itemExits = true;
                if (Integer.parseInt(item.get(4)) > 0) {
                    inStock = true;
                } else {
                    inStock = false;
                }
                loanType = item.get(3);
            }
        }
        scanItemFile.close();

        if ((customerType.equals("Guest") && numberOfRentedItem == 2)
                || (customerType.equalsIgnoreCase("Guest") && loanType.equalsIgnoreCase("2-day"))) {
            canRent = false;
        }

        if (itemExits && inStock && canRent && !duplicate) {
            Scanner scanCustomerFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
            while (scanCustomerFile.hasNext()) {
                String line = scanCustomerFile.nextLine();
                String[] customerString = line.split(",");
                ArrayList<String> customerArrayList = new ArrayList<>(List.of(customerString));

                if (customerArrayList.get(0).equals(userId)) {
                    customerArrayList.add(idItemRent);
                    if (customerType.equalsIgnoreCase("VIP")) {
                        int point = Integer.parseInt(customerArrayList.get(6));
                        point += 10;
                        customerArrayList.set(6, String.valueOf(point));
                    }
                    String[] customerArray = customerArrayList.toArray(new String[0]);
                    String joinedString = String.join(",", customerArray);
                    tempLine.add(joinedString);
                } else {
                    tempLine.add(line);
                }
            }
            scanCustomerFile.close();

            PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/customers.txt"));
            for (String line : tempLine) {
                printWriter.println(line);
            }
            printWriter.close();

            Scanner itemFileScanner = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));
            while (itemFileScanner.hasNext()) {
                String lineItem = itemFileScanner.nextLine();
                String[] itemString = lineItem.split(",");
                List<String> itemList = Arrays.asList(itemString);
                ArrayList<String> itemArrayList = new ArrayList<>(itemList);

                if (itemArrayList.get(0).equals(idItemRent)) {
                    int numberOfCopies = Integer.parseInt(itemArrayList.get(4));
                    numberOfCopies--;
                    itemArrayList.set(4, String.valueOf(numberOfCopies));
                    String[] itemArray = itemArrayList.toArray(new String[0]);
                    String joinedString = String.join(",", itemArray);

                    tempLineItem.add(joinedString);
                } else {
                    tempLineItem.add(lineItem);
                }
            }
            itemFileScanner.close();

            PrintWriter pwItem = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/items.txt"));
            for (String line : tempLineItem) {
                pwItem.println(line);
            }
            pwItem.close();
            noItemLabel.setText("Item successfully rented");
            noItemLabel.setTextFill(Color.GREEN);
        } else if (!inStock && canRent) {
            noItemLabel.setText("item is not in stock");
        } else if (duplicate) {
            noItemLabel.setText("you can not rent an item twice");
        } else if (!canRent) {
            noItemLabel.setText("you can not rent this item due to\n your account being a guest account");
        } else {
            noItemLabel.setText("Item doesn't exit, please try again");
        }

        Customer.initializeCustomer();
        Customer.initializedItemRented();
        Customer.initializeItemNotRented();
    }


    @FXML
    public void backButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("CustomerRentItem.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
