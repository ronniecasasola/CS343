/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

    private static Customer customer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        customer = WelcomeController.getCustomer();
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
