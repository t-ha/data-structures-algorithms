import java.util.*;

/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
*/

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	// you will need some private fields to represent the graph
	// you are also likely to want some private helper methods

	private Map<Vertex, Set<Edge>> graph; // a map of vertices to their edges
	private Map<String, Vertex> vert; // a map of labels to their vertices
	
	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		graph = new HashMap<Vertex, Set<Edge>>();
		vert = new HashMap<String, Vertex>();
		getVertices(v);
		getEdges(e);
	}
	
	/**
	 * map the vertices to labels
	 * @param v: collection of vertices in the graph
	 */
	private void getVertices(Collection<Vertex> v) {
		for (Vertex vertex: v) {
			if (!graph.containsKey(vertex)) {
				graph.put(vertex, new HashSet<Edge>());
				vert.put(vertex.getLabel(), vertex);
			}
		}
	}
	
	/**
	 * map the edges to their starting vertices
	 * @param e: collection of edges in the graph
	 */
	private void getEdges(Collection<Edge> e) {
		for (Edge edge: e) {
			int eWeight = edge.getWeight();
			if (eWeight < 0) {
				throw new IllegalArgumentException();
			}
			Vertex from = edge.getSource();
			Vertex to = edge.getDestination();
			if (!graph.containsKey(from) && !graph.containsKey(to)) {
				throw new IllegalArgumentException();
			}
			if (graph.containsKey(from)) {
				for (Edge temp: graph.get(from)) {
					if (temp.getDestination().equals(to)) {
						if (temp.getWeight() != eWeight) {
							throw new IllegalArgumentException();
						}
					}
				}
			}
			graph.get(from).add(edge);
			
		}
	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
		return graph.keySet();
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
		Collection<Edge> edges = new HashSet<Edge>();
		for (Vertex v: graph.keySet()) {
			for (Edge e: graph.get(v)) {
				edges.add(e);
			}
		}
		return edges;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {
		if (!graph.containsKey(v)) {
			throw new IllegalArgumentException();
		}
		
		Collection<Edge> edges = graph.get(v);
		Collection<Vertex> vertices = new HashSet<Vertex>();
		for (Edge e: edges) {
			vertices.add(vert.get(e.getDestination().getLabel()));
		}
		return vertices;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {
		if (!graph.containsKey(a) && !graph.containsKey(b)) {
			throw new IllegalArgumentException();
		}
		
		for (Edge e: graph.get(a)) {
			if (b.equals(e.getDestination())) {
				return e.getWeight();
			}
		}
		
		return -1;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {
		if (!graph.containsKey(a) && !graph.containsKey(b)) {
			throw new IllegalArgumentException();
		}
		
		List<Vertex> finalPath = new ArrayList<Vertex>();
		a.setCost(0);
		int cost = 0;
		
		if (!a.equals(b)) {
			Queue<Vertex> nodes = new LinkedList<Vertex>();
			nodes.add(a);
			setDefault(a, nodes, vert.values());
			
			dijkstra(nodes, nodes.size());
			
			cost = constructPath(b, finalPath);
		} else {
			finalPath.add(a);
		}
		
		// checks if the path exists
		if (!finalPath.contains(a)) {
			return null;
		} else {
			return new Path(finalPath, cost);
		}
	}
	
	/**
	 * sets all the vertices to unknown and infinity weight
	 * @param a: source vertex
	 * @param nodes: queue of unknown vertices
	 * @param vs: all the vertices
	 */
	private void setDefault(Vertex a, Queue<Vertex> nodes, Collection<Vertex> vs) {
		for (Vertex v: vs) {
			if (!v.getLabel().equals(a.getLabel())) {
				v.setCost(Integer.MAX_VALUE);
				v.setKnown(false);
				nodes.add(v);
			}
		}
	}
	
	/**
	 * iterates through the vertices to find the shortest path from a to b
	 * @param nodes: queue of unknown nodes
	 * @param size: size
	 */
	private void dijkstra(Queue<Vertex> nodes, int size) {
		while (!nodes.isEmpty()) {
			Vertex node = lowestCost(nodes, size - 1);
			node.setKnown(true);
			Collection<Vertex> list = adjacentVertices(node);
			for (Vertex v: list) {
				if (!v.isKnown()) {
					if (node.getCost() + edgeCost(node, v) < v.getCost()) {
						v.setCost(node.getCost() + edgeCost(node, v));
						v.setPath(node);
					}
				}
			}
			size--;
		}
	}
	
	/**
	 * constructs the path of the shortest path reverse order
	 * @param b: the destination vertex
	 * @param finalPath: the shortest path in reverse order
	 * @return: total cost of traversing the path
	 */
	private int constructPath(Vertex b, List<Vertex> finalPath) {
		int cost = 0;
		Vertex current = vert.get(b.getLabel());
		finalPath.add(current);
		cost += current.getCost();
		while (current.getPath() != null) {
			finalPath.add(current.getPath());
			current = current.getPath();
		}
		return cost;
	}
	
	/**
	 * returns the vertex with the lowest cost
	 * @param nodes: a queue of unknown nodes
	 * @param size
	 * @return: the vertex with the lowest cost
	 */
	private Vertex lowestCost(Queue<Vertex> nodes, int size) {
		Vertex node = nodes.remove();
		while (size > 0) {
			Vertex temp = nodes.remove();
			if (temp.getCost() < node.getCost()) {
				Vertex sub = temp;
				temp = node;
				node = sub;
			}
			
			nodes.add(temp);
			size--;
		}
		return node;
	}

}
