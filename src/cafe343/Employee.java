/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafe343;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.logging.Logger;
/**
 *
 * @author xinyi
 */
public class Employee 
{
    private int employeeID;
    private ArrayList<Integer> tableHelp;
    public ArrayList<Customer> customers;

    public static final int port = 4444;
    private static ServerSocket server;

    public ArrayList<Customer> getExistingCustomers(){
        return customers;
    }
    
    public Employee() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.handleConnection();
        

    }
    
    
    public void handleConnection() {
        System.out.println("Waiting for client message...");

        //
        // The server do a loop here to accept all connection initiated by the
        // client application.
        //
        while (true) {
            try {
                Socket socket = server.accept();
                ConnectionHandler c = new ConnectionHandler(socket);
                customers = c.getCustomers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConnectionHandler implements Runnable {
    private Socket socket;
    private ArrayList<Customer> customers;
    
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public ConnectionHandler(Socket socket) {
        customers = new ArrayList<Customer>();
        this.socket = socket;

        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try
        {
            //
            // Read a message sent by client application
            //
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Customer customer = (Customer) ois.readObject();
            System.out.println("Customer Received: " + customer.toString());

            //
            // Send a response information to the client application
            //
            customers.add(customer);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(customers);

            ois.close();
            oos.close();
            socket.close();

            System.out.println("Waiting for client message...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

   
    

