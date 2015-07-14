package tree.bst;

import tree.AVLTreeNode;
import tree.TreeNode;



public class AVLTreeComparable<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	public AVLTreeComparable(){
		super();
	}
	
	public AVLTreeComparable(BinarySearchTreeType treeType){
		super(treeType);
	}
	
	@Override
	public AVLTreeNode<K> makeNode(K element) {
		return new AVLTreeNode<K>(element, null, null);
	}
	
	@SuppressWarnings("unused")
	@Override
	protected void setAttributesWhileAddingNodes(TreeNode<K> itr,
			TreeNode<K> itrParent, int compareTo) {
		AVLTreeNode<K> avlItr = (AVLTreeNode<K>) itr;
		AVLTreeNode<K> avlItrParent = (AVLTreeNode<K>) itrParent;
		super.setAttributesWhileAddingNodes(avlItr, avlItrParent, compareTo);
		
		avlItr.parent = avlItrParent;
		
		while(avlItrParent != null) {
			
		}
	}
}