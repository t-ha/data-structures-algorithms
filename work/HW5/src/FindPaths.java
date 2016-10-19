import java.util.*;
import java.io.*;
/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
*/


/**
 * Driver program that reads in a graph and prompts user for shortests paths in the graph.
 * (Intentionally without comments.  Read through the code to understand what it does.)
 */

public class FindPaths {
	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("USAGE: java Paths <vertex_file> <edge_file>");
			System.exit(1);
		}

		MyGraph g = readGraph(args[0],args[1]);

		@SuppressWarnings("resource")
		Scanner console = new Scanner(System.in);
		Collection<Vertex> v = g.vertices();
                Collection<Edge> e = g.edges();
		System.out.println("Vertices are "+v);
		System.out.println("Edges are "+e);
		while(true) {
			System.out.print("Start vertex? ");
			Vertex a = new Vertex(console.nextLine());
			if(!v.contains(a)) {
				System.out.println("no such vertex");
				System.exit(0);
			}
			
			System.out.print("Destination vertex? ");
			Vertex b = new Vertex(console.nextLine());
			if(!v.contains(b)) {
				System.out.println("no such vertex");
				System.exit(1);
			}
			
			
			Path p = g.shortestPath(a, b);
			output(p, a, b);
		}
	}
	
	// Print the shortest path, if it exists, to the console along with the
	// total cost of the path
	public static void output(Path p, Vertex a, Vertex b) {
		System.out.println("Shortest path from " + a.getLabel() + " to " + b.getLabel() + ":");
		if (p != null) {
			Stack<Vertex> s = new Stack<Vertex>();
			for (Vertex vert: p.vertices) {
				s.push(vert);
			}
			System.out.print(s.pop());
			while (!s.isEmpty()) {
				System.out.print(" " + s.pop());
			}
			System.out.println();
			System.out.println(p.cost);
		} else {
			System.out.println("does not exist");
		}
	}

	public static MyGraph readGraph(String f1, String f2) {
		Scanner s = null;
		try {
			s = new Scanner(new File(f1));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f1);
			System.exit(2);
		}

		Collection<Vertex> v = new ArrayList<Vertex>();
		while(s.hasNext())
			v.add(new Vertex(s.next()));

		try {
			s = new Scanner(new File(f2));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f2);
			System.exit(2);
		}

		Collection<Edge> e = new ArrayList<Edge>();
		while(s.hasNext()) {
			try {
				Vertex a = new Vertex(s.next());
				Vertex b = new Vertex(s.next());
				int w = s.nextInt();
				e.add(new Edge(a,b,w));
			} catch (NoSuchElementException e2) {
				System.err.println("EDGE FILE FORMAT INCORRECT");
				System.exit(3);
			}
		}

		return new MyGraph(v,e);
	}
}
