import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.DirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

//You may use the Java Collections Framework in this part of your
//project, but note that your primary internal structure is an
//ArrayOfListsOfPairs, you may still find some of these useful
import java.util.*;


/**
 *  the NetWork Class.
 * 
 *  @author Abdikarim Abdirahman
 * 
 */
public class Network implements Graph<Host,Connection>, DirectedGraph<Host,Connection> {
	//We promise the network will never have more than this
	//many nodes... use that knowledge to simplify your code!


	/**
	 *  MAX_NETWORK_SIZE.
	 */
	private static final int MAX_NETWORK_SIZE = 255;

	//Adjacency List: Host 0's outgoing connections will be in list 0
	//and each key-value pair in list 0 is a host-connection pair from
	//host 0, to the host in the pair, using the connection in the pair.
	//You must use this as your internal storage, you may not change
	//the type, name, privacy, or anything else about this variable.



	/**
	 *  storage.
	 *  @param <Host> the Generic Host for the type 
	 *  @param <Connection> the Generic Connection for the type
	 */
	private ArrayOfListsOfPairs<Host,Connection> storage;

	//Any other variables you want here! (Must be private.)
	//You may also add private methods if you'd like, but nothing
	//public.
	/**
	 *  vertices here.
	 *  @param <Host> the Generic Host for the type 
	 */
	private ArrayList<Host> vertices= new ArrayList<Host> ();


	/**
	 *  edges here.
	 *  @param <Connection> the Generic Connection for the type 
	 */
	private ArrayList<Connection> edges= new ArrayList<Connection> ();


	/**
	 *  add the value from storage.
	 *  @param <Host> the Generic Host for the type 
	 *  @param <Connection> the Generic Connection for the type 
	 */

	private ArrayList<KeyValuePair<Host,Connection>> paired= new ArrayList<KeyValuePair<Host,Connection>>();


	/**
	 *  add the other vertex.
	 *  @param <Host> the Generic Host for the type 
	 *  @param <Connection> the Generic Connection for the type 
	 */
	private ArrayList<KeyValuePair<Host,Connection>> naired= new ArrayList<KeyValuePair<Host,Connection>>();


	/**
	 *  empty nodes.
	 *  @param <Host> the Generic Host for the type 
	 */
	private ArrayList<Host> empty= new ArrayList<Host> ();

	/**
	 *  nonempty nodes.
	 *  @param <Connection> the Generic Connection for the type 
	 */
	private ArrayList<Host> nonempty= new ArrayList<Host> ();


	/**
	 *  Constructor.
	 */
	public Network() {

		storage=new ArrayOfListsOfPairs<Host,Connection>(MAX_NETWORK_SIZE);
		//constructor to initialize what you want.
	}

	/**
	 * Returns a view of all edges in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the vertices within the set.
	 * @return a Collection view of all edges in this graph
	 */
	public Collection<Connection> getEdges() {


		return edges;
	}

	/**
	 * Returns a view of all vertices in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the vertices within the set.
	 * @return a Collection view of all vertices in this graph
	 */
	public Collection<Host> getVertices() {
		return vertices;
	}

	/**
	 * Returns true if this graph's vertex collection contains vertex.
	 * Equivalent to getVertices().contains(vertex).
	 * @param vertex the vertex whose presence is being queried
	 * @return true iff this graph contains a vertex vertex
	 */
	public boolean containsVertex(Host vertex) {
		return vertices.contains(vertex);
	}

	/**
	 * Returns true if this graph's edge collection contains edge.
	 * Equivalent to getEdges().contains(edge).
	 * @param edge the edge whose presence is being queried
	 * @return true iff this graph contains an edge edge
	 */
	public boolean containsEdge(Connection edge) {
		return edges.contains(edge);
	}

	/**
	 * Returns the number of edges in this graph.
	 * @return the number of edges in this graph
	 */
	public int getEdgeCount() {
		return edges.size();
	}

	/**
	 * Returns the number of vertices in this graph.
	 * @return the number of vertices in this graph
	 */
	public int getVertexCount() {
		return vertices.size();
	}

