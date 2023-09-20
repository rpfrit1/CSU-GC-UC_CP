/**
 * File Name: ShoppingCart.java
 * 
 * Date: July 30, 2023
 * 
 * Author: Richard Fritsche
 * 
 * Project Name: Module 2
 * 
 * Project Purpose: Build a Bag ADT using an Array
 * 
 * Algorithm Used: Use of Array implementation of the Bag ADT, dynamic use of
 * Array with an upper limit, add and remove elements from the bag, verify
 * contents, display contents, verify validity for data integrity.
 * 
 * Program Inputs: generic <T> objects created by another file
 * 
 * Program Limitations: bag can only hold a single data type. For different data
 * types, need to create different bags or use a parent data type.
 * 
 * Program Errors: isValid throws IllegalStateException.
 */

package module2;

import java.util.Arrays;

import interfaces.BagInterface;

/**
 * ShoppingCart<T>
 * 
 * @author Richard Fritsche
 * 
 *             Creates a Shopping Cart to store objects of a particular
 *             (generic) type
 * 
 * @param <T>
 */

public class ShoppingCart<T> implements BagInterface<T>
{
	private static final int DEFAULT_CAPACITY = 7;// The default number of items contained in the cart
	private static final int MAX_CAPACITY = 1000;// The highest number of items in the cart
	private T[] myBag;
	private int numberofProducts;
	private boolean veritas;// boolean variable to confirm object is valid.

	/**
	 * Default Constructor
	 *
	 * Creates a ShoppingCart object that will store a bag of objects of generic
	 * type <T>.
	 * 
	 * @param creates
	 *                    a cart of the default capacity size
	 *
	 */
	public ShoppingCart()
	{
		this(DEFAULT_CAPACITY);
	}// end default constructor

	/**
	 * Constructor
	 *
	 * Creates a ShoppingCart object that will store a bag of objects of generic
	 * type <T>.
	 * 
	 * @param initial
	 *                    capacity of the bag
	 */
	@SuppressWarnings("unchecked")
	public ShoppingCart(int capacity)
	{
		// set integrity variable to false so cannot be used until fully created
		veritas = false;

		// create the bag with the given capacity and set the number of Products
		// variable to show empty
		this.myBag = (T[]) new Object[capacity];
		numberofProducts = 0;

		// set the integrity variable to true, reflecting the object is completely
		// created
		veritas = true;
	}// end constructor

	/**
	 * add
	 * 
	 * This method will verify there is space in the ShoppingCart and then adds an
	 * item to the cart.
	 *
	 * @param <T>
	 *                new item to be added to the cart
	 * 
	 * @return boolean whether the add was successful
	 */
	@Override
	public boolean add(T newEntry)
	{
		try
		{
			// verify if the object is valid, throws exception if not valid
			isValid();

			if (isFull())
			{// If the array is full
				if (!expandBag())
				{// if the bag cannot be expanded
					return false;
				}// end if bag cannot be expanded
			}// end if array full

			// add new item into the bag and increment the bag size
			myBag[numberofProducts] = newEntry;
			numberofProducts++;

			// return success
			return true;
		}// end try

		catch (IllegalStateException e)
		{// if the object is in an illegal State
			return false;
		}// end catch
	}// end add method

	/**
	 * clear
	 * 
	 * Clears the contents of the cart and sets the size to 0 without changing the
	 * capacity
	 *
	 */
	@Override
	public void clear()
	{
		for (numberofProducts--; numberofProducts >= 0; numberofProducts--)
		{// for each element in the array
			myBag[numberofProducts] = null;// set this element as null
		}// end for
		numberofProducts++;
	}// end clear method

	/**
	 * contains
	 *
	 * Checks if a given item is in the cart. Returns whether it is contained or
	 * not.
	 * 
	 * @param <T>
	 *                the item to be checked for
	 * 
	 * @return boolean true if found false if not found
	 */
	@Override
	public boolean contains(T anEntry)
	{
		// set the boolean that shows if the item was found
		boolean found = false;

		try
		{
			// verify if the object is in a valid state
			isValid();

			for (int i = 0; i < numberofProducts && !found; i++)
			{// for each element in the bag
				found = myBag[i].equals(anEntry);// set found to if this element in the bag equals the passed item.
			}// end for
			return found;
		}// end try

		catch (IllegalStateException e)
		{// if the object is in an illegal state
			return found;
		}// end catch
	}// end contains method

	/**
	 * expandBag
	 *
	 * This method expands the bag that holds all of the items within and returns if
	 * it was successful.
	 * 
	 * @return boolean reflecting the success factor of the expand
	 */
	private boolean expandBag()
	{
		// boolean that shows the success level.
		boolean result = false;
		if (myBag.length < MAX_CAPACITY)
		{// if the length of the array can be expanded
			// set new size for array to the lesser of 2 times the old length or the Max
			// Capacity
			int newSize = ((myBag.length * 2) < MAX_CAPACITY) ? myBag.length * 2 : MAX_CAPACITY;

			// System.err.println("Increasing size. New size will be: " + newSize);
			myBag = Arrays.copyOf(myBag, newSize);
			result = true;
		}// end else
		return result;
	}// end expandBag method

	/**
	 * getCurrentSize
	 * 
	 * This method looks at what the current size of the bag is and returns it.
	 * 
	 * @return int the current number of items in the cart
	 *
	 */
	@Override
	public int getCurrentSize()
	{
		try
		{
			// verify object is valid
			isValid();

			// return the number of products in the cart
			return numberofProducts;
		}// end try

		catch (IllegalStateException e)
		{// if the object is in an illegal state
			return 0;
		}// end catch
	}// end getCurrentSize method

