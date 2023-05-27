package com.example.videostore.controller;

import com.example.videostore.AllAccount.Customer;
import com.example.videostore.ZApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ReturnItemController implements Initializable {

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
        ArrayList<String> currentItem = new ArrayList<>();
        String userId = LogInController.getIdValid();
        String deleteItem = new String();

        Scanner scanCustomerFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));

        while (scanCustomerFile.hasNext()) {
            List<String> account = Arrays.asList(scanCustomerFile.nextLine().split(","));

            if (account.get(0).equals(userId)) {
                int n = 8;

                while (n != account.size()) {
                    currentItem.add(account.get(n));

                    if (account.get(n).equals(itemIdDelete)) {
                        deleteItem = account.get(n);
                    }
                    n++;
                }
            }
        }
        scanCustomerFile.close();

        Scanner scanCustomerFile2 = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
        while (scanCustomerFile2.hasNext()) {
            String line = scanCustomerFile2.nextLine();
            String[] customerString = line.split(",");
            List<String> customerList = Arrays.asList(customerString);
            ArrayList<String> customerArrayList = new ArrayList<>(customerList);

            if (customerArrayList.get(0).equals(userId)) {
                int x = 0;
                while (x != customerArrayList.size()) {
                    if (customerArrayList.get(x).equals(deleteItem)) {
                        customerArrayList.remove(x);
                    }
                    x++;
                }
                String[] customerArray = customerArrayList.toArray(new String[0]);
                String joined = String.join(",", customerArray);
                tempLines.add(joined);
            } else {
                tempLines.add(line);
            }
        }
        scanCustomerFile2.close();

        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/customers.txt"));
        for (String line : tempLines) {
            pw.println(line);
        }
        pw.close();
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
