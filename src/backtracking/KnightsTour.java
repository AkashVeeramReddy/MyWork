package backtracking;

import utils.ArrayUtilities;

/**
 * http://www.geeksforgeeks.org/backtracking-set-1-the-knights-tour-problem/
 * 
 * 
 * @author user
 *
 */
public class KnightsTour {
	public static final int[][] MOVES = new int [][]{{1,2},{2,1},
												{1,-2},{2,-1},
												{-1,-2},{-2,-1},
												{-1,2},{-2,1}};
	public static void main(String[] args) {
		boolean knightsTour = knightsTour(0, 0, 5);
		System.out.println(knightsTour);
	}
	public static boolean knightsTour(int rowIdx, int colIdx, int dim) {
		Integer[][] array = new Integer[dim][dim];
		array[rowIdx][colIdx] = 1;
		boolean success = knightsTourAt(array, rowIdx, colIdx,dim);
		//if(success) {
			ArrayUtilities.printDoubleDimensionalArrayNeatly(array, 6);
		//}
		return success;
	}
	
	public static boolean knightsTourAt(Integer [][] array, int rowIdx, int colIdx, int dim) {
		int[] move;
		int newRowIdx, newColIdx;
		Integer prevMove = array[rowIdx][colIdx];
		if(prevMove == dim*dim) {
			return true;
		}
		int curMovNo = prevMove + 1;
		for(int i = 0; i< MOVES.length; i++) {
			move = MOVES[i];
			newRowIdx = rowIdx + move[0];
			newColIdx = colIdx + move[1];
			if(isSafe(newRowIdx, newColIdx,array, dim,curMovNo)) {
				array[newRowIdx][newColIdx] = curMovNo;
				boolean knightsTourAt = knightsTourAt(array, newRowIdx, newColIdx, dim);
				if(knightsTourAt) {
					return true;
				} else {
					//backtrack
					array[newRowIdx][newColIdx] = null;
					System.out.println("=================================");
					System.out.println(curMovNo);
					ArrayUtilities.printDoubleDimensionalArrayNeatly(array, 6);
					System.out.println("=================================");
				}
			}
			
		}
		return false;
	}
	
	public static boolean isSafe(int rowIdx, int colIdx, Integer [][]array, int dim, int curMoveNo) {
		boolean isValidIdx =  (rowIdx < dim && rowIdx >= 0 && colIdx < dim && colIdx >=0);
		if(isValidIdx) {
			Integer moveAt = array[rowIdx][colIdx];
			if(moveAt == null) {
				return true;
			}
		}
		return false;
	}
}
