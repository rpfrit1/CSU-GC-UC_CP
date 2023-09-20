/**
 * File Name: Order.java 
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
public class Order{

	private ArrayList<OrderDetail> orders;
	private boolean veritas;//boolian variable to confirm object is valid.
	
	public Order() {
		veritas = false;
		orders = new ArrayList<>();
		veritas = true;
	}//end constructor

	public void add(String name, int orderNum, double orderCost) {
		add(new OrderDetail(name, orderNum, orderCost));
	}//end add method
	
	public void display() {
		if (orders.size() <= 0) {//if there are no elements in the queue
			System.out.println("There are no elements to display");
			return;
		}//end if
		System.out.println("Displaying orders in the order they were entered: ");
		for(OrderDetail order: orders) {//for each order in orders
			order.display();
		}//end for
	}//end display method
	/**
	 * 
	 */
	public void isValid()throws IllegalStateException
	{
		if(!veritas) {//if not valid
			throw new IllegalStateException();
		}//end if
	}//end isValid

	public int size()
	{
		try {
			isValid();
		return orders.size();
		}//end try
		catch (IllegalStateException e) {//if the Order Queue is not valid
			return 0;
		}//end catch
	}

	public boolean isEmpty()
	{
		return orders.isEmpty();
	}

	public OrderDetail[] toArray()
	{
		return (OrderDetail[]) orders.toArray();
	}//end toArray method

	public void clear()
	{
		orders.clear();
		
	}

	public boolean add(OrderDetail o)
	{
		try {
			isValid();
			return orders.add(o);
		}//end try
		catch (IllegalStateException e) {
			return false;
		}//end catch
	}//end add method

	public boolean offer(OrderDetail e)
	{
		return add(e);
	}//end offer method

	public OrderDetail remove()
	{
		try {
			isValid();
			if (orders.size() <= 0) {
				throw new NoSuchElementException();
			}
			else{
			return orders.remove(0);
			}//end else
		}//end try
		catch (IllegalStateException e) {
			return null;
		}//end catch IllegalStateException
	}//end remove

	public OrderDetail poll()
	{
		return remove();
	}

	public OrderDetail peek()
	{
		return orders.get(0);
	}//end peek method
}//end class
