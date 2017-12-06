import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import java.time.*;

/**
 * 
 * The command_line class is a composed of a GUI element representing a command line similar to the Eclipse Console. It accepts commands
 * entered as Strings into a TextField, processes what it receives, verifies commands are entered properly, forwards output information to 
 * the communications class, and stores all entered commands in a data log GUI element.
 * 
 * @author Kody Miller
 *
 *
 */
public class command_line extends Application{

	private Pane cl_pane;
	private String command;
	private String response;
	public List<String> commandList = new ArrayList<String>();
	public List<String> commandListDisplay = new ArrayList<String>();
	private String commandListString;
	private TextArea commandLog = new TextArea();
	private String timeStamp;
	private static Alert alertDialog = new Alert(null);
	private TextInputDialog inputTextBox = new TextInputDialog();
	private String userToPromote;

	@Override
	public void start(Stage primaryStage) throws Exception {
		cl_pane = new Pane();

		//Create elements and add to the pane
		Image background = new Image("Resources/Astraeus MainGUI.jpg");
		ImageView backgroundv = new ImageView(background);
		backgroundv.setLayoutX(0);
		backgroundv.setLayoutY(0);
		backgroundv.fitWidthProperty().bind(cl_pane.widthProperty());
		backgroundv.fitHeightProperty().bind(cl_pane.heightProperty());
		TextField enterCommand = new TextField();
		enterCommand.setLayoutX(80);
		enterCommand.setLayoutY(480);
		enterCommand.setPrefSize(750,12);
		enterCommand.setStyle("-fx-control-inner-background: BLACK;-fx-text-fill: WHITE;");

		commandLog.setLayoutX(80);
		commandLog.setLayoutY(80);
		commandLog.setPrefSize(750, 400);
		commandLog.setEditable(false);
		commandLog.setStyle("-fx-control-inner-background: BLACK;-fx-text-fill: WHITE;");
		Button returnToMain = new Button("Return to Main");
		returnToMain.setMaxHeight(50);
		returnToMain.setMaxWidth(100);

		returnToMain.setMinHeight(40);
		returnToMain.setMinWidth(100);
		returnToMain.setLayoutX(200);
		returnToMain.setLayoutY(520);
		returnToMain.setOnAction(e -> {
			MainGUI mg = new MainGUI();
			try {
				mg.start(primaryStage);
				mg.setCommandList(commandList);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		UserAccount ua = new UserAccount();
		enterCommand.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER){

				try {
					if(ua.checkAdmin()==true) {
						if(enterCommand.getText().trim().isEmpty() == false){

							command = enterCommand.getText();

							if(command.contains(","))
							{
								throw new CommandError();
							}
							else if(command.equals("checkSoftware") == true){
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Retrieving Software information");
								alertDialog.show();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);
							}

							else if(command.equals("checkBatteryCells") == true){
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Retrieving Battery Cells Status...");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);
							}

							else if(command.equals("checkSolarCells") == true) {
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Retrieving Solar Cells Status...");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);

							}
							else if(command.equals("resetNavigation") == true) {
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Resetting CubeSat Navigation...");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);
							}

							else if(command.equals("reboot") == true) {
								enterCommand.clear();
								timeStamp = LocalDateTime.now().toString();
								command = timeStamp + ": " + command;
								commandList.add(0, command);
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText("CubeSat is now rebooting.");
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
							}

							else if(command.equals("changeBatteryCells") == true) {
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Changing battery cells...");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);
							}

							else if(command.equals("changeSolarCells") == true) {
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Changing solar cells...");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);
							}

							else if(command.equals("changeOrientation") == true) {
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Changing orientation...");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);
							}

							else if(command.equals("changeMagnetometers") == true) {
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.NONE);
								alertDialog.setHeaderText("Changing magnetometers...");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								SerialComm.Send(command); 	//Send command to SerialComm
								try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();} //Wait for response
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								response = SerialComm.getCommand();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText(response);
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								response = timeStamp + ": " + response;
								commandList.add(0,response);
							}

							else if(command.equals("help")== true) {
								enterCommand.clear();
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText("changeBatteryCells:		Toggle battery cells On/Off\n"
										+ "changeMagnetometers: 	Toggle control magnetometers On/Off\n"
										+ "changeOrientation:		Adjust current orientation settings (x,y,z)\n"
										+ "changeSolarCells:		Toggle solar cells On/Off\n"
										+ "checkBatteryCells:		Gives current battery cell status\n"
										+ "checkSolarCells:			Gives current solar cell status\n"
										+ "checkSoftware:			Gives current version of software\n"
										+ "exportLog:				Prompts the user to save the command log\n"
										+ "resetNavigation:			Resets navigation parameters to initial values\n"
										+ "reboot:					Reboots the CubeSat\n"
										+ "promoteUser:				Allows an admin to promote a user to admin\n");
								alertDialog.show();
								timeStamp = LocalDateTime.now().toString();
								command = timeStamp + ": " + command;
								commandList.add(0,command);
							}

							else if(command.equals("exportLog")==true) {
								enterCommand.clear();
								FileChooser fileChoose = new FileChooser();
								fileChoose.getExtensionFilters().addAll(new ExtensionFilter("Text Files", ".txt"));
								try(  PrintWriter out = new PrintWriter(fileChoose.showSaveDialog(primaryStage)  )  ){
									for(int i = 0; i < commandList.size();i++) {
										out.println(commandList.get(i)+"\n");
									}
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								}
							}

							else if(command.equals("promoteUser")==true) {
								enterCommand.clear();
								timeStamp = LocalDateTime.now().toString();
								command = timeStamp + ": " + command;
								commandList.add(0,command);
								inputTextBox.setHeaderText("Enter the user account you would like to promote");
								inputTextBox.showAndWait();
								userToPromote = inputTextBox.getResult();
								{
									try {
										boolean found = false;
										FileReader fr = new FileReader("UserAccountInfo.txt");
										BufferedReader br=new BufferedReader(fr);
										String line = br.readLine();
										StringBuilder sb = new StringBuilder();
										while (line != null) {
											sb.append(line);
											String l = sb.toString();
											if(l.contains(userToPromote)) {
												FileWriter fw = new FileWriter("AdminAccountInfo.txt", true);
												BufferedWriter write = new BufferedWriter(fw);
												String userpass = l;
												write.write(userpass);
												write.close();
												br.close();
												alertDialog.setAlertType(AlertType.CONFIRMATION);
												alertDialog.setHeaderText("User promoted succesfully");
												alertDialog.showAndWait();
												found = true;
												break;

											}	

											line = br.readLine();	
											sb.append(System.lineSeparator());
										}
										if(found==false) {
											alertDialog.setAlertType(AlertType.WARNING);
											alertDialog.setHeaderText("Unable to locate user, be sure the account exists before trying to promote it");
											alertDialog.showAndWait();
										}

									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}


							}

							else {
								enterCommand.clear();
								throw new CommandError();
							}

							commandListDisplay = commandList;
							Collections.reverse(commandListDisplay);
							commandListString = "";
							for(int i =0; i<commandListDisplay.size();i++) {
								commandListString = commandListString + commandListDisplay.get(i)+" \n";
							}

							commandLog.setText(commandListString);

						}
						else if(enterCommand.getText().trim().isEmpty() == true){
							throw new CommandError();

						}

					}
					//if user is not an admin
					else{
						alertDialog.setAlertType(AlertType.WARNING);
						alertDialog.setHeaderText("User does not have the appropriate permissions to use the command line");;
						alertDialog.show();
					}

				}

				catch (CommandError e1) {
					alertDialog.setAlertType(AlertType.WARNING);
					alertDialog.setHeaderText("User input could not be accepted due to improper formatting or invalid characters!");;
					alertDialog.show();
				}


			}});


		Text clock = new Text();
		clock.setLayoutX(950);
		clock.setLayoutY(600);
		clock.setText(LocalTime.now().toString());
		clock.setStyle("-fx-font: 46 gills-sans-MT; -fx-font-weight: bold");

		//creating bars for temp and voltage
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
		});//section which builds the constantly-updating temperature bar

		curr.setHeight(50);
		curr.setFill(Color.web("LIGHTGREEN"));
		Rectangle volt = new Rectangle();
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

		@SuppressWarnings("rawtypes")//test
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
		cl_pane.getChildren().add(backgroundv);
		cl_pane.getChildren().addAll(volt,temp,tempe,volta, curr, curre);
		cl_pane.getChildren().add(enterCommand);
		cl_pane.getChildren().add(commandLog);
		cl_pane.getChildren().add(returnToMain);
		cl_pane.getChildren().add(clock);


		//Setup GUI
		Scene cl_scene = new Scene(cl_pane,1280,720,Color.GREY);
		primaryStage.setScene(cl_scene);
		primaryStage.setMaxWidth(1280);
		primaryStage.setMinWidth(1280);
		primaryStage.setMaxHeight(720);
		primaryStage.setMinHeight(720);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Astraeus: Command Line");


		//Show the GUI
		primaryStage.show();

		MenuBar menubar = MenuBarmaker(primaryStage);
		BorderPane border = new BorderPane(cl_pane);
		border.setTop(menubar);
		Scene mainScene = new Scene(border);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

	public MenuBar MenuBarmaker(Stage primaryStage) {
		Menu astraeus = new Menu("Astreaus"); 
		Menu file = new Menu("File");
		Menu help = new Menu("Help");

		MenuItem disconnect = new MenuItem("Disconnect");
		MenuItem logout = new MenuItem("Logout");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem helpb = new MenuItem("Help");
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
				UserAccount ua = new UserAccount();
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

		helpt.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Tutorial t = new Tutorial();
				t.start(primaryStage);
			}
		});

		astraeus.getItems().addAll(disconnect, logout);
		file.getItems().addAll(open, save);
		help.getItems().addAll(helpb,helpt);
		MenuBar menubar = new MenuBar(astraeus, file, help);
		return menubar;
	}

	public void setCommandList(List<String> input) {
		commandList = input;
		commandListDisplay = commandList;
		Collections.reverse(commandListDisplay);
		commandListString =  "";
		for(int i =0; i<commandListDisplay.size();i++) {
			commandListString = commandListString + commandListDisplay.get(i)+" \n";
		}

		commandLog.setText(commandListString);


	}


	//Make sure execution begins	(this will be placed elsewhere but here for testing purposes)
	public static void main(String[] args)	{	launch(args);	}

}

class CommandError extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandError() {}
}		
