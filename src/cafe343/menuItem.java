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
    private String note; //note for chef on particular item
    menuItem(String name, double price)
    {
        this.name=name;
        this.price=price;
    }
    
    menuItem()
    {
        name = "";
        price = 0;
    }
    
    public void addNote(String note)
    {
        this.note = note;
    }
}
