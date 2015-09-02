package dynamicprogramming;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-
 * most-twice/
 * 
 * Maximum profit by buying and selling a share at most twice In a daily share
 * trading, a buyer buys shares in the morning and sells it on same day. If the
 * trader is allowed to make at most 2 transactions in a day, where as second
 * transaction can only start after first one is complete
 * (Sell->buy->sell->buy). Given stock prices throughout day, find out maximum
 * profit that a share trader could have made.
 * 
 * Examples:
 * 
 * Input: price[] = {10, 22, 5, 75, 65, 80} Output: 87 Trader earns 87 as sum of
 * 12 and 75 Buy at price 10, sell at 22, buy at 5 and sell at 80
 * 
 * @author user
 * 
 */
public class MaximiseStockTwice {
	public static void main(String[] args) {
		Integer stock[] = new Integer[]{100, 30, 15, 10, 8, 25, 80};
		System.out.println(getProfit(stock));
	}
	
	public static int getProfit(Integer[] stocks) {
		int length = stocks.length;
		//profitOneTransaction[i] - stock was brought at stocks[i] and sold later
		Integer[] profitOneTransaction = new Integer[length];
		int maximum = Integer.MIN_VALUE;
		int itr;
		for(int i=length-1;i>=0;i--) {
			itr = stocks[i];
			if(itr < maximum) {
				profitOneTransaction[i] = maximum - itr;
			} else {
				//itr >= maximum
				maximum = itr;
				profitOneTransaction[i] = 0;
			}
		}
		//maxProfitForOneTransaction[i] - max profit till ith day for 0 or one transaction
		Integer[] maxProfitForOneTransaction = new Integer[length];
		int minimum = Integer.MAX_VALUE;
		int itr1;
		for(int i=0;i<length;i++) {
			itr1 = stocks[i];
			if(itr1 >= minimum) {
				maxProfitForOneTransaction[i] = Math.max(maxProfitForOneTransaction[i-1], itr1-minimum);
			} else {
				//itr >= maximum
				minimum = itr1;
				maxProfitForOneTransaction[i] = (i==0)?0:maxProfitForOneTransaction[i-1];
			}
		}
		//System.out.println(Arrays.toString(maxProfitForOneTransaction));
		int netProfit = 0;
		for (int i = 0; i < length; i++) {
			netProfit = Math.max(netProfit, maxProfitForOneTransaction[i] +
					profitOneTransaction[i]);
		}
		return netProfit;
	}
}
