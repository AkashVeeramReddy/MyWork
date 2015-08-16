package tree;

import utils.ValidationUtils;

/**
 * For a function, code in {@link BinaryTree}. Use some other class like
 * {@link IntegerBinaryTree} to create an object and test its functionality
 *  as it extends {@link BinaryTree}.
 *  If it works move code to {@link ExtendedBinaryTree}
 * @author user
 *
 * @param <K>
 */
public class ExtendedBinaryTree<K> extends BinaryTree<K> {
	
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
	 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
	 * Given a Binary Tree, write a function to check whether the given Binary Tree is Complete Binary Tree or not.

		A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
		and all nodes are as far left as possible
	 * @return
	 */
	public boolean isCompleteTree() {
		if(root == null) {
			return true;
		}
		//do level order
		return false;
	}
	
	/**
	 * http://www.geeksforgeeks.org/in-place-convert-a-given-binary-tree-to-doubly-linked-list/
	 * iven a Binary Tree (Bt), convert it to a Doubly Linked List(DLL). 
	 * The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL.
	 *  The order of nodes in DLL must be same as Inorder of the given Binary Tree.
	 *  The first node of Inorder traversal (left most node in BT) must be head node of the DLL.
	 */
	public void convertBinaryTreeToLinkedListInPlace() {
		if(root != null) {
			root = convertBinaryTreeToLinkedListInPlace(root).head;
		}
	}
	protected BTtoLLInfo convertBinaryTreeToLinkedListInPlace(TreeNode<K> ptr) {
		if(ptr != null) {
			BTtoLLInfo left = null;
			if(ptr.left != null) {
				left = convertBinaryTreeToLinkedListInPlace(ptr.left);
			}
			BTtoLLInfo right = null;
			if(ptr.right != null) {
				right = convertBinaryTreeToLinkedListInPlace(ptr.right);
			}
			BTtoLLInfo info = new BTtoLLInfo();
			if(ptr.left != null) {
				info.head = left.head;
				ptr.left = left.tail;
				
				left.tail.right = ptr;
			} else {
				info.head = ptr;
			}
			if(ptr.right != null) {
				info.tail = right.tail;
				ptr.right = right.head;
				
				right.head.left = ptr;
			} else {
				info.tail = ptr;
			}
			return info;
		}
		return null;
	}
	
	public class BTtoLLInfo {
		TreeNode<K> head;
		TreeNode<K> tail;
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
	
	/**
	 * http://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
	 * Given a binary tree, a target node in the binary tree, and an integer value k, print all the nodes that are at distance k from the given target node.
	 *  No parent pointers are available.
	 * @param distance
	 * @param data
	 */
	public void printAllNodesAtDistance(int distance,K data) {
		TreeNode<K> nodeData = getNodeFromData(data);
		if(nodeData != null) {
			if(nodeData == root) {
				printAllNodesAtDistanceDown(root, distance);
			} else {
				printAllNodesAtDistanceDown(nodeData, distance);
				//print all nodes which have to be traversed upwards
				printAllNodesAtDistance(root, distance, nodeData);
			}
		}
	}
	
	public class DistInfo {
		int distFromNodeOfData;
		boolean isNodeDataPresent;
		@Override
		public String toString() {
			return "DistInfo [distFromNodeOfData=" + distFromNodeOfData
					+ ", isNodeDataPresent=" + isNodeDataPresent + "]";
		}
		
	}
	/**
	 * 
	 * @param rootPtr
	 * @param distance
	 * @param nodeData
	 * @return
	 */
	protected DistInfo printAllNodesAtDistance(TreeNode<K> rootPtr, int distance,
			TreeNode<K> nodeData) {
		if(distance == 0) {
			System.out.println(rootPtr.data);
		} else {
			if(rootPtr == nodeData) {
				DistInfo distInfo = new DistInfo();
				distInfo.distFromNodeOfData = 0;
				distInfo.isNodeDataPresent = true;
				return distInfo;
			}
			if(rootPtr.left != null && rootPtr.right != null) {
				DistInfo leftInfo = printAllNodesAtDistance(rootPtr.left, distance, nodeData);
				if(leftInfo.isNodeDataPresent) {
					//node present in left
					leftInfo.distFromNodeOfData++;
					if(leftInfo.distFromNodeOfData == distance) {
						System.out.println(rootPtr.data);
						return leftInfo;
					} else if(leftInfo.distFromNodeOfData > distance){
						return leftInfo;
					} else {
						printAllNodesAtDistanceDown(rootPtr.right,
								distance - leftInfo.distFromNodeOfData -1);
						return leftInfo;
					}
				} else {
					//node not present in left. so check in right
					DistInfo rightInfo = printAllNodesAtDistance(rootPtr.right, distance, nodeData);
					if(rightInfo.isNodeDataPresent) {
						rightInfo.distFromNodeOfData++;
						if(rightInfo.distFromNodeOfData == distance) {
							System.out.println(rootPtr.data);
							return rightInfo;
						} else if(rightInfo.distFromNodeOfData > distance){
							return rightInfo;
						} else {
							printAllNodesAtDistanceDown(rootPtr.left,
									distance - rightInfo.distFromNodeOfData -1);
							return rightInfo;
						}
					} else {
						//node not present in left and right
						return rightInfo;
					}
				}
			} else if(rootPtr.left != null){
				DistInfo leftInfo = printAllNodesAtDistance(rootPtr.left, distance, nodeData);
				if(leftInfo.isNodeDataPresent) {
					leftInfo.distFromNodeOfData++;
				}
				return leftInfo;
			} else if(rootPtr.right != null){
				DistInfo rightInfo = printAllNodesAtDistance(rootPtr.right, distance, nodeData);
				if(rightInfo.isNodeDataPresent) {
					rightInfo.distFromNodeOfData++;
				}
				return rightInfo;
			} else {
				//both left and right null
				DistInfo distInfo = new DistInfo();
				distInfo.distFromNodeOfData = 0;
				distInfo.isNodeDataPresent = false;
			}
		}
		/*
		if(distance == 0) {
			System.out.println(rootPtr.data);
		} else {
			//distance not 0
			if(rootPtr == nodeData) {
				return 1;
			} else {
				int leftDist = printAllNodesAtDistance(rootPtr.left, distance,nodeData);
				if(leftDist < 0) {
					//node not found in left subtree
					int rightDist = printAllNodesAtDistance(rootPtr.right, distance,nodeData);
					if(rightDist < 0) {
						return rightDist;
					} else {
						if(rightDist + 1 == distance) {
							System.out.println(rootPtr.data);
							return rightDist + 1;
						} else {
							return rightDist + 1;
						}
					}
				} else {
					//node found in left sub tree
					if(leftDist + 1 == distance) {
						System.out.println(rootPtr.data);
						return leftDist + 1;
					} else {
						return leftDist + 1;
					}
				}
			}
		}
		*/
		return null;
	}
	
	protected void printAllNodesAtDistanceDown(TreeNode<K> ptr, int distance) {
		if(ptr != null) {
			if(distance == 0) {
				System.out.println(ptr.data);
			} else if(distance > 0){
				printAllNodesAtDistanceDown(ptr.left,distance - 1);
				printAllNodesAtDistanceDown(ptr.right,distance - 1);
			}
		}
	}
}
