package default_package;
import java.io.*;
import java.util.Scanner;

public class AdministratorAccount extends UserAccount {
	static Scanner my_scan = new Scanner(System.in); 
	static Scanner my_scanINT = new Scanner(System.in); 

	public void AdministratorInterface() {
		int userChoice = getUserChoice();
		switch (userChoice) {
		case 1:
			removeUser();
			break;
		case 2:
			removeList();
			break;
		case 3:
			Logout();
			break;
		default:
			System.out.print("Please enter a valid choice.");
		}
	}


	public static void Logout() {
		System.out.println("You are successfully logged out");
	}

	 static void removeUser() {
		int NumUser = 0;
		boolean localBoolean = false;
		String[] LocalUserName = new String[100];
		String[] LocalPassword = new String[100];
		String[] LocalTypeAccount = new String[100];
		String[] LocalID = new String[100];
		System.out.println("");
		System.out.println("The list of users on PayUp");
		System.out.println("----------------------------------------------------------------");

		try {
			String sCurrentLine;
			String[] uCurrentLine = new String[4];
			BufferedReader br = new BufferedReader (new FileReader ("User_database.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				uCurrentLine = sCurrentLine.split("\t");
				LocalUserName[NumUser] = (uCurrentLine[0]);
				LocalPassword [NumUser] = (uCurrentLine[1]);
				LocalTypeAccount[NumUser] = (uCurrentLine[2]);
				LocalID[NumUser] = (uCurrentLine[3]);
				NumUser++;
			}

			br.close();
		} catch (IOException e) {
			System.out.println("The file does not exist!");
		}

		String[] FinalLocalUserName = new String[NumUser];
		System.arraycopy(LocalUserName, 0, FinalLocalUserName, 0, NumUser);
		String[] FinalLocalPassword = new String[NumUser];
		System.arraycopy(LocalPassword, 0, FinalLocalPassword, 0, NumUser);
		String[] FinalLocalTypeAccount = new String[NumUser];
		System.arraycopy(LocalTypeAccount, 0, FinalLocalTypeAccount, 0, NumUser);
		String[] FinalLocalID = new String[NumUser];
		System.arraycopy(LocalID, 0, FinalLocalID, 0, NumUser);

		for(int k = 0; k < NumUser; k++) {
			System.out.println(LocalUserName[k]);
		}

		System.out.println("Which user do you wish to remove?");
		String LocalRemovedUser = my_scan.nextLine();

		for (int v = 0; 0 < NumUser; v++) {
			if (LocalRemovedUser.equals(FinalLocalUserName[v])) {
				FinalLocalUserName[v] = "Deleted user";
				FinalLocalPassword[v] = "N/A";
				FinalLocalTypeAccount[v] = "N/A";
				FinalLocalID[v] = "N/A";
				localBoolean = true;
			}
		}

		while (localBoolean = true) {
			try { 
				PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database",false)));
				for (int b = 0; b < NumUser; b++) {
					wr.println(FinalLocalUserName[b] + "\t" + FinalLocalPassword[b] + "\t" + FinalLocalTypeAccount[b] + "\t" + FinalLocalID[b]);	
				}
				wr.close();
			}catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		}
	}

	static void removeList() {
		List.viewList();
	}
}
