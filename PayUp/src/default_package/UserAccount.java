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
	protected static String adminCode = "Yashar";

	public UserAccount(String nameC, String passwordC, int uID ) {
		/*
		This is the main constructor for the User account objects
		 */
		userName = nameC;
		password = passwordC;
		userID = uID;
	}

	public static UserAccount [] createAllUsers() {	
		/*
		 * This method reads the user database, creates an array of user objects
		 * containing user name, password, type off account, and user ID. The user
		 * account details are stored into separate arrays and then stored into one
		 * all user array, which is what this method returns
		 */		
		int numUser = 0;
		String [] localUserName = new String [100];
		String [] localPassword= new String [100];
		String [] localTypeAccount = new String [100];
		int [] localID = new int [100];

		UserAccount [] allUsers = new UserAccount [100];

		//The database is read in the following lines of code
		try {
			String sCurrentLine;
			String [] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				localUserName [numUser] = uCurrent [0];
				localPassword [numUser] = uCurrent [1];
				localTypeAccount[numUser] = uCurrent [2];
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

		for (int i = 0; i < numUser; i++) {
			allUsers [i]  = new UserAccount(finalLocalUserName [i], finalLocalPassword [i], finalLocalID [i]);
		}
		return allUsers;
	}

	public UserAccount() {
		// This empty constructor is recalled using the super() method in the extended constructors
	}

	protected static void register() {	
		// This method gets the user name and password from the user for the registration of a new user

		Boolean localBoolean = true;
		noOfUsers = readUserNumber();
		System.out.println("--------------------------------------------------------------------");	
		System.out.println("\tPlease Register User\t");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Are you registering as a (R) Regular or an (A) Administrator?");
		String typeOfAccount = userInput2.nextLine();

		// If the role of the user is equal to A, a, Admin etc., then the user will create an Admin account using the 
		// secret Admin code
		if (typeOfAccount.equals("R") || typeOfAccount.equals("r") || typeOfAccount.equals("Regular") 
				|| typeOfAccount.equals("regular") || typeOfAccount.equals("Reg") || 
				typeOfAccount.equals("reg")) {
			System.out.println("Please choose a username:");
			String un = userInput2.nextLine();
			System.out.println("Please choose a password:");
			String pw = userInput2.nextLine();

			int pwLenght = pw.length();

			//This is to prevent passwords with less than 5 characters
			if (pwLenght > 4) { 

				UserAccount[] temp3 = UserAccount.createAllUsers();

				//This is to check the uniqueness of entered user name within the user database
				for (int k = 0; k < noOfUsers; k++) {	
					if(un.equals(temp3[k].userName)) {
						localBoolean = false;
					}else {
					}
				}

				//If user name is unique a new line with the user's credentials will be written in the file
				if (localBoolean == true) {	
					writeUser(un, pw, typeOfAccount);	
					//The user will be redirected to the register interface due to a duplicate user name
				}else {	
					System.out.println("--------------------------------------------------------------------");
					System.out.println("This username is already taken. Please register again.");
					System.out.println("--------------------------------------------------------------------");
					register();	
				}
				//The user will be redirected to the register interface due to an invalid password
			}else {	
				System.out.println("--------------------------------------------------------------------");
				System.out.println("Please use a password of more than 4 characters");
				System.out.println("--------------------------------------------------------------------");
				register();	
			}
		}

		else if (typeOfAccount.equals("A") || typeOfAccount.equals("a") || typeOfAccount.equals("Adminstrator") 
				|| typeOfAccount.equals("Administrator") || typeOfAccount.equals("admin") || typeOfAccount.equals("Admin")) {
			System.out.println("Enter a unique admin code in order to register as an Admin: ");
			String input_admincode = userInput2.nextLine();
			if (input_admincode.equals(adminCode)) {
				System.out.println("Valid admin code! You may register as an admin now.");
				System.out.println("");

				System.out.println("Please choose a username:");
				String un = userInput2.nextLine();
				System.out.println("Please choose a password:");
				String pw = userInput2.nextLine();

				int pwLenght = pw.length();

				//This is to prevent passwords with less than 5 characters
				if (pwLenght > 4) { 

					UserAccount[] temp3 = UserAccount.createAllUsers();

					//This is to check the uniqueness of entered user name within the user database
					for (int k = 0; k < noOfUsers; k++) {	
						if(un.equals(temp3[k].userName)) {
							localBoolean = false;
						}else {
						}
					}

					//If user name is unique a new line with the user's credentials will be written in the file
					if (localBoolean == true) {	
						writeUser(un, pw, typeOfAccount);	
						//The user will be redirected to the register interface due to a duplicate user name
					}else {
						System.out.println("--------------------------------------------------------------------");
						System.out.println("This username is already taken. Please register again.");
						System.out.println("--------------------------------------------------------------------");
						register();	
					}
					//The user will be redirected to the register interface due to an invalid password
				}else {	
					System.out.println("--------------------------------------------------------------------");
					System.out.println("Please enter a password of more than 4 characters");
					System.out.println("--------------------------------------------------------------------");
					register();	
				}
			}else { 
				System.out.println("");
				System.out.println("Please enter a valid admin code. Try again. (Hint: professor ....)");
				register();
			}
		}
		else { 
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Please enter a valid type of account. Try again:");
			System.out.println("--------------------------------------------------------------------");
			register();
		}
	}

	private static void writeUser(String unc, String pwc, String typeOfAccountc) {	
		// This method writes a new line with the user's credentials in the user database txt file

		String un = unc;
		String pw = pwc;
		String typeOfAccount = typeOfAccountc;

		try {
			PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter("User_database.txt",true)));
			wr.println(un + "\t" + pw + "\t" + typeOfAccount + "\t" + noOfUsers);
			wr.close();	
		}catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}
		//The user will be redirected to the first menu after a successful registration
		System.out.println("--------------------------------------------------------------------");;
		System.out.println("\tThank you for registering to PayUp!\t");
		System.out.println("--------------------------------------------------------------------");
		new InterfaceClass();
	}

	protected static String[] login(){	
		/*
		 * This method is for the login of users and returns the user's loggedID and
		 * role
		 */
		int numUser = 0;
		String [] localUserName = new String [100];
		String [] localPassword = new String [100];
		String [] localRole = new String [100];
		int [] localID = new int [100];
		int loggedID = 0;
		String un;
		String pw;
		String role = "bla";
		//The login interface where the user enters his/her User Name and Password
		System.out.println("--------------------------------------------------------------------");
		System.out.println("\tPlease Login User\t");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Please input your username:");
		un = userInput2.nextLine();
		System.out.println("Please input your password:");
		pw = userInput2.nextLine();

		//This is for reading the user database file, sorting the data with user attributes, and storing the data into local arrays
		try {	
			String sCurrentLine;
			String [] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				localUserName [numUser] = uCurrent [0];
				localPassword [numUser] = uCurrent [1];
				localRole [numUser] = uCurrent [2];
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
		for (int i = 0; i < numUser; i++) {	//checking for every line in the user database if entered user name and password match with a row
			if (un.equals(finalLocalUserName [i]) && pw.equals(finalLocalPassword [i])) {	
				loggedID = localID [i];
				role = localRole [i];
				System.out.println("--------------------------------------------------------------------");
				System.out.println("Login successful, welcome " + un +"!");
			}
		}
		String arrayofLoggedUser [] = new String [2];
		arrayofLoggedUser [0] = Integer.toString(loggedID);
		arrayofLoggedUser [1] = role;

		return arrayofLoggedUser;
	}

	public static void exit() {	
		// This method will let the user exit PayUp

		System.out.println("\n\tYou are successfully logged out\t");
		System.out.println("\tThank you for visiting PayUp!\t");
	}

	public static int readUserNumber() {	
		//This method reads the user database file and returns all existing rows within the user database

		int numUser = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				numUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return numUser;
	}
}
