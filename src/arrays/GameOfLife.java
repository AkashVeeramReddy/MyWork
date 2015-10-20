package arrays;

/**
 * According to the Wikipedia's article:
 * "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * 
 * Given a board with m by n cells, each cell has an initial state live (1) or
 * dead (0). Each cell interacts with its eight neighbors (horizontal, vertical,
 * diagonal) using the following four rules (taken from the above Wikipedia
 * article):
 * 
 * Any live cell with fewer than two live neighbors dies, as if caused by
 * under-population. Any live cell with two or three live neighbors lives on to
 * the next generation. Any live cell with more than three live neighbors dies,
 * as if by over-population.. Any dead cell with exactly three live neighbors
 * becomes a live cell, as if by reproduction. Write a function to compute the
 * next state (after one update) of the board given its current state.
 * 
 * Follow up: Could you solve it in-place? Remember that the board needs to be
 * updated at the same time: You cannot update some cells first and then use
 * their updated values to update other cells. In this question, we represent
 * the board using a 2D array. In principle, the board is infinite, which would
 * cause problems when the active area encroaches the border of the array. How
 * would you address these problems?
 * 
 * https://leetcode.com/problems/game-of-life/
 * @author user
 * 
 */
public class GameOfLife {
	public static final int[][] NEIGHBOURS = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	 
	public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for(int i=0; i<rows;i++) {
            for(int j=0; j<cols; j++) {
                update(board,i,j,rows,cols);
            }
        }
        for(int i=0; i<rows;i++) {
            for(int j=0; j<cols; j++) {
               board[i][j] = board[i][j] / 2;
            }
        }
    }
    
    public static void update(int[][] board, int row, int col, int rows, int cols) {
        int liveNeighbours = 0;
        for(int[] neighbour : NEIGHBOURS) {
            liveNeighbours = liveNeighbours + getVal(row + neighbour[0],col + neighbour[1],rows,cols,board);
        }
        int newVal = 0;
        if(board[row][col] == 1) {
            //live
            if(liveNeighbours > 3) {
                newVal = 0;
            } else if(liveNeighbours < 2) {
                newVal = 0;
            } else {
                newVal = 1;
            }
        } else {
            //dead
            if(liveNeighbours == 3) {
                newVal = 1;
            }
        }
        newVal = newVal*2 + board[row][col];
        board[row][col] = newVal;
    }
    
    public static int getVal(int row, int col, int rows, int cols,int[][] board) {
        if(row >=0 && row < rows) {
            if(col >=0 && col < cols) {
                return board[row][col] %2;
            }
        }
        return 0;
    }
}
