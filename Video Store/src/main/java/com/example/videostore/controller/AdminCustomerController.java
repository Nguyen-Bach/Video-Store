package com.example.videostore.controller;

import com.example.videostore.AllAccount.Customer;
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

public class AdminCustomerController implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button itemButton;
    @FXML
    private Button addCustomerButton;
    @FXML
    private TableView<Customer> customerTableView = new TableView<>();
    @FXML
    private TableColumn<Customer, String> idColumn = new TableColumn<>("Id");
    @FXML
    private TableColumn<Customer, String> usernameColumn = new TableColumn<>("Username");
    @FXML
    private TableColumn<Customer, String> passwordColumn = new TableColumn<>("Password");
    @FXML
    private TableColumn<Customer, String> addressColumn = new TableColumn<>("Address");
    @FXML
    private TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone");
    @FXML
    private TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
    @FXML
    private TableColumn<Customer, String> typeColumn = new TableColumn<>("Type");
    @FXML
    private TableColumn<Customer, String> pointColumn = new TableColumn<>("Point");



    private void customerDisplay() throws FileNotFoundException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        pointColumn.setCellValueFactory(new PropertyValueFactory<>("Point"));

        ObservableList<Customer> list = FXCollections.observableArrayList(Customer.initializeCustomer());

        FilteredList<Customer> filteredList = new FilteredList<>(list, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(Customer -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String nameText = newValue.toLowerCase();
            String idText = newValue.toLowerCase();
            String typeText = newValue.toLowerCase();

            if (Customer.getId().toLowerCase().contains(idText)) {
                return true;
            } else if (Customer.getName().toLowerCase().contains(nameText)) {
                return true;
            } else if (Customer.getType().toLowerCase().contains(typeText)){
                return true;
            } else {
                return false;
            }
        }));
        SortedList<Customer> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(customerTableView.comparatorProperty());

        customerTableView.setItems(sortedList);
        customerTableView.getColumns().addAll(idColumn, nameColumn, addressColumn, phoneColumn,
                usernameColumn, passwordColumn, typeColumn, pointColumn);
    }
    public void itemButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AdminItem.fxml"));
        Stage stage = (Stage) itemButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

    public void logOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("LogInScreen.fxml"));
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    private void addCustomerButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("AddCustomer.fxml"));
        Stage stage = (Stage) addCustomerButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
    @FXML
    private void deleteCustomerButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZApplication.class.getResource("DeleteCustomer.fxml"));
        Stage stage = (Stage) deleteCustomerButton.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerDisplay();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
