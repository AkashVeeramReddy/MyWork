package tree.bst;
import tree.TreeNode;

public class AVLTreeNode<K> extends TreeNode<K> {
	
	public int height = 0;
	
	public AVLTreeNode(K data, TreeNode<K> left, TreeNode<K> right) {
		super(data, left, right);
	}
	
}