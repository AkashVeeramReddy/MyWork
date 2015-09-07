package backtracking;

import utils.MyUtilities;

/**
 * http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
 * 
 * A Maze is given as N*N binary matrix of blocks where source block is the
 * upper left most block i.e., maze[0][0] and destination block is lower
 * rightmost block i.e., maze[N-1][N-1]. A rat starts from source and has to
 * reach destination. The rat can move only in two directions: forward and down.
 * In the maze matrix, 0 means the block is dead end and 1 means the block can
 * be used in the path from source to destination. Note that this is a simple
 * version of the typical Maze problem. For example, a more complex version can
 * be that the rat can move in 4 directions and a more complex version can be
 * with limited number of moves.
 * 
 * @author user
 * 
 */
public class RatInAMaze {
	
	public static void main(String[] args) {
		Integer [][] maze = {{1, 0, 0, 0},
					        {1, 1, 0, 1},
					        {0, 1, 0, 0},
					        {1, 1, 1, 1}};
		System.out.println(ratInMaze(maze, maze.length));
	}
	
	public static boolean ratInMaze(Integer [][] maze, int dim) {
		Integer [][] movement = new Integer[dim][dim];
		movement[0][0] = 0;
		boolean ratInMaze =  ratInMaze(0, 0, movement, maze, dim);
		if(ratInMaze) {
			MyUtilities.printDoubleDimensionalArrayNeatly(movement, 5);
		}
		return ratInMaze;
	}
	public static boolean ratInMaze(int rowIdx, int colIdx, Integer [][] movement,Integer [][] maze, int dim) {
		if(rowIdx == colIdx && rowIdx == (dim - 1)) {
			return true;
		}
		int prevMove = movement[rowIdx][colIdx];
		int curMove = prevMove + 1;
		int newRowIdx = rowIdx;
		int newColIdx = colIdx;
		//move forward
		newColIdx = colIdx + 1;
		if(maze[newRowIdx][newColIdx] == 1) {
			movement[newRowIdx][newColIdx] = curMove;
			boolean ratInMaze = ratInMaze(newRowIdx, newColIdx, movement, maze, dim);
			if(ratInMaze) {
				return true;
			}
		}
		//move down
		newRowIdx = rowIdx + 1;
		newColIdx = colIdx;
		if(maze[newRowIdx][newColIdx] == 1) {
			movement[newRowIdx][newColIdx] = curMove;
			boolean ratInMaze = ratInMaze(newRowIdx, newColIdx, movement, maze, dim);
			if(ratInMaze) {
				return true;
			}
		}
		movement[newRowIdx][newColIdx] = null;
		return false;
	}
	
	public static boolean isSafe(int rowIdx, int colIdx, int dim) {
		boolean isValidIdx =  (rowIdx < dim && rowIdx >= 0 && colIdx < dim && colIdx >=0);
		return isValidIdx;
	}
}
