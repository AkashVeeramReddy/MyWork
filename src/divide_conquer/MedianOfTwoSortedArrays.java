package divide_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import utils.MyUtilities;
/**
 * TODO
 * @author nithin
 *
 */
public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		Integer array1[] = new Integer[4];
		Integer array2[] = new Integer[5];
		MyUtilities.populateIntegerArrayWithRandomNos(array1);
		MyUtilities.populateIntegerArrayWithRandomNos(array2);
		Arrays.sort(array1);
		Arrays.sort(array2);
		MyUtilities.printSingleDimensionArray(array1);
		MyUtilities.printSingleDimensionArray(array2);
		System.out.println(getMedianofTwoSortedArrays(array1, array2));

	}
	
	/**
	 * {@linkplain http://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/}
	 * 
	 * Median of two sorted arrays
	Question: There are 2 sorted arrays A and B of size n each. Write an algorithm to find the median of the array obtained after merging the above 2 arrays(i.e. array of length 2n). The complexity should be O(log(n))

	Median: In probability theory and statistics, a median is described as the number separating the higher half of a sample, a population, or a probability distribution, from the lower half.
	The median of a finite list of numbers can be found by arranging all the numbers from lowest value to highest value and picking the middle one.

	 * @param array1
	 * @param array2
	 * @return - median
	 */
	public static double getMedianofTwoSortedArrays(Integer[] array1,Integer[] array2) {
		if(array1.length > array2.length)
			return getMedianofTwoSortedArrays(array1, array2,0,array1.length-1,0,array2.length-1);
		else
			return getMedianofTwoSortedArrays(array2, array1,0,array2.length-1,0,array1.length-1);
	}
	
	public static double getMedianofTwoSortedArrays(Integer[] smallArr,Integer[] largeArr,
			int startIdxArr1,int endIdxArr1,int startIdxArr2,int endIdxArr2) {
		int sizeOfArray1 = endIdxArr1 - startIdxArr1 + 1;
		int sizeOfArray2 = endIdxArr2 - startIdxArr2 + 1;
		
		if(sizeOfArray1 <=2 && sizeOfArray2 <=2) {
			//brute force
			List<Integer> integers = new ArrayList<Integer>();
			for(int i = startIdxArr1;i<= endIdxArr1;i++) {
				integers.add(smallArr[i]);
			}
			for(int i = startIdxArr2;i<= endIdxArr2;i++) {
				integers.add(largeArr[i]);
			}
			Collections.sort(integers);
			Integer[] array = integers.toArray(new Integer[0]);
			return getMedianOfSortedArray(array, 0, array.length-1);
		} else if(sizeOfArray1 == 1) {
			double medianSmallArr = getMedianOfSortedArray(smallArr, startIdxArr1, endIdxArr1);
			double medianLargeArr = getMedianOfSortedArray(largeArr, startIdxArr2, endIdxArr2);
			if(medianLargeArr == medianSmallArr) {
				return medianSmallArr;
			} else if(medianSmallArr < medianLargeArr) {
				if(MyUtilities.isEven(sizeOfArray2)) {
					
				} else {
					
				}
			} else {
				//medianSmallArr > medianLargeArr
			}
			
		} else if(sizeOfArray1 == 2) {
			
		} else {
			double medianArr1 = getMedianOfSortedArray(smallArr, startIdxArr1, endIdxArr1);
			double medianArr2 = getMedianOfSortedArray(largeArr, startIdxArr2, endIdxArr2);
			if(medianArr1 == medianArr2) {
				return medianArr1;
			} else if(medianArr1 > medianArr2){
				//find median of starting half of array 1 and ending half of array 2
				int newEndOfArray1 = startIdxArr1 + sizeOfArray1/2;
				int newStartOfArray2 = endIdxArr2 - sizeOfArray2/2;
				return getMedianofTwoSortedArrays(smallArr, largeArr, startIdxArr1,
						newEndOfArray1, newStartOfArray2, endIdxArr2);
			} else {
				//medianArr1 < medianArr2
				//find median of ending half of array 1 and starting half of array 2
				int newStartOfArray1 = endIdxArr1 - sizeOfArray1/2;
				int newEndOfArray2 = startIdxArr2 + sizeOfArray2/2;
				return getMedianofTwoSortedArrays(smallArr, largeArr, newStartOfArray1,
						endIdxArr1, startIdxArr2, newEndOfArray2);
			}
		}
		return -1;
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
