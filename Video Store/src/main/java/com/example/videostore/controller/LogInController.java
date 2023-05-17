package com.example.videostore.controller;

import com.example.videostore.ZApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        if (usernameField.getText().toString().equals("admin") && passwordField.getText().toString().equals("admin")) {
            FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminItem.fxml"));
            Stage stage = (Stage) logInButton.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
        } else {
            wrongId.setText("Wrong username or password, please try again");
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