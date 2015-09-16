package backtracking;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import utils.FileUtils;




public class Sudoku {
	/**
	 * If value is m,means m*m squares(grid) with m*m elements in each square(grid),
	 * m*m rows,m*m columns and m*m elements in each row/column and values possible in
	 * each cell is [1..m*m]. If this variable is changed, change CREATE_PRINT_FILE
	 * boolean variable true to true for the first execution.
	 */
	public static final int SUDOKU_INT = 3;
	
	/**
	 * No of rows = No of columns = No of grids
	 */
	public static final int SUDOKU_INTXINT = (int) Math.pow(SUDOKU_INT, 2);
	
	public static final Integer NO_INT = 0;
	
	public static final String NEW_LINE = System.getProperty("line.separator");
	//array[0] = 0 if values hasnt been fixed, else the actual value
	//array[1-9] = 0 if the number cant occur, 1 if the number can occur,-1 - some other
	public static Map<Integer, int []> possibleValues = new LinkedHashMap<Integer, int []>();
	public static Map<Integer, Boolean> isChangeOccured = new LinkedHashMap<Integer, Boolean>();
	
	public static void main(String[] args) {
		solveSudoku();
	}
	private static void solveSudoku() {
		//the input file should be in the same location as of the Sudoku class file when running from eclipse
		readFileForInitializeSudoku("hardestSudoku.txt");
		eliminateRedundantValues();
		boolean isSolved = solveSudokuFromPossibleValues(0);
		printBoard();
		if(isSolved) {
			System.out.println("Sudoku solved");
		}
	}

	private static boolean solveSudokuFromPossibleValues(int cellIdx) {
		if(cellIdx == SUDOKU_INTXINT*SUDOKU_INTXINT) {
			for(int row = 0;row < SUDOKU_INTXINT;row++) {
				for(int col = 0;col < SUDOKU_INTXINT;col++) {
					Integer cellNo = getCellNo(row,col);
					int[] is = possibleValues.get(cellNo);
					int valueFixed = is[0];
					if(valueFixed == 0) {
						return false;
					}
				}
			}
			return true;
		}
		int[] poss = possibleValues.get(cellIdx);
		if(poss[0] != 0) {
			//already fixed. So consider nextCell
			return solveSudokuFromPossibleValues(cellIdx+1);
		} else {
			for (int i = 1; i < poss.length; i++) {
				if(poss[i] == 1) {
					//poss[i] is a possibility
					if(isSafePossibility(cellIdx, i)) {
						//update possible values for it and other cells in the same row/column/grid
						updatePossibilitiesFor(cellIdx, i);
						boolean isSolved = solveSudokuFromPossibleValues(cellIdx+1);
						if(isSolved) {
							//may have to change possibilities
							return true;
						} else {
							//revert and consider next possibility
							revertPossibilitiesFor(cellIdx, i);
						}
					}
				}
			}
		}
		return false;
	}
	
	public static void updatePossibilitiesFor(int cellIdx, int val) {
		int[] poss = possibleValues.get(cellIdx);
		poss[0] = val;
		poss[val] = 0;
		int rowNo = getRowNo(cellIdx);
		int colNo = getColNo(cellIdx);
		int gridNo = getGridNo(rowNo, colNo);
		for (int i = 0; i < SUDOKU_INTXINT; i++) {
			//traverse along cell in row
			if(i != colNo) {
				Integer cellNo = getCellNo(rowNo, i);
				poss = possibleValues.get(cellNo);
				poss[val]--;
			}
		}
		for (int i = 0; i < SUDOKU_INTXINT; i++) {
			//traverse along cell in column
			if(i != rowNo) {
				Integer cellNo = getCellNo(i, colNo);
				poss = possibleValues.get(cellNo);
				poss[val]--;
			}
		}
		//traverse along cells in grid
		int rowStart = (gridNo/SUDOKU_INT)*SUDOKU_INT;
		int colStart = (gridNo%SUDOKU_INT)*SUDOKU_INT;
		for (int rowItr = rowStart; rowItr < rowStart + SUDOKU_INT; rowItr++) {
			for (int colItr = colStart; colItr < colStart + SUDOKU_INT; colItr++) {
				if(rowItr == rowNo || colItr == colNo) {
					continue;
				}
				poss[val]--;
				
			}
		}
	}
	
