import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

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
	public static List<String> commandList = new ArrayList<String>();
	public List<String> commandListDisplay = new ArrayList<String>();
	private String commandListString;
	private String  naviValues = "";
	private String commandReturn;
	private static Alert alertDialog = new Alert(null);
	private TextInputDialog inputTextBox = new TextInputDialog();
	@Override
	public void start(Stage primaryStage) throws Exception {
		cl_pane = new Pane();
		
		//Create elements and add to the pane
		TextField enterCommand = new TextField();
		enterCommand.setLayoutX(220);
		enterCommand.setLayoutY(400);
		enterCommand.setPrefSize(400,12);
		enterCommand.setStyle("-fx-control-inner-background: BLACK;-fx-text-fill: GREEN;");
		TextArea commandLog = new TextArea();
		commandLog.setLayoutX(220);
		commandLog.setLayoutY(50);
		commandLog.setPrefSize(400, 350);
		commandLog.setEditable(false);
		commandLog.setStyle("-fx-control-inner-background: BLACK;-fx-text-fill: GREEN;");
		Button returnToMain = new Button();
		returnToMain.setMaxHeight(50);
		returnToMain.setMaxWidth(100);
			
        /**************HELP***************/
        Button help = new Button("Help");
        help.setMinHeight(40);
        help.setMinWidth(100);
        help.setOnAction(e -> {
//        	Tutorial t = new Tutorial();
        	try {
//        	t.start(primaryStage);
        	} catch (Exception e1) {
        	e1.printStackTrace();
        	}
        });
        
        Button backToMain = new Button("Back To Main Menu");
        backToMain.setMinHeight(40);
        backToMain.setMinWidth(100);
        backToMain.setLayoutX(150);
        backToMain.setOnAction(e -> {
        	MainGUI mg = new MainGUI();
        	try {
        	mg.start(primaryStage);
        	} catch (Exception e1) {
        	e1.printStackTrace();
        	}
        });
		
		
		//Event Handling for command entered
		enterCommand.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER){

				try {

					if(enterCommand.getText().trim().isEmpty() == false){

						command = enterCommand.getText();

						if(command.contains(","))
						{
							throw new CommandError();
						}
						//else if(command.equals("checkSoftware") == true){
						//	enterCommand.clear();
						//	//commandList.add(0,"Current Software Status: Good");
						//	commandList.add(1,command);							
						//	
						//	//Send command to SerialComm
						//	//SerialComm.Send(command);
						//}
						
						//else if(command.equals("changeSoftware") == true){
						//	enterCommand.clear();
						//	commandList.add(0,command);
						//	
						//	//Send command to SerialComm
						//	//SerialComm.Send(command);
						//}
						
						else if(command.equals("checkBatteryCells") == true){
							enterCommand.clear();
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText("CubeSat Battery Cells status is green!");
							alertDialog.show();
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("checkSolarCells") == true) {
							enterCommand.clear();
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText("Retrieving Solar Cells Status...");
							alertDialog.showAndWait();
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command); 
							try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();}
							response = SerialComm.getCommand();
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText(response);
							alertDialog.show();
							commandList.add(0,response);
							
						}
						
						else if(command.equals("resetNavigation") == true) {
							enterCommand.clear();
							commandList.add(0, command);
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText("Resetting CubeSat Navigation...");
							alertDialog.showAndWait();
							
							//Send command to SerialComm
							SerialComm.Send(command);
							try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();}
							response = SerialComm.getCommand();
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText(response);
							alertDialog.show();
							commandList.add(0,response);
						}
						
						else if(command.equals("reboot") == true) {
							enterCommand.clear();
							commandList.add(0, command);
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText("CubeSat is now rebooting.");
							alertDialog.showAndWait();
							
							//Send command to SerialComm
							SerialComm.Send(command);
							try {Thread.sleep(2000);} catch (InterruptedException e1) {e1.printStackTrace();}
							response = SerialComm.getCommand();
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText(response);
							alertDialog.show();
							commandList.add(0,response);
						}
						
						else if(command.equals("changeBatteryCells") == true) {
							enterCommand.clear();
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("changeSolarCells") == true) {
							enterCommand.clear();
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("changeNavigation") == true) {
							enterCommand.clear();
							inputTextBox.setHeaderText("Enter the new navigation values, seperated by commas then click ok.");
							naviValues = inputTextBox.showAndWait().get();
							
							System.out.println(naviValues);
							if(naviValues.isEmpty()) {
								alertDialog.setAlertType(AlertType.WARNING);
								alertDialog.setHeaderText("User input could not be accepted due to improper formatting or invalid characters!");;
								alertDialog.show();
							}
							else {
								alertDialog.setAlertType(AlertType.CONFIRMATION);
								alertDialog.setHeaderText("User input accepted, sending to CubeSat");
								alertDialog.show();
							}
							command = command+" "+naviValues;
							commandList.add(0,command);
							
							
							//Send command to SerialComm
							//SerialComm.Send(command);
						}
						
						else if(command.equals("changeOrientation") == true) {
							enterCommand.clear();
							commandList.add(0,command);
							
							//Send command to SerialComm
							//SerialComm.Send(command);
						}
						
						else if(command.equals("changeMagnetometers") == true) {
							enterCommand.clear();
							commandList.add(0,command);
							
							//Send command to SerialComm
							//SerialComm.Send(command);
						}
						
						else if(command.equals("help")== true) {
							enterCommand.clear();
							alertDialog.setAlertType(AlertType.CONFIRMATION);
							alertDialog.setHeaderText("changeBatteryCells:		Toggle battery cells On/Off\n"
									+ "changeMagnetometers: 	Toggle control magnetometers On/Off\n"
									+ "changeNavigation:		Adjust current navigation settings (x,y,z)\n"
									+ "changeOrientation:		Adjust current orientation settings (x,y,z)\n"
									+ "changeSolarCells:		Toggle solar cells On/Off\n"
									+ "changeSoftware:			Update software (file)\n"
									+ "checkBatteryCells:		Gives current battery cell status\n"
									+ "checkSolarCells:			Gives current solar cell status\n"
									+ "checkSoftware:			Gives current version of software\n"
									+ "resetNavigation:			Resets navigation parameters to initial values\n"
									+ "reboot:					Reboots the CubeSat");
							alertDialog.show();
							commandList.add(0,command);
						}
						
						else {
							enterCommand.clear();
							throw new CommandError();
						}
						
						ArrayList<String> commandListDisplay = new ArrayList<>(commandList);
						Collections.reverse(commandListDisplay);
						commandListString = "";
						for(int i =0; i<commandListDisplay.size();i++) {
							commandListString = commandListString + commandListDisplay.get(i)+" \n";
						}
						
						commandLog.setText(commandListString);
						
						System.out.println(commandList);	//Checking Array Contents are intact
						System.out.println(commandListDisplay);
						System.out.println(commandListString);
					}
					else if(enterCommand.getText().trim().isEmpty() == true){
						throw new CommandError();
						
					}


				} catch (CommandError e1) {
					alertDialog.setAlertType(AlertType.WARNING);
					alertDialog.setHeaderText("User input could not be accepted due to improper formatting or invalid characters!");;
					alertDialog.show();
				}
			}});

		//
		commandLog.setText(commandListString);

		cl_pane.getChildren().addAll(enterCommand,commandLog,help, backToMain);
		cl_pane.setStyle("-fx-background-color: WHITE");

		//Set GUI to constant resolution (changeable in the future)
		Scene cl_scene = new Scene(cl_pane,850,700,Color.GREY);
		primaryStage.setScene(cl_scene);
		primaryStage.setMaxWidth(850);
		primaryStage.setMinWidth(850);
		primaryStage.setMaxHeight(700);
		primaryStage.setMinHeight(700);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Astraeus: Command Line");

		//Show the GUI
		primaryStage.show();

	}
	
	//Make sure execution begins	(this will be placed elsewhere but here for testing purposes)
	public static void main(String[] args)	{	launch(args);	}

	}

	
class CommandError extends Exception{
	public CommandError() {}
}		
