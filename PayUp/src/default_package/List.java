package default_package;
import java.io.*;
import java.util.*;

public class List {
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	String listName;
	int listID;
	String date;
	UserAccount[] temp = UserAccount.readFile();

	public static void directToChoice(String currentUser) {
		new List(currentUser);
	}
	
	public List(String listNamec, int listIDc,) {
		listName = listNamec;
		listID = listIDc;
		//date = datec;
	}
	
	public List(String currentUser) {
		int userChoice = getUserChoice1();
		switch (userChoice) {
		case 1:
			createList(currentUser);
			break;
		case 2:
			viewList();
			break;
		}
		if (userChoice > 2) {
			System.out.println("Your choice is invalid");
		}
	}

	public int getUserChoice1() {
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("************\t What do you want to do? \t**********");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("");
		System.out.println("(1) Create new list");
		System.out.println("(2) View List");
		System.out.println("****************************************************************");
		System.out.print("Please enter your choice (1 or 2): ");
		return userInput1.nextInt();	// gets either 1 or 2 from the user

	}
	
	
	public static List[] createList(String currentUser) {
		System.out.println("How do you want to name the list? ");
		String listName = userInput2.nextLine();
		String cUser = currentUser;
		
		String aUser;
		
		//System.out.println("How many users do you want to add to the list? ");
		//int amountOfUsers = userInput1.nextInt();
		
		//String[] addedUser = new String[amountOfUsers + 1];
		
		System.out.println("What user do you want to add to your list? ");
		aUser = userInput2.nextLine();
			//if (temp[i].userName != aUser) {
			//	System.out.print("User does not exist");
			//}
			//addedUser[i] = aUser;
		
		File directory = new File("C:\\Users\\sjoerd97\\eclipse-workspace");
	    int fileCount = directory.list().length;
	    
	    fileCount = fileCount - 8;
		
		try { //This is for Registration of the users
			
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database" + fileCount + ".txt",true)));
			wr.println(listName); 
			wr.println(cUser + "\n" + aUser);
			wr.close();
			
		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}	
		
		List listObjectArray[] = new List[fileCount + 1];
		
		for (int i = 0; i < fileCount + 1; i++) {
			listObjectArray[i] = new List(listName, fileCount);
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
			BufferedReader myFile = new BufferedReader (new FileReader("list_database.txt")); 
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
