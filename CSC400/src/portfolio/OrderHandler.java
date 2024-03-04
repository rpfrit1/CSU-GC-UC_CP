/**
 * File Name: OrderHandler.java 
 * Date: Sep 7, 2023
 * Author: Richard Fritsche
 * Project Name: portfolio 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package portfolio;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author richard
 * 
 */
public class OrderHandler {

	private static Scanner scnr;
	private static Order list;
	private static OrderDisplay display;

	/**
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		list = new Order();
		display = new OrderDisplay();
		OrderDetail order;
		int option;
		do {
			option = menu();
			switch (option) {//switch on result of the menu method
			case 1:
				scnr.nextLine();
				add();
				break;
			case 2:
				scnr.nextLine();
				System.out.println("Removing: ");
				order = remove();
				if(order != null) {//if an element was returned
					order.display();
				}//end if
				break;
			case 3:
				scnr.nextLine();
				display();
				break;
			case 4:
				scnr.close();
				break;
			default:
				System.out.println("\n\nInvalid entry. Please try again.");
			}//end switch
			System.out.println("\n\n");
		}while(option!=4);//end do while loop
	}//end main method

	private static int menu() {
		try {
		System.out.println("Welcome to the order processing system.");
		System.out.println("Please select what you want to do.");
		System.out.println("1: Add a new order to the system");
		System.out.println("2: Remove the next order from the system");
		System.out.println("3: Display the list of orders in the system");
		System.out.println("4: Exit the system");
		System.out.print("Which option do you want?");
		return scnr.nextInt();
		}//end try
		catch (InputMismatchException e) {
			scnr.nextLine();
			return 0;//return an invalid value if the value cannot be parsed to a number
		}//end catch
	}
	private static void add() {
		try {
		String name;
		int orderNum;
		double total;
		OrderDetail temp;
		System.out.print("\n\nGreat! What is the customer's last name? ");
		name = scnr.nextLine();
		System.out.print("What is the order number? ");
		orderNum = scnr.nextInt();
		scnr.nextLine();
		System.out.print("And now was was the total to the order? $");
		total = scnr.nextDouble();
		scnr.nextLine();
		temp = new OrderDetail(name, orderNum, total);
		System.out.println("\n");
		list.add(temp);
		System.out.println("\n");
		display.add(temp);
		}//end try
		catch (InputMismatchException e) {
			System.out.println("\n\nThere seems to be a problem. Going back to the menu.");
			scnr.nextLine();
		}//end catch
	}//end add method

	private static OrderDetail remove() {
		try {
		OrderDetail temp = list.remove();
		display.remove(temp);
//		System.out.println("\n");
//		display.displayByName();
//		System.out.println("\n");
//		display.displayByNum();
		return temp;
		}//end try
		catch (NoSuchElementException e) {
			System.out.println("There were no elements to remove.");
			return null;
		}//end catch
	}//end remove method

	private static void display() {
		list.display();
//		System.out.println("\n\n");
//		display.displayByName();
//		System.out.println("\n\n");
//		display.displayByNum();
	}//end display method
	
}//end class