	/**
	 * Returns a Collection view of the outgoing edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose outgoing edges are to be returned
	 * @return a Collection view of the outgoing edges incident 
	 * 				to vertex in this graph
	 */
	public Collection<Connection> getOutEdges(Host vertex) {
		Collection<Connection> edge= new ArrayList<Connection>();



		for (int i=0;i<naired.size();i++)
		{

			KeyValuePair<Host, Connection> pemp = naired.get(i);

			if (pemp.getKey().equals(vertex))
			{
				edge.add(pemp.getValue());
			}

		}



		return edge;

	}

	/**
	 * Returns a Collection view of the incoming edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose incoming edges are to be returned
	 * @return  a Collection view of the incoming edges incident 
	 * 				to vertex in this graph
	 */
	public Collection<Connection> getInEdges(Host vertex) {
		Collection<Connection> edge= new ArrayList<Connection>();


		for (int i=0;i<paired.size();i++)
		{
			KeyValuePair<Host, Connection> pemp = paired.get(i);
			if (pemp.getKey().equals(vertex))
			{
				edge.add(pemp.getValue());
			}

		}

		return edge;

	}

	/**
	 * Returns a Collection view of the predecessors of vertex 
	 * in this graph.  A predecessor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an outgoing edge of 
	 * v and an incoming edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the predecessors of 
	 * 				vertex in this graph
	 */
	public Collection<Host> getPredecessors(Host vertex) {
		Collection<Host> vertexes= new ArrayList<Host>();
		for (int i=0;i<naired.size();i++)
		{
			KeyValuePair<Host, Connection> two = naired.get(i); 
			if (two.getKey().equals(vertex))
			{
				vertexes.add(paired.get(i).getKey());
			}

		}





		return vertexes;
	}

	/**
	 * Returns a Collection view of the successors of vertex 
	 * in this graph.  A successor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an incoming edge of 
	 * v and an outgoing edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the successors of 
	 * 				vertex in this graph
	 */
	public Collection<Host> getSuccessors(Host vertex) {
		Collection<Host> vertexes= new ArrayList<Host>();
		for (int i=0;i<paired.size();i++)
		{
			KeyValuePair<Host, Connection> one = paired.get(i); 
			if (one.getKey().equals(vertex))
			{
				vertexes.add(naired.get(i).getKey());
			}

		}

		return vertexes;
	}

	/**
	 * get the real size of storage.
	 * @param vertices vertices 
	 * @return big
	 */
	public int big(ArrayList<Host> vertices){
		int big = Integer.MIN_VALUE;
		for(int i=0; i<vertices.size(); i++){
			int value=vertices.get(i).getId();
			if(value>big)
			{
				big=value;
			}
		}
		return big;
	}

	/**
	 * If directedEdge is a directed edge in this graph, returns the source; 
	 * otherwise returns null. 
	 * The source of a directed edge d is defined to be the vertex for which  
	 * d is an outgoing edge.
	 * directedEdge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directedEdge the edge to get the source of
	 * @return  the source of directedEdge if it is a directed edge in this graph, or null otherwise
	 */
	public Host getSource(Connection directedEdge) {

		Host ans=null;
		for (int i=0;i<naired.size();i++)
		{


			KeyValuePair<Host, Connection> temp = naired.get(i);
			if (temp.getValue().equals(directedEdge))
			{
				ans=temp.getKey();

			}		
		}

		return ans;

	}

	/**
	 * If directedEdge is a directed edge in this graph, returns the destination; 
	 * otherwise returns null. 
	 * The destination of a directed edge d is defined to be the vertex 
	 * incident to d for which  
	 * d is an incoming edge.
	 * directedEdge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directedEdge the edge to get the destination of
	 * @return  the destination of directedEdge if it is a directed edge in this graph, or null otherwise
	 */
	public Host getDest(Connection directedEdge) {

		Host ans=null;
		for (int i=0;i<paired.size();i++)
		{


			KeyValuePair<Host, Connection> temp = paired.get(i);
			if (temp.getValue().equals(directedEdge))
			{
				ans=temp.getKey();
				break;
			}		
		}

		return ans;
	}

