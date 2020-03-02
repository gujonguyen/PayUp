package default_package;
import java.io.*;
import java.util.*;

public class UserAccount {
	String userName;
	String password;
	String typeOfAccount;
	static int noOfUsers;
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	static Scanner userInput3 = new Scanner(System.in);
	int userID = 0;
	private String sCurrentLine;
	static Scanner my_scan = new Scanner(System.in);
	static Scanner my_scanINT = new Scanner(System.in); 

	public UserAccount(String name, String pass, String role) {
		userName = name;
		password = pass;
		typeOfAccount = role;	
	}

	public static UserAccount[] readFile() {
		int lineNumber = 0;
		String[] aaxis = new String[100];
		String[] baxis = new String[100];
		String[] caxis = new String[100];
		int[] daxis = new int[100];

		try {
			String sCurrentLine;
			String[] uCurrent = new String [4];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				aaxis[lineNumber] = uCurrent[0];
				baxis[lineNumber] = uCurrent[1];
				caxis[lineNumber] = uCurrent[2];
				daxis[lineNumber] = Integer.parseInt(uCurrent[3]);

				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		String[] Finalaaxis = new String[lineNumber];
		System.arraycopy(aaxis, 0, Finalaaxis, 0, lineNumber);
		String[] Finalbaxis = new String[lineNumber];
		System.arraycopy(baxis, 0, Finalbaxis, 0, lineNumber);
		String[] Finalcaxis = new String[lineNumber];
		System.arraycopy(caxis, 0, Finalcaxis, 0, lineNumber);
		int[] Finaldaxis = new int[lineNumber];
		System.arraycopy(daxis, 0, Finaldaxis, 0, lineNumber);

		UserAccount user[] = new UserAccount[Finalaaxis.length];

		for (int i = 0; i < Finalaaxis.length; i++) {
			user[i] = new UserAccount(Finalaaxis[i], Finalbaxis[i], Finalcaxis[i]);
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
					if (readFile()[i].typeOfAccount.equals("r")) {
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
		default:
			System.out.print("Please enter a valid choice.");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserAccount();
	}
	// This method asks and returns what the user wants to do

	public static int getUserChoice(){
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
	

	public void Register() {
		
		noOfUsers = readfile1();
		//first: register 
		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t Please Register User");
		System.out.println("--------------------------------------------------------");
		System.out.println("Are you registering as a (R) Regular or an (A) Administrator?");
		String typeOfAccount = userInput3.nextLine();
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
	}

	public static String Login() {
		//second: login
		int lineNumber = 0;
		String[] xaxis = new String[100];
		String[] yaxis = new String[100];
		String Un;
		String Pw;
		String currentUser = null;		

		System.out.println("--------------------------------------------------------");
		System.out.println("\t\t Please Login User");
		System.out.println("--------------------------------------------------------");
		System.out.println("Please input your username:");
		Un = userInput2.nextLine();
		System.out.println("Please input your password:");
		Pw = userInput2.nextLine();

		try {
			String sCurrentLine;
			String[] uCurrent = new String [2];
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				xaxis[lineNumber] = uCurrent[0];
				yaxis[lineNumber] = uCurrent[1];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		String[] Finalxaxis = new String[lineNumber];
		System.arraycopy(xaxis, 0, Finalxaxis, 0, lineNumber);
		String[] Finalyaxis = new String[lineNumber];
		System.arraycopy(yaxis, 0, Finalyaxis, 0, lineNumber);

		for (int i = 0; i < lineNumber; i++) {
			if (Un.equals(Finalxaxis[i]) && Pw.equals(Finalyaxis[i])) {
				System.out.println("Login successful");
				currentUser = Un;
				break;
			}
		}
		return currentUser;
	}

	private String Exit() {
		System.out.println("--------------------------------------------------------");
		System.out.println("	Thank you for visiting PayUp!");
		return null;
	}

	public int readfile1() {
		int lineNumber = 0;
		try {
			BufferedReader myFile = new BufferedReader (new FileReader("User_database.txt")); 
			while ((setsCurrentLine(myFile.readLine())) != null) {
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}
		return lineNumber;
	}


	public String getsCurrentLine() {
		return sCurrentLine;
	}

	public String setsCurrentLine(String sCurrentLine) {
		this.sCurrentLine = sCurrentLine;
		return sCurrentLine;
	}

}




