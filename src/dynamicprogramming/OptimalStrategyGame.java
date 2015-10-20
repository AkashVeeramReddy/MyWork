package dynamicprogramming;
/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
 * Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even. We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin. Determine the maximum possible amount of money we can definitely win if we move first.

Note: The opponent is as clever as the user.

Let us understand the problem with few examples:

    1. 5, 3, 7, 10 : The user collects maximum value as 15(10 + 5)

    2. 8, 15, 3, 7 : The user collects maximum value as 22(7 + 15)
 * @author user
 *
 */
public class OptimalStrategyGame {
	public static void main(String[] args) {
		int arr1[] = {8, 15, 3, 7};
		long maxAmount = getMaxAmount(arr1);
		System.out.println(maxAmount);
	 
		int arr2[] = {2, 2, 2, 2};
		maxAmount = getMaxAmount(arr2);
		System.out.println(maxAmount);
	 
		int arr3[] = {20, 30, 2, 2, 2, 10};
	    maxAmount = getMaxAmount(arr3);
		System.out.println(maxAmount);
	}
	
	public static long getMaxAmount(int []coins) {
		int size = coins.length;
		long sum[] = new long[size];
		long sumItr = 0;
		for (int i = 0; i < size; i++) {
			sumItr = sumItr + coins[i];
			sum[i] = sumItr;
		}
		long arr[][] = new long[size][size];
		for (int j = 0; j < size; j++) {
			arr[j][j] = coins[j];
		}
		int end;
		long startPick,endPick;
		for (int lenItr = 2	; lenItr <=size; lenItr++) {
			for (int start = 0; start <= size - lenItr; start++) {
				end = start + lenItr - 1;
				//pick coin at start
				long sumRight = sum[end] - sum[start];
				startPick = coins[start] + sumRight - arr[start+1][end];
				//pick coin at end
				long sumLeft = sum[end-1] - (start==0?0:sum[start-1]);
				endPick = coins[end] + sumLeft - arr[start][end-1];
				
				arr[start][end] = Math.max(startPick, endPick); 
			}
		}
		return arr[0][size-1];
	}
}
