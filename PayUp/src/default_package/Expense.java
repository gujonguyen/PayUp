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
	List[] temp1 = List.readFile();
	int expenseId;
	int listId;
	String whoPaid;
	int noOfExpenses;
	private String sCurrentLine;

	public Expense(int listId, String name, double amount, String date, String whoPaid,int expId) {
		this.listId = listId;
		expenseName = name;
		this.amount = amount;
		expenseDate = date;
		this.whoPaid = whoPaid;
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

	public void createExpense(int currentUser) {
		noOfExpenses = readfile1();
		int cUser = currentUser;
		int [] aUser = new int[100];
		Boolean localBoolean = false;
		int lineNumber = 0;
		System.out.println("Please enter the list ID to which this expense belongs?");
		int listId = userInput1.nextInt();
		System.out.println("How do you want to name the expense? ");
		String expenseName = userInput2.nextLine();
		System.out.println("What is the amount of this expense?");
		double amount = userInput1.nextInt();
		System.out.println("What is the date of the expense? ");
		String expenseDate = userInput2.nextLine();
		System.out.println("Between how many additional people do you want to split this expense (not counting yourself?");
		int numberOfParticipants = userInput1.nextInt();

		for(int n = 0; n < numberOfParticipants; n++) {
			System.out.println("\"Please enter the user IDs of the people you want to split this expense between (not including yourself):");
			aUser[n] = userInput1.nextInt();
			for (int i = 0; i <temp.length; i++ ) {
				if (temp[i].userID != aUser[i]) {
					System.out.print("User does not exist");
				}
			}
		}

			try { 
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("Expense_database.txt",true)));
				wr.println(listId + "\t" + expenseName + "\t" + amount + "\t" + expenseDate + "\t" + cUser + "\t" + aUser + "\t" + noOfExpenses);
				wr.close();
			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}	
		}

	private int readfile1() {
		int NumExpense = 0;
		try {
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((setsCurrentLine(myFile.readLine())) != null) {
				NumExpense++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return NumExpense;
	}

	public String getsCurrentLine() {
		return sCurrentLine;
	}

	public String setsCurrentLine(String sCurrentLine) {
		this.sCurrentLine = sCurrentLine;
		return sCurrentLine;
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
