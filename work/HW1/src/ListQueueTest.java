// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.08.15
// HW1

// a class that tests functionality of the ListQueue class
// MUST UNCOMMENT toString method in ListQueue.java
public class ListQueueTest {
	public static void main(String[] args) {
		ListQueue q = new ListQueue();
		
//		q.dequeue(); // causes error (dequeue on an empty queue)
		System.out.println("enqueue: 1 2");
		q.enqueue("1");
		q.enqueue("2");
		System.out.println(q + "\n");
		System.out.println("dequeue: " + q.dequeue());
		System.out.println(q + "\n");
		System.out.println("dequeue: " + q.dequeue());
		System.out.println(q + "\n");
		
		System.out.println("enqueue: 4 5 6 7");
		q.enqueue("4");
		q.enqueue("5");
		q.enqueue("6");
		q.enqueue("7");
		System.out.println(q + "\n");
		
		System.out.println("dequeue: " + q.dequeue());
		System.out.println(q);
		System.out.println("dequeue: " + q.dequeue());
		System.out.println(q);
		System.out.println("dequeue: " + q.dequeue());
		System.out.println(q);
	}
}
