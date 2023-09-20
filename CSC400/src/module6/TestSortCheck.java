/**
 * File Name: TestSortCheck.java 
 * Date: Aug 23, 2023
 * Author: Richard Fritsche
 * Project Name: module6 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package module6;

import java.util.Arrays;

/**
 * @author richard
 * 
 */
public class TestSortCheck {
	/**
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] intArray = new Integer[10];
		Double[] numArray = new Double[10];
		
		for (int i = 0; i < intArray.length; i++) {//for each element in the array
			intArray[i] = (int)(Math.random() * intArray.length * 10);
			numArray[i] = (Math.random() * numArray.length);
		}//end for
		
		System.out.println("Here is the Integer array before sorting:\n" + SortExample.printArray(intArray) + "\n");
		System.out.println("Is it sorted before we start? " + SortCheck.check(intArray) + "\n");
		Arrays.sort(intArray);
		System.out.println("Here is the Integer array after sorting:\n" + SortExample.printArray(intArray) + "\n");
		System.out.println("Is it sorted before we start? " + SortCheck.check(intArray) + "\n");
		
		System.out.println("Here is the Double array before sorting:\n" + SortExample.printArray(numArray) + "\n");
		System.out.println("Is it sorted before we start? " + SortCheck.check(numArray) + "\n");
		Arrays.sort(numArray);
		System.out.println("Here is the Double array after sorting:\n" + SortExample.printArray(numArray) + "\n");
		System.out.println("Is it sorted before we start? " + SortCheck.check(numArray) + "\n");
	}//end main method
}//end class
