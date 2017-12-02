package cafe343;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

public class Meal {

    private String mealName;
    private int mealPrice;

    //Simple Integer and String Properties for populating TableView.
    private final SimpleIntegerProperty mealPriceProperty = new SimpleIntegerProperty(mealPrice);
    private final SimpleStringProperty mealNameProperty = new SimpleStringProperty(mealName);

    public int getMealPriceProperty() {
        return mealPriceProperty.get();
    }

    public String getMealNameProperty() {
        return mealNameProperty.get();
    }

    private void setMealPriceProperty(int mealPriceProperty) {
        this.mealPriceProperty.set(mealPriceProperty);
    }

    private void setMealNameProperty(String mealNameProperty) {
        this.mealNameProperty.set(mealNameProperty);
    }

    //Method used for creating meal.
    public static void createMeal(String mealName, int mealPrice){

        //add to database
    }

    //Method used for deleting meal.
    public static void deleteMeal(String mealName){

        //from database
    }

    //Method used for listing created meals.
    public static ObservableList<Meal> listMeal(){

        //Observable List for collecting created Meal Objects.
        ObservableList<Meal> mealList = FXCollections.observableArrayList();
        
            //create new menu item and add to meal list
                Meal createdMeal = new Meal();
               
                mealList.add(createdMeal);
            
                return mealList;
            
    }

    //Method used for listing meal names.
    public static ObservableList<String> fillComboBoxMeal(){

        //Observable List for collecting Meal Names.
        ObservableList<String> mealNameList = FXCollections.observableArrayList();
                //read from file and, while there are items, add its name to mealnamelist
                
                String mealName = "";
                //Adding the created String to ObservableList.
                mealNameList.add(mealName);
                


        return mealNameList;
    }
}
