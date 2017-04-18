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
	
	/* randomly generates a directed acyclic graph with a given number of nodes */
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
		
		// randomize the order of the adjacency list, to make the order more realistic
		Collections.shuffle(adjacencyList);
	
	} // end generateGraph()
	
	/* generates a topological ordering of the nodes using the DFS based algorithm */
	public ArrayList<Integer> DFSTopologicalSort() {
		ArrayDeque<ArrayList<Integer>> traversalStack = new ArrayDeque<ArrayList<Integer>>();
		ArrayList<Integer> pushOrder = new ArrayList<Integer>();
		ArrayList<Integer> popOrder = new ArrayList<Integer>();
		ArrayList<Integer> currentNode = null;
		boolean found;
		
		while (pushOrder.size() < size) {
			// pick an origin node that hasn't been visited
			for (ArrayList<Integer> i : adjacencyList) {
				if (!pushOrder.contains(i.get(0))) {
					currentNode = i;
					break;
				} // end if
			} // end for
			
			// push the origin node to the stack
			traversalStack.push(currentNode);
			pushOrder.add(currentNode.get(0));
			
			while (!traversalStack.isEmpty()) {
				currentNode = traversalStack.peek();
				
				found = false;
				// find an unvisited neighbor node to the top of the stack
				// and push it to the stack
				for (int i = 1; i < currentNode.size(); i++) {
					if (!pushOrder.contains(currentNode.get(i))) {
						currentNode = getConnections(currentNode.get(i));
						traversalStack.push(currentNode);
						pushOrder.add(currentNode.get(0));
						found = true;
					} // end if
				} // end for
				
				// if there are no unvisited neighbor nodes, pop it from the stack
				if (!found) {
					popOrder.add(traversalStack.pop().get(0));
				}
				 
			} // end while
		} // end while
		
		// reverse the pop order to get the topological ordering
		Collections.reverse(popOrder);
		
		return popOrder;
	}
	
	/* generates a topological ordering of the nodes using the source-removal based algorithm */
	/* takes the graph apart as a side effect */
	public ArrayList<Integer> sourceRemovalTopologicalSort() {
		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		ArrayList<Integer> topologicalOrder = new ArrayList<Integer>();
		
		while (topologicalOrder.size() < size) {
			// populate nodeList
			nodeList.clear();
			for (int i = 0; i < adjacencyList.size(); i++) {
				nodeList.add(adjacencyList.get(i).get(0));
			} // end for
			
			// remove all non-source nodes from nodeList
			for (ArrayList<Integer> i : adjacencyList) {
				for (int j = 1; j < i.size(); j++) {
					nodeList.remove(i.get(j));
				} // end for
			} // end for
			
			// for each source node, remove it completely from the graph and add it to the sort list
			for (int i = 0; i < nodeList.size(); i++) {
				topologicalOrder.add(nodeList.get(i));
				for (int j = 0; j < adjacencyList.size(); j++) {
					if (adjacencyList.get(j).get(0) == nodeList.get(i)) {
						adjacencyList.remove(j);
						j--;
						continue;
					}
					for (int k = 0; k < adjacencyList.get(j).size(); k++) {
						if (adjacencyList.get(j).get(k) == nodeList.get(i)) {
							adjacencyList.get(j).remove(k);
							k--;
							continue;
						}
					}
				}
			} // end for

		} // end while
		
		return topologicalOrder;
	}
}
