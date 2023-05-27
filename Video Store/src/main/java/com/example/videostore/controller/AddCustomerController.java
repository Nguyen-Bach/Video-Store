package com.example.videostore.controller;

import com.example.videostore.Item;
import com.example.videostore.ZApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.ShortStringConverter;
import com.example.videostore.AllAccount.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;

    public void saveButtonClick() throws IOException {
        String id = idField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean error = false;

        if (!Customer.idValidAccount(id)) {
            error = true;
        }
        for (Customer customer : Customer.getArrayCustomers()) {
            if (id.equalsIgnoreCase(customer.getId())) {
                error = true;
                break;
            }
        }

        if (name == null) {
            error  = true;
        }

        if (address == null) {
            error = true;
        }

        if (username == null) {
            error = true;
        }
        if (!phone.matches("\\d{10}")) {
            error = true;
        }

        if (password == null) {
            error = true;
        }

        if (error) {
            errorLabel.setText("Something is wrong with your input");
        } else {
            errorLabel.setText("");
            PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/customers.txt",
                    true));
            pw.println(id + "," + name + "," + address + "," + phone
                    + "," + "0" + "," + "Guest" + "," + username + "," + password);
            pw.close();
        }
        Customer.initializeCustomer();
    }
    public void backButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminCustomer.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
