package com.example.videostore.controller;

import com.example.videostore.ItemError;
import com.example.videostore.ZApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.videostore.Item;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField rentField;
    @FXML
    private TextField loanField;
    @FXML
    private TextField numberOfCopiesField;
    @FXML
    private TextField feeField;
    @FXML
    private TextField genreField;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private Label errorLabel;


    public void saveButtonClick() throws IOException {
        String id = idField.getText();
        String title = titleField.getText();
        String rentType = rentField.getText();
        String loanType = loanField.getText();
        int numberOfCopies = Integer.parseInt(numberOfCopiesField.getText());
        double fee = Double.parseDouble(feeField.getText());
        String genre = genreField.getText();

        boolean error = false;

        if (!Item.isValidId(id)) {
            error = true;
        }
        for (Item item : Item.getArrayItems()) {
            if (id.substring(1,4).equalsIgnoreCase(item.getId().substring(1,4))) {
                error = true;
                break;
            }
        }

        if (title == null) {
            error = true;
        }

        if (rentType == null) {
            error = true;
        } else if (!Item.isRentalTypeValid(rentType)) {
            error = true;
        }

        if (loanType == null) {
            error = true;
        } else if (!Item.isLoanTypeValid(loanType)) {
            error = true;
        }

        if (numberOfCopies <= 0 ) {
            error = true;
        }
        if (fee <= 0) {
            error = true;
        }

        if (genre == null) {
            error = true;
        } else if (rentType.equalsIgnoreCase("game")) {
            if (!genre.equalsIgnoreCase("NG")) {
                error = true;
            }
        } else if (!Item.isGenreValid(genre)) {
            error = true;
        }

        if (error) {
            errorLabel.setText("Something is wrong with your input");
        } else {
           errorLabel.setText("");
           PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/com/example/videostore/items.txt",
                   true));
           pw.println(id + "," + title + "," + rentType + "," + loanType + ","
                   + numberOfCopies + "," + fee + "," + genre);
           pw.close();
        }
        Item.initializeItems();
    }

    public void backButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminItem.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
