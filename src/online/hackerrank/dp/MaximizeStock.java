package online.hackerrank.dp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.FileUtils;
/**
 * https://www.hackerrank.com/challenges/stockmax
 * 
 * Problem Statement

Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next N days.

Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?
 * @author user
 *
 */
public class MaximizeStock {
	public static void main(String[] args) {
		/*Integer[] stocks = new Integer[]{1, 3, 1, 2};
		int maxProfit = getMaxProfit(stocks);
		System.out.println(maxProfit);*/
		boolean readFromFile = true;
		Scanner scanner = null;
		if(readFromFile) {
			File file = FileUtils.getFile("maxStocksTC.txt", MaximizeStock.class);
			try {
				scanner = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			scanner = new Scanner(System.in);
		}
		int testCases = scanner.nextInt();
		for(int i=1;i<=testCases;i++) {
			int size = scanner.nextInt();
			Integer[] stocks = new Integer[size];
			for(int j=0;j<size;j++) {
				stocks[j] = scanner.nextInt();
			}
			long maxProfit = getMaxProfit(stocks);
			System.out.println(maxProfit);
		}
		
	}
	
	public static long getMaxProfit(Integer []stocks) {
		long profit = 0;
		int max = stocks[stocks.length-1];
		int stocksItr;
		for (int i = stocks.length-2; i >=0; i--) {
			stocksItr = stocks[i];
			if(stocksItr > max) {
				max = stocksItr;
			} else {
				profit = profit + max - stocksItr;
			}
		}
		return profit;
	}
}
