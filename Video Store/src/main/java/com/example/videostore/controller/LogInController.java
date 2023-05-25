package com.example.videostore.controller;

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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LogInController implements Initializable {

    private String usernameValid;

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
                List<String> account = Arrays.asList(fileScanner.nextLine().split(","));

                String username = account.get(6);
                String password = account.get(7);

                if (usernameInput.equals(username) && passwordInput.equals(password)) {
                    valid = true;
                    usernameValid = usernameInput;
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

    public String getUsername() {
        return usernameValid;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }   //not using for now
}