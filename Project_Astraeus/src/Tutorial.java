import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		primaryStage.setTitle("Astraeus");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setResizable(false);
        
        Pane p1 = new Pane();
        p1.setStyle("-fx-background-color: WHITE");
        Pane p2 = new Pane();
        p2.setStyle("-fx-background-color: WHITE");
        Pane p3 = new Pane();
        p3.setStyle("-fx-background-color: WHITE");
        Pane p4 = new Pane();
        p4.setStyle("-fx-background-color: WHITE");
        Pane p5 = new Pane();
        p5.setStyle("-fx-background-color: WHITE");
        Pane p6 = new Pane();
        p6.setStyle("-fx-background-color: WHITE");
        Pane p7 = new Pane();
        p7.setStyle("-fx-background-color: WHITE");
        Pane p8 = new Pane();
        p8.setStyle("-fx-background-color: WHITE");
        
	    
        Button exitTut = new Button("Exit Tutorial");
        exitTut.setStyle("-fx-font: 20 century-gothic; -fx-font-weight: bold ");
        exitTut.setLayoutX(1100);
        exitTut.setLayoutY(330);
         
        Image arrow = new Image(getClass().getResourceAsStream("Resources/arrow.png"));
        ImageView pic1 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic1.png")));
        ImageView pic2 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic2.png")));
        ImageView pic3 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic3.png")));
        ImageView pic4 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic4.png")));
        ImageView pic5 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic5.png")));
        ImageView pic6 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic6.png")));
        ImageView pic7 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic7.png")));
        ImageView pic8 = new ImageView(new Image(getClass().getResourceAsStream("Resources/pic8.png")));
        
        pic1.setLayoutX(195);
        pic1.setLayoutY(50);
        pic2.setLayoutX(195);
        pic2.setLayoutY(50);
        pic3.setLayoutX(195);
        pic3.setLayoutY(50);
        pic4.setLayoutX(250);
        pic4.setLayoutY(90);
        pic5.setLayoutX(195);
        pic5.setLayoutY(50);
        pic6.setLayoutX(195);
        pic6.setLayoutY(50);
        pic7.setLayoutX(195);
        pic7.setLayoutY(50);
        pic8.setLayoutX(195);
        pic8.setLayoutY(50);
        
        Button next1 = new Button();
        next1.setGraphic(new ImageView(arrow));
        next1.setStyle("-fx-background-color: WHITE");
        next1.setLayoutX(1150);
        next1.setLayoutY(330);
        next1.setMaxHeight(50);
        next1.setMaxWidth(100);
        next1.setAlignment(Pos.CENTER_RIGHT);
        
        Button next2 = new Button();
        next2.setGraphic(new ImageView(arrow));
        next2.setStyle("-fx-background-color: WHITE");
        next2.setLayoutX(1150);
        next2.setLayoutY(330);
        next2.setMaxHeight(50);
        next2.setMaxWidth(100);
        next2.setAlignment(Pos.CENTER_RIGHT);
        
        Button next3 = new Button();
        next3.setGraphic(new ImageView(arrow));
        next3.setStyle("-fx-background-color: WHITE");
        next3.setLayoutX(1150);
        next3.setLayoutY(330);
        next3.setMaxHeight(50);
        next3.setMaxWidth(100);
        next3.setAlignment(Pos.CENTER_RIGHT);
        
        Button next4 = new Button();
        next4.setGraphic(new ImageView(arrow));
        next4.setStyle("-fx-background-color: WHITE");
        next4.setLayoutX(1150);
        next4.setLayoutY(330);
        next4.setMaxHeight(50);
        next4.setMaxWidth(100);
        next4.setAlignment(Pos.CENTER_RIGHT);
        
        Button next5 = new Button();
        next5.setGraphic(new ImageView(arrow));
        next5.setStyle("-fx-background-color: WHITE");
        next5.setLayoutX(1150);
        next5.setLayoutY(330);
        next5.setMaxHeight(50);
        next5.setMaxWidth(100);
        next5.setAlignment(Pos.CENTER_RIGHT);
        
        Button next6 = new Button();
        next6.setGraphic(new ImageView(arrow));
        next6.setStyle("-fx-background-color: WHITE");
        next6.setLayoutX(1150);
        next6.setLayoutY(330);
        next6.setMaxHeight(50);
        next6.setMaxWidth(100);
        next6.setAlignment(Pos.CENTER_RIGHT);
        
        Button next7 = new Button();
        next7.setGraphic(new ImageView(arrow));
        next7.setStyle("-fx-background-color: WHITE");
        next7.setLayoutX(1150);
        next7.setLayoutY(330);
        next7.setMaxHeight(50);
        next7.setMaxWidth(100);
        next7.setAlignment(Pos.CENTER_RIGHT);
       
        
        p1.getChildren().addAll(pic1, next1);		//1st page
        p2.getChildren().addAll(pic2, next2);		//2nd page
        p3.getChildren().addAll(pic3, next3);		//3rd page
        p4.getChildren().addAll(pic4, next4);		//4th page
        p5.getChildren().addAll(pic5, next5);		//5th page
        p6.getChildren().addAll(pic6, next6);		//6th page
        p7.getChildren().addAll(pic7, next7);		//7th page
        p8.getChildren().addAll(pic8, exitTut);		//8th page
        
	    Scene s1 = new Scene(p1);
        primaryStage.setScene(s1);
        primaryStage.show();

        next1.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene s2 = new Scene(p2);
				primaryStage.setScene(s2);
		        primaryStage.show();
			}
		});
        
        next2.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene s3 = new Scene(p3);
				primaryStage.setScene(s3);
		        primaryStage.show();
			}
		});
        
        next3.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene s4 = new Scene(p4);
				primaryStage.setScene(s4);
		        primaryStage.show();
			}
		});
        
        next4.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene s5 = new Scene(p5);
				primaryStage.setScene(s5);
		        primaryStage.show();
			}
		});
        
        next5.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene s6 = new Scene(p6);
				primaryStage.setScene(s6);
		        primaryStage.show();
			}
		});
        
        next6.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene s7 = new Scene(p7);
				primaryStage.setScene(s7);
		        primaryStage.show();
			}
		});
        
        next7.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Scene s8 = new Scene(p8);
				primaryStage.setScene(s8);
		        primaryStage.show();
			}
		});
        
        exitTut.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				MainGUI mg = new MainGUI();
				mg.start(primaryStage);
			}
		});
        
	}
}

