package backtracking;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/fill-two-instances-numbers-1-n-specific-way/
 * 
 * Given a number n, create an array of size 2n such that the array contains 2
 * instances of every number from 1 to n, and the number of elements between two
 * instances of a number i is equal to i. If such a configuration is not
 * possible, then print the same.
 * 
 * Examples:
 * 
 * Input: n = 3 Output: res[] = {3, 1, 2, 1, 3, 2}
 * 
 * Input: n = 2 Output: Not Possible
 * 
 * Input: n = 4 Output: res[] = {4, 1, 3, 1, 2, 4, 3, 2}
 * 
 * @author user
 * 
 */
public class TwoInstancesOneToN {
	public static void main(String[] args) {
		fillTwoInstances(4);
	}

	public static void fillTwoInstances(int n) {
		Integer []array = new Integer[2*n];
		Integer []firstOccur = new Integer[n];
		boolean fillTwoInstances = fillTwoInstances(array,firstOccur,n,0,n);
		if(fillTwoInstances) {
			System.out.println(Arrays.toString(array));
		}
	}

	private static boolean fillTwoInstances(Integer[] array, Integer[] firstOccur,
			int n,int arrayIdx, int noOfCharactersToFill) {
		if(noOfCharactersToFill == 0) {
			return true;
		}
		if(arrayIdx >= array.length) {
			return false;
		}
		if(array[arrayIdx] == null) {
			for (int i = 1; i <= n; i++) {
				if(firstOccur[i-1] == null) {
					//i has not been placed
					int nextPlace = arrayIdx + i+1;
					if(nextPlace < array.length && array[nextPlace]==null) {
						//safe to put
						firstOccur[i-1] = arrayIdx;
						array[arrayIdx] = i;
						array[nextPlace] = i;
						boolean fillTwoInstances = fillTwoInstances(array, firstOccur, n, arrayIdx+1,noOfCharactersToFill-1);
						if(fillTwoInstances) {
							return true;
						} else {
							//backtrack
							firstOccur[i-1] = null;
							array[arrayIdx] = null;
							array[nextPlace] = null;
						}
					}
					//firstOccur[i-1] = arrayIdx;
					
				}
			}
		} else {
			return fillTwoInstances(array, firstOccur, n, arrayIdx+1,noOfCharactersToFill);
		}
		return false;
	}
}
