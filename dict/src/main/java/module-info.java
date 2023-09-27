module com.example.dict {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dict to javafx.fxml;
    exports com.example.dict;
}