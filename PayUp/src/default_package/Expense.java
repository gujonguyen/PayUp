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
	String dummyname;
	
	public Expense(int id, String name, double amount, String date, String whoPaid) {
		expenseId = id;
		expenseName = name;
		this.amount = amount;
		expenseDate = date;
		dummyname = whoPaid;
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
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database" + listId + ".txt", true)));
				wr.println(listId + "\t" + expenseName + "\t" + amount + "\t" + expenseDate + "\t" + userName);
				wr.close();	
			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}
	
	
	public static double splitExpense() {
		int lineNumber = 0;
		String[] c1 = new String[100];
		String[] c2 = new String[100];
		String[] c3 = new String[100];
		String[] c4 = new String[100];
		String[] c5 = new String[100];
		double tempSum = 0;
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
			System.out.println("This file does not exist");
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
