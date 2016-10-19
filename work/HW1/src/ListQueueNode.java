// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.08.15
// HW1

// a single node of a ListQueue
public class ListQueueNode {
	
	public String data; // desired data
	public ListQueueNode next; // a link to the next node
	
	public ListQueueNode(String data) {
		this(data, null);
	}
	
	public ListQueueNode(String data, ListQueueNode next) {
		this.data = data;
		this.next = next;;
	}

}
