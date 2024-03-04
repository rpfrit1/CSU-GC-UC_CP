/** BagInterface.java
*An interface that describes the operations of a bag of objects.
*@author Frank M. Carrano
* Used as part of CSC400 "Data Structures and Algorythms" class at Colorado State University - Global Campus
*/
package interfaces;
public interface BagInterface<T>
{
	/**
Add a new entry to this bag.
@param newEntry The object to be added as a new entry.
@return true if the addition is successful, or false if not.
*/
public boolean add(T newEntry);

/**
Retrieve, and place into an array, all entries that are in this bag.
@return A newly allocated array of all the entries in the bag.
Note: If the bag is empty, the returned array is empty.
*/
public T[] toArray();

/**
Check whether this bag is empty.
@return true if the bag is empty, or false if not.
*/
public boolean isEmpty();

/**
Get the current number of entries in this bag.
@return The integer number of entries currently in the bag.
*/
public int getCurrentSize();

/**
Count the number of times a given entry appears in this bag.
@param anEntry The entry to be counted.
@return The number of times anEntry appears in the bag.
*/
public int getFrequencyOf(T anEntry);

/**
Test whether this bag contains a given entry.
@param anEntry The entry to locate.
@return true if the bag contains anEntry, or false if not.
*/
public boolean contains(T anEntry);

/**
Remove all entries from this bag.
*/
public void clear();

/**
Remove one unspecified entry from this bag, if possible.
@return Either the removed entry, if removal was successful, or null.
*/
public T remove();

/**
Remove one occurrence of a given entry from this bag.
@param anEntry The entry to be removed.
@return true if the removal was successful, or false if not.
     */
public boolean remove(T anEntry);
 }
