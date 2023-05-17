package com.example.test1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button changeScene;


    public void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scene2.fxml"));
        Stage stage = (Stage) changeScene.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

}