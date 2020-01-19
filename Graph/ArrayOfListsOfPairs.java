//This structure is an array where each entry is the head of its own
//linked list. The linked lists are made up of "bare nodes" (i.e.
//they are not "wrapped" in a nice linked list class). Each node
//in each linked list contains a key-value pair (rather than an single
//value).

//This class will form the baseline for creating a hash table for a
//map that uses separate chaining (each entry in a map is a key-value
//pair). This class will also form a baseline for creating an adjacency
//matrix (where each entry is a key-value pair where keys are the
//"adjacent" node and values are the connection between them). This way
//we have a universal way for to access your internal structures when
//grading these two classes.

//You have a lot of freedom in how you design this class, as long as
//the provided code work as described. However, because this is only
//a baseline for the other classes you are writing, it would be bad
//design on your part to add in anything specific to hash tables
//(such as rehashing) or specific to graphs (such as source/destination
//information for edges). Our advice to you is: (1) keep it simple
//and (2) think before you code.

//Read the "do not edit" section carefully so that you know what is
//already available. This should help you form some ideas of what
//types of things are missing.

//You may: Add additional methods or variables of any type (even
//public!), but again, you _must_ use the provided Node class, the
//provided "storage" instance variable, and all provided methods
//_must still work_.

//You may not import anything from java.util (and you may not use anything
//from java.util in your part of the code). We use java.util.ArrayList in
//the provided code, but it is not available to you.


/**
 *  ArrayOfListsOfPairs.
 * 
 *  @author Abdikarim Abdirahman
 *  @param <K> the Generic K for the type 
 *  @param <V> the Generic V for the type 
 * 
 */

public class ArrayOfListsOfPairs<K,V> {
	//This is your internal structure, you must use this
	//you may not change the type, name, privacy, or anything
	//else about this variable. It is initialized in the
	//provided constructor (see the do-not-edit section)
	//and the Node class is also defined there.

	/**
	 *  storage.
	 */

	private Node<K,V>[] storage;
	/**
	 *  size.
	 */

	private int size=0;


	/**
	 *  adds into storage.
	 *  @param pair the pair you are adding.
	 *  @param index at where you add.
	 *  @return bool
	 */

	public boolean add(KeyValuePair<K, V> pair, int index)
	{
		boolean bool=true;
		Node<K,V> node =new Node<K, V>(pair);
		if (check(storage[index],(K)pair.getKey()))
		{
			if (storage[index]==null)
			{
				storage[index]=node;
			}
			else
			{	
				Node<K,V> tail =getTail(storage[index]);
				tail.next=node;
				tail=node;
			}
			size++;

		}
		return bool;

	}


	/**
	 *  checks if keys repeat.
	 *  @param list the list.
	 *  @param key the key.
	 *  @return bool
	 */

	public boolean check(Node<K,V> list,K key)
	{
		boolean bool=true;
		Node<K,V> temp=list;
		while (temp!=null)
		{
			if (temp.pair.getKey().equals(key))
			{
				bool=false;
			}
			temp=temp.next;
		}
		return bool;

	}


	/**
	 *  gets the tails.
	 *  @param node tail.
	 *  @return temp
	 */
	public Node<K,V> getTail(Node<K,V> node)
	{
		Node<K,V> temp =node;
		while (temp.next!=null)
		{
			temp=temp.next;
		}

		return temp;
	}


	/**
	 *  returns the size.
	 *  @return size
	 */
	public int size()
	{
		return size;
	}

	/**
	 *  get the size of the linked list.
	 *  @param head the head.
	 *  @return count
	 */
	public int sizel(Node<K,V> head)
	{
		int count=0;
		Node<K,V> temp=head;
		while (temp!=null)
		{
			temp=temp.next;
			count++;
		}
		return count;
	}



	/**
	 *  get the pair at a value.
	 *  @param value the value.
	 *  @param size the size.
	 *  @return ans
	 */
	public KeyValuePair<K,V> getk(V value, int size)
	{

		KeyValuePair<K,V> ans=null;



		for (int i=0;i<size;i++)
		{
			Node<K,V> temp=storage[i];

			if (temp!=null)
			{

				while (temp!=null)
				{

					if (temp.pair.getValue().equals(value))
					{
						ans=temp.pair;
					}

					temp=temp.next;
				}
			}




		}



		return ans;
	}

