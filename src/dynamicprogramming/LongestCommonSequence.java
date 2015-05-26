package dynamicprogramming;

/**
 * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in
 *  both of them. A subsequence is a sequence that appears in the same relative order, but not
 *   necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are 
 *   subsequences of “abcdefg”. So a string of length n has 2^n different possible subsequences.

It is a classic computer science problem, the basis of diff (a file comparison program 
that outputs the differences between two files), and has applications in bioinformatics.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 *
 *	http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 */
public class LongestCommonSequence {
	

	public static void getLongestCommonSequence(String str0,String str1) {
		Info [][] memoizedInfo = new Info[str0.length()][str1.length()];
		for(int i = 0;i<str0.length();i++) {
			for(int j = 0;j<str1.length();j++) {
				char charAt = str0.charAt(i);
				char charAt1 = str1.charAt(j);
				Info info = new Info();
				memoizedInfo[i][j] = info;
				if(charAt == charAt1) {
					info.whichString = 2;
					if(i==0 || j == 0) {
						info.length = 1;
					} else {
						Info prevInfo = memoizedInfo[i-1][j-1];
						info.length = prevInfo.length + 1;
					}
				} else {
					//Compute[i][j-1]
					int val0 = 0;
					if(j==0) {
						val0 = 0;
					} else {
						val0 = memoizedInfo[i][j-1].length;
					}
					//Compute[i-1][j]
					int val1 = 0;
					if(i==0) {
						val1 = 0;
					} else {
						val1 = memoizedInfo[i-1][j].length;
					}
					if(val0 > val1) {
						info.whichString = 1;
					} else {
						info.whichString = 0;
					}
					info.length = Math.max(val0, val1);
				}
			}
		}
		//Calculate lcs from memoized info
		printLCS(memoizedInfo, str0.length()-1, str1.length()-1,str0);
	}
	
	
	private static void printLCS(Info [][] info,int idx1,int idx2,String str0) {
		if(idx1 == -1 || idx2 == -1)
			return;
		Info info2 = info[idx1][idx2];
		int whichString = info2.whichString;
		if(whichString == 2) {
			printLCS(info, idx1-1, idx2-1,str0);
			System.out.print(str0.charAt(idx1));
		} else if(whichString == 1) {
			printLCS(info, idx1, idx2-1, str0);
		} else if(whichString == 0) {
			printLCS(info, idx1-1, idx2, str0);
		}
	}
	
	
	private static class Info {
		private int length;
		/**
		 * 0 means str0
		 * 1 means str1
		 * 2 means both strings
		 */
		private int whichString = -1;
		
		@Override
		public String toString() {
			return "len:"+length+"str:"+whichString;
		}
	}
	
	public static void main(String[] args) {
		getLongestCommonSequence("nithin", "nikhil");
//		System.out.println(longestCommonSequence);
	}
	
}
