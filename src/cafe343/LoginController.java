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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LimSt
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userText;
    @FXML
    private TextField passText;
    @FXML
    private Button loginButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button returnButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void loginButtonEvt(ActionEvent event){
        System.out.println("Login Button was clicked");
    }
    @FXML
    private void clearButtonEvt(ActionEvent event){
        userText.setText("");
        passText.setText("");
    }
    
    @FXML
    private void returnButtonEvt(ActionEvent event) throws IOException {
        Parent customerMenuParent = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene customerMenuScene = new Scene(customerMenuParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(customerMenuScene);
        window.show();
    }
    
}
