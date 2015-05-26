package dynamicprogramming;

import utils.MyUtilities;

/**
 * Given a rod of length n inches and an array of prices that contains prices
 * of all pieces of size smaller than n. Determine the maximum value obtainable
 *  by cutting up the rod and selling the pieces. For example, if length of the 
 *  rod is 8 and the values of different pieces are given as following, then the
 *   maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)


length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20
And if the prices are as following, then the maximum obtainable value is 24 
(by cutting in eight pieces of length 1)

length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 3   5   8   9  10  17  17  20
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 * 
 */
public class CutTheRod {
	
	public static void optimumCutTheRoad(int lengthOfTheRod) {
		
		Integer[] priceArray = new Integer[lengthOfTheRod];
		for(int i = 0;i<lengthOfTheRod;i++) {
			priceArray[i] = (int) Math.ceil((Math.random() * 10)*(i + 1));
		}
		System.out.println("======================Price Array================================");
		MyUtilities.printSingleDimensionArray(priceArray);
		Info [] infoArray = new Info[lengthOfTheRod];
		Info firstInfo = new Info();
		firstInfo.totalCost = priceArray[0];
		firstInfo.optimalCut = 0;
		infoArray[0] = firstInfo;
		for(int i = 1;i < lengthOfTheRod;i++) {
			Info info = new Info();
			infoArray[i] = info;
			int totalCost = 0;
			int optimalCut = -1;
			int costItr = 0;
			for(int j=0;j <=i;j++) {
				if(j==i) {
					costItr = priceArray[i];
				} else {
					/*int oldLen = lengthOfTheRod - (j+1);
					Info info2 = infoArray[oldLen - 1];
					int totalCost2 = info2.totalCost;
					costItr = totalCost2 + */
					int prefixRodLength = j+1;
					int suffixRodLength = i+1 - prefixRodLength;
					costItr = infoArray[prefixRodLength - 1].totalCost
					+ priceArray[suffixRodLength - 1];
				}
				if(totalCost < costItr) {
					totalCost = costItr;
					optimalCut = j;
				}
			}
			info.totalCost = totalCost;
			info.optimalCut = optimalCut;
		}
		System.out.println("======================Optimal Cost Array================================");
		MyUtilities.printSingleDimensionArray(infoArray);
		
		System.out.println("===================Cut the rod as follows=================================");
		printOptimalCost(infoArray,lengthOfTheRod);
	}
	
	
	private static void printOptimalCost(Info[] infoArray, int lengthOfTheRod) {
		if(lengthOfTheRod == 0)
			return;
		int optimalCut = infoArray[lengthOfTheRod-1].optimalCut;
		int lengthOfSuffixRod = lengthOfTheRod - optimalCut -1;
		if(lengthOfSuffixRod != 0) {
			System.out.println("Break "+lengthOfSuffixRod+ " unit(s)");
			printOptimalCost(infoArray, lengthOfTheRod - lengthOfSuffixRod);
		} else {
			System.out.println("Use the remaining rod of "+lengthOfTheRod + " unit(s)");
		}
	}


	private static class Info {
		private int optimalCut;
		private int totalCost;
		@Override
		public String toString() {
			return "[oC=" + optimalCut + ", tC="
					+ totalCost + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		optimumCutTheRoad(8);
	}
}
