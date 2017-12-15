package cafe343;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MenuObject {

    //Simple Integer and String Properties for populating TableView.
    private final SimpleStringProperty menuObjectNameProperty;
    private final SimpleDoubleProperty menuObjectPriceProperty;
    private final SimpleStringProperty menuObjectDescriptionProperty;
    private SimpleStringProperty menuObjectPrice;
    
    MenuObject(String menuObjectName, double menuObjectPrice, String menuObjectDescription)
    {
        menuObjectNameProperty = new SimpleStringProperty(menuObjectName);
        menuObjectPriceProperty = new SimpleDoubleProperty(menuObjectPrice);
        menuObjectDescriptionProperty = new SimpleStringProperty(menuObjectDescription);
    }
    
    public double getMenuObjectPriceProperty() {
        return menuObjectPriceProperty.get();
    }
    
    public StringProperty nameProperty()
    {
        return menuObjectNameProperty;
    }
    public StringProperty priceProperty()
    {
         menuObjectPrice = new SimpleStringProperty(menuObjectPriceProperty.toString());
         return menuObjectPrice;
    }
    
    //created to resolve errors with displaying menu object price within the menu's grid of buttons with 
    //correct format ($ sign with .00 two decimal places)
    public String getMenuObjectPricePropertyFormatted() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(menuObjectPriceProperty.get());
    }

    public String getMenuObjectNameProperty() {
        return menuObjectNameProperty.get();
    }

    //Method used for deleting menu item. maybe not needed
    public static void deleteMenuObject(String menuObjectName){

        //from database
    }
    
    public String getMenuObjectDescriptionProperty(){
        return menuObjectDescriptionProperty.get();
    }

    //Method used for listing menu item names.
    public static ObservableList<String> fillComboBoxMeal(){

        //Observable List for collecting MenuObject Names.
        ObservableList<String> menuObjectNameList = FXCollections.observableArrayList();
                //read from file and, while there are items, add its name to menuObjectnamelist
                
                String menuObjectName = "";
                //Adding the created String to ObservableList.
                menuObjectNameList.add(menuObjectName);
                
        return menuObjectNameList;
    } 
}
