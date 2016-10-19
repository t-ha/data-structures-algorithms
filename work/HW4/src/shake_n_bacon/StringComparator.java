package shake_n_bacon;

import providedCode.*;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 */
public class StringComparator implements Comparator<String> {

	/**
	 * comparator  for two strings
	 * @return: negative if s1 comes before s2 alphabetically
	 * positive if s1 comes after s2 alphabetically
	 * 0 if s1 and s2 are equal (the exact same string)
	 */
	@Override
	public int compare(String s1, String s2) {
		int length;
		if (s1.length() <= s2.length()) {
			length = s1.length();
		} else {
			length = s2.length();
		}
		
		for (int i = 0; i < length; i++) {
			int ascii1 = (int) s1.charAt(i);
			int ascii2 = (int) s2.charAt(i);
			if (ascii1 != ascii2) {
				return ascii1 - ascii2;
			}
		}
		return s1.length() - s2.length();
	}
}
