package dynamicprogramming;

import java.util.Iterator;

public class MaxMin2 {
	/**
	 * http://www.geeksforgeeks.org/remove-minimum-elements-either-side-2min-max/
	 * Remove minimum elements from either side such that 2*min becomes more than max
		Given an unsorted array, trim the array such that twice of minimum is greater than maximum in the trimmed array. Elements should be removed either end of the array.
		
		Number of removals should be minimum.
		
		Examples:
		
		arr[] = {4, 5, 100, 9, 10, 11, 12, 15, 200}
		Output: 4
		We need to remove 4 elements (4, 5, 100, 200)
so that 2*min becomes more than max.
	 * @param args
	 */
	public static void main(String[] args) {
		Integer [] array = new Integer[]{4,1,3};
		remove2MinGtrMax(array);
	}
	
	public static void remove2MinGtrMax(Integer [] array) {
		int length = array.length;
		Info infoArr[][] = new Info [length][length];
		int end;
		for(int i=0; i < length; i++) {
			Info info = new Info();
			info.max = array[i];
			info.min = array[i];
			infoArr[i][i] = info;
			
		}
		int startEle = 0;
		int endEle = 0;
		int leftMax = 0;
		int leftMin = 0;
		
		int rightMax = 0;
		int rightMin = 0;
		for(int lenItr = 2;lenItr <= length;lenItr++) {
			for(int start = 0; start <= length - lenItr ; start++) {
				end = start + lenItr - 1;
				Info leftInfo = infoArr[start][end-1];
				Info rightInfo = infoArr[start+1][end];
				startEle = array[start];
				endEle = array[end];
				boolean remove = false;
				boolean left = false;
				/**
				 * is any element will have to be removed from left to calculate optimal
				 */
				boolean isRemovedFromLeft = false;
				/**
				 * is any element will have to be removed from right to calculate optimal
				 */
				boolean isRemovedFromRight = false;
				if(startEle > rightInfo.max) {
					leftMax = startEle;
				} else {
					leftMax = rightInfo.max;
				}
				if(startEle < rightInfo.min) {
					leftMin = startEle;
				} else {
					leftMin = rightInfo.min;
				}
				if(!(2*rightInfo.min > leftMax)) {
					isRemovedFromLeft = true;
				} else {
					isRemovedFromLeft = false;
				}
				if(!(2*leftMin > rightInfo.max)) {
					isRemovedFromLeft = true;
				} else {
					isRemovedFromLeft = false;
				}
				if(endEle > leftInfo.max) {
					rightMax = endEle;
				} else {
					rightMax = leftInfo.max;
				}
				if(endEle < leftInfo.min) {
					rightMin = endEle;
				} else {
					rightMax = leftInfo.min;
				}
				if(!(2*leftInfo.min > rightMax)) {
					isRemovedFromRight = true;
				} else {
					isRemovedFromRight = false;
				}
				if(!(2*rightMin > leftInfo.max)) {
					isRemovedFromRight = true;
				} else {
					isRemovedFromRight = false;
				}
				Info info = new Info();
				//nof of removes if start has been removed
				int leftRemove;
				//nof of removes if end has been removed
				int rightRemove;
				boolean isAnyLeftEleRemovedForLeftRemove;
				if(isRemovedFromLeft) {
					//whether element in start has a chance to be removed
					leftRemove = rightInfo.noOfRemove + 1;
					isAnyLeftEleRemovedForLeftRemove = true;
				} else {
					if(rightInfo.isAnyLeftEleRemoved) {
						leftRemove = rightInfo.noOfRemove + 1;
						isAnyLeftEleRemovedForLeftRemove = true;
					} else {
						leftRemove = rightInfo.noOfRemove;
						isAnyLeftEleRemovedForLeftRemove = false;
					}
				}
				boolean isAnyRightEleRemovedForRightRemove;
				if(isRemovedFromRight) {
					//whether element in right has a chance to be removed
					rightRemove = leftInfo.noOfRemove + 1;
					isAnyRightEleRemovedForRightRemove = true;
				} else {
					if(leftInfo.isAnyRightEleRemoved) {
						rightRemove = leftInfo.noOfRemove + 1;
						isAnyRightEleRemovedForRightRemove = true;
					} else {
						rightRemove = leftInfo.noOfRemove;
						isAnyRightEleRemovedForRightRemove = false;
					}
				}
				info.max = Math.max(leftMax, rightMax);
				info.min = Math.min(leftMin, rightMin);;
				if(leftRemove > rightRemove) {
					//rightRemove optimal
					info.noOfRemove = rightRemove;
					info.left = false;
					info.isAnyLeftEleRemoved = leftInfo.isAnyLeftEleRemoved;
					info.isAnyRightEleRemoved = isAnyRightEleRemovedForRightRemove;
				} else {
					//leftRemove optimal
					info.noOfRemove = leftRemove;
					info.left = true;
					info.isAnyLeftEleRemoved = isAnyLeftEleRemovedForLeftRemove;
					info.isAnyRightEleRemoved = rightInfo.isAnyRightEleRemoved;
				}
				infoArr[start][end] = info;
			}
		}
		System.out.println(infoArr[0][length-1].noOfRemove);
	}
	
	public static class Info {
		public int max;
		public int min;
		/**
		 * whether any element has been removed when calculating [i..j] from subarray 
		 * [i..j-1] or [i+1..j]
		 */
		public boolean remove = false;
		/**
		 * if true [i..j] came from [i..j-1] 
		 * else [i..j] came from [i+1..j] 
		 */
		public boolean left = false;
		/**
		 * is any element will have to be removed from left to calculate optimal
		 */
		public boolean isAnyLeftEleRemoved = false;
		/**
		 * is any element will have to be removed from right to calculate optimal
		 */
		public boolean isAnyRightEleRemoved = false;
		/**
		 * no of ele to be removed to calculate optimal
		 */
		public int noOfRemove;
		@Override
		public String toString() {
			return "[max=" + max + ",min=" + min + ",r=" + remove
					+ ",l=" + left + "]";
		}
		
	}
}
