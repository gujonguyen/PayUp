package default_package;
import java.io.*;
import java.util.*;


	public class UserAccount {
		String userName;
		String password;
		String typeOfAccount;
		static int noOfUsers;
		static Scanner userInput1 = new Scanner(System.in);
		static Scanner userInput2 = new Scanner(System.in);
		static Scanner userInput3 = new Scanner(System.in);
		int userID = 0;

	public UserAccount(String name, String pass, String role) {
		userName = name;
		password = pass;
		typeOfAccount = role;
		
	}

	public static UserAccount[] readFile() {
		int lineNumber = 0;
		String[] aaxis = new String[100];
		String[] baxis = new String[100];
		String[] caxis = new String[100];
		int[] daxis = new int[100];
		
		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				aaxis[lineNumber] = uCurrent[0];
				baxis[lineNumber] = uCurrent[1];
				caxis[lineNumber] = uCurrent[2];
				daxis[lineNumber] = Integer.parseInt(uCurrent[3]);
				
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		String[] Finalaaxis = new String[lineNumber];
		System.arraycopy(aaxis, 0, Finalaaxis, 0, lineNumber);
		String[] Finalbaxis = new String[lineNumber];
		System.arraycopy(baxis, 0, Finalbaxis, 0, lineNumber);
		String[] Finalcaxis = new String[lineNumber];
		System.arraycopy(caxis, 0, Finalcaxis, 0, lineNumber);
		int[] Finaldaxis = new int[lineNumber];
		System.arraycopy(daxis, 0, Finaldaxis, 0, lineNumber);
		
		UserAccount user[] = new UserAccount[Finalaaxis.length];
		
		for (int i = 0; i < Finalaaxis.length; i++) {//creates the object and returns it to the constructor
			user[i] = new UserAccount(Finalaaxis[i], Finalbaxis[i], Finalcaxis[i]);
		}		
		return user;
	}
	
	public UserAccount() {
		int userChoice = getUserChoice();
		switch (userChoice) {
		case 1:
			Register();
		case 2:
			Login();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserAccount();

		
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

	public void Register() {

		noOfUsers = readfile1();
		
		System.out.println("Register to PayUp");
		System.out.println("Do you want to register as a (R) Regular User or (A) Admin?");
		String typeOfAccount = userInput3.nextLine();
		System.out.println("Please choose a username:");
		String Un = userInput2.nextLine();
		System.out.println("Please choose a password:");
		String Pw = userInput2.nextLine();

		try { //This is for Registration of the users

			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database.txt",true)));
			wr.println(Un + "\t" + Pw + "\t" + typeOfAccount + "\t" + noOfUsers);
			
			wr.close();	
		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}
		Login();	
	}

	
	public boolean Login() {
		int lineNumber = 0;
		String[] xaxis = new String[100];
		String[] yaxis = new String[100];
		boolean j = false;
		String Un;
		String Pw;
				

		System.out.println("Login into PayUp");
		System.out.println("Please input your username:");
		Un = userInput2.nextLine();
		System.out.println("Please input your password:");
		Pw = userInput2.nextLine();

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
			if (Un.equals(Finalxaxis[i]) && Pw.equals(Finalyaxis[i])) {
				j = true;
				System.out.println("Login successful");
				break;
			} 
		}
		return j;
	}
	
	
	public int readfile1() {//increments the user ID
		int lineNumber = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return lineNumber;
	}
}
