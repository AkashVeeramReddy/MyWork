package strings;

import java.util.ArrayList;
import java.util.List;

import tree.string.TrieNode;

public class TrieNodeAnagrams extends TrieNode {
	public TrieNodeAnagrams(int noOfCharacters) {
		super(noOfCharacters);
	}
	public List<Integer> idxStrings = new ArrayList<Integer>();
	public TrieNodeAnagrams left;
	public TrieNodeAnagrams right;
}
