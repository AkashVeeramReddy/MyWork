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
 */
public class SortedRotatedArrayFunctions {
	public static void main(String[] args) {
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
		Integer maxEle = getMaxElement(noRotation);
		System.out.println(maxEle);
		maxEle = getMaxElement(lessRotation);
		System.out.println(maxEle);
		maxEle = getMaxElement(moreRotation);
		System.out.println(maxEle);
		System.out.println();
		
		System.out.println("Find Min Element");
		Integer minEle = getMinElement(noRotation);
		System.out.println(minEle);
		minEle = getMinElement(lessRotation);
		System.out.println(minEle);
		minEle = getMinElement(moreRotation);
		System.out.println(minEle);
		
		System.out.println("Search in less rotated array "+Arrays.toString(lessRotation));
		for (int i = 0; i <=7; i++) {
			System.out.println("Ele "+i+ " occurs at idx "+getIdxOfElement(lessRotation, i));
		}
		
		System.out.println("Search in more rotated array "+Arrays.toString(moreRotation));
		for (int i = 0; i <=7; i++) {
			System.out.println("Ele "+i+ " occurs at idx "+getIdxOfElement(moreRotation, i));
		}
	}
	
	public static boolean isSortedArrayRotated(Integer []arr) {
		return arr[0] > arr[arr.length-1];
	}
	
	public static Integer getMaxElement(Integer []arr) {
		boolean sortedArrayRotated = isSortedArrayRotated(arr);
		if(!sortedArrayRotated) {
			return arr[arr.length-1];
		}
		int startIdx = 0;
		int endIdx = arr.length-1;
		while(startIdx<=endIdx) {
			if(startIdx == endIdx) {
				//array of length 1
				return arr[startIdx];
			} else if(endIdx - startIdx == 1) {
				//array of length 2
				return Math.max(arr[startIdx],arr[endIdx]);
			} else {
				int mid = (startIdx + endIdx)/2;
				if(arr[mid+1] < arr[mid]) {
					//arr[mid] is max element
					return arr[mid];
				} else {
					if(arr[endIdx] > arr[mid]) {
						//array is rotated very less. So max element occurs in left part
						endIdx = mid-1;
					} else {
						//array is rotated more. So max element occurs in right part.
						startIdx = mid+1;
					}
				}
			}
		}
		return Integer.MIN_VALUE;
	}
	
	public static Integer getMinElement(Integer []arr) {
		boolean sortedArrayRotated = isSortedArrayRotated(arr);
		if(!sortedArrayRotated) {
			return arr[0];
		}
		int startIdx = 0;
		int endIdx = arr.length-1;
		while(startIdx<=endIdx) {
			if(startIdx == endIdx) {
				//array of length 1
				return arr[startIdx];
			} else if(endIdx - startIdx == 1) {
				//array of length 2
				return Math.min(arr[startIdx],arr[endIdx]);
			} else {
				int mid = (startIdx + endIdx)/2;
				if(arr[mid-1] > arr[mid]) {
					//mid is smaller element
					return arr[mid];
				} else {
					if(arr[endIdx] > arr[mid]) {
						//array is rotated very less. So min element occurs in left part
						endIdx = mid-1;
					} else {
						//array is rotated more. So min element occurs in right part.
						startIdx = mid+1;
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	
	/**
	 * Two approaches<br>
	 * 1) First find idx of pivot(max element).
	 * <br>
	 * If pivot == eleToFind return id 
	 * <br>
	 * If arr[0] > eleToFind, search to left of pivot, else right of pivot.<br>
	 * 2) Second approach is given below
	 * @param arr
	 * @param eleToFind
	 * @return index if it occurs else -1
	 */
	public static int getIdxOfElement(Integer[] arr, int eleToFind) {
		if(!isSortedArrayRotated(arr)) {
			return Arrays.binarySearch(arr, eleToFind);
		} else {
			int startIdx = 0;
			int endIdx = arr.length - 1;
			while(startIdx <= endIdx) {
				if(startIdx == endIdx) {
					//length 1
					if(arr[startIdx] == eleToFind) {
						return startIdx;
					} else {
						return -1;
					}
				} else if(endIdx - startIdx == 1) {
					//length 2
					if(arr[startIdx] == eleToFind) {
						return startIdx;
					}
					if(arr[endIdx] == eleToFind) {
						return endIdx;
					}
					return -1;
				} else {
					int mid = (startIdx + endIdx)/2;
					if(arr[mid] == eleToFind) {
						return mid;
					} else {
						if(arr[endIdx] > arr[mid]) {
							//less rotated
							if(eleToFind > arr[mid] && eleToFind < arr[endIdx]) {
								//search in right half
								startIdx = mid+1;
							} else {
								//search in left half
								endIdx = mid-1;
							}
						} else {
							//more rotated
							if(eleToFind < arr[mid] && eleToFind > arr[startIdx]) {
								//search in left half
								endIdx = mid - 1;
							} else {
								//search in right half
								startIdx = mid+1;
							}
						}
					}
				}
			}
			
		}
		return -1;
	}
}
