/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author xinyi
 */

public class Customer 
{
    private int tableNumber; //table number of the customer
    private int numberOfPeople; //number of people in this table
    private String seatedTime; //seated time for this customer
    private ArrayList<MenuObject> orders; //arraylist of menu item orders
    
    private  double totalCost;
    private boolean hasPaid;

//    private int numberOfPeople; //number of people in this table
//    private String seatedTime; //seated time for this customer
 //total amount this table has spent
    
    public Customer(int tableNumber) throws ClassNotFoundException
    {
        this.tableNumber = tableNumber;
        totalCost = 0;
        hasPaid = false;
        orders = new ArrayList<MenuObject>(); 
        totalCost = 0;
        
         try {
                //Connecting with database.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection connection = DriverManager.getConnection( DatabaseConnection.DB_URL );
                Statement statement = connection.createStatement();

                statement.executeUpdate("UPDATE CustomerTable SET tableID = " + tableNumber + "," + "totalCost = " + totalCost + ", hasPaid = " + hasPaid + " where tableID = " + tableNumber);
            
            statement.close();
            connection.close();
             }catch (SQLException e) {
                e.printStackTrace();
            }
    }
      
    public int getTableNumber()
    {
        return tableNumber;
    }
    
    public void makeOrder(MenuObject item) throws ClassNotFoundException
    {
        totalCost += item.getMenuObjectPriceProperty();
        
        try {
                //Connecting with database.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection connection = DriverManager.getConnection( DatabaseConnection.DB_URL );
                Statement statement = connection.createStatement();

                statement.executeUpdate("INSERT INTO CustomerOrder(tableID, objectName) values (" + tableNumber + ", '" + item.getMenuObjectNameProperty() + "')" );
                statement.executeUpdate("UPDATE CustomerTable SET totalCost = " + totalCost + " where tableID = " + tableNumber);
                statement.close();
                connection.close();
             }catch (SQLException e) {
                e.printStackTrace();
            }
        
        orders.add(item); 
    }
    
   
    
    public double getTotal(){
        return totalCost;
    }
    
    public ArrayList<MenuObject> viewHistory()
    {
        return orders;
    }
    
    public double splitByPeople(int numberOfPeople)
    {
        return totalCost*1.1025/numberOfPeople;
    }
    
    public int callServer()
    {
        return tableNumber;
    }
    
}