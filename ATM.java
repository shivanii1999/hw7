
import java.util.Scanner;

class WrongPINException extends Exception{
public String toString() {
			return "PIN you entered is wrong. Exiting..";
		}
}
class InvalidChoiceException extends Exception{
	public String toString() {
		return "You entered invalid choice, Try Again!!";
	}
}
class InvalidAmountException extends Exception{
	public String toString() {
		return "Please enter multiple of 100, 500 or 2000!!";
	}
}
class InsufficientBalanceException extends Exception{
	public String toString() {
		return "Insufficient Balance in your account!!";
	}
}
class InsufficientBalanceInATMException extends Exception{
	public String toString() {
		return "Insufficient Balance in ATM!!";
	}
}
class TransactionTimeOutException extends Exception{
	public String toString() {
		return "Transaction Timeout!!";
	}
}

public class ATM {
	
	public static void main(String [] args) {
		int ATMBalance = 1_00_000;
		int userBalance = 2_50_000;
		int userPIN = 1234;
		int amount;
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Enter the PIN : ");
			int pin = sc.nextInt();
			if(pin != userPIN)
				throw new WrongPINException();
			System.out.println("Select your choice : ");
			long startTime = System.currentTimeMillis();
			loop : while(true) {
				long currTime = System.currentTimeMillis();
				if(currTime - startTime > 20000)
					throw new TransactionTimeOutException();
		System.out.println("\n1.Deposit\n2.Withdraw\n3.Check Balance\n4.Exit");
			int choice = sc.nextInt();

			switch(choice) {
				case 1:
					boolean doneDep = false;
					while(!doneDep) {
						try {
				System.out.println("Enter the amount to deposit : ");
					amount = sc.nextInt();
		if( (amount % 100 != 0) && ( amount % 500 != 0) && ( amount % 2000 != 0) )
				throw new InvalidAmountException();
					userBalance += amount;
					ATMBalance += amount;							System.out.println("Successfully deposited Rs." + amount);
						doneDep = true;
							}
							catch(InvalidAmountException ex) {
								System.out.println(ex);
							}
						}
					break;
					case 2:
						boolean doneWithdraw = false;
						while(!doneWithdraw) {
							try {
				System.out.println("Enter the amount to withdraw : ");
						amount = sc.nextInt();
		if( (amount % 100 != 0) && ( amount % 500 != 0) && ( amount % 2000 != 0) )
			throw new InvalidAmountException();
				else if(amount > userBalance)
				throw new InsufficientBalanceException();
						else if(amount > ATMBalance )
					throw new InsufficientBalanceInATMException();
								userBalance -= amount;
								ATMBalance -= amount;			System.out.println("Please collect your cash and count..");
								doneWithdraw = true;
							}
							catch(InvalidAmountException ex) {
								System.out.println(ex);
							}
							catch(InsufficientBalanceException ex) {
								System.out.println(ex);
							}
							catch(InsufficientBalanceInATMException ex) {
								System.out.println(ex);
							}

				}	
		 		  break;
				 	case 3:
				System.out.println("Your balance is Rs."+userBalance);
						break;
					case 4:
						break loop;
					default:
						try {
							throw new InvalidChoiceException();
						}
						catch(InvalidChoiceException ex) {
							System.out.println(ex);
						}
				}
			}
		}
		catch(WrongPINException ex) {
			System.out.println(ex);
		}
		catch(TransactionTimeOutException ex) {
			System.out.println(ex);
		}
		finally{
			sc.close();
		}
		System.out.println("Thank you for visiting..");
	}
}
