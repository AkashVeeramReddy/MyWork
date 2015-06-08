package divide_conquer;

import utils.MyUtilities;

public class MaxEleInIncreasingDecreasingArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * 
		Given an array of integers which is initially increasing and then decreasing, find the maximum value in the array.
		
		Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}
		Output: 500
		
		Input: arr[] = {1, 3, 50, 10, 9, 7, 6}
		Output: 50
		
		Corner case (No decreasing part)
		Input: arr[] = {10, 20, 30, 40, 50}
		Output: 50
		
		Corner case (No increasing part)
		Input: arr[] = {120, 100, 80, 20, 0}
		Output: 120
	 * 
	 * @param array which is first increasing and then decreasing.
	 * @return MaxEleInIncreasingDecreasingArray
	 */
	public static <K extends Comparable<? super K>> K getMaxEleInIncreasingDecreasingArray
		(K[] array) {
		return getMaxEleInIncreasingDecreasingArray(array, 0, array.length);
	}
	
	private static <K extends Comparable<? super K>> K getMaxEleInIncreasingDecreasingArray
		(K[] array,int startIdx,int endIdx) {
		if(startIdx ==  endIdx) {
			return array[startIdx];
		} else if((endIdx - startIdx) == 1 ) {
			return MyUtilities.getMaxElement(array[startIdx],array[endIdx]);
		} else {
			K maxElement = null;
			int midIdx = (startIdx + endIdx) /2;
			K midEle = array[midIdx];
			K startEle = array[startIdx];
			K endEle = array[endIdx];
			K midEleNext = array[midIdx+1];
			K midElePrev = array[midIdx-1];
			
			int midCompareToNext = midEle.compareTo(midEleNext);
			int midCompareToPrev = midEle.compareTo(midElePrev);
			
			if((midCompareToNext == 1) && (midCompareToPrev == 1)) {
				//midEle greater than both
				return midEle;
			} else if((midCompareToNext == 1) && (midCompareToPrev == -1)) {
				//  midEle > midEleNext, midEle < midElePrev
				return getMaxEleInIncreasingDecreasingArray(array, startIdx, midIdx -1);
			} else {
				// midElePrev < midEle < midEleNext
				return getMaxEleInIncreasingDecreasingArray(array, midIdx + 1, endIdx);
			}
		}
		
	}

}
