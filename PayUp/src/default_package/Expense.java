package default_package;
import java.io.*;
import java.util.*;

public class Expense {
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	static Scanner userInput3 = new Scanner(System.in); // for double
	String expenseName; //
	String expenseDate; // 
	double amount; // These variables are created for the main constructor
	int expenseId; //
	int listID; //
	RegularAccount [] temp1 = RegularAccount.createRegulars(); // Creates Regular User Objects
	AdministratorAccount [] temp2 = AdministratorAccount.createAdmins(); //  Creates Admin User Objects
	UserAccount [] temp3 = UserAccount.createAllUsers(); // Creates All Users objects
	List [] temp4 = List.createLists(); // Creates List Objects
	double individualBalance;
	int userID;
	static int fileCount;
	int debitor;
	int userChoice;
	int loggedUserID;
	int creditor;

	public Expense(int listIdc, String name, double amountc, String date, int creditorsc, int debitorc, int expId) {
		/*
		This is the main Constructor for the Expense objects
		 */
		listID = listIdc;
		expenseName = name;
		amount = amountc;
		expenseDate = date;
		creditor = creditorsc;
		debitor = debitorc;
		expenseId = expId;
	}

	public Expense(int userChoicec, int loggedUserIDc) {
		/*
		This Constructor directs the Regular User to the right method based on a passed-on User Choice	
		 */
		userChoice = userChoicec;
		int loggedUserIDl = loggedUserIDc;

		switch (userChoice) {
		case 4:
			createIndividualBalance(loggedUserIDl);
			break;
		case 5:
			settleList(loggedUserIDl);
			break;
		case 6:
			addExpenseToList(loggedUserIDl);
			break;
		case 7:
			UserAccount.exit();
			break;
		}
	}

	public static Expense[] createExpenseObject() {
		/*
		This method creates Expense Objects
		 */
		int lineNumber = 0;
		int [] listID = new int [100];
		String [] expenseName = new String [100];
		double [] amount = new double [100];
		String [] expenseDate = new String [100];
		int [] creditors = new int [100];
		int [] debitors = new int [100];
		int [] expenseID = new int [100];

		try {
			String sCurrentLine;
			String [] uCurrent = new String [5];
			BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				listID [lineNumber] = Integer.parseInt(uCurrent [0]);
				expenseName [lineNumber] = uCurrent [1];
				amount [lineNumber] = Double.parseDouble(uCurrent [2]);
				expenseDate [lineNumber] = uCurrent [3];
				creditors [lineNumber] = Integer.parseInt(uCurrent [4]);
				debitors [lineNumber] = Integer.parseInt(uCurrent [5]);
				expenseID [lineNumber] = Integer.parseInt(uCurrent [6]);
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		int [] finallistID = new int [lineNumber];
		System.arraycopy(listID, 0, finallistID, 0, lineNumber);
		String [] finalexpenseName = new String [lineNumber];
		System.arraycopy(expenseName, 0, finalexpenseName, 0, lineNumber);
		double [] finalamount = new double [lineNumber];
		System.arraycopy(amount, 0, finalamount, 0, lineNumber);
		String [] finalexpenseDate = new String [lineNumber];
		System.arraycopy(expenseDate, 0, finalexpenseDate, 0, lineNumber);
		int [] finalcreditors = new int [lineNumber];
		System.arraycopy(creditors, 0, finalcreditors, 0, lineNumber);
		int [] finalDebitors = new int [lineNumber];
		System.arraycopy(debitors, 0, finalDebitors, 0, lineNumber);
		int [] finalexpenseID = new int [lineNumber];
		System.arraycopy(expenseID, 0, finalexpenseID, 0, lineNumber);

		Expense expense [] = new Expense [finallistID.length];



		for (int i = 0; i < finallistID.length; i++) {
			expense [i] = new Expense(finallistID [i], finalexpenseName [i], finalamount [i], finalexpenseDate [i], finalcreditors [i], finalDebitors [i], finalexpenseID [i]);
		}	

		return expense;
	}


	public void addExpenseToList(int loggedUserIDc) {
		/*
		This Method enables the user to create Expenses for a list that they are part of.
		All created expenses are written in an Expense Database.	
		 */
		//This creates all necessary local variables 
		int loggedUserIDl = loggedUserIDc;
		List [] lists = List.createLists(); // This creates objects of the list
		int amountOfLists = List.readListNumber();
		int lineNumber = 0;
		int [] aUser = new int [100];
		String sCurrentLine;
		String [] uCurrent = new String [4];
		int [] participantsInList = new int [100];
		Boolean localBoolean = false;
		boolean localBoolean2 = false;

		//Asks the user which list he/she wants to view
		System.out.println("What is the list ID to which this expense belongs?");
		int listId = InterfaceClass.getAnInteger();

		//This creates an array of all people that are part of a list
		sCurrentLine = lists[listId].participants;
		uCurrent = sCurrentLine.split(",");
		for (int k = 0; k < 2; k++) {
			participantsInList[k] = Integer.parseInt(uCurrent[k]);
		}
		int finalParticipantsInList [] = new int [2];
		System.arraycopy(participantsInList, 0, finalParticipantsInList, 0, 2);

		//This prevents you from adding an expense to a List where you are not part of
		for (int j = 0;  j < 2; j++ ) {
			if (loggedUserIDl == finalParticipantsInList [j]) {
				localBoolean2 = true ;
			}else {

			}
		}
		/*
		If the logged in user is part of the list then the following lines of code gets executed
		 */
		if (localBoolean2 == true) {

			System.out.println("What is the name of this expense?");
			String expenseName = userInput2.nextLine();
			System.out.println("What is the amount of this expense?");
			Double amount = InterfaceClass.getADouble();
			System.out.println("What is the date of this expense?");
			System.out.println("Please use the following Syntax: (day/month/year)");
			String date = userInput2.nextLine();

			try {
				String sCurrentLine1;
				BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
				while ((sCurrentLine1 = myFile.readLine()) != null) {
					lineNumber++;
				}
				myFile.close(); 
			}catch (IOException e) {
				System.out.println("This file does not exist");
			}			

			try { 

				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("Expense_database.txt",true)));
				wr.println(listId + "\t" + expenseName + "\t" + amount + "\t" + date + "\t" + loggedUserIDl + "\t" + aUser[0] + "\t" + lineNumber);
				wr.close();

			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
			System.out.println("--------------------------------------------------------------------");
			System.out.println("The expense is succesfully added to your list");
			System.out.println("You are now directed to the main menu");
			System.out.println("--------------------------------------------------------------------");
			new InterfaceClass();
			/*	
		If the logged in user is not part of the list then the following lines of code gets executed
		And you are sent back to this method again	
			 */
		}else {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("You do not have access to this list, please input a different List ID");
			System.out.println("--------------------------------------------------------------------");
			addExpenseToList(loggedUserIDl);
		}
	}

