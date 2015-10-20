package dynamicprogramming;

import utils.ArrayUtilities;

/**
 * Given a sequence of words, and a limit on the number of characters that can be put in
 *  one line (line width). Put line breaks in the given sequence such that the lines are
 *   printed neatly. Assume that the length of each word is smaller than the line width.

The word processors like MS Word do task of placing line breaks. 
The idea is to have balanced lines. In other words, not have few lines with lots of 
extra spaces and some lines with small amount of extra spaces.

The extra spaces includes spaces put at the end of every line except the last one.  
The problem is to minimize the following total cost.
 Cost of a line = (Number of extra spaces in the line)^3
 Total Cost = Sum of costs for all lines

For example, consider the following string and line width M = 15
 "Geeks for Geeks presents word wrap problem" 
     
Following is the optimized arrangement of words in 3 lines
Geeks for Geeks
presents word
wrap problem 

The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively. 
So optimal value of total cost is 0 + 2*2 + 3*3 = 13
Please note that the total cost function is not sum of extra spaces, but sum of cubes
 (or square is also used) of extra spaces. The idea behind this cost function is to balance
  the spaces among lines. For example, consider the following two arrangement of same set of
   words:

1) There are 3 lines. One line has 3 extra spaces and all other lines have 0 extra spaces. 
Total extra spaces = 3 + 0 + 0 = 3. Total cost = 3*3*3 + 0*0*0 + 0*0*0 = 27.

2) There are 3 lines. Each of the 3 lines has one extra space.
 Total extra spaces = 1 + 1 + 1 = 3. Total cost = 1*1*1 + 1*1*1 + 1*1*1 = 3.

Total extra spaces are 3 in both scenarios, but second arrangement should be preferred
 because extra spaces are balanced in all three lines. The cost function with cubic sum serves the purpose because the value of total cost in second scenario is less.
 * 
 * Method 1 (Greedy Solution)
The greedy solution is to place as many words as possible in the first line. 
Then do the same thing for the second line and so on until all words are placed.
 This solution gives optimal solution for many cases, but doesn’t give optimal solution in all cases.
  For example, consider the following string “aaa bb cc ddddd” and line width as 6. 
  Greedy method will produce following output.

aaa bb 
cc 
ddddd
Extra spaces in the above 3 lines are 0, 4 and 1 respectively. So total cost is 0 + 64 + 1 = 65.

But the above solution is not the best solution. Following arrangement has more balanced spaces. 
Therefore less value of total cost function.

aaa
bb cc
ddddd
Extra spaces in the above 3 lines are 3, 1 and 1 respectively. So total cost is 27 + 1 + 1 = 29.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 * 
 */
public class WordWrap {
	
	
	private static final int INFINITY = 9999;


	/**
	 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
	 * 
	 * 
	 */
	public static void wordWrap(String [] stringArray,int lineLength) {
		int length = stringArray.length;
		Integer extras[][] = new Integer[length][length];
		for(int i = 0; i < length;i++) {
			extras[i][i] = lineLength - stringArray[i].length();
			for(int j= i+1;j< length;j++) {
				extras[i][j] = extras[i][j-1] - stringArray[j].length();
			}
		}
		System.out.println("======================Extras Array====================");
		ArrayUtilities.printDoubleDimensionalArrayNeatly(extras, 20);
		//Compute least cost array
		Integer leastCost[][] = new Integer[length][length];
		for(int i = 0; i < length;i++) {
			for(int j= i;j< length;j++) {
				if(extras[i][j] < 0) {
					leastCost[i][j] = INFINITY;
				} else {
					if(j == length - 1) {
						leastCost[i][j] = 0;
					} else {
						leastCost[i][j] = (int) Math.pow(extras[i][j], 3);
					}
				}
			}
		}
		System.out.println("======================Least Cost Array====================");
		ArrayUtilities.printDoubleDimensionalArrayNeatly(leastCost, 20);
		//Compute Info array
		Info [] infoArray = new Info[length];
		for(int i = 0;i < length; i++) {
			int minIndex = -1;
			int minCost = INFINITY;
			int costOfPrev = 0;
			int sumOfCostItr = 0;
			for(int j = 0;j<=i;j++) {
				int leastCostIJ = leastCost[j][i];
				if(j > 0) {
					costOfPrev = infoArray[j-1].cost;
				}
				sumOfCostItr = leastCostIJ + costOfPrev;
				if(sumOfCostItr < minCost) {
					minCost = sumOfCostItr;
					minIndex = j;
				}
			}
			Info infoItr = new Info();
			infoItr.cost = minCost;
			infoItr.fromWhich = minIndex;
			infoArray[i] = infoItr;
		}
		System.out.println();
		System.out.println("The Wrapped words are as follows");
		printWordWrap(infoArray, stringArray, stringArray.length-1);
	}
	
	public static void printWordWrap(Info[] array,String []strArray,int length) {
		/*int startIndex = array[length].fromWhich;
		int k = 1;
		if(length == 0)
			k = 1;
		else {
			k = printWordWrap(array, strArray, startIndex) + 1;
		}*/
		int startIndex = array[length].fromWhich;
		if(length == 0) {
			
		} else {
			printWordWrap(array, strArray, startIndex-1);
		}
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = startIndex;i <= length;i++) {
			stringBuilder.append(strArray[i]);
			stringBuilder.append(" ");
		}	
		System.out.println(stringBuilder);
//		System.out.println("In line " + k + " Word(s) i");
//		return k;
		
	}
	
	private static class Info {
		
		private int cost = 0;
		private int fromWhich = -1;
		
		@Override
		public String toString() {
			return "[c=" + cost + ", fW=" + fromWhich + "]";
		}
		
	}
	
	public static void main(String[] args) {
		String [] array = new String[4];
		array[0] = "aaa";
		array[1] = "bb" ;
		array[2] = "cc" ;
		array[3] = "ddddd";
		wordWrap(array, 6);
	}
}
