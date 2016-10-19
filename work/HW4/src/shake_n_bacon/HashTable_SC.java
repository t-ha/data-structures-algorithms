package shake_n_bacon;

import providedCode.*;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 */

//A hashtable that uses separate chaining to put elements into the table

public class HashTable_SC extends DataCounter {
	
	private int size; // number of unique elements in the hashtable
	private Comparator<String> c; //comparator
	private Hasher h; //hashing function
	private int[] tableSize;// list of prime number table sizes
	private int primeIndex;// index of table sizes
	private DataNode[] hash; // hashtable
	private final double LOAD_FACTOR = 1.5;	// load factor

	
	// initalizes the separate chaining hashtable
	public HashTable_SC(Comparator<String> c, Hasher h) {
		this.c = c;
		this.h = h;
		tableSize = setPrimeTableSize();
		primeIndex = 0;
		hash = new DataNode[tableSize[primeIndex]];
		size = 0;
	}
	
	/**
	 * increment the count for a data element
	 */
	@Override
	public void incCount(String data) {
		// resize if necessary
		if ((size * 1.0) / tableSize[primeIndex] >= LOAD_FACTOR) {
			size = 0;
			hash = resize();
		}
		
		placement(data, 1, hash, 0);
	}

	/**
	 * return the size of the hashtable
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * the current count for the data, 0 if it's not in the hashtable
	 */
	@Override
	public int getCount(String data) {
		int hashed = h.hash(data);
		int index = hashed % tableSize[primeIndex];
		DataNode current = hash[index];
		while (current != null) {
			if (c.compare(data, hash[index].dc.data) == 0) {
				return hash[index].dc.count;
			}
			current = current.next;
		}
		return 0;
	}

	/**
	 * return an iterator for the elements
	 */
	@Override
	public SimpleIterator getIterator() {
		return new Iterator();
	}
	
	/**
	 * resize the hashtable and rehash it to fit more elements
	 * @return a rehashed table
	 */
	private DataNode[] resize() {
		DataNode[] doubleHash = new DataNode[tableSize[primeIndex + 1]];
		for (int i = 0; i < tableSize[primeIndex]; i++) {
			if (hash[i] != null) {
				DataNode current = hash[i];
				while (current != null) {
					placement(current.dc.data, current.dc.count, doubleHash, 1);
					current = current.next;
				}
			}
		}
		primeIndex++;
		return doubleHash;
	}
	
	/**
	 * places elements in the hashtable
	 * @param data: the element's data
	 * @param count: the element's count
	 * @param hashT: the hashtable
	 * @param rehash: whether this is normal placement or a rehash of the table
	 */
	private void placement(String data, int count, DataNode[] hashT, int rehash) {
		boolean counted = false;
		int index = h.hash(data) % tableSize[primeIndex + rehash];
		DataNode current = hashT[index];
		
		// finding the index of the hashtable
		if (current == null) {
			hashT[index] = new DataNode(new DataCount(data, count));
			size++;
		} else {
			// check for multiple elements in an individual bucket
			while (current != null) {
				if (c.compare(current.dc.data, data) == 0) {
					current.dc.count += count;
					counted = true;
					current = null;
				} else {
					current = current.next;
				}
			}
			if (!counted) {
				DataNode temp = new DataNode(new DataCount(data, count));
				temp.next = hashT[index];
				hashT[index] = temp;
				size++;
			}
		}
	}
	
	/**
	 * an iterator for elements for the counter
	 *
	 */
	private class Iterator implements SimpleIterator {
		private int index; // index
		private int counted; // number of elements gone through
		private DataNode current;
		
		// intialize the iterator
		public Iterator() {
			index = 0;
			counted = 0;
			current = null;
		}
		
		// returns the next DataCount object
		public DataCount next() {
			counted++;
			if (current != null) {
				current = current.next;
			}
			while (current == null) {
				index++;
				current = hash[index];
			}
			return current.dc;
		}
		
		// returns true if there is another DataCount object
		public boolean hasNext() {
			return size > counted;
		}
	}
	
	/**
	 * a list of table sizes (prime numbers)
	 * @return: list of prime number
	 */
	private int[] setPrimeTableSize() {
		int[] primes = new int[16];
		primes[0] = 11;
		primes[1] = 23;
		primes[2] = 53;
		primes[3] = 101;
		primes[4] = 199;
		primes[5] = 401;
		primes[6] = 809;
		primes[7] = 1601;
		primes[8] = 3203;
		primes[9] = 6421;
		primes[10] = 12809;
		primes[11] = 25601;
		primes[12] = 51203;
		primes[13] = 102407;
		primes[14] = 204803;
		primes[15] = 409609;
		return primes;
	}

	public class DataNode {
		
		public DataCount dc; // desired data
		public DataNode next; // a link to the next node
		
		/**
		 * @function initializes the ListNode with given data
		 * @param data
		 */
		public DataNode(DataCount dc) {
			this(dc, null);
		}
		
		public DataNode(DataCount dc, DataNode next) {
			this.dc = dc;
			this.next = next;;
		}

	}
}






