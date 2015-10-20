package arrays;

import utils.ArrayUtilities;
import utils.MyUtilities;

/**
 * Flood fill Algorithm – how to implement fill() in paint?
 * http://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/
 * 
 * In MS-Paint, when we take the brush to a pixel and click, the color of the
 * region of that pixel is replaced with a new selected color. Following is the
 * problem statement to do this task. Given a 2D screen, location of a pixel in
 * the screen and a color, replace color of the given pixel and all adjacent
 * same colored pixels with the given color.
 * 
 * @author user
 * 
 */
public class FloodFill {
	
	public static int[][] NEIGHBOURS = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void floodFill(Integer [][]arr, int row, int col, int newColor) {
		int noOfRows = arr.length;
		int noOfCols = arr[0].length;
		if(isValidIdx(row, col, noOfRows, noOfCols)) {
			floodFill(arr, arr[row][col], newColor, row, col, noOfRows, noOfCols);
		}
	}
	
	public static void floodFill (Integer [][]arr, int oldColor, int newColor, int row,
			int col, int noOfRows, int noOfCols) {
		if(isValidIdx(row, col, noOfRows, noOfCols)) {
			if(arr[row][col] == oldColor) {
				arr[row][col] = newColor;
				for (int[] neighbour : NEIGHBOURS) {
					floodFill(arr, oldColor, newColor, row+neighbour[0],
							col+neighbour[1], noOfRows, noOfCols);
				}
			} else {
				return;
			}
		}
	}
	public static boolean isValidIdx(int row, int col, int noOfRows, int noOfCols) {
		if(row >= 0 && row < noOfRows) {
			if(col >= 0 && col < noOfCols) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Integer arr[][] =  {{1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
               };
		floodFill(arr, 4, 4, 3);
		ArrayUtilities.printDoubleDimensionalArray(arr, 3);
	}
}
