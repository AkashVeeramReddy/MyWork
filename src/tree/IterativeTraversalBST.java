package tree;

import queue.IQueue;
import stack.IStack;
import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;

public class IterativeTraversalBST<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	public IList<K> populateIterativePreorderTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		IStack<TreeNode<K>> stack = new MyLinkedList<TreeNode<K>>();
		stack.push(root);
		TreeNode<K> removedEle = stack.pop();
		while(removedEle != null) {
			nodes.add(removedEle.data);
			if(removedEle.right != null)
				stack.push(removedEle.right);
			if(removedEle.left != null)
				stack.push(removedEle.left);
			removedEle = stack.pop();
		}
		return nodes;
	}
	
	public IList<K> populateIterativeInorderTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		IStack<TreeNode<K>> stack = new MyLinkedList<TreeNode<K>>();
		stack.push(root);
		TreeNode<K> itrElement = root;
		while(stack.canPop()) {
			if(itrElement == null) {
				TreeNode<K> pop = stack.pop();
				nodes.add(pop.data);
				itrElement = pop.right;
			} else {
				itrElement = itrElement.left;
			}
			if(itrElement != null) {
				stack.push(itrElement);
			}
		}
		return nodes;
	}
	
	public IList<K> populateIterativePostorderTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		MyLinkedList<TreeNode<K>> stack = new MyLinkedList<TreeNode<K>>();
		stack.push(root);
		TreeNode<K> itrElement = root;
		while(stack.canPop()) {
			if(itrElement == null) {
				itrElement = stack.pop();
				nodes.add(itrElement.data);
				itrElement = itrElement.right;
				if(itrElement != null)
					stack.push(itrElement);
			} else {
				/*if(itrElement.right != null)
					stack.push(itrElement.right);*/
				if(itrElement.left != null) {
					stack.push(itrElement.left);
				}
				itrElement = itrElement.left;
			}
		}
		return nodes;
	}
	
}
