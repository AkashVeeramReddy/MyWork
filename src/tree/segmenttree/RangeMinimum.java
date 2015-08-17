package tree.segmenttree;

import java.util.Arrays;
/**
 * http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
 * 
 * Segment Tree | Set 2 (Range Minimum Query)
We have introduced segment tree with a simple example in the previous post. In this post, Range Minimum Query problem is discussed as another example where Segment Tree can be used. Following is problem statement.

We have an array arr[0 . . . n-1]. We should be able to efficiently find the minimum value from index qs (query start) to qe (query end) where 0 <= qs <= qe <= n-1.
 The array is static (elements are not deleted and inserted during the series of queries).
 * @author user
 *
 */
public class RangeMinimum {

	protected Integer[] segmentTree;
	//no of elements(not in segment tree)
	protected int noOfElements;
	protected Integer[] actualArray;
	
	public RangeMinimum(Integer []array) {
		actualArray = Arrays.copyOf(array, array.length);
		noOfElements = array.length;
		//size reqd is 2*(2 ^ Ceil(log2(n))) - 1
		double logNoOfEle = log2(noOfElements);
		double ceil = Math.ceil(logNoOfEle);
		double pow = Math.pow(2, ceil);
		pow = pow*2;
		segmentTree = new Integer[(int)pow - 1];
		populateSegmentTree(array);
	}

	protected void populateSegmentTree(Integer[] array) {
		populateSegmentTree(array,0,array.length - 1,0);
	}
	
	protected int getMidPoint(int start,int end) {
		return start + (end - start)/2;
	}
	
	protected int getParentIdx(int idx) {
		return (idx + 1)/2 - 1;
	}
	
	protected int getLeftChildIdx(int idx) {
		return 2*idx + 1;
	}
	
	protected int getRightChildIdx(int idx) {
		return 2*idx + 2;
	}
	
	protected int populateSegmentTree(Integer[] array, int start, int end,int sumIdx) {
		if(start == end) {
			//leaf
			segmentTree[sumIdx] = array[start];
			return array[start];
		} else {
			int mid = getMidPoint(start, end);
			int leftSumIdx = getLeftChildIdx(sumIdx);
			int leftSum = populateSegmentTree(array, start, mid, leftSumIdx);
			
			int rightSumIdx = getRightChildIdx(sumIdx);
			int rightSum = populateSegmentTree(array, mid+1, end, rightSumIdx);
			
			segmentTree[sumIdx] = Math.min(leftSum, rightSum);
			return segmentTree[sumIdx];
		}
	}
	
	@Override
	public String toString() {
		return "Data = " + Arrays.toString(actualArray) + 
				" ST = " + Arrays.toString(segmentTree);
	}
	
	public static double log2(double x) {
	     return Math.log(x)/Math.log(2.0d);
	}
	
	public int findMinimum(int lower,int higher) {
		//check range
		if(lower >=0 && higher<noOfElements) {
			//return findSum(sumIdx,lower, higher);
			return findMinimumInRange(0, 0, noOfElements-1, lower, higher);
		}
		return -1;
	}
	
	public int findMinimumInRange(int sumIdx, int lowerForSumIdx,int higherForSumIdx,
			int lower,int higher) {
		if(lowerForSumIdx >= lower && higherForSumIdx <= higher) {
			//within range
			return segmentTree[sumIdx];
		} else {
			//range complete mismatch
			if(lowerForSumIdx > higher || higherForSumIdx < lower) {
				return Integer.MAX_VALUE;
			} else {
				//some range overlap
				int leftSumIdx = getLeftChildIdx(sumIdx);
				int rightSumIdx = getRightChildIdx(sumIdx);
				int mid = getMidPoint(lowerForSumIdx, higherForSumIdx);
				return Math.min(findMinimumInRange(leftSumIdx, lowerForSumIdx, mid, lower, higher)
							, findMinimumInRange(rightSumIdx, mid+1, higherForSumIdx, lower, higher));
			}
			
		} 
		
	}
}
