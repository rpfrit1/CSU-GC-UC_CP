/**
 * File Name: SortCheck.java 
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

/**
 * @author richard
 * 
 */
public class SortCheck {
	public static <T extends Comparable<T>> boolean check(T[] array) {
		boolean ordered = checkDown(array);
		ordered = (ordered || checkUp(array));
		return ordered;
	}//end check method

	/**
	 * checkDown
	 *
	 * Verifies if all of the elements are in descending order.
	 * 
	 * @param array
	 * @return true if in descending order, false otherwise.
	 */
	private static <T extends Comparable<T>> boolean checkDown(T[] array)
	{
		boolean result = true;
		for(int i = 0; i < array.length - 1; i++) {//for each element except the last element
			//verify if this element and the next element are in decending order
			result = result && (array[i].compareTo(array[i+1]) >= 0);
		}//end for
		return result;
	}//end method

	/**
	 * checkUp
	 *
	 * Verifies if all the elelments are in ascending order.
	 * @param array
	 * @return true if in acending order, false otherwise
	 */
	private static <T extends Comparable<T>> boolean checkUp(T[] array)
	{
		boolean result = true;
		for(int i = 0; i < array.length - 1; i++) {//for each element except the last element
			//verify if this element and the next element are in decending order
			result = result && (array[i].compareTo(array[i+1]) <= 0);
		}//end for
		return result;
	}
}//end class
