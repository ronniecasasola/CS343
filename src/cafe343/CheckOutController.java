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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author artur
 */
public class CheckOutController implements Initializable {

    @FXML
    private Button menuButton;
    @FXML
    private Button serverButton;
    @FXML
    private Button splitPeople;
    @FXML
    private Button splitItemButton;
    @FXML
    private Button feedbackButton;
    
    @FXML
    private TextField numberOfPeopleTextField;
    @FXML 
    private TextArea output;
    @FXML
    private TextArea total;
    
    private static Customer customer;
    
    private double pricePerCustomer;
    
    private int numberOfPeople;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customer = WelcomeController.getCustomer();
        numberOfPeople = 1;
        numberOfPeopleTextField.setText(Integer.toString(numberOfPeople));
        DecimalFormat df = new DecimalFormat("#.00");
        total.setText( df.format(customer.getTotal()));

    }    

    /**
     * Returns to main menu
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleMenuButton(ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Customer Menu");
        window.show();
    }

    @FXML
    private void handleServerButton(ActionEvent event) 
    {
        
    }

    /**
     * Divides the check by the number of paying customers
     * @param event 
     */
    @FXML
    private void handleSplitPeople(ActionEvent event) 
    {
        pricePerCustomer = customer.splitByPeople(Integer.parseInt(numberOfPeopleTextField.getText()));
        DecimalFormat df = new DecimalFormat("#.00");
        output.setText("Each person pays $"+df.format(pricePerCustomer));

    }

    //when subtract button is click, the text area decrease the number of people
    @FXML
    private void handleSubtract(ActionEvent event) 
    {
        if (numberOfPeople > 1){
             numberOfPeople-=1;
             numberOfPeopleTextField.setText(Integer.toString(numberOfPeople));
        }
   
    }
    
    //when add button is clicked, the text area update the text.
    @FXML
    private void handleAddPeople(ActionEvent event) 
    {
        numberOfPeople+=1;
        numberOfPeopleTextField.setText(Integer.toString(numberOfPeople));
    }
    
    /**
     * Accesses feedback screen
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleFeedbackButton(ActionEvent event) throws IOException{  
        
           Stage currentStage = (Stage)menuButton.getScene().getWindow();
           Parent root = FXMLLoader.load(getClass().getResource("Feedback.fxml"));
           currentStage.setScene(new Scene(root));

    }
}
