package tree;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tree.examples.TreeExamples;
import utils.FileUtils;


public class IntegerBinaryTree extends BinaryTree<Integer> {
	
	public IntegerBinaryTree() {
		
	}
	
	public IntegerBinaryTree(String testFile) {
		populateTree(testFile);
	}
	
	public IntegerBinaryTree(File testFile) {
		populateTree(testFile);
	}
	
	protected void populateTree(String testFile) {
		File file = getFile(testFile);
		if(file == null) {
			return;
		}
		populateTree(file);
	}
	
	protected void populateTree(File testFile) {
		Map<String, TreeNode<Integer>> map = new HashMap<String, TreeNode<Integer>>();
		try {
			List<String> lines = FileUtils.getFileLines(testFile);
			boolean processNodes = false;
			for (String line : lines) {
				line = line.trim();
				if(line.equals(TreeExamples.NODES_STR)) {
					processNodes = true;
				} else if(line.equals(TreeExamples.EDGES_STR)) {
					processNodes = false;
				} else {
					if(line.length() != 0) {
						if(!line.startsWith(TreeExamples.IGNORE_LINE)) {
							if(processNodes) {
								String nodeName = "";
								//process nodes
								if(line.contains(TreeExamples.NULL_REP)) {
									//null node
									int endIndex = line.indexOf("[");
									nodeName = line.substring(0, endIndex).trim();
									
									map.put(nodeName, null);
								} else {
									//valid node
									Integer key = Integer.parseInt(line);
									TreeNode<Integer> node = makeNode(key);
									map.put(line, node);
									if(root == null) {
										root = node;
									}
									
								}
							} else {
								//process edges
								if (line.contains(TreeExamples.LEFT_REP)) {
									//left edge
									int indexOf = line.indexOf("[");
									String edge = line.substring(0, indexOf).trim();
									
									int indexOfEdge = edge.indexOf(TreeExamples.EDGE_REP);
									
									String source = edge.substring(0, indexOfEdge).trim();
									String dest = edge.substring(indexOfEdge +
											TreeExamples.EDGE_REP.length(), edge.length()).trim();
									
									TreeNode<Integer> sourceNode = map.get(source);
									TreeNode<Integer> destNode = map.get(dest);
									
									sourceNode.left = destNode;
								} else if(line.contains(TreeExamples.RIGHT_REP)) {
									//right edge
									int indexOf = line.indexOf("[");
									String edge = line.substring(0, indexOf).trim();
									
									int indexOfEdge = edge.indexOf(TreeExamples.EDGE_REP);
									
									String source = edge.substring(0, indexOfEdge).trim();
									String dest = edge.substring(indexOfEdge +
											TreeExamples.EDGE_REP.length(), edge.length()).trim();
									
									TreeNode<Integer> sourceNode = map.get(source);
									TreeNode<Integer> destNode = map.get(dest);
									
									sourceNode.right = destNode;
								}
							}
							
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected File getFile(String testFile) {
		File file = new File(testFile);
		if(!file.exists()) {
			String path = "";
			try {
				path = TreeExamples.class.getProtectionDomain().getCodeSource()
									.getLocation().toURI().getPath();
				String regex = File.separator + "bin" +File.separator;
				String replacement = File.separator + "src" +File.separator;
				path = path.replaceAll(regex , 
						replacement);
				Package package1 = TreeExamples.class.getPackage();
				String folderName = path + package1.getName().replace('.', File.separatorChar);
				String filePath = folderName + File.separator + testFile;
				file = new File(filePath);
				if(!file.exists()) {
					return null;
				}
				//path = path + RELATIVE_LOCATION_IMG_FOLDER;
			} catch (URISyntaxException e) {
				//e.printStackTrace();
			}
		}
		return file;
	}
	
	
	/**
	 * http://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/
	 * 
	 * Given a binary tree or BST and a number, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals the given number.
	 *  Return false if no such path can be found.
	 * @param no
	 * @return
	 */
	public boolean isPathSumEquals(int no) {
		return isPathSumEquals(root,no);
	}

	protected boolean isPathSumEquals(TreeNode<Integer> root, int no) {
		if(root == null) {
			return no == 0;
		}
		return isPathSumEquals(root.left,no - root.data)||
				isPathSumEquals(root.right,no - root.data);
	}
	
	/**
	 * http://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/
	 * 
	 * Given a Binary Tree or BST, find the maximum sum path from a leaf to root. For example, in the following tree, 
	 * there are three leaf to root paths 8->-2->10, -4->-2->10 and 7->10. The sums of these three paths are 16, 4 and 17 respectively. The maximum of them is 17 and the path for maximum is 7->10.

                  10
               /      \
	     	-2        7
           /   \     
	 	8     -4    
	 * @return
	 */
	public int getMaxSumAndPrintPath() {
		int maxSum = getMaxSumInPath(root);
		printPath(maxSum);
		return maxSum;
	}
	
	public boolean printPath(int sum) {
		return printPath(root,sum);
	}
	
	protected boolean printPath(TreeNode<Integer> root, int sum) {
		if(root == null) {
			if(sum == 0) {
				return true;
			}
			return false;
		} else {
			boolean isSum = printPath(root.left,sum - root.data) || printPath(root.right, sum- root.data);
			if(isSum) {
				System.out.println(root.data);
			}
			return isSum;
		}
	}

	protected int getMaxSumInPath(TreeNode<Integer> root) {
		if(root == null)
			return 0;
		else {
			return root.data + Math.max(getMaxSumInPath(root.left),
					getMaxSumInPath(root.right));
		}
	}

}