	/**
	 *  get the pair at a key.
	 *  @param key the key.
	 *  @return ans
	 */

	public V getv(K key)
	{

		V ans=null;

		for (int i=0;i<storage.length;i++)
		{
			Node<K,V> temp=storage[i];
			while (temp!=null)
			{


				if (temp.pair.getKey().equals(key))
				{

					ans=temp.pair.getValue();
					break;
				}

				temp=temp.next;


			}

		}
		return ans;
	}

	/**
	 *  replace a value at a key.
	 *  @param key key.
	 *  @param value value.
	 *  @param index index.
	 *  @return bool
	 */
	public boolean replace(K key, V value, int index)
	{
		if (key==null || value==null)
		{
			return false;
		}
		boolean bool=true;
		KeyValuePair<K, V> geth= new KeyValuePair<K, V>(key,value);



		Node<K, V> temp = storage[index];
		while(temp!=null)
		{

			if (temp.pair.getKey().equals(key))
			{
				break;
			}

			temp=temp.next;
		}

		temp.pair=geth;



		return bool;
	}

	/**
	 *  checks if it contains key.
	 *  @param key the key.
	 *  @return bool
	 */

	public boolean contains_key(K key)
	{		
		if (key==null)
		{
			return false;
		}
		boolean bool=false;
		int index=(key.hashCode())%getNumLists();
		Node<K, V> temp = storage[index];
		while(temp!=null)
		{

			if (temp.pair.getKey().equals(key))
			{
				bool=true;
			}
			temp=temp.next;

		}
		return bool;
	}


	/**
	 *  checks if it contains the value.
	 *  @param value the value.
	 *  @return bool
	 */
	public boolean contains_value(V value)
	{		
		if (value==null)
		{
			return false;
		}
		boolean bool=false;
		for (int i=0;i<storage.length;i++)
		{
			Node<K, V> temp = storage[i];
			while(temp!=null)
			{

				if (temp.pair.getValue().equals(value))
				{
					bool=true;
				}
				temp=temp.next;
			}
		}
		return bool;
	}



	/**
	 *  removes the key.
	 *  @param key key.
	 *  @param index index.
	 *  @return bool
	 */
	public boolean remove_key(K key, int index)
	{		
		boolean bool=true;
		if (key==null)
		{
			return false;
		}



		int size=ksize(storage[index],key);

		if (storage[index].pair.getKey().equals(key))
		{
			storage[index]=storage[index].next;
		}

		else
		{
			int count=0;
			Node<K,V> temp =storage[index];
			while(count<size-1)
			{
				temp=temp.next;
				count++;
			}
			temp.next=temp.next.next;
			bool= true;
			size--;
		}
		return bool;

	}



	/**
	 *  removes the index.
	 *  @param key key .
	 *  @param index size.
	 *  @return bool
	 */
	public boolean remove_index(int index, K key)
	{		
		boolean bool=true;

		int ssize=ksize(storage[index],key);
		size=size-ssize;
		storage[index]=null;


		return bool;

	}

	/**
	 *  removes a specific value.
	 *  @param value value.
	 *  @param biggest size.
	 *  @return bool
	 */
	public boolean remove_value(V value, int biggest)
	{		

		boolean bool=true;
		if (value==null)
		{
			return false;
		}


		int count=0;


		for (int i=0;i<biggest;i++)
		{
			int size=vsize(storage[i],value);
			if (size==-1)
			{
				return false;
			}


			if (storage[i].pair.getValue().equals(value))
			{
				storage[i]=storage[i].next;
			}

			else
			{

				Node<K, V> temp = storage[i];
				while (temp!=null)
				{

					if (count==size-1)
					{
						break;


					}
					count++;
					temp=temp.next;
				}


				if (count==size-1)
				{
					temp.next=temp.next.next;
				}

			}

		}

		size--;

		return bool;
	}

