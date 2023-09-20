/**
 * File Name: FactorialTest.java 
 * Date: Aug 19, 2023
 * Author: Richard Fritsche
 * Project Name: module5 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package module5;


/**
 * @author richard
 * 
 */
public class FactorialTest {
	/**
	 * main
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		int i= 0;
		System.out.println("Testing frontFactorial on the case of i = " + i + ": " + FactorialRecurse.frontFactorial(i));
		System.out.println("Testing backFactorial on the base case of i = " + i + ": " + FactorialRecurse.backFactorial(i));
		for(i= 1; i < 100; i*=10) {//for each i up to 1000
		System.out.println("Testing frontFactorial on the case of i = " + i + ": " + FactorialRecurse.frontFactorial(i));
		System.out.println("Testing backFactorial on the base case of i = " + i + ": " + FactorialRecurse.backFactorial(i));
		System.out.println("Testing frontFactorial on the case of i = " + (-i) + ": " + FactorialRecurse.frontFactorial(-i));
		System.out.println("Testing backFactorial on the case of i = " + (-i) + ": " + FactorialRecurse.backFactorial(-i));
		}//end for
//		System.out.println("Testing frontFactorial on the base case of -1: " + FactorialRecurse.frontFactorial(-1));
//		System.out.println("Testing backFactorial on the base case of -1: " + FactorialRecurse.backFactorial(-1));
	}//end main method
}//end class
