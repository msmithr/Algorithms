
public class AVLTree {

	Node root;
	
	public AVLTree() {
		root = null;
	}
	
	public void add(int newEntry) {
		Node newNode = new Node(newEntry);
		
	}
	
	private class Node {
		public Node left;
		public Node right;
		public Node parent;
		public int label;
		
		public Node(int label) {
			left = null;
			right = null;
			parent = null;
			this.label = label;
		}
		
	}
}
