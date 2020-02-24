package default_package;
import java.io.*;
import java.util.*;


public class UserAccount {
	private int userId;
	static String userName;
	private static String password;
	private static String typeOfAccount;
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	static Scanner userInput3 = new Scanner(System.in);
	int userID = 0;
	
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
		}
		}
		// This method asks and returns what the user wants to do
		
	public static int getUserChoice(){
		System.out.println("What do you wan to do?");
		System.out.println("(1) Register your account");
		System.out.println("(2) Login into account");
		System.out.println("****************************************************************");
		System.out.print("Please enter your choice (1 or 2): ");
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
		
		
		try { //This is for Registrating the users
			
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database.txt",true)));
			wr.println(userName + "\t" + password + "\t" + typeOfAccount + "\t" + userID);
			
			wr.close();	
			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		userID++;	
		Login();	
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




