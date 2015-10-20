package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together-set-1/
 * 
 * Given a sequence of words, print all anagrams together | Set 2 Given an array
 * of words, print all anagrams together. For example, if the given array is
 * {“cat”, “dog”, “tac”, “god”, “act”}, then output may be “cat tac act dog
 * god”.
 * 
 * @author user
 * 
 */

public class GroupAnagramsWithoutTrie {
	public static void printAnagramsTogether(List<String> strs) {
		Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
		for (String string : strs) {
			char[] charArray = string.toCharArray();
			Arrays.sort(charArray);
			String sorted = new String(charArray);
			List<String> list = map.get(sorted);
			if(list == null) {
				list = new ArrayList<String>();
				map.put(sorted, list);
			}
			list.add(string);
		}
		//System.out.println(map.values());
		List<List<String>> all = new ArrayList<List<String>>();
		Set<Entry<String,List<String>>> entrySet = map.entrySet();
		for (Entry<String, List<String>> entry : entrySet) {
			List<String> value = entry.getValue();
			Collections.sort(value);
			all.add(value);
		}
	}
	
	public static void main(String[] args) {
		String[] stringArray = new String[]{"cat", "dog", "tac", "god", "act"};
		List<String> strings = new ArrayList<String>();
		for (String string : stringArray) {
			strings.add(string);
		}
		printAnagramsTogether(strings);
	}
}
