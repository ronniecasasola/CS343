package cafe343;

import com.sun.prism.impl.Disposer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ronnie
 */
public class EmployeeMenuController implements Initializable {

    //Images and ImageViews for Buttons.
    private Image imageRefreshIcon = new Image("resources/refresh_icon.png");
    private ImageView imageViewRefreshIcon = new ImageView(imageRefreshIcon);
    private ImageView imageViewMealRefreshIcon = new ImageView(imageRefreshIcon);
    private ImageView imageViewDepartmentRefreshIcon = new ImageView(imageRefreshIcon);
    private ImageView imageViewJobRefreshIcon = new ImageView(imageRefreshIcon);
    private ImageView imageViewEmployeeRefreshIcon = new ImageView(imageRefreshIcon);

    private Image imageTableIcon = new Image("resources/table_icon.png");

     //**********ROOT TAB PANE**********
    public TabPane tabPaneMainWindow;
    
    //**********MAIN PANEL TAB**********
    @FXML
    private GridPane gridPaneTables;
    private Button buttonAddTable;
    @FXML
    private Button tabPaneSignOutButton;
    @FXML
    private TableView tableViewOrders;
    
    @FXML
    private TableColumn<CustomerOrder, Integer> tableNumCol;
    @FXML
    private TableColumn<CustomerOrder, Integer> orderNumCol;
    @FXML
    private TableColumn<CustomerOrder, String> nameCol;
    
    private ObservableList<CustomerOrder> history = FXCollections.observableArrayList();
    //Table ID for Buttons.
    private int tableID = 1;
    private int tableCount;
    //Column Index and Row Index for adding Buttons to GridPane.
    private int columnIndex = 0;
    private int rowIndex = 0;
    @FXML
    private TabPane tabPaneMainPanel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        //Calling refresh to all tables at start.
        
        try {
            orderRefresh();
            listTable();
            tableRefresh();
            //Initializing Root TabPane.
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Initializing Main Panel Tab.
        
        //setButtonAddTable(buttonAddTable);
        
        //Initializing Restaurant Menu Tab.
  
    } 
    
    @FXML
    private void handleSignOutButtonAction(ActionEvent event) throws IOException {
        Parent Parent = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene nextScene = new Scene(Parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(nextScene);
        window.setTitle("Welcome Screen");
        window.show();
    }
    

    //Method used for adding Buttons(Tables) to GridPane.
    private void tableCreate(){

        //Checking the columnIndex initially to see if it equals to the limit.
        if (columnIndex == gridPaneTables.getColumnConstraints().size()){
            if (rowIndex < gridPaneTables.getRowConstraints().size() - 1){
                //Resetting the columnIndex and incrementing rowIndex.
                columnIndex = 0;
                rowIndex++;
            }
        }
        //Checking ColumnConstraints and RowConstraints before adding any Tables.
        if (columnIndex <= gridPaneTables.getColumnConstraints().size() - 1
                && rowIndex <= gridPaneTables.getRowConstraints().size() - 1){

            //Menu Items for Split Menu Button
            MenuItem menuObjectSetAvailable = new MenuItem("Set Available");
            MenuItem menuObjectSetOccupied = new MenuItem("Set Occupied");
            MenuItem menuObjectSetBooked = new MenuItem("Server Call Received");

            //Initializing Button for GridPane.
            final SplitMenuButton splitMenuButtonTable = new SplitMenuButton(menuObjectSetAvailable,
                                                                    menuObjectSetOccupied, menuObjectSetBooked);
            //Setting appearance of the table buttons.
            splitMenuButtonTable.setText("Table " + tableID);
            splitMenuButtonTable.setGraphic(new ImageView(imageTableIcon));
            splitMenuButtonTable.setStyle("-fx-background-color:#7CFC00;");
            //Setting Button's Text as TABLE_ID from database.
            splitMenuButtonTable.setId(String.valueOf(tableID));
            //Setting Button's size to MAX for forcing it to fit GridPane cells.
            splitMenuButtonTable.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            //Sets the Icon on top of the text.
            splitMenuButtonTable.setContentDisplay(ContentDisplay.TOP);


            splitMenuButtonTable.setOnAction(event -> {
                //Order TableID
                String tableID = splitMenuButtonTable.getId();
                
            });
            
            //Addorder was redacted here

            menuObjectSetAvailable.setOnAction(event -> splitMenuButtonTable.setStyle("-fx-background-color:#7CFC00;"));
            menuObjectSetOccupied.setOnAction(event -> splitMenuButtonTable.setStyle("-fx-background-color:#FF0000;"));
            menuObjectSetBooked.setOnAction(event -> splitMenuButtonTable.setStyle("-fx-background-color:#FFFF00;"));

            //Adding the created Button to GridPane and incrementing columnIndex.
            gridPaneTables.add(splitMenuButtonTable,columnIndex,rowIndex);
            columnIndex++;

            //Calling createTable Method to add created table to database by using tableID.
            Table.createTable(tableID);
            //Incrementing tableID after the add process in order to synchronize with TABLE_ID column from database.
            tableID++;

        } else {

            //Showing Error Dialog.
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error: You can't add more tables.");
            errorAlert.setContentText("You have reached the maximum number of tables." +
                    " You can't add more tables to the panel.");
            //Adding error_icon to Error Window.
            Stage errorStage = (Stage) errorAlert.getDialogPane().getScene().getWindow();
            errorStage.getIcons().add(new Image("resources/error_icon.png"));
            errorAlert.showAndWait();
        }
    }

    //Setting buttonAddTable with tableCreate Method.
    private void setButtonAddTable(Button buttonAddTable) {
        buttonAddTable.setOnAction(event -> tableCreate());
        this.buttonAddTable = buttonAddTable;
    }
    
    /**
     * Updates the data of orders input from the customer side program
     * @throws ClassNotFoundException 
     */
    public void orderRefresh() throws ClassNotFoundException{
        
         try {
                //Connecting with database.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection connection = DriverManager.getConnection( DatabaseConnection.DB_URL );
                Statement statement = connection.createStatement();
                //Returning the Usernames from database that are same with entered text.
                ResultSet resultSet = statement.executeQuery("SELECT ORDERID, TABLEID, OBJECTNAME FROM CUSTOMERORDER ");
                System.out.println(resultSet.toString());
                
                //Checking any Username is found from database that are same with entered text.
                while (resultSet.next()){
                    CustomerOrder createdOrder = new CustomerOrder(resultSet.getInt("ORDERID"), resultSet.getInt("TABLEID"), resultSet.getString("OBJECTNAME"));
                        history.add(createdOrder);
                
            } 
             }
      
                
                  catch (SQLException e) {
                e.printStackTrace();
            }
        tableNumCol.setCellValueFactory(cellData -> cellData.getValue().getTableIDProperty().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().getObjectNameProperty());
        orderNumCol.setCellValueFactory(cellData -> cellData.getValue().getOrderIDProperty().asObject());
        
         //Insert Button
        TableColumn col_action = new TableColumn<>("Action");
        tableViewOrders.getColumns().add(col_action);
        
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        
        tableViewOrders.setItems(history);
        
    }
    
     //Define the button cell
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Delete");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	CustomerOrder currentCustomer = (CustomerOrder) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        int o = currentCustomer.getOrderIDProperty().intValue();
                        deleteRowTable(o);
                	//remove selected item from the table list
                	history.remove(currentCustomer);
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }

    
       //Method used for listing table.
    //Reads the amount of tables from the database

