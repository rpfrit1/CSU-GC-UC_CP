package accountPassing;

import java.util.Scanner;

class CSC450Module6 {
    public static void main(String[] args) {
        try (Scanner scnr = new Scanner(System.in)) {
			AccountData acct = new AccountData();

			if(!acct.checkAccount(readUsername(scnr), readPassword(scnr))) {//if the username and password entered are valid
			    System.out.println("invalid username/passowrd. Please try again");
			}//end if
			else {
				scnr.close();
			    System.out.println("Access granted");
			}//end else
		}//end try
        catch(Exception e) {//if something went wrong
        	System.out.println("Something went wrong. Please try again");
        }//end catch
    }//end main method

	private static int readPassword(Scanner scnr) {
		System.out.print("Please enter your password:");
		return scnr.nextLine().hashCode();
	}

	private static String readUsername(Scanner scnr) {
		System.out.print("Please enter your username:");
		return scnr.nextLine();
	}
}//end class