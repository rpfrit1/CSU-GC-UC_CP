package module5;

import java.util.Scanner;


public class RecursiveArrayDisplay {
	
	private static String[] displays = new String[10];
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Please enter 10 strings to have displayed backwards.");
		for (int i = 0; i < displays.length; i++) {
			System.out.print("Enter string #" + (i + 1) + ": ");
			displays[i] = scnr.nextLine();
		}//end for
		
		stringDisplay(displays.length);
	}//end main method

	/**
	 * stringDisplay
	 * @param length 
	 *
	 */
	private static void stringDisplay(int length)
	{
		System.out.println(displays[length-1]);
		if(length > 1) {//if the length passed is more than one
			stringDisplay(length - 1);
		}//end if
	}//end stringDisplay method
}//end class
