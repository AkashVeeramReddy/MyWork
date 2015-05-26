package dynamicprogramming;

import utils.MyUtilities;

/**
 * Given an array arr[0 ... n-1] containing n positive integers, a subsequence of arr[] is called Bitonic
 *  if it is first increasing, then decreasing. Write a function that takes an array as argument and returns
 *   the length of the longest bitonic subsequence.
A sequence, sorted in increasing order is considered Bitonic with the decreasing part as empty.
 Similarly, decreasing order sequence is considered Bitonic with the increasing part as empty.

Examples:

Input arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
Output: 6 (A Longest Bitonic Subsequence of length 6 is 1, 2, 10, 4, 2, 1)

Input arr[] = {12, 11, 40, 5, 3, 1}
Output: 5 (A Longest Bitonic Subsequence of length 5 is 12, 11, 5, 3, 1)

Input arr[] = {80, 60, 30, 40, 20, 10}
Output: 5 (A Longest Bitonic Subsequence of length 5 is 80, 60, 30, 20, 10)
 *	
 * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
 */
public class BitonicSequence {
	
	public static void printBiotonicSequence(int length) {
		Integer[] intArray = new Integer[length];
		for(int i = 0;i<length;i++) {
			intArray[i] = (int) Math.ceil(Math.random() * 100);
		}
		System.out.println("======================Integer Array================================");
		MyUtilities.printSingleDimensionArray(intArray);
	}
	
	private static class Info {
		private int previousLength;
		private boolean included;
	}
	
	public static void main(String[] args) {
		printBiotonicSequence(8);
	}
}
