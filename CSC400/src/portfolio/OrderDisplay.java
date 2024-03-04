/**
 * File Name: OrderDisplay.java 
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author richard
 * 
 */
public class OrderDisplay {
	//class variables
	private ArrayList<OrderDetail> nameOrderedList;//copy of the customer orders by last name
	private OrderDetail[] nameOrdered;//copy of the customer orders by last name
	private ArrayList<OrderDetail> numOrderedList;//copy of the custormer orders by order number
	private OrderDetail[] numOrdered;
	private int size;

	private boolean veritas;//boolean variable to confirm object is valid.

	private void isValid() {
		if (!veritas) {
			throw new IllegalStateException();
		}//end if
	}//end isValid method

	public OrderDisplay() {
		veritas = false;
		nameOrderedList = new ArrayList<>();
		nameOrdered = new OrderDetail[10];
		numOrderedList = new ArrayList<>();
		numOrdered = new OrderDetail[10];
		size = 0;
		veritas = true;
	}//end constructor

	public void add(OrderDetail o) {
		try {
			isValid();
			if(size == nameOrdered.length) {//if the name Ordered array is full
				resize();
			}//end if
//		nameOrderedList.add(o);
		nameOrdered[size] = o;
//		numOrderedList.add(o);
		numOrdered[size] = o;
		size++;
		nameSort(0,size);
		numSort(0,size);
		displayByName();
		displayByNum();
		}//end try
		catch (IllegalStateException e) {
			return;
		}//end catch
	}//end add method

	/**
	 * resize
	 *
	 */
	private void resize()
	{
		OrderDetail[]temp = new OrderDetail[size * 2];

		for(int i = 0; i<nameOrdered.length;i++ ) {//for each element currently in nameOrdered array
			temp[i] = nameOrdered[i];//copy element over to temp array
		}//end for
		nameOrdered = temp;
		
		for(int i = 0; i <numOrdered.length; i++) {//for each element currently in numOrdered array
			temp[i] = numOrdered[i];
		}//end for
		numOrdered = temp;
	}//end resize method

	/**
	 * displayByNum
	 *
	 */
	public void displayByNum()
	{
		System.out.println("Printing List ordered by Order Number:");
		for(int i = 0; i < size; i++) {//for each order in the name ordered list
			//System.out.println("Index: " + i);
			numOrdered[i].display();
		}//for each loop
	}//end displayByNum method

	/**
	 * displayByName
	 *
	 */
	public void displayByName()
	{
		System.out.println("Printing List ordered by Customer Last Name:");
		for(int i = 0; i < size; i++) {//for each order in the name ordered list
			//System.out.println("Index: " + i);
			nameOrdered[i].display();
		}//for each loop
	}//end displayByName method

	/**
	 * numSort
	 * @param end 
	 * @param start 
	 *
	 */
	private void numSort(int start, int end)
	{
		int lower = start;
		int upper = end-1;
		int mid = (start + end)/2;
		OrderDetail temp;
		//System.out.println("Start numSort method with indexes " + start + " through " + end);
		if(start >= upper) {//if the start and end are the same
			//System.out.println("Set of 1, returning");
			return;
		}//end if
		
		if((end - start) < 3) {//if there are two elements in the subarray
			//System.out.println("Set of 2");
			//System.out.println("Comparing " + nameOrdered[start].getLastName() + " to " + nameOrdered[end-1].getLastName() + " results as " + nameOrdered[start].getLastName().compareTo(nameOrdered[end-1].getLastName()));
			if (numOrdered[start].getOrderNumber() < numOrdered[upper].getOrderNumber()) {//if the first element is lower value than the second value
				//System.out.println("Swapping the elements");
				temp = numOrdered[start];
				numOrdered[start] = numOrdered[upper];
				numOrdered[upper] = temp;
			}//end if
			//System.out.println("Returning");
			return;
		}//end if

		while ((lower < mid) ||(upper > mid)) {//while lower is less than mid or upper is less than mid
			//System.out.println("Mid " + mid + " is between Lower " + lower + " and Upper " + upper);
			while ((numOrdered[lower].getOrderNumber() > numOrdered[mid].getOrderNumber()) && (lower < mid)) {//while the elements set before the mid point are less than the mid point element and lower is less than mid
				//System.out.println("increment lower");
				lower++;
			}//end while
			while ((numOrdered[upper].getOrderNumber() < numOrdered[mid].getOrderNumber()) && (upper > mid)) {//while the elements set before the mid point are less than the mid point element and lower is less than mid
				//System.out.println("decrement upper");
				upper--;
			}//end while
			//System.out.println("Swap upper and lower");
			swap(numOrdered, lower, upper);
			lower = (lower < mid) ? lower + 1 : lower;
			upper = (upper > mid) ? upper - 1 : upper;
		}//end while
		
		//ensure that the mid element is in the proper spot. 
		if(numOrdered[mid].getOrderNumber() > numOrdered[lower].getOrderNumber()) {//if mid should be sorted before lower
			swap(numOrdered, mid, lower);
		}//end if
		
		if(numOrdered[mid].getOrderNumber() < numOrdered[upper].getOrderCost()) {//if the mid should be sorted after the upper
			swap(numOrdered, mid, upper);
		}//end if
		//System.out.println("Run on lower half");
		numSort(start, mid+1);
		//System.out.println("Run on upper half");
		numSort(mid, end);
	}//end numSort method

