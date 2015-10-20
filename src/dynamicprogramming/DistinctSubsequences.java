package dynamicprogramming;

/**
 * https://leetcode.com/problems/distinct-subsequences/
 * 
 * Given a string S and a string T, count the number of distinct subsequences of
 * T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit"
 * 
 * Return 3
 * 
 * @author user
 * 
 */
public class DistinctSubsequences {
	public static int numDistinct(String s, String t) {
        String large = s;
        String small = t;
        int largeLen = large.length();
        int smallLen = small.length();
        if(largeLen < smallLen) {
        	return 0;
        } else if(largeLen == smallLen) {
        	return large.equals(small)?1:0;
        }
        int[][] info = new int[largeLen+1][smallLen+1];
        info[0][0] = 1;
        for (int i = 1; i < largeLen; i++) {
			info[i][0] = 1;
		}
        for (int j = 1; j < smallLen; j++) {
			info[0][j] = 0;
		}
        char[] charLarge = large.toCharArray();
        char[] charSmall = small.toCharArray();
        for (int i = 0; i < largeLen; i++) {
			for (int j = 0; j < smallLen; j++) {
				info[i+1][j+1] = info[i][j+1];
				if(charLarge[i] == charSmall[j]) {
					info[i+1][j+1] = info[i+1][j+1] + info[i][j];
				}
			}
			
		}
        return info[largeLen][smallLen];
    }
	
	public static void main(String[] args) {
		int numDistinct = numDistinct("rabbbit", "rabbit");
		System.out.println(numDistinct);
	}
}
