package permutation;
/**
 * http://www.geeksforgeeks.org/print-all-combinations-of-given-length/
 * Given a set of characters and a positive integer k, print all possible strings
 *  of length k that can be formed from the given set.
 * 
 * http://www.geeksforgeeks.org/print-all-permutations-with-repetition-of-characters/
 * Given a string of length n, print all permutation of the given string. Repetition
 * of characters is allowed. Print these permutations in lexicographically sorted order 
 * @author user
 *
 */
public class PrintPermuatation {
	public static void main(String[] args) {
		char []array = new char[]{'a','b','c'};
		printPermutation(array, 3);
	}
	public static void printPermutation(char []array, int len) {
		char []print = new char[len];
		printPermutation(array, print, 0, len);
	}
	
	public static void printPermutation(char []array, char[] print, int idx, int len) {
		if(len == 0) {
			System.out.println(new String(print));
		} else {
			for (int i = 0; i < array.length; i++) {
				print[idx] = array[i];
				printPermutation(array, print, idx+1, len-1);
			}
		}
	}
}
