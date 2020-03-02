package default_package;
import java.io.*;
import java.util.*;

public class List {
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	String listName;
	int listID;
	String date;
	UserAccount[] temp1 = UserAccount.readFile();
	static int fileCount;

	public List(int listIDc, String listNamec) {
		listID = listIDc;
		listName = listNamec;
		//date = datec;
	}

	public static List[] readFile(){
		int lineNumber = 0;
		int [] aaxis = new int[100];
		String [] baxis = new String[100];

		try {
			String sCurrentLine;
			String[] uCurrent = new String [3];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				aaxis[lineNumber] = Integer.parseInt(uCurrent[0]);
				baxis[lineNumber] = uCurrent[1];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		int [] Finalaaxis = new int[lineNumber];
		System.arraycopy(aaxis, 0, Finalaaxis, 0, lineNumber);
		String[] Finalbaxis = new String[lineNumber];
		System.arraycopy(baxis, 0, Finalbaxis, 0, lineNumber);

		List listArray[] = new List[Finalaaxis.length];

		for (int i = 0; i < Finalaaxis.length; i++) {
			listArray[i] = new List(Finalaaxis[i], Finalbaxis[i]);
		}		
		return listArray;
	}

	public static void createList(String currentUser) {
		System.out.println("How do you want to name the list? ");
		String listName = userInput2.nextLine();
		String cUser = currentUser;
		String aUser;

		System.out.println("What user do you want to add to your list? ");
		aUser = userInput2.nextLine();

		File directory = new File("D:\\Program Files\\Eclipse\\workspace\\PayUp");
		fileCount = directory.list().length;

		fileCount = fileCount - 8;

		try { //This creates the individual lists per user to be filled with expenses.

			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_" + fileCount + ".txt",true)));
			wr.println("List Name: " + listName + "\t List Creator: " + cUser + "\t List Member: " + aUser + "/t " + "/t ");
			wr.println("List ID\tExpense Name\tExpense Amount\tExpense Date\tUser Name");
			wr.close();

		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}	

		try { //This creates the database to be used for object creation.

			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",true)));
			wr.println("List ID: " + fileCount + "\t List Name" + listName);
			wr.close();

		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}	

	}

	public static void viewList() {

		int lineNumber = 0;
		String[] c1 = new String[100];
		String[] c2 = new String[100];
		String[] c3 = new String[100];
		String[] c4 = new String[100];
		String[] c5 = new String[100];

		System.out.println("What list do you want to view? ");
		int viewList = userInput2.nextInt();
		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("List_" + viewList + ".txt")); 
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

		System.out.println(Finalc1[0] + "\t" + Finalc2[0] + "\t" + Finalc3[0] + "\t" + Finalc4[0] + "\t" + Finalc5[0]);
		System.out.println("--------------------------------------------------------");
		System.out.println(Finalc1[1] + "\t" + Finalc2[1] + "\t" + Finalc3[1] + "\t" + Finalc4[1] + "\t" + Finalc5[1]);
		for (int i = 2; i < lineNumber; i++) {
			System.out.println(Finalc1[i] + "\t" + Finalc2[i] + "\t\t" + Finalc3[i] + "\t\t" + Finalc4[i] + "\t" + Finalc5[i]);
		}	
	}

	public static void deleteList() {
		System.out.println("Enter the list ID of the list you want to delete.");
		int ListNumber = userInput2.nextInt();
		System.out.println("Are you sure? Deleting lists is permanent and you will no longer be able to add expenses to it? (Y/N)");
		String confirm = userInput1.nextLine();

		try {
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_" + ListNumber + ".txt",false)));
			wr.println("This list is now empty." + "\t " + "\t " + "\t " + "\t ");
			wr.close();

		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}	

		//else
	}
}
