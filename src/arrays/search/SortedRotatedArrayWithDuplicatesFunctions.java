package arrays.search;

import java.util.Arrays;


/**
 * A sorted array can be rotated clockwise from 0 times to length -1 times.
 * <br>
 * So a sorted array [1,2,3,4,5,6]'s rotations can be
 * <br>
 * 1) [1,2,3,4,5,6] - no rotations at all<br>
 * 2) [6,1,2,3,4,5] - 1 rotation<br>
 * 3) [5,6,1,2,3,4] - 2 rotation<br>
 * 4) [3,4,5,6,1,2] - 4 rotation<br>
 * 5) [2,3,4,5,6,1] - 5 rotation<br>
 * Note: Rotation 6 times(equal to array's length) is not possible.<p>
 * Array is rotated if and only if array[length -1] < array[0]. <p>
 * Minimum element in a rotated sorted array is one element whose previous element is
 * greater than it <br>
 * Maximum element in a rotated sorted array is one element whose next element is
 * smaller than it<p>
 * A sorted array can be rotated either less than length/2 or greater than length/2 times<br>
 * If array[high] > array[mid], then array is rotated less than length/2<br>
 * If array[high] < array[mid], then array is rotated more than length/2<br>
 * This property is useful in finding the min/max element which is the pivot in which
 * array is rotated.
 * <p>To search for an element in rotated sorted array, find pivot(say max element).
 * If element is greater than array[0] do a normal binary search in left of pivot, else
 * do a normal binary search in right of pivot
 * @author user
 * 
 * Not working
 *
 */
public class SortedRotatedArrayWithDuplicatesFunctions {
	public static void main(String[] args) {/*
		Integer[] noRotation = new Integer[]{1,2,3,4,5,6};
		Integer[] lessRotation = new Integer[]{5,6,1,2,3,4};
		Integer[] moreRotation = new Integer[]{3,4,5,6,1,2};
		
		System.out.println("Is Rotated");
		boolean isSortedArrayRotated = isSortedArrayRotated(noRotation);
		System.out.println(isSortedArrayRotated);
		isSortedArrayRotated = isSortedArrayRotated(lessRotation);
		System.out.println(isSortedArrayRotated);
		isSortedArrayRotated = isSortedArrayRotated(moreRotation);
		System.out.println(isSortedArrayRotated);
		System.out.println();
		
		System.out.println("Find Max Element");
		Integer maxEle = getMaxElementInSortedRotatedArray(noRotation);
		System.out.println(maxEle);
		maxEle = getMaxElementInSortedRotatedArray(lessRotation);
		System.out.println(maxEle);
		maxEle = getMaxElementInSortedRotatedArray(moreRotation);
		System.out.println(maxEle);
		System.out.println();
		
		System.out.println("Find Min Element");
		Integer minEle = getMinElementInSortedRotatedArray(noRotation);
		System.out.println(minEle);
		minEle = getMinElementInSortedRotatedArray(lessRotation);
		System.out.println(minEle);
		minEle = getMinElementInSortedRotatedArray(moreRotation);
		System.out.println(minEle);
		
		System.out.println("Search in less rotated array "+Arrays.toString(lessRotation));
		for (int i = 0; i <=7; i++) {
			System.out.println("Ele "+i+ " occurs at idx "+getIdxOfElementInSortedRotatedArray(lessRotation, i));
		}
		
		System.out.println("Search in more rotated array "+Arrays.toString(moreRotation));
		for (int i = 0; i <=7; i++) {
			System.out.println("Ele "+i+ " occurs at idx "+getIdxOfElementInSortedRotatedArray(moreRotation, i));
		}
	*/}
	/**
	 * Apply same conndition as without duplicates excepts for the last case arr[endIdx] > arr[mid]
	 * @param arr
	 * @param startIdx
	 * @param endIdx
	 * @return
	 */
	public static Integer getMinElementInSortedRotatedArray(Integer []arr, int startIdx, int endIdx) {
		if(startIdx == endIdx) {
			return arr[startIdx]; 
		} else if((endIdx - startIdx) == 1) {
			return Math.min(arr[startIdx],arr[endIdx]);
		} else {
			int mid = (startIdx + endIdx)/2;
			if(arr[mid-1] > arr[mid]) {
				//mid is smaller element
				return arr[mid];
			} else {
				if(arr[endIdx] > arr[mid]) {
					//array is rotated very less. So min element occurs in left part
					return getMinElementInSortedRotatedArray(arr, startIdx, mid-1);
				} else if(arr[endIdx] < arr[mid]){
					//array is rotated more. So min element occurs in right part
					return getMinElementInSortedRotatedArray(arr, mid+1, endIdx);
				} else {
					return Math.min(getMinElementInSortedRotatedArray(arr, startIdx, mid-1),
							getMinElementInSortedRotatedArray(arr, mid+1, endIdx));
				}
			}
		}
	}
}
