package tree.string;


public class TestTernarySearchTree {

	public static void main(String[] args) {
		TernarySearchTree tst = new TernarySearchTree();
		//boolean add = trie.add("a");
		tst.add("a");
		tst.add("all");
		System.out.println(tst.root.toString());
		tst.add("als");
		tst.add("as");
		System.out.println();
		
		boolean search = tst.search("a");
		search = tst.search("b");
		search = tst.search("als");
		search = tst.search("al");
		search = tst.search("as");
		search = tst.search("asl");
		search = tst.search("all");
		
		System.out.println(tst);
	}

}
