package matrices;

import utils.ArrayUtilities;

/**
 * Given a matrix, clockwise rotate elements in it.
 * 
 * http://www.geeksforgeeks.org/rotate-matrix-elements/
 * 
 * Input
	1	2	3
	4	5	6
	7	8	9
	
	Output:
	4	1	2
	7	5	3
	8	9	6
	
	Input
	 { {1,  2,  3,  4},
	    {5,  6,  7,  8},
	    {9,  10, 11, 12},
		{13, 14, 15, 16}  };
		
	Output
	5 1 2 3
	9 10 6 4
	13 11 7 8
	14 15 16 12	
 * @author user
 *
 */
public class RotateClockwise {
	public static void main(String[] args) {
		Integer[][]matrix =  { {1,  2,  3,  4},
			    {5,  6,  7,  8},
			    {9,  10, 11, 12},
				{13, 14, 15, 16}  };
		rotateSquareMatrixClockwise(matrix);
		ArrayUtilities.printDoubleDimensionalArray(matrix, 4);
	}
	
	public static void rotateSquareMatrixClockwise(Integer [][]matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		int startRow = 0;
		int startCol = 0;
		int endRow;
		int endCol;
		int nextRowIdx = -1;
		int nextColIdx = -1;
		int curRowIdx;
		int curColIdx;
		int dataAtCurIdx;
		int dataAtNextIdx;
		for(int i=0;i < Math.min(rows/2, cols/2); i++) {
				startRow = i;
				startCol = i;
			//for(;startCol < cols/2; startCol++) {
				endRow = rows - startRow-1;
				endCol = cols - startRow - 1;
				curRowIdx = startRow;
				curColIdx = startCol;
				dataAtCurIdx = matrix[curRowIdx][curColIdx];
				do {
					if(curRowIdx==startRow) {
						if(curColIdx != endCol) {
							//move right
							//nextRowIdx
							nextColIdx = curColIdx + 1;
							nextRowIdx = curRowIdx;
						}
					}
					if(curColIdx==endCol) {
						if(curRowIdx != endRow) {
							//move down
							nextRowIdx = curRowIdx + 1;
							nextColIdx = curColIdx;
						}
					}
					if(curRowIdx==endRow) {
						if(curColIdx != startCol) {
							//move left
							nextColIdx = curColIdx - 1;
							nextRowIdx = curRowIdx;
						}
					}
					if(curColIdx==startCol) {
						if(curRowIdx != startRow) {
							//move up
							nextRowIdx = curRowIdx - 1;
							nextColIdx = curColIdx;
						}
					}
					dataAtNextIdx = matrix[nextRowIdx][nextColIdx];
					matrix[nextRowIdx][nextColIdx] = dataAtCurIdx;
					
					curRowIdx = nextRowIdx;
					curColIdx = nextColIdx;
					dataAtCurIdx = dataAtNextIdx;
					
				} while(!(nextRowIdx == startRow && nextColIdx == startCol));
			//}
		}
	}
}
