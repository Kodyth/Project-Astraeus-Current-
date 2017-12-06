import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: Main GUI is the main menu of the program. It is the first page that displays after logging in. 
 * It contains buttons that lead to the command line and recent data charts. It also displays location of satellite,
 * temperature, voltage, and current readings.
 * Functions: Main, Start, MenuBarmaker
 * 
 * Collaborators: Kody, Geoffrey, Brandon, Noah, and Chloe
 * 
 */
public class MainGUI extends Application{
	public static List<String> commandList = new ArrayList<String>();
	String comPort =""; 
	UserAccount ua = new UserAccount();
	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//Setting parameters for the Stage
		
		primaryStage.setTitle("Astraeus");
		primaryStage.setWidth(1280);
		primaryStage.setHeight(720);
		Image logo = new Image("Resources/Astraeus Logo.png");
		primaryStage.getIcons().add(logo);
		primaryStage.setResizable(false);
		Pane mainLayout=new Pane();
		mainLayout.setStyle("-fx-background-color: WHITE");

		//Creating the image for use in the GUI
		Image background = new Image("Resources/Astraeus MainGUI.jpg");
		ImageView backgroundv = new ImageView(background);
		Image cube = new Image("Resources/Cubesat.png");
		ImageView cubev = new ImageView(cube);
		cubev.setFitWidth(274);
		cubev.setFitHeight(354);
		Text clock = new Text();
		clock.setLayoutX(950);
		clock.setLayoutY(600);
		clock.setText(LocalTime.now().toString());
		clock.setStyle("-fx-font: 46 gills-sans-MT; -fx-font-weight: bold");
		
		//Set up background
		backgroundv.setLayoutX(0);
		backgroundv.setLayoutY(0);
		backgroundv.fitWidthProperty().bind(mainLayout.widthProperty());
		backgroundv.fitHeightProperty().bind(mainLayout.heightProperty());
		mainLayout.getChildren().add(backgroundv);
		
		//Set up world map
		Image Map = new Image("Resources/map.bmp");
		ImageView map = new ImageView(Map);
		map.setLayoutX(80);
		map.setLayoutY(80);
		map.setFitWidth(750);
		map.setFitHeight(400);
		mainLayout.getChildren().add(clock);
		mainLayout.getChildren().add(map);

