package default_package;
import java.util.Scanner;

public class UserAccount {
	private String username;
	private String password;
	int userId;

public UserAccount(String username, String password) {
	try (Scanner input = new Scanner (System.in)) {
			this.setUsername(username);
			this.setPassword(password);
			
			System.out.println("Welcome to your PayUp site!");
			System.out.println("\nEnter your username and password to login to your account.");    

			System.out.println("Username: ");
			username = input.nextLine();

			System.out.println("Password: ");
			password = input.nextLine();
		}  

		UserAccount login = new UserAccount(username, password);

		if(login.checkPassword())
			System.out.println("You are logged in!");
		else
			System.out.println("The username and password you entered are incorrect.");
	}

	private boolean checkPassword() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

