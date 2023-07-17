package com.example.videostore.controller;

import com.example.videostore.AllAccount.Customer;
import com.example.videostore.ZApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ReturnItemController implements Initializable {
    @FXML
    private Label returnableLabel;
    @FXML
    private TextField idTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button returnButton;

    @FXML
    public void returnButtonClick() throws IOException {
        String itemIdDelete = idTextField.getText();
        ArrayList<String> tempLines = new ArrayList<>();
        ArrayList<String> tempLinesItem = new ArrayList<>();
        ArrayList<String> currentItem = new ArrayList<>();
        String userId = LogInController.getIdValid();
        String returnItem = new String();
        boolean returnable = false;

        Scanner scanCustomerFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));

        while (scanCustomerFile.hasNext()) {
            List<String> account = Arrays.asList(scanCustomerFile.nextLine().split(","));

            if (account.get(0).equals(userId)) {
                int n = 9;

                while (n != account.size()) {
                    currentItem.add(account.get(n));

                    if (account.get(n).equals(itemIdDelete)) {
                        returnItem = account.get(n);
                        returnable = true;
                        break;
                    }
                    n++;
                }
            }
        }
        scanCustomerFile.close();

        if (returnable) {
            returnableLabel.setText("Successful");
        } else {
            returnableLabel.setText("Invalid Id");
        }

        Scanner scanCustomerFile2 = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
        while (scanCustomerFile2.hasNext()) {
            String line = scanCustomerFile2.nextLine();
            String[] customerString = line.split(",");
            List<String> customerList = Arrays.asList(customerString);
            ArrayList<String> customerArrayList = new ArrayList<>(customerList);

            if (customerArrayList.get(0).equals(userId)) {
                int x = 0;
                while (x <= customerArrayList.size()) {
                    if (customerArrayList.get(x).equals(returnItem)) {
                        customerArrayList.remove(x);
                        break;
                    }
                    x++;
                }
                String[] customerArray = customerArrayList.toArray(new String[0]);
                String joinedString = String.join(",", customerArray);
                tempLines.add(joinedString);
            } else {
                tempLines.add(line);
            }
        }
        scanCustomerFile2.close();
        PrintWriter printWriterCustomer = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/customers.txt"));
        for (String line : tempLines) {
            printWriterCustomer.println(line);
        }
        printWriterCustomer.close();

        Scanner scanItemFile = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));
        while (scanItemFile.hasNext()) {
            String lineItem = scanItemFile.nextLine();
            String[] itemString = lineItem.split(",");
            List<String> itemList = Arrays.asList(itemString);
            ArrayList<String> itemArrayList = new ArrayList<>(itemList);

            if (itemArrayList.get(0).equals(returnItem)) {
                int numberOfCopies = Integer.parseInt(itemArrayList.get(4));
                numberOfCopies++;
                itemArrayList.set(4, String.valueOf(numberOfCopies));
                String[] itemArray = itemArrayList.toArray(new String[0]);
                String joinedString = String.join(",", itemArray);

                tempLinesItem.add(joinedString);
            } else {
                tempLinesItem.add(lineItem);
            }
        }
        scanItemFile.close();
        PrintWriter printWriterItem = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/items.txt"));
        for (String line : tempLinesItem) {
            printWriterItem.println(line);
        }
        printWriterItem.close();

        Customer.initializeCustomer();
        Customer.initializedItemRented();
        Customer.initializeItemNotRented();
    }

    @FXML
    public void backButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("CustomerItem.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
