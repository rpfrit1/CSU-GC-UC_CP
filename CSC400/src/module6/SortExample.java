package module6;

/**
 * @author richard
 * 
 */
public class SortExample {
	
	/**
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] intArray = new Integer[10];
		Double[] numArray = new Double[10];
		
		for (int i = 0; i < intArray.length; i++) {//for each element in the array
			intArray[i] = (int)(Math.random() * intArray.length * 10);
			numArray[i] = (Math.random() * numArray.length);
		}//end for
		
		System.out.println("Here is the Integer array before sorting: " + printArray(intArray));
		sortArray(intArray);
		System.out.println("Here is the Integer array after sorting: " + printArray(intArray));
		
		System.out.println("Here is the Double array before sorting: " + printArray(numArray));
		sortArray(numArray);
		System.out.println("Here is the Double array after sorting: " + printArray(numArray));
	}//end main method

	/**
	 * printArray
	 * @param <T>
	 *
	 * @param intArray
	 * @return
	 */
	public static <T extends Comparable<T>> String printArray(T[] arraySort)
	{
		String result = "[\n";
		for (T element : arraySort) {//for each element in the array
			result = result.concat(" [" + element + "]\n");
		}//end for
		return result + "]";
	}//end printArray method

	/**
	 * sortArray
	 *
	 * @param arraySort
	 */
	public static <T extends Comparable<T>> void sortArray(T[] arraySort)
	{
		boolean swap;//verifies if an swap had taken place on the last run
		T temp;
		for(int i = 0; i < arraySort.length; i++) {//for each element in the array
			swap = false;//sets the swap sensor to false
			for(int j = i; j < arraySort.length; j++) {//for each unsorted element in the array
				if(arraySort[j].compareTo(arraySort[i]) < 0) {//if the currently evaluated element is less than the temp int
					swap = true;
					temp = arraySort[j];
					arraySort[j] = arraySort[i];
					arraySort[i] = temp;
				}//end if
			}//end for
			if(!swap) {//if there was not a swap in this run
				break;//stop looping
			}//end if
		}//end for
	}//end sortArray method
}//end class
