package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tree.string.Trie;
import tree.string.TrieNode;

public class TrieAnagrams extends Trie {
	public List<String> strings = new ArrayList<String>();
	public TrieNodeAnagrams head;
	public TrieAnagrams(List<String> strings) {
		this.strings = strings;
	}
	
	/**
	 * 
	 * @param str
	 * @return true if added, else false(if already exists)
	 */
	public boolean add(String str, int idx) {
		return add(str.toCharArray(),idx);
	}
	
	public void printAnagrams() {
		for (int i = 0; i < strings.size(); i++) {
			char[] charArray = strings.get(i).toCharArray();
			Arrays.sort(charArray);
			add(charArray, i);
		}
		TrieNodeAnagrams itr = head;
		while(itr != null) {
			List<Integer> idxStrings = itr.idxStrings;
			for (Integer intIdx : idxStrings) {
				System.out.print(strings.get(intIdx));
				System.out.print(" ");
			}
			System.out.println();
			itr = itr.right;
		}
	}
	
	public boolean add(char[] charArray, int idx) {
		if(root == null) {
			//root's data must be unfilled
			root = new TrieNodeAnagrams(noOfCharacters);
		}
		return add(charArray,0,root,idx);
	}
	
	protected boolean add(char[] charArray, int fromIdx,TrieNode rootPtr,int idxString) {
		if(fromIdx < charArray.length) {
			char ch = charArray[fromIdx];
			TrieNodeAnagrams childByChar = (TrieNodeAnagrams) rootPtr.getChildrenByChar(ch);
			if(childByChar == null) {
				//create the node with isLeaf false
				childByChar = new TrieNodeAnagrams(noOfCharacters);
				childByChar.data = ch;
				rootPtr.setChildrenByChar(childByChar, ch);
			}
			if(fromIdx == charArray.length - 1) {
				if(childByChar.endOfStr) {
					//string with same anagrams already exists
					childByChar.idxStrings.add(idxString);
					return true;
				} else {
					childByChar.endOfStr = true;
					childByChar.idxStrings.add(idxString);
					if(head == null) {
						head = childByChar;
					} else {
						childByChar.right = head;
						head.left = childByChar;
						head = childByChar;
					}
					return true;
				}
			} else {
				return add(charArray, fromIdx+1, childByChar,idxString);
			}
			
		}
		return false;
	}
}
