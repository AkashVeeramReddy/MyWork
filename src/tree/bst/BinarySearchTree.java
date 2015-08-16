package tree.bst;
import static tree.bst.BinarySearchTreeType.INORDER_ASCENDING;
import tree.BinaryTree;
import tree.TreeNode;
/**
 * Basic fns of binary search tree
 * @author user
 *
 * @param <K>
 */
public class BinarySearchTree<K extends Comparable<? super K>> extends BinaryTree<K>{
	
	protected BinarySearchTreeType treeType;
	
	public BinarySearchTree(){
		this(INORDER_ASCENDING);
	}
	
	public BinarySearchTree(BinarySearchTreeType treeType){
		this.treeType = treeType;
	}
	
	public boolean add(K element) {
		if(root == null) {
			root = makeNode(element);
		} else {
			TreeNode<K> itr = root;
			TreeNode<K> itrParent = null;
			int compareTo = 0;
			while(itr != null) {
//				compareTo = itr.data.compareTo(element);
				compareTo = element.compareTo(itr.data);
				if(compareTo == 0)
					return false;
				else {
					itrParent = itr;
					if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
						itr = itr.left;
					} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
						itr = itr.right;
					}
				}
			}
			itr = makeNode(element);
			setAttributesWhileAddingNodes(itr, itrParent, compareTo);
		}
		return true;
	}

	protected void setAttributesWhileAddingNodes(TreeNode<K> itr,
			TreeNode<K> itrParent, int compareTo) {
		if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
			itrParent.left = itr;
		} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
			itrParent.right = itr;
		}
	}
	
	/*
	protected TreeNode<K> makeNode(K element) {
		return new TreeNode<K>(element, null, null);
	}
	*/
	
	public boolean isAscendingSearchTree() {
		switch (treeType) {
		case INORDER_ASCENDING:
			return true;
		case INORDER_DESCENDING:
			return false;
		default:
			return false;
		}
	}
	
	public boolean iterativeContains(K element){
		TreeNode<K> itr= root;
		while (itr != null) {
			int compareTo = element.compareTo(itr.data);
			if(compareTo == 0) {
				return true;
			} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
				itr=itr.right;
			} else if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
				itr = itr.left;
			}
		}
		return false;
	}
	
	protected boolean contains(TreeNode<K> node,K element){
		if (node != null) {
			int compareTo = element.compareTo(node.data);
			if(compareTo == 0) {
				return true;
			} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
				return contains(node.right,element);
			} else if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
				return contains(node.right,element);
			}
		}
		return false;
	}
	
	/*
	public boolean contains(K element){
		return contains(root, element);
	}
	*/
	
	

	public boolean remove(K element) {
		TreeNode<K> itr = root;
		TreeNode<K> itrParent = null;
		int compareTo = 0;
		while(itr != null) {
//			compareTo = itr.data.compareTo(element);
			compareTo = element.compareTo(itr.data);
			if(compareTo == 0) {
				TreeNode<K> nextElement = findReplacementElement(itr);
				if(nextElement != null)
					itr.data = nextElement.data;
				else {
					int compareTo2 = itr.data.compareTo(itrParent.data);
					if(compareTo2 == treeType.getComparisonValueToGoToLeftPart()) {
						itrParent.right = null;
					} else if(compareTo2 == treeType.getComparisonValueToGoToRightPart()) {
						itrParent.left = null;
					}
				}
				return true;
			} else {
				itrParent = itr;
				if(compareTo == treeType.getComparisonValueToGoToLeftPart()) {
					itr = itr.left;
				} else if(compareTo == treeType.getComparisonValueToGoToRightPart()) {
					itr = itr.right;
				}
			}
		}
		return false;
	}
	
	protected TreeNode<K> findReplacementElement(TreeNode<K> ele) {
		if(ele.right != null) {
			return findNextElement(ele);
		} else if(ele.left != null) {
			return findPreviousElement(ele);
		}
		return null;
	}
	
	protected TreeNode<K> findNextElement(TreeNode<K> ele) {
		TreeNode<K> itr = ele.right;
		TreeNode<K> parent = ele;
		while(itr.left != null) {
			parent = itr;
			itr = itr.left;
		}
		parent.left = null;
		return itr;
	}
	
	protected TreeNode<K> findPreviousElement(TreeNode<K> ele) {
		TreeNode<K> itr = ele.left;
		TreeNode<K> parent = ele;
		while(itr.right != null) {
			parent = itr;
			itr = itr.right;
		}
		parent.right = null;
		return itr;
	}
	
	
}
