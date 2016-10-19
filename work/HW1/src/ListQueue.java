// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.08.15
// HW1

// a queue made up of ListQueueNodes
// implements StringQueue
public class ListQueue implements StringQueue {
	
	private int size; // number of elements in the queue
	private ListQueueNode front; // front of the queue
	private ListQueueNode back; // back of the queue
	
	/**
	 * @function initialize an empty ListQueue
	 */
	public ListQueue(){
		front = null;
		back = null;
		size = 0;
	}
	

	/**
	 * @function returns the number of elements in the queue
	 * @return size
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * @function adds a string to the end of the queue
	 * @param toEnqueue: the input to be inserted
	 */
	public void enqueue(String toEnqueue){
		if (isEmpty()) {
			front = new ListQueueNode(toEnqueue); // set the front to the added string
			back = front;
		} else {
			back.next = new ListQueueNode(toEnqueue);
			back = back.next;
		}
		size += 1; // increment the size
	}
	
	/**
	 * @function removes the string from the front of the queue
	 * @return the string from the front of the queue
	 * @exception if the queue is empty throw an IndexOutOfBoundsException
	 */
	public String dequeue(){
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Cannot dequeue an empty queue");
		}
		String toDequeue = front.data; // store the dequeued string
		front = front.next;	// adjust the queue
		size -= 1; // decrement the size
		return toDequeue;
	}
	
	/**
	 * 
	 * @return returns true if the queue is empty, false otherwise
	 */
	public boolean isEmpty(){
		return front == null;
	}

	
	/****************************************************************/
	
	// Test method for ListQueue, used in ListQueueTest
	// returns the entire ListQueue as a String
	/*
	public String toString() {
		String result = "";
		ListQueueNode temp = front;
		while(temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		return result;
	}
	*/
	/****************************************************************/
}