package tree;

import list.IList;

public class TestIterativeTraversalTree {

	public static void main(String[] args) {
		//IterativeTraversalTree<K> 
		IntegerBinaryTree tree = new IntegerBinaryTree("med_bst.dot");
		IList<Integer> boundary = tree.populateBoundaryTraversal();
		System.out.println(boundary);
	}
}
