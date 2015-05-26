package dynamicprogramming;

public class PascalsTriangle {
	
	public static Integer getIntegerInPascalsTriangle(int row,int column) {
		if(column == 1 || column == (row + 1))
			return 1;
		Integer array[][] = new Integer[2][row+1];
		Integer whichRow = 0;
		for(int i = 0;i<2;i++) {
			for(int j = 0;i<=row;i++) {
				array[i][j] = -1;
			}
		}
		array[0][0] = 1;
		array[0][1] = 2;
		array[0][2] = 1;
		for(int i = 3;i <=row;i++) {
			
		}
		return array[whichRow][column];
	}
	
}
