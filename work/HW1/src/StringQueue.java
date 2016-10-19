// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.08.15
// HW1

// An interface for ArrayQueue and ListQueue
public interface StringQueue {
	
	/**
	 * @function returns the size of the queue
	 * @return size
	 */
	public int getSize();
	
	/**
	 * @function adds an item to the end of the queue
	 * @param toEnqueue: the item to add
	 */
	public void enqueue(String toEnqueue);
	
	/**
	 * @function removes an item from the front of the queue
	 * @return removed item
	 */
	public String dequeue();
	
	/**
	 * @function asks whether the queue is empty or not
	 * @return if the queue is empty or not
	 */
	public boolean isEmpty();
}
