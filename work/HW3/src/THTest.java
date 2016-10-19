// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.29.15
// HW3


// Testing client for ThreeHeap
public class THTest {
	public static void main(String[] args) {
		ThreeHeap list = new ThreeHeap();
		
		// insert testing
		list.insert(12);
		list.insert(5);
		list.insert(11);
		list.insert(3);
		list.insert(10);
		list.insert(2);
		list.insert(9);
		list.insert(4);
		list.insert(8);
		list.insert(1);
		list.insert(7);
		list.insert(6);
		list.insert(9);
		
		System.out.println(list + "   " + list.size());
		System.out.println("\n\n\n");
		
		// deleteMin testing
		int maxSize = list.size();
		for (int i = 0; i < maxSize; i++) {
			System.out.println(list.deleteMin());
			System.out.println(list + "   " + list.size());
			System.out.println();
		}

	}
}
