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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author artur
 */
public class EntreeController implements Initializable {

    @FXML
    private Button returnButton;
    @FXML
    private Button orderHistoryButton;
    @FXML
    private Button serverButton;
    @FXML
    private TextArea CustomerMenuEntreeDescriptionProperty;
    @FXML
    private GridPane gridPaneTables;

    private static Customer customer;
    
    private static ArrayList<MenuObject> menuObjectList;
    private ArrayList<Image> listOfImages;
    private int columnIndex = 0;
    private int rowIndex = 0;
    private int index; //index of the items in the array
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entreesRefresh();
        customer = WelcomeController.getCustomer();
    }    
    
    /*
    * uses the the size of the list of menu objects to store an image
    * of that menu object into a list of images
    */
    private void storeMenuImages(){
        listOfImages = new ArrayList<Image>();
        for(int i = 0; i < menuObjectList.size(); i++){
            listOfImages.add(new Image("entreeImages/"+Integer.toString(i+1)+".png"));
        }
    }

    /*
    * Adds the menu items onto the gridPaneTables
    */
     private void entreesRefresh(){
        //read and store appetizer menu items into arraylist
        listEntreeObject();
        //read appetizer images and store to listofImages
        storeMenuImages();
        
        //for loop so that every menuObject that is read, create a new button of that menuObject
        for (int i = 0; i < menuObjectList.size(); i ++){
            
            //get and store appetizer attributes
            String objectName = menuObjectList.get(i).getMenuObjectNameProperty();
            String objectPrice = menuObjectList.get(i).getMenuObjectPricePropertyFormatted();
            double  price = menuObjectList.get(i).getMenuObjectPriceProperty();
            String objectDescription = menuObjectList.get(i).getMenuObjectDescriptionProperty();
            
        //Checking the columnIndex initially to see if it equals to the limit.
        if (columnIndex == gridPaneTables.getColumnConstraints().size()){
            if (rowIndex < gridPaneTables.getRowConstraints().size() - 1){
                //Resetting the columnIndex and incrementing rowIndex.
                columnIndex = 0;
                rowIndex++;
            }
        }

            //Menu Items for Split Menu Button
            MenuItem menuItemAddOrder = new MenuItem("Add To Order");

            //Initializing Button for GridPane.
            final SplitMenuButton splitMenuButtonTable = new SplitMenuButton(menuItemAddOrder);

            //Setting appearance of the table buttons.
            
            splitMenuButtonTable.setText(objectName + "  " + objectPrice);
            splitMenuButtonTable.setGraphic(new ImageView(listOfImages.get(i)));
            splitMenuButtonTable.setStyle("-fx-background-color:#7CFC00;");

            //Setting Button's size to MAX for forcing it to fit GridPane cells.
            splitMenuButtonTable.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            //Sets the Icon on top of the text.
            splitMenuButtonTable.setContentDisplay(ContentDisplay.TOP);
            //sets alingment of itemName, price, and picture
            splitMenuButtonTable.setAlignment(Pos.CENTER);
            
            splitMenuButtonTable.setOnAction(event -> {
               CustomerMenuEntreeDescriptionProperty.setText(objectDescription);
               
            });

            menuItemAddOrder.setOnAction(event -> {
                //Creating the Add Order Dialog.
                Dialog addOrderDialog = new Dialog();
                addOrderDialog.setTitle("Add To Order");

                //Creating the GridPane.
                GridPane gridPane = new GridPane();
                gridPane.setHgap(25);
                gridPane.setVgap(15);
                gridPane.setPadding(new Insets(20, 150, 10, 10));
                
                addOrderDialog.getDialogPane().setContent(gridPane);

                //Adding application_icon to Stage.
                Stage addOrderStage = (Stage) addOrderDialog.getDialogPane().getScene().getWindow();
                addOrderStage.getIcons().add(new Image("resources/application_icon.png"));
                
                //making order
               index = 0;
               for(int j = 0; j<menuObjectList.size();j++)
               {
                   if(menuObjectList.get(j).getMenuObjectNameProperty().equals(objectName))
                   {
                       index = j;
                   }
               }
                try {
                    customer.makeOrder(menuObjectList.get(index));
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EntreeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //Adding the created Button to GridPane and incrementing columnIndex.
            gridPaneTables.add(splitMenuButtonTable,columnIndex,rowIndex);
            columnIndex++;
        }
     }
    
    
    @FXML
    private void handleReturnButton(ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Customer Menu");
        window.show();
    }
    
    @FXML
    private void handleOrderHistoryButton(ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Order History");
        window.show();
    }
    
//    @FXML
//    private void handleCheckoutButton(ActionEvent event) throws IOException {
//        Parent Parent = FXMLLoader.load(getClass().getResource("CheckOut.fxml"));
//        Scene nextScene = new Scene(Parent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setResizable(false);
//        window.setScene(nextScene);
//        window.setTitle("Check Out");
//        window.show();
//    }
    

    
    @FXML
    private void handleServerButton(ActionEvent event) throws IOException 
    {
        
    }
    
     /*
    * Reads from the text file and and stores
    */
    public static void listEntreeObject(){

        //Array List for collecting created MenuObject Objects.
        menuObjectList = new ArrayList<MenuObject>();
        
        //this is where you read from text file of menu items and create a new menu item to add to list of menu items
        File file = new File("src/data/EntreesList.txt");
        try {
            Scanner in = new Scanner(file); 
            String nextLine = "";
            //menu object atributes in text file should be separeted by commas
            //and values stored to make a new menu object and add to list of menu objects
            while(in.hasNextLine()){
                nextLine = in.nextLine();
                //separate by semicolon because menu descriptions sometimes have commas
                String[] ar = nextLine.split(";");
                String readName = ar[0];
                Double readPrice = Double.parseDouble(ar[1]);
                String readDescription = ar[2];

                MenuObject createdMenuObject = new MenuObject(readName, readPrice, readDescription);
                menuObjectList.add(createdMenuObject);
            }
             in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
