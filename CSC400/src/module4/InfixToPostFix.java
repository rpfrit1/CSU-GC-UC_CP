/**
 * File Name: InfixToPostFix.java 
 * Date: Aug 9, 2023
 * Author: Richard Fritsche
 * Project Name: module4 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package module4;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author richard
 * 
 */
public class InfixToPostFix {
	
	private boolean veritas;
	private Stack<String> operatorStack;
	private String postfix;
	
	public String convertToPostfix(String infix) {
		try
		{
			isValid();
			for(char nextCharacter : infix.toCharArray()) {//for each character in the String
				if (Character.isWhitespace(nextCharacter)) {//if the character read is white space
					continue;
				}//end if white space
				if (Character.isLetterOrDigit(nextCharacter)) {//if the character read is a letter or number
					concat(nextCharacter + "");
				}//end if letter or digit
				switch (nextCharacter) {//select between operations
				//for add and subtract, pop exponent, multiply, divide, add, and subtract off the stack
				case '+':
				case '-':
					processAddSub();
					pushOperator(nextCharacter);
					break;
					//for multiply and divide, pop exponent, multiply, and divide off the stack
				case '*':
				case '/':
					processTimesDivide();
					pushOperator(nextCharacter);
					break;
				//for open parenthesis and exponent, push onto the stack
				case '(':
				case '^':
					pushOperator(nextCharacter);
					break;
				//for closed parentesis, pop everything until the open parenthesis from the stack
				case ')':
					try {
						closedParen();
						break;
					}//end try
					
					//if processing the closed parenthesis empties the stack before an open parenthesis is found.
					catch (EmptyStackException e) {
						System.err.println("The equation is imbalanced.");
						System.err.println(e.getStackTrace());
						return postfix + ") Unbalanced at this point";
					}//end catch
					default:
						break;
				}//end switch
			}//end for
			while(!operatorStack.isEmpty()) {//where there are more operators on the stack
				concat(operatorStack.pop());
			}//end while
			return postfix;
		} //end try
		
		catch (IllegalStateException e)
		{
			return new InfixToPostFix().convertToPostfix(infix);
		}//end catch
	}//end convertToPostfix method
	
	
	/**
	 * closedPararen
	 *
	 */
	private void closedParen()
	{
			String topOperator = operatorStack.pop() + "";
			while(!topOperator.equals("(")) {//while have not reached the open parenthesis
				concat(topOperator);
				topOperator = operatorStack.pop();
			}//end while
	}//end closedParen m ethod


	/**
	 * concat
	 *
	 * @param item
	 */
	private void concat(String item)
	{
		postfix = postfix.concat(item);
	}//end concat method


	/**
	 * pushOperator
	 *
	 * @param nextCharacter
	 */
	private void pushOperator(char nextCharacter)
	{
		operatorStack.push(nextCharacter + "");
	}//end pushOperator method


	/**
	 * processTimesDivide
	 * pops exponent, multiply and divide off the stack and add to the postfix string
	 */
	private void processTimesDivide()
	{
		while((!operatorStack.isEmpty()) && ((operatorStack.peek().equals("^"))
				|| (operatorStack.peek().equals("*")) || 
				(operatorStack.peek().equals("/")))) {//while there are non 
			//parenthesis operators on the stack and the stack is not empty
			concat(operatorStack.pop());
		}//end while
	}//end processTimesDivide method


	/**
	 * processAddSub
	 * pops all except the open parenthesis from the stack
	 */
	private void processAddSub()
	{
		while((!operatorStack.isEmpty()) && ((operatorStack.peek().equals("^"))
				|| (operatorStack.peek().equals("*")) || 
				(operatorStack.peek().equals("/")) || 
				(operatorStack.peek().equals("+")) || (
						operatorStack.peek().equals("-")))) {//while there are
			//non parenthesis operators on the stack and the stack is not 
			//empty
			concat(operatorStack.pop());
		}//end while
	}//end processAddSub method


	/**
	 * Constructor
	 *
	 * @param
	 *
	 */
	public InfixToPostFix()
	{
		veritas = false;
		operatorStack = new Stack<String>();
		postfix = "";
		veritas = true;
	}//end private constructor
	
	
	/**
	 * Checks if the object was properly created. Throws an IllegalStateException if not valid in order to protect the system from running on invalid object.
	 */
	private void isValid()
	{
		if (!veritas) {//if the Singleton was not properly created
			throw new IllegalStateException();
		}//end if
	}//end isValid


	/**
	 * clear
	 *
	 */
	public void clear()
	{
		postfix = "";
		operatorStack.clear();
	}//end clear method
}//end class
