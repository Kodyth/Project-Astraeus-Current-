import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	    
	    Scene tut1 = new Scene(hbox);
        primaryStage.setScene(tut1);
        primaryStage.show();
        
        Image arrow = new Image(getClass().getResourceAsStream("Resources/arrow.png"));
        Button next = new Button();
        next.setGraphic(new ImageView(arrow));
        next.setStyle("-fx-background-color: WHITE");
        next.setMaxHeight(50);
        next.setMaxWidth(100);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().add(next);
             
        next.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene tut2 = new Scene(vbox);
				primaryStage.setScene(tut2);
		        primaryStage.show();
			}
		});
	}
}

