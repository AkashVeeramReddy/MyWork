package tree;


public class TestExtendedBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExtendedBinarySearchTree<Integer> tree = new ExtendedBinarySearchTree<Integer>();
		tree.add(50);
		tree.add(25);
		tree.add(75);
		tree.add(10);
		tree.add(30);
		tree.add(0);
		tree.add(100);
		//CollectionUtils.addNumbers(ebst);
		System.out.println(tree);
		
		System.out.println(tree.getLeastCommonAncestor(0, 100));
		System.out.println(tree.getLeastCommonAncestor(0, 30));
	}

}
