package module4;

import java.util.Scanner;

public class StackExample {
	
	private Node head;
	private int size = 0;
	
	public void push(String node) {
		Node temp = new Node(node, head);
		this.head = temp;
		size ++;
	}//end push method
	
	public String pop() {
		
		Node temp = head;
		this.head = head.getNext();
		size --;
		return temp.getData();
	}//end pop method
	
	public String peek() {
		return head.getData();
	}//end peek method
	
	public boolean isEmpty() {
		return size <= 0;
	}//end isEmpty method
	
	public void clear() {
		head = null;
		this.size = 0;
	}//end clear method
	
	
	public static void main(String[] args) {
		StackExample test = new StackExample();
		Scanner scnr = new Scanner(System.in);
		String testString;
		
		System.out.print("Please enter a sequence of words: ");
		testString = scnr.nextLine();
		scnr.close();
		
		for (int i = 0; i < testString.length(); i++) {
			test.push(testString.charAt(i) + "");
		}//end for

		System.out.println("Now for your message reversed:");
		while(!test.isEmpty()) {
			System.out.print(test.pop());
		}//end while
	}//end main method
	
	/**
	 * Constructor
	 *
	 * @param
	 *
	 * @param head
	 */
	public StackExample(Node head)
	{
		this.head = head;
	}//end constructor

	public StackExample() {
		this(null);
	}//end default constructor
	
	private class Node {
		private String data;
		private Node next;
		
		/**
		 * Constructor
		 *
		 * @param
		 *
		 * @param node
		 * @param head
		 */
		public Node(String node, Node head)
		{
			this.data = node;
			this.next = head;
		}//end constructor
		
		/**
		 * @return the data
		 */
		public String getData()
		{
			return data;
		}
		
		public Node getNext()
		{
			return next;
		}
	}//end Node class
}//end StackExample
