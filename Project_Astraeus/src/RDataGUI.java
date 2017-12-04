import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

 


 
        
public class RDataGUI extends Application{
	private String datatype = "LON";
	private TableView<Data> table = new TableView<Data>();

	private final ArrayList<TableView<Data>> tables= new ArrayList<TableView<Data>>();
			public static void main (String [] args) {	
		launch(args);
	}
	
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Astraeus");
        primaryStage.setWidth(850);
        primaryStage.setHeight(700);

        HBox vCenter = new HBox (40);
        vCenter.setMinWidth(282);

        if(DataLog.allData.size()<=0){}else{
        for(int i=0;i<DataLog.allData.get(0).size();i++){
        	tables.add(new TableView<Data>());
        }
        
        for(int i=0;i<tables.size();i++){
        	ObservableList<Data> data=FXCollections.observableArrayList();
        	String measured=DataLog.allData.get(0).get(i).getType();
        	TableColumn<Data, String> valuecol = new TableColumn<Data, String>(measured);
            valuecol.setMinWidth(100);
            valuecol.setCellValueFactory(
                    new PropertyValueFactory<Data, String>("value"));
            
            TableColumn<Data, String> locationCol = new TableColumn<Data, String>("Recency");
            locationCol.setMinWidth(100);
            locationCol.setCellValueFactory(
                    new PropertyValueFactory<Data, String>("order"));
            
            Task<Void> task = new Task<Void>() {
          	  @Override
          	  public Void call() throws Exception {
          	    
          	    while (true) {
          	      
          	      Platform.runLater(new Runnable() {
          	        @Override
          	        public void run() {
          	          for(int i=0;i<DataLog.allData.get(0).size();i++){
          	        	  
          	        		  if (DataLog.allData.get(0).get(i).getType()==measured){
          	        			 data.add(DataLog.allData.get(DataLog.allData.size()-1).get(i));
          	        		  }
          	        	  }
          	        		  
          	          }
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
        primaryStage.show();
	}}
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage,String Datatype) {
		Button mData = new Button("Back");
        mData.setMaxWidth(Double.MAX_VALUE);
        mData.setMinHeight(50);
        mData.setOnAction(e -> {
        	MainGUI mdat = new MainGUI();
        	try {
        	mdat.start(primaryStage);
        	} catch (Exception e1) {
        	e1.printStackTrace();
        	}});
        
		ObservableList<Data> data=FXCollections.observableArrayList();

		 primaryStage.setTitle("Astraeus");
         primaryStage.setWidth(850);
         primaryStage.setHeight(700);
         
        HBox mainLayout = new HBox();
         VBox vLeft = new VBox(30);
         vLeft.setMinWidth(284);
         vLeft.setPadding(new Insets(15, 0, 15, 0));
         VBox vRight = new VBox(30);
         vRight.setMinWidth(284);
         VBox vCenter = new VBox (40);
         vCenter.setMinWidth(282);
         mainLayout.getChildren().addAll(vLeft, vCenter, vRight);
         mainLayout.setStyle("-fx-background-color: WHITE");
         vCenter.getChildren().addAll(mData);
        table.setEditable(true);
       
        Datatype = datatype;
 
        TableColumn<Data, String> valuecol = new TableColumn<Data, String>(datatype);
        valuecol.setMinWidth(100);
        valuecol.setCellValueFactory(
                new PropertyValueFactory<Data, String>("value"));
 
        TableColumn<Data, String> locationCol = new TableColumn<Data, String>("Recency");
        locationCol.setMinWidth(100);
        locationCol.setCellValueFactory(
                new PropertyValueFactory<Data, String>("order"));
        
        Task<Void> task = new Task<Void>() {
      	  @Override
      	  public Void call() throws Exception {
      	    
      	    while (true) {
      	      
      	      Platform.runLater(new Runnable() {
      	        @Override
      	        public void run() {
      	          for(int i=0;i<DataLog.allData.get(0).size();i++){

      	        		  if (DataLog.allData.get(0).get(i).getType()==datatype){
      	        			  data.add(DataLog.allData.get(DataLog.allData.size()-1).get(i));
      	        		  }
      	        	  }
      	        		  

      	        }				
      	      });
      	      //recommitting
      	      Thread.sleep(1000);
      	    }
  	  }
      	};
      	Thread th = new Thread(task);
      	th.setDaemon(true);
      	th.start();

        table.setItems(data);
        table.getColumns().addAll(valuecol, locationCol );
 

        vCenter.getChildren().addAll(mData,table);
        
 

         
         Scene mainScene = new Scene(vCenter);
         primaryStage.setScene(mainScene);
         primaryStage.show();

	 }
}