package default_package;
import java.io.*;
import java.util.*;

public class List {
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	static Scanner userInput3 = new Scanner(System.in); // for double
	// These variables are created for the main constructor 
	String listName;
	int listID;
	String date;
	String status;

	RegularAccount [] temp1 = RegularAccount.createRegulars(); // Creates Regular User Objects
	AdministratorAccount [] temp2 = AdministratorAccount.createAdmins();
	UserAccount [] temp3 = UserAccount.createAllUsers();

	static int fileCount;
	String participants;
	int userChoice;
	int loggedUserID;

	public List(String listNamec, String participant, int listIDc, String statusc) {
		/*
		This is the main Constructor for the List objects
		 */
		listName = listNamec;
		participants = participant;
		listID = listIDc;
		status = statusc;
	}

	public List(int userChoicec, int loggedUserIDc){
		/*
		This Constructor directs the Regular User to the right method based on a passed-on User Choice	
		 */
		int loggedUserIDl = loggedUserIDc;
		userChoice = userChoicec;
		switch (userChoice) {
		case 1:
			createList(loggedUserIDl);
			break;
		case 2:
			viewList(loggedUserIDl);
			break;
		case 3:
			deleteList();
			break;
		}
	}

	public static List [] createLists(){
		//This method reads the List_database text file
		
		//Declaring all necessary variables
		int lineNumber = 0;
		int [] idAxis = new int [100];
		String [] nameAxis = new String [100];
		String [] nonsplittedString = new String [100];
		String [] statusAxis = new String [100];

		// Reading the List_database text file
		try {
			String sCurrentLine;
			String [] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				nameAxis [lineNumber] = uCurrent [0];
				nonsplittedString [lineNumber] = uCurrent [1];
				idAxis [lineNumber] = Integer.parseInt(uCurrent [2]);
				statusAxis [lineNumber] = uCurrent [3];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}


		int [] finalIDAxis = new int [lineNumber];
		System.arraycopy(idAxis, 0, finalIDAxis, 0, lineNumber);
		String [] finalParticipantAxis = new String [lineNumber];
		System.arraycopy(nonsplittedString, 0, finalParticipantAxis, 0, lineNumber);
		String [] finalNameAxis = new String [lineNumber];
		System.arraycopy(nameAxis, 0, finalNameAxis, 0, lineNumber);
		String [] finalStatusAxis = new String [lineNumber];
		System.arraycopy(statusAxis, 0, finalStatusAxis, 0, lineNumber);



		List listArray [] = new List[finalIDAxis.length];

		for (int i = 0; i < finalIDAxis.length; i++) {
			listArray[i] = new List(finalNameAxis [i], finalParticipantAxis [i], finalIDAxis [i], finalStatusAxis [i]);
		}		

		return listArray;
	}

