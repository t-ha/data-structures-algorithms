// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.08.15
// HW1

// a circular array that functions as a queue
// implements the StringQueue interface
public class ArrayQueue implements StringQueue {
	
	private String[] queueArray; // the queue
	private int size; // number of elements in the queue
	private int front; // index of the front of the queue
	private int back; // index of the end of the queue
	
	/**
	 * initialize an empty ArrayQueue with a size of 100
	 */
	public ArrayQueue(){
		queueArray = new String[100];
		size = 0;
		front = 0;
		back = -1;
	}
	
	/**
	 * initialize an empty ArrayQueue with the given size
	 * @param startSize: the size to initialize the ArrayQueue
	 */
	public ArrayQueue(int startSize){
		queueArray = new String[startSize];
		size = 0;
		front = 0;
		back = -1;
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
	 * @exception if the queue is full, throw an IndexOutOfBoundsException
	 */
	public void enqueue(String toEnqueue){
		if (isFull()) {
			throw new IndexOutOfBoundsException("You can't enqueue to a full queue.");
		}
		int index;
		
		// find the "back" of the array, the location to append the string to
		if (isEmpty()) {
			index = front;
		} else {
			index = (back + 1) % queueArray.length;
		}
		queueArray[index] = toEnqueue;
		back = index;
		size += 1; // increment the size
	}
	
	/**
	 * @function removes the string from the front of the queue
	 * @return the string from the front of the queue
	 * @exception if the queue is empty, throw an IndexOutOfBoudsException
	 */
	public String dequeue(){
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Can't dequeue an empty queue.");
		}
		String toDequeue = queueArray[front]; // store the dequeued string
		front = (front + 1) % queueArray.length;
		size -= 1; // decrement the size
		return toDequeue;
	}
	
	/**
	 * 
	 * @return returns true if the queue is empty, false otherwise
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * 
	 * @return returns true if the queue is full, false otherwise
	 */
	public boolean isFull(){
		return size == queueArray.length;
	}
	
/****************************************************************/
// These additional methods are for testing purposes only! Uncomment them
//	 in addition to using QueueTest.java
	/*
	public int getFront() {
		return front;
	}
	
	public int getBack() {
		return back;
	}
	
	// returns the ENTIRE ArrayQueue as a String
	public String toString() {
		String result = "";
		for (int i = 0; i < queueArray.length; i++) {
			result += queueArray[i] + " ";
		}
		return result;
	}
	*/
/****************************************************************/
}