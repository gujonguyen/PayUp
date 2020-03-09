package default_package;
import java.io.*;
import java.util.*;

public class UserAccount {
	String userName;
	String password;
	String role;
	static int noOfUsers;
	int userID = 0;
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	int userChoice;
	int loggedUserID;


	public UserAccount(String nameC, String passwordC, int uID ) {
		userName = nameC;
		password = passwordC;
		userID = uID;
	}

	public static UserAccount [] createAllUsers() {	
		/*
		 * // This method reads the user database, creates an array of user objects
		 * containing user name, // password, type off account, and user ID // The user
		 * account details are stored into separate arrays and then stored into // one
		 * all user array, which is what this method returns
		 */		
		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword= new String[100];
		String[] LocalTypeAccount = new String[100];
		int[] LocalID = new int[100];

		UserAccount [] allUsers = new UserAccount [100];

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


		for (int i = 0; i < NumUser; i++) {
			allUsers [i]  = new UserAccount(FinalLocalUserName[i], FinalLocalPassword[i], FinalLocalID[i]);
		}

		return allUsers;
	}
	public UserAccount() {
	// This empty constructor is recalled using the super() method in the extended constructors
	}

	protected static void Register() {	
		//This method gets the user name and password from the user for the registration of a new user


		Boolean localBoolean = true;
		noOfUsers = readfile1();
		System.out.println("--------------------------------------------------------");
		System.out.println("\tPlease Register User\t");
		System.out.println("--------------------------------------------------------");
		System.out.println("Are you registering as a (R) Regular or an (A) Administrator?");
		String typeOfAccount = userInput2.nextLine();
		
			System.out.println("Please choose a username:");
			String Un = userInput2.nextLine();
			System.out.println("Please choose a password:");
			String Pw = userInput2.nextLine();

			int pw = Pw.length();

			if (pw > 4) { //This is to prevent passwords with less than 5 characters

				UserAccount[] temp3 = UserAccount.createAllUsers();

				for (int k = 0; k < noOfUsers; k++) {	//This is to check the uniqueness of entered user name within the user database
					if(Un.equals(temp3[k].userName)) {
						localBoolean = false;
					}else {
					}
				}

				if (localBoolean == true) {	//If user name is unique a new line with the user's credentials will be written in the file
					writeUser(Un, Pw, typeOfAccount);	
				}else {	//The user will be redirected to the register interface due to a duplicate user name
					System.out.println("--------------------------------------------------------");
					System.out.println("This User Name is already taken, please Register again");
					System.out.println("--------------------------------------------------------");
					Register();	
				}
			}else {	//The user will be redirected to the register interface due to an invalid password
				System.out.println("--------------------------------------------------------");
				System.out.println("Please use a password of more than 4 characters");
				System.out.println("--------------------------------------------------------");
				Register();	
			}
		}
	}


	private static void writeUser(String Unc, String Pwc, String typeOfAccountc) {	
		//This method writes a new line with the user's credentials in the user database txt file
		String Un = Unc;
		String Pw = Pwc;
		String typeOfAccount = typeOfAccountc;

		try {
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database.txt",true)));
			wr.println(Un + "\t" + Pw + "\t" + typeOfAccount + "\t" + noOfUsers);
			wr.close();	
		}catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}
		//The user will be redirected to the first menu after a successful registration
		System.out.println("--------------------------------------------------------");
		System.out.println("\tThank you for registering to PayUp!\t");
		System.out.println("--------------------------------------------------------");
		new InterfaceClass();
	}


	protected static String[] Login() {	
		//This method is for the login of users and returns the user's loggedID and role
		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword = new String[100];
		String[] LocalRole = new String[100];
		int[] LocalID = new int[100];
		int loggedID = 0;
		String Un;
		String Pw;
		String role = "bla";
		//The login interface where the user enters his/her username and password
		System.out.println("--------------------------------------------------------");
		System.out.println("\tPlease Login User\t");
		System.out.println("--------------------------------------------------------");
		System.out.println("Please input your username:");
		Un = userInput2.nextLine();
		System.out.println("Please input your password:");
		Pw = userInput2.nextLine();

		try {	//This is for reading the user database file, sorting the data with user attributes, and storing the data into local arrays
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				LocalUserName[NumUser] = uCurrent[0];
				LocalPassword[NumUser] = uCurrent[1];
				LocalRole[NumUser] = uCurrent[2];
				LocalID[NumUser] = Integer.parseInt(uCurrent[3]);
				NumUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol1");
		}

		String[] FinalLocalUserName = new String[NumUser];
		System.arraycopy(LocalUserName, 0, FinalLocalUserName, 0, NumUser);
		String[] FinalLocalPassword = new String[NumUser];
		System.arraycopy(LocalPassword, 0, FinalLocalPassword, 0, NumUser);
		for (int i = 0; i < NumUser; i++) {	//checking for every line in the user database if entered user name and password match with a row
			if (Un.equals(FinalLocalUserName[i]) && Pw.equals(FinalLocalPassword[i])) {	
				loggedID = LocalID[i];
				role = LocalRole[i];
				System.out.println("--------------------------------------------------------");
				System.out.println("Login successful, welcome " + Un +"!");
			}

		}
		String arrayofLoggedUser[] = new String[2];
		arrayofLoggedUser[0] = Integer.toString(loggedID);
		arrayofLoggedUser[1] = role;

		return arrayofLoggedUser;

	}

	protected static void Exit() {	
		//This method will let the user exit PayUp
		System.out.println("\n\tYou are successfully logged out\t");
		System.out.println("\tThank you for visiting PayUp!\t");
	}

	public static int readfile1() {	
		// This method reads the user database file and returns all existing rows within the user database
		int NumUser = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				NumUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not existlol2");
		}
		return NumUser;
	}

}
