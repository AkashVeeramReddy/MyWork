package tree.fns;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tree.TreeNode;
import tree.examples.TreeExamples;
import utils.FileUtils;

public class TreePopulate {
	
	public static <K extends TreeNode<Integer>> K populateTree(String testFile,IMakeNode<Integer, K> tree) {
		File file = FileUtils.getFile(testFile,TreeExamples.class);
		if(file == null) {
			return null;
		}
		return populateTree(file, tree);
	}
	
	public static <K extends TreeNode<Integer>> K populateTree(File testFile,IMakeNode<Integer, K> tree) {
		K root = null;
		Map<String, K> map = new HashMap<String, K>();
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
									K node = tree.makeNode(key);
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
									
									K sourceNode = map.get(source);
									K destNode = map.get(dest);
									
									sourceNode.left = destNode;
								} else if(line.contains(TreeExamples.RIGHT_REP)) {
									//right edge
									int indexOf = line.indexOf("[");
									String edge = line.substring(0, indexOf).trim();
									
									int indexOfEdge = edge.indexOf(TreeExamples.EDGE_REP);
									
									String source = edge.substring(0, indexOfEdge).trim();
									String dest = edge.substring(indexOfEdge +
											TreeExamples.EDGE_REP.length(), edge.length()).trim();
									
									K sourceNode = map.get(source);
									K destNode = map.get(dest);
									
									sourceNode.right = destNode;
								}
							}
							
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
}
