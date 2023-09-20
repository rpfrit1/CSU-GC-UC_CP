/**
 * File Name: PostFixDemo.java 
 * Date: Aug 11, 2023
 * Author: Richard Fritsche
 * Project Name: module4 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package module4;

import java.util.Scanner;

/**
 * @author richard
 * 
 */
public class PostFixDemo {
	/**
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		InfixToPostFix converter = new InfixToPostFix();
//		Scanner scnr = new Scanner(System.in);
		//String infix;
		String postfix;
		
//		do {
			converter.clear();
		//System.out.print("Please enter an infix notation equation to convert to postfix notation: ");
		//infix = scnr.nextLine();
		postfix = converter.convertToPostfix("(a+b)*(c-d)^(g*h+i)");
		
		System.out.println("Original equation was: (a+b)*(c-d)^(g*h+i)");
		System.out.println("Converted, it is: " + postfix);

//		} while(continueScript(scnr));
	}//end main method

	/**
	 * continueScript
	 * @param scnr 
	 *
	 * @return
	 */
	private static boolean continueScript(Scanner scnr)
	{
		String contResponse;
		System.out.print("Would you like to do another equation?(y/n): ");
		contResponse = scnr.nextLine();
		return (contResponse.equalsIgnoreCase("y") || contResponse.equalsIgnoreCase("yes"));

	}//end main method
}//end class
