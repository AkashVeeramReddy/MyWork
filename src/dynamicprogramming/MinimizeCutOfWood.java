package dynamicprogramming;

import utils.ArrayUtilities;

/**
 * 
 * Suppose a wood of length x(say 100) metres can be cut at only some points(say 20,30,70).
 * When you cut a wood at some point,you incur a cost equal to cutting wood
 *  + (cost required for cutting sub woods).
 *  
 *  Optimize cutting of wood such that cost is minimum as possible.
 *  Input;[0,20,30,70,100].
 *
 */
public class MinimizeCutOfWood {
	
	public static void mimimizeCutOfWood(Integer[] points) {
		System.out.println("=============Points Array=================");
		ArrayUtilities.printSingleDimensionArray(points);
		Info[][] infoArray = new Info[points.length][points.length];
		Info infoLengthItr = null;
		for(int i=0;i<points.length-1;i++) {
			infoLengthItr = new Info();
			infoLengthItr.cost = 0;
			infoArray[i][i+1] = infoLengthItr;
		}
		System.out.println("=============Info Array=================");
		for(int lengthItr=2;lengthItr<points.length;lengthItr++) {
			for(int i=0;i<points.length-lengthItr;i++) {
				infoLengthItr = new Info();
				infoArray[i][i+lengthItr] = infoLengthItr;
				int costItr = 0;
				int optimalSplit = -1;
				int optimalCost = Integer.MAX_VALUE;
				int endIndex = i + lengthItr;
				for(int j=i+1;j<endIndex;j++) {
					int leftCost = infoArray[i][j].cost;
					int rightCost = infoArray[j][endIndex].cost;
					costItr = leftCost + rightCost;
					if(costItr < optimalCost) {
						optimalCost = costItr;
						optimalSplit = j;
					}
				}
				infoLengthItr.cost = optimalCost + points[i+lengthItr] - points[i];
				infoLengthItr.splitIndex = optimalSplit;
			}
			
		}
		ArrayUtilities.printDoubleDimensionalArrayNeatly(infoArray, 15);
		System.out.println();
		System.out.println("The minimum cost of cutting wood is "+infoArray[0][points.length -1].cost);
		System.out.println("Cut the wood as follows");
		printHowToCutWood(infoArray,0,points.length -1,points);
	}
	
	private static void printHowToCutWood(Info[][] infoArray, int startIdx, int endIdx,Integer[] points) {
		int splitIndex = infoArray[startIdx][endIdx].splitIndex;
		if(splitIndex != -1) {
			System.out.println("Cut at "+points[splitIndex]);
			printHowToCutWood(infoArray, startIdx, splitIndex, points);
			printHowToCutWood(infoArray, splitIndex, endIdx, points);
		}
	}

	private static class Info {
		
		private int cost = 0;
		//-1 means dont need to split again
		private int splitIndex = -1;
		
		@Override
		public String toString() {
			return "[c="+cost+",s="+splitIndex+"]";
		}
		
	}
	
	public static void main(String[] args) {
		mimimizeCutOfWood(new Integer[]{0,20,30,70,100});
	}
}
