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
//    private int numberOfPeople; //number of people in this table
//    private String seatedTime; //seated time for this customer
    private ArrayList<menuItem> orders;//arraylist of orders
    private int total; //total amount this table has spent
    
    public Customer(int tableNumber)
    {
        this.tableNumber = tableNumber;
        orders = new ArrayList<menuItem>(); 
        total = 0;
    }
    
    public Customer()
    {
        this.tableNumber = 0;
        orders = null;
        total = -1;
    }
    
    public void makeOrder(menuItem item)
    {
        orders.add(item);
        total += item.getPrice();
    }
    
    public ArrayList<menuItem> viewHistory()
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