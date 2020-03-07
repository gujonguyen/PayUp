package default_package;
import java.io.*;
import java.util.*;

public class List {
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	static Scanner userInput3 = new Scanner(System.in); // for double
	String listName;
	int listID;
	String date;
	RegularAccount[] temp1 = RegularAccount.createRegulars();
	AdministratorAccount[] temp2 = AdministratorAccount.createAdmins();
	UserAccount[] temp3 = UserAccount.createAllUsers();

	static int fileCount;
	String participants;
	int userChoice;
	int loggedUserID;

	public List(String listNamec, String participant, int listIDc) {
		listName = listNamec;
		participants = participant;
		listID = listIDc;
	}

	public List(int userChoicec, int loggedUserIDc){
		int loggedUserIDl = loggedUserIDc;
		userChoice = userChoicec;
		switch (userChoice) {
		case 1:
			createList(loggedUserIDl);
			break;
		case 2:
			viewList();
			break;
		case 3:
			deleteList();
			break;
		}
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
			System.out.println("This file does not existlol7");
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

	public void createList(int loggedUserIDc) {

		int loggedUserIDl = loggedUserIDc;
		

		int [] aUser = new int[100];
		Boolean localBoolean = false;
		int lineNumber = 0;

		System.out.println("How do you want to name the list? ");
		String listName = userInput2.nextLine();

		System.out.println("How many users do you want to add?");
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
				String sCurrentLine;
				BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
				while ((sCurrentLine = myFile.readLine()) != null) {
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

				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",true)));
				wr.println(listName + "\t" + str + "\t" + lineNumber);
				wr.close();
				

		} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}

	public static void viewList() {

		int lineNumber = 0;
		String [] c1 = new String[100];
		String[] c2 = new String[100];
		int [] c3 = new int[100];

		System.out.println("What is the list ID of the list that you want to view? ");
		int viewList = userInput1.nextInt();
		
		
		try {
			String sCurrentLine;
			String[] uCurrent = new String [3];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				c1[lineNumber] = uCurrent[0];
				c2[lineNumber] = uCurrent[1];
				c3[lineNumber] = Integer.parseInt(uCurrent[2]);
				lineNumber++;
			}
			myFile.close(); 	
		}catch (IOException e) {
			System.out.println("This file does not existlol9");
		}

		String[] Finalc1 = new String[lineNumber];
		System.arraycopy(c1, 0, Finalc1, 0, lineNumber);
		String[] Finalc2 = new String[lineNumber];
		System.arraycopy(c2, 0, Finalc2, 0, lineNumber);
		int [] Finalc3 = new int[lineNumber];
		System.arraycopy(c3, 0, Finalc3, 0, lineNumber);


		System.out.println("List Name: \t Particpants: \t List ID: \t \t ");
		for (int i = 0; i < lineNumber; i++) {
			if (viewList == Finalc3[i]) {
				System.out.println(Finalc1[i] + "\t\t\t" + Finalc2[i] + "\t\t" + Finalc3[i]);
				break;
			}
		}	
	}
	public static void deleteList() {
		int lineNumber = 0;
		int [] c1 = new int[100];
		String[] c2 = new String[100];
		String[] c3 = new String[100];

		System.out.println("Enter the list ID of the list you want to delete.");
		int viewList = userInput2.nextInt();
		System.out.println("Are you sure? Deleting lists is permanent and you will no longer be able to add expenses to it? (Y/N)");
		String confirm = userInput1.nextLine();

		try {
			String sCurrentLine;
			String[] uCurrent = new String [3];
			BufferedReader myFile = new BufferedReader (new FileReader("List_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				c1[lineNumber] = Integer.parseInt(uCurrent[0]);
				c2[lineNumber] = uCurrent[1];
				c3[lineNumber] = uCurrent[2];

				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol10");
		}

		int[] Finalc1 = new int[lineNumber];
		System.arraycopy(c1, 0, Finalc1, 0, lineNumber);
		String[] Finalc2 = new String[lineNumber];
		System.arraycopy(c2, 0, Finalc2, 0, lineNumber);
		String[] Finalc3 = new String[lineNumber];
		System.arraycopy(c3, 0, Finalc3, 0, lineNumber);

		while(confirm.equals("Y")) {
			try {
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("List_database.txt",false)));
				for (int i = 0; i < lineNumber; i++) {
					if(i == viewList) continue;
					wr.print(Finalc1[i] + "\t " + Finalc2[i]+ "\t " + Finalc3[i]);
				}
				wr.close();
			}catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}	
		}
	}
}
