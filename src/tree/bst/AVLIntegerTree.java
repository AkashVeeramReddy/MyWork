package tree.bst;

import java.util.LinkedList;

import tree.AVLTreeNode;
import tree.TreeNode;

public class AVLIntegerTree extends AVLTreeComparable<Integer> {
	/**
	 * 
	 * @param sum
	 * @return
	 */
	public boolean isPairExistsWithSum(int sum) {
		if(root == null)
			return false;
		LinkedList<TreeNode<Integer>> inorderStack = new LinkedList<TreeNode<Integer>>();
		LinkedList<TreeNode<Integer>> revInorderStack = new LinkedList<TreeNode<Integer>>();
		
		TreeNode<Integer> inorderItr = root;
		TreeNode<Integer> revInorderItr = root;
		
		inorderStack.push(inorderItr);
		while(inorderItr.left != null) {
			inorderStack.push(inorderItr.left);
			inorderItr = inorderItr.left;
		}
		inorderItr = inorderStack.pop();
		
		revInorderStack.push(revInorderItr);
		while(revInorderItr.right != null) {
			revInorderStack.push(revInorderItr.right);
			revInorderItr = revInorderItr.right;
		}
		
		revInorderItr = revInorderStack.pop();
		
		Integer leftData = inorderItr.data;
		Integer rightData = revInorderItr.data;
		
		Integer sumOfData = 0;
		while(leftData.compareTo(rightData) < 0) {
			sumOfData = leftData + rightData;
			if(sumOfData == sum) {
				System.out.println(leftData  + "+" + rightData);
				return true;
			} else if(sumOfData > sum) {
				//get element smaller than rightData
				revInorderItr = revInorderItr.left;
				if(revInorderItr != null) {
					revInorderStack.push(revInorderItr);
					while(revInorderItr.left != null) {
						revInorderStack.push(revInorderItr.left);
						revInorderItr = revInorderItr.left;
					}
				}
				revInorderItr = revInorderStack.pop();
			} else {
				//sumOfData < sum
				//get element larger than leftData
				inorderItr = inorderItr.right;
				if(inorderItr != null) {
					inorderStack.push(inorderItr);
					while(inorderItr.left != null) {
						inorderStack.push(inorderItr.left);
						inorderItr = inorderItr.left;
					}
				}
				inorderItr = inorderStack.pop();
			}
			leftData = inorderItr.data;
			rightData = revInorderItr.data;
		}
		return false;
	}
}
