/**
 * This class contains the logic methods for user/admin account creation and retrieval.
 * 
 * @author chloe
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserAccount{
	static String password;
	static String username;
	static boolean created=false;
	static boolean login=false;
	static boolean loggedin=false;
	static boolean loginAdmin=false;
	static boolean loggedinAdmin=false;
	static boolean usernameTaken=false;

	public void login() {
		try {
			FileReader fr = null;
			String userpass = username+"_"+password+";";
			if(loginGUI.loginUser==true) {
				fr = new FileReader("UserAccountInfo.txt");
			}
			if(loginGUI.loginUser==false) {
				fr = new FileReader("AdminAccountInfo.txt");
			}
			BufferedReader br=new BufferedReader(fr);
			String line = br.readLine();

			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line);
				String l = sb.toString();
				if(l.contains(userpass)) {

					if(loginGUI.loginUser==true) {
						login=true;
						loggedin = true;
					}
					if(loginGUI.loginUser==false) {
						loginAdmin=true;
						loggedinAdmin = true;
					}
					break;
				}
				line = br.readLine();	
				sb.append(System.lineSeparator());
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void logout() {
		login=false;
		loggedin=false;
		loginAdmin=false;
		loggedinAdmin=false;
	}

	public void changePassword() {

	}

	
	public boolean checkAdmin() {
		return loggedinAdmin;
	}
	
	public UserAccount(String username, String password) {	
	}

	public static boolean checkUsername() throws IOException {
		String user = username+"_";
		try {
			FileReader fr = null;
			if(createAccountGUI.selected==false) {
				fr = new FileReader("UserAccountInfo.txt");
			}
			if(createAccountGUI.selected==true) {
				fr = new FileReader("AdminAccountInfo.txt");
			}
			BufferedReader br=new BufferedReader(fr);
			String line = br.readLine();

			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line);
			
				String l = sb.toString();
				if(l.contains(user)) {
					usernameTaken=true;
					
					break;
				}
				line = br.readLine();	
				sb.append(System.lineSeparator());
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();		
		}
		return usernameTaken;
	}
	

public UserAccount() {
		
	}
	
public static void createNewAccount(){
		BufferedWriter write = null;
		if(createAccountGUI.selected==false) {
			try{FileWriter fw = new FileWriter("UserAccountInfo.txt", true);
			write = new BufferedWriter(fw);
			write.write(username +"_"+ password+";\n");
			write.close();
			created=true;

			}
			catch (IOException e){
				
				created=false;
			}
			{
				try {
					if (write != null)
						write.close();
				}
				catch (IOException e){
				}
			}
		}else {
			try{FileWriter fw = new FileWriter("AdminAccountInfo.txt", true);
			write = new BufferedWriter(fw);
			write.write(username+"_"+ password+";\n");
			write.close();
			created=true;

			}
			catch (IOException e){
				
				created=false;
			}
			{
				try {
					if (write != null)
						write.close();
				}
				catch (IOException e){
				}
			}
		}
	}
}
