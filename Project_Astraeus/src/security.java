

import java.util.ArrayList;

public class security {

	private String password;
	private int pin;
	private String username;
	private ArrayList<String> account = new ArrayList<String>(3);
	
	public void changePassword(String newPassword) {
		
	}
	
	public ArrayList createNewAccount(String username,String password) {
		account.add(username);
		account.add(password);
		account.add(String.valueOf(pin));
		return account;
	}
	
	public void logIn() {
		
	}
	
	public void logOut()	{
		
	}
	
	public void promoteAccount() {
		
	}
	
}
