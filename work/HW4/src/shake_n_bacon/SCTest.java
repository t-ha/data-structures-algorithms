package shake_n_bacon;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 */

// testing class for Hashtable separate chaining
public class SCTest {
	public static void main(String[] args) {
		StringComparator c = new StringComparator();
		HashTable_SC hash = new HashTable_SC(c, new StringHasher());
		hash.incCount("twoa");
//		System.out.println(hash.hash[6].dc.data); // must make hash public first
		hash.incCount("two");
		System.out.println(hash);
	}
}
