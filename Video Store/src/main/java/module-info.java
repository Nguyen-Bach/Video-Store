module com.example.videostore {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.videostore to javafx.fxml;
    exports com.example.videostore;
    exports com.example.videostore.controller;
    opens com.example.videostore.controller to javafx.fxml;
    exports com.example.videostore.AllAccount;
    opens com.example.videostore.AllAccount to javafx.fxml;

}