	/**
	 * swap
	 *
	 * @param numOrdered2
	 * @param lower
	 * @param upper
	 */
	private void swap(OrderDetail[] orders, int lower, int upper)
	{

		OrderDetail temp = orders[lower];
		orders[lower] = orders[upper];
		orders[upper] = temp;
	}

	/**
	 * nameSort
	 * @param start 
	 * @param end
	 *
	 */
	private void nameSort(int start, int end)
	{
		int lower = start;
		int upper = end-1;
		int mid = (start + end)/2;
		OrderDetail temp;
		//System.out.println("Start nameSort method with indexes " + start + " through " + end);
		if(start >= upper) {//if the start and end are the same
			//System.out.println("Set of 1, returning");
			return;
		}//end if
		if((end - start) < 3) {//if there are two elements in the subarray
			//System.out.println("Set of 2");
			///System.out.println("Comparing " + nameOrdered[start].getLastName() + " to " + nameOrdered[end-1].getLastName() + " results as " + nameOrdered[start].getLastName().compareTo(nameOrdered[end-1].getLastName()));
			if (nameOrdered[start].getLastName().compareToIgnoreCase(nameOrdered[upper].getLastName()) < 0) {//if the first element is lower value than the second value
				//System.out.println("Swapping the elements");
				temp = nameOrdered[start];
				nameOrdered[start] = nameOrdered[upper];
				nameOrdered[upper] = temp;
			}//end if
			//System.out.println("Returning");
			return;
		}//end if
		while ((lower < mid) ||(upper > mid)) {//while lower is less than mid or upper is less than mid
			//System.out.println("Mid " + mid + " is between Lower " + lower + " and Upper " + upper);
			while ((nameOrdered[lower].getLastName().compareToIgnoreCase(nameOrdered[mid].getLastName()) > 0) && (lower < mid)) {//while the elements set before the mid point are less than the mid point element and lower is less than mid
				//System.out.println("increment lower");
				lower++;
			}//end while
			while ((nameOrdered[upper].getLastName().compareToIgnoreCase(nameOrdered[mid].getLastName()) < 0) && (upper > mid)) {//while the elements set before the mid point are less than the mid point element and lower is less than mid
				//System.out.println("decrement upper");
				upper--;
			}//end while
			//System.out.println("Swap upper and lower");
			temp = nameOrdered[lower];
			nameOrdered[lower] = nameOrdered[upper];
			nameOrdered[upper] = temp;
			lower = (lower < mid) ? lower + 1 : lower;
			upper = (upper > mid) ? upper -1 : upper;
		}//end while
		
		//System.out.println("Run on lower half");
		nameSort(start, mid+1);
		//System.out.println("Run on upper half");
		nameSort(mid, end);
	}//end nameSort method

	public void remove(OrderDetail order) {
		int i;
		if (size <= 0) {//if there are no elements to remove
			throw new NoSuchElementException();
		}//end if
		//System.out.println("Searching in nameOrdered");
		i = findElement(nameOrdered, order);
		nameOrdered[i] = null;
		nameOrdered[i] = nameOrdered[size - 1];
		
		//System.out.println("Searching in numOrdered");
		i= findElement(numOrdered, order);
		numOrdered[i] = null;
		numOrdered[i] = numOrdered[size - 1];
		size--;
		nameSort(0, size);
		numSort(0, size);
	}//end remove method

	/**
	 * findElement
	 *
	 * @param nameOrdered2
	 * @param order
	 */
	private int findElement(OrderDetail[] orders, OrderDetail order)
	{
		for(int i = 0; i < orders.length; i++) {//for each element in orders
			if(orders[i] == order) {//if the order has been found
				//System.out.println("Found");
				return i;
			}//end if
		}//end for
		//System.out.println("Not found");
		return -1;
	}
}//end class
