package tree;

import java.io.File;

import list.IList;
import list.arraylist.MyArrayList;
import tree.fns.IMakeNode;
import tree.fns.TreePopulate;


public class IntegerBinaryTree extends BinaryTreeComparable<Integer> 
	implements IMakeNode<Integer, TreeNode<Integer>> {
	
	public IntegerBinaryTree() {
		
	}
	
	public IntegerBinaryTree(String testFile) {
		//populateTree(testFile);
		root = TreePopulate.populateTree(testFile, this);
	}
	
	public IntegerBinaryTree(File testFile) {
		//populateTree(testFile);
		root = TreePopulate.populateTree(testFile, this);
	}

/*
	protected void populateTree(String testFile) {
		File file = FileUtils.getFile(testFile,TreeExamples.class);
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
			e.printStackTrace();
		}
	}
*/
	
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
	
	public boolean isSumTree() {
		return getSumInfo(root).isSum;
	}
	
	/**
	 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/
	 * 
	 * Write a function that returns true if the given Binary Tree is SumTree else false.
	 *  A SumTree is a Binary Tree where the value of a node is equal to sum of the nodes present in its left subtree and right subtree.
	 *  An empty tree is SumTree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.

		Following is an example of SumTree.
		
		          26
		        /   \
		      10     3
		    /    \     \
		  4      6      3
	 * @param ptrRoot
	 * @return
	 */
	protected IsSumInfo getSumInfo (TreeNode<Integer> ptrRoot) {
		if(ptrRoot == null) {
			IsSumInfo isSumInfo = new IsSumInfo();
			isSumInfo.isSum = true;
			return isSumInfo;
		} else {
			if(ptrRoot.left == null && ptrRoot.right == null) {
				IsSumInfo isSumInfo = new IsSumInfo();
				isSumInfo.isSum = true;
				isSumInfo.subTreeSum = ptrRoot.data;
				return isSumInfo;
			} else /*if(ptrRoot.left != null && ptrRoot.right != null)*/ {
				IsSumInfo sumInfoLeft = getSumInfo(ptrRoot.left);
				IsSumInfo sumInfoRight = getSumInfo(ptrRoot.right);
				
				int sumLeftAndRight = sumInfoLeft.subTreeSum + sumInfoRight.subTreeSum;
				if(sumInfoLeft.isSum && sumInfoRight.isSum) {
					
					if(sumLeftAndRight == ptrRoot.data) {
						sumInfoLeft.isSum = true;
						sumInfoLeft.subTreeSum = 2*sumLeftAndRight;
						return sumInfoLeft;
					} else {
						sumInfoLeft.isSum = false;
						sumInfoLeft.subTreeSum = sumLeftAndRight + ptrRoot.data;
						return sumInfoLeft;
					}
				} else {
					sumInfoLeft.isSum = false;
					sumInfoLeft.subTreeSum = sumLeftAndRight + ptrRoot.data;
					return sumInfoLeft;
				}
			}
		}
	}
	/**
	 * http://www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
	 * @param preorder
	 * @param leafInfo - true if it is a leaf, else non leaf
	 */
	public void populateWithSpecialPreorder(Integer[] preorder,Boolean[] leafInfo) {
		root = populateWithSpecialPreorder(preorder, leafInfo, 0).root;
	}
	
	/**
	 * http://www.geeksforgeeks.org/fix-two-swapped-nodes-of-bst/
	 * Two of the nodes of a Binary Search Tree (BST) are swapped. Fix (or correct) the BST.

		Input Tree:
		         10
		        /  \
		       5    8
		      / \
		     2   20
		
		In the above tree, nodes 20 and 8 must be swapped to fix the tree.  
		Following is the output tree
		         10
		        /  \
		       5    20
		      / \
		     2   8
	 */
	public void correctBSTBySwappingTwoNodes() {
		getIncorrectedNode(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	protected TreeNode<Integer> getIncorrectedNode(TreeNode<Integer> root,int lower, int higher) {
		if(root != null) {
			Integer data = root.data;
			if(data > lower && data < higher) {
				TreeNode<Integer> incorrectedNode = getIncorrectedNode(root.left, lower, data);
			}
		}
		return null;
	}
	public SpecialPreorderInfo populateWithSpecialPreorder(Integer[] preorder,
			Boolean[] leafInfo,int idx) {
		if(idx >= preorder.length) {
			SpecialPreorderInfo info = new SpecialPreorderInfo();
			info.root = null;
			info.lastIdx = -1;
			return info;
		}
		if(leafInfo[idx]) {
			//node leaf
			SpecialPreorderInfo info = new SpecialPreorderInfo();
			TreeNode<Integer> node = makeNode(preorder[idx]);
			info.root = node;
			info.lastIdx = idx;
			return info;
		} else {
			//non leaf
			SpecialPreorderInfo info = new SpecialPreorderInfo();
			
			TreeNode<Integer> node = makeNode(preorder[idx]);
			
			SpecialPreorderInfo leftInfo = populateWithSpecialPreorder(preorder, leafInfo,idx + 1);
			node.left = leftInfo.root;
			
			SpecialPreorderInfo rightInfo = populateWithSpecialPreorder(preorder,
					leafInfo,leftInfo.lastIdx + 1);
			node.right = rightInfo.root;
			
			info.root = node;
			info.lastIdx = rightInfo.lastIdx;
			return info;
		}
	}
	
	static class SpecialPreorderInfo {
		int lastIdx = 0;
		TreeNode<Integer> root;
	}
	static class IsSumInfo {
		boolean isSum = false;
		int subTreeSum = 0;
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("IsSumInfo [isSum=");
			builder.append(isSum);
			builder.append(", subTreeSum=");
			builder.append(subTreeSum);
			builder.append("]");
			return builder.toString();
		} 
		
	}
	
	/**
	 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
	 * 
	 * Boundary Traversal of binary tree
		Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
		For example, boundary traversal of the following tree is “20 8 4 10 14 25 22″
	 * @return
	 */
	public IList<Integer> populateBoundaryTraversal() {
		IList<Integer> nodes = new MyArrayList<Integer>();
		//get non leaf nodes in left edge
		TreeNode<Integer> ptr = root;
		while(ptr.left != null) {
			nodes.add(ptr.data);
			ptr = ptr.left;
		}
		//populate leaf nodes
		populateLeafNodesInInorderFashion(root, nodes);
		//populate non leaf nodes in right edge
		populateNonLeafNodesInRightEdge(root.right, nodes);
		return nodes;
	}
	public void populateNonLeafNodesInRightEdge(TreeNode<Integer> ptr,IList<Integer> list) {
		if(ptr.right == null) {
			return;
		} else {
			populateNonLeafNodesInRightEdge(ptr.right, list);
			list.add(ptr.data);
		}
		
	}
	public void populateLeafNodesInInorderFashion(TreeNode<Integer> ptr,IList<Integer> list) {
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

}
