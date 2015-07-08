package tree.bst;

import tree.TreeNode;
import tree.TreeNodeWithParent;

public class BinarySearchTreeWithParent<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	public TreeNodeWithParent<K> makeNode(K element) {
		TreeNodeWithParent<K> treeNodeWithParent = new TreeNodeWithParent<K>(element, null, null);
		return treeNodeWithParent;
	}
	
	@Override
	protected void setAttributesWhileAddingNodes(TreeNode<K> itr,
			TreeNode<K> itrParent, int compareTo) {
		super.setAttributesWhileAddingNodes(itr, itrParent, compareTo);
		if(itr instanceof TreeNodeWithParent<?>) {
			TreeNodeWithParent<K> var = (TreeNodeWithParent<K>) itr;
			var.setParent((TreeNodeWithParent<K>) itrParent);
		}
	}
	
	public K getInorderSuccessor(K ele) {
		K successor = null;
		return successor;
	}
}
