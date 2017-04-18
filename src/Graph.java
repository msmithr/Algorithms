import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayDeque;

public class Graph {
	ArrayList<ArrayList<Integer>> adjacencyList;
	Random random;
	int size;
	
	public Graph(int size) {
		adjacencyList = new ArrayList<ArrayList<Integer>>();
		generateGraph(size);
	}
	
	/* Given a label and a list of connections, adds a node to the graph*/
	public void add(int label, ArrayList<Integer> connections) {
		ArrayList<Integer> newEntry = new ArrayList<Integer>();
		newEntry.add(label);
		for (int i : connections) newEntry.add(i);
		
		adjacencyList.add(newEntry);
	}
	
	/* Given the label of a node, returns its list of connections */
	public ArrayList<Integer> getConnections(int label) {

		for (int i = 0; i < adjacencyList.size(); i++) {
			if (adjacencyList.get(i).get(0) == label)
				return adjacencyList.get(i);
		}
		
		return null;
	}
	
	/* randomly generates a directed acylic graph with a given number of nodes */
	private void generateGraph(int size) {
		ArrayList<Integer> connections;
		random = new Random();
		this.size = size;
		
		for (int i = 0; i < size; i++) {
			connections = new ArrayList<Integer>();
			
			for (int j = i+1; j < size; j++) {
				if (random.nextBoolean()) connections.add(j);
			} // end for
			
			add(i, connections);
		} // end for
		
		Collections.shuffle(adjacencyList);
	
	} // end generateGraph()
	
	public ArrayList<Integer> DFS() {
		ArrayDeque<Integer> traversalStack = new ArrayDeque<Integer>();
		return null;
	}
	
	@Override
	public String toString() {
		return adjacencyList.toString();
	}
}
