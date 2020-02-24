package default_package;

public class RegularAccount extends UserAccount {
	public static void getUserChoiceList() {
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
		userInputList.nextInt();	// gets either 1 or 2 from the use

	
	public void viewList() {
		viewList();
	}
	public void createNewList() {
		createNewList();
	}
	public void deleteList() {
		deleteList();
	}
	public void settleList() {
		settleList();
	}
	public void viewIndividualBalance() {
		viewIndividualBalance();
	}
	public void viewExpenseHistory() {
		viewExpenseHistory();
	}
	public void writeExpenseHistory() {
		writeExpenseHistory();
	}
}
