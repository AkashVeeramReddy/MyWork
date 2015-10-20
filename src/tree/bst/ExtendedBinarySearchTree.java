package tree.bst;

import static tree.bst.BinarySearchTreeType.INORDER_ASCENDING;
import static tree.bst.BinarySearchTreeType.INORDER_DESCENDING;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import queue.IQueue;
import tree.BinaryTree;
import tree.ExtendedBinaryTree;
import tree.IntegerBinaryTree;
import tree.RotationType;
import tree.TreeNode;
import utils.MyUtilities;

/**
 * For a function, code in {@link BinarySearchTree}. Use some other class like
 * {@link IntegerBST} to create an object and test its functionality as it extends
 * {@link BinarySearchTree}.
 *  If it works move code to {@link ExtendedBinarySearchTree}
 * @author nithin
 *
 * @param <K>
 */
public class ExtendedBinarySearchTree<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	/*
	public void mirrorize() {
		super.mirrorize();
		switch (treeType) {
		case INORDER_ASCENDING:
			treeType = INORDER_DESCENDING;
		case INORDER_DESCENDING:
			treeType = INORDER_ASCENDING;
		}
	}
	*/
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
	
	/**
	 * http://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/
	 * 
	 * Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form.
	 *  The expected time complexity is O(m+n) where m is the number of nodes in first tree and n is the number of nodes in second tree.
	 *  Maximum allowed auxiliary space is O(height of the first tree + height of the second tree).
	 * @param tree
	 */
	public List<K> getSortedOrder(BinarySearchTree<K> tree) {
		List<K> list = new ArrayList<K>();
		Stack<TreeNode<K>> stack1 = new Stack<TreeNode<K>>();
		if(root != null) {
			stack1.push(root);
		}
		Stack<TreeNode<K>> stack2 = new Stack<TreeNode<K>>();
		if(tree.root != null) {
			stack2.push(tree.root);
		}
		//ptr for stacks
		TreeNode<K> ptr1 = root;
		TreeNode<K> ptr2 = tree.root;
		
		TreeNode<K> check1 = null;
		TreeNode<K> check2 = null;
		while(!(stack1.empty() && stack2.empty())) {
			// at least one is not empty
			if(!stack1.empty() && !stack2.empty()) {
				//both not empty
				while(ptr1.left != null) {
					stack1.push(ptr1.left);
					ptr1 = ptr1.left;
				}
				while(ptr2.left != null) {
					stack2.push(ptr2.left);
					ptr2 = ptr2.left;
				}
				if(check1 == null) {
					check1 = stack1.pop();
					if(check1.right != null) {
						ptr1 = check1.right;
						stack1.push(ptr1);
					}
				}
					
				if(check2 == null) {
					check2 = stack2.pop();
					if(check2.right != null) {
						ptr2 = check2.right;
						stack2.push(ptr2);
					}
				}
					
				if((check1.data.equals(25)) && (check2.data.equals(48))) {
					System.out.println();
				}
				int compareTo = check1.data.compareTo(check2.data);
				if(compareTo == 0) {
					//both elements same
					list.add(check1.data);
					list.add(check2.data);
					/*if(check1.right != null) {
						ptr1 = check1.right;
						stack1.push(ptr1);
					}
					if(check2.right != null) {
						ptr2 = check2.right;
						stack2.push(ptr2);
					}*/
					check1 = null;
					check2 = null;
				} else if(compareTo < 0) {
					//check 1 lesser
					list.add(check1.data);
					/*if(check1.right != null) {
						ptr1 = check1.right;
						stack1.push(ptr1);
					}*/
					check1 = null;
				} else {
					//check 2 smaller
					list.add(check2.data);
					/*if(check2.right != null) {
						ptr2 = check2.right;
						stack2.push(ptr2);
					}*/
					check2 = null;
				}
			} else if(!stack1.empty()) {
				//stack 2 empty
				while(ptr1.left != null) {
					stack1.push(ptr1.left);
					ptr1 = ptr1.left;
				}
				TreeNode<K> pop = stack1.pop();
				list.add(pop.data);
				if(pop.right != null) {
					ptr1 = pop.right;
					stack1.push(ptr1);
				}
			} else if(!stack2.empty()) {
				//stack 1 empty
				while(ptr2.left != null) {
					stack2.push(ptr2.left);
					ptr2 = ptr2.left;
				}
				TreeNode<K> pop = stack2.pop();
				list.add(pop.data);
				if(pop.right != null) {
					ptr2 = pop.right;
					stack2.push(ptr2);
				}
			}
		}
		return list;
	}
	
	
	/**
	 * http://www.geeksforgeeks.org/remove-bst-keys-outside-the-given-range/
	 * Remove BST keys outside the given range
	 * 
	 * Given a Binary Search Tree (BST) and a range [min, max], remove all keys which are outside the given range.
	 * The modified tree should also be BST
	 * @param lower
	 * @param higher
	 */
	public void removeKeysOutsiderange(K lower, K higher) {
	}
	
	/**
	 * http://www.geeksforgeeks.org/print-bst-keys-in-the-given-range/
	 * 
	 * Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
	 *  Print all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2
	 *  and x is a key of given BST. Print all the keys in increasing order.
	 * @param start
	 * @param end
	 */
	public void printElementsInRange(K start,K end) {
		printElementsInRange(root,start, end);
	}
	
	protected void printElementsInRange(TreeNode<K> ptr, K start, K end) {
		K data = ptr.data;
		int cmpStart = data.compareTo(start);
		int cmpEnd = data.compareTo(end);
		
		if(cmpStart >= 0) {
			printElementsInRange(start, data);
		}
		if(cmpStart >= 0 && cmpEnd <= 0) {
			System.out.println(data);
		}
		if(cmpEnd <= 0) {
			printElementsInRange(data, end);
		}
		
	}
	

	/**
	 * http://www.geeksforgeeks.org/sorted-order-printing-of-an-array-that-represents-a-bst/
	 * 
	 * Given an array that stores a complete Binary Search Tree, write a function that efficiently prints the given array in ascending order.

		For example, given an array [4, 2, 5, 1, 3], the function should print 1, 2, 3, 4, 5
	 */
	public static <K extends Comparable<? super K>> void getSortedOrder(K[] array) {
		getSortedOrder(array,0);
	}
	public static <K extends Comparable<? super K>> void getSortedOrder(K[] array,int start) {
		if(start > array.length -1) {
			return;
		} else if(start == array.length -1) {
			System.out.println(array[start]);
			return;
		} else {
			//node has children
			int leftChild = 2*start + 1;
			getSortedOrder(array,leftChild);
			System.out.println(array[start]);
			int rightChild = 2*(start + 1);
			getSortedOrder(array,rightChild);
		}
	}
}
