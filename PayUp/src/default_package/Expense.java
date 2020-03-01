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
	UserAccount[] temp = UserAccount.readFile();
	int expenseId;
	int listId;
	String dummyname;
	
	public Expense(int listId, String name, double amount, String date, String whoPaid, int expId) {
		this.listId = listId;
		expenseName = name;
		this.amount = amount;
		expenseDate = date;
		dummyname = whoPaid;
		expenseId = expId;
		
	}
	
	public static Expense[] readFile() {
	int lineNumber = 0;
	int[] c1 = new int[100];
	String[] c2 = new String[100];
	double[] c3 = new double[100];
	String[] c4 = new String[100];
	String[] c5 = new String[100];
	int[] c6 = new int[100];

	try {
		String sCurrentLine;
		String[] uCurrent = new String [5];
		BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
		while ((sCurrentLine = myFile.readLine()) != null) {
			uCurrent = sCurrentLine.split("\t");

			c1[lineNumber] = Integer.parseInt(uCurrent[0]);
			c2[lineNumber] = uCurrent[1];
			c3[lineNumber] = Double.parseDouble(uCurrent[2]);
			c4[lineNumber] = uCurrent[3];
			c5[lineNumber] = uCurrent[4];
			c6[lineNumber] = Integer.parseInt(uCurrent[5]);
			lineNumber++;
		}
		myFile.close(); 
	}catch (IOException e) {
		System.out.println("This file does not exist");
	}

	int[] Finalc1 = new int[lineNumber];
	System.arraycopy(c1, 0, Finalc1, 0, lineNumber);
	String[] Finalc2 = new String[lineNumber];
	System.arraycopy(c2, 0, Finalc2, 0, lineNumber);
	double[] Finalc3 = new double[lineNumber];
	System.arraycopy(c3, 0, Finalc3, 0, lineNumber);
	String[] Finalc4 = new String[lineNumber];
	System.arraycopy(c4, 0, Finalc4, 0, lineNumber);
	String[] Finalc5 = new String[lineNumber];
	System.arraycopy(c5, 0, Finalc5, 0, lineNumber);
	int[] Finalc6 = new int[lineNumber];
	System.arraycopy(c6, 0, Finalc6, 0, lineNumber);
	
	Expense expense[] = new Expense[Finalc1.length];
	
	for (int i = 0; i < Finalc1.length; i++) {
		expense[i] = new Expense(Finalc1[i], Finalc2[i], Finalc3[i], Finalc4[i], Finalc5[i], Finalc6[i]);
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
	
	
	public static double splitExpense() {
		double tempSum = 0;
		
		// correct reading code shoud come here
		
		// calcualtion colde below
		double[] amount = new double[Finalc3.length];
		for (int i = 0; i < Finalc3.length; i++) {
			amount[i] = Double.parseDouble(Finalc3[i]);
		}

		for (int i = 0; i < Finalc1.length; i++) {
			if (UserAccount.userName.equals(Finalc5[i])) {
				tempSum += amount [i]/2;
			}
			else {
				tempSum -= amount [i]/2;
			}
		}
		return tempSum;
	}
}
