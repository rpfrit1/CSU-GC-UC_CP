/**
 * File Name: twentyFourA.java 
 * Date: Aug 2, 2023
 * Author: Richard Fritsche
 * Project Name: module3 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package module3;

import java.util.Scanner;

/**
 * @author richard
 * 
 */
public class twentyFourA {

	public static void main(String args[]) {
		int[] values;
		boolean[] foundInt;
		
		values = new int[inputQuantity()];
		foundInt = new boolean[values.length];
		
		//assign a random value between 1 and elements + 1 to each element in values
		for(int i = 0; i < values.length; i++) {//for each element
			values[i] = (((int)(Math.random() * 100))%(foundInt.length) + 1);
			foundInt[i] = false;
		}//end for
		
		//check for missing values
		for(int i = 0; i < foundInt.length; i++) {//for each element in the missingInt array
			
			for (int j = 0; j < values.length; j++) {//for each element in the values array
				if (values[j] == (i + 1)) {//if the number was found
					foundInt[i] = true;
					break;
				}//end if
			}//end for each value
		}//end for each foundInt
		
		//output the missing numbers to the screen
		for(int i= 0; i < foundInt.length; i++) {//for each element in foundInt
			if(!foundInt[i]) {//if the number was not found
				System.out.println((i +1) + " was not found.");
			}//end if
		}//end for
	}//end main method

	/**
	 * inputQuantity
	 *
	 * @return
	 */
	private static int inputQuantity()
	{
		try (Scanner scnr = new Scanner(System.in)) {//try to execute getting a number
			System.out.print("How many elements do we want?: ");
			return Integer.parseInt(scnr.nextLine());
		}//end try
		catch (NumberFormatException e) {//if an invalid input was received
			System.out.println("An invalid input was received. Please try again.");
			return inputQuantity();
		}//end catch
	}//end inputQuantity method
}//end twentyFourA class 