/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author artur
 */
public class WelcomeScreen extends Application {
    
    
    @Override
    public void start(Stage primarystage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        
        Scene scene = new Scene(root);
        primarystage.setTitle("Welcome to Cafe 343");
        primarystage.setResizable(false); //Prevents user from resizing the window
        primarystage.getIcons().add(new Image("resources/application_icon.png"));
        primarystage.setScene(scene);
        primarystage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
