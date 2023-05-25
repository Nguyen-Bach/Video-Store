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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeleteCustomerController implements Initializable {


    @FXML
    private TextField idTextField;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button backButton;


    @FXML
    public void deleteButtonClick() throws IOException {
        String id = idTextField.getText();
        ArrayList<String> tempLines = new ArrayList<>();
        Scanner scanFile = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
        while (scanFile.hasNext()) {
            String line = scanFile.nextLine();
            String[] customer = line.split(",");

            if (customer[0].equals(id)) {

            } else {
                tempLines.add(line);
            }
        }
        scanFile.close();

        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/customers.txt"));
        for (String line: tempLines){
            pw.println(line);
        }
        pw.close();
        Customer.initializeCustomer();
    }

    @FXML
    public void backButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminCustomer.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
