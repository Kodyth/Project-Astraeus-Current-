import java.util.ArrayList;

import javafx.application.*;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
/**
 * RDataGUI.java
 * 
 * @Author Author: Geoffrey Mount
 * Collaborations: None
 * Date: 12/3/17
 * Description: This class builds the tables and the recorded data tabs.
 */
 


 
        
public class RDataGUI extends Application{


	private final ArrayList<TableView<Data>> tables= new ArrayList<TableView<Data>>();
			public static void main (String [] args) {	
		launch(args);
	}
	
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {//this method shows all the tables possible
		
		primaryStage.setTitle("Astraeus");
        primaryStage.setWidth(850);
        primaryStage.setHeight(700);

        HBox vCenter = new HBox (40);
        vCenter.setMinWidth(282);
        vCenter.setStyle("-fx-background-color: GRAY");//background setup
        if(DataLog.allData.size()<=0){}else{
        for(int i=0;i<DataLog.allData.get(0).size();i++){
        	tables.add(new TableView<Data>());//builds enough tables for all the datatypes represented in the master arraylist
        }
        
        for(int i=0;i<tables.size();i++){
        	ObservableList<Data> data=FXCollections.observableArrayList();
        	String measured=DataLog.allData.get(0).get(i).getType();
        	int datacolumn=i;
        	TableColumn<Data, String> valuecol = new TableColumn<Data, String>(measured);
            valuecol.setMinWidth(100);
            valuecol.setCellValueFactory(
                    new PropertyValueFactory<Data, String>("value"));//the value column of each datatype
            
            
            TableColumn<Data, String> locationCol = new TableColumn<Data, String>("Recency");//the recency column of each data type
            locationCol.setMinWidth(100);
            locationCol.setCellValueFactory(new PropertyValueFactory<Data,String>("order"));
            
            Task<Void> task = new Task<Void>() {
          	  @Override
          	  public Void call() throws Exception {//thread which checks for new additions
          	    
          	    while (true) {
          	      
          	      Platform.runLater(new Runnable() {
          	        @Override
          	        public void run() {
          	        	if(tables.get(datacolumn).getItems().size()==0){
          	        		for(int j=0;j<DataLog.allData.get(0).size();j++){
          	        	  		
            	        		  if (DataLog.allData.get(0).get(j).getType()==measured){
            	        			  for(int k=0;k<DataLog.allData.size();k++)
            	        			 data.add(DataLog.allData.get(k).get(j));//inputs all values made before the scene starts
            	        		  }
            	        	  }
          	        	}
          	        	else{
          	          for(int j=0;j<DataLog.allData.get(0).size();j++){
          	        	  		
          	        		  if (DataLog.allData.get(0).get(j).getType()==measured){
          	        			 data.add(DataLog.allData.get(DataLog.allData.size()-1).get(j));//inputs any values made after the scene starts
          	        		  }
          	        	  }
          	        		  
          	          }}
          	        }				
          	      );
          	      //recommitting
          	      Thread.sleep(1000);
          	    }
          	  }
          	};
          	Thread th = new Thread(task);
          	th.setDaemon(true);
          	th.start();

            tables.get(i).setItems(data);
            tables.get(i).getColumns().addAll(valuecol, locationCol );
     

            vCenter.getChildren().addAll(tables.get(i));
        }
 
        Scene mainScene = new Scene(vCenter);
        primaryStage.setScene(mainScene);
        primaryStage.show();//show the tables
	}}
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage,String Datatype) {//this method is used for a single table
		primaryStage.setTitle("Astraeus");
        primaryStage.setWidth(850);
        primaryStage.setHeight(700);

        HBox vCenter = new HBox (40);
        vCenter.setMinWidth(282);
        vCenter.setStyle("-fx-background-color: GRAY");//sets the background
        
        if(DataLog.allData.size()<=0){}else{
        	int locationOfinfo=0;
        	for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size()-1);i++){
    			if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == Datatype){
    				locationOfinfo=i;
    			}
    		}
        
        	tables.add(new TableView<Data>());//creates the tables
        
        
        for(int i=0;i<tables.size();i++){
        	int datacolumn=i;
        	ObservableList<Data> data=FXCollections.observableArrayList();
        	String measured=DataLog.allData.get(0).get(locationOfinfo).getType();
        	TableColumn<Data, String> valuecol = new TableColumn<Data, String>(measured);
            valuecol.setMinWidth(100);
            valuecol.setCellValueFactory(
                    new PropertyValueFactory<Data, String>("value"));
            
            TableColumn<Data, String> locationCol = new TableColumn<Data, String>("Recency");
            locationCol.setMinWidth(100);
            locationCol.setCellValueFactory(new PropertyValueFactory<Data,String>("order"));
            
            Task<Void> task = new Task<Void>() {
          	  @Override
          	  public Void call() throws Exception {//thread which checks for new additions
          	    
          	    while (true) {
          	      
          	      Platform.runLater(new Runnable() {
          	        @Override
          	        public void run() {
          	        	if(tables.get(datacolumn).getItems().size()==0){//inputs all values made before the scene starts
          	        		for(int j=0;j<DataLog.allData.get(0).size();j++){
          	        	  		
            	        		  if (DataLog.allData.get(0).get(j).getType()==measured){//inputs any values made after the scene starts
            	        			  for(int k=0;k<DataLog.allData.size();k++)
            	        			 data.add(DataLog.allData.get(k).get(j));
            	        		  }
            	        	  }
          	        	}
          	        	else{
          	          for(int i=0;i<DataLog.allData.get(0).size();i++){
          	        	  
          	        		  if (DataLog.allData.get(0).get(i).getType()==measured){
          	        			 data.add(DataLog.allData.get(DataLog.allData.size()-1).get(i));
          	        		  }
          	        	  }
          	        		  
          	          }}
          	        }				
          	      );
          	      //recommitting
          	      Thread.sleep(1000);
          	    }
          	  }
          	};
          	Thread th = new Thread(task);
          	th.setDaemon(true);
          	th.start();

            tables.get(i).setItems(data);
            tables.get(i).getColumns().addAll(valuecol, locationCol );//show the tables
     

            vCenter.getChildren().addAll(tables.get(i));
        }
 
        Scene mainScene = new Scene(vCenter);
        primaryStage.setScene(mainScene);
        primaryStage.show();
	}}

	 
}
