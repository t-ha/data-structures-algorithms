// Timothy Ha
// CSE 373
// 04.15.15
// Homework 2

// calculates run times of different algorithms

public class AsymptoticAnalysis {
	
	public static final int NUM_TIMINGS = 8; // number of times to time the code
	
	public static final int N = 4000;			// number of iterations for N.
												// used values: 10^4, 10^5, 10^6, 10^7
	
	public static void main(String[] args) {
		
		for (int timing = 0; timing < NUM_TIMINGS ; timing++) {
			long startTime = System.nanoTime();
			
//			// 1.
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				sum++;
//			}
			
//			// 2.
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					sum++;
//				}
//			}
			
//			// 3.
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < i; j++) {
//					sum++;
//				}
//			}
			
//			// 4.
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N * N; j++) {
//					sum++;
//				}
//			}
			
//			// 5.
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < i; j++) {
//					sum++;
//				}
//				for (int k = 0; k < 8000; k++) {
//					sum++;
//				}
//			}
			
//			// 6.
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < i * i; j++) {
//					if (j % i == 0) {
//						for (int k = 0; k < j; k++) {
//							sum++;
//						}
//					}
//				}
//			}
			
			long endTime = System.nanoTime();
			long elapsed = endTime - startTime;
			System.out.println(elapsed + " nanoseconds elapsed.");
		}
	}

}
