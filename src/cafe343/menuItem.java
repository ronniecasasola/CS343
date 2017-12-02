/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

/**
 *
 * @author xinyi
 */
public class menuItem 
{
    private String name; //name of the item
    private double price; //price of the item
    private String type; //type of food
    
//    private String note; //note for chef on particular item
    menuItem(String name, double price, String type)
    {
        this.name=name;
        this.price=price;
    }
    
    menuItem()
    {
        name = "";
        price = 0;
        type ="";
    }
    
//    public void addNote(String note)
//    {
//        this.note = note;
//    }
    
    public String getName()
    {
        return name;
    }
    
    public double getPrice()
    {
        return price;
    }
    
//    public String toString()
//    {
//        return name+"     "+price;
//    }
}