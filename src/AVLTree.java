// implementation of simple AVL tree
// using only integers as node labels

public class AVLTree {

	public Node root;
	
	public AVLTree(int rootLabel) {
		root = new Node(rootLabel);
	}
	
	/**
	 * Adds an element to the tree and automatically balances
	 * @param newLabel Label of element to be added
	 */
	public void add(int newLabel) {
		add(newLabel, root); // recursive call to private method
		root = balance(root); // balance
	}
	
	private void add(int newLabel, Node root) {
		if (newLabel < root.label) {
			if (root.leftChild == null) root.leftChild = new Node(newLabel, root);
			else {
				add(newLabel, root.leftChild);
				root.leftChild = balance(root.leftChild);
			}
		} else {
			if (root.rightChild == null) root.rightChild = new Node(newLabel, root);
			else {
				add(newLabel, root.rightChild);
				root.rightChild = balance(root.rightChild);
			}
		}
	}
	
	// performs necessary rotations to balance the tree over a given pivot
	private Node balance(Node pivot) {
		
		if (pivot.balanceFactor() < -1) {
			if (pivot.leftChild.balanceFactor() < 0) {
				return rotateRight(pivot);
			} else {
				return rotateLeftRight(pivot);
			}
		} else if (pivot.balanceFactor() > 1) {
			if (root.rightChild.balanceFactor() > 0) {
				return rotateLeft(pivot);
			} else {
				return rotateRightLeft(pivot);
			}
		}
		
		return pivot;
	}
	
	// simple method to print contents of the tree
	public void printTree() {
		root.printNode();
	}
	
	// R rotation
	private Node rotateRight(Node pivot) {
		Node temp = pivot.leftChild;
		
		pivot.leftChild = temp.rightChild;
		if (temp.rightChild != null) temp.rightChild.parent = pivot;
		
		temp.rightChild = pivot;
		temp.parent = pivot.parent;
		pivot.parent = temp;
		
		return temp;
	}
	
	// L rotation
	private Node rotateLeft(Node pivot) {
		Node temp = pivot.rightChild;
		
		pivot.rightChild = temp.leftChild;
		if (temp.leftChild != null) temp.leftChild.parent = pivot;
		
		temp.leftChild = pivot;
		temp.parent = pivot.parent;
		pivot.parent = temp;
		
		return temp;
	}
	
	// RL rotation
	private Node rotateRightLeft(Node pivot) {
		Node temp = pivot.rightChild;
		
		Node tempRightResult = rotateRight(temp);
		
		tempRightResult.parent = pivot;
		pivot.rightChild = tempRightResult;

		return rotateLeft(pivot);
	}
	
	// LR rotation
	private Node rotateLeftRight(Node pivot) {
		Node temp = pivot.leftChild;
		
		Node tempLeftResult = rotateLeft(temp);
		
		tempLeftResult.parent = pivot;
		pivot.leftChild = tempLeftResult;

		return rotateRight(pivot);
	}
	
	/* inner node class */
	private class Node {
		Node parent = null;
		Node leftChild = null;
		Node rightChild = null;
		int label;
		
		public Node(int label) {
			this(label, null);
		}
		
		public Node(int label, Node parent) {
			this.label = label;
			this.parent = parent;
		}
		
		public int height() {
			int leftHeight;
			int rightHeight;
			
			if (leftChild == null) leftHeight = -1;
			else leftHeight = leftChild.height();
			
			if (rightChild == null) rightHeight = -1;
			else rightHeight = rightChild.height();
			
			return Math.max(leftHeight, rightHeight) + 1;
		} // end height()
		
		public int balanceFactor() {
			int leftHeight = -1;
			int rightHeight = -1;
			
			if (leftChild != null) leftHeight = leftChild.height();
			if (rightChild != null) rightHeight = rightChild.height();
			
			return rightHeight - leftHeight;
		} // end balanceFactor()
		
		public void printNode() {
			int parentLabel = -1;
			int leftLabel = -1;
			int rightLabel = -1;
			
			if (parent != null) parentLabel = parent.label;
			if (leftChild != null) leftLabel = leftChild.label;
			if (rightChild != null) rightLabel = rightChild.label;
			
			System.out.printf("Label: %d, Parent: %d, Left: %d, Right: %d\n", label, parentLabel, leftLabel, rightLabel);
			
			if (leftChild != null) leftChild.printNode();
			if (rightChild != null) rightChild.printNode();
		}
		
		
	}
}
