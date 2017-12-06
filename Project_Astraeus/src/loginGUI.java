/**
 * This class is the main gui for logging in. There is a login for users and one for admins. Once someone logs in, their username is
 * displayed in the top right corner, and if anyone else tries to log on there are told that someone is already logged in. If the program
 * user doesn't already have an account, they can click on the create account button to make one, which will switch them to that
 * GUI.  
 * 
 * @author chloe
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginGUI extends Application {

	UserAccount ua = new UserAccount(UserAccount.username, UserAccount.password);
	static boolean loginUser;
	static int i=0;
	static Stage loginstage;
	public static void main (String [] args) {	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Pane mainPane = new Pane();
		GridPane root = new GridPane();
		root.setMinSize(300, 300);
		primaryStage.setTitle("Astraeus");
		root.setVgap(5);
		root.setHgap(5);
		root.setLayoutX(100);

		ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("Resources/logo.png")));
		logo.setFitHeight(175);
		logo.setFitWidth(175);
		logo.setLayoutX(20);
		logo.setLayoutY(515);
		
		ImageView banner = new ImageView(new Image(getClass().getResourceAsStream("Resources/background.png")));
		banner.setFitHeight(275);
		banner.setFitWidth(1280);
		banner.setLayoutY(440);
		
		Label title = new Label("                 Astraeus CubeSat Ground Station");
		title.setLayoutX(30);
		title.setStyle("-fx-text-fill: #c0c0c0; -fx-font: 50 century-gothic; -fx-font-weight: bold ");
		
		Label blank = new Label("                           ");
		blank.setLayoutX(30);
		blank.setStyle("-fx-font: 30 century-gothic; -fx-font-weight: bold ");
		root.add(blank, 1, 1);
		
		Text blank2 = new Text("      ");
		blank2.setStyle("-fx-font: 18 century-gothic; -fx-font-weight: bold ");
		root.add(blank2, 3, 40);
		
		//User Accounts
		Text User = new Text("          User Login");
		User.setFill(Color.WHITE);
		User.setStyle("-fx-font: 18 century-gothic; -fx-font-weight: bold ");
		root.add(User, 3, 22);

		Text username = new Text("Username:");
		username.setFill(Color.WHITE);
		username.setStyle("-fx-font: 12 century-gothic; -fx-font-weight: bold ");
		root.add(username, 2, 23);
		TextField usernameLogin = new TextField();
		usernameLogin.setPrefColumnCount(17);
		root.add(usernameLogin, 3, 23);

		Text password = new Text("Password:");
		password.setFill(Color.WHITE);
		password.setStyle(" -fx-font: 12 century-gothic; -fx-font-weight: bold ");
		root.add(password, 2, 26);
		final PasswordField passwordLogin = new PasswordField();
		final TextField passwordLoginTF = new TextField();
		passwordLoginTF.setManaged(false);
		passwordLoginTF.setVisible(false);
		CheckBox userPasswordBox = new CheckBox("Show/Hide Password");
		userPasswordBox.setStyle("-fx-text-fill: white;-fx-font: 12 century-gothic; -fx-font-weight: bold ");

		//making it so the password is concealed unless the box to show it is checked
		passwordLoginTF.managedProperty().bind(userPasswordBox.selectedProperty());
		passwordLoginTF.visibleProperty().bind(userPasswordBox.selectedProperty());
		passwordLogin.managedProperty().bind(userPasswordBox.selectedProperty().not());
		passwordLogin.visibleProperty().bind(userPasswordBox.selectedProperty().not());
		passwordLoginTF.textProperty().bindBidirectional(passwordLogin.textProperty());
		passwordLogin.setPrefColumnCount(17);
		root.add(passwordLogin, 3, 26);
		root.add(passwordLoginTF, 3, 26);
		root.add(userPasswordBox, 3, 28);

		Button loginU = new Button();
		loginU.setMaxWidth(500);
		root.add(loginU, 3, 29);
		loginU.setText("Sign On");
		
		//Admin Accounts
		Text Admin = new Text("          Admin Login");
		Admin.setFill(Color.WHITE);
		Admin.setStyle(" -fx-font: 18 century-gothic; -fx-font-weight: bold ");
		root.add(Admin, 9, 22);

		Text usernameAdmin = new Text("Username:");
		usernameAdmin.setFill(Color.WHITE);
		usernameAdmin.setStyle("-fx-font: 12 century-gothic; -fx-font-weight: bold ");
		root.add(usernameAdmin, 8, 23);
		TextField usernameLoginAdmin = new TextField();
		usernameLoginAdmin.setPrefColumnCount(17);
		root.add(usernameLoginAdmin, 9, 23);

		Text passwordAdmin = new Text("Password:");
		passwordAdmin.setFill(Color.WHITE);
		passwordAdmin.setStyle("-fx-font: 12 century-gothic; -fx-font-weight: bold ");
		root.add(passwordAdmin, 8, 26);
		final PasswordField passwordLoginAdmin = new PasswordField();
		final TextField passwordLoginAdminTF = new TextField();
		passwordLoginAdminTF.setManaged(false);
		passwordLoginAdminTF.setVisible(false);
		CheckBox adminPasswordBox = new CheckBox("Show/Hide Password");
		adminPasswordBox.setStyle("-fx-text-fill: white;-fx-font: 12 century-gothic; -fx-font-weight: bold ");

		//making it so the password is concealed unless the box to show it is checked
		passwordLoginAdminTF.managedProperty().bind(adminPasswordBox.selectedProperty());
		passwordLoginAdminTF.visibleProperty().bind(adminPasswordBox.selectedProperty());
		passwordLoginAdmin.managedProperty().bind(adminPasswordBox.selectedProperty().not());
		passwordLoginAdmin.visibleProperty().bind(adminPasswordBox.selectedProperty().not());
		passwordLoginAdminTF.textProperty().bindBidirectional(passwordLoginAdmin.textProperty());
		passwordLoginAdmin.setPrefColumnCount(17);	
		root.add(passwordLoginAdmin, 9, 26);
		root.add(passwordLoginAdminTF, 9, 26);
		root.add(adminPasswordBox, 9, 28);

		Button loginA = new Button();
		loginA.setMaxWidth(500);
		root.add(loginA, 9, 29);
		loginA.setText("Sign On");

		Text noAccount = new Text("Don't Have an Account?");
		noAccount.setFill(Color.WHITE);
		noAccount.setStyle("-fx-font: 14 century-gothic; -fx-font-weight: bold ");
		root.add(noAccount, 3, 33);
		Button create = new Button();
		create.setMaxWidth(500);
		root.add(create, 3, 34);
		create.setText("Create Account");

		mainPane.setStyle("-fx-background-color: black");
		mainPane.getChildren().add(root);
		mainPane.getChildren().add(title);
		mainPane.getChildren().add(banner);
		mainPane.getChildren().add(logo);


		root.setStyle("-fx-background-color: black");
		
		/////////////////////////////////////////
		Scene scene = new Scene(mainPane, 1280, 720);

		primaryStage.setScene(scene);
		loginstage = primaryStage;
		primaryStage.show();	
		
		Text nothingL = new Text("Missing username and/or password");
		nothingL.setFill(Color.WHITE);
		nothingL.setStyle("-fx-font: 14 century-gothic; -fx-font-weight: bold ");
		nothingL.setLayoutX(540);
		nothingL.setLayoutY(410);
		
		Text loginfalse = new Text("Incorrect username or password");
		loginfalse.setFill(Color.WHITE);
		loginfalse.setStyle("-fx-font: 14 century-gothic; -fx-font-weight: bold ");
		loginfalse.setLayoutX(550);
		loginfalse.setLayoutY(410);
		
		loginU.setOnAction(new EventHandler<ActionEvent>() {			
			@Override public void handle(ActionEvent e) {	
				mainPane.getChildren().remove(nothingL);
				mainPane.getChildren().remove(loginfalse);
				
				if(usernameLogin.getText().isEmpty() | passwordLogin.getText().isEmpty()) {					
					mainPane.getChildren().add(nothingL);
				}
				else{
					if(UserAccount.loggedin==true | UserAccount.loggedinAdmin==true) {	
						Text logintrue = new Text("Someone's already logged in");	
						logintrue.setFont(new Font(14));
						if(i==0) {
							root.add(logintrue, 3, 35);	
						}
						i++;
					}

					loginUser=true;
					UserAccount.username = usernameLogin.getText();
					UserAccount.password = passwordLogin.getText();
					ua.login();

					if(UserAccount.login==true || UserAccount.loginAdmin==true) {

						if(UserAccount.created==true) {
							Tutorial tut = new Tutorial();
							try {
								tut.start(primaryStage);
								UserAccount.created=false;
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else{
							MainGUI maingui = new MainGUI();
							try {
								maingui.start(primaryStage);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}					
					}
					else {				
						mainPane.getChildren().add(loginfalse);
					}
				}
			}
		});

		loginA.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				loginUser=false;
				
				mainPane.getChildren().remove(nothingL);
				mainPane.getChildren().remove(loginfalse);
				
				if(usernameLoginAdmin.getText().isEmpty() | passwordLoginAdmin.getText().isEmpty()) {			
					mainPane.getChildren().add(nothingL);
				}

				else {
					if(UserAccount.loggedin==true | UserAccount.loggedinAdmin==true) {
						Text logintrue = new Text("Someone's already logged in");
						logintrue.setFont(new Font(14));
						if(i==0) {
							root.add(logintrue, 3, 35);	
						}	
						i++;
					}
					UserAccount.username = usernameLoginAdmin.getText();
					UserAccount.password = passwordLoginAdmin.getText();
					ua.login();
					if(UserAccount.loginAdmin==true | UserAccount.login==true) {
						Text logintrue = new Text("Logged in as "+usernameLogin.getText());
						root.add(logintrue, 15, 22);
						if(UserAccount.created==true) {
							Tutorial tut = new Tutorial();
							try {
								tut.start(primaryStage);
								UserAccount.created=false;
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else{
							MainGUI maingui = new MainGUI();
							try {
								maingui.start(primaryStage);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}			
					}
					else {
						mainPane.getChildren().add(loginfalse);
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
