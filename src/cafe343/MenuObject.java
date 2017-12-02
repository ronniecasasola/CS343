package cafe343;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MenuObject {

    //Simple Integer and String Properties for populating TableView.
    private final SimpleStringProperty menuObjectNameProperty;
    private final SimpleDoubleProperty menuObjectPriceProperty;
    private final SimpleStringProperty menuObjectTypeProperty;

    MenuObject(String menuObjectName, double menuObjectPrice, String menuObjectType)
    {
        menuObjectNameProperty = new SimpleStringProperty(menuObjectName);
        menuObjectPriceProperty = new SimpleDoubleProperty(menuObjectPrice);
        menuObjectTypeProperty = new SimpleStringProperty(menuObjectType);
    }
    
    public double getMenuObjectPriceProperty() {
        return menuObjectPriceProperty.get();
    }

    private void setMenuObjectPriceProperty(int menuObjectPriceProperty) {
        this.menuObjectPriceProperty.set(menuObjectPriceProperty);
    }
    
    public String getMenuObjectNameProperty() {
        return menuObjectNameProperty.get();
    }


    private void setMenuObjectNameProperty(String menuObjectNameProperty) {
        this.menuObjectNameProperty.set(menuObjectNameProperty);
    }


    //Method used for deleting menu item.
    public static void deleteMenuObject(String menuObjectName){

        //from database
    }

    //Method used for listing created menu items.
    public static ObservableList<MenuObject> listAppetizerObject(){

        //Observable List for collecting created MenuObject Objects.
        ObservableList<MenuObject> menuObjectList = FXCollections.observableArrayList();
        
        //this is where you read from text file of menu items and create a new menu item to add to list of menu items
        String readName;
        Double readPrice;
        String readType;
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/data/MenuObjects.txt"));
            String nextLine;
            //menu object atributes in text file should be separeted by commas
            //and values stored to make a new menu object and add to list of menu objects
            while((nextLine = in.readLine()) != null){
                String[] ar = nextLine.split(",");
                readName = ar[0];
                readPrice = Double.parseDouble(ar[1]);
                readType = ar[2];
                
                if(readType == "appetizer"){
                
                MenuObject createdMenuObject = new MenuObject(readName, readPrice, readType);
                menuObjectList.add(createdMenuObject);
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuObject.class.getName()).log(Level.SEVERE, null, ex);
        }
                return menuObjectList;
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
