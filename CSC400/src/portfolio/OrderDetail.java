/**
 * File Name: OrderDetail.java 
 * Date: Aug 23, 2023
 * Author: Richard Fritsche
 * Project Name: portfolio 
 * Project Purpose: 
 * Algorithm Used: 
 * Program Inputs: 
 * Program Limitations:
 * Program Errors:
 */

package portfolio;

/**
 * @author richard
 * 
 */
public class OrderDetail {
	/**
	 * Constructor
	 *
	 * @param
	 *
	 * @param lastName
	 * @param orderNumber
	 * @param orderCost
	 */
	public OrderDetail(String lastName, int orderNumber, double orderCost)
	{
		veritas = false;
		this.lastName = lastName;
		this.orderNumber = orderNumber;
		this.orderCost = orderCost;
		veritas = true;
	}//end constructor

	public OrderDetail(String lastName, int orderNumber) {
		this(lastName, orderNumber, 0.0);
	}//end constructor

	/**
	 * Constructor
	 *
	 * If this constructor is ever called, it will invalidate the object
	 * @param
	 *
	 */
	private OrderDetail() {
		veritas = false;
	}//end private constructor
	
	/**
	 * @return the orderCost
	 */
	public double getOrderCost()
	{
		return orderCost;
	}//end getOrderCost method

	/**
	 * @param orderCost the orderCost to set
	 */
	public void setOrderCost(double orderCost)
	{
		this.orderCost = orderCost;
	}//end set OrderCost method

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}//end getLastName method

	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber()
	{
		return orderNumber;
	}//end getOrderNumber method

	//class variables
	private boolean veritas;//boolean variable to confirm object is valid.
	private String lastName;
	private int orderNumber;
	private double orderCost;

	private void isValid() {
		if (!veritas) {
			throw new IllegalStateException();
		}//end if
	}//end isValid method

	public void display() {
		System.out.println("Customer Last Name: " + getLastName());
		System.out.println("Order Number: " + getOrderNumber());
		System.out.printf("Order Total: $%,.2f\n\n", getOrderCost());
	}
}//end class
