package dynamicprogramming;

import utils.MyUtilities;

/**
 * 

Given a sequence, find the length of the longest palindromic subsequence in it.
 For example, if the given sequence is “BBABCBCAB”,
  then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it.
   “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones. 
 *
 */
public class LongestPalindromicSubSequence {
	
	private static void printLongestPalindromicSequence(String string) {
		int length = string.length();
		Info [][] infoArray = new Info[length][length];
		for(int i=0;i<length;i++) {
			for(int j=0;j<=i;j++) {
				if(i == j) {
					Info info = new Info();
					infoArray[i][j] = info;
					info.longestSequence = 1;
					info.direction = 2;
				}
			}
		}
		for(int subLengthItr = 2;subLengthItr <= length;subLengthItr ++ ) {
			for(int startItr = 0;startItr<(length-subLengthItr+1);startItr++) {
				int endItr = startItr + subLengthItr -1;
				String substring = string.substring(startItr, endItr+1);
				char charAtStart = string.charAt(startItr);
				char charAtEnd = string.charAt(endItr);
				int longestSequence = 0;
				int direction = -1;
				if(charAtStart == charAtEnd) {
					direction = 2;
					if(subLengthItr == 2) {
						longestSequence = 2;
					} else {
						longestSequence = infoArray[startItr + 1][endItr - 1].longestSequence + 2;
					}
				} else {
					int longestSequenceFormStart = infoArray[startItr][endItr-1].longestSequence;
					int longestSequenceFormEnd = infoArray[startItr+1][endItr].longestSequence;
					if(longestSequenceFormStart > longestSequenceFormEnd) {
						longestSequence = longestSequenceFormStart;
						direction = 0;
					} else {
						longestSequence = longestSequenceFormEnd;
						direction = 1;
					}
				}
				Info info = new Info();
				info.longestSequence = longestSequence;
				info.direction = direction;
				infoArray[startItr][endItr] = info;
			}
		}
		System.out.println("======================Info Array================================");
		MyUtilities.printDoubleDimensionalArray(infoArray, 15);
		
		System.out.println("The longest palindromic sequence  of " +string+" is ");
		printPalindromicSequence(infoArray,string,0,length -1);
		
	}
	
	private static void printPalindromicSequence(Info[][] infoArray,
			String string, int row,int column) {
		if(row > column) {
		} else {
			Info info = infoArray[row][column];
			int direction = info.direction;
			if(direction == 0) {
				printPalindromicSequence(infoArray, string, row, column-1);
			} else if(direction == 1) {
				printPalindromicSequence(infoArray, string, row+1, column);
			} else if(direction == 2) {
				System.out.print(string.charAt(row));
				printPalindromicSequence(infoArray, string, row+1, column-1);
				if(row != column)
					System.out.print(string.charAt(column));
			}
		}
	}

	private static class Info {
		
		private int longestSequence = 0;
		/**
		 * 0 means max came from String[0..n-1]
		 * 1 means max came from String[1..n]
		 * 2 means at each end characters are same
		 */
		private int direction = -1;
		@Override
		public String toString() {
			return "[Ls=" + longestSequence + ", D="
					+ direction + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
//		printLongestPalindromicSequence("B");
		printLongestPalindromicSequence("BBABCBCAB");
	}

}
