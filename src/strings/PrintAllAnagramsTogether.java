package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together-set-2/
 * 
 * Given a sequence of words, print all anagrams together | Set 2
Given an array of words, print all anagrams together. For example, if the given array is {“cat”, “dog”, “tac”, “god”, “act”},
 then output may be “cat tac act dog god”.
 * @author user
 *
 */
public class PrintAllAnagramsTogether {
	
	public static void main(String[] args) {
		String[] stringArray = new String[]{"cat", "dog", "tac", "god", "act"};
		List<String> strings = new ArrayList<String>();
		for (String string : stringArray) {
			strings.add(string);
		}
		TrieAnagrams anagrams = new TrieAnagrams(strings);
		anagrams.printAnagrams();
	}
}
