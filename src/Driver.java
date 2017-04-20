// Problem 8
// Program for constructing an AVL tree given a list of n distinct integers

public class Driver {
	public static void main(String[] args) {
		int[] integerList = {3, 6, 5, 1, 2, 4};
		
		AVLTree newTree = constructAVLTree(integerList);
		
		newTree.printTree();
		
		/* Results:
		 * Label: 3, Parent: -1, Left: 2, Right: 5
		 * Label: 2, Parent: 3, Left: 1, Right: -1
         * Label: 1, Parent: 2, Left: -1, Right: -1
         * Label: 5, Parent: 3, Left: 4, Right: 6
         * Label: 4, Parent: 5, Left: -1, Right: -1
         * Label: 6, Parent: 5, Left: -1, Right: -1 
         */

	}
	
	public static AVLTree constructAVLTree(int[] integerList) {
		AVLTree newTree = new AVLTree(integerList[0]);
		
		for (int i = 1; i < integerList.length; i++) {
			newTree.add(integerList[i]);
		}
		
		return newTree;
	}
}
