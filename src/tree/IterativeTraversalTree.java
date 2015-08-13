package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import stack.IStack;

public class IterativeTraversalTree<K> extends BinaryTree<K> {
	
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
		if(root == null)
			return nodes;
		MyLinkedList<TreeNode<K>> stack = new MyLinkedList<TreeNode<K>>();
		if(root.right != null)
			stack.push(root.right);
		stack.push(root);
		TreeNode<K> itrElement = root.left;
		while(stack.canPop()) {
			if(itrElement == null) {
				itrElement = stack.pop();
				if(itrElement.right == null) {
					nodes.add(itrElement.data);
					//itrElement set to null
					itrElement = null;
				} else {
					if(stack.canPop() && (stack.getHeader().getData() == itrElement.right)) {
						//remove the right child
						stack.pop();
						//push the poped itrElement again
						stack.push(itrElement);
						//point itrElement to right child
						itrElement = itrElement.right;
					} else {
						//right child visited
						nodes.add(itrElement.data);
						//itrElement set to null
						itrElement = null;
					}
				}
			} else {
				if(itrElement.right != null)
					stack.push(itrElement.right);
				stack.push(itrElement);
				/*if(itrElement.left != null) {
					stack.push(itrElement.left);
				}*/
				itrElement = itrElement.left;
			}
		}
		return nodes;
	}
	/**
	 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
	 * 
	 * Boundary Traversal of binary tree
		Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
		For example, boundary traversal of the following tree is “20 8 4 10 14 25 22″
	 * @return
	 */
	public IList<K> populateBoundaryTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		//get non leaf nodes in left edge
		TreeNode<K> ptr = root;
		while(ptr != null) {
			nodes.add(ptr.data);
			ptr = ptr.left;
		}
		//populate leaf nodes
		populateLeafNodesInInorderFashion(root.right, nodes);
		//populate non leaf nodes in right edge
		
		return nodes;
	}
	public void populateNonLeafNodesInRightEdge(TreeNode<K> ptr,IList<K> list) {
		if(ptr == null) {
			return;
		} else {
			populateNonLeafNodesInRightEdge(ptr.right, list);
			list.add(ptr.data);
		}
		
	}
	public void populateLeafNodesInInorderFashion(TreeNode<K> ptr,IList<K> list) {
		if(ptr == null) {
			return;
		} else if(ptr.left == null && ptr.right == null) {
			//leaf
			list.add(ptr.data);
		} else {
			populateLeafNodesInInorderFashion(ptr.left, list);
			populateLeafNodesInInorderFashion(ptr.right, list);
		}
	}
	
	/**
	 * Print Nodes in Top View of Binary Tree
Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. Given a binary tree, print the top view of it. The output nodes can be printed in any order. Expected time complexity is O(n)

A node x is there in output if x is the topmost node at its horizontal distance. Horizontal distance of left child of a node x is equal to horizontal distance of x minus 1, and that of right child is horizontal distance of x plus 1.

       1
    /     \
   2       3
  /  \    / \
 4    5  6   7
Top view of the above binary tree is
4 2 1 3 7

        1
      /   \
    2       3
      \   
        4  
          \
            5
             \
               6
Top view of the above binary tree is
2 1 3 6
	 */
	public List<K> getTopView() {
		List<K> list = new ArrayList<K>();
		LinkedList<TopViewInfo<K>> queue = new LinkedList<TopViewInfo<K>>();
		int lowerLevelViewInfo = 1;
		int upperLevelViewInfo = -1;
		if(root != null) {
			queue.addLast(new TopViewInfo<K>(root.data, 0));
			while(queue.size() > 0) {
				TopViewInfo<K> removeFirst = queue.removeFirst();
			}
		}
		return list;
	}
	
	public static class TopViewInfo<K> {
		K data;
		int viewInfo;
		
		public TopViewInfo(K data,int viewInfo) {
			this.data = data;
			this.viewInfo = viewInfo;
		}
	}
}
