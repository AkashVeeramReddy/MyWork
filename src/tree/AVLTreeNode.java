package tree;

public class AVLTreeNode<K> extends TreeNodeWithParent<K> {
	
	public int height = 0;
	
	public AVLTreeNode(K data, TreeNode<K> left, TreeNode<K> right) {
		super(data, left, right);
	}

}
