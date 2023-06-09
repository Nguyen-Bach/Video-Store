package com.example.videostore.controller;

import com.example.videostore.AllAccount.Customer;
import com.example.videostore.Item;
import com.example.videostore.ZApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private Button returnButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Item> itemTableView = new TableView<>();
    @FXML
    private TableColumn<Item, String> idColumn= new TableColumn<>("id");
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
    private TableColumn<Item, Item.Genre> genreColumn = new TableColumn<>("Genre");


    private void itemDisplay() throws FileNotFoundException {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        rentalTypeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalType"));
        loanTypeColumn.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        rentalFeeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalFee"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));

        ObservableList<Item> list = FXCollections.observableArrayList(Customer.initializedItemRented());

        FilteredList<Item> filteredList = new FilteredList<>(list, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(Item -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String titleText = newValue.toLowerCase();
                    String idText = newValue.toLowerCase();

                    if (Item.getId().toLowerCase().contains(idText)) {
                        return true;
                    } else if (Item.getTitle().toLowerCase().contains(titleText)) {
                        return true;
                    } else
                        return false;
                }));
        SortedList<Item> sortedData = new SortedList<>(filteredList);

        sortedData.comparatorProperty().bind(itemTableView.comparatorProperty());

        itemTableView.setItems(sortedData);
        itemTableView.getColumns().addAll(idColumn, titleColumn, rentalTypeColumn, loanTypeColumn, rentalFeeColumn, genreColumn);
    }

    public void logOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("LogInScreen.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    public void rentButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("CustomerRentItem.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

    public void returnButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("ReturnItem.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            itemDisplay();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
