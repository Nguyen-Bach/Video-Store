package com.example.videostore.controller;

import com.example.videostore.ZApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminItemController {
    @FXML
    private Button logOutButton;
    @FXML
    private Button customerButton;

    public void customerButtonClick() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminCustomer.fxml"));
            Stage stage = (Stage) customerButton.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
    }

    public void logOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("LogInScreen.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

}
