/* simple Java program to test and time topological sorting algorithms on randomly generated acyclic graphs */
/* Author: Michael Smith */

public class Driver {
	
	public static void main(String[] args) {
		for (int i = 1; i < 1000000; i*=10) {
			timeSorts(i);
		}
	}
	
	public static void timeSorts(int size) {
		Graph g = new Graph(size);
		long startTime;
		long endTime;

		startTime = System.currentTimeMillis();
		g.DFSTopologicalSort();
		endTime = System.currentTimeMillis();
		
		System.out.printf("%d Nodes: DFS Sort: %dms\n", size, endTime - startTime);
		
		startTime = System.currentTimeMillis();
		g.sourceRemovalTopologicalSort();
		endTime = System.currentTimeMillis();
		
		System.out.printf("%d Nodes: Source Removal Sort: %dms\n\n", size, endTime - startTime);

	}
}
