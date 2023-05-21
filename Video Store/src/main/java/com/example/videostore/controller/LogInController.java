package com.example.videostore.controller;

import com.example.videostore.AllAccount.admin;
import com.example.videostore.ZApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LogInController implements Initializable {

    @FXML
    private Button logInButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label wrongId;

    public void logInClick() throws IOException {
        String usernameInput = usernameField.getText().toString();
        String passwordInput = passwordField.getText().toString();
        boolean valid = false;

        try {
            Scanner fileScanner = new Scanner(new File("src/main/resources/com/example/videostore/customers.txt"));
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                String id = tokenizer.nextToken();
                String name = tokenizer.nextToken();
                String address = tokenizer.nextToken();
                String phone = tokenizer.nextToken();
                String NULL = tokenizer.nextToken();
                String role = tokenizer.nextToken();
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                if (usernameInput.equals(username) && passwordInput.equals(password)) {
                    valid = true;
                }
            }
            if (valid) {
                FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("CustomerItem.fxml"));
                Stage stage = (Stage) logInButton.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
            } else  if (usernameField.getText().toString().equals("admin") && passwordField.getText().toString().equals("admin")){
                FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminItem.fxml"));
                Stage stage = (Stage) logInButton.getScene().getWindow();
                stage.setScene(new Scene(fxmlLoader.load()));
            } else {
                wrongId.setText("wrong username or password");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void exitClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Are you sure you want to exit");

        if(alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }   //not using for now
}