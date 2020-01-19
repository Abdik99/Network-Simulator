//Hash table with separate chaining. Each key and value gets
//placed together as a single entry in the table. The hash code
//of a key is used to place the pair in the table and to look
//for it again. Note that KeyValuePair is a structure for
//ArrayOfListsOfPairs, this part of the code needs to be able to
//deal with keys and values separately.

import java.util.Collection;

/**
 *  Explain.
 * 
 *  @author Abdikarim Abdirahman
 *  @param <K> Explain.
 *  @param <V> Explain.
 * 
 */
public class HashTable<K,V> {
	//This is the minimum number of slots in the hash table
	//Do not change this.

	/**
	 *  Explain.
	 */
	private static final int MIN_SLOTS = 2;

	//You must use this as your internal storage, you may not change
	//the type, name, privacy, or anything else about this variable.

	/**
	 *  Explain.
	 */
	protected ArrayOfListsOfPairs<K,V> storage;
	/**
	 *  Explain.
	 */
	private int nums=0;

	//If the number of slots requested is less than the minimum
	//number of slots, use the minimum instead.

	/**
	 *  Explain.
	 *  
	 *  @param numSlots Explain.
	 */
	public HashTable(int numSlots) {
		nums=numSlots;
		storage= new ArrayOfListsOfPairs<K,V>(numSlots);


	}

	//The number of key-value entries in the table.
	//O(1)

	/**
	 *  Explain.
	 *  @return size.
	 */
	public int size() {

		return storage.size();
	}

	//Returns the number of slots in the table.
	//O(1)

	/**
	 *  Explain.
	 *  @return numslots.
	 */

	public int getNumSlots() {
		return nums;
	}

	//Returns the load on the table.
	//O(1)

	/**
	 *  Explain.
	 *  @return load.
	 */
	public double getLoad() {
		double load=((size())/(nums));
		return load;
	}

	//If the key is not in the table, add the key-value entry to the table
	//and return true. If unable to add the entry, return false. Keys and
	//values are _not_ allowed to be null in this table, so return false if
	//either of those are provided instead of trying to add them.

	//If the load goes above 3 after adding an entry, this method should
	//rehash to three times the number of slots.

	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.
	/**
	 *  Explain.
	 *  @param key Explain.
	 *  @param value Explain.
	 *  @return bool.
	 */
	public boolean add(K key, V value) {
		if (key==null || value==null)
		{
			return false;
		}
		KeyValuePair<K, V> pair= new KeyValuePair<K, V>(key,value);
		int index=pair.hashCode()%nums;

		boolean bool=storage.add(pair, index);
		if (getLoad()>3)
		{
			rehash(3*nums);
		}

		return bool;
	}

	//Rehashes the table to the given new size (new number of
	//slots). If the new size is less than the minimum number
	//of slots, use the minimum instead.

	//Must run in worst case O(n+m) where n is the number of
	//key-value pairs in the table and m is the number of
	//"slots" in the table.

	/**
	 *  Explain.
	 *  @param newSize Explain.
	 */
	public void rehash(int newSize) {
		if (newSize<MIN_SLOTS)
		{	
			storage.rehash(MIN_SLOTS);
		}
		else
		{
			storage.rehash(newSize);
		}
	}

	//If the key requested is in the table, change the associated value
	//to the provided value and return true. Otherwise return false.

	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.


	/**
	 *  Explain.
	 *  @param key Explain.
	 *  @param value Explain.
	 *  @return bool.
	 */
	public boolean replace(K key, V value) {



		int index=(key.hashCode())%nums;
		return storage.replace(key,value,index);
	}

	//If the key requested is in the table, remove the key-value entry
	//and return true. Otherwise return false.

	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.

	/**
	 *  Explain.
	 *  @param key Explain.
	 *  @return bool Explain.
	 */
	public boolean remove(K key) {

		int index=(key.hashCode())%nums;
		return storage.remove_key(key,index);

	}

	//If the key requested is in the table, return true. Otherwise return false.

	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.


	/**
	 *  Explain.
	 *  @param key Explain.
	 *  @return bool.
	 */
	public boolean contains(K key) {


		return storage.contains_key(key);
	}

	//If the key requested is in the table, return the associated value.
	//Otherwise return null.

	//Must run in worst case O(n) and average case O(n/m) where n is the
	//number of key-value pairs in the table and m is the number of "slots"
	//in the table.

	/**
	 *  Explain.
	 *  @param key Explain.
	 *  @return null.
	 */
	public V get(K key) {
		int index=(key.hashCode())%nums;
		return storage.getv(key);
	}

	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------

	/**
	 *  toString.
	 *  @return super.toString().
	 */
	public String toString() {
		//you may edit this to make string representations of your
		//lists for testing
		return super.toString();
	}

	/**
	 *  main method.
	 *  @param args Explain.
	 */

	public static void main(String[] args) {


	}

	//--------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	//--------------------------------------------------------


	//This will be used to check that you are setting
	//the storage up correctly... it's very important
	//that you (1) are using the ArrayOfListsOfPairs 
	//provided and (2) don't edit this at all

	/**
	 *  Explain.
	 *  @return storage.
	 */
	public ArrayOfListsOfPairs<K,V> getInternalTable() {
		return storage;
	}
}