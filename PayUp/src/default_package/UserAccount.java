package default_package;

import java.util.Scanner;

public class InterfaceClass {
	static Scanner my_scan = new Scanner(System.in);
	static Scanner my_scanINT = new Scanner(System.in); 
	String typeOfAccount;
	static int loggedUserID;
	String[] passedUserData;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InterfaceClass();
	}

	public InterfaceClass() {
		int userChoice = getUserChoice1();
		switch (userChoice) {
		case 1:
			UserAccount.Register();

		case 2:
			String[] arrayofLoggedUser = UserAccount.Login();
			typeOfAccount = arrayofLoggedUser[1];
			int loggeduserIDl;
			loggeduserIDl = Integer.parseInt(arrayofLoggedUser[0]);
			if (typeOfAccount.equals("R") || typeOfAccount.equals("r") || typeOfAccount.equals("Regular") || typeOfAccount.equals("regular")){
				RegularAccount(typeOfAccount, loggeduserIDl);
				}
			else if (typeOfAccount.equals("A") || typeOfAccount.equals("a")|| typeOfAccount.equals("Admin")|| typeOfAccount.equals("admin")) {
				AdministratorAccount(typeOfAccount, loggeduserIDl);
			}
			else {
				System.out.println("Invalid login credentials, please try again!");
				new InterfaceClass();
			}
		case 3:
			UserAccount.Exit();
			break;
		}
	}

	private static int getUserChoice1(){
		System.out.println("--------------------------------------------------------");
		System.out.println("\t Welcome to PayUp! ");
		System.out.println("\n What do you wish to?");   
		System.out.println("--------------------------------------------------------");
		System.out.println("(1) Register your account");
		System.out.println("(2) Login into account");
		System.out.println("(3) Exit");
		System.out.println("--------------------------------------------------------");
		System.out.print("Enter your option: ");
		return my_scanINT.nextInt();
	}

	public void RegularAccount(String typeOfAccountc, int loggedUserIDc) {
		
		typeOfAccount = typeOfAccountc;
		
		int loggedUserIDl = loggedUserIDc;
		int userChoice = regularUserInterface();
		
		if (userChoice < 4) {
			new List(userChoice, loggedUserIDl);
		}else {
			new RegularAccount(userChoice, loggedUserIDl);
		}

	}

	public static int regularUserInterface() {
		System.out.println("--------------------------------------------------------");//user interface for regular users
		System.out.println("You are logged in as a Regular user!");
		System.out.println("What do you want to do?");
		System.out.println("--------------------------------------------------------");
		System.out.println("");
		System.out.println("(1) Create a new list");
		System.out.println("(2) View a list");
		System.out.println("(3) View individual balance");
		System.out.println("(4) Delete a list");
		System.out.println("(5) Settle a list");
		System.out.println("(6) Add Expense To List");
		System.out.println("(7) View Expense History");
		System.out.println("(8) Write Expense History");
		System.out.println("(9) Logout");
		System.out.println("--------------------------------------------------------");
		System.out.print("You want to: ");
		return my_scanINT.nextInt();	//getting user choice 
	}

	public void AdministratorAccount(String typeOfAccountc, int loggedUserIDc){
		typeOfAccount = typeOfAccountc;
		int loggedUserIDl = loggedUserIDc;
		int userChoice = AdminInterface();
		if (userChoice < 3) {
			new AdministratorAccount(userChoice, loggedUserIDl);
		}else {
			new UserAccount(userChoice, loggedUserIDl);
		}
	}

	static int AdminInterface() {

		System.out.println("--------------------------------------------------------");
		System.out.println("Welcome Admin of PayUp!");
		System.out.println("You are logged in to the Administrator Account!");
		System.out.println("--------------------------------------------------------");
		System.out.println("What do you wish to do?");
		System.out.println("(1) Remove a User from PayUp");
		System.out.println("(2) Remove List");
		System.out.println("(3) Logout");
		System.out.println("--------------------------------------------------------");
		System.out.print("Please enter your choice: ");
		return  my_scanINT.nextInt();
	}

}
