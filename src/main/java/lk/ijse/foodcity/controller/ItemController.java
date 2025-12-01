package lk.ijse.foodcity.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.foodcity.dto.CustomerDTO;
import lk.ijse.foodcity.dto.ItemDTO;
import lk.ijse.foodcity.model.ItemModel;

/*

SQL query for the item table creation
=====================================
CREATE TABLE item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    qty INT NOT NULL DEFAULT 0,
    unit_price DECIMAL(10,2) NOT NULL DEFAULT 0.00
);

*/

public class ItemController implements Initializable {

    @FXML
    private TableColumn<ItemDTO, Integer> colId;

    @FXML
    private TableColumn<ItemDTO, String> colName;

    @FXML
    private TableColumn<ItemDTO, Double> colPrice;
    
    @FXML
    private TableColumn<ItemDTO, Integer> colQty;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;
    
    @FXML
    private TextField priceField;

    @FXML
    private TextField qtyField;

    @FXML
    private TableView<ItemDTO> tblItem;
    
    private final ItemModel itemModel = new ItemModel();
    
    private final String ITEM_ID_REGEX = "^[0-9]+$";
    private final String ITEM_NAME_REGEX = "^[A-Za-z]{3,}$";
    private final String ITEM_QTY_REGEX = "^[0-9]+$";
    private final String ITEM_PRICE_REGEX = "^[0-9]{2,}(\\.[0-9]?)$";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        handleLoadItemTable();
    }    
    
    @FXML
    void handleDeleteItem(ActionEvent event) {
        try {
            
            String itemId = idField.getText();
            
            if(!itemId.matches(ITEM_ID_REGEX)) {
                    
                    new Alert(Alert.AlertType.ERROR, "Invalid item id!").show();
            
            } else {
                    
                    ItemDTO result = itemModel.searchItem(itemId);
                
                    if(result!=null) {
                        
                        nameField.setText(result.getName());
                        qtyField.setText(String.valueOf(result.getQty()));
                        
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                    }
                    
            }
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void handleResetItem(ActionEvent event) {
        cleanFields();
    }

    @FXML
    void handleSaveItem(ActionEvent event) {
        try {
        
            String itemName = nameField.getText();
            String itemPrice = priceField.getText();
            String itemQty = qtyField.getText();
            
            
            if(!itemName.matches(ITEM_NAME_REGEX)) {
                
                    new Alert(Alert.AlertType.ERROR, "Invalid item name!").show();
            
            } else if(!itemPrice.matches(ITEM_PRICE_REGEX)) {
            
                    new Alert(Alert.AlertType.ERROR, "Invalid item price!").show();
                
            } else if(!itemQty.matches(ITEM_QTY_REGEX)) {
            
                    new Alert(Alert.AlertType.ERROR, "Invalid item qty!").show();
                
            } else {
                    
                    boolean result = itemModel.saveItem(new ItemDTO(itemName, Double.parseDouble(itemPrice), Integer.parseInt(itemQty)));
                
                    if(result) {
                        new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!").show();
                        cleanFields();
                        handleLoadItemTable();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                    }
                    
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void handleUpdateItem(ActionEvent event) {
        try {
            String itemId = idField.getText();
            String itemName = nameField.getText();
            String itemPrice = priceField.getText();
            String itemQty = qtyField.getText();
            if(!itemId.matches(ITEM_ID_REGEX)) {
                    
                    new Alert(Alert.AlertType.ERROR, "Invalid item id!").show();
            
            } else if(!itemName.matches(ITEM_NAME_REGEX)) {
                
                    new Alert(Alert.AlertType.ERROR, "Invalid item name!").show();
            
            } else if(!itemPrice.matches(ITEM_PRICE_REGEX)) {
            
                    new Alert(Alert.AlertType.ERROR, "Invalid item price!").show();
                
            } else if(!itemQty.matches(ITEM_QTY_REGEX)) {
            
                    new Alert(Alert.AlertType.ERROR, "Invalid item qty!").show();
                
            } else {
                    
                    boolean result = itemModel.updateItem(new ItemDTO(Integer.parseInt(itemId), itemName, Double.parseDouble(itemPrice), Integer.parseInt(itemQty)));
                
                    if(result) {
                        new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();
                        cleanFields();
                        handleLoadItemTable();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                    }
                    
            }
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    
    @FXML
    void handleSearchItem(KeyEvent event) {
        try {
        
            if(event.getCode() == KeyCode.ENTER) {
        
                String id = idField.getText();

                if(!id.matches(ITEM_ID_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
                } else {

                    ItemDTO itemDTO = itemModel.searchItem(id);
                    
                    if(itemDTO!=null) {
                        nameField.setText(itemDTO.getName());
                        priceField.setText(String.valueOf(itemDTO.getPrice()));
                        qtyField.setText(String.valueOf(itemDTO.getQty()));
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Item not found!").show();
                    }

                }

            }
        
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    
    void cleanFields() {
    
        idField.setText("");
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
        
    }
    
    void handleLoadItemTable() {
    
        try {
            
            List<ItemDTO> itemList = itemModel.getAllItems();
            
            ObservableList<ItemDTO> obList = FXCollections.observableArrayList();
            
            for (ItemDTO itemDTO : itemList) {
                obList.add(itemDTO);
            }
            
            tblItem.setItems(obList);
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }
    
}
