module lk.ijse.foodcity {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens lk.ijse.foodcity.controller to javafx.fxml;
    opens lk.ijse.foodcity.dto to java.base;
    exports lk.ijse.foodcity;
    exports lk.ijse.foodcity.controller;
    exports lk.ijse.foodcity.dto;
}
