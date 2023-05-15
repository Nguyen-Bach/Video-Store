package com.example.videostore;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {
    @FXML
    private Button exitButton;


    @FXML
    public void exitClick() {
        Platform.exit();
    }
}