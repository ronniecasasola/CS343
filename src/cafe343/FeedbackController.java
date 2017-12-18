/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 *
 * @author Steven Lim
 */
public class FeedbackController implements Initializable {
    
    private Customer customer;
    private Date date;
    private DateFormat dateFormat;
        
    protected File file;
    
    @FXML
    private TextArea feedbackText;
    
    @FXML
    private Button submitButton;
    
    @FXML
    private AnchorPane backgroundPane;
    
    @FXML
    private Button returnButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customer = WelcomeController.getCustomer();
        
        //CREATES NEW BACKGROUND IMAGE
        BackgroundImage myBG= new BackgroundImage(new Image("resources/bg.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        
        //SETS BACKGROUND
        backgroundPane.setBackground(new Background(myBG));
    }    
    
    /**
     * Reads the textArea in the window and prints formatted text into a text file located in the data folder
     * @param event
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @FXML
    private void handleSubmitButton(ActionEvent event) throws FileNotFoundException, IOException {
       try{
           if(feedbackText.getText() != ""){ //If the text is not empty
                date = new Date(); //Get the date and time
                dateFormat= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                file = new File("src/data/Feedback.txt"); //Find the file
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.println("Table ID: " + customer.getTableNumber()); //Write the file according to the following format
                printWriter.println(dateFormat.format(date));
                printWriter.println(feedbackText.getText());

                printWriter.close();

               Stage currentStage = (Stage)submitButton.getScene().getWindow(); //And return to the main screen
               Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
               currentStage.setScene(new Scene(root));
           }
       
       }
       catch(FileNotFoundException e){
           System.out.println("File was not found!");
       }
       catch(IOException f){
           System.out.println("Input Output Exception Error");
       }
       

    }

    /**
     * Returns to the main screen and ends the session
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleReturnButton(ActionEvent event) throws IOException{
           Stage currentStage = (Stage)submitButton.getScene().getWindow();
           Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
           currentStage.setScene(new Scene(root));
    }
    

    
}
