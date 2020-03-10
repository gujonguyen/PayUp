package default_package;
import java.io.*;
import java.util.*;

public class List {
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	static Scanner userInput3 = new Scanner(System.in); // for double
	String listName;
	int listID;
	String date;
	String status;
	RegularAccount[] temp1 = RegularAccount.createRegulars(); // Creates Regular User Objects
	AdministratorAccount[] temp2 = AdministratorAccount.createAdmins(); //  Creates Admin User Objects
	UserAccount[] temp3 = UserAccount.createAllUsers(); // Creates All Users objects
	Expense [] temp4 = Expense.createExpenseObject();
	static int fileCount;
	String participants;
	int userChoice;
	int loggedUserID;
	String status;
	
	public List(String listNamec, String participant, int listIDc, String statusc) {
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
		case 0:
			System.out.println("Please enter a value between 1-9");
			InterfaceClass.regularUserInterface();
			break;
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

	public static List[] readFile(){
		/*
		This method reads the List_database text file
		 */
		
		// Declaring all necessary variables
		int lineNumber = 0;
		int [] idAxis = new int[100];
		String [] nameAxis = new String[100];
		String[] nonsplittedString = new String[100];
		String[] statusAxis = new String[100];

		// Reading the List_database text file
		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				nameAxis[lineNumber] = uCurrent[0];
				nonsplittedString[lineNumber] = uCurrent[1];
				idAxis[lineNumber] = Integer.parseInt(uCurrent[2]);
				statusAxis[lineNumber] = uCurrent[3];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol7");
		}
		

		int [] FinalIDAxis = new int[lineNumber];
		System.arraycopy(idAxis, 0, FinalIDAxis, 0, lineNumber);
		String [] FinalParticipantAxis = new String[lineNumber];
		System.arraycopy(nonsplittedString, 0, FinalParticipantAxis, 0, lineNumber);
		String [] FinalNameAxis = new String[lineNumber];
		System.arraycopy(nameAxis, 0, FinalNameAxis, 0, lineNumber);
		String [] finalStatusAxis = new String [lineNumber];
		System.arraycopy(statusAxis, 0, finalStatusAxis, 0, lineNumber);
		
		List listArray[] = new List[FinalIDAxis.length];

		for (int i = 0; i < FinalIDAxis.length; i++) {
			listArray[i] = new List(FinalNameAxis[i], FinalParticipantAxis[i], FinalIDAxis[i], finalStatusAxis[i]);
		}
		return listArray;
	}

	public void createList(int loggeduserIDc) {
		/*
		This method allows the users to create Lists
		 */
		
		// Declaring all necessary variables
		int loggedUserIDl = loggeduserIDc;
		String status = "Non-settled";
		int [] aUser = new int[100];
		Boolean localBoolean = false;
		int lineNumber = 0;
		
		// User input is asked for in order to create the list
		System.out.println("How do you want to name the list? ");
		String listName = userInput2.nextLine();

		System.out.println("How many users do you want to add?");
		int amountOfUser = userInput1.nextInt();
		aUser [0] = loggedUserIDl;

		System.out.println("What is the userID of the user that you want to add to your list? ");
		aUser[1] = userInput1.nextInt();
		
		int length = UserAccount.readfile1(); // This returns the amount of registered user for the for-loop below
		
		for (int i = 0; i < length; i++ ) {
			if (aUser[1] == temp3[i].userID) {
				localBoolean = true;
			}else {

			}
		}

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
			for (int i = 0; i < 1; i++) {
				 str = str + aUser[i] + ",";	
			}

			try { 
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",true)));
				wr.println(listName + "\t" + str + "\t" + lineNumber + "\t" + status);
				System.out.println("--------------------------------------------------------------------");
				System.out.println("The List is succesfully created.");
				System.out.println("You are now redirected to the main menu.");
				System.out.println("--------------------------------------------------------------------");
				new InterfaceClass();
				wr.close();
				

		} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}

