package backtracking;

import utils.ArrayUtilities;

/**
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * 
 * A permutation, also called an “arrangement number” or “order,” is a rearrangement of the
 *  elements of an ordered list S into a one-to-one correspondence with S itself. 
 *  A string of length n has n! permutation.

Below are the permutations of string ABC.
ABC, ACB, BAC, BCA, CAB, CBA
 * @author user
 *
 */
public class PrintAllPermutations {
	public static void main(String[] args) {
		printAllPermuatations("abc");
	}
	
	public static void printAllPermuatations(String str) {
		char[] print = new char[str.length()];
		printAllPermuatations(str.toCharArray(),print,0);
	}

	private static void printAllPermuatations(char[] charArray,char[] print, int i) {
		int length = charArray.length;
		if(i== length-1) {
			print[i] = charArray[i];
			System.out.println(new String(print));
		} else {
			for (int j = i; j < charArray.length; j++) {
				print[i] = charArray[j];
				ArrayUtilities.swapCharArray(charArray, i, j);
				printAllPermuatations(charArray, print, i+1);
				ArrayUtilities.swapCharArray(charArray, i, j);
			}
		}
	}
}
