package permutation;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/ Print all possible combinations of r elements in a
 * given array of size n Given an array of size n, generate and print all
 * possible combinations of r elements in array. For example, if input array is
 * {1, 2, 3, 4} and r is 2, then output should be {1, 2}, {1, 3}, {1, 4}, {2,
 * 3}, {2, 4} and {3, 4}.
 * 
 * @author user
 * 
 */
public class PrintCombination {
	public static void main(String[] args) {
		int [] array = new int[]{1,2,3,4};
		printCombination(array, 2);
	}
	public static void printCombination(int[] data, int r) {
		int [] print = new int[r];
		printCombination(data, print, 0, 0, r);
	}
	public static void printCombination(int []data, int [] print,int dataIdx,int printIdx, int r) {
		int n = data.length;
		if(n <(r + dataIdx)) {
			return;
		}
		if(r == 0) {
			System.out.println(Arrays.toString(print));
		} else {
			print[printIdx] = data[dataIdx];
			//containing  data[dataIdx]
			printCombination(data,print, dataIdx + 1,printIdx+1, r-1);
			//not containing  data[dataIdx]
			printCombination(data, print, dataIdx + 1,printIdx, r);
		}
	}
}
