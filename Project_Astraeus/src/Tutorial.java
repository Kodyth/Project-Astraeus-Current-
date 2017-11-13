

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tutorial extends Application{
	public static void main (String [] args) {	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {		
		primaryStage.setTitle("Astraeus");
        primaryStage.setWidth(850);
        primaryStage.setHeight(700);
        
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 10, 15, 10));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: WHITE;");
	    
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.setStyle("-fx-background-color: WHITE;");
	    
	    Scene mainScene = new Scene(hbox);
        primaryStage.setScene(mainScene);
        primaryStage.show();

	}
}

