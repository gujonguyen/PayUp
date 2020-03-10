package default_package;
import java.io.*;

public class AdministratorAccount extends UserAccount {
	String userName;
	String password;
	static String role;
	int id;
	String typeOfAccount;
	int userChoice;

	public AdministratorAccount(String nameC, String passwordC, int userID, String roleC ) {
		/*
		This is the main Constructor for the Administrator objects
		 */
		super(nameC, passwordC, userID);
		role = roleC;
	}

	public AdministratorAccount(int userChoicec, int loggedUserIDLocal) {
		/*
		This Constructor directs the Admin User to the right method based on a passed-on Admin User Choice	
		 */
		super();
		userChoice = userChoicec;
		switch (userChoice) {
		case 0:
			UserAccount.exit();
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
		int numberOfUsers = 0;
		String[] localUserName = new String[100];
		String[] localPassword= new String[100];
		String[] localTypeAccount = new String[100];
		int[] localID = new int[100];

		AdministratorAccount [] admins = new AdministratorAccount [100];

		try {
			String currentLine;
			String[] currentUser = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((currentLine = myFile.readLine()) != null) {
				currentUser = currentLine.split("\t");

				localUserName[numberOfUsers] = currentUser[0];
				localPassword[numberOfUsers] = currentUser[1];
				localTypeAccount[numberOfUsers] = currentUser[2];
				localID[numberOfUsers] = Integer.parseInt(currentUser[3]);

				numberOfUsers++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol5");
		}

		String[] finalLocalUserName = new String[numberOfUsers];
		System.arraycopy(localUserName, 0, finalLocalUserName, 0, numberOfUsers);
		String[] finalLocalPassword = new String[numberOfUsers];
		System.arraycopy(localPassword, 0, finalLocalPassword, 0, numberOfUsers);
		String[] finalLocalTypeAccount = new String[numberOfUsers];
		System.arraycopy(localTypeAccount, 0, finalLocalTypeAccount, 0, numberOfUsers);
		int[] finalLocalID = new int[numberOfUsers];
		System.arraycopy(localID, 0, finalLocalID, 0, numberOfUsers);
		int counterAdmins = 0;

		for (int i = 0; i < numberOfUsers; i++) {
			if (finalLocalTypeAccount[i].equals("R")) {
				admins [counterAdmins]  = new AdministratorAccount(finalLocalUserName[i], finalLocalPassword[i],  finalLocalID[i], finalLocalTypeAccount[i]);
				counterAdmins ++;
			}
		}

		return admins;
	}

	public void removeUser() {
		/* 
		 * This method removes a certain user from Pay-Up
		 */		
		int numUser = 0;
		String[] localUserName = new String[100];
		String[] localPassword= new String[100];
		String[] localTypeAccount = new String[100];
		int[] localID = new int[100];
		
		System.out.println("");
		System.out.println("The list of users on PayUp");
		System.out.println("--------------------------------------------------------------------");

		int numUser = UserAccount.readfile1();
		
		UserAccount[] tempUserObjects = UserAccount.createAllUsers(); 

		System.out.println("User ID \t\t User Name");
		for(int k = 0; k < numUser; k++) {
			System.out.println(tempUserObjects[k].userID + "\t\t\t" + tempUserObjects[k].userName);
		}
		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				localUserName[numUser] = uCurrent[0];
				localPassword[numUser] = uCurrent[1];
				localTypeAccount[numUser] = uCurrent[2];
				localID[numUser] = Integer.parseInt(uCurrent[3]);

				numUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		String[] finalLocalUserName = new String[numUser];
		System.arraycopy(localUserName, 0, finalLocalUserName, 0, numUser);
		String[] finalLocalPassword = new String[numUser];
		System.arraycopy(localPassword, 0, finalLocalPassword, 0, numUser);
		String[] finalLocalTypeAccount = new String[numUser];
		System.arraycopy(localTypeAccount, 0, finalLocalTypeAccount, 0, numUser);
		int[] finalLocalID = new int[numUser];
		System.arraycopy(localID, 0, finalLocalID, 0, numUser);

		//removing the user from PayUp
		System.out.println("");
		System.out.println("Enter the User ID you wish to remove: ");
		int localRemovedUser = InterfaceClass.getAnInteger();
		
		
		if (localRemovedUser > numUser) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("This User does not exist");
		System.out.println("You will be redirected to the User Interface");
		System.out.println("--------------------------------------------------------------------");
		new InterfaceClass();
		}else {
		System.out.println("Are you sure? Deleting users is permanent and the user will not be able to login anymore (Y/N)");
		String confirm = userInput2.nextLine();
		System.out.println(confirm);
		
		if(confirm.equals("Y")) {
		try {
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database.txt",false)));
			for (int i = 0; i < numUser; i++) {
				if (i == localRemovedUser) {
					wr.println("N/A" + "\t" + "N/A" + "\t" + "N/A" + "\t" + finalLocalID[i]);
					System.out.println("You have successfully deleted the User ID:" + localRemovedUser);
				} else {
					wr.println(finalLocalUserName[i] + "\t" + finalLocalPassword[i] + "\t" +  finalLocalTypeAccount[i] + "\t" + finalLocalID[i]);	
					break;	
				}
				
		}
		wr.close();
		}catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}
		}else {
			System.out.println("You will be redirected to the User Interface");
			new InterfaceClass();
		}
	}
	}
}
