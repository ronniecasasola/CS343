/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author artur
 */
public class OrderHistoryController implements Initializable {

    @FXML
    private Button menuButton;
    @FXML
    private Button checkout;
    @FXML
    private Button serverButton;
 
    
    @FXML
    private TableView<MenuObject> orderHistory;
    @FXML
    private TableColumn<MenuObject, String> nameCol;
    @FXML
    private TableColumn<MenuObject, String> priceCol;
    @FXML
    private TextArea subtotal;
    @FXML
    private TextArea tax;
    @FXML
    private TextArea total;
    
    private static Customer customer;
    
    private ObservableList<MenuObject> history = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        customer = WelcomeController.getCustomer();
        
        for (int i =0; i<customer.viewHistory().size();i++)
        {
            history.add(customer.viewHistory().get(i));
        }
        
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().getMenuObjectPricePropertyFormattedForOrderHistory());
        
        orderHistory.setItems(history);
        
        DecimalFormat df = new DecimalFormat("#.00");
        subtotal.setText(df.format(customer.getSubTotal()));
        tax.setText(df.format(customer.getSubTotal()*0.1025));
        total.setText(df.format(customer.getTotal()));
    }  
    
    
    @FXML
    private void handleMenuButton(ActionEvent event) throws IOException 
    {
        Parent Parent = FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Customer Menu");
        window.show();
    }

    @FXML
    private void handleCheckoutButton(ActionEvent event) throws IOException 
    {
        Parent Parent = FXMLLoader.load(getClass().getResource("CheckOut.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Check Out");
        window.show();
    }
    
    @FXML
    private void handleServerButton(ActionEvent event) 
    {
        
    }
    
}
