package default_package;
import java.io.*;
import java.util.*;

public class Expense {
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	static Scanner userInput3 = new Scanner(System.in); // for double
	String expenseName;
	String expenseDate;
	double amount;
	int expenseId;
	int listId;
	RegularAccount[] temp1 = RegularAccount.createRegulars();
	AdministratorAccount[] temp2 = AdministratorAccount.createAdmins();
	UserAccount[] temp3 = UserAccount.createAllUsers();
	

	static int fileCount;
	String participants;
	int userChoice;
	int loggedUserID;


	public Expense(int listIdc, String name, double amountc, String date, String participants, int expId) {
		listId = listIdc;
		expenseName = name;
		amount = amountc;
		expenseDate = date;
		this.participants = participants;
		expenseId = expId;
	}

	public Expense(int userChoicec, int loggedUserIDc) {
		super();
		userChoice = userChoicec;
		loggedUserID = loggedUserIDc;

		switch (userChoice) {
		case 4:
			viewIndividualBalance(loggedUserIDc);
			break;
		case 5:
			//settleList();
			break;
		case 6:
			addExpenseToList(loggedUserIDc);
			break;
		case 7:
			//viewExpenseHistory();
			break;
		case 8:
			//writeExpenseHistory();
			break;
		case 9:
			//logout();
			break;
		}
	}

	public static Expense[] readFile() {
		int lineNumber = 0;
		int[] listID = new int[100];
		String[] expenseName = new String[100];
		double[] amount = new double[100];
		String[] expenseDate = new String[100];
		String[] userArray = new String[100];
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
				userArray[lineNumber] = uCurrent[4];
				expenseID[lineNumber] = Integer.parseInt(uCurrent[5]);
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
		String[] FinalUserArray = new String[lineNumber];
		System.arraycopy(userArray, 0, FinalUserArray, 0, lineNumber);
		int[] FinalexpenseID = new int[lineNumber];
		System.arraycopy(expenseID, 0, FinalexpenseID, 0, lineNumber);

		Expense expense[] = new Expense[FinallistID.length];

		for (int i = 0; i < FinallistID.length; i++) {
			expense[i] = new Expense(FinallistID[i], FinalexpenseName[i], Finalamount[i], FinalexpenseDate[i], FinalUserArray[i], FinalexpenseID[i]);
		}	

		return expense;
	}

	public void viewIndividualBalance(int loggedUserIDc) {
		
		

		int loggedUserIDl = loggedUserIDc;
		Expense [] allExpenses = readFile();
		Expense [] myExpenses = new Expense [100]; //finds only the expenses the logged in user is a part of
		String [] temp = new String [allExpenses.length];
		
		
		for (int i = 0; i < allExpenses.length; i++) {
			temp = allExpenses[i].participants.split (",");
		}
		

		int [] participants = new int [allExpenses.length];
		double balance;

		for (int i = 0; i < allExpenses.length; i++) {

		}

	}


	public void addExpenseToList(int loggedUserIDc) {

		List [] temp4 = List.readFile();
		int amountOfLists = List.readfile1();
		
		String sCurrentLine;
		String[] uCurrent = new String [4];
		String[] participantsInList = new String[100];
		
		
		
		sCurrentLine = temp4[0].participants;
		uCurrent = sCurrentLine.split(",");
		
		participantsInList[0] = uCurrent[0];
		
		System.out.println(participantsInList[0]);
		
		int loggedUserIDl = loggedUserIDc;


		int [] aUser = new int[100];
		Boolean localBoolean = false;
		int lineNumber = 0;

		System.out.println("What is the list ID to which this expense belongs?");
		int listId = userInput1.nextInt();

		System.out.println("What is the name of this expense?");
		String expenseName = userInput2.nextLine();

		System.out.println("What is the amount of this expense?");
		Double amount = userInput3.nextDouble();

		System.out.println("What is the date of this expense?");
		String date = userInput2.nextLine();

		System.out.println("Among how many users do you want to devide the amount (not counting yourself)?");
		int amountOfUser = userInput1.nextInt();
		aUser [0] = loggedUserIDl;

		for(int n = 1; n < amountOfUser + 1; n++) {
			System.out.println("What is the userID of the user that you want to add to your list? ");
			aUser[n] = userInput1.nextInt();
		}

		int length = UserAccount.readfile1(); // This returns the amount of registered user for the for-loop below

		for (int j = 1; j < amountOfUser + 1 ; j++) {
			for (int i = 0; i < length; i++ ) {
				if (aUser[j] == temp3[i].userID) {
					localBoolean = true;
				}else {

				}
			}
		}

		if (localBoolean == true) {
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

			String str = "";
			for (int i = 0; i < amountOfUser + 1; i++) {
				str = str + aUser[i] + ",";	
			}				

			try { 

				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("Expense_database.txt",true)));
				wr.println(listId + "\t" + expenseName + "\t" + amount + "\t" + date + "\t" + str + "\t" + lineNumber);
				wr.close();

			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}


	//	public static double splitExpense() {
	//		double tempSum = 0;
	//		
	//		// correct reading code shoud come here
	//		
	//		// calcualtion colde below
	//		double[] amount = new double[Finalc3.length];
	//		for (int i = 0; i < Finalc3.length; i++) {
	//			amount[i] = Double.parseDouble(Finalc3[i]);
	//		}
	//
	//		for (int i = 0; i < Finalc1.length; i++) {
	//			if (UserAccount.userName.equals(Finalc5[i])) {
	//				tempSum += amount [i]/2;
	//			}
	//			else {
	//				tempSum -= amount [i]/2;
	//			}
	//		}
	//		return tempSum;
	//	}
}
