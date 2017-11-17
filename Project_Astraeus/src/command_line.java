import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	public static List<String> commandList = new ArrayList<String>();
	private String commandListString;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		cl_pane = new Pane();

		//Pad List with blank entries (prevent out of bounds exceptions)
		commandList.add("");	//0
		commandList.add("");	//1
		commandList.add("");	//2
		commandList.add("");	//3
		commandList.add("");	//4
		commandList.add("");	//5
		commandList.add("");	//6
		commandList.add("");	//7
		commandList.add("");	//8
		commandList.add("");	//9
		commandList.add("");	//10
		commandList.add("");	//11
		commandList.add("");	//12
		commandList.add("");	//13
		commandList.add("");	//14
		commandList.add("");	//15
		commandList.add("");	//16
		commandList.add("");	//17
		commandList.add("");	//18
		commandList.add("");	//19

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
        help.setMinHeight(50);
        help.setMinWidth(100);
        help.setOnAction(e -> {
//        	Tutorial t = new Tutorial();
        	try {
//        	t.start(primaryStage);
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
						else if(command.equals("checkSoftware") == true){
							enterCommand.clear();
							//commandList.add(0,"Current Software Status: Good");
							commandList.add(1,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("changeSoftware") == true){
							enterCommand.clear();
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("checkBatteryCells") == true){
							enterCommand.clear();
							commandList.add(0, "Current Battery Status: Good");
							commandList.add(1,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("checkSolarCells") == true) {
							enterCommand.clear();
							commandList.add(0, "Current Solar Cells Status: Good");
							commandList.add(1,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("resetNavigation") == true) {
							enterCommand.clear();
							commandList.add(0, "Navigation for the CubeSat has been reset");
							commandList.add(1,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("reboot") == true) {
							enterCommand.clear();
							commandList.add(0, "CubeSat Rebooting");
							commandList.add(1,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
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
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("changeOrientation") == true) {
							enterCommand.clear();
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("changeMagnetometers") == true) {
							enterCommand.clear();
							commandList.add(0,command);
							
							//Send command to SerialComm
							SerialComm.Send(command);
						}
						
						else if(command.equals("help")== true) {
							enterCommand.clear();
							commandList.add(0,"changeBatteryCells:		Toggle battery cells On/Off\n"
									+ "changeMagnetometers:		Toggle control magnetometers On/Off\n"
									+ "changeNavigation:		Adjust current navigation settings (x,y,z)\n"
									+ "changeOrientation:		Adjust current orientation settings (x,y,z)\n"
									+ "changeSolarCells:		Toggle solar cells On/Off\n"
									+ "changeSoftware:		Update software (file)\n"
									+ "checkBatteryCells:		Gives current battery cell status\n"
									+ "checkSolarCells:		Gives current solar cell status\n"
									+ "checkSoftware:		Gives current version of software\n"
									+ "resetNavigation:		Resets navigation parameters to initial values\n"
									+ "reboot:		Reboots the CubeSat");
							commandList.add(1, command);
						}
						
						else {
							enterCommand.clear();
							throw new CommandError();
						}
						
						commandListString = commandList.get(19)+"\n"+commandList.get(18)+"\n"+commandList.get(17)+
								"\n"+commandList.get(16)+"\n"+commandList.get(15)+"\n"+commandList.get(14)+
								"\n"+commandList.get(13)+"\n"+commandList.get(12)+"\n"+commandList.get(11)+
								"\n"+commandList.get(10)+"\n"+commandList.get(9)+"\n"+commandList.get(8)+"\n"+commandList.get(7)+
								"\n"+commandList.get(6)+"\n"+commandList.get(5)+"\n"+commandList.get(4)+
								"\n"+commandList.get(3)+"\n"+commandList.get(2)+"\n"+commandList.get(1)+
								"\n"+commandList.get(0);
						
						commandLog.setText(commandListString);
						
						System.out.println(commandList);	//Checking Array Contents are intact
					
					}
					else if(enterCommand.getText().trim().isEmpty() == true){
						throw new CommandError();
						
					}


				} catch (CommandError e1) {
					command = "Unable to accept command check it is a valid command then try again";
					commandList.add(0,command);
					commandLog.setText(commandList.get(19)+"\n"+commandList.get(18)+"\n"+commandList.get(17)+
							"\n"+commandList.get(16)+"\n"+commandList.get(15)+"\n"+commandList.get(14)+
							"\n"+commandList.get(13)+"\n"+commandList.get(12)+"\n"+commandList.get(11)+
							"\n"+commandList.get(10)+"\n"+commandList.get(9)+"\n"+commandList.get(8)+"\n"+commandList.get(7)+
							"\n"+commandList.get(6)+"\n"+commandList.get(5)+"\n"+commandList.get(4)+
							"\n"+commandList.get(3)+"\n"+commandList.get(2)+"\n"+commandList.get(1)+
							"\n"+commandList.get(0));
				}
			}});

		//
		commandLog.setText(commandList.get(19)+"\n"+commandList.get(18)+"\n"+commandList.get(17)+
				"\n"+commandList.get(16)+"\n"+commandList.get(15)+"\n"+commandList.get(14)+
				"\n"+commandList.get(13)+"\n"+commandList.get(12)+"\n"+commandList.get(11)+
				"\n"+commandList.get(10)+"\n"+commandList.get(9)+"\n"+commandList.get(8)+"\n"+commandList.get(7)+
				"\n"+commandList.get(6)+"\n"+commandList.get(5)+"\n"+commandList.get(4)+
				"\n"+commandList.get(3)+"\n"+commandList.get(2)+"\n"+commandList.get(1)+
				"\n"+commandList.get(0));

		cl_pane.getChildren().add(enterCommand);
		cl_pane.getChildren().add(commandLog);
		cl_pane.getChildren().add(help);
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
