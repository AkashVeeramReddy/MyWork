package utils;

import java.util.Arrays;
import java.util.List;

public class MyUtilities {
	
	public static boolean nullSafeEquals(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}
	
	public static boolean isEven(int no) {
		return no % 2 == 0;
	}
	
	public static boolean isOdd(int no) {
		return no % 2 != 0;
	}
	
	public static <K> void swap(K [] array,int idx1,int idx2) {
		K temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
	}
	
	public static <K extends Comparable<? super K>> K getMaxElement(K... elements) {
		return getMaxElementinArray(elements);
	}
	
	public static <K extends Comparable<? super K>> K getMaxElementinArray(K[] elements) {
		K maxEle = null;
		int itr = 0;
		for (K eleItr : elements) {
			if(itr == 0) {
				maxEle = eleItr;
			} else {
				int compareTo = maxEle.compareTo(eleItr);
				if(compareTo < 0) {
					maxEle = eleItr;
				}
			}
			itr++;
		}
		return maxEle;
	}
	
	public static <K extends Comparable<? super K>> K getMinElement(K... elements) {
		return getMinElementInArray(elements);
	}
	
	public static <K extends Comparable<? super K>> K getMinElementInArray(K[] elements) {
		K minEle = null;
		int itr = 0;
		for (K eleItr : elements) {
			if(itr == 0) {
				minEle = eleItr;
			} else {
				int compareTo = minEle.compareTo(eleItr);
				if(compareTo > 0) {
					minEle = eleItr;
				}
			}
			itr++;
		}
		return minEle;
	}
	
	public static void removeCommaAtEndOfBuilder(StringBuilder builder) {
		if(builder != null) {
			int length = builder.length();
			if(length >0 && builder.charAt(length - 1) == ',') {
				builder.setLength(length - 1);
			}
		}
	}
	
	public static void populateIntegerArrayWithRandomNos(int [][] array) {
		populateIntegerArrayWithRandomNos(array,10);
	}
	
	public static void populateIntegerArrayWithRandomNos(int [][] array,int maxElement) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				int no = 0;
				while(no==0) {
					no = (int) (Math.random() * maxElement);
				}
				array[i][j] = no;
			}
		}
	}
	
	public static void populateIntegerArrayWithRandomNos(Integer [][] array, int maxEle) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				int no = 0;
				while(no==0) {
					no = (int) (Math.random() * maxEle);
				}
				array[i][j] = no;
			}
				
		}
	}
	
	public static void populateIntegerArrayWithRandomNos(Integer [][] array) {
		populateIntegerArrayWithRandomNos(array,10);
	}
	
	public static void populateIntegerArrayWithRandomNos(Integer [] array) {
		populateIntegerArrayWithRandomNos(array, 10);
	}
	
	public static void populateIntegerArrayWithRandomNos(Integer [] array,int maxEle) {
		for (int i = 0; i < array.length; i++) {
			int no = 0;
			while(no==0) {
				no = (int) (Math.random() * maxEle);
			}
			array[i] = no;
		}
	}
	
	public static void populateIntegerArrayWithRandomNos(int [] array) {
		populateIntegerArrayWithRandomNos(array, 10);
	}
	
	public static void populateIntegerArrayWithRandomNos(int [] array,int maxEle) {
		for (int i = 0; i < array.length; i++) {
			int no = 0;
			while(no==0) {
				no = (int) (Math.random() * maxEle);
			}
			array[i] = no;
		}
	}
	
	/**
	 * [0,0] appears on top
	 * @param <K>
	 * @param array
	 * @param cellWidth
	 */
	public static <K> void printDoubleDimensionalArray(K[][] array,int cellWidth) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				String string = String.valueOf(array[i][j]);
				System.out.print(string);
				printTrailingSpaces(cellWidth, string);
			}
			System.out.println();
		}
	}
	
	/**
	 * [0,0] appears on bottom
	 */
	public static <K> void printDoubleDimensionalArrayNeatly(K[][] array,int cellWidth) {
		for (int i = array.length-1; i >= 0; i--) {
			for (int j = 0; j < array[0].length; j++) {
				String string = String.valueOf(array[i][j]);
				System.out.print(string);
				printTrailingSpaces(cellWidth, string);
			}
			System.out.println();
		}
	}
	
	public static <K> void printSingleDimensionArray(K[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(String.valueOf(array[i]));
			System.out.print("\t");
		}
		System.out.println();
	}
	
	/**
	 * 
	 * @param <K>
	 * @param array
	 * @param cellWidth
	 */
	public static <K> void printSingleDimensionArrayNeatly(K[] array,int cellWidth) {
		for (int i = 0; i < array.length; i++) {
			String string = String.valueOf(array[i]);
			System.out.print(string);
			printTrailingSpaces(cellWidth, string);
		}
		System.out.println();
	}

	public static void printTrailingSpaces(int cellWidth, String printedString) {
		for(int k = cellWidth;k >= printedString.length();k--) {
			System.out.print(" ");
		}
	}
	
	public static <K> String getStringRepOfArray(K[] array) {
		List<K> asList = Arrays.asList(array);
		return asList.toString();
	}
}
