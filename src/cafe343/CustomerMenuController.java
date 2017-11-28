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
public class CustomerMenuController implements Initializable {

    @FXML
    private Button EntreesButton;
    @FXML
    private Button DrinksButton;
    @FXML
    private Button ViewOrderButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEntreesButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleDrinksButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleViewOrderButton(ActionEvent event) throws IOException {
        // System.out.println("You clicked Customer!");
        Parent orderCartParent = FXMLLoader.load(getClass().getResource("OrderCart.fxml"));
        Scene orderCartScene = new Scene(orderCartParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(orderCartScene);
        window.show();
    }
    
}
