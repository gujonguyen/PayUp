package default_package;
import java.io.*;

public class RegularAccount extends UserAccount{	
	//This is a subclass of the superclass UserAccount

	String userName;
	String password;
	static String role;
	int id;
	String typeOfaccount;
	int userChoice;
	int loggedUserID;

	public RegularAccount(String nameC, String passwordC, int uID, String roleC ) {
		/*
		This is the main constructor for the Regular account objects
		 */
		super(nameC, passwordC, uID);
		role = roleC;

	}

	public static RegularAccount [] createRegulars() {	
		/*
		 * This method reads the user database and searches for regular users
		 * specifically. This method then creates an array of regular user objects
		 * containing their user name,password, type off account, and user ID. The
		 * regular account details are stored into separate arrays and then stored into
		 * one regulars array containing all regular accounts, which is what this
		 * method returns
		 */
		int numUser = 0;
		String [] localUserName = new String [100];
		String [] localPassword= new String [100];
		String [] localTypeAccount = new String [100];
		int [] localID = new int [100];

		RegularAccount [] regulars = new RegularAccount [100];

		try {
			String sCurrentLine;
			String [] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				localUserName [numUser] = uCurrent [0];
				localPassword [numUser] = uCurrent [1];
				localTypeAccount [numUser] = uCurrent [2];
				localID [numUser] = Integer.parseInt(uCurrent [3]);

				numUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		String [] finalLocalUserName = new String [numUser];
		System.arraycopy(localUserName, 0, finalLocalUserName, 0, numUser);
		String [] finalLocalPassword = new String [numUser];
		System.arraycopy(localPassword, 0, finalLocalPassword, 0, numUser);
		String [] finalLocalTypeAccount = new String [numUser];
		System.arraycopy(localTypeAccount, 0, finalLocalTypeAccount, 0, numUser);
		int [] finalLocalID = new int [numUser];
		System.arraycopy(localID, 0, finalLocalID, 0, numUser);
		int counterRegulars = 0;
		for (int i = 0; i < numUser; i++) {
			if (finalLocalTypeAccount[i].equals("R")) {
				regulars [counterRegulars]  = new RegularAccount(finalLocalUserName [i], finalLocalPassword [i], finalLocalID [i], finalLocalTypeAccount [i] );
				counterRegulars ++;
			}
		}
		return regulars;
	}
}
