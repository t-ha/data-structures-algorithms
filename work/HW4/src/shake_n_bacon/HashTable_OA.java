package shake_n_bacon;

import providedCode.*;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
 */

// A hashtable that uses open addressing to put elements into the table
public class HashTable_OA extends DataCounter {
	
	private int size;	// number of unique elements in the hashtable
	private Comparator<String> c; // comparator
	private Hasher h; // hashing function
	private int[] tableSize; // list of prime number table sizes
	private int primeIndex;	// index of table sizes
	private DataCount[] hash; // hashtable
	private final double LOAD_FACTOR = 0.5; // load factor
	
	
	// initializes the open addressing hash table
	public HashTable_OA(Comparator<String> c, Hasher h) {
		this.c = c;
		this.h = h;
		tableSize = setPrimeTableSize();
		primeIndex = 0;
		hash = new DataCount[tableSize[primeIndex]];
		size = 0;
	}

	/**
	 * increment the count for a data element
	 */
	@Override
	public void incCount(String data) {
		// resize if necessary
		if (((size * 1.0) / tableSize[primeIndex]) >= LOAD_FACTOR) {
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
		int probe = 0; // iTH probe
		int times = 0;
		int hashed = h.hash(data);
		while (times <= size) {
			int index = (hashed + probe * probe) % tableSize[primeIndex];
			if (hash[index] != null) {
				if (c.compare(data, hash[index].data) == 0) {
					return hash[index].count;
				}
			}
			probe++;
			times++;
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
	private DataCount[] resize() {
		DataCount[] doubleHash = new DataCount[tableSize[primeIndex + 1]];
		for (int i = 0; i < tableSize[primeIndex]; i++) {
			if (hash[i] != null) {
				placement(hash[i].data, hash[i].count, doubleHash, 1);
			}
		}
		primeIndex++;
		return doubleHash;
	}
	
	/**
	 * an iterator for elements for the counter
	 *
	 */
	private class Iterator implements SimpleIterator {
		private int index; // index
		private int counted; // number of elements counted
		
		//initialize the iterator
		public Iterator() {
			index = 0;
			counted = 0;
		}
		
		// return the next DataCount object
		public DataCount next() {
			while (hash[index] == null) {
				index++;
			}
			index++;
			counted++;
			return hash[index - 1];
		}
		
		// returns true if there is another DataCount object
		public boolean hasNext() {
			return size > counted;
		}
	}
	
	/**
	 * places elements in the hashtable accordingly
	 * @param data: the element's data
	 * @param count: the element's count
	 * @param hashT: the hashtable
	 * @param rehash: whether this is normal placement or a rehash of the table
	 */
	private void placement(String data, int count, DataCount[] hashT, int rehash) {
		int probe = 0;
		boolean counted = false;
		
		while (!counted) {
			// quadratic probing
			int index = (h.hash(data) + (probe * probe)) % tableSize[primeIndex + rehash];
			if (hashT[index] == null) {
				hashT[index] = new DataCount(data, count);
				counted = true;
				size++;
			} else {
				if (rehash == 0) {
					if (c.compare(hashT[index].data, data) == 0) {
						hashT[index].count += 1;
						counted = true;
					}
				}
				probe += 1;
			}
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
}