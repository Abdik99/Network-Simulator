//This class represents a key-value pair. It is completed for you,
//but you need to add JavaDocs.
/**
 *  Explain.
 * 
 *  @author Abdikarim Abdirahman
 *  @param <K> Explain.
 *  @param <V> Explain.
 */
//--------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
//--------------------------------------------------------

public class KeyValuePair<K,V> {

	/**
	 * Explain.
	 */
	private K key;


	/**
	 * Explain.
	 */
	private V value;

	/**
	 *  Explain.
	 * @param key Explain.
	 * @param value Explain.
	 */
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 *  Explain.
	 *  @return key
	 */
	public K getKey() {
		return key;
	}

	/**
	 *  Explain.
	 *  @return value
	 */
	public V getValue() {
		return value;
	}

	/**
	 *  Explain.
	 *  @param o Explain.
	 *  @return false
	 */
	public boolean equals(Object o) {
		if(o instanceof KeyValuePair) {
			return key.equals(((KeyValuePair)o).key);
		}
		return false;
	}

	/**
	 *  Explain.
	 *  @return key.hashCode()
	 */
	public int hashCode() {
		return key.hashCode();
	}

	/**
	 *  Explain.
	 *  @return "("+key+","+value+")"
	 */
	public String toString() {
		return "("+key+","+value+")";
	}
}