package default_package;
import java.util.*;
import java.io.*;

public class RegularAccount extends UserAccount{
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	static Scanner userInput3 = new Scanner(System.in); // for double
	String userName;
	String password;
	static String role;
	int id;
	String typeOfaccount;
	int userChoice;
	int loggedUserID;

	

	public RegularAccount(String nameC, String passwordC, int uID, String roleC ) {
		super(nameC, passwordC, uID);
		role = roleC;

	}
	
	public RegularAccount(int userChoicec, int loggedUserIDc) {
	super();
	userChoice = userChoicec;
	loggedUserID = loggedUserIDc;
	
	switch (userChoice) {
	case 4:
		viewIndividualBalance();
		break;
	case 5:
		settleList();
		break;
	case 6:
		addExpenseToList();
		break;
	case 7:
		viewExpenseHistory();
		break;
	case 8:
		writeExpenseHistory();
		break;
	case 0:
		UserAccount.Logout();
		break;
		
	default: 
		System.out.println("--------------------------------------------------------");
		System.out.println("------------------------CAUTION!------------------------");
		System.out.println("          Please only enter a number between 0-8!        "); 
		System.out.println("------------------------CAUTION!------------------------");
		new InterfaceClass();

	}
	}

	public static RegularAccount[] createRegulars() {

		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword= new String[100];
		String[] LocalTypeAccount = new String[100];
		int[] LocalID = new int[100];
		
		RegularAccount [] regulars = new RegularAccount [100];

		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				LocalUserName[NumUser] = uCurrent[0];
				LocalPassword[NumUser] = uCurrent[1];
				LocalTypeAccount[NumUser] = uCurrent[2];
				LocalID[NumUser] = Integer.parseInt(uCurrent[3]);

				NumUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol3");
		}
		
		String[] FinalLocalUserName = new String[NumUser];
		System.arraycopy(LocalUserName, 0, FinalLocalUserName, 0, NumUser);
		String[] FinalLocalPassword = new String[NumUser];
		System.arraycopy(LocalPassword, 0, FinalLocalPassword, 0, NumUser);
		String[] FinalLocalTypeAccount = new String[NumUser];
		System.arraycopy(LocalTypeAccount, 0, FinalLocalTypeAccount, 0, NumUser);
		int[] FinalLocalID = new int[NumUser];
		System.arraycopy(LocalID, 0, FinalLocalID, 0, NumUser);
		int counterRegulars = 0;
		for (int i = 0; i < NumUser; i++) {
		if (FinalLocalTypeAccount[i].equals("R")) {
			regulars [counterRegulars]  = new RegularAccount(FinalLocalUserName[i], FinalLocalPassword[i], FinalLocalID[i], FinalLocalTypeAccount[i] );
			counterRegulars ++;
		}
		}

		return regulars;
	}

		

	public void viewIndividualBalance() {
		System.out.println("Your invidivual balance is: " + Expense.splitExpense() + "€");
	}


	public static void settleList() {
		settleList();
	}

	public void addExpenseToList() {
		System.out.println("How many expenses do you want to add? ");
		int counter = my_scanINT.nextInt();
		for (int i = 0; i < counter; i++) {
			System.out.println("Please choose the list ID of the list this expense belongs to: ");
			List.listId = userInput1.nextInt();
			System.out.println("Please choose an expense name: ");
			Expense.expenseName = userInput2.nextLine();
			System.out.println("Please choose the expense ammount: ");
			Expense.amount = userInput3.nextDouble();
			System.out.println("Please choose the expense date: ");
			Expense.expenseDate = userInput2.nextLine();
			System.out.println("Who paid for this expense? ");
			UserAccount.userName = userInput2.nextLine();

			try {
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("Expense_database.txt", true)));
				wr.println(List.listId + "\t" + Expense.expenseName + "\t" + Expense.amount + "\t" + Expense.expenseDate + "\t" + UserAccount.userName);
				wr.close();	
			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}

	public void viewExpenseHistory() {
		int lineNumber = 0;
		String[] c1 = new String[100];
		String[] c2 = new String[100];
		String[] c3 = new String[100];
		String[] c4 = new String[100];
		String[] c5 = new String[100];
		System.out.println("List ID\tExpense Name\tAmount\tDate\tWho paid?");
		try {
			String sCurrentLine;
			String[] uCurrent = new String [5];
			BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				c1[lineNumber] = uCurrent[0];
				c2[lineNumber] = uCurrent[1];
				c3[lineNumber] = uCurrent[2];
				c4[lineNumber] = uCurrent[3];
				c5[lineNumber] = uCurrent[4];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol4");
		}
		String[] Finalc1 = new String[lineNumber];
		System.arraycopy(c1, 0, Finalc1, 0, lineNumber);
		String[] Finalc2 = new String[lineNumber];
		System.arraycopy(c2, 0, Finalc2, 0, lineNumber);
		String[] Finalc3 = new String[lineNumber];
		System.arraycopy(c3, 0, Finalc3, 0, lineNumber);
		String[] Finalc4 = new String[lineNumber];
		System.arraycopy(c4, 0, Finalc4, 0, lineNumber);
		String[] Finalc5 = new String[lineNumber];
		System.arraycopy(c5, 0, Finalc5, 0, lineNumber);

		for (int i = 0; i < Finalc1.length; i++) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n", Finalc1[i], Finalc2[i], Finalc3[i], Finalc4[i], Finalc5[i]);
		}
	}

	public void writeExpenseHistory() {
		writeExpenseHistory();
	}
}
