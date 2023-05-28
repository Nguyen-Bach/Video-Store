package com.example.videostore.controller;

import com.example.videostore.Item;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeleteItemController implements Initializable {
    @FXML
    private TextField idTextField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;


    @FXML
    public void deleteButtonClick() throws IOException {
        String id = idTextField.getText();
        ArrayList<String> tempLines = new ArrayList<>();
        Scanner scanItemFile = new Scanner(new File("src/main/resources/com/example/videostore/items.txt"));
        while (scanItemFile.hasNext()) {
            String line = scanItemFile.nextLine();
            String[] item = line.split(",");

            if (item[0].equals(id)) {

            } else {
                tempLines.add(line);
            }
        }
        scanItemFile.close();

        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/items.txt"));
        for (String line: tempLines){
            pw.println(line);
        }
        pw.close();
        Item.initializeItems();
    }

    @FXML
    public void backButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminItem.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
