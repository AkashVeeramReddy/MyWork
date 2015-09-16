package permutation;

import java.util.Arrays;

import sun.security.util.Length;
import utils.MyUtilities;

/**
 * http://www.geeksforgeeks.org/lexicographic-permutations-of-string/
 * 
 * Print all permutations in sorted (lexicographic) order
Given a string, print all permutations of it in sorted order. For example, if the input string is “ABC”,
 then output should be “ABC, ACB, BAC, BCA, CAB, CBA”.
 * @author user
 *
 */
public class PrintLexicographicPermutation {
	public static void main(String[] args) {
		printLexicographicPermutation("abc");
	}
	public static void printLexicographicPermutation(String str) {
		printLexicographicPermutation(str.toCharArray());
	}

	public static void printLexicographicPermutation(char[] charArray) {
		int length = charArray.length;
		char[] printArray = new char[length];
		for (int i = 0; i < printArray.length; i++) {
			printArray[i] = charArray[i];
		}
		printLexicographicPermutation(charArray,printArray,0);
		
	}

	private static void printLexicographicPermutation(char[] charArray,
			char[] printArray, int idx) {
		if(idx == charArray.length) {
			System.out.println(Arrays.toString(printArray));
		} else {
			for (int i = idx; i < printArray.length; i++) {
				MyUtilities.swapCharArray(printArray, idx, i);
				printLexicographicPermutation(charArray,printArray,idx+1);
				//we can reverse the array from [idx+1,length)
				Arrays.sort(printArray, idx+1, printArray.length);
			}
		}
	}
}
