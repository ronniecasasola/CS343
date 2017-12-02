/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;
import java.util.*;
/**
 *
 * @author xinyi
 */
public class Customer 
{
    private int tableNumber; //table number of the customer
    private int numberOfPeople; //number of people in this table
    private String seatedTime; //seated time for this customer
    private ArrayList<Meal> orders; //arraylist of meal orders

//    private int numberOfPeople; //number of people in this table
//    private String seatedTime; //seated time for this customer
  
    private int total; //total amount this table has spent
    
    public Customer(int tableNumber)
    {
        this.tableNumber = tableNumber;
        orders = new ArrayList<Meal>(); 
        total = 0;
    }
    
    public Customer()
    {
        this.tableNumber = 0;
        orders = null;
        total = -1;
    }
    
    public void makeOrder(Meal item)
    {
        orders.add(item);
        total += item.getMealPriceProperty();
    }
    
    public ArrayList<Meal> viewHistory()
    {
        return orders;
    }
    
    public double splitByPeople(int numberOfPeople)
    {
        return total/numberOfPeople;
    }
    
    public int callServer()
    {
        return tableNumber;
    }
    
}