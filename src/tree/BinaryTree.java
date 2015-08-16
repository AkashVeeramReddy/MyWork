package tree;

import java.awt.Desktop;
import java.io.File;
import java.util.regex.Matcher;

import tree.examples.TreeExamples;
import utils.FileUtils;
import utils.MyConstants;
import utils.PatternUtils;
import utils.ValidationUtils;
/**
 * Basic fns in a binary tree
 * @author user
 *
 * @param <K>
 */
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
	
	
	
	public TreeNode<K> makeNode(K element) {
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
	
	protected TreeNode<K> getNodeFromData(K data) {
		return getNodeFromData(root,data);
	}
	
	protected TreeNode<K> getNodeFromData(TreeNode<K> node, K data) {
		if(node == null) {
			return null;
		}
		if(node.data == data || node.data.equals(data)) {
			return node;
		} else {
			TreeNode<K> left = getNodeFromData(node.left,data);
			TreeNode<K> right = getNodeFromData(node.right,data);
			return (left != null) ? left : right;
		}
	}
	
	
	
}
