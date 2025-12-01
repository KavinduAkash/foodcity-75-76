package lk.ijse.foodcity.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import lk.ijse.foodcity.model.CustomerModel;
import lk.ijse.foodcity.model.ItemModel;

/**

SQL query for the order table creation
======================================
CREATE TABLE order (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

SQL query for the order item table creation
===========================================
CREATE TABLE order_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    qty INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES order(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);
* 
 */

public class OrderController implements Initializable {

    @FXML
    private ComboBox comboCustomerId;
    
    @FXML
    private ComboBox comboItemId;
    
    private final CustomerModel customerModel = new CustomerModel();
    private final ItemModel itemModel = new ItemModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadCustomerIds();
        loadItemIds();
        
    }    
    
    private void loadCustomerIds() {
    
        try {
        
            List<String> customerIdList = customerModel.getAllCustomerIds();
            
            ObservableList<String> obList =  FXCollections.observableArrayList();
            
            /*for(String id: customerIdList) {
                obList.add(id);
            }*/
            
            obList.addAll(customerIdList);
            
            comboCustomerId.setItems(obList);
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }
    
    private void loadItemIds() {
    
         try {
        
            List<String> itemIdList = itemModel.getAllItemIds();
            
            ObservableList<String> obList =  FXCollections.observableArrayList();
            
            obList.addAll(itemIdList);
            
            comboItemId.setItems(obList);
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }
    
}
