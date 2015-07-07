package tree.string;

public class Trie {
	
	
	protected TrieNode root;
	protected int noOfCharacters;
	
	public Trie() {
		this(26);
	}
	
	public Trie(int noOfCharacters) {
		this.noOfCharacters = noOfCharacters;
	}
	/**
	 * 
	 * @param str
	 * @return true if added, else false(if already exists)
	 */
	public boolean add(String str) {
		return add(str.toCharArray());
	}
	
	public boolean add(char[] charArray) {
		if(root == null) {
			//root's data must be unfilled
			root = new TrieNode(noOfCharacters);
		}
		return add(charArray,0,root);
	}
	
	protected boolean add(char[] charArray, int fromIdx,TrieNode rootPtr) {
		if(fromIdx < charArray.length) {
			char ch = charArray[fromIdx];
			TrieNode childByChar = rootPtr.getChildrenByChar(ch);
			if(childByChar == null) {
				//create the node with isLeaf false
				childByChar = new TrieNode(noOfCharacters);
				rootPtr.setChildrenByChar(childByChar, ch);
			}
			if(fromIdx == charArray.length - 1) {
				if(childByChar.isLeaf) {
					//string already exists
					return false;
				} else {
					childByChar.isLeaf = true;
					return true;
				}
			} else {
				return add(charArray, fromIdx+1, childByChar);
			}
			
		}
		return false;
	}
	
	public boolean search(String str) {
		return search(str.toCharArray());
	}
	
	public boolean search(char[] charArray) {
		return search(charArray,0,root);
	}
	
	protected boolean search(char[] charArray, int fromIdx,TrieNode rootPtr) {
		if(rootPtr == null)
			return false;
		if(fromIdx < charArray.length) {
			char ch = charArray[fromIdx];
			TrieNode childByChar = rootPtr.getChildrenByChar(ch);
			if(childByChar == null)
				return false;
			if(fromIdx == charArray.length - 1) {
				return childByChar.isLeaf;
			} else {
				return search(charArray, fromIdx+1, childByChar);
			}
			
		}
		return false;
	}
}
