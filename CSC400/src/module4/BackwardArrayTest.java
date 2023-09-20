/**
 * File Name: BackwardArrayTest.java 
 * Date: Aug 20, 2023
 * Author: Richard Fritsche
 * Project Name: module4 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package module4;

/**
 * @author richard
 * 
 */
public class BackwardArrayTest {
	public static void main(String[] args) {
		int[] testArgs = {4, 7, 9, 10, 478, 57, 39};//the array that will be looked at backwards
		
		System.out.println(evalArray(testArgs, 4, 6) + "");
	}//end main method

	/**
	 * evalArray
	 *
	 * @param testArgs
	 * @param i
	 * @param j
	 * @return
	 */
	private static String evalArray(int[] testArgs, int start, int end)
	{
		if((end >= testArgs.length) || (start >= testArgs.length)|| (start > end)) {
			return null;
		}//end if
		if(start == end) {
			return testArgs[start] + "";
		}//end if
		return testArgs[end] + "->" + evalArray(testArgs, start, end-1);
	}
}//end class
