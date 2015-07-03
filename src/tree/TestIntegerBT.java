package tree;

public class TestIntegerBT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntegerBinaryTree tree = new IntegerBinaryTree();
		Integer []preOrder = new Integer[]{10, 30, 20, 5, 15};
		Boolean []leafInfo = new Boolean[]{false,false,true,true,true};
		tree.populateWithSpecialPreorder(preOrder, leafInfo);
		tree.showImage();
		//System.out.println(tree.getLargestSizeOfBST());
		//System.out.println(tree);
		//tree.mirrorize();
		//System.out.println(tree.isSumTree());
		//tree.showImage();
	}

}
