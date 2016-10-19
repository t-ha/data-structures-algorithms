package shake_n_bacon;


import java.io.IOException;

import providedCode.*;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
*/

// finds the variance of word frequencies between two text files
public class Correlator {

	public static void main(String[] args) {
		if (args.length != 3) {
			usage();
		}

		String firstArg = args[0].toLowerCase();
		DataCounter c1 = null;
		DataCounter c2 = null;
		if (firstArg.equals("-s")) {
			c1 = new HashTable_SC(new StringComparator(),
					new StringHasher());
			c2 = new HashTable_SC(new StringComparator(),
					new StringHasher());
		} else if (firstArg.equals("-o")) {
			c1 = new HashTable_OA(new StringComparator(),
					new StringHasher());
			c2 = new HashTable_OA(new StringComparator(),
					new StringHasher());
		} else {
			usage();
		}
		
		// get the total number of words for each file
		int c1word = countWords(args[1], c1);
		int c2word = countWords(args[2], c2);
		
		// initialize small and large counters
		DataCounter small = null;
		DataCounter large = null;
		
		// appropriately set the small total words and sizes and large total words and sizes
		// we only want to go through the smaller data set because we are only looking for 
		// words found in both files
		int size;
		int smallTotal;
		int largeTotal;
		if (c1.getSize() <= c2.getSize()) {
			small = c1;
			large = c2;
			smallTotal = c1word;
			largeTotal = c2word;
			size = c1.getSize();
		} else {
			small = c2;
			large = c1;
			smallTotal = c2word;
			largeTotal= c1word;
			size = c2.getSize();
		}
		
		SimpleIterator iter = small.getIterator();
		// a list of squared differences between word frequencies of two files
		double[] list = normList(iter, small, large, size, smallTotal, largeTotal);
		
		// calculate the variance
		double variance = 0.0;
		for (int i = 0; i < list.length; i++) {
			variance += list[i];
		}
		
		System.out.println(variance);
	}
	
	/**
	 * creates and returns the list of squared differences of word frequencies
	 * @param iter: the iterator
	 * @param small: the smaller data set
	 * @param large: the larger data set
	 * @param size: size
	 * @param smallT: total words of the smaller file
	 * @param largeT: total words of the larger file
	 * @return: the list of squared differences of words frequencies between two files
	 */
	public static double[] normList(SimpleIterator iter, DataCounter small, DataCounter large, 
			int size, int smallT, int largeT) {
		double[] list = new double[size];
		
		int count = 0;
		while(iter.hasNext()) {
			DataCount temp = iter.next();
			// small frequency
			double smallF = (temp.count * 1.0) / smallT;
			if (0.0001 <= smallF && smallF <= 0.01) {
				// only if the smaller file's word frequency is useful do we want to continue
				int largeCount = large.getCount(temp.data);
				if (largeCount > 0) {
					double largeF = (largeCount * 1.0) / largeT;
					if (0.0001 <= largeF && largeF <= 0.01) {
						// only if both frequencies are useful do we want to add them into the list
						list[count] = (largeF - smallF) * (largeF - smallF);
						count++;
					}
				}
			}
		}
		
		return list;
	}
	
	/**
	 * TAKEN FROM WORDCOUNT.JAVA (messageboard said we could use these)
	 * @param file: the text file
	 * @param counter: the hashtable
	 * @return: the total number of words
	 */
	private static int countWords(String file, DataCounter counter) {
		int totalWords = 0;
		try {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
			while (word != null) {
				counter.incCount(word);
				word = reader.nextWord();
				totalWords++;
			}
		} catch (IOException e) {
			System.err.println("Error processing " + file + " " + e);
			System.exit(1);
		}
		return totalWords;
	}
	
	
	/*
	 * Print error message and exit
	 */
	private static void usage() {
		System.err
				.println("Usage: [-s | -o] <filename1> <filename2>");
		System.err.println("-s for hashtable with separate chaining");
		System.err.println("-o for hashtable with open addressing");
		System.exit(1);
	}
}
