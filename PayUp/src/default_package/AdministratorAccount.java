package default_package;
import java.io.*;
import java.util.Scanner;

public class AdministratorAccount extends UserAccount {
	static Scanner my_scan = new Scanner(System.in);
	static Scanner my_scanINT = new Scanner(System.in); 

	public void userChoiceList() {
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

	private static void removeList() {
		List.viewList();
	}
}
