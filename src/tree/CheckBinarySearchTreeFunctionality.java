package tree;

import list.IList;
import list.linkedlist.MyLinkedList;

public class CheckBinarySearchTreeFunctionality {
	public static void main(String[] args) {
		Integer a[] = {1,2,3,4,5,6,7};
		TreeNode<Integer> treeFrom = ExtendedBinarySearchTree.getTreeFrom(a);
		System.out.println();
		ExtendedBinarySearchTree<Integer> tree = new ExtendedBinarySearchTree<Integer>();
		tree.add(50);
		tree.add(25);
		tree.add(75);
		tree.add(10);
		tree.add(0);
		tree.add(100);
		boolean balancedTree = tree.isBalancedTree();
		System.out.println();
		/*ExtendedBinarySearchTree<Integer> tree = new ExtendedBinarySearchTree<Integer>();
		tree.add(50);
		tree.add(25);
//		tree.add(75);
//		boolean balancedTree = tree.isBalancedTree();
		System.out.println();*/
		/*tree.add(13);
		tree.add(38);
		tree.add(62);
		tree.add(87);
		tree.add(0);
		tree.add(20);
		tree.add(30);
		tree.add(45);
		tree.add(55);
		tree.add(70);
		tree.add(80);
		tree.add(100);*/
//		IList<Integer> populateIterativePreorderTraversal = tree.populateIterativePostorderTraversal();
		/*IList<Integer> spiralTraversal = tree.getSpiralTraversal();
		tree.initializeSiblingPointers();
		String string = tree.toString();*/
//		tree.remove(75);
//		MyLinkedList<Integer> levelOrderTraversal = tree.getLevelOrderTraversal();
//		tree.mirrorize();
		System.out.println();
	}
}
