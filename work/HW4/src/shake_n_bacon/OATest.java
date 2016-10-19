package shake_n_bacon;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 */

// testing class for hashtable open addressing
public class OATest {
	public static void main(String[] args) {
		StringComparator c = new StringComparator();
		HashTable_OA hash = new HashTable_OA(c, new StringHasher());
		hash.incCount("one");
//		System.out.println(hash.hash[4]);  // works after making hash public
		hash.incCount("two");
		System.out.println(hash);
	}
}
