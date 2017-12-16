/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ronnie
 */
public class CustomerOrder {
    
    //Simple Integer and String Properties for populating TableView.
    private final SimpleIntegerProperty orderIDProperty;
    private final SimpleIntegerProperty tableIDProperty;
    private final SimpleStringProperty objectNameProperty;
    
    CustomerOrder(int orderID, int tableID, String objectName)
    {
        orderIDProperty = new SimpleIntegerProperty(orderID);
        tableIDProperty = new SimpleIntegerProperty(tableID);
        objectNameProperty = new SimpleStringProperty(objectName);
    }
    
     public StringProperty getObjectNameProperty()
    {
        return objectNameProperty;
    }
    public IntegerProperty getOrderIDProperty()
    {
         return orderIDProperty;
    }
    
    public IntegerProperty getTableIDProperty()
    {
         return tableIDProperty;
    }
    
}