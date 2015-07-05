package tree.bst;

import tree.TreeNode;



public class AVLTreeComparable<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	public AVLTreeComparable(){
		super();
	}
	
	public AVLTreeComparable(BinarySearchTreeType treeType){
		super(treeType);
	}
	
	@Override
	protected AVLTreeNode<K> makeNode(K element) {
		return new AVLTreeNode<K>(element, null, null);
	}
	
	@Override
	protected void setAttributesWhileAddingNodes(TreeNode<K> itr,
			TreeNode<K> itrParent, int compareTo) {
		AVLTreeNode<K> avlItr = (AVLTreeNode<K>) itr;
		AVLTreeNode<K> avlItrParent = (AVLTreeNode<K>) itrParent;
		
	}
}