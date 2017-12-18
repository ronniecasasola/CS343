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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    @FXML
    private AnchorPane backgroundPane;
    
    private static Customer customer;
    
    private int tableNumber = 0;
    @FXML
    private Label titleLabel;
    @FXML
    private Label titleLabel1;
    @FXML
    private TextField tableNumberTextField;
    @FXML
    private Button addTableNumber;
    @FXML
    private Button subtractTableNumber;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CREATES NEW BACKGROUND IMAGE
        BackgroundImage myBG= new BackgroundImage(new Image("resources/bg.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        //SETS BACKGROUND
        backgroundPane.setBackground(new Background(myBG));
        //SET ICON
        EmployeeButton.setGraphic(new ImageView(new Image("resources/employee_icon.png")));
        
        tableNumber = 1;
        tableNumberTextField.setText(Integer.toString(tableNumber));
    }    

    @FXML
    private void handleCustomerButtonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        //tableNumber+=1;
        customer = new Customer(tableNumber);
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

    @FXML
    private void handleAdd(ActionEvent event) {
        EmployeeMenuController e = new EmployeeMenuController();       
        if (tableNumber < 10){
            tableNumber+=1;
            tableNumberTextField.setText(Integer.toString(tableNumber));            
        }
    }

    @FXML
    private void handleSubtract(ActionEvent event) {
        if (tableNumber > 1){
            tableNumber-=1;
            tableNumberTextField.setText(Integer.toString(tableNumber));
        }
    }    
}