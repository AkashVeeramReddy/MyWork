package tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class BinaryTreeConstructionFromTraversals<K> extends BinaryTree<K> {
	/**
	 * http://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
	 * Given two arrays that represent preorder and postorder traversals of a full binary tree,
	 *  construct the binary tree.

		A Full Binary Tree is a binary tree where every node has either 0 or 2 children
		
	 * tree should be preferably empty
	 * @param preOrder
	 * @param postOrder
	 */
	public void populateFullTreeFromPreorderPostOrder(K[] preOrder,K[] postOrder) {
		if(preOrder.length == 0 || postOrder.length == 0)
			return;
		Map<K, Integer> preOrderMap = new HashMap<K, Integer>();
		for (int i = 0; i < preOrder.length; i++) {
			preOrderMap.put(preOrder[i], i);
		}
		Map<K, Integer> postOrderMap = new HashMap<K, Integer>();
		for (int i = 0; i < postOrder.length; i++) {
			postOrderMap.put(postOrder[i], i);
		}
		root = populateFullTreeFromPreorderPostOrder(preOrder, postOrder, preOrderMap,
				postOrderMap, 0, postOrder.length - 1);
	}
	
	protected TreeNode<K> populateFullTreeFromPreorderPostOrder(K[] preOrder,K[] postOrder,
			Map<K, Integer> preOrderIdxInfo, Map<K, Integer> postOrderIdxInfo,
			int preOrderIdx, int postOrderIdx) {
		TreeNode<K> node = makeNode(preOrder[preOrderIdx]);
		boolean hasChild = false;
		int leftChildIndex = preOrderIdx+1;
		int rightChildIdx = postOrderIdx-1;
		if(leftChildIndex == preOrder.length || rightChildIdx < 0) {
			hasChild = false;
		} else {
			K leftChild = preOrder[leftChildIndex];
			/*
			 * if left child exists, then left child should occur before the parent
			 * in post order trvaersal.
			 */
			Integer postOrderLeftChildIdx = postOrderIdxInfo.get(leftChild);
			if(postOrderLeftChildIdx < postOrderIdx) {
				hasChild = true;
			} else {
				hasChild = false;
			}
		}
		if(hasChild) {
			node.left = populateFullTreeFromPreorderPostOrder(preOrder, postOrder, preOrderIdxInfo,
					postOrderIdxInfo, leftChildIndex, postOrderIdxInfo.get(preOrder[leftChildIndex]));
			node.right = populateFullTreeFromPreorderPostOrder(preOrder, postOrder, preOrderIdxInfo,
					postOrderIdxInfo, preOrderIdxInfo.get(postOrder[rightChildIdx]), rightChildIdx);
		}
		return node;
	}
	
	/**
	 * http://www.geeksforgeeks.org/given-linked-list-representation-of-complete-tree-convert-it-to-linked-representation/
	 * Given Linked List Representation of Complete Binary Tree, construct the Binary tree
	 * @param list
	 */
	public void populateCompleteTreeFromLinkedList(LinkedList<K> list) {
		LinkedList<TreeNode<K>> queue = new LinkedList<TreeNode<K>>();
		Iterator<K> listIterator = list.iterator();
		if(list.isEmpty()) {
			root = null;
		}
		K data = (K) listIterator.next();
		TreeNode<K> makeNode = makeNode(data);
		queue.add(makeNode);
		//stack.push(data);
		while (!queue.isEmpty()) {
			TreeNode<K> pop = queue.removeFirst();
			if(root == null) {
				root = pop;
			}
			if(listIterator.hasNext()) {
				data = (K) listIterator.next();
				makeNode = makeNode(data);
				pop.left = makeNode;
				queue.add(makeNode);
			}
			if(listIterator.hasNext()) {
				data = (K) listIterator.next();
				makeNode = makeNode(data);
				pop.right = makeNode;
				queue.add(makeNode);
			}
			
		}
		
	}
}
