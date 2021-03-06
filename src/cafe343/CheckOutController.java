/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    @FXML
    private AnchorPane backgroundPane;
    
    private static Customer customer;
    
    private double pricePerCustomer;
    
    private int numberOfPeople;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CREATES NEW BACKGROUND IMAGE
        BackgroundImage myBG= new BackgroundImage(new Image("resources/bg.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        //SETS BACKGROUND
        backgroundPane.setBackground(new Background(myBG));
        
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
    private void handleServerButton(ActionEvent event) throws ClassNotFoundException 
    {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Calling Server");
            errorAlert.setHeaderText("Please wait a moment");
            errorAlert.setContentText("A server will arrive to assist you soon");
            //Adding error_icon to Error Window.
            Stage errorStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            errorStage.getIcons().add(new Image("resources/error_icon.png"));
            errorAlert.showAndWait();
            
            String status = "Alert";
            
            try {
                //Connecting with database.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection connection = DriverManager.getConnection( DatabaseConnection.DB_URL );
                Statement statement = connection.createStatement();

                statement.executeUpdate("UPDATE CustomerTable SET tablestatus = " + "'" + status + "'" + " where tableID = " + customer.getTableNumber());
            
            statement.close();
            connection.close();
             }catch (SQLException e) {
                e.printStackTrace();
            }
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
