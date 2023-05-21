package com.example.videostore.controller;

import com.example.videostore.Item;
import com.example.videostore.ZApplication;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminItemController implements Initializable {
    @FXML
    private Button logOutButton;
    @FXML
    private Button customerButton;
    @FXML
    private TableView<Item> itemTableView = new TableView<>();
    @FXML
    private TableColumn<Item, String> idColumn= new TableColumn<>("ID");
    @FXML
    private TableColumn<Item, String> titleColumn= new TableColumn<>("Title");
    @FXML
    private TableColumn<Item, Item.RentalType> rentalTypeColumn = new TableColumn<>("Type");
    @FXML
    private TableColumn<Item, Item.LoanType> loanTypeColumn= new TableColumn<>("Loan Type");
    @FXML
    private TableColumn<Item, Integer> numberOfCopiesColumn= new TableColumn<>("Copies");
    @FXML
    private TableColumn<Item, Double> rentalFeeColumn= new TableColumn<>("Fee");
    @FXML
    private TableColumn<Item, String> genre = new TableColumn<>("Genre");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            itemDisplay();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void itemDisplay() throws FileNotFoundException {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        rentalTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        loanTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Fee"));
        numberOfCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("Copies  "));
        rentalFeeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalType"));
        genre.setCellValueFactory(new PropertyValueFactory<>("loanType"));


        itemTableView.setItems((ObservableList<Item>) Item.initializeItems());
    }



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

