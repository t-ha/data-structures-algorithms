/**
 * @author Timothy Ha
 * @UWNetID junkwan
 * @studentID 1367917
 * @email junkwan@uw.edu
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

// testing class for

public class MyGraphTest {
	public static void main(String[] args) {
		MyGraph g = readGraph("vertex2.txt", "edge2.txt");
		for (Vertex v: g.vertices()) {
			System.out.println(v);
		}
		
		for (Edge e: g.edges()) {
			System.out.println(e);
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
