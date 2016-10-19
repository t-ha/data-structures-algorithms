package shake_n_bacon;

import providedCode.Hasher;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 */

// A hasher class function for strings
public class StringHasher implements Hasher {

	/**
	 * a hashing function for a passed in string
	 * @return: the hash value used to index in a hashtable
	 */
	@Override
	public int hash(String str) {
		int index = 0;
		for (int i = 1; i <= str.length(); i++) {
			int ascii = (int) str.charAt(i - 1);
			index += i * ascii;
		}
		index /= str.length() + 1;
		return index;
	}
}
