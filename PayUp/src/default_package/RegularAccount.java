package default_package;
import java.util.*;
import java.io.*;

public class RegularAccount extends UserAccount {

	public RegularAccount() {
		int userChoice = RegUserChoice();
		switch (userChoice) {
		case 1:
			createList();
		case 2:
			viewList();
		case 3:
			addExpenseToList();
		case 4:
			viewIndividualBalance();
		case 5:
			viewExpenseHistory();
		case 6:
			settleList();
		case 7:
			deleteList();
		}
	}
	
	public static int RegUserChoice() {
		System.out.println("\tWelcome to PayUp! What would you like to do?");
		System.out.println("");
		System.out.println("****************************************************************");
		System.out.println("");
		System.out.println("(1) create a new list or view an excisting list");
		System.out.println("(2) add expense to a list");
		System.out.println("(3) view the individual balance");
		System.out.println("(4) view the expense history");
		System.out.println("(5) settle a list");
		System.out.println("(6) delete a list");
		System.out.println("");
		System.out.println("****************************************************************");
		System.out.println("");
		System.out.print("\tPlease enter your preferred choice: ");
		return userInput1.nextInt();	// gets the preferred choice from the user
	}

	public static void createOrViewList() {
		List.getUserChoice1();
	}
	public void deleteList() {
		
		deleteList();
	}
	public void settleList() {
		settleList();
	}
	public static void viewIndividualBalance() {
		System.out.println("Your invidivual balance is: " + Expense.splitExpense() + "€");
	}
	public static void viewExpenseHistory() {
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

		for (int i = 0; i < Finalc1.length; i++) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n", Finalc1[i], Finalc2[i], Finalc3[i], Finalc4[i], Finalc5[i]);
		}
	}

	public void writeExpenseHistory() {
		writeExpenseHistory();
	}
	public void addUserToList() {

	}
	public static void addExpenseToList() {

		System.out.println("How many expenses do you want to add? ");
		int counter = userInput1.nextInt();
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
	public void allocateExpensetoUser() {

	}
}
