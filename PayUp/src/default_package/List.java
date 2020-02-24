package default_package;
import java.util.*;

public class List {
	static Scanner userInput1 = new Scanner(System.in);

	public static void main(String[] args) {
		int userChoice = getUserChoice1();
	}

	public static int getUserChoice1() {
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("************\t\t What do you want to do? \t\t**********");
		System.out.println("****************************************************************");
		System.out.println("****************************************************************");
		System.out.println("");
		System.out.println("(1) Create new list");
		System.out.println("(2) View List");
		System.out.println("****************************************************************");
		System.out.print("Please enter your choice (1 or 2): ");
		userInput1.nextInt();	// gets either 1 or 2 from the user

	}
	public static void errorChoice() {
		int userChoice = getUserChoice1();
		if (userChoice > 2) {
			System.out.println("Your choice is invalid");

		}
		break;


	}
}
