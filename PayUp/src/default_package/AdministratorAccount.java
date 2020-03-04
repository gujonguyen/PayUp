package default_package;
import java.io.*;
import java.util.Scanner;

public class AdministratorAccount extends UserAccount {
	static Scanner userInput1 = new Scanner(System.in);

	public AdministratorAccount(){
		int userChoice = AdminInterface();
		switch (userChoice) {
		case 1:
			removeUser();
			break;
		case 2:
			removeList();
			break;
		case 3:
			UserAccount.Exit();
			break;
		default:
			System.out.print("Please enter a valid choice.");
		}
	}

	static int AdminInterface() {

			System.out.println("--------------------------------------------------------");
			System.out.println("Welcome Admin of PayUp!");
			System.out.println("You are logged in to the Administrator Account!");
			System.out.println("--------------------------------------------------------");
			System.out.println("What do you wish to do?");
			System.out.println("(1) Remove a User from PayUp");
			System.out.println("(2) Remove List");
			System.out.println("(3) Logout");
			System.out.println("--------------------------------------------------------");
			System.out.print("Please enter your choice: ");
			return  my_scanINT.nextInt();
	}	

	private void removeUser() {
		int lineNumber = 0;
		boolean localBoolean = false;
		String[] aAxis = new String[100];
		String[] bAxis = new String[100];
		String[] cAxis = new String[100];
		String[] dAxis = new String[100];
		System.out.println("");
		System.out.println("The list of users on PayUp");
		System.out.println("----------------------------------------------------------------");

		try {
			String sCurrentLine;
			String[] uCurrentLine = new String[4];
			BufferedReader br = new BufferedReader (new FileReader ("User_database.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				uCurrentLine = sCurrentLine.split("\t");
				aAxis[lineNumber] = (uCurrentLine[0]);
				bAxis[lineNumber] = (uCurrentLine[1]);
				cAxis[lineNumber] = (uCurrentLine[2]);
				dAxis[lineNumber] = (uCurrentLine[3]);
				lineNumber++;
			}

			br.close();
		} catch (IOException e) {
			System.out.println("The file does not exist!");
		}

		String[] Finalaaxis = new String[lineNumber];
		System.arraycopy(aAxis, 0, Finalaaxis, 0, lineNumber);
		String[] Finalbaxis = new String[lineNumber];
		System.arraycopy(bAxis, 0, Finalbaxis, 0, lineNumber);
		String[] Finalcaxis = new String[lineNumber];
		System.arraycopy(cAxis, 0, Finalcaxis, 0, lineNumber);
		String[] Finaldaxis = new String[lineNumber];
		System.arraycopy(dAxis, 0, Finaldaxis, 0, lineNumber);

		for(int k = 0; k < lineNumber; k++) {
			System.out.println(aAxis[k]);
		}

		System.out.println("Which user do you wish to remove?");
		String localRemovedUser = userInput1.nextLine() ;

		for (int v = 0; 0 < lineNumber; v++) {
			if (localRemovedUser.equals(Finalaaxis[v])) {
				Finalaaxis[v] = "Deleted user";
				Finalbaxis[v] = "N/A";
				Finalcaxis[v] = "N/A";
				Finaldaxis[v] = "N/A";
				localBoolean = true;
			}
		}

		while (localBoolean = true) {
			try { 
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database",false)));
				for (int b = 0; b < lineNumber; b++) {
					wr.println(Finalaaxis[b] + "\t" + Finalbaxis[b] + "\t" + Finalcaxis[b] + "\t" + Finaldaxis[b]);	
				}
				wr.close();
			}catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}

	private static void removeList() {
		List.viewList();
	}
}
