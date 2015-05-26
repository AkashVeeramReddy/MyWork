package dynamicprogramming;

import utils.MyUtilities;

/**
 * Given weights and values of n items, put these items in a knapsack of capacity W
 * to get the maximum total value in the knapsack. In other words, given two integer
 * arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated 
 * with n items respectively. Also given an integer W which represents knapsack capacity,
 * find out the maximum value subset of val[] such that sum of the weights of this subset
 * is smaller than or equal to W
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * 
 * @author KH348
 *
 */
public class KnapSack {
	
	public static void printKnapSack(Integer weightArray[],Integer valueArray[],int totalWeight) {
		if(weightArray.length != valueArray.length) {
			System.out.println("Length of values array and weights array inequal");
			return;
		}
		Info[][] costArray = new Info[weightArray.length + 1][totalWeight + 1];
		
		for(int blockItr=0;blockItr<=weightArray.length;blockItr++) {
			for(int weightItr=0;weightItr<=totalWeight;weightItr++) {
				int cost = Integer.MIN_VALUE;
				boolean isBlockIncluded = false;
				if(blockItr==0 || weightItr==0) {
					//base condition
					cost = 0;
				} else {
					Integer weightOfBlock = weightArray[blockItr-1];
					Integer valueOfBlock = valueArray[blockItr-1];
					if(weightOfBlock > weightItr){
						//weight of the block more than entireWeightItr(block not included)
						cost = costArray[blockItr-1][weightItr].cost;
						isBlockIncluded = false;
					} else {
						//weight of the block less than entireWeightItr
						int blockNotIncludedCost = costArray[blockItr-1][weightItr].cost;
						int blockIncludedCost = costArray[blockItr-1][weightItr - weightOfBlock].cost + valueOfBlock;
						if(blockNotIncludedCost > blockIncludedCost) {
							isBlockIncluded = false;
						} else {
							isBlockIncluded = true;
						}
						cost = Math.max(blockIncludedCost, blockNotIncludedCost);
					}
				}
				Info info = new Info();
				costArray[blockItr][weightItr] = info;
				info.cost = cost;
				info.isBlockIncluded = isBlockIncluded;
			}
		}
		System.out.println("========The Weight Array is===========");
		MyUtilities.printSingleDimensionArray(weightArray);
		System.out.println("========The Value Array is===========");
		MyUtilities.printSingleDimensionArray(valueArray);
//		System.out.println("===============Cost Array=========================");
//		MyUtilities.printDoubleDimensionalArrayNeatly(costArray, 15);
		System.out.println("Maximum cost is "+costArray[weightArray.length][totalWeight].cost);
		printOptimumCost(costArray,weightArray.length,totalWeight,weightArray,valueArray);
	}
	
	private static void printOptimumCost(Info[][] infoArray, int length,
			int totalWeight,Integer[] weightArray,Integer[] valueArray) {
		if(totalWeight ==0 || length ==0) {
			return;
		}
		boolean isBlockIncluded = infoArray[length][totalWeight].isBlockIncluded;
		if(isBlockIncluded) {
			printOptimumCost(infoArray, length-1, totalWeight-weightArray[length-1],weightArray,valueArray);
			System.out.println("Use Block No "+(length)+ " weighing "+weightArray[length-1]+",costing "+valueArray[length-1]);
		} else {
			printOptimumCost(infoArray, length-1, totalWeight,weightArray,valueArray);
		}
	}
	
	public static void main(String[] args) {
		printKnapSack(new Integer[]{5,10,15,20,25,30},
					  new Integer[]{30,19,25,30,40,60}, 50);
	}

	private static class Info {
		
		private int cost = Integer.MIN_VALUE;;
		private boolean isBlockIncluded = false;
		
		@Override
		public String toString() {
			return "[c:"+cost + ",i:"+isBlockIncluded + "]";
		}
		
	}
	
}
