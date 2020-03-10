package default_package;
import java.io.*;

public class RegularAccount extends UserAccount{	//This is a subclass of the superclass UserAccount
	String userName;
	String password;
	static String role;
	int id;
	String typeOfaccount;
	int userChoice;
	int loggedUserID;


	public RegularAccount(String nameC, String passwordC, int uID, String roleC ) {
		super(nameC, passwordC, uID);
		role = roleC;

	}

	public static RegularAccount[] createRegulars() {	
		/*
		 * // This method reads the user database and searches for regular users
		 * specifically // This method then creates an array of regular user objects
		 * containing their user name, // password, type off account, and user ID // The
		 * regular account details are stored into separate arrays and then stored into
		 * // one regulars array containing all regular accounts, which is what this
		 * method returns
		 */

		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword= new String[100];
		String[] LocalTypeAccount = new String[100];
		int[] LocalID = new int[100];
		
		RegularAccount [] regulars = new RegularAccount [100];

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
			System.out.println("This file does not existlol3");
		}
		
		String[] FinalLocalUserName = new String[NumUser];
		System.arraycopy(LocalUserName, 0, FinalLocalUserName, 0, NumUser);
		String[] FinalLocalPassword = new String[NumUser];
		System.arraycopy(LocalPassword, 0, FinalLocalPassword, 0, NumUser);
		String[] FinalLocalTypeAccount = new String[NumUser];
		System.arraycopy(LocalTypeAccount, 0, FinalLocalTypeAccount, 0, NumUser);
		int[] FinalLocalID = new int[NumUser];
		System.arraycopy(LocalID, 0, FinalLocalID, 0, NumUser);
		int counterRegulars = 0;
		for (int i = 0; i < NumUser; i++) {
		if (FinalLocalTypeAccount[i].equals("R")) {
			regulars [counterRegulars]  = new RegularAccount(FinalLocalUserName[i], FinalLocalPassword[i], FinalLocalID[i], FinalLocalTypeAccount[i] );
			counterRegulars ++;
		}
		}

		return regulars;
	}
}
