package strings;

import java.lang.reflect.Array;
import java.text.StringCharacterIterator;

public class StringsAndArrays {
	
	public static final int NO_OF_ROWS = 3;
	public static final int NO_OF_COLUMNS = 4;
	
	/**
	 * @assume str1 and str2 has only small letters
	 * @param str1
	 * @param str2
	 * @return true if str1 and str2 are anagrams else false
	 */
	public static boolean testAnagrams(String str1,String str2) {
		int count[] = new int[26];
		char[] charArray = str1.toCharArray();
		for (char c : charArray) {
			int i = c - 97;
			count[i]++;
		}
		charArray = str2.toCharArray();
		for (char c : charArray) {
			int i = c - 97;
			if(count[i] == 0)
				return false;
			count[i]--;
		}
		for(int a:count) {
			if(a!=0)
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param str
	 * @return string with space replaced by '%20'
	 * @example 'ha i' -> 'ha%20i'
	 */
	public static String replaceSpaceWithPercentage20(String str) {
		StringBuilder builder = new StringBuilder();
		char[] charArray = str.toCharArray();
		for(char c:charArray) {
			if(c == ' ')
				builder.append("%20");
			else
				builder.append(c);
				
		}
		return builder.toString();
		
	}
	
	public static <K> void rotateArrayBy90Degrees(K [][] array,int rows,int cols,Class<K> clazz) {
		K [][] newArray = (K[][]) Array.newInstance(clazz, cols,rows);
		for (int i = 0; i < rows; i++) {
			for(int j=0;j<cols;j++) {
				newArray [j][rows - 1 -i] = array[i][j]; 
			}
		}
		printArray(newArray, cols, rows);
	}
	
	public static void main(String[] args) {
//		System.out.println(replaceSpaceWithPercentage20("ha i"));
		/*Integer array[][] = new Integer[NO_OF_ROWS][NO_OF_COLUMNS];
		populateIntegerArrayWithNos(array, NO_OF_ROWS, NO_OF_COLUMNS);
		printArray(array, NO_OF_ROWS, NO_OF_COLUMNS);
		rotateArrayBy90Degrees(array, NO_OF_ROWS, NO_OF_COLUMNS,Integer.class);*/
		Integer[][] array = new Integer[3][3];
		populateIntegerArrayWithNos(array, 3, 3);
//		rotate(array, 3);
		printArray(array, 3, 3);
		System.out.println();
	}
	
	public static void populateIntegerArrayWithNos(Integer[][] array,int rows,int cols) {
		int count = 0;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				array[i][j] = count++;
			}
		}
	}
	
	public static <K> void printArray(K[][] array,int rows ,int cols) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				builder.append(array[i][j]);
				builder.append(",");
			}
			builder.append("\n");
		}
		System.out.println(builder);
	}
	
	public static void setEntireRowAndColumnToZeroIfElementIsZero(Integer [][] matrix,int noOfRows,
			int noOfColumns,int itrRow,int itrColumn) {
		for(int i = itrRow;i<noOfRows;i++) {
			for(int j=itrColumn;j<noOfColumns;j++) {
				if(matrix[i][j] == 0) {
					setEntireRowAndColumnToZeroIfElementIsZero(matrix, noOfRows, noOfColumns, itrRow+1, itrColumn+1);
					return;
				}
			}
		}
	}
	
	/*public static void rotate(Integer[][] matrix, int n) {
		 for (int layer = 0; layer < n / 2; ++layer) {
		 int first = layer;
		 int last = n - 1 - layer;
		 for(int i = first; i < last; ++i) {
		 int offset = i - first;
		 int top = matrix[first][i]; // save top
		 // left -> top
		 matrix[first][i] = matrix[last-offset][first];
		
		 // bottom -> left
		 matrix[last-offset][first] = matrix[last][last - offset];
		
		 // right -> bottom
		 matrix[last][last - offset] = matrix[i][last];
		
		 // top -> right
		 matrix[i][last] = top; // right <- saved top
		 }
		 }
		}*/
}
