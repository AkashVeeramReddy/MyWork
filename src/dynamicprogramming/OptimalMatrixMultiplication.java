package dynamicprogramming;

import utils.ArrayUtilities;

/**
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together. The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.

We have many options to multiply a chain of matrices because matrix multiplication is associative. In other words, no matter how we parenthesize the product, the result will be the same. For example, if we had four matrices A, B, C, and D, we would have:

    (ABC)D = (AB)(CD) = A(BCD) = ....
However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed to compute the product, or the efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,

    (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
    A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
Clearly the first method is the more efficient.

Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of dimension p[i-1] x p[i]. We need to write a function MatrixChainOrder() that should return the minimum number of multiplications needed to multiply the chain.

  Input: p[] = {40, 20, 30, 10, 30}   
  Output: 26000  
  There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
  Let the input 4 matrices be A, B, C and D.  The minimum number of 
  multiplications are obtained by putting parenthesis in following way
  (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30

  Input: p[] = {10, 20, 30, 40, 30} 
  Output: 30000 
  There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30. 
  Let the input 4 matrices be A, B, C and D.  The minimum number of 
  multiplications are obtained by putting parenthesis in following way
  ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30

  Input: p[] = {10, 20, 30}  
  Output: 6000  
  There are only two matrices of dimensions 10x20 and 20x30. So there 
  is only one way to multiply the matrices, cost of which is 10*20*30
 * @author KH348
 *
 */
public class OptimalMatrixMultiplication {
	
	public static void matrixMultiplication(int rowMatrix1,int colMatrix1OrRowMatrix2,int colMatrix2) {
		Integer array1[][] = new Integer[rowMatrix1][colMatrix1OrRowMatrix2];
		Integer array2[][] = new Integer[colMatrix1OrRowMatrix2][colMatrix2];
		ArrayUtilities.populateIntegerArrayWithRandomNos(array1);
		ArrayUtilities.populateIntegerArrayWithRandomNos(array2);
		System.out.println("========================Array 1==========================");
		ArrayUtilities.printDoubleDimensionalArray(array1, 5);
		System.out.println("========================Array 2==========================");
		ArrayUtilities.printDoubleDimensionalArray(array2, 5);
		
		Integer [][] multipliedArray = new Integer[rowMatrix1][colMatrix2];
		for(int i=0;i<rowMatrix1;i++) {
			for(int j=0;j<colMatrix2;j++) {
				int sum = 0;
				for(int k=0;k<colMatrix1OrRowMatrix2;k++) {
					sum = sum + array1[i][k]*array2[k][j];
				}
				multipliedArray[i][j] = sum;
			}
		}
		System.out.println("========================Multiplied Array==========================");
		ArrayUtilities.printDoubleDimensionalArray(multipliedArray, 5);
	}
	
	public static void printOptimalMatrixMultiplication(int noOfMatrices) {
		System.out.println("No of matrices "+noOfMatrices);
		Integer [] rowColumnInfo = new Integer[noOfMatrices+1];
		ArrayUtilities.populateIntegerArrayWithRandomNos(rowColumnInfo);
		System.out.println("========================Row Column Matrix==========================");
		ArrayUtilities.printSingleDimensionArray(rowColumnInfo);
		System.out.println("========================Row Column Info=============================");
		for(int i = 0;i<noOfMatrices;i++) {
			System.out.println("Matrix "+i+" rows:" + rowColumnInfo[i] + " columns:"+rowColumnInfo[i+1]);
		}
		Info[][] infoArray = new Info[noOfMatrices][noOfMatrices];
		for(int i=0;i<=(noOfMatrices-2);i++) {
			Info info = new Info();
			int otherIndex = i + 1;
			infoArray[i][otherIndex] = info;
			info.cost = rowColumnInfo[i]*rowColumnInfo[otherIndex]*rowColumnInfo[otherIndex+1];
		}
		for(int length = 3;length<=noOfMatrices;length++) {
			for(int i=0;i<=(noOfMatrices-length);i++) {
				int endIndex = i + length -1;
				int optimalSplitIndex = -1;
				int optimalCost = 999999;
				
				int costIterator = 0;
				for(int j=i;j<endIndex;j++) {
					int leftSubStartIndex = i;
					int leftSubEndIndex = j;
					int rightSubStartIndex = j+1;
					int rightSubEndIndex = endIndex;
					int constantMultiplicationCost = rowColumnInfo[leftSubStartIndex]
					                *rowColumnInfo[rightSubStartIndex]*rowColumnInfo[rightSubEndIndex+1];
					if(leftSubStartIndex == leftSubEndIndex) {
						costIterator = infoArray[rightSubStartIndex][rightSubEndIndex].cost
						+ constantMultiplicationCost;
					} else if(rightSubStartIndex==rightSubEndIndex) {
						costIterator = infoArray[leftSubStartIndex][leftSubEndIndex].cost
						+ constantMultiplicationCost;
					} else {
						costIterator = infoArray[leftSubStartIndex][leftSubEndIndex].cost
							+ infoArray[rightSubStartIndex][rightSubEndIndex].cost
							+ constantMultiplicationCost;
					}
					if(costIterator < optimalCost) {
						optimalSplitIndex = j;
						optimalCost = costIterator;
					}
				}
				Info info = new Info();
				info.splitIndex = optimalSplitIndex;
				info.cost = optimalCost;
				infoArray[i][endIndex] = info;
			}
		}
		System.out.println("========================Cost Info==========================");
		ArrayUtilities.printDoubleDimensionalArrayNeatly(infoArray, 15);
		System.out.println();
		printOptimalMatrix(infoArray,0,noOfMatrices-1);
	}
	
	private static void printOptimalMatrix(Info[][] infoArray, int startIdx,
			int endIdx) {
		if(startIdx == endIdx) {
			System.out.print(startIdx);
		} else {
			System.out.print("(");
			int splitIndex = infoArray[startIdx][endIdx].splitIndex;
			if(splitIndex == -1) {
				System.out.print(startIdx);
				System.out.print(endIdx);
			} else {
				printOptimalMatrix(infoArray, startIdx, splitIndex);
				printOptimalMatrix(infoArray, splitIndex+1, endIdx);
			}
			System.out.print(")");
		}
	}

	public static void main(String[] args) {
//		matrixMultiplication(3, 4, 3);
		printOptimalMatrixMultiplication(6);
	}
	
	private static class Info {
		
		/**
		 * -1 means nothing to split eg:nothing to split if length is 2
		 * 
		 * [a,b,c] is split at b means [ab][c] is the resulting split
		 */
		private int splitIndex = -1;
		private int cost;
		@Override
		public String toString() {
			return "[sI=" + splitIndex + ",c=" + cost + "]";
		}
		
		
		
	}
}
