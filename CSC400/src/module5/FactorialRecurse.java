/**
 * File Name: FactorialRecurse.java 
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
public class FactorialRecurse {
	
	
	public static long frontFactorial(int num) {
		//System.out.println ("Testing frontFactorial on " + num);
		if (num == 0) {//if the passed number is 0, base case
			//System.out.println("Passed number is 0, returning 1");
			return 1;
		}//end if
		
		if(num < 0) {//if the passed num is less than 0
			//System.out.println("Passed number is less than 0. returning frontFactorial of " + (num + 1) + " * " + num);
			return frontFactorial(num + 1) * num;
		}//end if

		//System.out.println("Passed number is greater than 0. returning frontFactorial of " + (num - 1) + " * " + num);
		return frontFactorial(num-1) * num;
	}//end frontFactorial method
	
	public static long backFactorial(int num) {
		//System.out.println ("Testing backFactorial on " + num);
		if((num < 1) && (num > -1)) {//if the passed number is 0, base case
			//System.out.println("Passed number is 0, returning 1");
			return 1;
		}//end if
		
		if(num < 0) {//if the passed num is less than 0
			//System.out.println("Passed number is less than 0. returning " + num + " * backFactorial of " + (num + 1));
			return num * backFactorial(num + 1);
		}//end if
		
		//System.out.println("Passed number is greater than 0. returning " + num + " * backFactorial of " + (num - 1));
		return num * backFactorial(num-1);
	}//end backFactorial method

}//end class