		//Creating Buttons for main GUI
		Button commandb = new Button("Command Line (Admin Use Only)");
		commandb.setMaxWidth(Double.MAX_VALUE);
		commandb.setMinHeight(50);
		commandb.setOnAction(e -> {
			command_line cmd = new command_line();
			try {
				cmd.start(primaryStage);
				cmd.setCommandList(commandList);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		commandb.setLayoutX(200);
		commandb.setLayoutY(520);
		Button rData = new Button("View Recorded Data");//recorded data button
		rData.setMaxWidth(Double.MAX_VALUE);
		rData.setMinHeight(50);
		rData.setOnAction(e -> {
			RDataGUI rdat = new RDataGUI();
			try {
				Stage secondary=new Stage();
				secondary.setWidth(1280);
				secondary.setHeight(720);
				rdat.start(secondary);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}//section which starts the recorded data tab when the button is clicked
		});
		rData.setLayoutX(600);
		rData.setLayoutY(520);
		mainLayout.getChildren().addAll(rData,commandb);
		
		//creating bars for temp and voltage
		PositionPointer lat = new PositionPointer();//latitude
		PositionPointer lon = new PositionPointer();//longitude
		BarLengthForData bar1 = new BarLengthForData();//temperature 
		BarLengthForData bar2 = new BarLengthForData();//voltage
		BarLengthForData bar3 = new BarLengthForData();//current
	
		Label tempe = new Label();
		tempe.setStyle("-fx-text-fill: BLACK; -fx-font: 15 gills-sans-MT");
		Label volta = new Label();
		volta.setStyle("-fx-text-fill: BLACK; -fx-font: 15 gills-sans-MT");
		Label curre = new Label();
		curre.setStyle("-fx-text-fill: BLACK; -fx-font: 15 gills-sans-MT");
		Rectangle temp = new Rectangle();
		temp.setOnMouseClicked(e -> {
			RDataGUI rdat = new RDataGUI();
			try {
				Stage secondary=new Stage();
				rdat.start(secondary, "TMP");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});//section which builds the constantly-updating temperature bar

		temp.setHeight(50);
		temp.setFill(Color.web("LIGHTGREEN"));
		Rectangle curr = new Rectangle();
		curr.setOnMouseClicked(e -> {
			RDataGUI rdat = new RDataGUI();
			try {
				Stage secondary=new Stage();
				rdat.start(secondary, "CUR");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});//section which builds the constantly-updating current bar

		curr.setHeight(50);
		curr.setFill(Color.web("LIGHTGREEN"));
		Rectangle volt = new Rectangle();
	
		Image sat = new Image("Resources/loc.png");
		ImageView loc = new ImageView();
        	loc.setImage(sat);
		map.setOnMouseClicked(e -> {
			RDataGUI rdat = new RDataGUI();
			try {
				Stage secondary=new Stage();
				rdat.start(secondary, "LAT");

				rdat.start(secondary, "LON");
			} catch (Exception e1) {
				e1.printStackTrace();
			}//section which builds the constantly-updating moving satellite picture
		});
		volt.setOnMouseClicked(e -> {
			RDataGUI rdat = new RDataGUI();
			try {
				Stage secondary=new Stage();
				rdat.start(secondary, "VOL");
			} catch (Exception e1) {
				e1.printStackTrace();//section which builds the constantly-updating voltage bar
			}
		});
		

		volt.setHeight(50);
		volt.setFill(Color.web("LIGHTGREEN"));

		@SuppressWarnings("rawtypes")
		Task task = new Task<Void>() {
			@Override
			public Void call() throws Exception {

				while (true) {

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							double temperature = bar1.DataToBarLength("TMP",1);//fetches the values for the temperature bar
							double voltage = bar2.DataToBarLength("VOL",1);//fetches the values for the voltage bar
							double current = bar3.DataToBarLength("CUR",1);//fetches the values for the current bar
							double locY= (lat.PositionPointerX()-5);//fetches the values for the latitude of the satellite
							double locX= (lon.PositionPointerY());//fetches the values for the longitude of the satellite
							loc.setX(locX);
							loc.setY(locY);
							if(loc.getY() < 79) {
								loc.setVisible(false);
							}//hides the satellite when nothing is happening
							else
							{
							loc.setVisible(true);	
							}
							temp.setWidth(temperature/400);
							volt.setWidth(voltage/5);
							curr.setWidth(current/5);
							volta.setText("Voltage: " + (voltage/100) + "  [V]\n");
							tempe.setText("Temperature: " + (temperature/100) + "  [K]\n");
							curre.setText("Current: " + (current/100) + "  [A]\n");
							tempe.setTextFill(Color.SLATEGRAY);//location of the text and bar, color of the text.
							tempe.setLayoutX(930);
							tempe.setLayoutY(110);
							temp.setLayoutX(930);
							temp.setLayoutY(160);
							volta.setTextFill(Color.SLATEGRAY);
							volta.setLayoutX(930);
							volta.setLayoutY(240);
							volt.setLayoutX(930);
							volt.setLayoutY(285);
							curre.setTextFill(Color.SLATEGRAY);
							curre.setLayoutX(930);
							curre.setLayoutY(360);
							curr.setLayoutX(930);
							curr.setLayoutY(410);
							clock.setText(LocalTime.now().toString());
						}				
					});

					Thread.sleep(1000);
				}
			}
		};

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		mainLayout.getChildren().addAll(volt,temp,tempe,volta,loc, curr, curre);
		
		//creating text field and launch
		TextField serial = new TextField();
		serial.setPromptText("Serial Comm");
		Button launch = new Button("Launch");
		launch.setOnAction(e -> {
			try {
				Close.onoff=0;
				COMPORT.portNum=serial.getText();
				SerialComm.Run(serial.getText());
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		serial.setLayoutX(920);
		serial.setLayoutY(520);
		launch.setLayoutX(1100);
		launch.setLayoutY(520);
		mainLayout.getChildren().addAll(launch,serial);

		
		MenuBar menubar = MenuBarmaker(primaryStage);
		BorderPane border = new BorderPane(mainLayout);
		border.setTop(menubar);
		Scene mainScene = new Scene(border);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	MenuBar MenuBarmaker(Stage primaryStage) {
		Menu astraeus = new Menu("Astreaus"); 
		Menu file = new Menu("File");
		Menu help = new Menu("Help");

		MenuItem disconnect = new MenuItem("Disconnect");
		MenuItem logout = new MenuItem("Logout");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem helpt = new MenuItem("Tutorial");

		disconnect.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Close.onoff=1;
			}
		});
		logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				loginGUI gui = new loginGUI();
				gui.start(primaryStage);
				
				ua.logout();
			}
		});
		open.setOnAction(e -> {
			try {
				import_data.read();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		save.setOnAction(e -> {
			try {
				export_data.write();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		helpt.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Tutorial t = new Tutorial();
				t.start(primaryStage);
			}
		});
		
		astraeus.getItems().addAll(disconnect, logout);
		file.getItems().addAll(open, save);
		help.getItems().addAll(helpt);
		MenuBar menubar = new MenuBar(astraeus, file, help);
		return menubar;
	}

	public void setCommandList(List<String> input) {
		commandList = input;
	}

}