	public static void revertPossibilitiesFor(int cellIdx, int val) {
		int[] poss = possibleValues.get(cellIdx);
		poss[0] = 0;
		poss[val] = 1;
		int rowNo = getRowNo(cellIdx);
		int colNo = getColNo(cellIdx);
		int gridNo = getGridNo(rowNo, colNo);
		for (int i = 0; i < SUDOKU_INTXINT; i++) {
			//traverse along cell in row
			if(i != colNo) {
				Integer cellNo = getCellNo(rowNo, i);
				poss = possibleValues.get(cellNo);
				poss[val]++;
			}
		}
		for (int i = 0; i < SUDOKU_INTXINT; i++) {
			//traverse along cell in column
			if(i != rowNo) {
				Integer cellNo = getCellNo(i, colNo);
				poss = possibleValues.get(cellNo);
				poss[val]++;
			}
		}
		//traverse along cells in grid
		int rowStart = (gridNo/SUDOKU_INT)*SUDOKU_INT;
		int colStart = (gridNo%SUDOKU_INT)*SUDOKU_INT;
		for (int rowItr = rowStart; rowItr < rowStart + SUDOKU_INT; rowItr++) {
			for (int colItr = colStart; colItr < colStart + SUDOKU_INT; colItr++) {
				if(rowItr == rowNo || colItr == colNo) {
					continue;
				}
				poss[val]++;
				
			}
		}
	}
	
