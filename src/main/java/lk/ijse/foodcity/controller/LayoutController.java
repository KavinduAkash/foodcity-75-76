package lk.ijse.foodcity.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.foodcity.App;

public class LayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Layout is loaded!");
    }    
    
    @FXML
    private void clickCustomerNav() throws IOException {
        Parent customerFXML = App.loadFXML("Customer");
        mainContent.getChildren().setAll(customerFXML);
    }
    
    @FXML
    private void clickItemNav() throws IOException {
        Parent customerFXML = App.loadFXML("Item");
        mainContent.getChildren().setAll(customerFXML);
    }
    
    @FXML
    private void clickOrderNav() throws IOException {
        Parent customerFXML = App.loadFXML("Order");
        mainContent.getChildren().setAll(customerFXML);
    }
     
}
