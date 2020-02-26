package default_package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class AdministratorAccount extends UserAccount{
	private final String username = "admin";
	private final String password = "admin";
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int adminChoice = getAdminChoice();
		switch (adminChoice) {
		case 1:
			viewUsers();
		case 2:
			RemoveUsers();
			break;
		case 3:
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
	String filename = "temp.txt";

	public static void viewUsers() {
		int i = 0;
		String[] xAxis = new String[100];
		String[] yAxis = new String[100];
		System.out.println("");
		System.out.println("The list of users on PayUp");
		System.out.println("----------------------------------------------------------------");
		System.out.println("Username:");
		System.out.println("----------------------------------------------------------------");
		
		try {
			String sCurrentLine;
			String[] uCurrentLine = new String[2];
			BufferedReader br = new BufferedReader (new FileReader ("User_database.txt"));
			
			while ((sCurrentLine = br.readLine()) != null) {
				uCurrentLine = sCurrentLine.split(",");
				xAxis[i] = (uCurrentLine[0]);
				yAxis[i] = (uCurrentLine[0]);
				i++;
			}
			
			for(int k = 0; k<5; k++) {
				System.out.println(xAxis[k]);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("The file does not exist!");
		}
		String[] username = new String [i];
		System.arraycopy(xAxis, 0, username, 0, i);
		return;
	}

	public static void RemoveUsers() {
	System.out.println("Input a username you wish to remove:");
	String userName = userInput2.nextLine();
	
	}
	
		public static void RemoveList() {
			
			
		}
	}

