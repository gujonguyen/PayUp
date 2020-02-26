package default_package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class AdministratorAccount extends UserAccount{
	private final String username = "admin";
	private final String password = "admin";
	private int lineNumber;
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	private static String line;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int adminChoice = getAdminChoice();
		switch (adminChoice) {
		case 1:
			RemoveUsers();
			break;
		case 2:
			RemoveList();
			break;
		}
	}
	// This method asks the administrator what he wants to do

	public static int getAdminChoice(){
		System.out.println("Welcome admin of PayUp!");
		System.out.println("What do you wish to do?");
		System.out.println("(1) Remove a User");
		System.out.println("(2) Remove Lists");
		System.out.println("****************************************************************");
		System.out.print("Please enter your choice (1 or 2): ");
		return userInput1.nextInt();
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	public static String[] RemoveUsers() {
		int lineNumber = 0;
		String[] xAxis = new String[100];
		
		System.out.println("Choose a user from the list you wish to remove from PayUp:");
		System.out.println("----------------------------------------------------------------");
		System.out.println(" Username");
		System.out.println("----------------------------------------------------------------");

		try {
			String sCurrentLine;
			String[] uCurrentLine = new String[2];
			BufferedReader br = new BufferedReader (new FileReader ("User_database.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				uCurrentLine = sCurrentLine.split("\t");
				xAxis[lineNumber] = (uCurrentLine[0]);
				lineNumber++;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("The file does not exist!");
		}
		String[] username = new String [lineNumber];
		System.arraycopy(xAxis, 0, username, 0, lineNumber);
		return username;
	}
	

				
	public static void RemoveList() {

	}

}
