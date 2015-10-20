package dynamicprogramming;

import utils.ArrayUtilities;

/**
 * Length of the longest continous substring without repeating characters

Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substrings without repeating characters for “ABDEFGABEF” are “BDEFGA” and “DEFGAB”, 
with length 6.
 For “BBBB” the longest substring is “B”, with length 1. 
 For “GEEKSFORGEEKS”, there are two longest substrings shown in the below diagrams, with length 7.
 * @author KH348
 *
 */
public class LongestContinousSubStringWithoutRepeatingChar {
	
	private static void printLongestContinousSubStringWithoutRepeatingChar(
			String string) {
		int length = string.length();
		Info [][]infoArray = new Info[length][length];
		
		for(int i = 0;i<length;i++) {
			Info info = new Info();
			info.startIndex = i;
			info.endIndex = i;
			infoArray[i][i] = info;
		}
		
		for(int lengthItr = 2;lengthItr<=length;lengthItr++) {
			for(int startIndexItr =0;startIndexItr<=(length - lengthItr);startIndexItr++) {
				int optimalStartIndex = 0;
				int optimalEndIndex = 0;
				
				int endIndexItr = startIndexItr + lengthItr - 1;
				Info info = new Info();
				infoArray[startIndexItr][endIndexItr] = info;
				
				
				Info infoForLeftPart = infoArray[startIndexItr][endIndexItr-1];
				
				int optimalStartIndexForLeftPart = infoForLeftPart.startIndex;
				int optimalEndIndexForLeftPart = infoForLeftPart.endIndex;
				char startChar = string.charAt(startIndexItr);
				char endChar = string.charAt(endIndexItr);
				if(infoForLeftPart.endIndex == (endIndexItr -1)) {
					boolean matchFound = false;
					for(int i = optimalStartIndexForLeftPart;i<=optimalEndIndexForLeftPart;i++) {
						if(string.charAt(i) == endChar) {
							matchFound = true;
						}
					}
					if(!matchFound) {
						optimalEndIndexForLeftPart = endIndexItr;
					}
				}
				Info infoForRightPart = infoArray[startIndexItr+1][endIndexItr];
				
				int optimalStartIndexForRightPart = infoForRightPart.startIndex;
				int optimalEndIndexForRightPart = infoForRightPart.endIndex;
				
				if(infoForRightPart.startIndex == (startIndexItr+1)) {
					boolean matchFound = false;
					for(int i = optimalStartIndexForRightPart;i<=optimalEndIndexForRightPart;i++) {
						if(string.charAt(i) == startChar) {
							matchFound = true;
						}
					}
					if(!matchFound) {
						optimalStartIndexForRightPart = startIndexItr;
					}
				}
				if((optimalEndIndexForLeftPart - optimalStartIndexForLeftPart) > 
					(optimalEndIndexForRightPart - optimalStartIndexForRightPart)) {
					optimalStartIndex = optimalStartIndexForLeftPart;
					optimalEndIndex = optimalEndIndexForLeftPart;
				} else {
					optimalStartIndex = optimalStartIndexForRightPart;
					optimalEndIndex = optimalEndIndexForRightPart;
				}
				info.startIndex = optimalStartIndex;
				info.endIndex = optimalEndIndex;
			}
		}
		System.out.println("==============================Info Array=====================================");
		ArrayUtilities.printDoubleDimensionalArrayNeatly(infoArray, 20);
		Info info = infoArray[0][length-1];
		System.out.println("The longest continous non repeating substring is "+string.substring(info.startIndex, info.endIndex+1));
	}
	
	private static class Info {
		
		private int startIndex = 0;
		private int endIndex = 0;
		
		@Override
		public String toString() {
			return "[sI=" + startIndex + ",eI=" + endIndex
					+ "]";
		}

		private static int getLength(Info info) {
			return info.endIndex - info.startIndex + 1;
		}
		
	}
	
	public static void main(String[] args) {
		printLongestContinousSubStringWithoutRepeatingChar("GEEKSFORGEEKS");
	}

	
	
}
