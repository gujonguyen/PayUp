package default_package;
import java.io.*;
import java.util.*;

public class UserAccount {
	String userName;
	String password;
	static String typeOfAccount;
	static int noOfUsers;
	int userID = 0;
	private String sCurrentLine;
	static Scanner my_scan = new Scanner(System.in);
	static Scanner my_scanINT = new Scanner(System.in); 
	int userChoice;
	
	
	public UserAccount(int userChoicec) {
	userChoice = userChoicec;
	switch (userChoice){
	case 3:
		Exit();
		break;
	}
	}

	protected static void Register() {

		noOfUsers = readfile1();
		System.out.println("--------------------------------------------------------");
		System.out.println("\t Please Register User");
		System.out.println("--------------------------------------------------------");
		System.out.println("Are you registering as a (R) Regular or an (A) Administrator?");
		String typeOfAccount = my_scan.nextLine();
		System.out.println("Please choose a username:");
		String Un = my_scan.nextLine();
		System.out.println("Please choose a password:");
		String Pw = my_scan.nextLine();
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

	protected static String Login() {
		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword = new String[100];
		String[] LocalRole = new String[100];
		int[] LocalID = new int[100];
		int loggedID = 0;
		String Un;
		String Pw;
		String role = "";

		System.out.println("--------------------------------------------------------");
		System.out.println("\t Please Login User");
		System.out.println("--------------------------------------------------------");
		System.out.println("Please input your username:");
		Un = my_scan.nextLine();
		System.out.println("Please input your password:");
		Pw = my_scan.nextLine();

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
			System.out.println("This file does not exist");
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
		return role;
	}

	protected static void Exit() {
		System.out.println("--------------------------------------------------------");
		System.out.println("\tThank you for visiting PayUp!\t");
	}

	private static int readfile1() {
		int NumUser = 0;
		try {
			String sCurrentLine;
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				NumUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return NumUser;
	}

}