	public void viewList(int loggedUserc) {

		int loggedUserIDl = loggedUserc;

		int lineNumber = 0;
		String [] c1 = new String[100];
		String[] c2 = new String[100];
		int [] c3 = new int[100];
		String[] status = new String[100];
		String sCurrentLine;
		String[] uCurrent = new String[3];
		int[] participantsInList = new int [100];
		boolean localBoolean2 = false;

		System.out.println("What is the list ID of the list that you want to view? ");
		int viewList = userInput1.nextInt();

		List[] tempListObject = List.readFile();

		//This creates an array of all people that are part of a list
		sCurrentLine = tempListObject[viewList].participants;
		uCurrent = sCurrentLine.split(",");
		for (int k = 0; k < 2; k++) {
			participantsInList[k] = Integer.parseInt(uCurrent[k]);
		}
		int finalParticipantsInList [] = new int[2];
		System.arraycopy(participantsInList, 0, finalParticipantsInList, 0, 2);

		//This checks if a user has acces to a certain List
		for (int j = 0;  j < 2; j++ ) {
			if (loggedUserIDl == finalParticipantsInList[j]) {
				localBoolean2 = true ;
			}else {

			}
		}
		/*
		If the logged in user is part of the list then the following lines of code gets executed
		 */
		if (localBoolean2 == true) {

			try {
				String sCurrentLine2;
				String[] uCurrent2 = new String [4];
				BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
				while ((sCurrentLine2 = myFile.readLine()) != null) {
					uCurrent2 = sCurrentLine2.split("\t");

					c1[lineNumber] = uCurrent2[0];
					c2[lineNumber] = uCurrent2[1];
					c3[lineNumber] = Integer.parseInt(uCurrent2[2]);
					status[lineNumber] = uCurrent[3];
					
					lineNumber++;
				}
				myFile.close(); 	
			}catch (IOException e) {
				System.out.println("This file does not existlol9");
			}

			String[] Finalc1 = new String[lineNumber];
			System.arraycopy(c1, 0, Finalc1, 0, lineNumber);
			String[] Finalc2 = new String[lineNumber];
			System.arraycopy(c2, 0, Finalc2, 0, lineNumber);
			int [] Finalc3 = new int[lineNumber];
			System.arraycopy(c3, 0, Finalc3, 0, lineNumber);
			String[] finalStatus = new String[lineNumber];
			System.arraycopy(status, 0, finalStatus, 0, lineNumber);

			System.out.println("List Name: \t Particpants: \t List ID: \t \t Status: ");
			for (int i = 0; i < lineNumber; i++) {
				if (viewList == Finalc3[i]) {
					System.out.println(Finalc1[i] + "\t\t\t" + Finalc2[i] + "\t\t" + Finalc3[i] + "\t" + finalStatus[i]);
					break;
				}

			}
			int amountOfExpenses = Expense.readfile1();
			System.out.println("Expense Name: \t Amount: \t Expense Data: \t \t Creditor: \t Debitor:" );
			for (int i = 0; i < amountOfExpenses; i++) {
				if (viewList == temp4[i].listID) {
					System.out.println(temp4[i].expenseName + "\t" + temp4[i].amount + "\t" + temp4[i].expenseDate + "\t" +  temp4[i].creditor + "\t" + temp4[i].debitor);
				}

			}
		/*	
		If the User doesn't have access to the list the following lines of code get executed	
		*/
		}else {
			System.out.println("---------------------------------------------");
			System.out.println("You do not have acces to this list, please input a different List ID");
			System.out.println("---------------------------------------------");	
			viewList(loggedUserIDl);
		}
	}
	
	public void deleteList() {
		int lineNumber = 0;
		String [] c1 = new String[100];
		String[] c2 = new String[100];
		int [] c3 = new int[100];
		String[] status = new String[100];

		System.out.println("Enter the list ID of the list you want to delete.");
		int viewList = userInput2.nextInt();
		System.out.println("Are you sure? Deleting lists is permanent and you will no longer be able to add expenses to it? (Y/N)");
		String confirm = userInput1.nextLine();

		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				c1[lineNumber] = uCurrent[0];
				c2[lineNumber] = uCurrent[1];
				c3[lineNumber] = Integer.parseInt(uCurrent[2]);
				status[lineNumber] = uCurrent[3];

				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol10");
		}

		String[] Finalc1 = new String[lineNumber];
		System.arraycopy(c1, 0, Finalc1, 0, lineNumber);
		String[] Finalc2 = new String[lineNumber];
		System.arraycopy(c2, 0, Finalc2, 0, lineNumber);
		int[] Finalc3 = new int[lineNumber];
		System.arraycopy(c3, 0, Finalc3, 0, lineNumber);
		String[] finalStatus = new String[lineNumber];
		System.arraycopy(status, 0, finalStatus, 0, lineNumber);

		if(confirm.equals("Y")) {
			try {
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",false)));
				for (int i = 0; i < lineNumber; i++) {
					if (i == viewList) {
						wr.println("N/A" + "\t" + "N/A" + "\t" + Finalc3[i] + "\t" + "N/A");
					} else {
						wr.println(Finalc1[i] + "\t" + Finalc2[i] + "\t" + Finalc3[i] + "\t" + finalStatus[i]);
					}
				}
				wr.close();

			}catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}	
		}
	}
	public static int readfile1() {
		int numLists = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				numLists++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol2");
		}
		return numLists;
	}
}
