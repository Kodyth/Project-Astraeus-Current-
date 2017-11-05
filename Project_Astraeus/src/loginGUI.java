//test
//test2
/**
 * This class is the main gui for logging in. There is a login for users and one for admins. Once someone logs in, their username is
 * displayed in the top right corner, and if anyone else tries to log on there are told that someone is already logged in. If the program
 * user doesn't already have an account, they can click on the create account button to make one, which will switch them to that
 * GUI.
 * 
 * @author chloe
 *
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginGUI extends Application {


	//HERE IS MY CHANGE 
	
	UserAccount ua = new UserAccount(UserAccount.username, UserAccount.password);
	static boolean loginUser;
	static int i=0;
	static Stage loginstage;
	public static void main (String [] args) {	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
		root.setMinSize(300, 300);
		root.setVgap(5);
		root.setHgap(5);
		
		//User Accounts
		Text User = new Text("User Login");
		User.setFill(Color.BLACK);
		User.setFont(new Font(15));
		root.add(User, 2, 1);
		System.out.println("github test");

		Text username = new Text("Username:");
		username.setFill(Color.BLACK);
		root.add(username, 1, 2);
		TextField usernameLogin = new TextField();
		usernameLogin.setPrefColumnCount(17);
		root.add(usernameLogin, 2, 2);

		Text password = new Text("Password:");
		password.setFill(Color.BLACK);
		root.add(password, 1, 5);
		final PasswordField passwordLogin = new PasswordField();
		final TextField passwordLoginTF = new TextField();
		passwordLoginTF.setManaged(false);
		passwordLoginTF.setVisible(false);
		CheckBox userPasswordBox = new CheckBox("Show/Hide password");
		
		//making it so the password is concealed unless the box to show it is checked
		passwordLoginTF.managedProperty().bind(userPasswordBox.selectedProperty());
		passwordLoginTF.visibleProperty().bind(userPasswordBox.selectedProperty());
		passwordLogin.managedProperty().bind(userPasswordBox.selectedProperty().not());
		passwordLogin.visibleProperty().bind(userPasswordBox.selectedProperty().not());
		passwordLoginTF.textProperty().bindBidirectional(passwordLogin.textProperty());
		passwordLogin.setPrefColumnCount(17);
		root.add(passwordLogin, 2, 5);
		root.add(passwordLoginTF, 2, 5);
		root.add(userPasswordBox, 2, 7);
		
		Button loginU = new Button();
		loginU.setMaxWidth(500);
		root.add(loginU, 2, 8);
		loginU.setText("Sign On");

		//Admin Accounts
		Text Admin = new Text("Admin Login");
		Admin.setFill(Color.BLACK);
		Admin.setFont(new Font(15));
		root.add(Admin, 8, 1);

		Text usernameAdmin = new Text("Username:");
		usernameAdmin.setFill(Color.BLACK);
		root.add(usernameAdmin, 7, 2);
		TextField usernameLoginAdmin = new TextField();
		usernameLoginAdmin.setPrefColumnCount(17);
		root.add(usernameLoginAdmin, 8, 2);

		Text passwordAdmin = new Text("Password:");
		passwordAdmin.setFill(Color.BLACK);
		root.add(passwordAdmin, 7, 5);
		final PasswordField passwordLoginAdmin = new PasswordField();
		final TextField passwordLoginAdminTF = new TextField();
		passwordLoginAdminTF.setManaged(false);
		passwordLoginAdminTF.setVisible(false);
		CheckBox adminPasswordBox = new CheckBox("Show/Hide password");
		
		//making it so the password is concealed unless the box to show it is checked
		passwordLoginAdminTF.managedProperty().bind(adminPasswordBox.selectedProperty());
		passwordLoginAdminTF.visibleProperty().bind(adminPasswordBox.selectedProperty());
		passwordLoginAdmin.managedProperty().bind(adminPasswordBox.selectedProperty().not());
		passwordLoginAdmin.visibleProperty().bind(adminPasswordBox.selectedProperty().not());
		passwordLoginAdminTF.textProperty().bindBidirectional(passwordLoginAdmin.textProperty());
		passwordLoginAdmin.setPrefColumnCount(17);	
		root.add(passwordLoginAdmin, 8, 5);
		root.add(passwordLoginAdminTF, 8, 5);
		root.add(adminPasswordBox, 8, 7);
		
		Button loginA = new Button();
		loginA.setMaxWidth(500);
		root.add(loginA, 8, 8);
		loginA.setText("Sign On");

		Text noAccount = new Text("Don't Have an Account?");
		noAccount.setFill(Color.BLACK);
		root.add(noAccount, 2, 12);
		Button create = new Button();
		create.setMaxWidth(500);
		root.add(create, 2, 13);
		create.setText("Create Account");
		
		Button logout = new Button();
		root.add(logout, 13, 1);
		logout.setText("Logout");
		
		root.setStyle("-fx-background-color: WHITE");
		Scene scene = new Scene(root, 850, 700);
		primaryStage.setScene(scene);
		loginstage = primaryStage;
		primaryStage.show();

		logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				loginGUI gui = new loginGUI();
				gui.start(primaryStage);
				ua.logout();
			}
		});
			
		
		loginU.setOnAction(new EventHandler<ActionEvent>() {			
			@Override public void handle(ActionEvent e) {	
				if(usernameLogin.getText().isEmpty() | passwordLogin.getText().isEmpty()) {
					Text nothingL = new Text("Missing username and/or password");
					nothingL.setFill(Color.BLACK);
					root.getChildren().remove(nothingL);
					root.add(nothingL, 2, 16);
				}
				else{
					if(UserAccount.loggedin==true | UserAccount.loggedinAdmin==true) {	
					Text logintrue = new Text("Someone's already logged in");	
					logintrue.setFill(Color.BLACK);
					logintrue.setFont(new Font(14));
					if(i==0) {
						root.add(logintrue, 2, 14);	
					}
					i++;
				}
					
					loginUser=true;
					UserAccount.username = usernameLogin.getText();
					UserAccount.password = passwordLogin.getText();
					ua.login();
				
					if(UserAccount.login==true | UserAccount.loginAdmin==true) {
					Text logintrue = new Text("Logged in as "+usernameLogin.getText());
					logintrue.setFill(Color.BLACK);
					root.add(logintrue, 14, 1);		
					
					MainGUI maingui = new MainGUI();
	            	try {
	            		maingui.start(primaryStage);
	            	} catch (Exception e1) {
	            	e1.printStackTrace();
	            	}
					
					}
					else {
						Text loginfalse = new Text("Incorrect username or password");
						loginfalse.setFill(Color.BLACK);
						root.getChildren().remove(loginfalse);
						root.add(loginfalse, 2, 17);
					}
				}
			}
		});

		loginA.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				loginUser=false;
				if(usernameLoginAdmin.getText().isEmpty() | passwordLoginAdmin.getText().isEmpty()) {
					Text nothingL = new Text("Missing username and/or password");
					nothingL.setFill(Color.BLACK);
					root.getChildren().remove(nothingL);
					root.add(nothingL, 8, 16);
				}
				
				else {
					if(UserAccount.loggedin==true | UserAccount.loggedinAdmin==true) {
						Text logintrue = new Text("Someone's already logged in");
						logintrue.setFill(Color.BLACK);
						logintrue.setFont(new Font(14));
						if(i==0) {
							root.add(logintrue, 2, 14);	
						}	
						i++;
					}
					UserAccount.username = usernameLoginAdmin.getText();
					UserAccount.password = passwordLoginAdmin.getText();
					ua.login();
					if(UserAccount.loginAdmin==true | UserAccount.login==true) {
						Text logintrue = new Text("Logged in as "+usernameLogin.getText());
						logintrue.setFill(Color.BLACK);
						root.add(logintrue, 14, 1);
						
						MainGUI maingui = new MainGUI();
		            	try {
		            		maingui.start(primaryStage);
		            	} catch (Exception e1) {
		            	e1.printStackTrace();
		            	}
					}
					else {
						Text loginfalse = new Text("Incorrect username or password");
						loginfalse.setFill(Color.BLACK);
						root.getChildren().remove(loginfalse);
						root.add(loginfalse, 2, 16);
					}
					
				}
			}
		});

		//switches to the account creation GUI if create account button clicked
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				createAccountGUI GUI = new createAccountGUI();
				primaryStage.getScene().setRoot(GUI.getGridPane());
			}
		});


	}

}
