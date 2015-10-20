package dynamicprogramming;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Given a string, find the length of the longest substring without repeating
 * characters. For example, the longest substring without repeating letters for
 * "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
 * is "b", with the length of 1.
 * 
 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-
 * repeating-characters/ Given a string, find the length of the longest
 * substring without repeating characters. For example, the longest substrings
 * without repeating characters for “ABDEFGABEF” are “BDEFGA” and “DEFGAB”, with
 * length 6. For “BBBB” the longest substring is “B”, with length 1. For
 * “GEEKSFORGEEKS”, there are two longest substrings shown in the below
 * diagrams, with length 7.
 * 
 * The desired time complexity is O(n) where n is the length of the string.
 * 
 * @author user
 * 
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		int lengthOfLongestSubstring = lengthOfLongestSubstring("aa");
		System.out.println(lengthOfLongestSubstring);
	}
	public static int lengthOfLongestSubstring(String s) {
		if(s.length() <= 1)
			return s.length();
        int[]count = new int[256];
        char[] charArray = s.toCharArray();
        char ch;
        int maxWindow = 0,currentWindow,startWindow = 0,endWindow = -1;
        int idxForCh;
        for (int i = 0; i < charArray.length; i++) {
			ch = charArray[i];
			idxForCh = ch;
			if(count[idxForCh] == 0) {
				count[idxForCh] = 1;
				endWindow++;
			} else {
				//count[idxForCh] == 1. A character already existed
				currentWindow = endWindow - startWindow + 1;
				maxWindow = Math.max(maxWindow, currentWindow);
				//now pop from left
				while(charArray[startWindow] != ch && startWindow<=endWindow) {
					count[charArray[startWindow]] = 0;
					startWindow++;
				}
				startWindow++;
				endWindow++;
			}
		}
        currentWindow = endWindow - startWindow + 1;
        maxWindow = Math.max(maxWindow, currentWindow);
		return maxWindow;
    }
}
