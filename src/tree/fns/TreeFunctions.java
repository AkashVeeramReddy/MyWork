package tree.fns;

public class TreeFunctions {
	/**
	 * http://www.geeksforgeeks.org/check-if-each-internal-node-of-a-bst-has-exactly-one-child/
	 * Given Preorder traversal of a BST, check if each non-leaf node has only one child. Assume that the BST contains unique entries.

		Examples
		
		Input: pre[] = {20, 10, 11, 13, 12}
		Output: Yes
		The give array represents following BST. In the following BST, every internal
		node has exactly 1 child. Therefor, the output is true.
		        20
		       /
		      10
		       \
		        11
		          \
		           13
		           /
		         12
	 * @param preorder
	 * @return
	 */
	public static boolean isInternalNodeOneChild(Integer[] preorder) {
		return isInternalNodeOneChild(preorder, 0) != null;
	}
	
	/**
	 * 
	 * @param preorder
	 * @param idx
	 * @return 0th index - min of the subtree with root preorder[idx];
	 * 1th index - max of the subtree with root preorder[idx];
	 */
	public static Integer[] isInternalNodeOneChild(Integer[] preorder,int idx) {
		if(idx < preorder.length -1) {
			Integer[] internalNodeOneChild = isInternalNodeOneChild(preorder,idx+1);
			if(internalNodeOneChild == null)
				return null;
			Integer cur = preorder[idx];
			Integer next = preorder[idx+1];
			int min = internalNodeOneChild[0];
			int max = internalNodeOneChild[1];
			if(cur > next) {
				if(cur < max) {
					return null;
				}
				//update new max
				internalNodeOneChild[1] = cur;
			} else {
				//cur < next
				if(cur > min) {
					return null;
				}
				//update new min
				internalNodeOneChild[0] = cur;
			}
			//info our node
			return internalNodeOneChild;
		} else if(idx == preorder.length -1){
			return new Integer[]{preorder[idx],preorder[idx]};
		}
		return null;
	}
	
	public static void main(String[] args) {
		boolean internalNodeOneChild = isInternalNodeOneChild(new Integer[]{20, 22, 11, 13, 12});
		System.out.println(internalNodeOneChild);
	}
}
