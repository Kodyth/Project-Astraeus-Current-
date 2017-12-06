/**
 * This class shows the GUI for account creation and adds any account information into the appropriate text file.
 * There are two different text files that accounts are written in to, there's a file for user accounts and one for admin accounts. 
 * If the admin account box is checked, then an admin account is made and written to the admin account text file, otherwise, it's 
 * written to the user account text file. Once an account is created, there is a back button that allows that user to go back to login
 * screen. 
 *
 * @author chloe
 *
 */
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class createAccountGUI{
	GridPane root = new GridPane();
	Pane pane = new Pane();
	static boolean selected;
	public createAccountGUI() {
		root.setMinSize(300, 300);
		root.setVgap(5);
		root.setHgap(5);
		
		ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("Resources/logo.png")));
		logo.setFitHeight(175);
		logo.setFitWidth(175);
		logo.setLayoutX(20);
		logo.setLayoutY(515);
		root.add(logo, 1, 44);
		
		Text blank = new Text("                   ");
		blank.setStyle("-fx-text-fill: BLACK; -fx-font: 80 century-gothic; -fx-font-weight: bold ");
		root.add(blank, 1, 1);
		
		Text createAccount = new Text("Create An Account");
		createAccount.setStyle("-fx-text-fill: BLACK; -fx-font: 25 century-gothic; -fx-font-weight: bold ");
		root.add(createAccount, 6, 2);
		
		Text username = new Text("Username:");
		username.setStyle("-fx-text-fill: BLACK; -fx-font: 13 century-gothic; -fx-font-weight: bold ");
		root.add(username, 4, 4);
		TextField usernameCreate = new TextField();
		usernameCreate.setPrefColumnCount(10);
		root.add(usernameCreate, 6, 4);
		Text blank2 = new Text("      ");
		blank2.setStyle("-fx-text-fill: BLACK; -fx-font: 10 century-gothic; -fx-font-weight: bold ");
		root.add(blank2, 6, 5);

		Text password = new Text("Password:");
		password.setStyle("-fx-text-fill: BLACK; -fx-font: 13 century-gothic; -fx-font-weight: bold ");
		root.add(password, 4, 7);
		final TextField passwordCreate = new TextField();
		passwordCreate.setPrefColumnCount(10);
		Text blank3 = new Text("      ");
		blank3.setStyle("-fx-text-fill: BLACK; -fx-font: 10 century-gothic; -fx-font-weight: bold ");
		root.add(blank3, 4, 8);

		Text verifyPassword = new Text("Verify Password:");
		verifyPassword.setStyle("-fx-text-fill: BLACK; -fx-font: 13 century-gothic; -fx-font-weight: bold ");
		root.add(verifyPassword, 4, 9);
		final TextField vpassword = new TextField();
		vpassword.setPrefColumnCount(10);
			
		final PasswordField passwords = new PasswordField();
		final PasswordField passwordsV = new PasswordField();
		passwordCreate.setManaged(false);
		passwordCreate.setVisible(false);
		vpassword.setManaged(false);
		vpassword.setVisible(false);
		
		CheckBox showPass = new CheckBox("Show/Hide Password");
		showPass.setStyle("-fx-text-fill: BLACK; -fx-font: 13 century-gothic; -fx-font-weight: bold ");
		passwordCreate.managedProperty().bind(showPass.selectedProperty());
		passwordCreate.visibleProperty().bind(showPass.selectedProperty());
		vpassword.managedProperty().bind(showPass.selectedProperty());
		vpassword.visibleProperty().bind(showPass.selectedProperty());
		
		passwords.managedProperty().bind(showPass.selectedProperty().not());
		passwords.visibleProperty().bind(showPass.selectedProperty().not());
		passwordsV.managedProperty().bind(showPass.selectedProperty().not());
		passwordsV.visibleProperty().bind(showPass.selectedProperty().not());
		passwordCreate.textProperty().bindBidirectional(passwords.textProperty());
		vpassword.textProperty().bindBidirectional(passwordsV.textProperty());
		vpassword.setPrefColumnCount(17);

		root.add(vpassword, 6, 9);
		root.add(passwords, 6, 7);
		root.add(passwordsV, 6, 9);
		root.add(showPass, 6, 11);
		root.add(passwordCreate, 2, 7);
		
		CheckBox adminBox = new CheckBox("Admin Account");
		adminBox.setStyle("-fx-text-fill: BLACK; -fx-font: 13 century-gothic; -fx-font-weight: bold ");
		root.add(adminBox, 6, 13);

		Button create = new Button();
		create.setMaxWidth(500);
		root.add(create, 6, 14);
		create.setText("Create Account");

		Button back = new Button();
		back.setMaxWidth(500);
		root.add(back, 7, 14);
		back.setText("Go Back");
		root.setStyle("-fx-background-color: dimgrey");
		
		Text nothingL = new Text("Please fill out all fields");
		nothingL.setStyle("-fx-text-fill: BLACK; -fx-font: 14 century-gothic; -fx-font-weight: bold ");
		Text nothingP = new Text("Passwords do not match");
		nothingP.setStyle("-fx-text-fill: BLACK; -fx-font: 14 century-gothic; -fx-font-weight: bold ");
		Text created = new Text("Account Created");
		created.setStyle("-fx-text-fill: BLACK; -fx-font: 14 century-gothic; -fx-font-weight: bold ");
		Text notCreated = new Text("Username Already Exists");
		notCreated.setStyle("-fx-text-fill: BLACK; -fx-font: 14 century-gothic; -fx-font-weight: bold ");
		
//		pane.setStyle("-fx-background-color: black");
//		pane.getChildren().add(root);

		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				root.getChildren().remove(nothingL);
				root.getChildren().remove(nothingP);
				root.getChildren().remove(notCreated);
				root.getChildren().remove(created);
				UserAccount.usernameTaken=false;
				
				selected = adminBox.isSelected();
				if(usernameCreate.getText().isEmpty() | passwordCreate.getText().isEmpty() | vpassword.getText().isEmpty()) {				
					root.add(nothingL, 6, 16);
				}
				if(!passwordCreate.getText().isEmpty() && !vpassword.getText().isEmpty()) {
					String pass = passwordCreate.getText();
					String passV = vpassword.getText();
					if(!pass.equals(passV)) {			
						root.add(nothingP, 6, 16);
					}

					else {
							UserAccount.username = usernameCreate.getText();
							UserAccount.password = passwordCreate.getText();
							try {
								UserAccount.checkUsername();
								if(UserAccount.usernameTaken==false) {
									UserAccount.createNewAccount();
								}
								else {
									root.add(notCreated, 6, 16);
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
												
							if(UserAccount.created==true) {
								root.add(created, 6, 16);
							}
					}
				}
			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				loginGUI GUI = new loginGUI();
				GUI.start(loginGUI.loginstage);
			}
		});	
	}

	public GridPane getGridPane() {
		return root;
	}
}
