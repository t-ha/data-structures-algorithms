// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.29.15
// HW3


import java.util.Arrays; // only used for toString

/**
 * BinaryHeap implementation of a Priority Queue
 * @implements PriorityQueue
 */
public class BinaryHeap implements PriorityQueue {
	
	private int size; // number of elements in the heap
	private double[] heap; // the heap
	private static final int INITIAL_SIZE = 10; // initial capacity of the heap
	
	/**
	 * @function initializes the heap with the default capacity
	 */
	public BinaryHeap() {
		this(INITIAL_SIZE);
	}
	
	/**
	 * @function initializes the heap with the passed in capacity
	 * @param capacity
	 */
	public BinaryHeap(int capacity) {
		heap = new double[capacity + 1];
		size = 0;
	}
	
	
	/**
	 * @function determines if the heap is empty
	 * @return if the heap is empty or not
	 */
	public boolean isEmpty(){
		return size == 0;
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
		return heap[1];
	}
	
	/**
	 * @function adds the passed in value to the heap
	 * @param the desired value
	 */
	public void insert(double x) {
		if (size == heap.length - 1) {
			resize();
		}
		size++;
		heap[size] = x;
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
		double min = heap[1];
		percolateDown(heap[size]);
		size--;
		return min;
	}
	
	/**
	 * @function empties the heap
	 */
	public void makeEmpty() {
		size = 0;
	}
	
//	// testing toString method
//	public String toString() {
//		return "" + Arrays.toString(heap);
//	}
	
	/**
	 * @function doubles the capacity of the heap
	 */
	private void resize() {
		double[] temp = new double[heap.length * 2 - 1];
		for (int j = 1; j <= size; j++) {
			temp[j] = heap[j];
		}
		heap = temp;
	}
	
	/**
	 * @function arranges the passed in value for the correct priority
	 * @param x
	 */
	private void percolateUp(double x) {
		int index = size;
		while (index > 1 && (heap[index] < heap[index / 2])) {
			heap[index] = heap[index / 2];
			heap[index / 2] = x;
			index /= 2;
		}
	}
	
	/**
	 * @function arranges the value according to the priority
	 * @param x
	 */
	private void percolateDown(double x) {
		int index = 1;
		while (2 * index <= size) {
			int left = 2 * index; // left node
			int right = left + 1; // right node
			int replace;
			if (right > size || heap[left] < heap[right]) {
				replace = left;
			} else {
				replace = right;
			}
			
			if (heap[replace] < x) {
				heap[index] = heap[replace];
				index = replace;
			} else { 
				break;
			}
		}
		
		heap[index] = heap[size];
		heap[size] = 0;
	}
}
