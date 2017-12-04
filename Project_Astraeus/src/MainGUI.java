
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Pos;


/**
 * Description: Main GUI is the main menu of the program. It is the first page that displays after logging in. Contains buttons that lead to every other section of the program. 
 * Will display location of satellite, temperature readings, and voltage readings.
 * Functions: Main, Start
 * 
 * FRANCE IS BACON
 * HAIL BRITTANIA
 * USA USA USA
 */
public class MainGUI extends Application{
	private command_line command;
	private boolean trigger;


	public static void main(String[] args) {
		try {
						
			System.out.println("Test");
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Test2");
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//Setting parameters for the Stage

		primaryStage.setTitle("Astraeus");
		primaryStage.setWidth(850);
		primaryStage.setHeight(700);


		//Creating Horizontal Box and Vertical Boxes that make up the main scene
		HBox mainLayout2 = new HBox();
            Pane mainLayout=new Pane();
		VBox vLeft = new VBox(30);
		vLeft.setMinWidth(284);
		vLeft.setPadding(new Insets(15, 0, 15, 0));
		VBox vRight = new VBox(10);
		vRight.setMinWidth(284);
		VBox vCenter = new VBox (40);
		vCenter.setMinWidth(282);
		HBox hRight = new HBox(10);
		mainLayout2.getChildren().addAll(vLeft, vCenter, vRight);
		             mainLayout.getChildren().add(mainLayout2);
		mainLayout.setStyle("-fx-background-color: WHITE");

		//Creating the image for use in the GUI
		Image cube = new Image("Resources/Cubesat.png");
		ImageView cubev = new ImageView(cube);
		cubev.setFitWidth(274);
		cubev.setFitHeight(354);

		// This is a temporary stand-in for the actual map function
		Image dummyMap = new Image("Resources/dummymap.png");
		ImageView map = new ImageView(dummyMap);
		map.setFitWidth(274);
		map.setFitHeight(285);
		vLeft.getChildren().addAll(cubev, map);

		//Creating the main label for the GUI
		BorderPane root = new BorderPane();
		Label astraeus = new Label("Astraeus \n\n\n\n");
		astraeus.setStyle("-fx-text-fill: BLACK; -fx-font: 50 century-gothic; -fx-font-weight: bold");
		astraeus.setPadding(new Insets(0, 0, 10, 0));
		root.setMaxWidth(Double.MAX_VALUE);
		root.setCenter(astraeus);

		//Creating Buttons for main GUI
		Button commandb = new Button("Control Cubesat/ View Commands");
		commandb.setMaxWidth(Double.MAX_VALUE);
		commandb.setMinHeight(50);
		commandb.setOnAction(e -> {
			command_line cmd = new command_line();
			try {
				cmd.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		Button rtData= new Button("View Real-Time Data");
		rtData.setMaxWidth(Double.MAX_VALUE);
		rtData.setMinHeight(50);
		Button rData = new Button("View Recorded Data");
		rData.setMaxWidth(Double.MAX_VALUE);
		rData.setMinHeight(50);
		rData.setOnAction(e -> {
			RDataGUI rdat = new RDataGUI();
			try {
				Stage secondary=new Stage();
				             	rdat.start(secondary);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		/**************LOGOUT*****************/
		Button logout = new Button();
		Image logoutImage = new Image(getClass().getResourceAsStream("Resources/logout_button.png"));
		logout.setGraphic(new ImageView(logoutImage));
		logout.setStyle("-fx-background-color: WHITE");
		logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				loginGUI gui = new loginGUI();
				gui.start(primaryStage);
				UserAccount ua = null;
				ua.logout();
			}
		});

		/**************TUTORIAL***************/  
		Button tutorial = new Button("Tutorial");
		tutorial.setMaxWidth(Double.MAX_VALUE);
		tutorial.setMinHeight(50);

		tutorial.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Tutorial t = new Tutorial();
				t.start(primaryStage);
			}
		});


		hRight.getChildren().addAll(logout);

		vCenter.getChildren().addAll(root, commandb, rData, tutorial);

		//creating bars for temp and voltage. arbitrary values inuted temporarily
		BarLengthForData bar1 = new BarLengthForData();
		BarLengthForData bar2 = new BarLengthForData();
		BarLengthForData lat = new BarLengthForData();
		             BarLengthForData lon = new BarLengthForData();
		Label tempe = new Label();
		tempe.setStyle("-fx-text-fill: BLACK; -fx-font: 15 century-gothic");
		Label volta = new Label();
		volta.setStyle("-fx-text-fill: BLACK; -fx-font: 15 century-gothic");
		Rectangle temp = new Rectangle();

		temp.setHeight(50);
		temp.setFill(Color.web("RED"));
		Rectangle volt = new Rectangle();
		Rectangle loc = new Rectangle();
		              loc.setFill(Color.web("BLUE"));
		              loc.setHeight(10);
		              loc.setWidth(10);

		volt.setHeight(50);
		volt.setFill(Color.web("RED"));
		@SuppressWarnings("rawtypes")
		 			Task task = new Task<Void>() {
			@Override
			public Void call() throws Exception {

				while (true) {

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							double temperature = bar1.DataToBarLength("TMP",1);
							double voltage = bar2.DataToBarLength("VOL",1);
							double locY= (Math.sin(lat.DataToBarLength("LAT",100))*10)+600;
							             	          double locX= (lon.DataToBarLength("LON", 100)*.9);
							             	          loc.setX(locX);
							             	          loc.setY(locY);
							temp.setWidth(temperature/400);
							volt.setWidth(voltage/5);
							volta.setText("Voltage: " + (voltage/100) + "\n");
							tempe.setText("Temperature: " + (temperature/100) + "\n");
						}				
					});

					Thread.sleep(1000);
				}
			}
		};

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		//creating text field and launch
		TextField serial = new TextField();
		serial.setPromptText("Serial Comm");
		Button launch = new Button("Launch");
		launch.setOnAction(e -> {
			try {
				SerialComm.Run(serial.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		HBox hb = new HBox();
		hb.getChildren().addAll(serial, launch);
		
		//creating text field and save
		TextField filename = new TextField();
		filename.setPromptText("File name");
		Button save = new Button("Save");
		save.setOnAction(e -> {
			try {
				export_data.write();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(filename, save);
		vRight.getChildren().addAll(hb, hRight, tempe,temp,volta,volt, hb1);
		mainLayout.getChildren().add(loc);
		Scene mainScene = new Scene(mainLayout);
		primaryStage.setScene(mainScene);
		primaryStage.show();


	}
}




