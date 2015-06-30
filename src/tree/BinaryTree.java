package tree;

import java.awt.Desktop;
import java.io.File;
import java.util.regex.Matcher;

import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import queue.IQueue;
import tree.examples.TreeExamples;
import utils.FileUtils;
import utils.MyConstants;
import utils.MyUtilities;
import utils.PatternUtils;
import utils.ValidationUtils;

public class BinaryTree<K> {
	
	protected TreeNode<K> root;
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

		A tree can be folded if left and right subtrees of the tree are structure wise mirror image of each other. An empty tree is considered as foldable.
		
		Consider the below trees:
		(a) and (b) can be folded.
		(c) and (d) cannot be folded.
		
		(a)
		       10
		     /    \
		    7      15
		     \    /
		      9  11
		
		(b)
		        10
		       /  \
		      7    15
		     /      \
		    9       11
		
		(c)
		        10
		       /  \
		      7   15
		     /    /
		    5   11
		
		(d)
		
		         10
		       /   \
		      7     15
		    /  \    /
		   9   10  12
	 * @param root2
	 * @return
	 */
	protected boolean isFoldable(TreeNode<K> root2) {
		return false;
	}
}
