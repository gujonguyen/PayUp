package default_package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AdministratorAccount extends UserAccount {
	static Scanner userInput1 = new Scanner(System.in);
	private static String removeUser;
	
	static void AdminInterface() {

		while(true){
			System.out.println("--------------------------------------------------------");
			System.out.println("Welcome Admin of PayUp!");
			System.out.println("You are logged in to the Administrator Account!");
			System.out.println("--------------------------------------------------------");
			System.out.println("What do you wish to do?");
			System.out.println("(1) Remove a User from PayUp");
			System.out.println("(2) Remove Lists");
			System.out.println("(0) Logout");
			System.out.println("--------------------------------------------------------");
			System.out.print("Please enter your choice: ");
			int userChoice = my_scanINT.nextInt();
			if(userChoice==1){
				int i = 0;
				String[] xAxis = new String[100];
				String[] yAxis = new String[100];
				System.out.println("");
				System.out.println("The list of users on PayUp");
				System.out.println("----------------------------------------------------------------");

				try {
					String sCurrentLine;
					String[] uCurrentLine = new String[2];
					BufferedReader br = new BufferedReader (new FileReader ("User_database.txt"));
					
					while ((sCurrentLine = br.readLine()) != null) {
						uCurrentLine = sCurrentLine.split("\t");
						xAxis[i] = (uCurrentLine[0]);
						yAxis[i] = (uCurrentLine[0]);
						i++;
					}

					for(int k = 0; k<i; k++) {
						System.out.println(xAxis[k]);
					}
					br.close();
				} catch (IOException e) {
					System.out.println("The file does not exist!");
				}
				String[] username = new String [i];
				System.arraycopy(xAxis, 0, username, 0, i);
				System.out.println("Which user do you wish to remove?");
				setRemoveUser(userInput1.nextLine());
				return;		
			}
			else if(userChoice==2){System.out.println("Remove lists"); break;}
			else if(userChoice==0){System.out.println("You are logged out"); break;}
			else System.out.println("Please enter valid choice");
		}
	}

	public static String getRemoveUser() {
		return removeUser;
	}

	public static void setRemoveUser(String removeUser) {
		AdministratorAccount.removeUser = removeUser;
	}
}