	/**
	 * getFrequencyOf
	 * 
	 * Counts the number of times a given item is in the cart
	 * 
	 * @param <T> the item to search for
	 * 
	 * @return int the number of times the item is found
	 */
	@Override
	public int getFrequencyOf(T anEntry)
	{
		//initialize the count variable
		int count = 0;
		
		try
		{
			//verify if object is in a valid state
			isValid();
			
			for (int i = 0; i < numberofProducts; i++)
			{// for each element in the bag
				count += (myBag[i].equals(anEntry)) ? 1 : 0;// if myBag[i] is equal to the passed entry, add 1.
															// Otherwise, add 0
			}// end for
			
			return count;
		}// end try
		
		catch (IllegalStateException e)
		{// if the object is in an illegal state
			return count;
		}// end catch
	}// end getFrequencyOf method

	/**
	 * isEmpty
	 * 
	 * This method verifies if the cart is empty of items. Returns true if the cart is empty. Returns false if there are items in the cart.
	 * 
	 * @return boolean of if the cart is empty.
	 *
	 */
	@Override
	public boolean isEmpty()
	{
		try {
			//verify if the object is in a valid state
			isValid();
			return numberofProducts == 0;
		}//end try
		
		catch (IllegalStateException e) {//if the object is not in a valid state
			//a non-valid object is always considered empty
			return true;
		}//end catch
	}//end isEmpty method

	/**
	 * isFull
	 *
	 * Checks if the cart is full or can have more added to it. Returns true if full, false if there is more room. 
	 * @return boolean true if full, false if not.
	 */
	public boolean isFull()
	{
		try {
			//verify if object is valid
			isValid();
			
			//return if the number of items in the cart is the same as the length of the array.
			return numberofProducts == myBag.length;
		}//end try
		
		catch (IllegalStateException e) {//if the object is not in a valid state
			//an illegal state cart is always full
			return true;
		}//end catch
	}// end isFull method

	/**
	 * isValid
	 * 
	 * Verifies if the object is in a valid state. Throws IllegalStateException if not in valid state. Otherwise, just returns to the calling method.
	 *
	 * @throws IllegalStateException
	 */
	private void isValid() throws IllegalStateException
	{
		if (!veritas)
		{//if the validity variable is false
			throw new IllegalStateException("Shopping Cart is not properly setup.");
		}// end if
	}// end isValid method

	/**
	 * remove
	 * 
	 * Removes the last item from the cart and returns it.
	 * 
	 * @return T last element in the cart.
	 *
	 */
	@Override
	public T remove()
	{
		T result = null;
		try
		{
			//verify if the object is valid 
			isValid();
			
			//sets the return item to the last valid place of the array. 
			result = myBag[numberofProducts - 1];
			
			//removes the item from the array and returns it to to calling method.
			remove(numberofProducts - 1);
			return result;
		}// end try
		
		catch (IllegalStateException e)
		{//if the object is in an illegal state
			return result;
		}// end catch
	}// end remove last method

	/**
	 * remove
	 * 
	 * Removes an element at a specific location in the cart. Replaces it with the last item in the cart, sets the last spot in the cart to null, and decrements the number of items in the cart by one.
	 *
	 * @param int the index of the item to remove
	 * 
	 * @return boolean, the result of if the remove was successful.
	 */
	private boolean remove(int i)
	{
		boolean result = false;
		if (numberofProducts > 0)
		{// if there are elements in the cart
			if (i != numberofProducts - 1)
			{// if it is the last element of the cart
				myBag[i] = myBag[numberofProducts - 1];
			}// end if i is not the same as numberofProducts - 1
				// decrement the number of Products and set the last element to null.
			myBag[--numberofProducts] = null;
			result = true;
		}// end if there are elements

		// return result
		return result;
	}// end remove by index@Override

	/**
	 * remove
	 * 
	 * Finds and removes the first instance of aspecific item from the cart.
	 * 
	 *  @param <T> the item to remove
	 *  
	 *  @return the success of the remove
	 *
	 */
	public boolean remove(T anEntry)
	{
		boolean found = false;
		try
		{
			//verify if the object is in a valid state
			isValid();
			
			for (int i = 0; i < numberofProducts && !found; i++)
			{// for each element until element is found
				if (myBag[i].equals(anEntry))
				{// if the element was found
					found = remove(i);
				}// end if
			}// end for

			return found;
		}// end try
		catch (IllegalStateException e)
		{//if the object is in an illegal state
			return found;
		}// end catch
	}// end remove specific element method

	/**
	 * toArray
	 * 
	 * Creates and returns a copy of the cart. Returns null if it cannot make a clone.
	 * 
	 * @return <T>[] array of the cart contents
	 *
	 */
	@Override
	public T[] toArray()
	{
		try
		{
			//verify if the object is in a valid state
			isValid();
			//return a copy of the array
			return myBag.clone();
		}// end try
		
		catch (IllegalStateException e)
		{// if the object is in an illegal state
			return null;
		}// end catch
	}// end toArray method

	/**
	 * toString
	 * 
	 * Creates and returns a String representation of the cart
	 * 
	 * @return String of the cart and contents
	 *
	 */
	@Override
	public String toString()
	{
		return "numberofProducts= " + numberofProducts + "; ShoppingCart [myBag= " + Arrays.toString(myBag) + "]";
	}//end toString method

}// end ShoppingCart class
