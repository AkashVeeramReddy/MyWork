package tree;

public class TreeNodeWithParent<K> extends TreeNode<K> {
	
	public TreeNodeWithParent<K> parent;
	
	public TreeNodeWithParent(K data, TreeNode<K> left, TreeNode<K> right) {
		super(data, left, right);
	}

	public TreeNodeWithParent<K> getParent() {
		return parent;
	}

	public void setParent(TreeNodeWithParent<K> parent) {
		this.parent = parent;
	}
	
}
