package dynamicprogramming;

import utils.ArrayUtilities;

/**
 *  http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 *	The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence
 *	 of a given sequence such that all elements of the subsequence are sorted in increasing order.
 *	 For example, length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is
 *	 {10, 22, 33, 50, 60, 80}.
 */


public class LongestIncreasingSequence {
	
	public static final Integer MIN_VALUE = Integer.MIN_VALUE;
	public static final Integer MAX_VALUE = Integer.MAX_VALUE;
	
	public static void printLongestIncreasingSequence(int length) {
		Integer[] intArray = new Integer[length];
		for(int i = 0;i<length;i++) {
			intArray[i] = (int) Math.ceil(Math.random() * 100);
		}
		System.out.println("======================Integer Array================================");
		ArrayUtilities.printSingleDimensionArray(intArray);
		Info [][] infoArray = new Info [length][length];
		for(int i =0;i<length;i++) {
			Info info = new Info();
			info.index = i;
			info.length = 1;
			info.minValue = intArray[i];
			info.maxValue = intArray[i];
			info.direction = 0;
			infoArray[i][i] = info;
		}
		//first calculate for lengths
		for(int i =2;i<=length;i++) {
			for(int j =0;j<=(length-i);j++) {
				int minValueItr = 0;
				int maxValueItr = 0;
				int maxIndex = -1;
				int maxLengthOfSubArray = 0;
				int maxDirection = 0;
				for(int k = j;k<(j+i);k++) {
					//Calculate length of subarray[j..k-1]
					int direction = 0;
					int valueAtK = intArray[k];
					int upperLimitForLeftSubArray = k-1;
					boolean isEmptyLeftArray = true;
					boolean isEmptyRightArray = true;
					int lengthForLeftArray = 0;
					int lengthForRightArray = 0;
					int maxValueForLeftArray = MIN_VALUE;
					int minValueForRightArray = MAX_VALUE;
					Info infoForLeftArray = null;
					Info infoForRightArray = null;
					if(upperLimitForLeftSubArray >=j) {
						isEmptyLeftArray = false;
						infoForLeftArray = infoArray[j][upperLimitForLeftSubArray];
						lengthForLeftArray = infoForLeftArray.length;
						maxValueForLeftArray = infoForLeftArray.maxValue;
					}
					//Calculate length of subarray[k+1..j+i-1]
					int lowerLimitForRightArray = k+1;
					if(lowerLimitForRightArray <=(j+i-1)) {
						isEmptyRightArray = false;
						infoForRightArray = infoArray[lowerLimitForRightArray][j+i-1];
						lengthForRightArray = infoForRightArray.length;
						minValueForRightArray = infoForRightArray.minValue;
					}
					/*if(!isEmptyLeftArray) {
						if(!isEmptyRightArray) {
							direction = 3;
						} else {
							direction = 2;
						}
					} else {
						if(!isEmptyRightArray) {
							direction = 1;
						} else {
							direction = 0;
						}
					}*/
					int lengthOfSubArrayItr = 0;
					if(valueAtK > maxValueForLeftArray) {
						if(valueAtK < minValueForRightArray) {
							direction = 3;
							lengthOfSubArrayItr = lengthForLeftArray + lengthForRightArray + 1;
						} else {
							direction = 2;
							lengthOfSubArrayItr = lengthForLeftArray + 1;
						}
					} else {
						if(valueAtK < minValueForRightArray) {
							direction = 1;
							lengthOfSubArrayItr = lengthForRightArray + 1;
						} else {
							lengthOfSubArrayItr = 1;
							direction = 0;
						}
					}
					if(lengthOfSubArrayItr > maxLengthOfSubArray) {
						maxDirection = direction; 
						maxIndex = k;
						maxLengthOfSubArray = lengthOfSubArrayItr;
						if(infoForLeftArray != null) {
							minValueItr = infoForLeftArray.minValue;
							if(infoForRightArray != null) {
								maxValueItr = infoForRightArray.maxValue;
							} else {
								maxValueItr = valueAtK;
							}
						} else {
							minValueItr = valueAtK;
							if(infoForRightArray != null) {
								maxValueItr = infoForRightArray.maxValue;
							} else {
								maxValueItr = valueAtK;
							}
						}
					}
				}
				Info infoItr = new Info();
				infoItr.index = maxIndex;
				infoItr.length = maxLengthOfSubArray;
				infoItr.minValue = minValueItr;
				infoItr.maxValue = maxValueItr;
				infoItr.direction = maxDirection;
				infoArray[j][j+i-1] = infoItr;
			}
		}
		System.out.println("======================Info Array================================");
		ArrayUtilities.printDoubleDimensionalArrayNeatly(infoArray, 30);
		System.out.println("The longest sub sequence is ");
		printInfoArray(infoArray,0,length-1,intArray);
	}
	
	
	private static void printInfoArray(Info[][] infoArray, int start, int end,Integer [] intArray) {
		if(start <= end) {
			Info info = infoArray[start][end];
			int direction = info.direction;
			int index = info.index;
			Integer splitInteger = intArray[index];
			switch (direction) {
			case 0:
				System.out.print(splitInteger+ " ");
				break;
			case 1:
				System.out.print(splitInteger+ " ");
				printInfoArray(infoArray, index + 1, end, intArray);
				break;
			case 2:
				printInfoArray(infoArray, start, index-1, intArray);
				System.out.print(splitInteger+ " ");
				break;
			case 3:
				printInfoArray(infoArray, start, index-1, intArray);
				System.out.print(splitInteger+ " ");
				printInfoArray(infoArray, index + 1, end, intArray);
				break;
			default:
				break;
			}
		}
	}


	private static class Info {
		
		private int index;
		private int length;
		private int minValue;
		private int maxValue;
		/**
		 * 0 both subarray empty
		 * 1 left subarray empty
		 * 2 right subarray empty
		 * 3 both sub array non empty
		 */
		private int direction = -1;
		
		@Override
		public String toString() {
			return "[i=" + index + ",l=" + length
					+ ",min=" + minValue + ",max=" + maxValue +
					",d=" + direction +"]";
		}
		
		
	}
	
	public static void main(String[] args) {
		printLongestIncreasingSequence(4);
	}
}
