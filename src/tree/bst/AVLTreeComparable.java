package tree.bst;


public class AVLTreeComparable<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	public AVLTreeComparable(){
		super();
	}
	
	public AVLTreeComparable(BinarySearchTreeType treeType){
		super(treeType);
	}
	
	@Override
	public boolean add(K element) {
		return false;
	}
}