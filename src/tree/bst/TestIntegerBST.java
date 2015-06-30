package tree.bst;

import utils.CollectionUtils;

public class TestIntegerBST {
	public static void main(String[] args) {
		IntegerBST bst = new IntegerBST();
		CollectionUtils.addToCollection(bst, 10,15,12,17,20,5,7,3,0,8);
		bst.showImage();
		
		System.out.println(bst.getMaxSumAndPrintPath());
	}
}
