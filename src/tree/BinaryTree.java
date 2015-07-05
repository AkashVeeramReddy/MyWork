package tree;

import java.awt.Desktop;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import queue.IQueue;
import tree.examples.TreeExamples;
import utils.FileUtils;
import utils.MyConstants;
import utils.PatternUtils;
import utils.ValidationUtils;

public class BinaryTree<K> {
	
	/*
	 * if protected, not able to access in subclasses in different package.
	 * no idea why
	 */
	public TreeNode<K> root;
	public BinaryTree() {
		
	}
	public boolean add(K element) {
		return false;
	}
	
	public boolean contains(K element){
		return contains(root, element);
	}
	
	protected boolean contains(TreeNode<K> ptr, K data) {
		if(ptr == null)
			return false;
		K ptrData = ptr.data;
		if(ValidationUtils.nullSafeEquals(ptrData, data)) {
			return true;
		} else {
			return contains(ptr.left, data) || contains(ptr.right, data);
		}
	}
	public boolean remove(K element) {
		return false;
	}
	
	public void mirrorize() {
		mirrorize(root);
	}
	
	public boolean isMirrorTree() {
		return isMirrorTree(root.left,root.right);
	}
	
	protected boolean isMirrorTree(TreeNode<K> left, TreeNode<K> right) {
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

	protected void mirrorize(TreeNode<K> ele) {
		if(ele != null) {
			mirrorize(ele.left);
			mirrorize(ele.right);
			//MyUtilities.swap(ele.left, ele.right);
			TreeNode<K> temp = ele.left;
			ele.left = ele.right;
			ele.right = temp;
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
	
	/**
	 * print all paths from root to all leaves
	 */
	public void printAllPaths() {
		printAllPaths(root,new StringBuilder());
	}
	
	protected void printAllPaths(TreeNode<K> node,StringBuilder builder) {
		if(node == null) {
			System.out.println(builder);
		} else {
			builder.append(node.data);
			//builder.append("->");
			if(node.left == null && node.right == null) {
				System.out.println(builder);
			} else {
				builder.append("->");
				if(node.left != null && node.right != null) {
					printAllPaths(node.left,new StringBuilder(builder));
					printAllPaths(node.right,new StringBuilder(builder));
				} else if(node.left == null) {
					printAllPaths(node.right,builder);
				} else {
					//node.right == null
					printAllPaths(node.left,builder);
				}
			}
			
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
	
	protected TreeNode<K> makeNode(K element) {
		return new TreeNode<K>(element, null, null);
	}
	
	@Override
	public String toString() {
		return toString(root);
	}
	
	private String toString(TreeNode<K> root) {
		if(root == null) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(toString(root.left));
		builder.append(",");
		builder.append(root.data);
		builder.append(",");
		builder.append(toString(root.right));
		return builder.toString();
	}
	
	public void showImage() throws RuntimeException {
		String dotContents = getDotFileContents();
		
		File homeFolder = new File(MyConstants.USER_HOME);
		File dotFile = new File(homeFolder,"tree.dot");
		File imageFile = null;
		try {
			if(!dotFile.exists()) {
				dotFile.createNewFile();
			}
			FileUtils.writeToFile(dotFile, dotContents);
			imageFile = new File(homeFolder, "tree.png");
			/*if(!imageFile.exists()) {
				imageFile.createNewFile();
			}*/
			Runtime.getRuntime().exec("dot -Tpng "+dotFile.getAbsolutePath()+
					" -o " + imageFile.getAbsolutePath());
			Desktop.getDesktop().open(imageFile);
		} catch (Exception e) {
			new RuntimeException(e);
		} finally {
			//imageFile.delete();
			//dotFile.delete();
		}
	}

	protected String getDotFileContents() {
		StringBuilder builder = new StringBuilder();
		builder.append("digraph DotContents {");
		builder.append(MyConstants.NEW_LINE);
		
		builder.append(getDotFileContents(root));
		builder.append(MyConstants.NEW_LINE);
		builder.append("}");
		
		return builder.toString();
		
	}
	
	protected StringBuilder getDotFileContents(TreeNode<K> ptrRoot) {
		
		StringBuilder builder = new StringBuilder();
		if(ptrRoot == null)
			return builder;
		String string = ptrRoot.data.toString();
		Matcher m = PatternUtils.WHITESPACE_PATTERN.matcher(string);
		String nodeName = m.replaceAll("");
		builder.append(nodeName);
		builder.append(MyConstants.NEW_LINE);
		
		if(ptrRoot.left == null && ptrRoot.right == null) {
			return builder;
		}
		
		if(ptrRoot.left != null) {
			builder.append(getDotFileContents(ptrRoot.left));
			builder.append(MyConstants.NEW_LINE);
			
			String leftStr = ptrRoot.left.data.toString();
			Matcher leftM = PatternUtils.WHITESPACE_PATTERN.matcher(leftStr);
			String leftNodeName = leftM.replaceAll("");
			
			builder.append(nodeName);
			builder.append(" " + "->" + " ");
			builder.append(leftNodeName);
			builder.append(TreeExamples.LEFT_EDGE_LABEL);
			
			builder.append(MyConstants.NEW_LINE);
		} else {
			String leftNull = "left" + nodeName;
			builder.append(leftNull);
			builder.append(" [label=\"null\"]");
			builder.append(MyConstants.NEW_LINE);
			
			builder.append(nodeName);
			builder.append(" " + "->" + " ");
			builder.append(leftNull);
			builder.append(TreeExamples.LEFT_EDGE_LABEL);
			
			builder.append(MyConstants.NEW_LINE);
		}
		if(ptrRoot.right != null) {
			builder.append(getDotFileContents(ptrRoot.right));
			builder.append(MyConstants.NEW_LINE);
			
			String rightStr = ptrRoot.right.data.toString();
			Matcher rightM = PatternUtils.WHITESPACE_PATTERN.matcher(rightStr);
			String rightNodeName = rightM.replaceAll("");
			
			builder.append(nodeName);
			builder.append(" " + "->" + " ");
			builder.append(rightNodeName);
			builder.append(TreeExamples.RIGHT_EDGE_LABEL);
			
			builder.append(MyConstants.NEW_LINE);
		} else {
			String rightNull = "right" + nodeName;
			builder.append(rightNull);
			builder.append(" [label=\"null\"]");
			builder.append(MyConstants.NEW_LINE);
			
			builder.append(nodeName);
			builder.append(" " + "->" + " ");
			builder.append(rightNull);
			builder.append(TreeExamples.RIGHT_EDGE_LABEL);
			
			builder.append(MyConstants.NEW_LINE);
		}
		return builder;
	}
	
	public boolean isFoldable() {
		return isFoldable(root);
	}
	
	/**
	 * http://www.geeksforgeeks.org/foldable-binary-trees/
	 * Question: Given a binary tree, find out if the tree can be folded or not.

	 * @param root2
	 * @return
	 */
	protected boolean isFoldable(TreeNode<K> root) {
		if(root == null) {
			return true;
		}
		if(root.left != null && root.right != null) {
			//mirrorize root's right subtree
			mirrorize(root.right);
			return isIdenticalStructure(root.left, root.right);
		}
		return false;
	}
	
	protected boolean isIdenticalStructure(TreeNode<K> ptr1,TreeNode<K> ptr2) {
		if(ptr1 == null && ptr2 == null) {
			return true;
		} else if(ptr1 != null && ptr2 != null) {
			return isIdenticalStructure(ptr1.left, ptr2.left) &&
					isIdenticalStructure(ptr1.right, ptr2.right);
		}
		return false;
	}
	
	/**
	 * http://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/
	 * 
	 * Given two binary trees, check if the first tree is subtree of the second one.
	 *  A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.
	 *   The subtree corresponding to the root node is the entire tree; 
	 *   the subtree corresponding to any other node is called a proper subtree.
	 * @param subTree
	 * @return - is subTree a sub tree of this tree
	 */
	public boolean isSubtree(BinaryTree<K> subTree) {
		return isSubtree(root, subTree.root);
	}
	
	public boolean isSubtree(TreeNode<K> ptrTree,TreeNode<K> ptrSubTree) {
		if(ptrTree == null && ptrSubTree == null) {
			return true;
		} else if(ptrTree == null || ptrSubTree == null) {
			return false;
		} else {
			//both not null
			boolean equals = ValidationUtils.nullSafeEquals(ptrTree.data, ptrSubTree.data);
			if(equals) {
				/*
				 * If data is equal 2 chances can arise
				 * 1) left subtrees of both are equal and right subtrees of both are equal.
				 * 2) check for ptrSubTree in left subtree and right sub tree
				 */
				return (isEqual(ptrTree.left,ptrSubTree.left) && isEqual(ptrTree.right,ptrSubTree.right))
						|| (
							(isSubtree(ptrTree.left,ptrSubTree)) || (isSubtree(ptrTree.right,ptrSubTree))
							);
			} else {
				//check for ptrSubTree in left subtree and right sub tree
				return isSubtree(ptrTree.left,ptrSubTree) || isSubtree(ptrTree.left,ptrSubTree);
			}
		}
	}
	
	/**
	 * are two trees equal
	 * @param tree
	 * @return
	 */
	public boolean isEqual(BinaryTree<K> tree) {
		return isEqual(root,tree.root);
	}
	
	protected boolean isEqual(TreeNode<K> ptr1,TreeNode<K> ptr2) {
		if(ptr1 == null && ptr2 == null) {
			return true;
		} else if(ptr1 != null && ptr2 != null) {
			return ValidationUtils.nullSafeEquals(ptr1.data, ptr2.data)
					&& isIdenticalStructure(ptr1.left, ptr2.left) &&
					isIdenticalStructure(ptr1.right, ptr2.right);
		}
		return false;
	}
	
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
	
	
}
