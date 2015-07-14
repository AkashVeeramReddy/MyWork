package arrays.search;

import utils.MyUtilities;

public class ArraySearching {
	
	/**
	 * Given an array of n numbers whose range is [0..n-1], find element occuring max number of times
	 * in O(n) time and O(1) extra space. You can modify the array
	 * 
	 * from Searching Chapter-Datastructures and algo made easy by karumachi.
	 * @param array
	 * @return
	 */
	public static int getElementOccuringMaxWithConstraints(Integer[] array) {
		int size = array.length;
		int ele;
		int eleModSize;
		for (int i = 0; i < array.length; i++) {
			ele = array[i];
			eleModSize = ele % size;
			//add size(n) to the index associated with the element
			array[eleModSize] = array[eleModSize] + size;
		}
		int eleOccuringMax = -1;
		int maxOccurence = 0;
		
		int itrEle;
		int itrOccurence;
		for (int i = 0; i < array.length; i++) {
			itrEle = i;
			//number of times itrEle occured.
			itrOccurence = array[i]/size;
			
			if(itrOccurence > maxOccurence) {
				maxOccurence = itrOccurence;
				eleOccuringMax = itrEle;
			}
			//restore the array back
			array[i] = array[i] % size;
		}
		//MyUtilities.printSingleDimensionArray(array);
		return eleOccuringMax;
	}
	/**
	 * You are given an unsorted array with both positive and negative elements.
	 *  You have to find the smallest positive number missing from the array in O(n) time using constant extra space.
	 *   You can modify the original array.
	 *   http://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
	 * @param array
	 * @return
	 */
	public static int findSmallestPositiveEleMissing(Integer[] array) {
		//seggregate + and - numbers.
		//numbers on left side of evenIdx are even nos
		int negIdx = 0;
		//numbers on right side of oddIdx are odd nos
		int posIdx = array.length - 1;
		int noAtNegIdx;
		boolean isNoAtNegIdxNeg;
		int noAtPosIdx;
		boolean isNoAtPosIdxPos;
		while(negIdx < posIdx) {
			noAtNegIdx = array[negIdx];
			noAtPosIdx= array[posIdx];
			
			isNoAtNegIdxNeg = !MyUtilities.isPositive(noAtNegIdx);
			isNoAtPosIdxPos = MyUtilities.isPositive(noAtPosIdx);
			
			if(isNoAtNegIdxNeg && isNoAtPosIdxPos) {
				negIdx++;
				posIdx --;
			} else if(!isNoAtNegIdxNeg && !isNoAtPosIdxPos) {
				MyUtilities.swap(array, negIdx, posIdx);
				negIdx++;
				posIdx --;
			} else {
				if(isNoAtNegIdxNeg) {
					//both even
					negIdx++;
				} else {
					//both odd
					posIdx --;
				}
			}
		}
		int startPosIdx = posIdx + 1;
		for (int i = startPosIdx; i < array.length; i++) {
			noAtPosIdx = array[i];
			if(MyUtilities.isNegative(noAtPosIdx)) {
				noAtPosIdx = -noAtPosIdx;
			}
			int hashLocn = noAtPosIdx + startPosIdx -1;
			if(hashLocn < array.length) {
				int noAtHash = array[hashLocn];
				if(MyUtilities.isPositive(hashLocn)) {
					array[hashLocn] = -noAtHash;
				}
			}
		}
		for (int i = startPosIdx,j=1; i < array.length; i++,j++) {
			noAtPosIdx = array[i];
			if(MyUtilities.isPositive(noAtPosIdx)) {
				return j;
			}
		}
		MyUtilities.printSingleDimensionArray(array);
		return -1;
	}
	public static void main(String[] args) {
		Integer [] array = new Integer[]{ 2, 3, -7, 6, 8, 1, -10, 15 };
		int findSmallestPositiveEleMissing = findSmallestPositiveEleMissing(array);
		System.out.println(findSmallestPositiveEleMissing);
	}
	
}
