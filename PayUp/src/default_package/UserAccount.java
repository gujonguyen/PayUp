package default_package;
import java.io.*;
import java.util.*;

public class UserAccount {
	static Scanner my_scan = new Scanner(System.in);
	static Scanner my_scanINT = new Scanner(System.in); 
	static RegularAccount[] my_regulars = new RegularAccount[100]; //let the maximum number =100
	static AdministratorAccount[] my_admins = new AdministratorAccount[100]; //let the maximum number =100
	static String[] current_line = new String[5];

	static int LoggedInID=0; //ID is the parameter of the object, index is its location in the users array
	int LoggedInIndex=1000;

	private static String UserName;
	private static String Password;
	public String FirstName, LastName, Role;
	public int UserID;
	static int NumUser=0;

	static Scanner input = new Scanner(System.in);
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);

	public UserAccount (int UserID, String FirstName, String LastName, String Role, String UserName, String Password) {
		this.UserName = UserName;
		this.Password = Password;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.UserID = UserID;
		this.Role = Role;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int userChoice = getUserChoice();
		switch (userChoice) {
		case 1:
			Register();
			break;
		case 2:
			Login();
			break;
		case 3:
			Exit();
			break;
		}
	}
	// This method asks and returns what the user wants to do

	public static int getUserChoice(){
		System.out.println("--------------------------------------------------------");
		System.out.println("Welcome to your PayUp homepage!");
		System.out.println("\n What do you wish to?");   
		System.out.println("--------------------------------------------------------");
		System.out.println("(1) Register your account");
		System.out.println("(2) Login into account");
		System.out.println("(3) Exit");
		System.out.println("--------------------------------------------------------");
		System.out.print("Enter your option: ");
		return userInput1.nextInt();
	}

	public static void Register() {
		//first: register 
		System.out.println("Please choose a username:");
		UserName = userInput2.nextLine();
		System.out.println("Please choose a password:");
		Password = userInput2.nextLine();
		System.out.println("Please enter your first name:");
		String FirstName = userInput2.nextLine();
		System.out.println("Please enter your last name:");
		String LastName = userInput2.nextLine();
		System.out.println("Are you registering as an Administrator or Regular?");
		String role = userInput2.nextLine();

		try { 
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database",true)));
			wr.println(UserName + "," + Password);
			wr.close();	
		} catch (IOException e) {
			System.out.println("I/O error when writing on file");
		}

	}

	public static boolean Login() {
		int LoggedInID=0, LoggedInIndex=1000; //ID is the parameter of the object, index is its location in
		while(true){
			//second: login
			boolean AdministratorAccountLoggedIn=false, RegularAccountLoggedIn=false; //turn true when either Admin or Regular Account logs in

			System.out.print("Enter your username: ");
			String InputUserName = my_scan.nextLine();
			System.out.print("Enter your password: ");
			String InputUserPassword = my_scan.nextLine();

			//First check the administrator DB:
			for(int i=0;i<AdministratorAccount.Num_admins;i++){
				LoggedInID = my_admins[i].LoginMethod(InputUserName, InputUserPassword);
				if(LoggedInID>0){
					//Login Success
					AdministratorAccountLoggedIn=true;
					LoggedInIndex = i;
					break;
				}
			}
			//If not found in the administrator DB, look into the regular DB
			if(LoggedInID==0){
				for(int i=0;i<RegularAccount.Num_regulars;i++){
					LoggedInID = my_regulars[i].LoginMethod(InputUserName, InputUserPassword);
					if(LoggedInID>0){
						//Login Success
						RegularAccountLoggedIn=true;
						LoggedInIndex = i;
						break;
					}
				}
			}
			if(LoggedInID==0) System.out.println("Login Failed!"); //Finished the for loop without finding the user either in Admin or Regular DB:
			else if(AdministratorAccountLoggedIn) AdminInterface(my_admins[LoggedInIndex],my_regulars);
			else if(RegularAccountLoggedIn) RegularInterface(my_regulars[LoggedInIndex]);

		}

	}

	public static void Exit() {
		//third: exiting the menu
		System.out.println("You are logged out!");
	}

	private static void RegularInterface(RegularAccount regularAccount) {
		System.out.println("You just logged in! You are now accessing the regular account interface");
		while(true){
			System.out.println("--------------------------------------------------------");
			System.out.println("Please select from the menu option below");
			System.out.println("(1)Print my total expense balance");
			System.out.println("(2)Logout");
			System.out.println("--------------------------------------------------------");
			System.out.print("You want to: ");
			int userChoice = my_scanINT.nextInt();
			if(userChoice==1){
				System.out.println("Your total expense balance is: ");
			}
			else if(userChoice==2){System.out.println("You are logged out"); break;}
			else System.out.println("Please enter valid chouce");
		}
	}

	private static void AdminInterface(AdministratorAccount administratorAccount, RegularAccount[] my_regulars2) {
		System.out.println("Welcome admin of PayUp! You are now accessing the administrator interface");
		while(true){
			System.out.println("--------------------------------------------------------");
			System.out.println("What do you wish to do?");
			System.out.println("(1)Remove a User from PayUp");
			System.out.println("(2)Remove Lists");
			System.out.println("(3)Logout");
			System.out.println("--------------------------------------------------------");
			System.out.print("Please enter your choice (1, 2 or 3): ");
			int userChoice = my_scanINT.nextInt();
			if(userChoice==1){
				int i = 0;
				String[] xAxis = new String[100];
				String[] yAxis = new String[100];
				System.out.println("");
				System.out.println("The list of users on PayUp");
				System.out.println("----------------------------------------------------------------");
				System.out.println("Username:");
				System.out.println("----------------------------------------------------------------");

				try {
					String sCurrentLine;
					String[] uCurrentLine = new String[2];
					BufferedReader br = new BufferedReader (new FileReader ("User_database.txt"));

					while ((sCurrentLine = br.readLine()) != null) {
						uCurrentLine = sCurrentLine.split(",");
						xAxis[i] = (uCurrentLine[0]);
						yAxis[i] = (uCurrentLine[0]);
						i++;
					}

					for(int k = 0; k<5; k++) {
						System.out.println(xAxis[k]);
					}
					br.close();
				} catch (IOException e) {
					System.out.println("The file does not exist!");
				}
				String[] username = new String [i];
				System.arraycopy(xAxis, 0, username, 0, i);
				return;
			}

			else if(userChoice==2){System.out.println("Remove lists"); break;}
			else if(userChoice==3){System.out.println("You are logged out"); break;}
			else System.out.println("Please enter valid chouce");
		}
	}


	public static User[] ReadUserData(){
		String LocalFirstName, LocalLastName, LocalUserName, LocalPassword, LocalRole;
		int LocalID;
		boolean FirstLine= true;
		User[] my_users_Local = new User[20];
		//RegularAccount[] my_regulars_Local = new RegularAccount[100];
		String[] current_line = new String[5];

		try{
			BufferedReader my_reader = new BufferedReader(new FileReader("User_database.txt"));
			//declaring the reader object
			String input_line; //variable to read line by line
			while( (input_line=my_reader.readLine()) != null){
				if (FirstLine){
					FirstLine= false;
					continue;
				}
				current_line = input_line.split(","); //split the line at the tab

				//current_line is an array:
				//order in the database: FirstName, LastName, UserName, Password, ID, Role
				//Get the values from the String[] and convert it if needed:
				LocalFirstName = current_line[0];
				LocalLastName = current_line[1];
				LocalUserName = current_line[2];
				LocalPassword = current_line[3];
				LocalID = Integer.parseInt(current_line[4]);
				LocalRole = current_line[5];
				if(LocalRole.equals("Administrator")){
					Teacher.Num_teachers++;
				}
				if(LocalRole.equals("Regular")){
					Student.Num_students++;
				}
				my_users_Local[User.NumUser] = new User(LocalID, LocalFirstName, LocalLastName,
						LocalRole, LocalUserName, LocalPassword);
				User.NumUser++;
			}
			my_reader.close();
		}
		catch(IOException e){
			System.out.println("can't read file");
		}
		return(my_users_Local);
	}
	public int LoginMethod(String InUserName, String InPassword){ //returns the ID if login correct
		if(UserName.equals(InUserName) && Password.equals(InPassword)){ //
			//Login Success:
			System.out.println("--------------------------------------------------------");
			System.out.println("Welcome "+ FirstName + " " + LastName);
			return(UserID);
		}
		else return(0);
	}
	public String GetPassword(boolean LoggedIn){
		if(LoggedIn) return(Password);
		else return("Not Logged In");
	}
	public String GetUserName(boolean LoggedIn){
		if(LoggedIn) return(UserName);
		else return("Not Logged In");
	}
}
