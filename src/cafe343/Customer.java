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
    private ArrayList<Order> orders = new ArrayList<Order>(); //arraylist of orders
    private int total; //total amount this table has spent
    
    public Customer(int tableNumber, int numberOfPeople, String seatedTime)
    {
        this.tableNumber = tableNumber;
        this.numberOfPeople = numberOfPeople;
        this.seatedTime = seatedTime;
    }
    
    public void makeOrder(menuItem item, String note)
    {
        
    }
    
}
