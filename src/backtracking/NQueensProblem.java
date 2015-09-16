package backtracking;

import utils.MyUtilities;

public class NQueensProblem {
	public static final int [][] MOVES = {
		{+1,-1},
		{+1,+1},
		{-1,+1},
		{-1,-1},
		{0,+1},
		{0,-1},
		{+1,0},
		{-1,0}
	};
	
	public static void main(String[] args) {
		solveNQueens(8);
		System.out.println("Over");
	}

	public static void solveNQueens(int dim) {
		//1 means queen present, null means no queen present
		Integer[][] pos = new Integer[dim][dim];
		//null means no queen's influence present, else queens influence present
		int[][] influence = new int[dim][dim];
		solveNQueens(pos,influence, dim, dim);
		MyUtilities.printDoubleDimensionalArray(pos, 5);
		/*
		int [] rowInfo = new int[dim];
		int [] colInfo = new int[dim];
		*/
	}

	private static boolean solveNQueens(Integer[][] pos,int[][] influence, int noOfQueensLeft, int dim) {
		if(noOfQueensLeft == 0) {
			return true;
		}
		for (int rowItr = 0; rowItr < dim; rowItr++) {
			for (int colItr = 0; colItr < dim; colItr++) {
				if(pos[rowItr][colItr] == null) {
					//no queen present. So put queen if safe
					if(isSafe(influence, rowItr, colItr, dim)) {
						pos[rowItr][colItr] = 1;
						//update influence
						updateInfluence(influence, rowItr, colItr, dim, 1);
						boolean solveNQueens = solveNQueens(pos, influence, noOfQueensLeft-1, dim);
						if(solveNQueens) {
							return true;
						} else {
							pos[rowItr][colItr] = null;
							//restore influence
							updateInfluence(influence, rowItr, colItr, dim, -1);
						}
					}
				}
			}
		}
		return false;
	}
	public static void updateInfluence(int[][] influence, int rowIdx, int colIdx, int dim, Integer updateValue) {
		influence[rowIdx][colIdx] = influence[rowIdx][colIdx] + updateValue;
		int rowIdxItr = rowIdx, colIdxItr = colIdx;
		for (int i = 0; i < MOVES.length; i++) {
			int []move = MOVES[i];
			rowIdxItr = rowIdx + move[0];
			colIdxItr = colIdx + move[1];
			while(isValidIdx(rowIdxItr, colIdxItr, dim)) {
				influence[rowIdxItr][colIdxItr] = influence[rowIdxItr][colIdxItr] + updateValue;
				rowIdxItr = rowIdxItr + move[0];
				colIdxItr = colIdxItr + move[1];
			}
			
		}
		System.out.println();
	}
	
	public static boolean isSafe(int[][] influence, int rowIdx, int colIdx, int dim) {
		return influence[rowIdx][colIdx] == 0;
	}
	
	public static boolean isValidIdx(int rowIdx, int colIdx, int dim) {
		boolean isValidIdx =  (rowIdx < dim && rowIdx >= 0 && colIdx < dim && colIdx >=0);
		return isValidIdx;
	}
}
