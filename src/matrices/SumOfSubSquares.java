package matrices;

import utils.ArrayUtilities;

/**
 * http://www.geeksforgeeks.org/given-n-x-n-square-matrix-find-sum-sub-squares-size-k-x-k/
 * @author user
 *
 *Given an n x n square matrix, find sum of all sub-squares of size k x k where k is smaller than or equal to n.

Examples

Input:
n = 5, k = 3
arr[][] = { {1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4},
            {5, 5, 5, 5, 5},
         };
Output:
       18  18  18
       27  27  27
       36  36  36
 */
public class SumOfSubSquares {
	public static void main(String[] args) {
		Integer arr[][] = { {1, 1, 1, 1, 1},
	            {2, 2, 2, 2, 2},
	            {3, 3, 3, 3, 3},
	            {4, 4, 4, 4, 4},
	            {5, 5, 5, 5, 5},
	         };
		Integer[][] sumOfSubSquares = getSumOfSubSquares(arr, 3);
		ArrayUtilities.printDoubleDimensionalArray(sumOfSubSquares,5);
	}
	
	public static Integer[][] getSumOfSubSquares(Integer[][] matrix, int k) {
		int dim = matrix.length;
		int dimSquareSum = dim - k + 1;
		Integer [][] sumOfSubSquares = new Integer[dimSquareSum][dimSquareSum];
		if(dimSquareSum >= 0) {
			sumOfSubSquares = new Integer[dimSquareSum][dimSquareSum];
			Integer[] sumInColumnForK = new Integer[dim];
			for(int i = 0;i<dim;i++) {
				for(int j=0;j<k;j++) {
					sumInColumnForK[i] = (sumInColumnForK[i]==null?0:sumInColumnForK[i])
						+ matrix[j][i]; 
				}
			}
			ArrayUtilities.printSingleDimensionArray(sumInColumnForK);
			int sum = 0;
			for(int i=0;i<=(dim-k);i++) {
				sum = 0;
				for(int j=0;j<dim;j++) {
					if(i==0) {
						//no change	
					} else {
						sumInColumnForK[j] = sumInColumnForK[j] + matrix[i+k-1][j]
								- matrix[i-1][j];
					}
					sum = sum + sumInColumnForK[j];
					if(j>=k) {
						sum = sum - sumInColumnForK[j-k];
					}
					if(j>=(k-1)) {
						sumOfSubSquares[i][j-k+1] = sum;
					}
				}
			}
		}
		return sumOfSubSquares;
	}
}
