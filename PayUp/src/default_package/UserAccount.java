package default_package;
import java.io.*;
import java.util.*;


public class UserAccount {
	int userId;
	static String userName;
	static String password;
	static Scanner userInput1 = new Scanner(System.in);
	static Scanner userInput2 = new Scanner(System.in);
	
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
		}
		}
		// This method asks and returns what the user wants to do
		
	public static int getUserChoice(){
		System.out.println("What do you wan to do?");
		System.out.println("(1) Register your account");
		System.out.println("(2) Login into account");
		System.out.println("****************************************************************");
		System.out.print("Please enter your choice (1 or 2): ");
		return userInput1.nextInt();
	}
	
	public static void Register() {


		System.out.println("Please choose a username:");
		userName = userInput2.nextLine();
		System.out.println("Please choose a password:");
		password = userInput2.nextLine();
		
		
		try { 
			
			PrintWriter wr = new PrintWriter( new BufferedWriter(new FileWriter("User_database",true)));
			wr.println(userName + "\t" + password);
				
			wr.close();	
			} catch (IOException e) {
				System.out.println("I/O error when writing on file");
			}
		
	}
	
	public static boolean Login() {
		int lineNumber = 0;
		String[] xaxis = new String[100];
		String[] yaxis = new String[100];
		
		System.out.println("Please input your username:");
		userName = userInput2.nextLine();
		System.out.println("Please input your password:");
		password = userInput2.nextLine();
		
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
		System.arraycopy(xaxis, 0, Finalxaxis, 0, lineNumber);
		System.arraycopy(yaxis, 0, Finalyaxis, 0, lineNumber);
		
		for (int i = 0; i < lineNumber; lineNumber++) {
			if (userName == Finalxaxis[i] && password == Finalyaxis[i]) {
				return true;
			} else {
				return false;
			}
		}
		
	}
}



