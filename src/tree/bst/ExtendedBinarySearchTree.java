package tree.bst;

import static tree.bst.BinarySearchTreeType.INORDER_ASCENDING;
import static tree.bst.BinarySearchTreeType.INORDER_DESCENDING;
import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import queue.IQueue;
import tree.TreeNode;
import utils.MyUtilities;
import utils.RotationType;

public class ExtendedBinarySearchTree<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	
	public void mirrorize() {
		super.mirrorize();
		switch (treeType) {
		case INORDER_ASCENDING:
			treeType = INORDER_DESCENDING;
		case INORDER_DESCENDING:
			treeType = INORDER_ASCENDING;
		}
	}
	
	/**
	 * both are present in the tree
	 * @param data1
	 * @param data2
	 * @return LCA of data1 and data2
	 */
	public K getLeastCommonAncestor(K data1,K data2) {
		return getLeastCommonAncestor(root, data1, data2);
	}
	
	protected K getLeastCommonAncestor(TreeNode<K> ptr, K data1,K data2) {
		if(ptr == null) {
			return null;
		}
		K data = ptr.data;
		if(data == data1 || data.equals(data1)) {
			return data1;
		}
		if(data == data2 || data.equals(data2)) {
			return data2;
		}
		int dataCmpData1 = data.compareTo(data1);
		int dataCmpData2 = data.compareTo(data2);
		
		if(dataCmpData1 > 0  && dataCmpData2 > 0) {
			//search in left subtree
			return getLeastCommonAncestor(ptr.left,data1,data2);
		} else if(dataCmpData1 < 0  && dataCmpData2 < 0) {
			//search in right subtree
			return getLeastCommonAncestor(ptr.right,data1,data2);
		} else {
			return data;
		}
		
	}
	
	public static <I extends Comparable<? super I>> TreeNode<I> getBSTFrom(I[] sortedArrayinAscendingOrder) {
		return getBSTFrom(sortedArrayinAscendingOrder,0,sortedArrayinAscendingOrder.length-1);
	}
	
	public static <I extends Comparable<? super I>> TreeNode<I> getBSTFrom(I[] sortedArrayinAscendingOrder,int start,
			int end) {
		if(end < start)
			return null;
		else {
			int half = (start + end)/2;
			I data = sortedArrayinAscendingOrder[half];
			TreeNode<I> root = new TreeNode<I>(data, null, null);
			root.left = getBSTFrom(sortedArrayinAscendingOrder, start, half-1);
			root.right = getBSTFrom(sortedArrayinAscendingOrder, half+1,end);
			return root;
		}
	}
}