	public static boolean isSafePossibility(int cellIdx, int possibility) {
		int rowNo = getRowNo(cellIdx);
		int colNo = getColNo(cellIdx);
		int gridNo = getGridNo(rowNo, colNo);
		for (int i = 0; i < SUDOKU_INTXINT; i++) {
			//traverse along cell in row
			if(i != colNo) {
				Integer cellNo = getCellNo(rowNo, i);
				int[] poss = possibleValues.get(cellNo);
				if(poss[0] == possibility) {
					return false;
				}
			}
		}
		for (int i = 0; i < SUDOKU_INTXINT; i++) {
			//traverse along cell in column
			if(i != rowNo) {
				Integer cellNo = getCellNo(i, colNo);
				int[] poss = possibleValues.get(cellNo);
				if(poss[0] == possibility) {
					return false;
				}
			}
		}
		//traverse along cells in grid
		int rowStart = (gridNo/SUDOKU_INT)*SUDOKU_INT;
		int colStart = (gridNo%SUDOKU_INT)*SUDOKU_INT;
		for (int rowItr = rowStart; rowItr < rowStart + SUDOKU_INT; rowItr++) {
			for (int colItr = colStart; colItr < colStart + SUDOKU_INT; colItr++) {
				if(rowItr == rowNo || colItr == colNo) {
					continue;
				}
				Integer cellNo = getCellNo(rowItr, colItr);
				int[] poss = possibleValues.get(cellNo);
				if(poss[0] == possibility) {
					return false;
				}
				
			}
		}
		return true;
	}
	private static void printBoard() {
		int count = 0,lineCount = 0;
		for(int row = 0;row < SUDOKU_INTXINT;row++) {
			if(lineCount % SUDOKU_INT == 0) {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < SUDOKU_INTXINT*2 + SUDOKU_INT; i++) {
					builder.append("-");
				}
				System.out.println(builder);
				lineCount = 0;
			}
			lineCount++;
			for(int col = 0;col < SUDOKU_INTXINT;col++) {
				Integer cellNo = getCellNo(row,col);
				System.out.print(possibleValues.get(cellNo)[0] + " ");
				count++;
				if(count % SUDOKU_INT == 0) {
					System.out.print("|");
					count = 0;
				}
			}
			//lineCount++;
			System.out.print(NEW_LINE);
			
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < SUDOKU_INTXINT*2 + SUDOKU_INT; i++) {
			builder.append("-");
		}
		System.out.println(builder);
	}
	/**
	 * Function is not called.
	 */
	public static void eliminateRedundantValues() {
		for(int row = 0;row < SUDOKU_INTXINT;row++) {
			for(int col = 0;col < SUDOKU_INTXINT;col++) {
				eliminateRedundantValues(row,col);
			}
		}
	}
	public static boolean isOneValuePossible(int []array) {
		int sum = 0;
		int idx = 0;
		for (int i = 1; i < array.length; i++) {
			sum = sum + array[i];
			if(array[i] == 1) {
				idx = i;
			}
		}
		if(sum == 1) {
			//set the one probable
			array[0] = idx;
			return true;
		}
		return false;
	}
	public static void eliminateRedundantValues(int row, int col) {
		Integer cellNo = getCellNo(row, col);
		if(!isChangeOccured.get(cellNo)) {
			return;
		}
		//System.out.println("eliminateRedundantValues for row:"+row+" colNo:"+col);
		
		int[] possibleCellValues = possibleValues.get(cellNo);
		Set<Integer> changedCellNos = new HashSet<Integer>();
		if(possibleCellValues[0] != 0) {
			//only one value
			Integer intInCell = possibleCellValues[0];
			//go thru other row values
			for (int i = 0; i < SUDOKU_INTXINT; i++) {
				if(i == col) {
					continue;
				}
				Integer rowCellItr = getCellNo(row, i);
				int[] possibleCellValuesRowItr = possibleValues.get(rowCellItr);
				if(possibleCellValuesRowItr[intInCell] !=0) {
					possibleCellValuesRowItr[intInCell] = 0;
					isChangeOccured.put(rowCellItr, true);
					if(isOneValuePossible(possibleCellValuesRowItr)) {
						changedCellNos.add(rowCellItr);
					}
				}
				
			}
			//go thru other col values
			for (int i = 0; i < SUDOKU_INTXINT; i++) {
				if(i == row) {
					continue;
				}
				Integer colCellItr = getCellNo(i, col);
				int[] possibleCellValuesColItr = possibleValues.get(colCellItr);
				if(possibleCellValuesColItr[intInCell] !=0) {
					possibleCellValuesColItr[intInCell] = 0;
					isChangeOccured.put(colCellItr, true);
					if(isOneValuePossible(possibleCellValuesColItr)) {
						changedCellNos.add(colCellItr);
					}
				}
				//possibleCellValuesColItr.remove(intInCell);
				
			}
			//go thru other grid values
			int gridNo = getGridNo(row, col);
			int rowStart = (gridNo/SUDOKU_INT)*SUDOKU_INT;
			int colStart = (gridNo%SUDOKU_INT)*SUDOKU_INT;
			for (int rowItr = rowStart; rowItr < rowStart + SUDOKU_INT; rowItr++) {
				for (int colItr = colStart; colItr < colStart + SUDOKU_INT; colItr++) {
					if(rowItr == row && colItr == col) {
						continue;
					}
					Integer cellInGrid = getCellNo(rowItr, colItr);
					int[] possibleCellValuesGridItr = possibleValues.get(cellInGrid);
					if(possibleCellValuesGridItr[intInCell] !=0) {
						possibleCellValuesGridItr[intInCell] = 0;
						isChangeOccured.put(cellInGrid, true);
						if(isOneValuePossible(possibleCellValuesGridItr)) {
							changedCellNos.add(cellInGrid);
						}
					}
					//possibleCellValuesGridItr.remove(intInCell);
					
				}
			}
			isChangeOccured.put(cellNo, false);
			for (Integer cellNoItr : changedCellNos) {
				eliminateRedundantValues(getRowNo(cellNoItr),getColNo(cellNoItr));
			}
		}
	}
	
	public static int getRowNo(int cellNo) {
		return cellNo/SUDOKU_INTXINT;
	}
	
	public static int getColNo(int cellNo) {
		return cellNo%SUDOKU_INTXINT;
	}
	
	public static int getGridNo(int row,int column) {
		return (row/SUDOKU_INT)*SUDOKU_INT + column/SUDOKU_INT;
	}
	
	public static String getFileContents(File file) {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				builder.append(line);
				builder.append(NEW_LINE);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	private static void readFileForInitializeSudoku(String fileName) {
		File testCaseFile = FileUtils.getFile(fileName,Sudoku.class);
		try (BufferedReader br = new BufferedReader(new FileReader(testCaseFile))) {
		    String line;
		    int row = 0;
		    while ((line = br.readLine()) != null) {
		    	StringTokenizer st = new StringTokenizer(line);
		    	int col = 0;
				while (st.hasMoreTokens()) {
					String nextToken = st.nextToken();
					try {
						int parseInt = Integer.parseInt(nextToken);
						fillMapWithPossibilities(parseInt, row, col);
					} catch (NumberFormatException e) {
						fillMapWithPossibilities(NO_INT, row, col);
					}
					
					col++;
				}
				row++;
				
		    }
		    System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Integer getCellNo(int row,int col) {
		return row*SUDOKU_INTXINT + col;
	}
	
	public static void fillMapWithPossibilities(int val,int row,int col) {
		//array[0] = 0 if values hasnt been fixed, else the actual value
		//array[1-9] = 0 if the number cant occur, 1 if the number can occur,-1 - some other
		int []array = new int[SUDOKU_INTXINT+1];
		//Set<Integer> values = new LinkedHashSet<Integer>();
		if(val == NO_INT) {
			for (int i=1; i <= SUDOKU_INTXINT ; i++) {
				array[i] = 1;
			}
		} else {
			array[0] = val;
		}
		Integer cellNo = getCellNo(row, col);
		possibleValues.put(cellNo, array);
		isChangeOccured.put(cellNo, true);
	}
}