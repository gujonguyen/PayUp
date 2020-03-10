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
	RegularAccount[] temp1 = RegularAccount.createRegulars(); // Creates Regular User Objects
	AdministratorAccount[] temp2 = AdministratorAccount.createAdmins(); //  Creates Admin User Objects
	UserAccount[] temp3 = UserAccount.createAllUsers(); // Creates All Users objects
	List [] temp4 = List.readFile(); // Creates List Objects
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
	}}

	public Expense(int userChoicec, int loggedUserIDc) {
		/*
		This Constructor directs the Regular User to the right method based on a passed-on User Choice	
		 */
		userChoice = userChoicec;
		loggedUserID = loggedUserIDc;

		switch (userChoice) {
		case 4:
			createIndividualBalance(loggedUserIDc);
			break;
		case 5:
			//settleList();
			break;
		case 6:
			addExpenseToList(loggedUserIDc);
			break;
		case 7:
			UserAccount.Exit();
			break;
		}
	}

	public static Expense[] createExpenseObject() {
		/*
		This method creates Expense Objects
		 */
		int lineNumber = 0;
		int[] listID = new int[100];
		String[] expenseName = new String[100];
		double[] amount = new double[100];
		String[] expenseDate = new String[100];
		int[] creditors = new int[100];
		int[] debitors = new int[100];
		int[] expenseID = new int[100];

		try {
			String sCurrentLine;
			String[] uCurrent = new String [5];
			BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				listID[lineNumber] = Integer.parseInt(uCurrent[0]);
				expenseName[lineNumber] = uCurrent[1];
				amount[lineNumber] = Double.parseDouble(uCurrent[2]);
				expenseDate[lineNumber] = uCurrent[3];
				creditors[lineNumber] = Integer.parseInt(uCurrent[4]);
				debitors[lineNumber] = Integer.parseInt(uCurrent[5]);
				expenseID[lineNumber] = Integer.parseInt(uCurrent[6]);
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		int[] FinallistID = new int[lineNumber];
		System.arraycopy(listID, 0, FinallistID, 0, lineNumber);
		String[] FinalexpenseName = new String[lineNumber];
		System.arraycopy(expenseName, 0, FinalexpenseName, 0, lineNumber);
		double[] Finalamount = new double[lineNumber];
		System.arraycopy(amount, 0, Finalamount, 0, lineNumber);
		String[] FinalexpenseDate = new String[lineNumber];
		System.arraycopy(expenseDate, 0, FinalexpenseDate, 0, lineNumber);
		int[] Finalcreditors = new int[lineNumber];
		System.arraycopy(creditors, 0, Finalcreditors, 0, lineNumber);
		int[] FinalDebitors = new int[lineNumber];
		System.arraycopy(debitors, 0, FinalDebitors, 0, lineNumber);
		int[] FinalexpenseID = new int[lineNumber];
		System.arraycopy(expenseID, 0, FinalexpenseID, 0, lineNumber);

		Expense expense[] = new Expense[FinallistID.length];



		for (int i = 0; i < FinallistID.length; i++) {
			expense[i] = new Expense(FinallistID[i], FinalexpenseName[i], Finalamount[i], FinalexpenseDate[i], Finalcreditors[i], FinalDebitors[i], FinalexpenseID[i]);
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
		List [] temp4 = List.readFile(); // This creates objects of the list
		int amountOfLists = List.readfile1();
		int lineNumber = 0;
		int [] aUser = new int[100];
		String sCurrentLine;
		String[] uCurrent = new String [4];
		int [] participantsInList = new int[100];
		Boolean localBoolean = false;
		boolean localBoolean2 = false;

		//Asks the user which list he/she wants to view
		System.out.println("What is the list ID to which this expense belongs?");
		int listId = userInput1.nextInt();

		//This creates an array of all people that are part of a list
		sCurrentLine = temp4[listId].participants;
		uCurrent = sCurrentLine.split(",");
		for (int k = 0; k < 2; k++) {
			participantsInList[k] = Integer.parseInt(uCurrent[k]);
		}
		int finalParticipantsInList [] = new int[2];
		System.arraycopy(participantsInList, 0, finalParticipantsInList, 0, 2);

		//This prevents you from adding an expense to a List where you are not part of
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

			System.out.println("What is the name of this expense?");
			String expenseName = userInput2.nextLine();
			System.out.println("What is the amount of this expense?");
			Double amount = userInput3.nextDouble();
			System.out.println("What is the date of this expense?");
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
			System.out.println("---------------------------------------------");	
			System.out.println("The expense is succesfully added to your list");
			System.out.println("---------------------------------------------");
			/*	
		If the logged in user is not part of the list then the following lines of code gets executed
		And you are sent back to this method again	
			 */
		}else {
			System.out.println("---------------------------------------------");
			System.out.println("You do not have acces to this list, please input a different List ID");
			System.out.println("---------------------------------------------");	
			addExpenseToList(loggedUserIDl);
		}
	}

	public double createIndividualBalance(int loggedUserIDc) {
		// Declaring all necessary variables
		int loggedUserIDl = loggedUserIDc;
		boolean localBoolean2 = false;
		String sCurrentLine;
		String[] uCurrent = new String [4];
		int [] participantsInList = new int[100];
		int [] participantsInExpense = new int [100];
		double tempBalance = 0.00; 	//Creates the variable for the temporary balance of the logged in user of a specific list

		//Asking the user which list he or she wants to know the individual balance for
		System.out.println("For which ListID do you want to view your individual expense");
		int individualBalanceOfList = userInput1.nextInt();

		//Splitting the participants of a certain list based on comma
		//The first User ID is always the one who created that list
		sCurrentLine = temp4[individualBalanceOfList].participants;
		uCurrent = sCurrentLine.split(",");
		for (int k = 0; k < 2; k++) {
			participantsInList[k] = Integer.parseInt(uCurrent[k]);
		}
		int finalParticipantsInList [] = new int[2];
		System.arraycopy(participantsInList, 0, finalParticipantsInList, 0, 2);

		//Creates Expense objects.
		Expense[] temp5 = Expense.createExpenseObject(); 


		//Checking if the Logged in user is indeed part of the List that he or she wants to know the balance for
		for (int j = 0;  j < 2; j++ ) {
			if (loggedUserIDl == finalParticipantsInList[j]) {
				localBoolean2 = true ;
			}else {

			}
		}
		/*
		 * If the logged in user is part of the list then the following block of code
		 * is executed
		 */
		if (localBoolean2 == true) {


			//Splitting the debitors of a certain expense based on comma
			int sCurrentInt = temp5[individualBalanceOfList].debitor;
			
			for (int k = 0; k < 1; k++) {
				participantsInExpense[k] = sCurrentInt;
			}
			int finalParticipantsInExpense [] = new int[1];
			System.arraycopy(participantsInExpense, 0, finalParticipantsInExpense, 0, 1);

			//Creates local variables for the expense splitting
			double[] arrayOfAmount = new double[100];
			int[] arrayForCreditors = new int[100];
			int counter = 0;		
			int amountOfExpenses = readfile1();

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
			System.out.println("----------------------------------------------");
			System.out.print("Your total balance for List" + individualBalanceOfList + " is: ");
			System.out.println(tempBalance);
			System.out.println("-----------------------------------------------");

			/*
		If the user is not part if the specific list he or she get redirected to this method again
			 */
		}else {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("You do not have acces to this list, please input a different List ID");
			System.out.println("--------------------------------------------------------------------");
			createIndividualBalance(loggedUserIDl);
		}
		return tempBalance;
	}


	public static int readfile1() {
		/*
		this method return how many expenses have been logged.
		 */
		int numLists = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
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