	public void createIndividualBalance(int loggedUserIDc) {
		// Declaring all necessary variables
		int loggedUserIDl = loggedUserIDc;
		boolean localBoolean2 = false;
		String sCurrentLine;
		String [] uCurrent = new String [4];
		int [] participantsInList = new int [100];
		double tempBalance = 0.00; 	//Creates the variable for the temporary balance of the logged in user of a specific list

		// This checks if the list has already expenses in it
		// If not, the user is directed to the main menu again
		int numExpenses = readExpenseNumber();
		if (numExpenses == 0) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("This list is empty, please add expenses first");
			System.out.println("You will be directed to the main menu");
			System.out.println("--------------------------------------------------------------------");
			new InterfaceClass();
		}else {

			//Asking the user which list he or she wants to know the individual balance for
			System.out.println("For which ListID do you want to view your individual expense");
			int individualBalanceOfList = InterfaceClass.getAnInteger();

			//Splitting the participants of a certain list based on comma
			//The first User ID is always the one who created that list
			sCurrentLine = temp4[individualBalanceOfList].participants;
			uCurrent = sCurrentLine.split(",");
			for (int k = 0; k < 2; k++) {
				participantsInList [k] = Integer.parseInt(uCurrent [k]);
			}
			int finalParticipantsInList [] = new int [2];
			System.arraycopy(participantsInList, 0, finalParticipantsInList, 0, 2);

			//Creates Expense objects.
			Expense [] temp5 = Expense.createExpenseObject(); 


			//Checking if the Logged in user is indeed part of the List that he or she wants to know the balance for
			for (int j = 0;  j < 2; j++ ) {
				if (loggedUserIDl == finalParticipantsInList[j]) {
					localBoolean2 = true ;
				}else {
				}
			}

			//If the logged in user is part of the list then the following block of code
			//is executed

			if (localBoolean2 == true) {

				//Creates local variables for the expense splitting
				double[] arrayOfAmount = new double[100];
				int[] arrayForCreditors = new int[100];
				int counter = 0;		
				int amountOfExpenses = readExpenseNumber();

				//If the List ID matches with the chosen List by the user then
				//the amount of all expenses in that list are copied into the arrayOfAmount
				for (int j = 0; j < amountOfExpenses; j++ )
					if (temp5[j].listID == individualBalanceOfList ) {
						arrayOfAmount[counter] = temp5[j].amount;
						arrayForCreditors[counter] = temp5[j].creditor;
						counter++; // This counter keeps track of how many expenses occurred in that specific list
					}	
				double finalAmount [] = new double[counter];
				System.arraycopy(arrayOfAmount, 0, finalAmount, 0, counter);
				int finalCreditors [] = new int[counter];
				System.arraycopy(arrayForCreditors, 0, finalCreditors, 0, counter);

				//This calculates the individual balance of a logged-in User in a specific list
				//If the creditor is equal to the loggedUser then the amount is equally divided and added to his/her balance
				//If this is not the case then the amount is equally divided and subtracted from his/her balance
				for(int j = 0; j < counter; j++) {
					if (loggedUserIDl == finalCreditors[j]) {
						tempBalance = tempBalance + (finalAmount[j]/2);
					}else {
						tempBalance = tempBalance - (finalAmount[j]/2);

					}
				}

				//This prints out your personal balance of the user
				System.out.println("--------------------------------------------------------------------");
				System.out.print("Your total balance for List" + individualBalanceOfList + " is: ");
				System.out.printf("%.2f", tempBalance);
				System.out.println("You will be directed to the main menu");
				System.out.println("--------------------------------------------------------------------");
				new InterfaceClass();


				//If the user is not part if the specific list he or she get redirected to this method again

			}else {
				System.out.println("--------------------------------------------------------------------");
				System.out.println("You do not have acces to this list, please input a different List ID");
				System.out.println("--------------------------------------------------------------------");
				createIndividualBalance(loggedUserIDl);
			}
		}
	}

	public void settleList(int loggedUserIDc) {
		// Declaring all necessary variables
		int loggedUserIDl = loggedUserIDc;
		boolean localBoolean = false;
		String sCurrentLine;
		String [] uCurrent = new String [5];
		int [] participantsInList = new int [100];
		String [] listName = new String [100];
		String[] participants = new String [100];
		int [] listID = new int [100];
		String [] status = new String [100];
		String sCurrentLine2;
		String [] uCurrentLine = new String [4];
		int numLists = 0;

		//Retrieving the List ID that the user wants to settle
		System.out.println("--------------------------------------------------------------------");
		System.out.println("What is the list ID of the list that you want to settle:");
		System.out.println("--------------------------------------------------------------------");
		int settledList = InterfaceClass.getAnInteger();

		//Splitting the participants of a certain list based on comma
		//The first User ID is always the one who created that list
		sCurrentLine = temp4[settledList].participants;
		uCurrent = sCurrentLine.split(",");
		for (int k = 0; k < 2; k++) {
			participantsInList[k] = Integer.parseInt(uCurrent[k]);
		}
		int finalParticipantsInList [] = new int[2];
		System.arraycopy(participantsInList, 0, finalParticipantsInList, 0, 2);

		// Checking if the Logged in User is indeed part of the list that he or she wants to settle
		for (int j = 0;  j < 2; j++ ) {
			if (loggedUserIDl == finalParticipantsInList[j]) {
				localBoolean = true ;
			}else {

			}
		}

		// If the logged in user is part of the list then the following block of code is executed

		if (localBoolean == true) {

			//this checks if the list is already settled and sends you back to this method when that is the case
			if (temp4[settledList].status.equals("Settled")) {
				System.out.println("--------------------------------------------------------------------");
				System.out.println("This list is already settled please choose a different ListID");
				System.out.println("--------------------------------------------------------------------");
				createIndividualBalance(loggedUserIDl);


				// If the list is not yet settled the following lines of code are executed

			}else {


				//In order to overwrite the List database we first need to read the database and parse it to arrays

				try {

					BufferedReader br = new BufferedReader (new FileReader ("List_database.txt"));

					while ((sCurrentLine2 = br.readLine()) != null) {
						uCurrentLine = sCurrentLine2.split("\t");
						listName[numLists] = uCurrentLine[0];
						participants[numLists] = uCurrentLine[1];
						listID[numLists] = Integer.parseInt(uCurrentLine[2]);
						status[numLists] = uCurrentLine[3];
						numLists++;
					}

					br.close();
				} catch (IOException e) {
					System.out.println("The file does not exist");
				}

				String [] finalListName = new String [numLists];
				System.arraycopy(listName, 0, finalListName, 0, numLists);
				String [] finalParticipants = new String [numLists];
				System.arraycopy(participants, 0, finalParticipants, 0, numLists);
				int [] finalListID = new int [numLists];
				System.arraycopy(listID, 0, finalListID, 0, numLists);
				String [] finalStatus = new String[numLists];
				System.arraycopy(status, 0, finalStatus, 0, numLists);

				System.out.println("Are you sure? Settling a List cannot be undone (Y/N)");
				String confirm = userInput2.nextLine();

				if(confirm.equals("Y")) {
					try {
						PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",false)));
						for (int i = 0; i < numLists; i++) {
							if (i == settledList) {
								wr.println(finalListName[i] + "\t" + finalParticipants[i] + "\t" + finalListID[i] + "\t" + "Settled");
							}else {
								wr.println(finalListName[i] + "\t" + finalParticipants[i] + "\t" + finalListID[i] + "\t" + finalStatus[i]);	
							}
						}
						wr.close();

					}catch (IOException e) {
						System.out.println("I/O error when writing on file");
					}
					double amount = individualBalanceForSettledLists(loggedUserIDl, settledList);

					System.out.println("--------------------------------------------------------------------");
					System.out.println("This List is succesfully Settled!");
					if (amount < 0) {
						System.out.printf("You owe:" + "%.2f \n", amount);
					}else {
						System.out.printf("You are owed: " + "%.2f \n", amount);
					}
					System.out.println("--------------------------------------------------------------------");
					//if user chooses N then the following lines of code are executed
				}else {
					System.out.println("--------------------------------------------------------------------");
					System.out.println("You will now be redirected to the main menu");
					System.out.println("--------------------------------------------------------------------");
					new InterfaceClass();

				}

			}
			//If a user has not got acces to the list the following lines are executed
		}else {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("You do not have acces to this list, please input a different List ID");
			System.out.println("--------------------------------------------------------------------");
			createIndividualBalance(loggedUserIDl);
		}

	}

	public double individualBalanceForSettledLists(int loggedUserIDc, int settledListc ) {
		/*
		This extra method returns the balance of a list when it is settled
		It is largely structured the same as the createIndividualBalance method
		 */

		// Declaring all necessary variables
		int loggedUserIDl = loggedUserIDc;
		int settledListl = settledListc;
		double tempBalance = 0.00; 	//Creates the variable for the temporary balance of the logged in user of a specific list

		Expense [] temp5 = Expense.createExpenseObject(); 

		//Creates local variables for the expense splitting
		double [] arrayOfAmount = new double [100];
		int [] arrayForCreditors = new int [100];
		int counter = 0;		
		int amountOfExpenses = readExpenseNumber();

		//If the List ID matches with the chosen List by the user then
		//the amount of all expenses in that list are copied into the arrayOfAmount
		for (int j = 0; j < amountOfExpenses; j++ )
			if (temp5[j].listID == settledListl ) {
				arrayOfAmount [counter] = temp5[j].amount;
				arrayForCreditors [counter] = temp5[j].creditor;
				counter++; // This counter keeps track of how many expenses occurred in that specific list
			}	
		double finalAmount [] = new double[counter];
		System.arraycopy(arrayOfAmount, 0, finalAmount, 0, counter);
		int finalCreditors [] = new int[counter];
		System.arraycopy(arrayForCreditors, 0, finalCreditors, 0, counter);

		//This calculates the individual balance of a logged-in User in a specific list
		//If the creditor is equal to the loggedUser then the amount is equally divided and added to his/her balance
		//If this is not the case then the amount is equally divided and subtracted from his/her balance
		for(int j = 0; j < counter; j++) {
			if (loggedUserIDl == finalCreditors[j]) {
				tempBalance = tempBalance + (finalAmount[j]/2);
			}else {
				tempBalance = tempBalance - (finalAmount[j]/2);

			}
		}
		return tempBalance;
	}

	public static int readExpenseNumber() {
		/*
		this method return how many expenses have been logged.
		 */
		int numExpenses = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				numExpenses++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return numExpenses;
	}
}
