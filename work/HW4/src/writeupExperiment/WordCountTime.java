package writeupExperiment;

import java.io.IOException;
import java.util.Arrays;

import providedCode.*;
import shake_n_bacon.*;

/**
 * USED FOR TIMING
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 *
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCountTime {

	// Implement a method that returns an array of DataCount objects
	// containing each unique word.
	private static DataCount[] getCountsArray(DataCounter counter) {
		DataCount[] list = new DataCount[counter.getSize()];
		SimpleIterator iterator = counter.getIterator();
		int index = 0;
		while (iterator.hasNext()) {
			list[index] = iterator.next();
			index++;
		}
		return list;
	}

	// ////////////////////////////////////////////////////////////////////////
	// /////////////// DO NOT MODIFY ALL THE METHODS BELOW ///////////////////
	// ////////////////////////////////////////////////////////////////////////

	private static long countWords(String file, DataCounter counter) {
		long startTime = System.nanoTime();
		try {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
// TIMING BEGINS			
			
			while (word != null) {
				counter.incCount(word);
				word = reader.nextWord();
			}
			
// TIMING STOPS
		} catch (IOException e) {
			System.err.println("Error processing " + file + " " + e);
			System.exit(1);
		}
		long endTime = System.nanoTime();
		return (endTime - startTime);
	}

	// IMPORTANT: Used for grading. Do not modify the printing *format*!
	private static void printDataCount(DataCount[] counts) {
		int sum = 0;
		for (DataCount c : counts) {
			sum += c.count;
			System.out.println(c.count + "\t" + c.data);
		}
		System.out.println(counts.length);
		System.out.println("sum: " + sum);
	}

	/*
	 * Sort the count array in descending order of count. If two elements have
	 * the same count, they should be ordered according to the comparator. This
	 * code uses insertion sort. The code is generic, but in this project we use
	 * it with DataCount and DataCountStringComparator.
	 * 
	 * @param counts array to be sorted.
	 * 
	 * @param comparator for comparing elements.
	 */
	private static <E> void insertionSort(E[] array, Comparator<E> comparator) {
		for (int i = 1; i < array.length; i++) {
			E x = array[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (comparator.compare(x, array[j]) >= 0) {
					break;
				}
				array[j + 1] = array[j];
			}
			array[j + 1] = x;
		}
	}

	/*
	 * Print error message and exit
	 */
	private static void usage() {
		System.err
				.println("Usage: [-s | -o] <filename of document to analyze>");
		System.err.println("-s for hashtable with separate chaining");
		System.err.println("-o for hashtable with open addressing");
		System.exit(1);
	}

	/**
	 * Entry of the program
	 * 
	 * @param args
	 *            the input arguments of this program
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
		}

		String firstArg = args[0].toLowerCase();
		DataCounter counter = null;
		if (firstArg.equals("-s")) {
			counter = new HashTable_SC(new StringComparator(),
					new StringHasher());
		} else if (firstArg.equals("-o")) {
			counter = new HashTable_OA(new StringComparator(),
					new StringHasher());
		} else {
			usage();
		}

		long elapsed = countWords(args[1], counter);
		DataCount[] counts = getCountsArray(counter);
		insertionSort(counts, new DataCountStringComparator());
		printDataCount(counts);
		System.out.println("\n");
		System.out.println("Time elapsed: " + elapsed + " nanoseconds");
		
	}
}
