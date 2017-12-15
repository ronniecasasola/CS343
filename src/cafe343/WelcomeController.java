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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author artur
 */
public class WelcomeController implements Initializable {

    @FXML
    private Button customerButton;
    @FXML
    private Button EmployeeButton;
    private static Customer customer;
    private int n = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCustomerButtonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        n+=1;
        customer = new Customer(n);
        Parent customerMenuParent = FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
        Scene customerMenuScene = new Scene(customerMenuParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(customerMenuScene);
        window.setTitle("Customer Menu");
        window.getIcons().add(new Image("resources/application_icon.png"));
        window.show();
        
    }

    @FXML
    private void handleEmployeeButtonAction(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene LoginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(LoginScene);
        window.getIcons().add(new Image("resources/application_icon.png"));

        window.setResizable(false); 
        window.show();
    }
    
    // use to pass customer
    public static Customer getCustomer()
    {
        return customer;
    }
}