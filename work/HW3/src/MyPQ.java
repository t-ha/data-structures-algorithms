// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.29.15
// HW3

/**
 * Linked ListNode implementation of a Priority Queue
 * @implements PriorityQueue
 */
public class MyPQ implements PriorityQueue {
	
	private int size; // number of elements in the priority queue
	private ListNode front; // front of the pq
	private ListNode back; // back of the pq
	
	/**
	 * @function initializes the priority queue
	 */
	public MyPQ() {
		makeEmpty();
	}
	
	/**
	 * @function determines if the heap is empty
	 * @return if the heap is empty or not
	 */
	public boolean isEmpty(){
		return front == null;
	}
	
	/**
	 * @function returns the size of the heap
	 * @return returns the size of the heap
	 */
	public int size() {
		return size;
	}
	
	/**
	 * @function if the heap is not empty, return the minimum value of the heap
	 * @exception if the heap is empty, throw a EmptyPQException
	 * @return returns the min value
	 */
	public double findMin() {
		if (isEmpty()) {
			throw new EmptyPQException();
		}
		return front.data;
	}
	
	/**
	 * @function adds the passed in value to the heap
	 * @param the desired value
	 */
	public void insert(double x) {
		if (isEmpty()) {
			front = new ListNode(x); // set the front to the added string
			back = front;
		} else {
			back.next = new ListNode(x);
			back = back.next;
		}
		size++;
		percolateUp(x);
	}
	
	/**
	 * @function deletes the min value and returns it
	 * @exception if the heap is empty, throw EmptyPQException
	 * @return the minimum value
	 */
	public double deleteMin() {
		if (isEmpty()) {
			throw new EmptyPQException();
		}
		double min = front.data;
		size--;
		
		if (front.next != null) { // more than one value in the priority queue
			percolateDown(back.data);
		} else { // one value left
			front = null;
		}
		return min;
	}
	
	/**
	 * @function empties the heap
	 */
	public void makeEmpty() {
		front = null;
		back = null;
		size = 0;
	}
	
	/**
	 * @function arranges the passed in value for the correct priority
	 * @param x
	 */
	private void percolateUp(double x) {
		ListNode index = back;
		ListNode temp = front;
		int current = size;
		for (int i = 1; i < current / 2; i++) {
			temp = temp.next;
		}
		
		// arranges the values by priority
		while (index != null && (index.data < temp.data)) {
			double value = temp.data;
			temp.data = x;
			index.data = value;
			index = temp;
			temp = front;
			current /= 2;
			for (int i = 1; i < current / 2; i++) {
				temp = temp.next;
			}
		}
		
	}
	
	/**
	 * @function arranges the value according to the priority
	 * @param x
	 */
	private void percolateDown(double x) {
		front.data = back.data;
		ListNode temp = front;
		while (temp.next.next != null) {
			temp = temp.next;
		}
		back = temp;
		back.next = null;
		
		ListNode current = front;
		temp = front;
		int index = 1;
		while (index * 2 <= size) {
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			
			index *= 2;
			if (temp.next != null) { // chooses the left or right node
				if (temp.next.data <= temp.data) {
					index++;
					temp = temp.next;
				}
			}
			
			// switches the values according to priority
			if (temp.data < x) {
				current.data = temp.data;
				temp.data = x;
			} else {
				break;
			}
			current = temp;
		}
	}
	 // testing toString method
	public String toString() {
		String result = "";
		ListNode temp = front;
		while(temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		return result;
	}	
	
	// ListNode class for LinkedList
	private class ListNode {
		
		public double data; // desired data
		public ListNode next; // a link to the next node
		
		/**
		 * @function initializes the ListNode with given data
		 * @param data
		 */
		public ListNode(double data) {
			this(data, null);
		}
		
		public ListNode(double data, ListNode next) {
			this.data = data;
			this.next = next;;
		}

	}
}
