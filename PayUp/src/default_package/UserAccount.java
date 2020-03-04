package default_package;
import java.io.*;
import java.util.*;

public class UserAccount {
	protected String userName;
	private String password;
	private String typeOfAccount;
	private static int noOfUsers;
	int userID = 0;
	private String sCurrentLine;
	static Scanner my_scan = new Scanner(System.in);
	static Scanner my_scanINT = new Scanner(System.in); 

	public UserAccount(String name, String pass, String role) {
		userName = name;
		setPassword(pass);
		typeOfAccount = role;
	}

	public static UserAccount[] readFile() {
		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword= new String[100];
		String[] LocalTypeAccount = new String[100];
		int[] LocalID = new int[100];

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
			System.out.println("This file does not exist");
		}

		String[] FinalLocalUserName = new String[NumUser];
		System.arraycopy(LocalUserName, 0, FinalLocalUserName, 0, NumUser);
		String[] FinalLocalPassword = new String[NumUser];
		System.arraycopy(LocalPassword, 0, FinalLocalPassword, 0, NumUser);
		String[] FinalLocalTypeAccount = new String[NumUser];
		System.arraycopy(LocalTypeAccount, 0, FinalLocalTypeAccount, 0, NumUser);
		int[] FinalLocalID = new int[NumUser];
		System.arraycopy(LocalID, 0, FinalLocalID, 0, NumUser);

		UserAccount user[] = new UserAccount[FinalLocalUserName.length];

		for (int i = 0; i < FinalLocalUserName.length; i++) {
			user[i] = new UserAccount(FinalLocalUserName[i], FinalLocalPassword[i], FinalLocalTypeAccount[i]);
		}	

		return user;
	}

	public UserAccount() {
		int userChoice = getUserChoice();
		switch (userChoice) {
		case 1:
			Register();
			
		case 2:
			String currentUser = Login();
			for (int i = 0; i < readFile().length; i++) {
				if (readFile()[i].userName.equals(currentUser)) {
					if (readFile()[i].typeOfAccount.equals("R")) {
						RegularAccount.userChoiceList();
					}
					else {
						AdministratorAccount.AdminInterface();
					}
					break;
				}
			}	
		case 3:
			Exit();
			break;
			default:
				System.out.println("--------------------------------------------------------");
				System.out.println("Please enter a valid user choice!");
				getUserChoice();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserAccount();
	}
	// This method asks and returns what the user wants to do

	private static int getUserChoice(){
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t Welcome to PayUp! ");
		System.out.println("\n What do you wish to?");   
		System.out.println("--------------------------------------------------------");
		System.out.println("(1) Register your account");
		System.out.println("(2) Login into account");
		System.out.println("(3) Exit");
		System.out.println("--------------------------------------------------------");
		System.out.print("Enter your option: ");
		return my_scanINT.nextInt();
	}

	protected void Register() {

		noOfUsers = readfile1();
		//first: register 
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t Please Register User");
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
	}

	protected static String Login() {
		//second: login
		int NumUser = 0;
		String[] LocalUserName = new String[100];
		String[] LocalPassword = new String[100];
		String Un;
		String Pw;
		String currentUser = null;		
		
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t Please Login User");
		System.out.println("--------------------------------------------------------");
		System.out.println("Please input your username:");
		Un = my_scan.nextLine();
		System.out.println("Please input your password:");
		Pw = my_scan.nextLine();

		try {
			String sCurrentLine;
			String[] uCurrent = new String [2];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				LocalUserName[NumUser] = uCurrent[0];
				LocalPassword[NumUser] = uCurrent[1];
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
				System.out.println("--------------------------------------------------------");
				System.out.println("Login successful, welcome " + Un +"!");
				currentUser = Un;
				break;
			}
		}
		return currentUser;
	}

	protected static void Exit() {
		System.out.println("--------------------------------------------------------");
		System.out.println("	Thank you for visiting PayUp!");
	}

	private int readfile1() {
		int NumUser = 0;
		try {
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((setsCurrentLine(myFile.readLine())) != null) {
				NumUser++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return NumUser;
	}

	public String getsCurrentLine() {
		return sCurrentLine;
	}

	public String setsCurrentLine(String sCurrentLine) {
		this.sCurrentLine = sCurrentLine;
		return sCurrentLine;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
