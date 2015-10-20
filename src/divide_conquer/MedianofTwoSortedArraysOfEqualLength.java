package divide_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import utils.ArrayUtilities;

public class MedianofTwoSortedArraysOfEqualLength {

	public static void main(String[] args) {
		Integer array1[] = new Integer[4];
		Integer array2[] = new Integer[4];
		ArrayUtilities.populateIntegerArrayWithRandomNos(array1);
		ArrayUtilities.populateIntegerArrayWithRandomNos(array2);
		Arrays.sort(array1);
		Arrays.sort(array2);
		ArrayUtilities.printSingleDimensionArray(array1);
		ArrayUtilities.printSingleDimensionArray(array2);
		System.out.println(getMedianofTwoSortedArraysOfEqualLength(array1, array2));

	}
	
	/**
	 * Median of two sorted arrays
	Question: There are 2 sorted arrays A and B of size n each. Write an algorithm to find the median of the array obtained after merging the above 2 arrays(i.e. array of length 2n). The complexity should be O(log(n))

	Median: In probability theory and statistics, a median is described as the number separating the higher half of a sample, a population, or a probability distribution, from the lower half.
	The median of a finite list of numbers can be found by arranging all the numbers from lowest value to highest value and picking the middle one.

	 * @param array1
	 * @param array2
	 * @return - median
	 */
	public static double getMedianofTwoSortedArraysOfEqualLength(Integer[] array1,Integer[] array2) {
		return getMedianofTwoSortedArraysOfEqualLength(array1, array2,0,array1.length-1,0,array1.length-1);
	}
	
	public static double getMedianofTwoSortedArraysOfEqualLength(Integer[] array1,Integer[] array2,
			int startIdxArr1,int endIdxArr1,int startIdxArr2,int endIdxArr2) {
		int sizeOfArray1 = endIdxArr1 - startIdxArr1 + 1;
		int sizeofArray2 = sizeOfArray1;
		if(sizeOfArray1 == 1) {
			return (array1[startIdxArr1] + array2[startIdxArr2]) /2;
		} else if(sizeOfArray1 == 2) {
			List<Integer> integers = new ArrayList<Integer>();
			integers.add(array1[startIdxArr1]);
			integers.add(array1[endIdxArr1]);
			integers.add(array2[startIdxArr2]);
			integers.add(array2[endIdxArr2]);
			Collections.sort(integers);
			return ((double)integers.get(1) + integers.get(2))/2;
		} else {
			double medianArr1 = getMedianOfSortedArray(array1, startIdxArr1, endIdxArr1);
			double medianArr2 = getMedianOfSortedArray(array2, startIdxArr2, endIdxArr2);
			if(medianArr1 == medianArr2) {
				return medianArr1;
			} else if(medianArr1 > medianArr2){
				//find median of starting half of array 1 and ending half of array 2
				int newEndOfArray1 = startIdxArr1 + sizeOfArray1/2;
				int newStartOfArray2 = endIdxArr2 - sizeOfArray1/2;
				return getMedianofTwoSortedArraysOfEqualLength(array1, array2, startIdxArr1,
						newEndOfArray1, newStartOfArray2, endIdxArr2);
			} else {
				//medianArr1 < medianArr2
				//just reverse the call
				return getMedianofTwoSortedArraysOfEqualLength(array2, array1, startIdxArr2,
						endIdxArr2, startIdxArr1, endIdxArr1);
			}
		}
	}
	
	public static double getMedianOfSortedArray(Integer[] array, int startIdx,int endIdx) {
		int size = endIdx - startIdx + 1;
		if((size % 2) == 0) {
			int sum = (array[(startIdx + endIdx)/2] + array[(startIdx + endIdx)/2 + 1]);
			return ((double)sum/2);
		} else {
			return array[(startIdx + endIdx)/2];
		}
	}

}