	public void createList(int loggeduserIDc) {
		/*
		This method allows the users to create Lists
		 */

		int loggedUserIDl = loggeduserIDc;
		int [] aUser = new int [100];
		Boolean localBoolean = false;
		int lineNumber = 0;
		String status = "Non-settled";

		// User input is asked for in order to create the list
		System.out.println("How do you want to name the list? ");
		String listName = userInput2.nextLine();

		aUser [0] = loggedUserIDl;

		int numUsers = UserAccount.readUserNumber();

		System.out.println("UserID: \t userName");
		for (int i = 0; i < numUsers; i++) {
			System.out.println(temp3[i].userID + "\t" + temp3[i].userName);
		}

		System.out.println("What is the userID of the user that you want to add to your list? ");
		aUser [1] = InterfaceClass.getAnInteger();

		int length = UserAccount.readUserNumber(); // This returns the amount of registered user for the for-loop below

		//This for loop checks if the user that needs to be added to the list is actually registered
		// by checking the user ID
		for (int i = 0; i < length; i++ ) {
			if (aUser [1] == temp3[i].userID) {
				localBoolean = true;
			}else {

			}
		}

		// If the localBoolean is true, a List_database file gets created and the 
		//newly created list gets added, or the existing List_database text file 
		//gets appended with the new list
		if (localBoolean == true) {
			try {
				String sCurrentLine;
				BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
				while ((sCurrentLine = myFile.readLine()) != null) {
					lineNumber++;
				}
				myFile.close(); 
			}catch (IOException e) {
				System.out.println("This file does not exist");
			}

			String str = "";
			for (int i = 0; i < 1 + 1; i++) {
				str = str + aUser [i] + ",";	
			}

			try { 
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",true)));
				wr.println(listName + "\t" + str + "\t" + lineNumber + "\t" + status);
				wr.close();
				System.out.println("--------------------------------------------------------------------");
				System.out.println("The List is succesfully created.");
				System.out.println("You are now redirected to the main menu.");
				System.out.println("--------------------------------------------------------------------");
				new InterfaceClass();

			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}

	public void viewList(int loggedUserc) {
		/*
		This method allows the users to view Lists 
		 */

		// Declaring all necessary variables
				int loggedUserIDl = loggedUserc;
		int lineNumber = 0;
		String[] listName = new String[100];
		String[] participants = new String[100];
		int[] listID = new int[100];
		String [] status = new String [100];
		String sCurrentLine;
		String [] uCurrent = new String [3];
		int [] participantsInList = new int [100];
		boolean localBoolean2 = false;

		System.out.println("What is the list ID of the list that you want to view? ");
		int viewList = InterfaceClass.getAnInteger();

		List [] tempListObject = List.createLists();

		//This creates an array of all people that are part of a list
		sCurrentLine = tempListObject[viewList].participants;
		uCurrent = sCurrentLine.split(",");
		for (int k = 0; k < 2; k++) {
			participantsInList [k] = Integer.parseInt(uCurrent [k]);
		}
		int finalParticipantsInList [] = new int [2];
		System.arraycopy(participantsInList, 0, finalParticipantsInList, 0, 2);

		//This checks if a user has access to a certain List
		for (int j = 0;  j < 2; j++ ) {
			if (loggedUserIDl == finalParticipantsInList[j]) {
				localBoolean2 = true ;
			}else {

			}
		}

		//If the logged in user is part of the list then the following lines of code gets executed
		if (localBoolean2 == true) {

			try {
				String sCurrentLine2;
				String [] uCurrent2 = new String [4];
				BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
				while ((sCurrentLine2 = myFile.readLine()) != null) {
					uCurrent2 = sCurrentLine2.split("\t");

					listName [lineNumber] = uCurrent2 [0];
					participants [lineNumber] = uCurrent2 [1];
					listID [lineNumber] = Integer.parseInt(uCurrent2 [2]);
					status [lineNumber] = uCurrent2 [3];

					lineNumber++;
				}
				myFile.close(); 	
			}catch (IOException e) {
				System.out.println("This file does not exist");
			}

			String [] finalListName = new String [lineNumber];
			System.arraycopy(listName, 0, finalListName, 0, lineNumber);
			String [] finalParticipants = new String [lineNumber];
			System.arraycopy(participants, 0, finalParticipants, 0, lineNumber);
			int [] finalListID = new int [lineNumber];
			System.arraycopy(listID, 0, finalListID, 0, lineNumber);
			String [] finalStatus = new String [lineNumber];
			System.arraycopy(status, 0, finalStatus, 0, lineNumber);

			String userName1 = getUserName(finalListID[0]); 
			String userName2 = getUserName(finalListID[1]);

			System.out.println("List Name: \t Particpants: \t List ID: \t \t Status: ");
			for (int i = 0; i < lineNumber; i++) {
				if (viewList == finalListID [i]) {
					System.out.println(finalListName [i] + "\t\t" + userName1 + "\t" + userName2 + "\t\t" + finalListID [i] + "\t\t\t" + finalStatus [i]);
					break;
				}
			}
			
			int amountOfExpenses = Expense.readExpenseNumber();
			Expense [] temp4 = Expense.createExpenseObject();
			System.out.println("Expense Name: \tAmount: \tExpense Data: \t \t Creditor: \t Debitor:" );
			for (int i = 0; i < amountOfExpenses; i++) {
				if (viewList == temp4[i].listID) {
					System.out.println(temp4[i].expenseName + "\t\t" + temp4[i].amount + "\t\t" + temp4[i].expenseDate + "\t\t " +  temp4[i].creditor + "\t\t " + temp4[i].debitor);
				}

			}
			System.out.println("--------------------------------------------------------------------");	
			System.out.println("You are now redirected to the main menu.");
			System.out.println("--------------------------------------------------------------------");
			new InterfaceClass();

			//If the User doesn't have access to the list the following lines of code get executed	
		}else {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("You do not have access to this list, please input a different List ID");
			System.out.println("--------------------------------------------------------------------");
			viewList(loggedUserIDl);
		}
	}

	public static void deleteList() {
			/*
		This method allows the users to delete existing Lists 
		 */

		// Declaring all necessary variables
		int lineNumber = 0;
		String[] listName = new String[100];
		String[] participants = new String[100];
		int[] listID = new int[100];
		String [] status = new String [100];

		// Asking for user input
		System.out.println("Enter the list ID of the list you want to delete.");
		int viewList = InterfaceClass.getAnInteger();
		System.out.println("Are you sure? Deleting lists is permanent and you will no longer be able to add expenses to it? (Y/N)");
		String confirm = userInput2.nextLine();


		// Reading the List_database file
		try {
			String sCurrentLine;
			String [] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				listName [lineNumber] = uCurrent [0];
				participants [lineNumber] = uCurrent [1];
				listID [lineNumber] = Integer.parseInt(uCurrent [2]);
				status [lineNumber] = uCurrent [3];
				
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		String [] finalListName = new String [lineNumber];
		System.arraycopy(listName, 0, finalListName, 0, lineNumber);
		String [] finalParticipants = new String [lineNumber];
		System.arraycopy(participants, 0, finalParticipants, 0, lineNumber);
		int [] finalListID = new int [lineNumber];
		System.arraycopy(listID, 0, finalListID, 0, lineNumber);
		String [] finalStatus = new String [lineNumber];
		System.arraycopy(status, 0, finalStatus, 0, lineNumber);

		//When the user confirms that he wants to delete the list, the list with the 
		//list ID that was previously indicated gets deleted
		if(confirm.equals("Y")) {
			try {
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",false)));
				for (int i = 0; i < lineNumber; i++) {
					if (i == viewList) {
						wr.println("N/A" + "\t" + "N/A" + "\t" + finalListID[i] + "\t" + "N/A");
					} else {
						wr.println(finalListName [i] + "\t" + finalParticipants [i] + "\t" + finalListID [i] + "\t" + finalStatus [i]);
					}
				}
				wr.close();

			}catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}	
		}System.out.println("--------------------------------------------------------------------");	
		System.out.println("The list is deleted");
		System.out.println("You are now redirected to the main menu.");
		System.out.println("--------------------------------------------------------------------");
		new InterfaceClass();
	}
	
	public static int readListNumber() {
		/*
		this method returns how many expenses have been logged.
		 */
		int numLists = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				numLists++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return numLists;
	}
	public String getUserName(int userIDc) {
		/*
		This method gets the User Name for a certain User ID
		 */
		int userIDl = userIDc;
		String currentUser = "bla"; 

		int numUsers = UserAccount.readUserNumber();
		for (int i = 0; i < numUsers; i++) {
			if (temp3[i].userID == userIDl) {
				currentUser = temp3[i].userName; 
			}
		}

		return currentUser;
	}
}
