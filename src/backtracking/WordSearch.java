package backtracking;

/**
 * https://leetcode.com/problems/word-search/
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where
 * "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * For example, Given board =
 * 
 * [ ["ABCE"],
 *   ["SFCS"],
 *   ["ADEE"] ]
 *   word = "ABCCED", -> returns true,
 *    word =
 * "SEE", -> returns true, 
 * word = "ABCB", -> returns false.
 * 
 * @author user
 * 
 */
public class WordSearch {
	
	public static int[][] NEIGHBOURS = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static boolean exist(char[][] board, String word) {
        char[]wordArray = word.toCharArray();
        int rows = board.length;
		int cols = board[0].length;
		if(rows == 0 || cols == 0) {
			return (word.length() == 0);
		}
		//char[]fillArray = new char[wordLength];
		boolean visited[][] = new boolean[rows][cols];
		boolean exists;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				exists = exists(board, wordArray, visited, 0, i, j, rows, cols);
				if(exists) {
					return true;
				}
			}
		}
        return false;
    }
	
	public static boolean exists(char[][] board, char[]wordArray, boolean visited[][],
			int idxSeen, int rowItr, int colItr, int noOfRows, int noOfCols) {
		//if(isValidIdx(rowItr, colItr, noOfRows, noOfCols)) {
		if(board[rowItr][colItr] == wordArray[idxSeen]) {
			if(idxSeen == (wordArray.length-1)) {
				return true;
			}
			visited[rowItr][colItr] = true;
			int neighRowItr, neighColItr;
			boolean neighExists;
			for (int[] neighbour : NEIGHBOURS) {
				neighRowItr = rowItr + neighbour[0];
				neighColItr = colItr + neighbour[1];
				if(isValidIdx(neighRowItr, neighColItr, noOfRows, noOfCols)) {
					if(!visited[neighRowItr][neighColItr]) {
						neighExists = exists(board, wordArray, visited, idxSeen+1,
								neighRowItr, neighColItr, noOfRows, noOfCols);
						if(neighExists)
							return true;
					}
				}
			}
			visited[rowItr][colItr] = false;
			return false;
		} else {
			return false;
		}
		//}
		//return false;
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
		char[][] board ={
			{'A','B','C','E'},
			{'S','F','C','S'},
			{'A','D','E','E'},
			};
		String word = "ABCCED";
		boolean exist = exist(board, word);
		System.out.println(exist);
	}
}
