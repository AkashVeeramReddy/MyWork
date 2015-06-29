package tree;
import static tree.BinarySearchTreeType.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import queue.IQueue;
import list.linkedlist.MyLinkedList;
import utils.FileUtils;
import utils.MyConstants;
import utils.MyUtilities;
import utils.PatternUtils;
public class BinarySearchTree<K extends Comparable<? super K>> {
	
	protected TreeNode<K> root;
	protected BinarySearchTreeType treeType;
	public BinarySearchTree(){
		this(INORDER_ASCENDING);
	}
	
	public BinarySearchTree(BinarySearchTreeType treeType){
		this.treeType = treeType;
	}
	
	public boolean add(K element) {
		if(root == null) {
			root = makeNode(element);
		} else {
			TreeNode<K> itr = root;
			TreeNode<K> itrParent = null;
			int compareTo = 0;
			while(itr != null) {
//				compareTo = itr.data.compareTo(element);
				compareTo = element.compareTo(itr.data);
				if(compareTo == 0)
					return false;
				else {
					itrParent = itr;
					if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
						itr = itr.left;
					} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
						itr = itr.right;
					}
				}
			}
			itr = makeNode(element);
			setAttributesWhileAddingNodes(itr, itrParent, compareTo);
		}
		return true;
	}

	protected void setAttributesWhileAddingNodes(TreeNode<K> itr,
			TreeNode<K> itrParent, int compareTo) {
		if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
			itrParent.left = itr;
		} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
			itrParent.right = itr;
		}
	}
	
	protected TreeNode<K> makeNode(K element) {
		return new TreeNode<K>(element, null, null);
	}

	public boolean isAscendingSearchTree() {
		switch (treeType) {
		case INORDER_ASCENDING:
			return true;
		case INORDER_DESCENDING:
			return false;
		default:
			return false;
		}
	}
	
	public boolean contains(K element){
		TreeNode<K> itr= root;
		while (itr != null) {
			int compareTo = element.compareTo(itr.data);
			if(compareTo == 0) {
				return true;
			} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
				itr=itr.right;
			} else if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
				itr = itr.left;
			}
		}
		return false;
	}
	
	private boolean contains(TreeNode<K> node,K element){
		if (node != null) {
			int compareTo = element.compareTo(node.data);
			if(compareTo == 0) {
				return true;
			} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
				return contains(node.right,element);
			} else if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
				return contains(node.right,element);
			}
		}
		return false;
	}
	
	public boolean recursiveContains(K element){
		return contains(root, element);
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
			
			builder.append(MyConstants.NEW_LINE);
		} else {
			String leftNull = "left" + nodeName;
			builder.append(leftNull);
			builder.append(" [label=\"null\"]");
			builder.append(MyConstants.NEW_LINE);
			
			builder.append(nodeName);
			builder.append(" " + "->" + " ");
			builder.append(leftNull);
			
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
			
			builder.append(MyConstants.NEW_LINE);
		} else {
			String rightNull = "right" + nodeName;
			builder.append(rightNull);
			builder.append(" [label=\"null\"]");
			builder.append(MyConstants.NEW_LINE);
			
			builder.append(nodeName);
			builder.append(" " + "->" + " ");
			builder.append(rightNull);
			
			builder.append(MyConstants.NEW_LINE);
		}
		return builder;
	}

	public boolean remove(K element) {
		TreeNode<K> itr = root;
		TreeNode<K> itrParent = null;
		int compareTo = 0;
		while(itr != null) {
//			compareTo = itr.data.compareTo(element);
			compareTo = element.compareTo(itr.data);
			if(compareTo == 0) {
				TreeNode<K> nextElement = findReplacementElement(itr);
				if(nextElement != null)
					itr.data = nextElement.data;
				else {
					int compareTo2 = itr.data.compareTo(itrParent.data);
					if(compareTo2 == treeType.getComparisonValueToGoToLeftPart()) {
						itrParent.right = null;
					} else if(compareTo2 == treeType.getComparisonValueToGoToRightPart()) {
						itrParent.left = null;
					}
				}
				return true;
			} else {
				itrParent = itr;
				if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
					itr = itr.left;
				} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
					itr = itr.right;
				}
			}
		}
		return false;
	}
	
	private TreeNode<K> findReplacementElement(TreeNode<K> ele) {
		if(ele.right != null) {
			return findNextElement(ele);
		} else if(ele.left != null) {
			return findPreviousElement(ele);
		}
		return null;
	}
	
	private TreeNode<K> findNextElement(TreeNode<K> ele) {
		TreeNode<K> itr = ele.right;
		TreeNode<K> parent = ele;
		while(itr.left != null) {
			parent = itr;
			itr = itr.left;
		}
		parent.left = null;
		return itr;
	}
	
	private TreeNode<K> findPreviousElement(TreeNode<K> ele) {
		TreeNode<K> itr = ele.left;
		TreeNode<K> parent = ele;
		while(itr.right != null) {
			parent = itr;
			itr = itr.right;
		}
		parent.right = null;
		return itr;
	}

}
