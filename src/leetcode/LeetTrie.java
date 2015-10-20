package leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 * http://www.geeksforgeeks.org/trie-insert-and-search/
 * @author nithin
 *
 */

class LeetTrieNode {
	
	public char data = 0;
	public boolean endOfStr = false;
	
	public int noOfChildren;
	public LeetTrieNode [] children;
	
	public LeetTrieNode() {
		this(26);
	}
	
	public LeetTrieNode(int noOfChildren) {
		this.noOfChildren = noOfChildren;
		children = new LeetTrieNode[noOfChildren];
	}
	
	public LeetTrieNode getChildrenAt(int idx) {
		return children[idx];
	}
	
	public void setChildrenAt(LeetTrieNode child,int idx) {
		children[idx] = child;
	}
	
	public LeetTrieNode getChildrenByChar(char childChar) {
		return getChildrenAt(getIndexOfCharacter(childChar));
	}
	
	public void setChildrenByChar(LeetTrieNode child,char childChar) {
		setChildrenAt(child,getIndexOfCharacter(childChar));
	}
	
	/**
	 * subclasses need to override. default is handing small letters
	 * @param childChar
	 * @return
	 */
	public int getIndexOfCharacter(char childChar) {
		return childChar - 'a';
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(data);
		builder.append(',');
		builder.append("endOfStr:");
		builder.append(endOfStr);
		builder.append(',');
			//append child
		builder.append('{');
		for (int i = 0; i < children.length; i++) {
			LeetTrieNode child = children[i];
			if(child != null) {
				builder.append(child.data);
				builder.append(":");
				builder.append(i);
				builder.append(',');
			}
		}
		builder.append('}');
		
		return builder.toString();
	}
}
public class LeetTrie {
	
	
	private LeetTrieNode root;
	protected int noOfCharacters;
	
	public LeetTrie() {
		this(26);
	}
	
	public LeetTrie(int noOfCharacters) {
		this.noOfCharacters = noOfCharacters;
	}
	/**
	 * 
	 * @param str
	 * @return true if added, else false(if already exists)
	 */
	public void insert(String str) {
		add(str.toCharArray());
	}
	
	public boolean add(char[] charArray) {
		if(root == null) {
			//root's data must be unfilled
			root = new LeetTrieNode(noOfCharacters);
		}
		return add(charArray,0,root);
	}
	
	protected boolean add(char[] charArray, int fromIdx,LeetTrieNode rootPtr) {
		if(fromIdx < charArray.length) {
			char ch = charArray[fromIdx];
			LeetTrieNode childByChar = rootPtr.getChildrenByChar(ch);
			if(childByChar == null) {
				//create the node with isLeaf false
				childByChar = new LeetTrieNode(noOfCharacters);
				childByChar.data = ch;
				rootPtr.setChildrenByChar(childByChar, ch);
			}
			if(fromIdx == charArray.length - 1) {
				if(childByChar.endOfStr) {
					//string already exists
					return false;
				} else {
					childByChar.endOfStr = true;
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
	
	protected boolean search(char[] charArray, int fromIdx,LeetTrieNode rootPtr) {
		if(rootPtr == null)
			return false;
		if(fromIdx < charArray.length) {
			char ch = charArray[fromIdx];
			LeetTrieNode childByChar = rootPtr.getChildrenByChar(ch);
			if(childByChar == null)
				return false;
			if(fromIdx == charArray.length - 1) {
				return childByChar.endOfStr;
			} else {
				return search(charArray, fromIdx+1, childByChar);
			}
			
		}
		return false;
	}
	
	public String toString() {
		return getAllStringsInAlphabeticalOrder().toString();
	}
	
	public List<String> getAllStringsInAlphabeticalOrder() {
		return getAllStringsInAlphabeticalOrder(root, "");
	}
	
	public List<String> getAllStringsInAlphabeticalOrder(LeetTrieNode rootPtr,String strSeen) {
		List<String> strings = new ArrayList<String>();
		if(rootPtr != null) {
			String newStrSeen = strSeen + rootPtr.data;
			if(rootPtr.endOfStr) {
				strings.add(newStrSeen);
			}
			LeetTrieNode child = null;
			for (int i = 0; i < rootPtr.children.length; i++) {
				child = rootPtr.children[i];
				if(child != null) {
					strings.addAll(getAllStringsInAlphabeticalOrder(child,newStrSeen));
				}
			}
		}
		return strings;
	}
	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 * @param str
	 * @return
	 */
	public boolean startsWith(String str) {
		return startsWith(str.toCharArray());
	}

	public boolean startsWith(char[] charArray) {
		return startsWith(root,charArray,0);
	}

	public boolean startsWith(LeetTrieNode ptr, char[] charArray, int idx) {
		if(ptr != null && idx < charArray.length) {
			LeetTrieNode childrenByChar = ptr.getChildrenByChar(charArray[idx]);
			if(childrenByChar != null) {
				if(idx == charArray.length - 1) {
					return true;
				} else {
					return startsWith(childrenByChar, charArray, idx+1);
				}
			} else {
				return false;
			}
		}
		return false;
	}
}
