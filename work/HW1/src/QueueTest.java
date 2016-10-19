// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.08.15
// HW1

// Class to test the ArrayQueue in various ways
// must uncomment the commented methods in ArrayQueue first!!
public class QueueTest {
	public static void main(String[] args) {
		ArrayQueue q = new ArrayQueue(3);
//		q.dequeue();						// causes error (dequeue on empty queue)
		q.enqueue("a");
		q.enqueue("b");
		System.out.println("full: " + q);
		System.out.println("dequeue: " + q.dequeue());
		System.out.println("full after dequeue: " + q);
		System.out.println("front: " + q.getFront());
		System.out.println("back: " + q.getBack());
		System.out.println();
		
		q.enqueue("1");
		q.enqueue("2");
		System.out.println("full: " + q);
		System.out.println("front: " + q.getFront());
		System.out.println("back: " + q.getBack());
//		q.enqueue("3");						// causes error (enqueue on full queue)
		System.out.println();
		
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
//		System.out.println(q.dequeue());	// causes error (dequeue on empty queue)
	}
}
