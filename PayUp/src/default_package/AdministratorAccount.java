package default_package;
import java.io.*;
import java.util.Scanner;

public class AdministratorAccount extends UserAccount {
	static Scanner userInput1 = new Scanner(System.in);
	String userName;
	String password;
	static String role;
	int id;
	String typeOfAccount;
	List[] temp1 = List.readFile();
	int userChoice;
	
	public AdministratorAccount(String nameC, String passwordC, String roleC, int uID ) {
			userName = nameC;
			password = passwordC;
			role = roleC;
			id = uID;

	}
	
	public AdministratorAccount(int userChoicec) {
		userChoice = userChoicec;
		switch (userChoice) {
		case 1:
			removeUser();
			break;
		case 2:
			removeList();
			break;
		}
	}
	

	public static AdministratorAccount [] createAdmins() {
		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword= new String[100];
		String[] LocalTypeAccount = new String[100];
		int[] LocalID = new int[100];
		
		AdministratorAccount [] admins = new AdministratorAccount [100];

		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				LocalUserName[NumUser] = uCurrent[0];
				LocalPassword[NumUser] = uCurrent[1];
				LocalTypeAccount[NumUser] = uCurrent[2];
				LocalID[NumUser] = Integer.parseInt(uCurrent[3]);

				NumUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol5");
		}
		
		String[] FinalLocalUserName = new String[NumUser];
		System.arraycopy(LocalUserName, 0, FinalLocalUserName, 0, NumUser);
		String[] FinalLocalPassword = new String[NumUser];
		System.arraycopy(LocalPassword, 0, FinalLocalPassword, 0, NumUser);
		String[] FinalLocalTypeAccount = new String[NumUser];
		System.arraycopy(LocalTypeAccount, 0, FinalLocalTypeAccount, 0, NumUser);
		int[] FinalLocalID = new int[NumUser];
		System.arraycopy(LocalID, 0, FinalLocalID, 0, NumUser);
		int counterAdmins = 0;
		
		for (int i = 0; i < NumUser; i++) {
		if (FinalLocalTypeAccount[i].equals("R")) {
			admins [counterAdmins]  = new AdministratorAccount(FinalLocalUserName[i], FinalLocalPassword[i], FinalLocalTypeAccount[i], FinalLocalID[i]);
			counterAdmins ++;
		}
		}

		return admins;
	}
	
	public static void removeUser() {
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
			System.out.println("The file does not existlol6");
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
			System.out.println(Finalaaxis[k]);
		}

		System.out.println("Which user do you wish to remove?");
		String localRemovedUser = userInput1.nextLine() ;

		for (int v = 0; 0 < lineNumber; v++) {
			if (localRemovedUser.equals(Finalaaxis[v])) {
				Finalaaxis[v] = "";
				Finalbaxis[v] = "";
				Finalcaxis[v] = "";
				Finaldaxis[v] = "";
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

	public static void removeList() {
		List.viewList();
	}
}
