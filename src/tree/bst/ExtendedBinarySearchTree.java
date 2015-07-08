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
import tree.TreeNode;
import utils.MyUtilities;
import utils.RotationType;

/**
 * normal bst + more fns
 * @author nithin
 *
 * @param <K>
 */
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
	
	/**
	 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversal-set-2/
	 * Given preorder traversal of a binary search tree, construct the BST.
	 * @param preOrder
	 */
	public void populateFromPreOrder(K[] preOrder) {
		if(preOrder.length == 0)
			return;
		Stack<TreeNode<K>> stack = new Stack<TreeNode<K>>();
		int arrayItr = 0;
		TreeNode<K> node = makeNode(preOrder[arrayItr]);
		arrayItr++;
		stack.push(node);
		root = node;
		TreeNode<K> topNode = node;
		K topEle = preOrder[0];
		
		/*if last operation was a pop*/
		TreeNode<K> lastPoppedNode = null;
		K lastPopEle = null;
		
		while(arrayItr < preOrder.length) {
			K arrayEle = preOrder[arrayItr];
			node = makeNode(arrayEle);
			if(stack.empty()) {
				//stack empty
				int arrayCmpLastPop = arrayEle.compareTo(lastPopEle);
				if(arrayCmpLastPop >= 0) {
					lastPoppedNode.right = node;
					stack.push(lastPoppedNode);
					//initialze vars after push
					topNode = node;
					topEle = arrayEle;
					arrayItr++;
					
					lastPoppedNode = null;
					lastPopEle = null;
				} else {
					//TODO
				}
			} else {
				//stack not empty
				int eleCmpTop = arrayEle.compareTo(topEle);
				if(eleCmpTop <= 0) {
					if(lastPoppedNode != null) {
						lastPoppedNode.right = node;
						stack.push(node);
						arrayItr++;
					} else {
						topNode.left = node;
						stack.push(node);
						arrayItr++;
					}
					//initialze vars after push
					topNode = node;
					topEle = arrayEle;
					
					lastPoppedNode = null;
					lastPopEle = null;
				} else {
					stack.pop();
					//initialize variables after pop
					lastPoppedNode = topNode;
					lastPopEle = topEle;
					
					if(stack.empty()) {
						topEle = null;
						topNode = null;
					} else {
						topNode = stack.peek();
						topEle = topNode.data;
					}
				}
			}
			/*node = makeNode(arrayEle);
			if(!stack.empty()) {
				topNode = stack.peek();
				topEle = topNode.data;
				
				int eleCmpTop = arrayEle.compareTo(topEle);
				if(eleCmpTop <= 0) {
					topNode.left = node;
					stack.push(node);
					arrayItr++;
				} else {
					if(stack.empty()) {
						topNode.right = node;
						stack.push(node);
						arrayItr++;
					} else {
						//same as topNode
						stack.pop();
						TreeNode<K> newTopNode = stack.peek();
						K newTopEle = newTopNode.data;
						int eleCmpNewTop = arrayEle.compareTo(newTopEle);
						if(eleCmpNewTop <= 0) {
							//same as the popped one
							topNode.right = node;
							stack.push(node);
							arrayItr++;
						} else {
							//array itr remains same
						}
					}
					
				}
			} else {
				topEle = null;
				topNode = null;
				stack.push(node);
				arrayItr++;
			}*/
		}
	}
}