    private void listTable() throws ClassNotFoundException{

        try {
                //Connecting with database.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection connection = DriverManager.getConnection( DatabaseConnection.DB_URL );
                Statement statement = connection.createStatement();
                //Creates a query
                ResultSet resultSet = statement.executeQuery("Select COUNT(tableID) AS total from CustomerTable");
                if(resultSet.next()){
                    tableCount = resultSet.getInt("total");
                    System.out.println(tableCount);
                }
                
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException f){
            f.printStackTrace();
        }

    }

    
    
    /**
     * Updates the amount of tables based on the value from the Tables class
     */
    private void tableRefresh(){
        //Add Tables until tableID is equal to the number of available tables
        while (tableID <= tableCount){

            //Checks the columns initially to see if it is equal to the max
            if (columnIndex == gridPaneTables.getColumnConstraints().size()){
                if (rowIndex < gridPaneTables.getRowConstraints().size() - 1){
                    //Resetting the columnIndex and incrementing rowIndex.
                    columnIndex = 0;
                    rowIndex++;
                }
            }
            //Checking ColumnConstraints and RowConstraints before adding any Tables.
            if (columnIndex <= gridPaneTables.getColumnConstraints().size() - 1
                    && rowIndex <= gridPaneTables.getRowConstraints().size() - 1) {

                //Menu Items for Split Menu Button
                MenuItem menuObjectSetAvailable = new MenuItem("Set Available");
                MenuItem menuObjectSetOccupied = new MenuItem("Occupied");
                MenuItem menuObjectSetBooked = new MenuItem("Server Call Received");

                //Initializing Button for GridPane.
                final SplitMenuButton splitMenuButtonTable = new SplitMenuButton(menuObjectSetAvailable,
                                                                                menuObjectSetOccupied, menuObjectSetBooked);
                //Setting appearance of the table buttons.
                splitMenuButtonTable.setText("Table " + tableID);
                splitMenuButtonTable.setGraphic(new ImageView(imageTableIcon));
                splitMenuButtonTable.setStyle("-fx-background-color:#7CFC00;");
                //Setting Button's Text as TABLE_ID from database.
                splitMenuButtonTable.setId(String.valueOf(tableID));
                
                //Setting Button's size to MAX for forcing it to fit GridPane cells.
                splitMenuButtonTable.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
                //Sets the Icon on top of the text.
                splitMenuButtonTable.setContentDisplay(ContentDisplay.TOP);

                splitMenuButtonTable.setOnAction(event -> {
                    //Order TableID
                    String tableID = splitMenuButtonTable.getId();
                   
                });
                
                menuObjectSetAvailable.setOnAction(event -> splitMenuButtonTable.setStyle("-fx-background-color:#7CFC00;"));
                menuObjectSetOccupied.setOnAction(event -> splitMenuButtonTable.setStyle("-fx-background-color:#FF0000;"));
                menuObjectSetBooked.setOnAction(event -> splitMenuButtonTable.setStyle("-fx-background-color:#FFFF00;"));

                //Adding the created Button to GridPane and incrementing columnIndex.
                gridPaneTables.add(splitMenuButtonTable, columnIndex, rowIndex);
                columnIndex++;

                //Incrementing tableID after the add process in order to synchronize with TABLE_ID column from database.
                tableID++;
            }
        }
    }
    
        private void deleteRowTable(int o) { //throws ClassNotFoundException{
        int o1 = o;
        try {
                //Connecting with database.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection connection = DriverManager.getConnection( DatabaseConnection.DB_URL );
                Statement statement = connection.createStatement();
                //Creates a query
                statement.executeUpdate("Delete From CUSTOMERORDER Where ORDERID =" + o1);
               
// if(resultSet.next()){
                 //   tableCount = resultSet.getInt("total");
                   // System.out.println(tableCount);
                //}
                
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException f){
            f.printStackTrace();
        }

    }
}
