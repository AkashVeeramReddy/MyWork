package tree.bst;

import tree.AVLTreeNode;
import tree.AVLTreeNode.GRANDPARENT_CHILD_RELN;
import tree.AVLTreeNode.PARENT_CHILD_RELN;
import tree.TreeNode;
import tree.TreeNodeWithParent;
import utils.MyUtilities;



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
		AVLTreeNode<K> grandParent = (AVLTreeNode<K>) avlItrParent.parent;
		super.setAttributesWhileAddingNodes(avlItr, avlItrParent, compareTo);
		
		avlItr.parent = avlItrParent;
		avlItr.height = 1;
		
		if(avlItrParent.height == 1) {
			avlItrParent.height = 2;
		}
		/*if(avlItrParent.left == avlItr) {
			if(avlItrParent.height == 0) {
				avlItrParent.height = 1;
			} else {
				avlItrParent.height = 0;
			}
		} else {
			//avlItrParent.right == avlItr
			if(avlItrParent.height == 0) {
				avlItrParent.height = -1;
			} else {
				avlItrParent.height = 0;
			}
		}*/
		if(grandParent != null) {
			if(grandParent.left == avlItrParent) {
				
			} else {
				//grandParent.right == avlItrParent
			}
		}
		AVLTreeNode<K> left = null;
		AVLTreeNode<K> right = null;
		while(grandParent != null) {
			AVLTreeNode<K> gr8GrandPar = (AVLTreeNode<K>) grandParent.parent;
			int balanceFactor = grandParent.getBalanceFactor();
			boolean unBalanced = grandParent.isUnBalanced();
			if(unBalanced) {
				GRANDPARENT_CHILD_RELN relnBtwGPChild = 
						AVLTreeNode.getRelationBtwnGrandParentAndChild(grandParent, avlItr);
				if(relnBtwGPChild == GRANDPARENT_CHILD_RELN.ZIG_ZAG) {
					AVLTreeNode<K> rotateParWrtChild = rotateParWrtChild(avlItrParent, avlItr);
					//swap avlItr and avlItrParent as rotation would have changed them
					AVLTreeNode<K> temp = avlItrParent;
					avlItrParent = avlItr;
					avlItr =temp;
				}
				AVLTreeNode<K> newRoot = rotateParWrtChild(grandParent, avlItrParent);
				//swap grandParent and avlItrParent as rotation would have changed them
				AVLTreeNode<K> temp = avlItrParent;
				avlItrParent = grandParent;
				grandParent =temp;
				if(gr8GrandPar == null) {
					root = newRoot;
				}/*
				else {
					if(gr8GrandPar.left == grandParent) {
						gr8GrandPar.left = newRoot;
					} else {
						gr8GrandPar.right = newRoot;
					}
					newRoot.parent = gr8GrandPar;
				}
				*/
			}
			grandParent.updateHeight();
			avlItr = avlItrParent;
			avlItrParent = grandParent;
			grandParent = gr8GrandPar;
		}
	}
	
	/**
	 * rotates and returns root
	 * @param parent
	 * @param child
	 * @return
	 */
	protected AVLTreeNode<K> rotateParWrtChild(AVLTreeNode<K> parent,
			AVLTreeNode<K> child) {
		PARENT_CHILD_RELN relationWrtParent = child.getRelationWrtParent();
		switch (relationWrtParent) {
		case LEFT:
			return rightRotate(parent, child);
		case RIGHT:
			return leftRotate(parent, child);
		default:
			break;
		}
		return null;
	}
	
	protected AVLTreeNode<K> leftRotate(AVLTreeNode<K> parent,AVLTreeNode<K> child) {
		//left subtree of child becomes right subtree of parent
		parent.right = child.left;
		if(child.left != null) {
			AVLTreeNode<K> left = (AVLTreeNode<K>) child.left;
			left.parent = parent;
		}
		//parent becomes left child of child. Updation of parent's parent done at last
		child.left = parent;
		//update heights
		parent.updateHeight();
		child.updateHeight();
		AVLTreeNode<K> grandParent = (AVLTreeNode<K>) parent.parent;
		if(grandParent != null) {
			if(grandParent.left == parent) {
				grandParent.left = child;
			} else {
				grandParent.right = child;
			}
		}
		parent.parent = child;
		child.parent = grandParent;
		return child;
	}
	
	protected AVLTreeNode<K> rightRotate(AVLTreeNode<K> parent,AVLTreeNode<K> child) {
		//right subtree of child becomes left subtree of parent
		parent.left = child.right;
		if(child.right != null) {
			AVLTreeNode<K> right = (AVLTreeNode<K>) child.right;
			right.parent = parent;
		}
		//parent becomes right child of child. Updation of parent's parent done at last
		child.right = parent;
		//update heights
		parent.updateHeight();
		child.updateHeight();
		AVLTreeNode<K> grandParent = (AVLTreeNode<K>) parent.parent;
		if(grandParent != null) {
			if(grandParent.left == parent) {
				grandParent.left = child;
			} else {
				grandParent.right = child;
			}
		}
		parent.parent = child;
		child.parent = grandParent;
		return child;
	}
	
}