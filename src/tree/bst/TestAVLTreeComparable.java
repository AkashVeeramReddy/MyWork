package tree.bst;

import utils.CollectionUtils;

public class TestAVLTreeComparable {
	public static void main(String[] args) {
		AVLTreeComparable<Integer> avlTree = new AVLTreeComparable<Integer>();
		CollectionUtils.addToCollection(avlTree, 15, 20, 24, 10, 13, 7, 30, 36, 25);
		/*avlTree.add(1);
		//avlTree.showImage();
		avlTree.add(3);
		avlTree.showImage();
		avlTree.add(2);*/
		avlTree.showImage();
	}
	
}
