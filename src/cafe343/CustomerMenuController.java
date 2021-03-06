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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
public class CustomerMenuController implements Initializable {

    @FXML
    private Button entreesButton;
    @FXML
    private Button appetizersButton;
    @FXML
    private Button drinksButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button orderHistoryButton;
    @FXML
    private Button serverButton;
    @FXML
    private AnchorPane backgroundPane;
    
    private static Customer customer;
    @FXML
    private Label tableNumberLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //CREATES NEW BACKGROUND IMAGE
        BackgroundImage myBG= new BackgroundImage(new Image("resources/bg.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        //SETS BACKGROUND
        backgroundPane.setBackground(new Background(myBG));
        
        customer = WelcomeController.getCustomer(); //pass customer 
        
        tableNumberLabel.setText("Table: " + customer.getTableNumber());

    }

    
    
    @FXML
    private void handleEntreesButtonAction(ActionEvent event) throws IOException{
        Parent Parent = FXMLLoader.load(getClass().getResource("Entree.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Entrées");
        window.show();
    }

    @FXML
    private void handleAppetizersButtonAction(ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource("Appetizer.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Appetizers");
        window.show();
    }
    
    @FXML
    private void handleDrinksButtonAction(ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource("Drink.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Drinks");
        window.show();
    }

    @FXML
    private void handleReturnButton(ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Welcome to Cafe 343");
        window.show();
    }
    
    @FXML
    private void handleOrderHistoryButton(ActionEvent event) throws IOException {
        /* Previous Method
        Parent Parent = FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Order History");
        window.show();
        */
 
                   
                   
           Stage currentStage = (Stage)orderHistoryButton.getScene().getWindow();
           Parent root = FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));
           currentStage.setScene(new Scene(root));

           
           
           
            //Close Previous Stage
      //     Stage primaryStage = (Stage)orderHistoryButton.getScene().getWindow();
        //   primaryStage.close();
    }

//    @FXML
//    private void handleCheckoutButton(ActionEvent event) throws IOException{
//        Parent Parent = FXMLLoader.load(getClass().getResource("CheckOut.fxml"));
//        Scene nextScene = new Scene(Parent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setResizable(false);
//        window.setScene(nextScene);
//        window.setTitle("Check Out");
//        window.show();
//    }

    
    /**
     * Delivers a popup alert and changes the status of the customer's table
     * @param event 
     */
    @FXML
    private void handleServerButton(ActionEvent event) throws ClassNotFoundException {
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
}
