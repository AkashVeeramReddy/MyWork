package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import utils.MyUtilities;


public class BinaryTreeComparable<K extends Comparable<? super K>> extends BinaryTree<K> {
	
	public BinaryTreeComparable() {
		
	}
	
	/**
	 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
	 * 
	 * Given a Binary Tree, write a function that returns the size of the largest subtree which is also a Binary Search Tree (BST).
	 *  If the complete Binary Tree is BST, then return the size of whole tree.
	 * @return
	 */
	public int getLargestSizeOfBST() {
		return getLargestSizeOfBST(root).maxSize;
	}
	
	protected SubBSTInfo getLargestSizeOfBST(TreeNode<K> ptr) {
		if(ptr == null) {
			return new SubBSTInfo();
		} else {
			SubBSTInfo bstInfo = new SubBSTInfo();
			int newSizeWithRoot = 0;
			int newMaxSize = 0;
			if(ptr.left == null && ptr.right == null) {
				newMaxSize = 1;
				newSizeWithRoot = 1;
			} else if(ptr.right != null || ptr.left != null) {
				//one of ptr.right or ptr.left or both != null
				SubBSTInfo rightInfo = getLargestSizeOfBST(ptr.right);
				SubBSTInfo leftInfo = getLargestSizeOfBST(ptr.left);
				
				int leftCmp = -1;
				if(ptr.left != null) {
					leftCmp = ptr.data.compareTo(ptr.left.data);
				}
				int rightCmp = 1;
				if(ptr.right != null) {
					rightCmp = ptr.data.compareTo(ptr.right.data);
				}
				
				
				if(leftCmp > 0 && rightCmp < 0) {
					//these form a bst by itself
					//include the current node
					
					if(leftInfo.isRootIncludedInMaxSize() && rightInfo.isRootIncludedInMaxSize()) {
						newSizeWithRoot = 1 + leftInfo.sizeWithRoot + rightInfo.sizeWithRoot;
						newMaxSize = newSizeWithRoot;
					} else  if(leftInfo.isRootIncludedInMaxSize()) {
						newSizeWithRoot = 1 + leftInfo.sizeWithRoot;
						newMaxSize = MyUtilities.getMaxElement(newSizeWithRoot,
								rightInfo.maxSize);
					} else if(rightInfo.isRootIncludedInMaxSize()) {
						newSizeWithRoot = 1 + rightInfo.sizeWithRoot;
						newMaxSize = MyUtilities.getMaxElement(newSizeWithRoot,
								leftInfo.maxSize);
					} else {
						newSizeWithRoot = 0;
						newMaxSize = MyUtilities.getMaxElement(leftInfo.maxSize, rightInfo.maxSize);
					}
					
					
				} else if(leftCmp > 0) {
					//left Node is lesser than root
					
					if(leftInfo.isRootIncludedInMaxSize()) {
						//include root
						newSizeWithRoot = 1 + leftInfo.sizeWithRoot;
						newMaxSize = MyUtilities.getMaxElement(newSizeWithRoot,
								rightInfo.maxSize);
					} else {
						newSizeWithRoot = 0;
						newMaxSize = MyUtilities.getMaxElement(newSizeWithRoot,
								leftInfo.maxSize, rightInfo.maxSize);
					}
				} else if(rightCmp < 0) {
					//right node is more than root
					if(rightInfo.isRootIncludedInMaxSize()) {
						//include root
						newSizeWithRoot = 1 + rightInfo.sizeWithRoot;
						newMaxSize = MyUtilities.getMaxElement(newSizeWithRoot,
								leftInfo.maxSize);
					} else {
						newSizeWithRoot = 0;
						newMaxSize = MyUtilities.getMaxElement(leftInfo.maxSize, rightInfo.maxSize);
					}
				} else {
					//these dont form a bst
					newSizeWithRoot = 0;
					newMaxSize = MyUtilities.getMaxElement(leftInfo.maxSize,rightInfo.maxSize);
				}
				
			}
			bstInfo.maxSize = newMaxSize;
			bstInfo.sizeWithRoot = newSizeWithRoot;
			
			return bstInfo;
		}
	}
	/**
	 * http://www.geeksforgeeks.org/binary-tree-to-binary-search-tree-conversion/
	 * Given a Binary Tree, convert it to a Binary Search Tree. 
	 * The conversion must be done in such a way that keeps the original structure of Binary Tree.
	 */
	public void convertToBST() {
		root = convertToBST(root);
	}
	
	protected TreeNode<K> convertToBST(TreeNode<K> node) {
		if(node == null) {
			return null;
		} else {
			TreeNode<K> leftRoot = convertToBST(node.left);
			TreeNode<K> rightRoot = convertToBST(node.right);
			/**
			 * traverse tree to get data. Sort them and them populate tree
			 */
			K nodeData = node.data;
			//if()
		}
		return node;
	}
	
	static class SubBSTInfo {
		int sizeWithRoot = 0;
		int maxSize = 0;
		
		boolean isRootIncludedInMaxSize() {
			return sizeWithRoot == maxSize;
		}
	}
	
	
	
	
}
