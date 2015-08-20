package tree.segmenttree;

import java.util.Arrays;
/**
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * Segment Tree | Set 1 (Sum of given range)
Let us consider the following problem to understand Segment Trees.

We have an array arr[0 . . . n-1]. We should be able to
1 Find the sum of elements from index l to r where 0 <= l <= r <= n-1 2 Change value of a specified element of the array arr[i] = x where 0 <= i <= n-1.
 * @author user
 *
 */
public class SumInRange {

	protected Integer[] segmentTree;
	//no of elements(not in segment tree)
	protected int noOfElements;
	protected Integer[] actualArray;
	
	public SumInRange(Integer []array) {
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
			
			segmentTree[sumIdx] = leftSum + rightSum;
			return segmentTree[sumIdx];
		}
	}
	
	public void update(int arrayIdx,int diff) {
		update(arrayIdx,diff,0,noOfElements-1,0);
	}
	protected void update(int arrayIdx, int diff, int start, int end, int idxOfTree) {
		if(start == end && start == arrayIdx) {
			segmentTree[idxOfTree] = segmentTree[idxOfTree] + diff;
		} else {
			if(arrayIdx >= start && arrayIdx <= end) {
				//in range
				segmentTree[idxOfTree] = segmentTree[idxOfTree] + diff;
				//update the child tree
				int mid = getMidPoint(start, end);
				if(arrayIdx <= mid) {
					//update in left sub tree
					int leftSumIdx = getLeftChildIdx(idxOfTree);
					update(arrayIdx, diff, start, mid, leftSumIdx);
				} else {
					//update in right sub tree
					int rightIdx = getRightChildIdx(idxOfTree);
					update(arrayIdx, diff, mid+1, end, rightIdx);
				}
			}
			
			
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
	
	public int findSum(int lower,int higher) {
		//check range
		if(lower >=0 && higher<noOfElements) {
			//return findSum(sumIdx,lower, higher);
			return findSumInRange(0, 0, noOfElements-1, lower, higher);
		}
		return -1;
	}
	
	public int findSumInRange(int sumIdx, int lowerForSumIdx,int higherForSumIdx,
			int lower,int higher) {
		if(lowerForSumIdx >= lower && higherForSumIdx <= higher) {
			//within range
			return segmentTree[sumIdx];
		} else {
			//range complete mismatch
			if(lowerForSumIdx > higher || higherForSumIdx < lower) {
				return 0;
			} else {
				//some range overlap
				int leftSumIdx = getLeftChildIdx(sumIdx);
				int rightSumIdx = getRightChildIdx(sumIdx);
				int mid = getMidPoint(lowerForSumIdx, higherForSumIdx);
				return findSumInRange(leftSumIdx, lowerForSumIdx, mid, lower, higher)
							+ findSumInRange(rightSumIdx, mid+1, higherForSumIdx, lower, higher);
			}
			
		} 
		
	}
}
