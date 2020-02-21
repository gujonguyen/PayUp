package default_package;
import java.util.*;

public class Expense {
	String expenseName;
	Date expenseDate;
	double amount;
	List belongsToList = new List(); //every expense needs to belong to a list
	List [] participants = new List[2];

	public double splitExpense() {
		double splitAmount = amount/participants.length;
		return splitAmount;
	}
}
