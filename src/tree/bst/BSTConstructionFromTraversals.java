package tree.bst;

import java.util.Stack;

import tree.TreeNode;

public class BSTConstructionFromTraversals<K extends Comparable<? super K>> extends BinarySearchTree<K> {
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
	 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
	 * @param preOrder
	 */
	public void populateFromPreOrderRec(K[] preOrder) {
		root = populateFromPreOrder(preOrder,0,preOrder.length-1,null,null).root;
	}
	/**
	 * 
	 * @param preOrder
	 * @param start start idx
	 * @param end end idx
	 * @param object lower limit
	 * @param object2 end limit
	 * @return
	 */
	protected PreOrderInfo populateFromPreOrder(K[] preOrder, int start, int end,
			K lower, K higher) {
		if(start == end) {
			TreeNode<K> node = new TreeNode<K>(preOrder[start], null, null);
			if(higher != null) {
				int compareTo = node.data.compareTo(higher);
				if(compareTo > 0) {
					//greater element has come
					PreOrderInfo info = new PreOrderInfo();
					info.root = null;
					info.idxOfGr8rEleThanRoot = start;
					return info;
				}
			}
			PreOrderInfo info = new PreOrderInfo();
			info.root = node;
			info.idxOfGr8rEleThanRoot = start + 1;
			return info;
		} else if(start < end) {
			TreeNode<K> node = new TreeNode<K>(preOrder[start], null, null);
			if(lower != null) {
				int compareTo = node.data.compareTo(lower);
				if(compareTo < 0) {
					//not possible
					return null;
				}
			}
			if(higher != null) {
				int compareTo = node.data.compareTo(higher);
				if(compareTo > 0) {
					//greater element has come
					PreOrderInfo info = new PreOrderInfo();
					info.root = null;
					info.idxOfGr8rEleThanRoot = start;
					return info;
				}
			}
			PreOrderInfo leftInfo = populateFromPreOrder(preOrder, start+1, end, lower, preOrder[start]);
			node.left = leftInfo.root;
			PreOrderInfo rightInfo = populateFromPreOrder(preOrder, leftInfo.idxOfGr8rEleThanRoot,end , preOrder[start], higher);
			node.right = rightInfo.root;
			PreOrderInfo info = new PreOrderInfo();
			info.root = node;
			info.idxOfGr8rEleThanRoot = rightInfo.idxOfGr8rEleThanRoot;
			return info;
		}
		return null;
	}
	
	public class PreOrderInfo {
		TreeNode<K> root;
		int idxOfGr8rEleThanRoot;
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
