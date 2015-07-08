package tree.bst;

import utils.CollectionUtils;

public class TestIntegerBST {
	public static void main(String[] args) {
		IntegerBST bst = new IntegerBST("med_bst.dot");
		//Integer []array = new Integer[]{10, 5, 1, 0, 7, 6,8,15,20,25};
		//bst.populateFromPreOrder(array);
		//CollectionUtils.addToCollection(bst, 10,15,12,17,20,5,7,3,0,8);
		bst.showImage();
		
	}
}