	/**
	 *  the postion of a key.
	 *  @param head head.
	 *  @param key key.
	 *  @return num
	 */
	public int ksize(Node<K, V> head,K key)
	{		
		int num=0;
		Node<K, V> temp= head;
		while (temp!=null)
		{
			if (key.equals(temp.pair.getKey()))
			{
				break;
			}
			num++;
			temp=temp.next;
		}
		return num;
	}

	/**
	 *  the psotion of a value.
	 *  @param head head.
	 *  @param value value.
	 *  @return num
	 */

	public int vsize(Node<K, V> head,V value)
	{		
		int num=0;
		boolean bool=false;

		Node<K, V> temp= head;
		while (temp!=null)
		{
			if (value.equals(temp.pair.getValue()))
			{
				bool=true;
				break;
			}
			num++;
			temp=temp.next;
		}
		if (bool==false)
		{
			num=-1;
		}


		return num;
	}

	/**
	 *  rehashes the hashtable.
	 *  @param size the size.
	 */
	public void rehash(int size)
	{
		int old=getNumLists();
		int key=0;
		Node<K,V>[] temp = storage;
		for (int i=0;i<storage.length;i++)
		{
			if (i==0)
			{
				key=old;
			}
			else
			{
				key=i;
			}
			int index=(key)%size;
			temp[index]=storage[i];
		}
		storage=temp;
	}





	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------

	/**
	 *  toString.
	 *  @return super.toString()
	 */
	public String toString() {
		//you may edit this to make string representations of your
		//lists for testing
		return super.toString();
	}
	/**
	 *  main method.
	 *  @param args main method
	 */
	public static void main(String[] args) {


	}




	//--------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	//--------------------------------------------------------


	/**
	 *  This is what one node in one linked list looks like.
	 * 
	 *  @author Abdikarim Abdirahman
	 *  @param <K> the Generic K for the type 
	 *  @param <V> the Generic V for the type 
	 * 
	 */
	public static class Node<K,V> {


		/**
		 *  it contains one key-value pair.	
		 *  @param <K> the Generic K for the type 
		 *  @param <V> the Generic V for the type 
		 */
		public KeyValuePair<K,V> pair;

		/**
		 *  and one pointer to the next node.
		 *  @param <K> the Generic K for the type 
		 *  @param <V> the Generic V for the type 
		 */

		public Node<K,V> next;

		/**
		 *  constructor.
		 *  @param pair pair
		 */
		public Node(KeyValuePair<K,V> pair) {
			this.pair = pair;
		}

		/** 
		 *  convenience constructor.
		 *  @param pair pair
		 *  @param next next
		 */

		public Node(KeyValuePair<K,V> pair, Node<K,V> next) {
			this.pair = pair;
			this.next = next;
		}
	}


	/** 
	 * Creates an array with the specified number of lists-of-pairs.
	 *  @param numLists numLists
	 */
	@SuppressWarnings("unchecked")
	public ArrayOfListsOfPairs(int numLists) {
		storage = (Node<K,V>[]) new Node[numLists];
	}


	/**
	 * Returns the number of lists in this collection.
	 * @return storage.length
	 */
	public int getNumLists() {
		return storage.length;
	}


	/**
	 * Returns all key-value pairs in the specified sublist of this collection.
	 * @param listId listId
	 * @return lst
	 */
	public java.util.ArrayList<KeyValuePair<K,V>> getAllPairs(int listId) {
		java.util.ArrayList<KeyValuePair<K,V>> lst = new java.util.ArrayList<>();

		Node<K,V> current = storage[listId];
		while(current != null) {
			lst.add(current.pair);
			current = current.next;
		}

		return lst;
	}


	/**
	 * Returns all key-value pairs in this collection.
	 * @return lst
	 */
	public java.util.ArrayList<KeyValuePair<K,V>> getAllPairs() {
		java.util.ArrayList<KeyValuePair<K,V>> lst = new java.util.ArrayList<>();

		for(int i = 0; i<storage.length; i++) {
			lst.addAll(getAllPairs(i));
		}

		return lst;
	}
}