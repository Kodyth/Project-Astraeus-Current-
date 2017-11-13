

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tutorial extends Application{
	public static void main (String [] args) {	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 10, 15, 10));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: WHITE;");
	    
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    vbox.setStyle("-fx-background-color: WHITE;");
	}
}

