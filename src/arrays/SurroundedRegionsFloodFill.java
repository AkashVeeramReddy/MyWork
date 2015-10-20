package arrays;

import utils.ArrayUtilities;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * 
 * http://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/
 * @author user
 *
 */
public class SurroundedRegionsFloodFill {
	public static int[][] NEIGHBOURS = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void main(String[] args) {
		char[][] board = {{'X' ,'X' ,'X' ,'X'},
				{'X' ,'O' ,'O' ,'X'},
				{'X' ,'X' ,'O' ,'X'},
				{'X' ,'O' ,'X' ,'X'}};
		solve(board);
		ArrayUtilities.printDoubleDimensionalArray(board, 2);
	}
	
	public static void solve(char[][] board) {
		int noOfRows = board.length;
		if(noOfRows == 0)
			return;
		int noOfCols = board[0].length;
		if(noOfCols == 0)
			return;
		for (int i = 0; i <noOfRows; i++) {
			if(board[i][0] == 'O') {
				floodFill(board, 'O', 'F', i, 0, noOfRows, noOfCols);
			}
		}
		for (int i = 0; i <noOfRows; i++) {
			if(board[i][noOfCols-1] == 'O') {
				floodFill(board, 'O', 'F', i, noOfCols-1, noOfRows, noOfCols);
			}
		}
		for (int i = 0; i <noOfCols; i++) {
			if(board[0][i] == 'O') {
				floodFill(board, 'O', 'F', 0, i, noOfRows, noOfCols);
			}
		}
		for (int i = 0; i <noOfCols; i++) {
			if(board[noOfRows-1][i] == 'O') {
				floodFill(board, 'O', 'F', noOfRows-1, i, noOfRows, noOfCols);
			}
		}
		for (int i = 0; i < noOfRows; i++) {
			for (int j = 0; j < noOfCols; j++) {
				if(board[i][j] == 'O') {
					board[i][j] = 'X';
				} else if(board[i][j] == 'F') {
					board[i][j] = 'O';
				}
			}
		}
	}
	
	public static void floodFill (char[][] board, char oldColor, char newColor, int row,
			int col, int noOfRows, int noOfCols) {
		if(isValidIdx(row, col, noOfRows, noOfCols)) {
			if(board[row][col] == oldColor) {
				board[row][col] = newColor;
				for (int[] neighbour : NEIGHBOURS) {
					floodFill(board, oldColor, newColor, row+neighbour[0],
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
}
