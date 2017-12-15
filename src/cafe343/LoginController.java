/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LimSt
 */
public class LoginController implements Initializable {

    @FXML
    private TextField IDText;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button returnButton;

    private HashMap credentials;
    private String IDnum;
    private String password;
    
    private static Employee employee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readFromFile();

    }

    @FXML
    private void loginButtonEvt(ActionEvent event) throws IOException, ClassNotFoundException{

        if (IDText.getText().equals("")) {

            //Showing Error Dialog.
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error: Field ID number is empty.");
            errorAlert.setContentText("Field ID number is empty."
                    + " Please provide a valid ID number and try again.");
            //Adding error_icon to Error Window.
            Stage errorStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            errorStage.getIcons().add(new Image("resources/error_icon.png"));

            errorAlert.showAndWait();

            //Checking TextField Meal Price is empty.
        } else if (passwordField.getText().equals("")) {

            //Showing Error Dialog.
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error: Field password is empty.");
            errorAlert.setContentText("Field password is empty."
                    + " Please provide a valid password and try again.");
            //Adding error_icon to Error Window.
            Stage errorStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            errorStage.getIcons().add(new Image("resources/error_icon.png"));

            errorAlert.showAndWait();

        } else {

            //Getting values from TextFields and creating Meal by them in database.
            IDnum = IDText.getText();
            password = passwordField.getText();

             try {
                //Connecting with database.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection connection = DriverManager.getConnection( DatabaseConnection.DB_URL );
                Statement statement = connection.createStatement();
                //Returning the Usernames from database that are same with entered text.
                ResultSet resultSet = statement.executeQuery("SELECT USERID FROM EMPLOYEE WHERE "
                                                                + "USERID = '" + IDnum+ "'" + " AND USERPIN = '" + password + "'");
                System.out.println(resultSet.toString());
                //Checking any Username is found from database that are same with entered text.
                if (resultSet.next()){
  
                        Parent Parent = FXMLLoader.load(getClass().getResource("EmployeeMenu.fxml"));
                    Scene nextScene = new Scene(Parent);
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setResizable(false);
                    window.setScene(nextScene);
                    window.setTitle("Entrées");
                    window.show();
                
            } else {
                System.out.println("Invalid ID Number. ");
            }
             }
      
                    
                    
                
                
                
                  catch (SQLException e) {
                e.printStackTrace();
            }
                
           

        }
    }

    @FXML
    private void clearButtonEvt(ActionEvent event) {
        IDText.setText("");
        passwordField.setText("");
    }

    @FXML
    private void returnButtonEvt(ActionEvent event) throws IOException {
        Parent customerMenuParent = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene customerMenuScene = new Scene(customerMenuParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setTitle("Welcome to Cafe 343");
        window.setScene(customerMenuScene);
        window.show();
    }

    private void readFromFile() {
        credentials = new HashMap();
        File file = new File("src/data/LoginCredentials.txt");
        try {
            Scanner in = new Scanner(file);
            String nextLine = "";
            //menu object atributes in text file should be separeted by commas
            //and values stored to make a new menu object and add to list of menu objects
            while (in.hasNextLine()) {
                nextLine = in.nextLine();
                //separate by semicolon because menu descriptions sometimes have commas
                String[] ar = nextLine.split(",");
                String readID = ar[0];
                String readPassword = ar[1];
                credentials.put(readID, readPassword);
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
