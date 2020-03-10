package default_package;
import java.io.*;

public class AdministratorAccount extends UserAccount {
	String userName;
	String password;
	static String role;
	int id;
	String typeOfAccount;
	int userChoice;

	public AdministratorAccount(String nameC, String passwordC, int uID, String roleC ) {
		/*
		This is the main Constructor for the Administrator objects
		 */
		super(nameC, passwordC, uID);
		role = roleC;
	}

	public AdministratorAccount(int userChoicec, int loggedUserIDl) {
		/*
		This Constructor directs the Admin User to the right method based on a passed-on Admin User Choice	
		 */
		super();
		userChoice = userChoicec;
		switch (userChoice) {
		case 0:
			UserAccount.Exit();
			break;
		case 1:
			removeUser();
			break;
		case 2:
			List.deleteList();	
			break;
		}
	}


	public static AdministratorAccount [] createAdmins() {
		/*
		This method creates Admin Objects
		 */
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
				admins [counterAdmins]  = new AdministratorAccount(FinalLocalUserName[i], FinalLocalPassword[i],  FinalLocalID[i], FinalLocalTypeAccount[i]);
				counterAdmins ++;
			}
		}

		return admins;
	}

	public void removeUser() {
		//listing all the users of PayUp
		int NumUser = 0;
		System.out.println("");
		System.out.println("The list of users on PayUp");
		System.out.println("----------------------------------------------------------------");

		int numUser = UserAccount.readfile1();
		
		UserAccount[] tempUserObjects = UserAccount.createAllUsers(); 

		System.out.println("User ID \t\t User Name");
		for(int k = 0; k < numUser; k++) {
			System.out.println(tempUserObjects[k].userID + "\t\t\t" + tempUserObjects[k].userName);
		}

		//removing the user from PayUp
		System.out.println("");
		System.out.println("Enter the User ID you wish to remove admin: ");
		int localRemovedUser = userInput1.nextInt() ;
		System.out.println("Are you sure? Deleting users is permanent and the user will not be able to login anymore (Y/N)");
		String confirm = userInput2.nextLine();

		if(confirm.equals("Y")) {
		try {
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database.txt",false)));
			for (int i = 0; i < NumUser; i++) {
				if (i == localRemovedUser) {
					wr.println("N/A" + "\t" + "N/A" + "\t" + "N/A" + "\t" + tempUserObjects[i].userID);
					System.out.println("You have successfully deleted the User ID:" + localRemovedUser);
				} else {
					wr.println(tempUserObjects[i].userName + "\t" + tempUserObjects[i].password + "\t" + tempUserObjects[i].role + "\t" + tempUserObjects[i].userID);	
				}
			}
			wr.close();

		}catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}
		}
	}
}

