package tree.string;


public class TestTrie {

	public static void main(String[] args) {
		Trie trie = new Trie();
		//boolean add = trie.add("a");
		trie.add("all");
		trie.add("als");
		trie.add("as");
		System.out.println();
		
		boolean search = trie.search("a");
		search = trie.search("b");
		search = trie.search("als");
		search = trie.search("al");
		search = trie.search("asl");
		
		System.out.println(search);
		System.out.println(trie);
	}

}
