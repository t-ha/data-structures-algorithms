// Timothy Ha
// 1367917
// junkwan
// CSE 373
// 04.29.15
// HW3

import java.util.Random;


// Timing client for MyPQ
public class MyPQTiming {
	
	public static final int N = 100000;
	
	public static void main(String[] args) {
		MyPQ list = new MyPQ();
		Random r = new Random();
		
		long tElapsed = 0;
		long tEl = 0;
		for (int times = 0; times < 10; times++) {
			
			// insert timing
			long startTime = System.nanoTime();
			for(int i = 0; i < N; i++) {
				list.insert(r.nextInt(100));
			}
			long endTime = System.nanoTime();
			long elapsed = endTime - startTime;
			System.out.println("insert:    " + elapsed + " nanoseconds elapsed.");
			tElapsed += elapsed;
			
			// deleteMin timing
			int maxSize = list.size();
			long sTime = System.nanoTime();
			for (int i = 0; i < maxSize; i++) {
				list.deleteMin();
			}
			long eTime = System.nanoTime();
			long el = eTime - sTime;
			System.out.println("deleteMin: " + el + " nanoseconds elapsed.");
			tEl += el;
			
			System.out.println("\n**********************\n");
		}
		
		// average insert and deleteMin times
		tElapsed /= 10;
		tEl /= 10;
		System.out.println("insert avg:    " + tElapsed + " nanoseconds elapsed.");
		System.out.println("deleteMin avg: " + tEl + " nanoseconds elapsed.");

	}
}
