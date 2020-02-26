package default_package;
import java.io.*;
import java.util.*;


public class UserAccount {
	static String userName;
	private static String password;
	private static String typeOfAccount;
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	static Scanner userInput3 = new Scanner(System.in);
	private static int userID;
	static UserAccount[] UserAcc = new UserAccount[100];
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int userChoice = getUserChoice();
		switch (userChoice) {
		case 1:
		Register();
		break;
		case 2:
		Login();
		break;
		case 3:
		AdminLogin();
		break;
		}
		}
		// This method asks and returns what the user wants to do
		
	public static int getUserChoice(){
		System.out.println("Welcome to your PayUp homepage!");
		System.out.println("What do you wish to do?");
		System.out.println("(1) Register your account");
		System.out.println("(2) Login as a regular account");
		System.out.println("(3) Login as an administrator ");
		System.out.println("****************************************************************");
		System.out.print("Please enter your choice (1, 2 or 3): ");	
		return userInput1.nextInt();
	}
	
	public static void Register() {
		
		
		System.out.println("Register to PayUp");
		System.out.println("Do you want to register as a (R) Regular User (A) Admin?");
		typeOfAccount = userInput3.nextLine();
		System.out.println("Please choose a username:");
		userName = userInput2.nextLine();
		System.out.println("Please choose a password:");
		password = userInput2.nextLine();
		
		//UserAccount newUser = new User(userName, password);
	
		System.out.println("You are now registered as a member at PayUp!");
		System.out.println("");
			
		try { //This is for Registration of the users
			
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database.txt",true)));
			wr.println(userName + "\t" + password + "\t" + typeOfAccount + "\t" + userID);
			userID++;
			wr.close();	
			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		UserAcc("Sjoerd", "Hoi");
		Login();	
	}
	
	public UserAccount[] readfile() {
		
		int lineNumber = 0;
		String[] xaxis = new String[100];
		String[] yaxis = new String[100];
		try {
			String sCurrentLine;
			String[] uCurrent = new String [2];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				xaxis[lineNumber] = uCurrent[0];
				yaxis[lineNumber] = uCurrent[1];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
				System.out.println("This file does not exist");
		}
		
		String[] UserAccount = new String[lineNumber];
		System.arraycopy(UserAccount, 0, Finalxaxis, 0, lineNumber);
		
		return UserAccount[];	
	}
	public static boolean Login() {
		int lineNumber = 0;
		String[] xaxis = new String[100];
		String[] yaxis = new String[100];
		boolean j = false;
		
		System.out.println("Login into PayUp");
		System.out.println("Please input your username:");
		userName = userInput2.nextLine();
		System.out.println("Please input your password:");
		password = userInput2.nextLine();
		
		try {
			String sCurrentLine;
			String[] uCurrent = new String [2];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				xaxis[lineNumber] = uCurrent[0];
				yaxis[lineNumber] = uCurrent[1];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
				System.out.println("This file does not exist");
		}
		
		String[] Finalxaxis = new String[lineNumber];
		System.arraycopy(xaxis, 0, Finalxaxis, 0, lineNumber);
		String[] Finalyaxis = new String[lineNumber];
		System.arraycopy(yaxis, 0, Finalyaxis, 0, lineNumber);
		
		for (int i = 0; i < lineNumber; i++) {
			if (userName.equals(Finalxaxis[i]) && password.equals(Finalyaxis[i])) {
				j = true;
				break;
			} 
		}
		return j;
	}
}




