package tree.bst;

import utils.CollectionUtils;

public class TestAVLTreeInteger {
public static void main(String[] args) {
	AVLIntegerTree avlTree = new AVLIntegerTree();
	//image is avltree.png
	CollectionUtils.addToCollection(avlTree, 15, 20, 24, 10, 13, 7, 30, 36, 25,12,11);
	//avlTree.showImage();
	boolean pairExistsWithSum = avlTree.isPairExistsWithSum(32);
	System.out.println(pairExistsWithSum);
}
}
