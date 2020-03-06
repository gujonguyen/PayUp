package default_package;
import java.io.*;
import java.util.*;

public class UserAccount {
	String userName;
	String password;
	String role;
	static int noOfUsers;
	int userID = 0;
	private String sCurrentLine;
	static Scanner userInput1 = new Scanner(System.in); //for int
	static Scanner userInput2 = new Scanner(System.in); // for string
	static Scanner userInput3 = new Scanner(System.in); // for double
	int userChoice;
	int loggedUserID;
	
	public UserAccount(String nameC, String passwordC, int uID ) {
		userName = nameC;
		password = passwordC;
		userID = uID;
	}
	
	public static UserAccount [] createAllUsers() {
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
	
	
	public UserAccount(int userChoicec, int loggedIDc) {
	userChoice = userChoicec;
	loggedUserID = loggedIDc;
	switch (userChoice){
	case 3:
		Exit();
		break;
	}
	}
	public UserAccount() {
		
	}

	protected static void Register() {

		noOfUsers = readfile1();
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t Please Register User");
		System.out.println("--------------------------------------------------------");
		System.out.println("Are you registering as a (R) Regular or an (A) Administrator?");
		String typeOfAccount = userInput2.nextLine();
		System.out.println("Please choose a username:");
		String Un = userInput2.nextLine();
		System.out.println("Please choose a password:");
		String Pw = userInput2.nextLine();
		System.out.println("--------------------------------------------------------");
		System.out.println("\t Thank you for registering to PayUp!");
		System.out.println("--------------------------------------------------------");

		try { //This is for Registration of the users
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database.txt",true)));
			wr.println(Un + "\t" + Pw + "\t" + typeOfAccount + "\t" + noOfUsers);
			wr.close();	
		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}
		new InterfaceClass();
	}

	protected static String[] Login() {
		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword = new String[100];
		String[] LocalRole = new String[100];
		int[] LocalID = new int[100];
		int loggedID = 0;
		String Un;
		String Pw;
		String role = "bla";

		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t Please Login User");
		System.out.println("--------------------------------------------------------");
		System.out.println("Please input your username:");
		Un = userInput2.nextLine();
		System.out.println("Please input your password:");
		Pw = userInput2.nextLine();

		try {
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
		for (int i = 0; i < NumUser; i++) {
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
		System.out.println("--------------------------------------------------------");
		System.out.println("\tYou are successfully logged out\t");
	}

	public static int readfile1() {
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
