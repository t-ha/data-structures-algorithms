package shake_n_bacon;

import providedCode.*;
/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 */
public class GenTest {
	public static void main(String[] args) {
		
		// checking for String compareTo and StringComparator consistency
		String s1 = "abc";
		String s2 = "sdf";
		System.out.println(s1.compareTo(s2));  // -1, s1 comes before s2
		System.out.println();
		
		//
		StringComparator c = new StringComparator();
		System.out.println(c.compare(s1, s2));
		System.out.println();
		
		Comparator<String> c2 = new StringComparator();
		System.out.println(c.compare(s1, s2));
		System.out.println();
		
		// StringHasher tests
		StringHasher strHasher = new StringHasher();
		System.out.println(strHasher.hash("two"));
		System.out.println(strHasher.hash("twoa"));
		System.out.println(strHasher.hash("three"));
		System.out.println();
		System.out.println();
	}
}
