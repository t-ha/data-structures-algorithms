/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
*/

/**
 * Representation of a graph vertex
 */
public class Vertex {
	// label attached to this vertex
	private String label;
	private int cost; // cost
	private boolean known; // status of being known
	private Vertex path; // connection to another vertex
	
	/**
	 * Construct a new vertex
	 * 
	 * @param label
	 *            the label attached to this vertex
	 */
	public Vertex(String label) {
		if (label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
	}

	/**
	 * Get a vertex label
	 * 
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * A string representation of this object
	 * 
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}
	
	/**
	 * get the cost
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * set the cost
	 * @param cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * set if the vertex is known
	 * @param known
	 */
	public void setKnown(boolean known) {
		this.known = known;
	}
	
	/**
	 * check to see if the vertex is known
	 * @return if the vertex is known
	 */
	public boolean isKnown() {
		return known;
	}
	
	/**
	 * return the path
	 * @return what is connected to the vertex
	 */
	public Vertex getPath() {
		return path;
	}
	
	/**
	 * connect a vertex
	 * @param v
	 */
	public void setPath(Vertex v) {
		path = v;
	}

	// auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	// auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
			return other.label == null;
		} else {
			return label.equals(other.label);
		}
	}

}
