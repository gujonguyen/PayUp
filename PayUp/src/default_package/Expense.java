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
	String dummyName;
	RegularAccount[] temp1 = RegularAccount.createRegulars();
	AdministratorAccount[] temp2 = AdministratorAccount.createAdmins();
	
	
	public Expense(int listIdc, String name, double amountc, String date, String whoPaid, int expId) {
		listId = listIdc;
		expenseName = name;
		amount = amountc;
		expenseDate = date;
		dummyName = whoPaid;
		expenseId = expId;
		
	}
	
	public static Expense[] readFile() {
	int lineNumber = 0;
	int[] listID = new int[100];
	String[] expenseName = new String[100];
	double[] amount = new double[100];
	String[] expenseDate = new String[100];
	String[] dummyName = new String[100];
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
			dummyName[lineNumber] = uCurrent[4];
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
	String[] FinaldummyName = new String[lineNumber];
	System.arraycopy(dummyName, 0, FinaldummyName, 0, lineNumber);
	int[] FinalexpenseID = new int[lineNumber];
	System.arraycopy(expenseID, 0, FinalexpenseID, 0, lineNumber);
	
	Expense expense[] = new Expense[FinallistID.length];
	
	for (int i = 0; i < FinallistID.length; i++) {
		expense[i] = new Expense(FinallistID[i], FinalexpenseName[i], Finalamount[i], FinalexpenseDate[i], FinaldummyName[i], FinalexpenseID[i]);
	}	
		
	return expense;
}


	public static void createExpense() {
		System.out.println("How many expenses do you want to add? ");
		int counter = userInput1.nextInt();
		for (int i = 0; i < counter; i++) {
			System.out.println("Please choose the list ID of the list this expense belongs to: ");
			String listId = userInput2.nextLine();
			System.out.println("Please choose an expense name: ");
			String expenseName = userInput2.nextLine();
			System.out.println("Please choose the expense ammount: ");
			Double amount = userInput3.nextDouble();
			System.out.println("Please choose the expense date: ");
			String expenseDate = userInput2.nextLine();
			System.out.println("Who paid for this expense? ");
			String userName = userInput2.nextLine();
			
			try {
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_" + listId + ".txt", true)));
				wr.println(listId + "\t" + expenseName + "\t" + amount + "\t" + expenseDate + "\t" + userName);
				wr.close();	
			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
			try {
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("Expense_database.txt", true)));
				wr.println(listId + "\t" + expenseName + "\t" + amount + "\t" + expenseDate + "\t" + userName);
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
