package default_package;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Expense {
	static String expenseName;
	static String expenseDate;
	static double amount;
	List belongsToList = new List(); //every expense needs to belong to a list
	List [] participants = new List[2];
	UserAccount[] temp = UserAccount.readFile();

	public static double splitExpense() {
		int lineNumber = 0;
		String[] c1 = new String[100];
		String[] c2 = new String[100];
		String[] c3 = new String[100];
		String[] c4 = new String[100];
		String[] c5 = new String[100];
		double tempSum = 0;
		try {
			String sCurrentLine;
			String[] uCurrent = new String [5];
			BufferedReader myFile = new BufferedReader (new FileReader("Expense_database.txt")); 
			while ((sCurrentLine = myFile.readLine()) != null) {
				uCurrent = sCurrentLine.split("\t");

				c1[lineNumber] = uCurrent[0];
				c2[lineNumber] = uCurrent[1];
				c3[lineNumber] = uCurrent[2];
				c4[lineNumber] = uCurrent[3];
				c5[lineNumber] = uCurrent[4];
				lineNumber++;
			}
			myFile.close(); 
		}catch (IOException e) {
			System.out.println("This file does not exist");
		}

		String[] Finalc1 = new String[lineNumber];
		System.arraycopy(c1, 0, Finalc1, 0, lineNumber);
		String[] Finalc2 = new String[lineNumber];
		System.arraycopy(c2, 0, Finalc2, 0, lineNumber);
		String[] Finalc3 = new String[lineNumber];
		System.arraycopy(c3, 0, Finalc3, 0, lineNumber);
		String[] Finalc4 = new String[lineNumber];
		System.arraycopy(c4, 0, Finalc4, 0, lineNumber);
		String[] Finalc5 = new String[lineNumber];
		System.arraycopy(c5, 0, Finalc5, 0, lineNumber);
		
		double[] amount = new double[Finalc3.length];
		for (int i = 0; i < Finalc3.length; i++) {
			amount[i] = Double.parseDouble(Finalc3[i]);
		}
		
		for (int i = 0; i < Finalc1.length; i++) {
			if (UserAccount.userName.equals(Finalc5[i])) {
				tempSum += amount [i]/2;
			}
			else {
				tempSum -= amount [i]/2;
			}
		}
		return tempSum;
	}
}
