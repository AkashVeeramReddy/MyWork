package tree.bst;

import tree.TreeNode;

public class IntegerBST extends BinarySearchTree<Integer> {

	/**
	 * http://www.geeksforgeeks.org/check-for-children-sum-property-in-a-binary-tree/
	 * Check for Children Sum Property in a Binary Tree.
		Given a binary tree, write a function that returns true if the tree satisfies below property.
		
		For every node, data value must be equal to sum of data values in left and right children. 
		Consider data value as 0 for NULL children
	 * @return
	 */
	public boolean isChildSumSatisfied() {
		return isChildSumSatisfied(root);
	}

	protected boolean isChildSumSatisfied(TreeNode<Integer> ptr) {
		if(ptr == null) {
			return true;
		}
		if(ptr.left == null && ptr.right == null) {
			return true;
		} else  {
			Integer leftData = (ptr.left == null ? 0 : ptr.left.data);
			Integer rightData = (ptr.right == null ? 0 : ptr.right.data);
			if((leftData + rightData) == ptr.data) {
				return isChildSumSatisfied(ptr.left) && isChildSumSatisfied(ptr.right);
			}
			return false;
		}
	}
	
	/**
	 * http://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/
	 * 
	 * Given a binary tree and a number, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals the given number.
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
	 * Given a Binary Tree, find the maximum sum path from a leaf to root. For example, in the following tree, 
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
