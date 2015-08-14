package tree;

public class AVLTreeNode<K> extends TreeNodeWithParent<K> {
	

	/**
	 * difference of height of left subtree and right subtree.
	 * In AVL tree, it is either -1,0,+1
	 * balanceFactor = height(left subtree) - height(right subtree)
	 */
	public int height = 0;
	
	public AVLTreeNode(K data, TreeNode<K> left, TreeNode<K> right) {
		super(data, left, right);
	}
	
	public int getBalanceFactor() {
		AVLTreeNode<K> left = (AVLTreeNode<K>) this.left;
		AVLTreeNode<K> right = (AVLTreeNode<K>) this.right;
		int leftHeight = ((left == null) ? 0 : left.height);
		int rightHeight = ((right == null) ? 0 : right.height);
		return leftHeight - rightHeight;
	}
	
	public void updateHeight() {
		AVLTreeNode<K> left = (AVLTreeNode<K>) this.left;
		AVLTreeNode<K> right = (AVLTreeNode<K>) this.right;
		int leftHeight = ((left == null) ? 0 : left.height);
		int rightHeight = ((right == null) ? 0 : right.height);
		height = Math.max(leftHeight, rightHeight) + 1;
	}
	
	public boolean isUnBalanced() {
		int balanceFactor = getBalanceFactor();
		return (balanceFactor == 2) || (balanceFactor == -2);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public static <K> GRANDPARENT_CHILD_RELN getRelationBtwnGrandParentAndChild
		(AVLTreeNode<K> grandParent, AVLTreeNode<K> child) {
		AVLTreeNode<K> parent = (AVLTreeNode<K>) child.parent;
		PARENT_CHILD_RELN relationWrtParent = child.getRelationWrtParent();
		PARENT_CHILD_RELN relationWrtParent2 = parent.getRelationWrtParent();
		return ((relationWrtParent == relationWrtParent2) ? GRANDPARENT_CHILD_RELN.STRAIGHT
				:GRANDPARENT_CHILD_RELN.ZIG_ZAG);
	}
	
	public PARENT_CHILD_RELN getRelationWrtParent() {
		TreeNodeWithParent<K> parent2 = getParent();
		if(parent2 != null) {
			if(parent2.left == this) {
				return PARENT_CHILD_RELN.LEFT;
			}
			return PARENT_CHILD_RELN.RIGHT;
		}
		return null;
	}
	public static enum GRANDPARENT_CHILD_RELN {
		STRAIGHT,
		ZIG_ZAG;
	}
	
	public static enum PARENT_CHILD_RELN {
		LEFT,
		RIGHT;
	}
}
