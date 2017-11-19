import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
 


 
        
public class RDataGUI extends Application{
	private String datatype = "LON";
	private TableView<Data> table = new TableView<Data>();
	
	private final ArrayList<TableView<Data>> tables= new ArrayList<TableView<Data>>();
			public static void main (String [] args) {	
		launch(args);
	}
	
	public void start(Stage primaryStage) {
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
        
        for(int i=0;i<DataLog.allData.size();i++){
        	tables.add(new TableView<Data>());
        }
        
        for(int i=0;i<tables.size();i++){
        	ObservableList<Data> data=FXCollections.observableArrayList();
        	String measured=DataLog.allData.get(i).get(0).getType();
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
          	          for(int i=0;i<DataLog.allData.size();i++){
          	        	  for(int j=0;j<DataLog.allData.get(i).size();j++){
          	        		  if (DataLog.allData.get(i).get(j).getType()==measured){
          	        			  data.add(DataLog.allData.get(i).get(j));
          	        		  }
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

            tables.get(i).setItems(data);
            tables.get(i).getColumns().addAll(valuecol, locationCol );
     

            mainLayout.getChildren().addAll(tables.get(i));
        }
      
        //        
//       ArrayList<TableColumn<Data, String>> variableTables = new ArrayList<TableColumn<Data, String>>();
//       for(int i=0;i<DataLog.allData.size();i++){
//       	variableTables.add( new TableColumn<Data, String>(DataLog.allData.get(i).get(0).getType()));
//       }
//       for(int i=0; i<variableTables.size();i++){
//           variableTables.get(i).setMinWidth(100);
//           variableTables.get(i).setCellValueFactory(
//           new PropertyValueFactory<Data, String>("order"));
//       	table.getColumns().add(variableTables.get(i));
//       	Task<Void> task = new Task<Void>() {
//          	  @Override
//           	  public Void call() throws Exception {
//          	    int l=i;
//          	    while (true) {
//          	      
//          	      Platform.runLater(new Runnable() {
//          	        @Override
//          	        public void run() {
//          	        
//           	       
//           	          for(int i=0;i<DataLog.allData.size();i++){
//           	        	  for(int j=0;j<DataLog.allData.get(i).size();j++){
//           	        		  if (DataLog.allData.get(i).get(j).getType()==variableTables.get(l).getProperty()){
//           	        			  data.add(DataLog.allData.get(i).get(j));
//           	        		  }
//           	        	  }
//           	        		  
//           	          }
//           	        }				
//           	      });
//           	      //recommitting
//         	      Thread.sleep(1000);
//         	    }
//           	  }
//	
//        }
        Scene mainScene = new Scene(mainLayout);
        primaryStage.setScene(mainScene);
        primaryStage.show();
	}
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage,String Datatype) {
		
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
      	          for(int i=0;i<DataLog.allData.size();i++){
      	        	  for(int j=0;j<DataLog.allData.get(i).size();j++){
      	        		  if (DataLog.allData.get(i).get(j).getType()==datatype){
      	        			  data.add(DataLog.allData.get(i).get(j));
      	        		  }
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
 

        mainLayout.getChildren().addAll(table);
 

         
         Scene mainScene = new Scene(mainLayout);
         primaryStage.setScene(mainScene);
         primaryStage.show();

	 }
}
