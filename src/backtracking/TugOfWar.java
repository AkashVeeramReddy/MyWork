package backtracking;

import utils.MyUtilities;

/**
 * http://www.geeksforgeeks.org/tug-of-war/
 * Given a set of n integers, divide
 * the set in two subsets of n/2 sizes each such that the difference of the sum
 * of two subsets is as minimum as possible. If n is even, then sizes of two
 * subsets must be strictly n/2 and if n is odd, then size of one subset must be
 * (n-1)/2 and size of other subset must be (n+1)/2.
 * 
 * For example, let given set be {3, 4, 5, -3, 100, 1, 89, 54, 23, 20}, the size
 * of set is 10. Output for this set should be {4, 100, 1, 23, 20} and {3, 5,
 * -3, 89, 54}. Both output subsets are of size 5 and sum of elements in both
 * subsets is same (148 and 148). Let us consider another example where n is
 * odd. Let given set be {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4}. The
 * output subsets should be {45, -34, 12, 98, -1} and {23, 0, -99, 4, 189, 4}.
 * The sums of elements in two subsets are 120 and 121 respectively.
 * 
 * @author user
 * 
 */
public class TugOfWar {
	public static void main(String[] args) {
		int []array = new int[]{23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
		getTugOfWar(array);
	}
	
	public static void getTugOfWar(int[] array) {
		int size = array.length;
		int []soln = new int[size];
		int minDif = getNewTugOfWar(array, soln, 0, 0,0 ,0,Integer.MAX_VALUE);
		System.out.println(minDif);
	}
	private static int getNewTugOfWar(int[] array, int[] soln, int arrayIdx, int sumSoFar,
			int sum1, int size1, int minDiff) {
		int maxSizeOfArray1 = array.length/2;
		int maxSizeOfArray2 = array.length - array.length/2;
		int ele = array[arrayIdx];
		int size2 = arrayIdx - size1;
		sumSoFar = sumSoFar + ele;
		int minDiff1 = Integer.MAX_VALUE;
		int minDiff2 = Integer.MAX_VALUE;
		if(size1 < maxSizeOfArray1) {
			//add to array1
			if(arrayIdx == array.length - 1) {
				sum1 = sum1 + ele;
				minDiff1 = Math.abs(sumSoFar - 2*sum1);
			} else {
				minDiff1 = getNewTugOfWar(array, soln, arrayIdx+1, sumSoFar, sum1+ele, size1+1, minDiff);
			}
		}
		if(size2 < maxSizeOfArray2) {
			//add to array2
			if(arrayIdx == array.length - 1) {
				minDiff1 = Math.abs(sumSoFar - 2*sum1);
			} else {
				minDiff2 = getNewTugOfWar(array, soln, arrayIdx+1, sumSoFar, sum1, size1, minDiff);
			}
		}
		if(Math.min(minDiff1, minDiff2) < minDiff) {
			if(minDiff1 < minDiff2) {
				soln[arrayIdx] = 1;
			} else {
				soln[arrayIdx] = 2;
			}
			return Math.min(minDiff1, minDiff2);
		}
		return minDiff;
	}
}
