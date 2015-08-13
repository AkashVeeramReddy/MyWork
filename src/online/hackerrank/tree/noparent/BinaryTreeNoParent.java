package online.hackerrank.tree.noparent;

import java.awt.Desktop;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import tree.examples.TreeExamples;
import utils.FileUtils;
import utils.MyConstants;
import utils.PatternUtils;

public class BinaryTreeNoParent {
	
	protected Node root;
	
	public BinaryTreeNoParent() {
		
	}
	
	public BinaryTreeNoParent(String testFile) {
		//populateTree(testFile);
		root = BinaryTreeUtils.populateTree(testFile, this);
	}
	
	public BinaryTreeNoParent(File testFile) {
		//populateTree(testFile);
		root = BinaryTreeUtils.populateTree(testFile, this);
	}

	public Node makeNode(int key) {
		Node node = new Node();
		node.data = key;
		return node;
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
	
	protected StringBuilder getDotFileContents(Node ptrRoot) {
		
		StringBuilder builder = new StringBuilder();
		if(ptrRoot == null)
			return builder;
		String string = ptrRoot.data + "";
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
			
			String leftStr = ptrRoot.data + "";
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
			
			String rightStr = ptrRoot.data + "";
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
	
	@Override
	public String toString() {
		return toString(root);
	}
	
	private String toString(Node root) {
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
	
	//Programs start here
	
	
}
