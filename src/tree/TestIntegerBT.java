package tree;

public class TestIntegerBT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntegerBinaryTree tree = new IntegerBinaryTree("tree2.dot");
		System.out.println(tree);
		tree.mirrorize();
		tree.showImage();
	}

}
