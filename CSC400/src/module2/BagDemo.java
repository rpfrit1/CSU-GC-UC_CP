/**
 * File Name: BagDemo.java 
 * Date: July 30, 2023
 * Author: Richard Fritsche
 * Project Name: Module 2 
 * Project Purpose: Build a Bag ADT using an Array
 * Algorithm Used: This file creates an Item (internal class to be stored in the Shopping Cart. 
 * Program Inputs: Random number generation and hard coded double to store Item data. User presses enter to review display
 * Program Limitations:This program only prompts the user in order to pause output for the user to review.
 * Program Errors: isValid in Item internal class throws IllegalStateException, which the calling methods handle.
 */

package module2;

import java.util.Scanner;


/**
 * @className BagDemo
 * 
 * @author richard
 * 
 * @purpose This class is the test class for the ShoppingCart class. It contains a main method that runs the test. It contains an internal class for items that are entered into the ShoppingCart.
 */
public class BagDemo {
	//private class variables
	private static ShoppingCart<Item> cart;
	
	//private class to be contained in the ShoppingCart
	/**
	 * @classname Item
	 * 
	 * @author richard
	 * 
	 * @containingClass BagDemo
	 * 
	 * @purpose This class handles the contents of the ShoppingCart class being tested
	 */
	private static class Item {
		//private class variables
		private boolean veritas;//the verify boolean to ensure only working with valid objects
		private Double content;//quick contents of the object
		/**
		 * Constructor: This creates an Item to be stored how it is needed.
		 * 
		 * @param Double: Passes the data that will be stored in the Item.
		 */
		public Item(Double content) {
			veritas = false;
			this.content = content;
			veritas = true;
		}//end constructor
		
		/**
		 * Constructor: This creates an Item to be stored how it is needed.
		 * 
		 * This is the default constructor with no parameters. Creates an Item with "null" value as the data stored
		 *
		 * @param 
		 *
		 */
		public Item() {
			this(null);
		}//end default constructor
		
		/**
		 * isValid
		 *
		 * @throws IllegalStateException
		 * 
		 * This does not return a value as if not valid, will throw the exception
		 */
		private void isValid() throws IllegalStateException{
			if (!veritas) {
				throw new IllegalStateException("Item Object not valid");
			}//end if
		}//end isValid method

		/**
		 * toString
		 * 
		 * @return String representation of the Item
		 *
		 */
		@Override
		public String toString() {
			try {
				isValid();
				return "Item [content= " + content + "]";
			}//end try
			catch (IllegalStateException e) {//if the Item object is invalid
				return null;
			}//end catch
		}//end toString method

		/**
		 * equals
		 * 
		 * @param Object to be tested against this Item
		 * 
		 * @return boolean of if the two store the same information
		 *
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Item)) {
				return false;
			}
			Item other = (Item) obj;
			if (content == null) {
				if (other.content != null) {
					return false;
				}
			} else if (!content.equals(other.content)) {
				return false;
			}
			return true;
		}
	}//end Item class

	/**
	 * main
	 * 
	 * runs the tests for of the ShoppingCart class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		  Scanner scnr = new Scanner(System.in);
		//testing add from default constructor
		cart = new ShoppingCart<Item>();
		addLoop();
		System.out.print("Pausing to review. Press enter when ready: ");
		scnr.nextLine();
		
		//testing remove
		removeLoop();
		System.out.print("Pausing to review. Press enter when ready: ");
		scnr.nextLine();
		
		//testing add from standard constructor
		cart = new ShoppingCart<Item>(249);
		addLoop();
		System.out.print("Pausing to review. Press enter when ready: ");
		scnr.nextLine();
		
		//testing clear
		testClear();
		
	}//end main method

	/**
	 * testClear
	 *
	 * runs the test of the clear function. Displays to the screen the contents, then runs the clear. Tests if the cart is empty
	 * and then displays the contents again. All contents should be null on second run.
	 */
	private static void testClear() {
		displayBag();
		cart.clear();
		System.out.println("Is the cart empty?: " + cart.isEmpty());
		displayBag();
		
	}//end testClear method

	/**
	 * removeLoop
	 *
	 * runs the test of the remove through the entire cart. Tests remove from the end as well as remove a specific Item.
	 */
	private static void removeLoop() {
		Item testItem = new Item(4.5);
		System.out.println("Is the cart empty?: " + cart.isEmpty());//testing if empty
		while (!cart.isEmpty()) {//while the cart is not full
			//add to the cart and then display the cart
			System.out.println("Removing element: " + testItem + ". Was it a success? " + cart.remove(testItem));
			displayBag();
			System.out.println("Removed: " + cart.remove());
			displayBag();
		}//end while
	}//end removeLoop method

	/**
	 * addLoop
	 *
	 * runs a loop adding elements and testing if cart is full. Keeps adding until the cart is full. The method will display the contents after each run of the testaddl method
	 * After running the loop, will display if the cart is empty, if the cart includes a particular Item, and how many of that item.
	 */
	private static void addLoop() {
		//testItem for testing if an item is in the cart after done.
		Item testItem = new Item(4.5);
		
		//verify if the cart starts empty
		System.out.println("Is the cart empty?: " + cart.isEmpty());//testing if empty
		
		//loop to keep adding to cart until full or until cannot add any more
		while (!cart.isFull() && testAddl()) {//while the cart is not full and adding does not fail
			//display the cart
			displayBag();
		}//end while
		
		//Verify empty, if the cart contains the testItem, and how many
		System.out.println("Is the cart empty?: " + cart.isEmpty());//testing if empty
		System.out.println("Does the cart contain testItem? :" + cart.contains(testItem));//testing the contains method
		System.out.println("The cart contains " + cart.getFrequencyOf(testItem) + " of " + testItem);//testing the getFrequencyOf method
	}//end addLoop method


	/**
	 * displayBag
	 *
	 *This method handles displaying the contents of the cart as part of the tests.
	 */
	private static void displayBag() {
		System.out.println("Displaying the contents of the cart:");
		System.out.println(cart.toString());
	}//end displayBag method

	/**
	 * testAddl
	 *
	 * @return boolean of success on last add call.
	 */
	@SuppressWarnings("removal")
	private static boolean testAddl() {
		//add a null Item from default constructor then the testItem value
		cart.add(new Item());
		cart.add(new Item(4.5));
		
		//add to the cart with a random value
		return cart.add(new Item(new Double((int)(Math.random()*1000)%100)));//add and Item with a random value
	}//end testAddl method
}//end demo class
