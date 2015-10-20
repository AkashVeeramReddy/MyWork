package dynamicprogramming;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/
 * 
 * Given a string s and a dictionary of words dict, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author user
 * 
 */
public class WordBreak {
	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("leet");
		dict.add("code");
		boolean wordBreak = wordBreak("leetcode", dict);
		System.out.println(wordBreak);
		
	}
	public static boolean wordBreak(String s, Set<String> wordDict) {
        char[] charArray = s.toCharArray();
        int len = charArray.length;
        boolean []isTiled = new boolean[len+1];
        isTiled[0] = false;
        boolean itr = false;
        for (int i = 0; i < len; i++) {
        	itr = false;
        	String str = charArray[i] + "";
			for (int j = i-1; j >=0; j--) {
				itr = isTiled[j+1] && wordDict.contains(str);
				if(itr == true) {
					isTiled[i+1] = true;
					break;
				}
				str = charArray[j] + str;
				//isTiled[i+1] = wordDict.contains(str);
			}
			if(isTiled[i+1] == false) {
				isTiled[i+1] = wordDict.contains(str);
			}
		}
		return isTiled[len];
    }
}
