package tree;

import static tree.BinarySearchTreeType.INORDER_ASCENDING;
import static tree.BinarySearchTreeType.INORDER_DESCENDING;
import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import queue.IQueue;
import utils.MyUtilities;
import utils.RotationType;

public class ExtendedBinarySearchTree<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	public void mirrorize() {
		mirrorize(root);
		switch (treeType) {
		case INORDER_ASCENDING:
			treeType = INORDER_DESCENDING;
		case INORDER_DESCENDING:
			treeType = INORDER_ASCENDING;
		}
	}
	
	public boolean isMirrorTree() {
		return isMirrorTree(root.left,root.right);
	}
	
	private boolean isMirrorTree(TreeNode<K> left, TreeNode<K> right) {
		if(left == null && right == null) {
			return true;
		}
		if(left != null && right != null) {
			K leftData = left.data;
			K rightData = right.data;
			if(leftData.equals(rightData)) {
				return isMirrorTree(left.left,right.right) && isMirrorTree(left.right,right.left);
			}
		}
		return false;
	}

	private void mirrorize(TreeNode<K> ele) {
		if(ele != null) {
			mirrorize(ele.left);
			mirrorize(ele.right);
			MyUtilities.swap(ele.left, ele.right);
		}
	}
	
	public IList<K> getLevelOrderTraversal() {
		IList<K> list = new MyArrayList<K>();
		populateLevelOrderFor(root,list);
		return list;
	}
	
	private void populateLevelOrderFor(TreeNode<K> node, IList<K> list) {
		IQueue<TreeNode<K>> queue = new MyLinkedList<TreeNode<K>>();
		queue.enqueue(node);
		TreeNode<K> dequeuedEle = queue.dequeue();
		while(dequeuedEle != null) {
			list.add(dequeuedEle.data);
			if(dequeuedEle.left != null) {
				queue.enqueue(dequeuedEle.left);
			}
			if(dequeuedEle.right != null) {
				queue.enqueue(dequeuedEle.right);
			}
			dequeuedEle = queue.dequeue();
		}
	}
	
	public boolean isBalancedTree() {
		Info info = new Info();
		return isBalancedTree(root,0,info);
	}
	
	private boolean isBalancedTree(TreeNode<K> root,int currentLevel,Info info) {
		if(root == null) {
			return true;
		}
		else {
			currentLevel ++;
			if(root.isLeaf()) {
				if(info.minDepthLevel == 0) {
					info.minDepthLevel = currentLevel;
					return true;
				} else {
					int diff = currentLevel - info.minDepthLevel;
					if(diff ==0 || diff ==1 || diff==-1) {
						info.minDepthLevel = Math.min(currentLevel, info.minDepthLevel);
						return true;
					}
					return false;
						
				}
			}
			else {
				return isBalancedTree(root.left,currentLevel ,info
				) && isBalancedTree(root.right,currentLevel,info);
			}
		}
	}
	
	class Info {
		private int minDepthLevel;
	}
	
	public static <I extends Comparable<I>> TreeNode<I> getTreeFrom(I[] sortedArrayinAscendingOrder) {
		return getTreeFrom(sortedArrayinAscendingOrder,0,sortedArrayinAscendingOrder.length-1);
	}
	
	public static <I extends Comparable<I>> TreeNode<I> getTreeFrom(I[] sortedArrayinAscendingOrder,int start,
			int end) {
		if(end < start)
			return null;
		else {
			int half = (start + end)/2;
			I data = sortedArrayinAscendingOrder[half];
			TreeNode<I> root = new TreeNode<I>(data, null, null);
			root.left = getTreeFrom(sortedArrayinAscendingOrder, start, half-1);
			root.right = getTreeFrom(sortedArrayinAscendingOrder, half+1,end);
			return root;
		}
	}
}