	/**
	 * Returns an edge that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if either of the following is true:
	 * v1 == e.getSource() && v2 == e.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * @param v1  v1
	 * @param v2  v2 
	 * @return ans 
	 * 
	 * 
	 */
	public Connection findEdge(Host v1, Host v2) {
		Connection ans=null;

		for (Connection e:edges)
		{
			if (v1==(getSource(e)) &&  (v2==(getDest(e))))
			{

				ans=e;
				break;

			}


		}

		return ans;
	}

	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2.
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If this graph assigns edge types to its edges, the edge type of
	 * e will be the default for this graph.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure.
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object, EdgeType)
	 */
	public boolean addEdge(Connection e, Host v1, Host v2) {
		boolean bool=false;

		KeyValuePair<Host,Connection> pair= new KeyValuePair<Host, Connection>(v2,e);			

		KeyValuePair<Host,Connection> nair= new KeyValuePair<Host, Connection>(v1,e);			



		if (vertices.contains(v1) && vertices.contains(v2))
		{


			storage.add(pair, v1.getId());	
			edges.add(e);
			paired.add(pair);
			naired.add(nair);
			nonempty.add(v1);
			nonempty.add(v2);
	


		}



		else
		{

			throw new IllegalArgumentException();
		}





		bool=true;




		return bool;
	}

	/**
	 * Adds vertex to this graph.
	 * Fails if vertex is null or already in the graph.
	 * 
	 * @param vertex	the vertex to add
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if vertex is null
	 */
	public boolean addVertex(Host vertex) {

		boolean bool=false;
		if (vertex==null)
		{
			throw new IllegalArgumentException(); 
		}

		if (!vertices.contains(vertex))
		{

			vertices.add(vertex);


		}

		empty.add(vertex);






		bool=true;



		return bool;
	}

	/**
	 * Removes edge from this graph.
	 * Fails if edge is null, or is otherwise not an element of this graph.
	 * 
	 * @param edge the edge to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeEdge(Connection edge) {
		if (edge==null || !edges.contains(edge))
		{
			return false;
		}



		empty.removeAll(nonempty);

		edges.remove(edge);

		for (int z=0;z<paired.size();z++)
		{
			if (paired.get(z).getValue().equals(edge))
			{
				paired.remove(z);
				naired.remove(z);

			}
		}


		for (int i=0;i<vertices.size();i++)
		{
			if (getSuccessorCount(vertices.get(i))==0 && getPredecessorCount(vertices.get(i))==0)
			{
				removeVertex(vertices.get(i));
			}
		}


		storage.remove_value(edge, big(vertices)+10);
















		return true;
	}

	/**
	 * Removes vertex from this graph.
	 * As a side effect, removes any edges e incident to vertex if the 
	 * removal of vertex would cause e to be incident to an illegal
	 * number of vertices.  (Thus, for example, incident hyperedges are not removed, but 
	 * incident edges--which must be connected to a vertex at both endpoints--are removed.) 
	 * 
	 * <p>Fails under the following circumstances:
	 * <ul>
	 * <li/>vertex is not an element of this graph
	 * <li/>vertex is null
	 * </ul>
	 * 
	 * @param vertex the vertex to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeVertex(Host vertex) {


		ArrayList<Connection> temp= new ArrayList<Connection> ();
		ArrayList<Integer> array= new ArrayList<Integer> ();
		for (int i=0;i<edges.size();i++)
		{



			Connection e = edges.get(i);

			if (getDest(e).equals(vertex)|| getSource(e).equals(vertex))
			{

				if (edges.contains(e))
				{
					temp.add(e);

				}
			}


		}
		for (int a=0;a<temp.size();a++)
		{

			Connection q=temp.get(a);
			KeyValuePair<Host,Connection> pair= new KeyValuePair<Host, Connection>(vertex,q);			

			for (int z=0;z<paired.size();z++)
			{
				if (paired.get(z).getValue().equals(q))
				{
					paired.remove(z);
					naired.remove(z);

				}
			}
			edges.remove(temp.get(a));
		}


		vertices.remove(vertex);

		storage.remove_index(vertex.getId(),vertex);


		return true;
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
		//graph for testing, making a string representation for
		//ArrayOfListsOfPairs might be helpful here too.
		return super.toString();
	}

	/**
	 *  main method.
	 *  @param args main method
	 */
	public static void main(String[] args) {

	}

	//********************************************************************************
	// YOU MAY, BUT DON'T NEED TO, EDIT THINGS IN THIS SECTION, BUT DON'T BREAK IT...
	// THERE ARE MUCH MORE OPTIMAL WAYS TO DO MANY OF THESE METHODS, SO IT MIGHT BE
	// GOOD TO LOOK HERE IF YOUR CODE IS SLOWER THAN IT NEEDS TO BE.
	//********************************************************************************

	/**
	 * Returns true if v1 is a predecessor of v2 in this graph.
	 * Equivalent to v1.getPredecessors().contains(v2).
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a predecessor of v2, and false otherwise.
	 */
	public boolean isPredecessor(Host v1, Host v2) {
		return getPredecessors(v2).contains(v1);
	}

	/**
	 * Returns true if v1 is a successor of v2 in this graph.
	 * Equivalent to v1.getSuccessors().contains(v2).
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a successor of v2, and false otherwise.
	 */
	public boolean isSuccessor(Host v1, Host v2) {
		return getSuccessors(v2).contains(v1);
	}

	/**
	 * getEndpoints.
	 * @param edge the edge whose endpoints are to be returned
	 * @return the endpoints (incident vertices) of edge
	 */
	public Pair<Host> getEndpoints(Connection edge) {
		//System.out.println(getSource(edge).getId() + "---" + edge + "---" + getDest(edge).getId());
		return new Pair<Host>(getSource(edge), getDest(edge));
	}

	/**
	 * Returns true if vertex and edge 
	 * are incident to each other.
	 * Equivalent to getIncidentEdges(vertex).contains(edge) and to
	 * getIncidentVertices(edge).contains(vertex).
	 * @param vertex vertex.
	 * @param edge edge.
	 * @return true if vertex and edge are incident to each other
	 */
	public boolean isIncident(Host vertex, Connection edge) {
		return getIncidentEdges(vertex).contains(edge);
	}

	/**
	 * Returns true if v1 and v2 share an incident edge.
	 * Equivalent to getNeighbors(v1).contains(v2).
	 * @param v1 the first vertex to test
	 * @param v2 the second vertex to test
	 * @return true if v1 and v2 share an incident edge
	 */
	public boolean isNeighbor(Host v1, Host v2) {
		return getNeighbors(v1).contains(v2);
	}

	/**
	 * Returns the collection of vertices which are connected to vertex
	 * via any edges in this graph.
	 * If vertex is connected to itself with a self-loop, then 
	 * it will be included in the collection returned.
	 * @param vertex the vertex whose neighbors are to be returned
	 * @return  the collection of vertices which are connected to vertex, 
	 */
	public Collection<Host> getNeighbors(Host vertex) {
		if(!containsVertex(vertex)) return null;
		ArrayList<Host> neighbors = new ArrayList<>();
		neighbors.addAll(getSuccessors(vertex));
		neighbors.addAll(getPredecessors(vertex));

		Connection c = findEdge(vertex, vertex);
		if(c != null) neighbors.remove(vertex);

		return neighbors;
	}

	/**
	 * Returns the collection of edges in this graph which are connected to vertex.
	 * 
	 * @param vertex the vertex whose incident edges are to be returned
	 * @return  the collection of edges which are connected to vertex, 
	 * 				or null if vertex is not present
	 */
	public Collection<Connection> getIncidentEdges(Host vertex) {
		if(!containsVertex(vertex)) return null;
		ArrayList<Connection> edges = new ArrayList<>();
		edges.addAll(getOutEdges(vertex));
		edges.addAll(getInEdges(vertex));

		Connection c = findEdge(vertex, vertex);
		if(c != null) edges.remove(c);

		return edges;
	}

	/**
	 * Returns the number of incoming edges incident to vertex.
	 * Equivalent to getInEdges(vertex).size().
	 * @param vertex	the vertex whose indegree is to be calculated
	 * @return  the number of incoming edges incident to vertex
	 */
	public int inDegree(Host vertex) {
		return getInEdges(vertex).size();
	}

	/**
	 * Returns the number of vertices that are adjacent to vertex
	 * (that is, the number of vertices that are incident to edges in vertex's
	 * incident edge set).
	 * 
	 * <p>Equivalent to getNeighbors(vertex).size().
	 * @param vertex the vertex whose neighbor count is to be returned
	 * @return the number of neighboring vertices
	 */
	public int getNeighborCount(Host vertex) {
		return getNeighbors(vertex).size();
	}

	/**
	 * Returns the number of edges incident to vertex.  
	 * Special cases of interest:
	 * each of its neighbors (and vice versa), then the value returned 
	 * will also be equal to the number of neighbors that this vertex has
	 * (that is, the output of getNeighborCount).
	 * the sum of this vertex's indegree (the number of edges whose 
	 * destination is this vertex) and its outdegree (the number
	 * of edges whose source is this vertex), minus the number of
	 * incident self-loops (to avoid double-counting).
	 * @param vertex the vertex whose degree is to be returned
	 * @return the degree of this node
	 * @see Hypergraph#getNeighborCount(Object)
	 */
	public int degree(Host vertex) {
		return getIncidentEdges(vertex).size();
	}

	/**
	 * Returns the number of outgoing edges incident to vertex.
	 * Equivalent to getOutEdges(vertex).size().
	 * @param vertex	the vertex whose outdegree is to be calculated
	 * @return  the number of outgoing edges incident to vertex
	 */
	public int outDegree(Host vertex) {
		return getOutEdges(vertex).size();
	}

	/**
	 * Returns the number of predecessors that vertex has in this graph.
	 * Equivalent to vertex.getPredecessors().size().
	 * @param vertex the vertex whose predecessor count is to be returned
	 * @return  the number of predecessors that vertex has in this graph
	 */
	public int getPredecessorCount(Host vertex) {
		return getPredecessors(vertex).size();
	}

	/**
	 * Returns the number of successors that vertex has in this graph.
	 * Equivalent to vertex.getSuccessors().size().
	 * @param vertex the vertex whose successor count is to be returned
	 * @return  the number of successors that vertex has in this graph
	 */
	public int getSuccessorCount(Host vertex) {
		return getSuccessors(vertex).size();
	}

	/**
	 * Returns the vertex at the other end of edge from vertex.
	 * (That is, returns the vertex incident to edge which is not vertex.)
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return the vertex at the other end of edge from vertex
	 */
	public Host getOpposite(Host vertex, Connection edge) {
		Pair<Host> p = getEndpoints(edge);
		if(p.getFirst().equals(vertex)) {
			return p.getSecond();
		}
		else {
			return p.getFirst();
		}
	}

	/**
	 * Returns all edges that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if v1 is not connected to v2.
	 * <br/>Returns an empty collection if either v1 or v2 are not present in this graph.
	 * v1 == d.getSource() && v2 == d.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * @param v1 v1
	 * @param v2 v2
	 * @return  a collection containing all edges that connect v1 to v2, 
	 */
	public Collection<Connection> findEdgeSet(Host v1, Host v2) {
		Connection edge = findEdge(v1, v2);
		if(edge == null) {
			return null;
		}

		ArrayList<Connection> ret = new ArrayList<>();
		ret.add(edge);
		return ret;

	}

	/**
	 * Returns true if vertex is the source of edge.
	 * Equivalent to getSource(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the source of edge
	 */
	public boolean isSource(Host vertex, Connection edge) {
		return getSource(edge).equals(vertex);
	}

	/**
	 * Returns true if vertex is the destination of edge.
	 * Equivalent to getDest(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the destination of edge
	 */
	public boolean isDest(Host vertex, Connection edge) {
		return getDest(edge).equals(vertex);
	}

	/**
	 * Returns the collection of vertices in this graph which are connected to edge.
	 * Note that for some graph types there are guarantees about the size of this collection
	 * (i.e., some graphs contain edges that have exactly two endpoints, which may or may 
	 * not be distinct).  Implementations for those graph types may provide alternate methods 
	 * that provide more convenient access to the vertices.
	 * 
	 * @param edge the edge whose incident vertices are to be returned
	 * @return  the collection of vertices which are connected to edge, 
	 * 				or null if edge is not present
	 */
	public Collection<Host> getIncidentVertices(Connection edge) {
		if(!containsEdge(edge)) return null;

		ArrayList<Host> vert = new ArrayList<>();

		Host source = getSource(edge);
		Host dest = getDest(edge);

		vert.add(source);
		if(!source.equals(dest)) vert.add(dest);

		return vert;
	}

	/**
	 * Returns the number of edges of type edgeType in this graph.
	 * @param edgeType the type of edge for which the count is to be returned
	 * @return the number of edges of type edgeType in this graph
	 */
	public int getEdgeCount(EdgeType edgeType) {
		if(edgeType == EdgeType.DIRECTED) {
			return getEdgeCount();
		}
		return 0;
	}

	/**
	 * Returns the collection of edges in this graph which are of type edgeType.
	 * @param edgeType the type of edges to be returned
	 * @return the collection of edges which are of type edgeType, or
	 * 				null if the graph does not accept edges of this type
	 * @see EdgeType
	 */
	public Collection<Connection> getEdges(EdgeType edgeType) {
		if(edgeType == EdgeType.DIRECTED) {
			return getEdges();
		}
		return null;
	}

	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2.
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If edgeType is not legal for this graph, this method will
	 * throw IllegalArgumentException.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure.
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @param edgeType the type to be assigned to the edge
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object)
	 */
	public boolean addEdge(Connection e, Host v1, Host v2, EdgeType edgeType) {
		//NOTE: Only directed edges allowed

		if(edgeType == EdgeType.UNDIRECTED) {
			throw new IllegalArgumentException();
		}

		return addEdge(e, v1, v2);
	}

	/**
	 * Adds edge to this graph.
	 * Fails under the following circumstances:
	 * @param edge edge 
	 * @param vertices vertices
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, 
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(Connection edge, Collection<? extends Host> vertices) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		Host[] vs = (Host[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1]);
	}

	/**
	 * Adds edge to this graph with type edgeType.
	 * Fails under the following circumstances:
	 * @param edge edge 
	 * @param vertices vertices
	 * @param edgeType edgeType
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, 
	 * 				or if a different vertex set in this graph is already connected by edge, 
	 * 				or if vertices are not a legal vertex set for edge 
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(Connection edge, Collection<? extends Host> vertices, EdgeType edgeType) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		Host[] vs = (Host[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1], edgeType);
	}

	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE EXCEPT TO ADD/CORRECT JAVADOCS
	//********************************************************************************

	//This will be used to check that you are setting
	//the storage up correctly... it's very important
	//that you (1) are using the ArrayOfListsOfPairs 
	//provided and (2) don't edit this at all
	/**
	 * ArrayOfListsOfPairs.
	 * @return storage
	 */
	public ArrayOfListsOfPairs<Host,Connection> getInternalTable() {
		return storage;
	}

	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * @return new Network()
	 */
	@SuppressWarnings("unchecked")
	public static Factory<Graph<Host,Connection>> getFactory() { 
		return new Factory<Graph<Host,Connection>> () {
			public Graph<Host,Connection> create() {
				return (Graph<Host,Connection>) new Network();
			}
		};
	}

	/**
	 * Returns the edge type of edge in this graph.
	 * @param edge edge 
	 * @return the EdgeType of edge, or null if edge has no defined type
	 */
	public EdgeType getEdgeType(Connection edge) {
		return EdgeType.DIRECTED;
	}

	/**
	 * Returns the default edge type for this graph.
	 * 
	 * @return the default edge type for this graph
	 */
	public EdgeType getDefaultEdgeType() {
		return EdgeType.DIRECTED;
	}

	/**
	 * Returns the number of vertices that are incident to edge.
	 * For hyperedges, this can be any nonnegative integer; for edges this
	 * must be 2 (or 1 if self-loops are permitted). 
	 * 
	 * <p>Equivalent to getIncidentVertices(edge).size().
	 * @param edge the edge whose incident vertex count is to be returned
	 * @return the number of vertices that are incident to edge.
	 */
	public int getIncidentCount(Connection edge) {
		return 2;
	}
}
