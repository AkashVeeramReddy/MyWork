package dynamicprogramming;

import utils.ArrayUtilities;

/**
 * Continuing further on dynamic programming series, edit distance is an interesting algorithm.

Problem: Given two strings of size m, n and set of operations
 replace (R), insert (I) and delete (D) all at equal cost.
  Find minimum number of edits (operations) required to convert one string into another
 *
 *http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 */
public class EditDistance {
	
	/**
	 * 0 - no change
	 * 1 - insert
	 * 2 - replace
	 * 3 - delete	
	 * @param source
	 * @param target
	 */
	public static void printEditDistance(String source,String target) {
		int sourceLength = source.length();
		int targetLength = target.length();
		Info [][] infoArray = new Info[sourceLength+1][targetLength+1];
		Info infoAtZeroZero = new Info();
		infoAtZeroZero.editDistance = 0;
		infoAtZeroZero.fromPrev = 0;
		infoArray[0][0] = infoAtZeroZero;
		for(int  i = 1;i<=sourceLength;i++) {
			Info infoAtZeroColumn = new Info();
			infoAtZeroColumn.editDistance = i;
			infoAtZeroColumn.fromPrev = 3;
			infoArray[i][0] = infoAtZeroColumn;
		}
		for(int  j = 1;j<=targetLength;j++) {
			Info infoAtZeroColumn = new Info();
			infoAtZeroColumn.editDistance = j;
			infoAtZeroColumn.fromPrev = 1;
			infoArray[0][j] = infoAtZeroColumn;
		}
		for (int i = 1; i <= sourceLength; i++) {
			for (int j = 1; j <= targetLength; j++) {
				Info info = new Info();
				int editDistance = -1;
				int fromPrev = -1;
				char charAtSource = source.charAt(i-1);
				char charAtTarget = target.charAt(j-1);
				infoArray[i][j] = info;
				boolean isSourceAndTargetCharEq = (charAtSource == charAtTarget);
				Integer costFromRemoval = getInfoFor(infoArray,i-1, j) + 1;
				Integer costFromInsert = getInfoFor(infoArray,i, j-1) + 1;
				Integer costFromReplace = getInfoFor(infoArray, i-1, j-1) + (isSourceAndTargetCharEq?0:1);
				if(costFromRemoval < costFromInsert) {
					editDistance = costFromRemoval;
					fromPrev = 3;
					if(costFromRemoval < costFromReplace) {
						editDistance = costFromRemoval;
						fromPrev = 3;
					} else {
						editDistance = costFromReplace;
						fromPrev = isSourceAndTargetCharEq?0:2;
					}
				} else {
					if(costFromInsert < costFromReplace) {
						editDistance = costFromInsert;
						fromPrev = 1;
					} else {
						editDistance = costFromReplace;
						fromPrev = isSourceAndTargetCharEq?0:2;
					}
				}
				info.editDistance = editDistance;
				info.fromPrev = fromPrev;
			}
		}
		System.out.println("=======================Info Array=============================");
		ArrayUtilities.printDoubleDimensionalArrayNeatly(infoArray, 15);
		System.out.println();
		System.out.println("Edit Distance is "+infoArray[sourceLength][targetLength].editDistance);
		printEditDistance(source, target,infoArray,sourceLength,targetLength);
	}
	
	private static void printEditDistance(String source, String target,
			Info[][] infoArray, int sourceLength, int targetLength) {
		if(sourceLength == 0 && targetLength == 0)
			return;
		Info info = infoArray[sourceLength][targetLength];
		int fromPrev = info.fromPrev;
		switch (fromPrev) {
		case 0:
			printEditDistance(source, target, infoArray, sourceLength-1, targetLength-1);
			break;
		case 1:
			printEditDistance(source, target, infoArray, sourceLength, targetLength-1);
			System.out.print("Insert character '"+target.charAt(targetLength-1));
			System.out.print("' to form "+target.substring(0, targetLength));
			System.out.println();
			break;
		case 2:
			printEditDistance(source, target, infoArray, sourceLength-1, targetLength-1);
			System.out.print("Replace character at '"+source.charAt(sourceLength-1) + "' with");
			System.out.print(" character at '"+target.charAt(targetLength-1) + "' to form "+target.substring(0, targetLength));
			System.out.println();
			break;
		case 3:
			printEditDistance(source, target, infoArray, sourceLength-1, targetLength);
			System.out.print("Delete character at '"+source.charAt(sourceLength-1));
			System.out.print("' to form "+target.substring(0, targetLength));
			System.out.println();
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		printEditDistance("abcd", "abecd");
	}
	
	public static int getInfoFor(Info[][] info,int row,int column) {
		return info[row][column].editDistance;
	}
	
	private static class Info {
		int editDistance;
		/**
		 * 0 - no change
		 * 1 - insert
		 * 2 - replace
		 * 3 - delete	
		 */
		int fromPrev;
		@Override
		public String toString() {
			return "[eD=" + editDistance + ",fP="
					+ fromPrev + "]";
		}
		
		
	}
	
}
