package default_package;
import java.io.*;
import java.util.*;

public class List {
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	String listName;
	int listID;
	UserAccount[] temp = UserAccount.readFile();
	UserAccount temp1 = UserAccount.passUserName();

	public static void directToChoice() {
		new List();
	}
	
	public List() {
		int userChoice = getUserChoice1();
		switch (userChoice) {
		case 1:
			createList();
		case 2:
			viewList();
		}
		if (userChoice > 2) {
			System.out.println("Your choice is invalid");
		}
	}

	public int getUserChoice1() {
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("************\t\t What do you want to do? \t\t**********");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("");
		System.out.println("(1) Create new list");
		System.out.println("(2) View List");
		System.out.println("****************************************************************");
		System.out.print("Please enter your choice (1 or 2): ");
		return userInput1.nextInt();	// gets either 1 or 2 from the user

	}
	
	public List(String name, int count) {
		listName = name;
		listID = count;
			
	}
	
	public void createList() {
		System.out.println("How do you want to name the list? ");
		String listName = userInput1.nextLine();
		
		String aUser;
		
		//System.out.println("How many users do you want to add to the list? ");
		//int amountOfUsers = userInput1.nextInt();
		
		//String[] addedUser = new String[amountOfUsers + 1];
		
		System.out.println("What user do you want to add to your list? ");
		aUser = userInput1.nextLine();
			//if (temp[i].userName != aUser) {
			//	System.out.print("User does not exist");
			//}
			//addedUser[i] = aUser;
		
		String loggedInUser = temp1.userName;
		
		try { //This is for Registration of the users

			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("list_database.txt",true)));
			wr.println(listName); 
			wr.println(loggedInUser + "\n" + aUser);
			
			
			wr.close();	
		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}	
	}
	
	public void viewList() {
		System.out.println("What list do you want to view? ");
		String[] xaxis = new String[100];
		String[] yaxis = new String[100];
		int lineNumber = 0;
		
		String viewList = userInput1.nextLine();
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
		for (int i = 0; i < lineNumber; i++) {
		System.out.println(xaxis[i] + "\n" + yaxis[i]);
		}
		
	}
}
