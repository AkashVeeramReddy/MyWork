package dynamicprogramming;

public class CoinChange {
	
	/**
	 * Optimise no of coins(should be minimum) to make noOfRupees from 
	 * rupee of denominations denominationsArray
	 * @param noOfRupees
	 * @param denominationsArray - should contain denomination of 1
	 */
	public static void printCoinChange(int noOfRupees,Integer denominationsArray[]) {
		Integer minIndexArray[] = new Integer[noOfRupees+1];
		minIndexArray[0] = 0;
		for(int i = 1;i<=noOfRupees;i++) {
			int minIndex = -1;
			int minNoOfDeno = 99999;
			for(int j = 0;j<denominationsArray.length;j++) {
				int noOfDeno = 0;
				if(i >= denominationsArray[j]) {
					noOfDeno = 1 + minIndexArray[i - denominationsArray[j]];
					if(noOfDeno < minNoOfDeno) {
						minIndex = j;
						minNoOfDeno = noOfDeno;
					}
				}
			}
			minIndexArray[i] = minIndex;
		}
		printMinIndex(noOfRupees,minIndexArray,denominationsArray);
	}

	private static void printMinIndex(int noOfRupees,Integer minIndexArray[],Integer denominationsArray[]) {
		if(noOfRupees > 0) {
			Integer integer = minIndexArray[noOfRupees];
			System.out.print(denominationsArray[integer]+",");
			printMinIndex(noOfRupees - denominationsArray[integer], minIndexArray, denominationsArray);
		}
	}
	
	public static void main(String[] args) {
		printCoinChange(10, new Integer[]{6,5,1});
	}
	
}
