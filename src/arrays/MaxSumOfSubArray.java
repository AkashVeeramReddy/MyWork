package arrays;

import divide_conquer.IndexOfFirstNonSortedElementInSortedRotatedArray;


public class MaxSumOfSubArray {
	
	/**
	 * int array[]={-1,-9,8,11,-4};
	 * returns 8 + 11;
	 */
	
	public static int calculateMaxSumOfSubArray(int [] array) {
		return calculateMaxSumOfSubArray(array, 0).maxSumOfArray;
	}
	
	public static InfoForCalculatingMaxSumOfSubArray calculateMaxSumOfSubArray(int [] array,int idx) {
		int noAtIdx = array[idx];
		if(idx == array.length -1) {
			InfoForCalculatingMaxSumOfSubArray infoForEnd = new InfoForCalculatingMaxSumOfSubArray();
			if(noAtIdx > 0) {
				infoForEnd.startIdx = idx;
				infoForEnd.endIdx = idx;
				infoForEnd.maxSumOfArray = noAtIdx;
				infoForEnd.sumFromEndIdx = noAtIdx;
			}
			return infoForEnd;
		}
		InfoForCalculatingMaxSumOfSubArray calculateSize = calculateMaxSumOfSubArray(array,idx+1);
//		calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + i;
		if(noAtIdx > 0) {
			/**
			 * sub array whose sum is positive starts from next index
			 */
			if(calculateSize.startIdx == (idx + 1)) {
				calculateSize.startIdx = idx;
				calculateSize.maxSumOfArray = calculateSize.maxSumOfArray + noAtIdx;
				calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
				return calculateSize;
			}
			/**
			 * Till now there is no sub array whose sum is positive
			 */
			if(calculateSize.maxSumOfArray == 0) {
				calculateSize.maxSumOfArray = calculateSize.sumFromEndIdx = noAtIdx;
				calculateSize.startIdx = calculateSize.endIdx = idx;
				return calculateSize;
			}
			/**
			 * sumOfMiddleElements will be definitely negative
			 */
			int sumOfMiddleElements = calculateSize.sumFromEndIdx - calculateSize.maxSumOfArray;
			if(noAtIdx + sumOfMiddleElements > 0) {
				calculateSize.startIdx = idx;
				calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
				calculateSize.maxSumOfArray = calculateSize.maxSumOfArray + noAtIdx + sumOfMiddleElements;
				return calculateSize;
			} else if(noAtIdx + sumOfMiddleElements < 0) {
				if(noAtIdx > calculateSize.maxSumOfArray) {
					calculateSize.startIdx = idx;
					calculateSize.endIdx = idx;
					calculateSize.maxSumOfArray = noAtIdx;
					calculateSize.sumFromEndIdx = noAtIdx;
				} else {
					calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
				}
				return calculateSize;
			}
		} else {
			if(calculateSize.sumFromEndIdx >0) {
				calculateSize.sumFromEndIdx = calculateSize.sumFromEndIdx + noAtIdx;
			}
		}
		return calculateSize;
	}
	
	public static class InfoForCalculatingMaxSumOfSubArray {
		public int startIdx = -1;
		public int endIdx = -1;
		public int sumFromEndIdx = 0;
		public int maxSumOfArray = 0;
	}
	
	public static void main(String[] args) {
		Integer [] array = {0,1,2,3,4,5,6,7,8,9};
		int indexOfFirstNonElement = IndexOfFirstNonSortedElementInSortedRotatedArray.getIndexOfFirstNonSortedElementInSortedRotatedArray(array);
		System.out.println();
	}
	
}
