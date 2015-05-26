package permutation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Recursion {
	
	public static Collection<String> getAllClosedParanthesis(int n) {
		if(n==1) {
			Collection<String> arrayList = new HashSet<String>();
			arrayList.add("()");
			return arrayList;
		} else {
			Collection <String> para = getAllClosedParanthesis(n-1);
			Collection<String> new1 = new HashSet<String>();
			for (String string : para) {
				char[] charArray = string.toCharArray();
				for (int i = 0; i < charArray.length; i++) {
					for (int j = i; j < charArray.length; j++) {
						StringBuilder builder = new StringBuilder(string);
						builder.insert(i, "(");
						builder.insert(j+1, ")");
						new1.add(builder.toString());
					}
				}
			}
			return new1;
		}
	}
	
	public static void printAllClosedParanthesis(int i) {
		char [] array = new char[2*i];
		printAllClosedParanthesis(array,i,i,0);
	}

	private static void printAllClosedParanthesis(char[] array, int noOfLeftParRem, int noOfRightParRem,int currentIdx) {
		if(noOfLeftParRem == 0 && noOfRightParRem==0)
			System.out.println(array);
		else {
			if(noOfLeftParRem > 0) {
				String str = new String(array);
				char[] charArray = str.toCharArray();
				charArray[currentIdx] = '(';
				printAllClosedParanthesis(charArray,noOfLeftParRem -1,noOfRightParRem,currentIdx+1);
			} 
			if(noOfRightParRem > noOfLeftParRem) {
				String str = new String(array);
				char[] charArray = str.toCharArray();
				charArray[currentIdx] = ')';
				printAllClosedParanthesis(charArray,noOfLeftParRem,noOfRightParRem-1,currentIdx+1);
			}
		}
	}
	
	public static void printAllPossibilitiesWithRepititionForAllLength() {
		char [] charArray = {'a','b'};
		printAllPossibilitiesWithRepititionForAllLength(charArray,0,"");
	}

	private static void printAllPossibilitiesWithRepititionForAllLength(char[] charArray, int length,String str) {
		if(length == (charArray.length))
			return;
		for (int j = 0; j < charArray.length; j++) {
			String str2 = str + charArray[j];
			System.out.println(str2);
			printAllPossibilitiesWithRepititionForAllLength(charArray, length+1, str2);
		}
	}
	
	public static void printPermutationsWithoutRepititionOfSameLength() {
		String str = "abc";
		List<String> printPerm = printPermutationsWithoutRepititionOfSameLength(str);
		System.out.println();
	}

	private static List<String> printPermutationsWithoutRepititionOfSameLength(String str) {
		List<String> printPerm1 = new ArrayList<String>();
		if(str.length()==1) {
			printPerm1.add(str);
			return printPerm1;
		}
		List<String> printPerm = printPermutationsWithoutRepititionOfSameLength(str.substring(1));
		char charAt = str.charAt(0);
		for (String string : printPerm) {
			for (int i = 0; i <= string.length(); i++) {
				StringBuilder builder = new StringBuilder(string);
				builder.insert(i, charAt);
				printPerm1.add(builder.toString());
			}
		}
		return printPerm1;
	}
	
	public static List<String> getAllSubsets(String str) {
		if(str.length() == 1) {
			List<String> set = new ArrayList<String>();
			set.add(str);
			return set;
		}
		List<String> sub = getAllSubsets(str.substring(1));
		List<String> newSub = new ArrayList<String>();
		char charAt = str.charAt(0);
		newSub.add(String.valueOf(charAt));
		for (String string : sub) {
			newSub.add(string + charAt);
		}
		sub.addAll(newSub);
		return sub;
	}
	
	/**
	 * Incorrect algorithm as repetitions will come
	 * @param noOfRupees
	 * @param denominations
	 * @param combination
	 *//*
	public static void printAllCombinations(int noOfRupees,Integer [] denominations,List<Integer> combination){
		for(int i=0;i<denominations.length;i++) {
			Integer denomination = denominations[i];
			int noOfRupeesLeft = noOfRupees - denomination;
			ArrayList<Integer> combination2 = new ArrayList<Integer>(combination);
			if(noOfRupeesLeft > 0) {
				combination2.add(denomination);
				printAllCombinations(noOfRupeesLeft, denominations,combination2);
			} else if(noOfRupeesLeft == 0) {
				combination2.add(denomination);
				System.out.println(combination2);
			}
		}
	}*/
	
	public static void main(String[] args) {
//		printAllPossibilitiesWithRepititionForAllLength();
//		printAllCombinations(10, new Integer[]{1,2,5},0,new ArrayList<Integer>());
//		int a =findNoOfPossibleCombinationsForCoins(4, new Integer[]{1,2},0);
		int a = findNoWaysForRobotInGrid(1, 1);
		System.out.println(a);
	}
	
	public static void printAllCombinationsForCoins(int noOfRupees,Integer[] denominations,int index,List<Integer> combination) {
		if(index < denominations.length) {
			Integer integer = denominations[index];
			int option = noOfRupees / integer;
			for(int i=0;i<=option;i++) {
				int noOfRupeesLeft = noOfRupees - integer*i;
				ArrayList<Integer> arrayList = new ArrayList<Integer>(combination);
				for(int j=0;j<i;j++) {
					arrayList.add(integer);
				}
				if(noOfRupeesLeft == 0) {
					System.out.println(arrayList);
				} else {
					printAllCombinationsForCoins(noOfRupeesLeft, denominations, (index+1), arrayList);
				}
			}
		}
	}
	
	public static int findNoOfPossibleCombinationsForCoins(int noOfRupees,Integer[] denominations,int index){
		int way = 0;
		if(index < denominations.length) {
			Integer integer = denominations[index];
			int option = noOfRupees / integer;
			for(int i=0;i<=option;i++) {
				int noOfRupeesLeft = noOfRupees - integer*i;
				if(noOfRupeesLeft == 0)
					way = way + 1;
				else
					way = way + findNoOfPossibleCombinationsForCoins(noOfRupeesLeft, denominations, index+1);
			}
		}
		return way;
	}
	
	/**
	 * Imagine a robot sitting on the upper left hand corner of an NxN grid.
	 *  The robot can only move in two directions: right and down.
	 *   How many possible paths are there for the robot?
	 * @param noOfRows
	 * @param noOfColumns
	 * @return
	 */
	public static int findNoWaysForRobotInGrid(int noOfRows,int noOfColumns) {
		if(noOfRows ==0 && noOfColumns==0) {
			return 1;
		} else if(noOfRows == 0) {
			return findNoWaysForRobotInGrid(noOfRows, noOfColumns - 1);
		} else if(noOfColumns == 0) {
			return findNoWaysForRobotInGrid(noOfRows-1, noOfColumns);
		} else {
			return findNoWaysForRobotInGrid(noOfRows, noOfColumns - 1)
			+ findNoWaysForRobotInGrid(noOfRows-1, noOfRows);
		}
//		return 0;
	}
}
