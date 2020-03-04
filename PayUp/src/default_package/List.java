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
	String participants;

	public List(String listNamec, String participantc, int listIDc) {
		listName = listNamec;
		participants = participantc;
		listID = listIDc;
	}

	public static List[] readFile(){
		int lineNumber = 0;
		int [] idAxis = new int[100];
		String [] nameAxis = new String[100];
		String [] participantAxis = new String[100];
		String[] nonsplittedString = new String[100];

		try {
			String sCurrentLine;
			String[] uCurrent = new String [3];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				nameAxis[lineNumber] = uCurrent[0];
				nonsplittedString[lineNumber] = uCurrent[1];
				idAxis[lineNumber] = Integer.parseInt(uCurrent[2]);
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		

		int [] FinalIDAxis = new int[lineNumber];
		System.arraycopy(idAxis, 0, FinalIDAxis, 0, lineNumber);
		String [] FinalNameAxis = new String[lineNumber];
		System.arraycopy(nameAxis, 0, FinalNameAxis, 0, lineNumber);
		String [] FinalParticipantAxis = new String[lineNumber];
		System.arraycopy(participantAxis, 0, FinalParticipantAxis, 0, lineNumber);

		List listArray[] = new List[FinalIDAxis.length];

		for (int i = 0; i < FinalIDAxis.length; i++) {
			listArray[i] = new List(FinalNameAxis[i], FinalParticipantAxis[i], FinalIDAxis[i]);
		}		
		return listArray;
	}

	public void createList(int currentUser) {

		int cUser = currentUser;
		int [] aUser = new int[100];
		Boolean localBoolean = false;
		int lineNumber = 0;

		System.out.println("How do you want to name the list? ");
		String listName = userInput2.nextLine();

		System.out.println("How many users do you want to add?");
		int amountOfUser = userInput1.nextInt();
		aUser [0] = cUser;

		for(int n = 1; n < amountOfUser + 1; n++) {
			System.out.println("What is the userID of the user that you want to add to your list? ");
			aUser[n] = userInput1.nextInt();
			for (int i = 0; i <temp1.length; i++ ) {
				if (temp1[i].userID != aUser[i]) {
					System.out.print("User does not exist");
				} else localBoolean = true;
			}
		}

		if (localBoolean == true) {

			try {
				String sCurrentLine;
				BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
				while ((sCurrentLine = myFile.readLine()) != null) {
					lineNumber++;
				}
				myFile.close(); 
			}catch (IOException e) {
				System.out.println("This file does not exist");
			}

			String [] FinalaUser = new String[amountOfUser];
			System.arraycopy(aUser, 0, FinalaUser, 0, amountOfUser);

			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < FinalaUser.length; i++) {
				sb.append(FinalaUser[i] + ", ");
			}
			String str = sb.toString();


			try { 

				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",true)));
				wr.println("List ID: " + lineNumber + "\t List Name" + listName + "\t List participants" + str);
				wr.close();

			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}	
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

		while(confirm.equals("Y")) {
			try {
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_" + ListNumber + ".txt",false)));
				wr.println("This list is now empty." + "\t " + "\t " + "\t " + "\t ");
				wr.close();

			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}	
		}
		//else
	}
}
