package default_package;
import java.util.*;
import java.io.*;

public class RegularAccount extends UserAccount{
	static Scanner my_scanINT = new Scanner(System.in); 
	static Scanner userInput2 = new Scanner(System.in); //for strings
	static Scanner userInput3 = new Scanner(System.in); //for doubles

	public void userChoiceList() {
		int userChoice = getUserChoice();
		switch (userChoice) {
		case 1:
			createNewList();
			break;
		case 2:
			viewList();
			break;
		case 3:
			viewIndividualBalance();
			break;
		case 4:
			deleteList();
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
			break;
	}
}
	
	public static void logout() {
		System.out.println("You are successfully logged out");

		
	}

	public static int getUserChoice() {
		System.out.println("--------------------------------------------------------");//user interface for regular users
		System.out.println("You are logged in as a Regular user!");
		System.out.println("What do you want to do?");
		System.out.println("--------------------------------------------------------");
		System.out.println("");
		System.out.println("(1) Create a new list");
		System.out.println("(2) View a list");
		System.out.println("(3) View individual balance");
		System.out.println("(4) Delete a list");
		System.out.println("(5) Settle a list");
		System.out.println("(6) Add Expense To List");
		System.out.println("(7) View Expense History");
		System.out.println("(8) Write Expense History");
		System.out.println("(0) Logout");
		System.out.println("--------------------------------------------------------");
		System.out.print("You want to: ");
		return my_scanINT.nextInt();	//getting user choice 
	}

	public static void viewIndividualBalance() {
		System.out.println("Your invidivual balance is: " + Expense.splitExpense() + "€");
	}
	
	public static void createNewList() {
		List.createList();
		//you should be able to add user list to viewList method
				//public void addUserToList() { 
		
				//same for allocating expense to user
				//public void allocateExpensetoUser() {
	}
	
	public static void viewList() {
		viewList();
		//you should be able to add user list to viewList method
		//public void addUserToList() { 

		//same for allocating expense to user
		//public void allocateExpensetoUser() {
	}

	public static void deleteList() {
		deleteList();
	}
	public static void settleList() {
		settleList();
	}
	
	public static void addExpenseToList() {
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

	public static void writeExpenseHistory() {
		writeExpenseHistory();
	}
}


