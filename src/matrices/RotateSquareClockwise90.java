package matrices;

import utils.MyUtilities;

/**
 * You are given an n x n 2D matrix representing an image.
https://leetcode.com/problems/rotate-image/
Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
 * @author user
 *
 */
public class RotateSquareClockwise90 {
	public static void main(String[] args) {
		Integer[][]matrix =  { 
				{1,  2,  3,  4},
			    {5,  6,  7,  8},
			    {9,  10, 11, 12},
				{13, 14, 15, 16}  };
		MyUtilities.printDoubleDimensionalArray(matrix, 4);
		rotateSquareMatrixClockwise90Recursive(matrix);
		System.out.println();
		MyUtilities.printDoubleDimensionalArray(matrix, 4);
	}

	private static void rotateSquareMatrixClockwise90Recursive(
			Integer[][] matrix) {
		rotateSquareMatrixClockwise90Recursive(matrix,0,matrix.length-1,0,matrix.length-1);
	}

	private static void rotateSquareMatrixClockwise90Recursive(
			Integer[][] matrix, int startRow, int endRow, int startCol, int endCol) {
		int length = endRow - startRow + 1;
		if(length <=1)
			return;
		for(int j=0;j<length-1;j++) {
			int eleAtTopRing = matrix[startRow][startCol+j];
			int eleAtRightRing = matrix[startRow+j][endCol];
			int eleAtBottomRing = matrix[endRow][endCol-j];
			int eleAtLeftRing = matrix[endRow-j][startCol];
			
			matrix[startRow+j][endCol] = eleAtTopRing;
			matrix[endRow][endCol-j] = eleAtRightRing;
			matrix[endRow-j][startCol] = eleAtBottomRing;
			matrix[startRow][startCol+j] = eleAtLeftRing;
		}
		rotateSquareMatrixClockwise90Recursive(matrix,startRow+1,endRow-1,startCol+1,endCol-1);
	}
}
